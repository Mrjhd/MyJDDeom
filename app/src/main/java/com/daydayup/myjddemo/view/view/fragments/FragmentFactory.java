package com.daydayup.myjddemo.view.view.fragments;



public class FragmentFactory {
    public static BaseFragment createFragement(int type) {
        BaseFragment baseFragment = null;
        switch (type) {
            case 0:
                baseFragment = new ShouYeFragment();
                break;
            case 1:
                baseFragment = new FenLeiFragment();
                break;
            case 2:
                baseFragment = new FaXianFragment();
                break;
            case 3:
                baseFragment = new GouWuCheFragment();
                break;
            case 4:
                baseFragment = new WoDeFragment();
                break;
        }
        return baseFragment;
    }
}
