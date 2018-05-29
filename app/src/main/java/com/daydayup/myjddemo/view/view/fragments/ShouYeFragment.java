package com.daydayup.myjddemo.view.view.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daydayup.myjddemo.R;
import com.daydayup.myjddemo.model.Jiugongge_bean;
import com.daydayup.myjddemo.model.Mymode;
import com.daydayup.myjddemo.model.Shouye_bean;
import com.daydayup.myjddemo.model.adapter.Myadapter;
import com.daydayup.myjddemo.presenter.Mypresente;
import com.daydayup.myjddemo.view.view.Iview;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;



public class ShouYeFragment extends BaseFragment implements Iview{


    private View view;
    private XRecyclerView rx;
    private View inflate;
    private List<Jiugongge_bean.DataBean> data;
    private List<String> lunbolist=new ArrayList<>();


    private Myadapter myadapter;
    private Handler handler=new Handler();

    private int mDistanceY;
    private XBanner myxbanner;
    private View view2;


    //页面
    @Override
    View createView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.layout_sy, null);
        rx = view.findViewById(R.id.rx);
        Mypresente mypresenter=new Mypresente();
        mypresenter.getmv1(this,new Mymode());
        mypresenter.getmv(getContext(),this,new Mymode());
        inflate = View.inflate(getContext(), R.layout.shouye_xbanner, null);
        rx.addHeaderView(inflate);


        return view;
    }


    @Override
    public void setadapter(final Context context, Shouye_bean shouye_bean) {
        List<Shouye_bean.DataBean> data1 = shouye_bean.getData();
        for (int i = 0; i < data1.size(); i++) {
            lunbolist.add(data1.get(i).getIcon());
        }
        myxbanner = inflate.findViewById(R.id.myxbanner);
        myxbanner.setData(lunbolist, null);
        myxbanner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                Glide.with(getContext()).load(lunbolist.get(position)).into((ImageView) view);
            }
        });
        myadapter = new Myadapter(context, data, shouye_bean);
        rx.setAdapter(myadapter);
        rx.setLayoutManager(new LinearLayoutManager(context));
        rx.setLoadingMoreEnabled(false);
        rx.setLoadingListener(new XRecyclerView.LoadingListener() {

            private FrameLayout fg;

            @Override
            public void onRefresh() {
                fg = view2.findViewById(R.id.fg);
                fg.setVisibility(View.GONE);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fg.setVisibility(View.VISIBLE);
                        rx.refreshComplete();
                    }
                },1000);
            }

            @Override
            public void onLoadMore() {

            }
        });
        rx.addOnScrollListener(new RecyclerView.OnScrollListener() {


            private TextView sousuo;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //滑动的距离
                mDistanceY += dy;
                //toolbar的高度
                view2 = View.inflate(context, R.layout.ding_item,null);
                FrameLayout f1_frag = view2.findViewById(R.id.fg);

                int toolbarHeight = f1_frag.getBottom();

                //当滑动的距离 <= toolbar高度的时候，改变Toolbar背景色的透明度，达到渐变的效果
                if (mDistanceY <= 300) {
                    float scale = (float) mDistanceY / 300;
                    float alpha = scale * 255;
                    f1_frag.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));

                } else {
                    //将标题栏的颜色设置为完全不透明状态
                    f1_frag.setBackgroundResource(R.color.colorAccent);

                }
            }
        });


    }

    @Override
    public void setadapter1(List<Jiugongge_bean.DataBean> data) {

        this.data=data;
    }
}
