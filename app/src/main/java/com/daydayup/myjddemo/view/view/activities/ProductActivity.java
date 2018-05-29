package com.daydayup.myjddemo.view.view.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.daydayup.myjddemo.R;
import com.daydayup.myjddemo.model.FenleiEvent;
import com.daydayup.myjddemo.model.FenleiLiebiaoBean;
import com.daydayup.myjddemo.model.adapter.LieBiaoAdapter;
import com.daydayup.myjddemo.presenter.LieBiaoPresenter;
import com.daydayup.myjddemo.utils.Api;
import com.daydayup.myjddemo.view.view.iview.LieBiaoIView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class ProductActivity extends AppCompatActivity implements LieBiaoIView {
    //    @Bind(R.id.liebiaorecycler)
    RecyclerView liebiaorecycler;
    private int id;
    private LieBiaoPresenter lieBiaoPresenter;

//    @Bind(R.id.pro_id)
//    TextView proId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
//        ButterKnife.bind(this);
        liebiaorecycler = (RecyclerView) findViewById(R.id.liebiaorecycler);
        EventBus.getDefault().register(this);

        lieBiaoPresenter = new LieBiaoPresenter(this);
        lieBiaoPresenter.loadRightList(Api.SORT_LEFT,id,1,0);
    }

    //在onStart调用register后，执行消息
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(FenleiEvent event) {
        id = event.getId();
//        lieBiaoPresenter.loadRightList(Api.HOST,id,1,0);
//        proId.setText(id+"");
//        Toast.makeText(getContext(),"id"+ android.R.attr.id,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void showList(List<FenleiLiebiaoBean.DataBean> list) {

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        liebiaorecycler.setLayoutManager(linearLayoutManager);
        LieBiaoAdapter lieBiaoAdapter = new LieBiaoAdapter(ProductActivity.this, list);
        liebiaorecycler.setAdapter(lieBiaoAdapter);
    }

    @Override
    public void showError(String e) {

    }
}
