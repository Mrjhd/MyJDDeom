package com.daydayup.myjddemo.model.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daydayup.myjddemo.R;
import com.daydayup.myjddemo.model.FenleiEvent;
import com.daydayup.myjddemo.model.FenleiRightBean;
import com.daydayup.myjddemo.view.view.activities.ProductActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by 因为有个你i on 2018/5/23.
 */

public class FenleiChildAdapter  extends RecyclerView.Adapter<FenleiChildAdapter.ViewHolder>{
    private Context context;
    private List<FenleiRightBean.DataBean.ListBean> listBean;

    public FenleiChildAdapter(Context context, List<FenleiRightBean.DataBean.ListBean> listBean) {
        this.context = context;
        this.listBean = listBean;
    }

    @Override
    public FenleiChildAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fenlei_right_child, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FenleiChildAdapter.ViewHolder holder, final int position) {
        holder.childimg.setImageURI(listBean.get(position).getIcon());
        holder.childname.setText(listBean.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().postSticky(new FenleiEvent(listBean.get(position).getPcid()));
                Intent intent = new Intent(context, ProductActivity.class);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return listBean.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public SimpleDraweeView childimg;
        public TextView childname;
        public ViewHolder(View itemView) {
            super(itemView);
            childimg=itemView.findViewById(R.id.right_child_img);
            childname=itemView.findViewById(R.id.right_child_name);
        }
    }
}
