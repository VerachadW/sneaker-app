package com.agoda.sneakershop.screen.sneaker.detail

import android.os.Bundle
import android.view.View
import com.agoda.sneakershop.R
import com.agoda.sneakershop.SneakerShopApplication
import com.agoda.sneakershop.common.extension.format
import com.agoda.sneakershop.common.extension.setImageUrl
import com.agoda.sneakershop.screen.base.BaseLceFragment
import com.hannesdorfmann.mosby.mvp.ParcelerDataLceViewState
import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState
import kotlinx.android.synthetic.main.fragment_sneaker_detail.*
import javax.inject.Inject

class SneakerDetailFragment : BaseLceFragment<SneakerDetailViewModel, SneakerDetailView, SneakerDetailPresenter>(), SneakerDetailView {

    companion object {

        private const val ARG_SNEAKER_ID = "ARG_SNEAKER_ID"

        @JvmStatic
        fun instance(id: Long) = SneakerDetailFragment().apply {
            arguments = Bundle().apply {
                putLong(ARG_SNEAKER_ID, id)
            }
        }

    }

    @Inject
    lateinit var injectedPresenter: SneakerDetailPresenter

    override val layoutId: Int = R.layout.fragment_sneaker_detail

    val sneakerId by lazy { arguments.getLong(ARG_SNEAKER_ID, 0L) }

    override fun onCreate(savedInstanceState: Bundle?) {
        SneakerShopApplication.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun createPresenter(): SneakerDetailPresenter = injectedPresenter

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        srlSneakerDetail.setOnRefreshListener {
            loadData(true)
        }
    }

    override fun setData(data: SneakerDetailViewModel) {
        presenter.viewModel = data
        srlSneakerDetail.isRefreshing = false
        tvSneakerDetailName.text = data.name
        tvSneakerDetailDescription.text = data.description
        tvSneakerDetailCategoryName.text = data.categoryName
        tvSneakerDetailCollectiomName.text = data.collectionName
        tvSneakerDetailPrice.text = "$ ${data.price.format(2)}"
        ivSneakerDetailImage.setImageUrl(data.imageUrl)
    }

    override fun showContent() {
        super.showContent()
        srlSneakerDetail.isRefreshing = false
    }

    override fun showError(e: Throwable?, pullToRefresh: Boolean) {
        super.showError(e, pullToRefresh)
        srlSneakerDetail.isRefreshing = false
    }

    override fun createViewState(): LceViewState<SneakerDetailViewModel, SneakerDetailView>
            = ParcelerDataLceViewState()

    override fun loadData(pullToRefresh: Boolean) {
        presenter.refreshData(sneakerId, pullToRefresh)
    }

    override fun getData(): SneakerDetailViewModel = presenter.viewModel

}
