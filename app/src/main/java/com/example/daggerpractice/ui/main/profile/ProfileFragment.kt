package com.example.daggerpractice.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.daggerpractice.R
import com.example.daggerpractice.model.Resource
import com.example.daggerpractice.model.User
import com.example.daggerpractice.vm.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_profile.view.*
import javax.inject.Inject

class ProfileFragment: DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: ProfileViewModel
    private lateinit var binding: ViewDataBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ProfileViewModel::class.java)
        subscribeObserver()
    }

    private fun subscribeObserver(){
        viewModel.getLiveUser().removeObservers(viewLifecycleOwner)
        viewModel.getLiveUser().observe(viewLifecycleOwner, Observer {
            when(it.status){
                Resource.AuthStatus.SUCCESS -> setUserDetail(it.data)
                Resource.AuthStatus.ERROR -> setError(it.message)
                else -> setError("Something wrong")
            }
        })
    }
    private fun setUserDetail(data: User?) {
        binding.root.email.text = data?.email
        binding.root.username.text = data?.username
        binding.root.website.text = data?.website
    }

    private fun setError(message: String?) {
        binding.root.email.text = message
    }
}