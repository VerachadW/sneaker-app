package com.agoda.sneakershop.screen.sneaker.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.agoda.sneakershop.R;
import com.agoda.sneakershop.SneakerShopApplication;
import com.agoda.sneakershop.screen.base.BaseLceFragment;
import com.hannesdorfmann.mosby.mvp.ParcelerDataLceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;

import javax.inject.Inject;

import butterknife.BindView;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class SneakerListFragment extends BaseLceFragment<SneakerListViewModel, SneakerListView, SneakerListPresenter> implements SneakerListView {

    public interface Listener {
        void openSneakerDetail(Long sneakerId);
    }

    @Inject
    SneakerListPresenter injectedPresenter;

    @BindView(R.id.rvSneakerList)
    RecyclerView rvSneakerList;
    @BindView(R.id.srlSneakerList)
    SwipeRefreshLayout srlSneakerList;

    SneakerListAdapter sneakerListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        SneakerShopApplication.appComponent.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_sneaker_list;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sneakerListAdapter = new SneakerListAdapter();
        sneakerListAdapter.setOnItemClick((new Function1<SneakerListItemViewModel, Unit>() {
            @Override
            public Unit invoke(SneakerListItemViewModel sneakerListItemViewModel) {
                ((Listener) getActivity()).openSneakerDetail(sneakerListItemViewModel.getId());
                return Unit.INSTANCE;
            }
        }));
        rvSneakerList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvSneakerList.setAdapter(sneakerListAdapter);
        srlSneakerList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData(true);
            }
        });
    }

    @Override
    public LceViewState<SneakerListViewModel, SneakerListView> createViewState() {
        return new ParcelerDataLceViewState<>();
    }

    @Override
    public SneakerListViewModel getData() {
        return presenter.getViewModel();
    }

    @Override
    public SneakerListPresenter createPresenter() {
        return injectedPresenter;
    }

    @Override
    public void setData(SneakerListViewModel data) {
        srlSneakerList.setRefreshing(false);
        sneakerListAdapter.setItems(data.getItems());
        presenter.setViewModel(data);
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        super.showError(e, pullToRefresh);
        srlSneakerList.setRefreshing(false);
    }

    @Override
    public void showContent() {
        super.showContent();
        srlSneakerList.setRefreshing(false);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.refreshData(pullToRefresh);
    }
}
