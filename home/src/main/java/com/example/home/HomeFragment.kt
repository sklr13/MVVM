package com.example.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.core.BaseApplication
import com.example.core.data.domain_entity.RepositoryModel
import com.example.core.ui.BaseFragment
import com.example.home.di.HomeComponent
import javax.inject.Inject
import androidx.recyclerview.widget.DividerItemDecoration

class HomeFragment : BaseFragment() {

    @Inject
    lateinit var homeViewModelFactory: ViewModelProvider.Factory

    private var repositoryAdapter: RepositoryAdapter? = null

    private val homeViewModel: HomeViewModel by viewModels {
        homeViewModelFactory
    }

    override fun getLayoutId(): Int = R.layout.home_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        HomeComponent.setup((activity?.application as BaseApplication).getComponent()).inject(this)
        super.onCreate(savedInstanceState)
        homeViewModel.getErrors().observe(
            this, Observer { handleError(it.throwable, it.runnable) }
        )
        repositoryAdapter = RepositoryAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        showProgress()
        homeViewModel.getRepos().observe(
            this, Observer { showList(it) }
        )
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList(view)
    }

    private fun initList(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.repository_list)
        recyclerView.adapter = repositoryAdapter
        val dividerItem = DividerItemDecoration(
            context,
            DividerItemDecoration.VERTICAL
        )
        val dividerDrawable =
            ContextCompat.getDrawable(requireContext(), R.drawable.default_divider)
        dividerDrawable?.let { dividerItem.setDrawable(it) }
        recyclerView.addItemDecoration(dividerItem)
    }

    private fun showList(reposList: List<RepositoryModel>?) {
        hideProgress()
        repositoryAdapter?.items = reposList
        repositoryAdapter?.notifyDataSetChanged()
    }

    override fun tryAgain(retryAction: (() -> Unit)?) {
        showProgress()
        retryAction?.let { it() }
    }
}