package com.example.daggerpractice.ui.main.post

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import com.example.daggerpractice.SessionManager
import com.example.daggerpractice.model.Comment
import com.example.daggerpractice.model.Post
import com.example.daggerpractice.model.Resource
import com.example.daggerpractice.network.main.MainAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostsViewModel @Inject constructor(application: Application,
                                         private val mainAPI: MainAPI,
                                         private val sessionManager: SessionManager): AndroidViewModel(application) {

    private val livePosts = MediatorLiveData<Resource<List<Post>>>()
    private val liveComments = MediatorLiveData<Int>()

    fun getLivePosts(): LiveData<Resource<List<Post>>> {
        livePosts.value = Resource.loading()
        val source = LiveDataReactiveStreams.fromPublisher(
            mainAPI.getPostsFromUser(sessionManager.getCachedUser().value?.data?.id)
                .subscribeOn(Schedulers.io())
                .onErrorReturn{ listOf()}
                .map {
                    if(it.isEmpty()){
                        Resource.error(null, "Something wrong")
                    } else{
                        Resource.success(it)
                    }
                }
        )

        livePosts.addSource(source) {
            livePosts.value = it as Resource<List<Post>>?
            livePosts.removeSource(source)
        }

        return livePosts
    }
}