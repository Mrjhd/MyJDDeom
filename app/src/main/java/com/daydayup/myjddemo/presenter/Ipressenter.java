package com.daydayup.myjddemo.presenter;

import android.content.Context;

import com.daydayup.myjddemo.model.Imode;
import com.daydayup.myjddemo.view.view.Iview;

/**
 * Created by 因为有个你i on 2018/5/18.
 */

public interface Ipressenter {
    void getmv(Context context, Iview iview, Imode imode);



    void getmv1(Iview iview, Imode imode);
}
