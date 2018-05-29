package com.daydayup.myjddemo.model.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.List;

/**
 * Created by 因为有个你i on 2018/5/28.
 */

public class ViewPagerAdapter extends PagerAdapter{



    public void addData(List<String> listImage) {
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}
