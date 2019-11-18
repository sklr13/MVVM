package com.example.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.core.BaseApplication
import com.example.core.data.domain_entity.RepositoryModel
import com.example.core.ui.BaseFragment
import com.example.home.di.HomeComponent
import javax.inject.Inject

class HomeFragment : BaseFragment() {

    @Inject
    lateinit var homeViewModelFactory: ViewModelProvider.Factory

    private val homeViewModel: HomeViewModel by viewModels {
        homeViewModelFactory
    }

    override fun getLayoutId(): Int = R.layout.home_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        HomeComponent.setup((activity?.application as BaseApplication).getComponent()).inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        homeViewModel.getRepos().observe(
            this, Observer { showList(it) }
        )
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun showList(reposList: List<RepositoryModel>?) {
        Log.e("LIIIIIIZZZZTTTTTTTTTTT", "size${reposList?.size}")
    }
}