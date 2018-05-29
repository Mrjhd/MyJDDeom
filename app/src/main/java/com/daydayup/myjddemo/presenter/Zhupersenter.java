package com.daydayup.myjddemo.presenter;

import com.daydayup.myjddemo.view.view.iview.ZView;

import com.daydayup.myjddemo.model.RegBean;
import com.daydayup.myjddemo.model.ZhuMyodel;

/**
 * Created by 因为有个你i on 2018/5/18.
 */

public class Zhupersenter {
    private final ZView zview;
    private final ZhuMyodel zhuMyodel;

    public Zhupersenter(ZView zview) {
        this.zview = zview;
        this.zhuMyodel=new ZhuMyodel();
    }
    public void zhustart(String url,String mobile,String password){
        zhuMyodel.zhuLog(url,mobile,password);
        zhuMyodel.setChangce(new ZhuMyodel.setOverzhu() {
            @Override
            public void zhuOver(RegBean zhu) {
                if (zhu.getCode().equals("0")){
                    zview.zhusucess();
                }else{
                    zview.zhufarile();
                }
            }
        });
    }
}
