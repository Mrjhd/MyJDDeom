package com.daydayup.myjddemo.view.view.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daydayup.myjddemo.R;
import com.daydayup.myjddemo.model.CartBean;
import com.daydayup.myjddemo.model.adapter.RecyAdapter;
import com.daydayup.myjddemo.presenter.MyPresenter;
import com.daydayup.myjddemo.view.view.iview.MyViewCallBack;



public class GouWuCheFragment extends BaseFragment implements MyViewCallBack {


    private View view;
    private RecyclerView recyclerView;
    private TextView total_price;
    private TextView total_num;
    private CheckBox quanxuan;
    private MyPresenter myPresenter;
    private RecyAdapter recyAdapter;
    private TextView bianji;
    private LinearLayout linear_shanchu;
    private TextView quzhifu;
    private TextView shanchu;

    @Override
    View createView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.layout_gwc, null);
        recyclerView = view.findViewById(R.id.recycler_View);
        total_price = view.findViewById(R.id.total_price);
        total_num = view.findViewById(R.id.total_num);
        quanxuan = view.findViewById(R.id.quanxuan);
        bianji = view.findViewById(R.id.bianji);
        linear_shanchu = view.findViewById(R.id.linear_shanchu);
        quzhifu = view.findViewById(R.id.quzhifu);
        shanchu = view.findViewById(R.id.shanchu);
        bianji.setTag(1);//编辑设置tag
        quanxuan.setTag(1);//1为不选中
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);

        myPresenter = new MyPresenter(this);
        recyAdapter = new RecyAdapter(getActivity());
        //进入页面查询购物车的数据
        myPresenter.select();

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(recyAdapter);
        //调用recyAdapter里面的接口,设置 全选按钮 总价 总数量
        recyAdapter.setUpdateListener(new RecyAdapter.UpdateListener() {
            @Override
            public void setTotal(String total, String num, boolean allCheck) {
                //设置ui的改变
                total_num.setText("共"+num+"件商品");//总数量
                total_price.setText("总价 :¥"+total+"元");//总价
                if(allCheck){
                    quanxuan.setTag(2);

                }else{
                    quanxuan.setTag(1);



                }
                quanxuan.setChecked(allCheck);
            }
        });

        //这里只做ui更改, 点击全选按钮,,调到adapter里面操作
        quanxuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用adapter里面的方法 ,,把当前quanxuan状态传递过去

                int tag = (int) quanxuan.getTag();
                if(tag==1){
                    quanxuan.setTag(2);

                }else{
                    quanxuan.setTag(1);

                }

                recyAdapter.quanXuan(quanxuan.isChecked());
            }
        });

        //点击批量删除的按钮
        shanchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyAdapter.shanChu();
            }
        });

        //点击编辑按钮,
        bianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tag = (int) bianji.getTag();
                if(tag==1){
                    bianji.setText("完成");
                    bianji.setTag(2);
                    quzhifu.setVisibility(View.GONE);
                    linear_shanchu.setVisibility(View.VISIBLE);
                }else{
                    bianji.setText("编辑");
                    bianji.setTag(1);
                    quzhifu.setVisibility(View.VISIBLE);
                    linear_shanchu.setVisibility(View.GONE);
                }
            }
        });
        return view;
    }


    @Override
    public void success(CartBean cartBean) {
        if(cartBean!=null) {
            //System.out.println(cartBean.getData().get(0).getList().get(0).getTitle());
            //将返回的数据 添加到适配器里
            recyAdapter.add(cartBean);
        }
    }

    @Override
    public void failure() {
        System.out.println("网不好");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        myPresenter.detach();
    }
}
