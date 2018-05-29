package com.daydayup.myjddemo.model.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daydayup.myjddemo.R;
import com.daydayup.myjddemo.model.Shouye_bean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by 因为有个你i on 2018/5/18.
 */

public class Miaoshaadapter  extends RecyclerView.Adapter<Miaoshaadapter.Mymiaoshahodler> {
    private Context context;
    private List<Shouye_bean.MiaoshaBean.ListBeanX> miaoshalist;

    public Miaoshaadapter(Context context, List<Shouye_bean.MiaoshaBean.ListBeanX> miaoshalist) {
        this.context = context;
        this.miaoshalist = miaoshalist;
    }

    @Override
    public Mymiaoshahodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.shouye_tuijian_zi, null);
        Mymiaoshahodler mymiaoshahodler=new Mymiaoshahodler(inflate);
        return mymiaoshahodler;
    }

    @Override
    public void onBindViewHolder(Mymiaoshahodler holder, int position) {
        holder.jiugongge_tv.setText(miaoshalist.get(position).getTitle());
        String images = miaoshalist.get(position).getImages();
        String[] split = images.split(".jpg");
        holder.sd.setImageURI(Uri.parse(split[0]+".jpg"));
    }

    @Override
    public int getItemCount() {
        return miaoshalist.size();
    }

    class Mymiaoshahodler extends RecyclerView.ViewHolder{
        public SimpleDraweeView sd;
        public TextView jiugongge_tv;
        public Mymiaoshahodler(View itemView) {
            super(itemView);
            this.sd=itemView.findViewById(R.id.sd);
            this.jiugongge_tv=itemView.findViewById(R.id.jiugongge_tv);
        }
    }
}
