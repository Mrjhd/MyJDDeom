package com.daydayup.myjddemo.view.view.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daydayup.myjddemo.R;
import com.daydayup.myjddemo.model.FenleiLeftBean;
import com.daydayup.myjddemo.model.FenleiRightBean;
import com.daydayup.myjddemo.model.adapter.FenleiRightAdapter;
import com.daydayup.myjddemo.model.adapter.FenleileftAdapter;
import com.daydayup.myjddemo.presenter.FenleiLeftPresenter;
import com.daydayup.myjddemo.presenter.FenleiRightPresenter;
import com.daydayup.myjddemo.utils.Api;
import com.daydayup.myjddemo.view.view.iview.FenleiLeftIView;
import com.daydayup.myjddemo.view.view.iview.FenleiRightIView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;



public class FenLeiFragment extends BaseFragment implements FenleiLeftIView, FenleiRightIView {

    @BindView(R.id.fenleileftrecler)
    RecyclerView fenleileftrecler;
    @BindView(R.id.fenleirightrecler)
    RecyclerView fenleirightrecler;
    Unbinder unbinder;
    private View view;
    private int id;
    private FenleileftAdapter fenleileftAdapter;
    private FenleiRightPresenter fenleiRightPresenter;
    //页面
    @Override
    View createView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.layout_fl, null);
        ButterKnife.bind(this, view);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        fenleileftrecler.setLayoutManager(linearLayoutManager);
        LinearLayoutManager linearLayoutManagerright=new LinearLayoutManager(getContext());
        fenleirightrecler.setLayoutManager(linearLayoutManagerright);
        FenleiLeftPresenter fenleiLeftPresenter = new FenleiLeftPresenter(this);
        fenleiLeftPresenter.loadList(Api.SORT_LEFT);
        fenleiRightPresenter = new FenleiRightPresenter(this);
        fenleiRightPresenter.loadRightList(Api.SORT_LEFT,1);
        return view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showList(final List<FenleiLeftBean.DataBean> list) {

        fenleileftAdapter = new FenleileftAdapter(getContext(), list);
        fenleileftrecler.setAdapter(fenleileftAdapter);
        fenleileftAdapter.setOnClickListener(new FenleileftAdapter.OnClickListener() {
            @Override
            public void OnDianji(View v, int position) {
                int cid = list.get(position).getCid();
                fenleiRightPresenter.loadRightList(Api.SORT_LEFT,cid);
            }
        });
    }
    @Override
    public void showRight(List<FenleiRightBean.DataBean> list) {
        FenleiRightAdapter fenleiRightAdapter = new FenleiRightAdapter(getContext(), list);
        fenleirightrecler.setAdapter(fenleiRightAdapter);
    }

    @Override
    public void showError(String e) {

    }


}
