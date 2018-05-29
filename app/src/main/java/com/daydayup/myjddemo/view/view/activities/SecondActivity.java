package com.daydayup.myjddemo.view.view.activities;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.daydayup.myjddemo.R;
import com.daydayup.myjddemo.model.AddCartBean;
import com.daydayup.myjddemo.model.MessageEvent;
import com.daydayup.myjddemo.model.XQBean;
import com.daydayup.myjddemo.presenter.AddCartPresenter;
import com.daydayup.myjddemo.utils.Api;
import com.daydayup.myjddemo.utils.ApiServer;
import com.daydayup.myjddemo.view.view.AddCartViewCallBack;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SecondActivity extends AppCompatActivity implements AddCartViewCallBack{


    @BindView(R.id.goodsImg)
    ImageView goodsImg;
    @BindView(R.id.goodsTitle)
    TextView goodsTitle;
    @BindView(R.id.bargainPrice)
    TextView bargainPrice;
    @BindView(R.id.original_price)
    TextView originalPrice;
    @BindView(R.id.salenum)
    TextView salenum;
    @BindView(R.id.change)
    RelativeLayout change;
    @BindView(R.id.gongyingshang)
    TextView gongyingshang;
    @BindView(R.id.dianpu)
    TextView dianpu;
    @BindView(R.id.guanzhu)
    CheckBox guanzhu;
    @BindView(R.id.gouwuche)
    TextView gouwuche;
    @BindView(R.id.add_Buy)
    Button addBuy;
    private String pid;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
//        EventBus.getDefault().register(XiangQing.this);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.SORT_LEFT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        ApiServer apiService = retrofit.create(ApiServer.class);
        Observable<XQBean> goodsInfo = apiService.getGoodsInfo("2","android");
        goodsInfo.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<XQBean>() {


                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(XQBean xqBean) {

                        String[] images = xqBean.getData().getImages().split("\\|");
                        Glide.with(SecondActivity.this).load(images[0]).into(goodsImg);
                        goodsTitle.setText(xqBean.getData().getTitle());
                        bargainPrice.setText("¥"+xqBean.getData().getBargainPrice());
                        originalPrice.setText("原价 "+xqBean.getData().getPrice());
                        originalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
                        salenum.setText("销量" +xqBean.getData().getSalenum());
                    }
                });

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(MessageEvent event) {
        pid = event.getPid();
        Toast.makeText(this, "pid"+ pid, Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @OnClick(R.id.add_Buy)
    public void onViewClicked() {
        AddCartPresenter addCartPresenter=new AddCartPresenter(this);
        addCartPresenter.getData(pid);
    }

    @Override
    public void success(AddCartBean addCartBean) {

    }

    @Override
    public void failure() {

    }
}
