package com.daydayup.myjddemo.model.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.daydayup.myjddemo.R;
import com.daydayup.myjddemo.model.Jiugongge_bean;
import com.daydayup.myjddemo.model.Shouye_bean;
import com.daydayup.myjddemo.view.view.iview.NoticeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 因为有个你i on 2018/5/18.
 */

public class Myadapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    List<Jiugongge_bean.DataBean> data ;
    private Shouye_bean shouye_bean;
    private List<String> notices;
    private Handler timeHandler;
    private View inflate;
    private View inflate1;
    private View inflate2;

    public Myadapter(Context context, List<Jiugongge_bean.DataBean> data , Shouye_bean shouye_bean) {
        this.context = context;
        this.data = data;
        this.shouye_bean = shouye_bean;

    }
    private long mHour = 02;
    private long mMin = 15;
    private long mSecond = 36;
    private boolean isRun = true;

    //定义常量  确定多条目加载类型
    final static int TYPE_ONE=1;
    final static int TYPE_TWO=2;
    final static int TYPE_THREE=3;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_ONE:
                inflate =View.inflate(context,R.layout.shouyerc,null);
                Jiugongge jiugongge=new Jiugongge(inflate);
                return jiugongge;
            case TYPE_TWO:
                inflate1 = View.inflate(context, R.layout.shouye_tuijian, null);
                Tuijian tuijian=new Tuijian(inflate1);
                return tuijian;
            case TYPE_THREE:
                inflate2 = View.inflate(context, R.layout.shouyemiaosha_item, null);
                Miaosha miaosha=new Miaosha(inflate2);
                return miaosha;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof Jiugongge){
            Myjiugongge myjiugongge=new Myjiugongge(context,data);
            ((Jiugongge) holder).shouye_rc.setAdapter(myjiugongge);
            ((Jiugongge) holder).shouye_rc.setLayoutManager(new GridLayoutManager(context,2,GridLayoutManager.HORIZONTAL,false));
            notices = new ArrayList<>();
            notices.add("大促销下单拆福袋，亿万新年红包随便拿");
            notices.add("家电五折团，抢十亿无门槛现金红包");
            notices.add("星球大战剃须刀首发送200元代金券");
            ((Jiugongge) holder).notice_view.addNotice(notices);
            ((Jiugongge) holder).notice_view.startFlipping();
            ((Jiugongge) holder).notice_view.setOnNoticeClickListener(new NoticeView.OnNoticeClickListener() {
                @Override
                public void onNotieClick(int position, String notice) {
                    Toast.makeText(context, notices.get(position),Toast.LENGTH_SHORT).show();
                }
            });
        }

        if (holder instanceof Tuijian){
            ((Tuijian) holder).shouye_tuijian_tv.setText("京东推荐");
            List<Shouye_bean.TuijianBean.ListBean> list = shouye_bean.getTuijian().getList();
            Mytuijian mytuijian=new Mytuijian(context,list);
            ((Tuijian) holder).shouye_tuijian_rc.setAdapter(mytuijian);
            ((Tuijian) holder).shouye_tuijian_rc.setLayoutManager(new GridLayoutManager(context,1,GridLayoutManager.HORIZONTAL,false));
        }

        if (holder instanceof Miaosha){
            ((Miaosha) holder).shouue_miaosha_tv.setText("京东秒杀");
            ((Miaosha) holder).shouue_miaosha_tv1.setText("16点场");
            startRun();
            timeHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    if (msg.what==1) {
                        computeTime();
                        if (mHour<10){
                            ((Miaosha) holder).tv_hour.setText("0"+mHour+"");
                        }else {
                            ((Miaosha) holder).tv_hour.setText("0"+mHour+"");
                        }
                        if (mMin<10){
                            ((Miaosha) holder).tv_minute.setText("0"+mMin+"");
                        }else {
                            ((Miaosha) holder).tv_minute.setText(mMin+"");
                        }
                        if (mSecond<10){
                            ((Miaosha) holder).tv_second.setText("0"+mSecond+"");
                        }else {
                            ((Miaosha) holder).tv_second.setText(mSecond+"");
                        }
                    }
                }
            };

            List<Shouye_bean.MiaoshaBean.ListBeanX> miaoshalist = shouye_bean.getMiaosha().getList();
            Miaoshaadapter miaosha=new Miaoshaadapter(context,miaoshalist);
            ((Miaosha) holder).miaosha_rv.setAdapter(miaosha);
            ((Miaosha) holder).miaosha_rv.setLayoutManager(new GridLayoutManager(context,2));

        }
    }

    /**
     * 开启倒计时
     */
    private void startRun() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (isRun) {
                    try {
                        Thread.sleep(1000); // sleep 1000ms
                        Message message = Message.obtain();
                        message.what = 1;
                        timeHandler.sendMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * 倒计时计算
     */
    private void computeTime() {
        mSecond--;
        if (mSecond < 0) {
            mMin--;
            mSecond = 59;
            if (mMin < 0) {
                mMin = 59;
                mHour--;
            }
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return TYPE_ONE;
        }
        if (position==1){
            return TYPE_TWO;
        }
        if (position==2){
            return TYPE_THREE;
        }
        return 0;
    }
    class Miaosha extends RecyclerView.ViewHolder{
        public TextView shouue_miaosha_tv;
        public TextView shouue_miaosha_tv1;
        public TextView tv_hour;
        public TextView tv_minute;
        public TextView tv_second;
        public RecyclerView miaosha_rv;
        public Miaosha(View itemView) {
            super(itemView);
            this.shouue_miaosha_tv=itemView.findViewById(R.id.shouue_miaosha_tv);
            this.shouue_miaosha_tv1=itemView.findViewById(R.id.shouue_miaosha_tv1);
            this.tv_hour=itemView.findViewById(R.id.tv_hour);
            this.tv_minute=itemView.findViewById(R.id.tv_minute);
            this.tv_second=itemView.findViewById(R.id.tv_second);
            this.miaosha_rv=itemView.findViewById(R.id.miaosha_rv);
        }
    }

    class Tuijian extends RecyclerView.ViewHolder{
        public TextView shouye_tuijian_tv;
        public RecyclerView shouye_tuijian_rc;
        public Tuijian(View itemView) {
            super(itemView);
            this.shouye_tuijian_rc=itemView.findViewById(R.id.shouye_tuijian_rc);
            this.shouye_tuijian_tv=itemView.findViewById(R.id.shouye_tuijian_tv);
        }
    }

    class Jiugongge extends RecyclerView.ViewHolder{
        public RecyclerView shouye_rc;
        public NoticeView notice_view;
        public Jiugongge(View itemView) {
            super(itemView);
            this.shouye_rc=itemView.findViewById(R.id.shouye_rc);
            this.notice_view=itemView.findViewById(R.id.notice_view);
        }
    }
}
