package com.agoda.sneakershop.screen.sneaker.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.agoda.sneakershop.R
import com.agoda.sneakershop.SneakerShopApplication
import com.agoda.sneakershop.screen.base.BaseLceFragment
import com.hannesdorfmann.mosby.mvp.ParcelerDataLceViewState
import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState
import kotlinx.android.synthetic.main.fragment_sneaker_list.*
import javax.inject.Inject

class SneakerListFragment : BaseLceFragment<SneakerListViewModel, SneakerListView, SneakerListPresenter>(), SneakerListView {

    @Inject
    lateinit var injectedPresenter: SneakerListPresenter
    
    val sneakerListAdapter = SneakerListAdapter()
    
    override val layoutId: Int = R.layout.fragment_sneaker_list

    interface Listener {
        fun openSneakerDetail(sneakerId: Long)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        SneakerShopApplication.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sneakerListAdapter.onItemClick = (fun(sneakerListItemViewModel: SneakerListItemViewModel): Unit {
            (activity as Listener).openSneakerDetail(sneakerListItemViewModel.id)
            return Unit
        })
        rvSneakerList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = sneakerListAdapter
        }
        srlSneakerList.setOnRefreshListener { loadData(true) }
    }

    override fun createViewState(): LceViewState<SneakerListViewModel, SneakerListView> = ParcelerDataLceViewState()
    
    override fun getData(): SneakerListViewModel = presenter.viewModel
    
    override fun createPresenter(): SneakerListPresenter = injectedPresenter
    
    override fun setData(data: SneakerListViewModel) {
        srlSneakerList.isRefreshing = false
        sneakerListAdapter.items = data.items
        presenter.viewModel = data
    }

    override fun showError(e: Throwable, pullToRefresh: Boolean) {
        super.showError(e, pullToRefresh)
        srlSneakerList.isRefreshing = false
    }

    override fun showContent() {
        super.showContent()
        srlSneakerList.isRefreshing = false
    }

    override fun loadData(pullToRefresh: Boolean) {
        presenter.refreshData(pullToRefresh)
    }
}
