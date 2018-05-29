package com.daydayup.myjddemo.model.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daydayup.myjddemo.R;
import com.daydayup.myjddemo.model.FenleiLiebiaoBean;
import com.daydayup.myjddemo.model.MessageEvent;
import com.daydayup.myjddemo.view.view.activities.SecondActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 因为有个你i on 2018/5/23.
 */

public class LieBiaoAdapter extends RecyclerView.Adapter<LieBiaoAdapter.ViewHolder> {
    private Context context;
    private List<FenleiLiebiaoBean.DataBean> liebiaolist=new ArrayList<>();

    public LieBiaoAdapter(Context context, List<FenleiLiebiaoBean.DataBean> liebiaolist) {
        this.context = context;
        this.liebiaolist = liebiaolist;
    }

    @Override
    public LieBiaoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.liebiao_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LieBiaoAdapter.ViewHolder holder, final int position) {

        String images = liebiaolist.get(position).getImages();
        if (images!=null){
            String[] split = images.split("\\|");
            Uri uri = Uri.parse(split[0]);
            holder.liebiapimg.setImageURI(uri);
            holder.liebiao_title.setText(liebiaolist.get(position).getTitle());
            holder.liebiao_price.setText("价格："+liebiaolist.get(position).getPrice());
            holder.liebiao_num.setText("销量："+liebiaolist.get(position).getSalenum());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().postSticky(new MessageEvent(liebiaolist.get(position).getPid()+""));
                    Intent intent = new Intent(context, SecondActivity.class);
                    context.startActivity(intent);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return liebiaolist.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public SimpleDraweeView liebiapimg;
        public TextView liebiao_title,liebiao_price,liebiao_num;
        public ViewHolder(View itemView) {
            super(itemView);
            liebiapimg=itemView.findViewById(R.id.liebiao_img);
            liebiao_title=itemView.findViewById(R.id.liebiao_name);
            liebiao_price=itemView.findViewById(R.id.liebiao_price);
            liebiao_num=itemView.findViewById(R.id.liebiao_num);
        }
    }
}
