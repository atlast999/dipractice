package com.example.daggerpractice.ui.main.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.daggerpractice.R
import com.example.daggerpractice.model.Resource
import com.example.daggerpractice.util.VerticalItemDecaration
import com.example.daggerpractice.vm.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_posts.view.*
import timber.log.Timber
import javax.inject.Inject

class PostsFragment: DaggerFragment() {

    private lateinit var viewModel: PostsViewModel
    private lateinit var binding: ViewDataBinding

    @Inject
    lateinit var postsAdapter: PostsAdapter
    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Toast.makeText(activity, "Posts fragment...", Toast.LENGTH_LONG).show()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_posts, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this, factory).get(PostsViewModel::class.java)

        initRecyclerView()
        subscribeObserver()
    }

    private fun subscribeObserver(){
        viewModel.getLivePosts().removeObservers(viewLifecycleOwner)
        viewModel.getLivePosts().observe(viewLifecycleOwner, Observer {
            if(it.status == Resource.AuthStatus.SUCCESS){
                postsAdapter.setPosts(it.data!!)
            } else{
                Timber.i("Cant get posts")
            }
        })
    }

    private fun initRecyclerView() {
        binding.root.recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(VerticalItemDecaration(15))
            this.adapter = postsAdapter
        }
    }
}