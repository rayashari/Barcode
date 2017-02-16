package com.rayashari.barcode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by rayasha on 2/4/2017.
 */

public class TabTempat extends Fragment {

    private FragmentTabHost mTabhost;

    public TabTempat(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.tab_tempat, container, false);

//        mTabhost = new FragmentTabHost(getActivity());
//        mTabhost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);
//
//        mTabhost.addTab(mTabhost.newTabSpec("tempatsemua").setIndicator("Fragment Tempat Semua"), TabTempatSemua.class, null);
//        mTabhost.addTab(mTabhost.newTabSpec("tempatrestoran").setIndicator("Fragment Restoran"), TabTempatRestoran.class, null);
//        mTabhost.addTab(mTabhost.newTabSpec("tempatbankdanatm").setIndicator("Fragment Bank dan ATM"), TabTempatBankdanatm.class, null);
//        mTabhost.addTab(mTabhost.newTabSpec("tempatibadah").setIndicator("Fragment Ibadah"), TabTempatIbadah.class, null);
//
//
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
}
