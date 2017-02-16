package com.rayashari.barcode;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by rayasha on 2/4/2017.
 */

public class Pager extends FragmentPagerAdapter {

//    private String[] tabTitles = new String[]{"Wisata","Tempat","Transportasi","Cuaca"};
    public Pager(FragmentManager fm){
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
//        switch(position){
//            case 0:
//                TabWisata tabWisata = new TabWisata();
//                return tabWisata;
//            case 1:
//                TabTempat tabTempat = new TabTempat();
//                return tabTempat;
//            case 2:
//                TabTransportasi tabTransportasi = new TabTransportasi();
//                return tabTransportasi;
//            case 3:
//                TabCuaca tabCuaca = new TabCuaca();
//                return  tabCuaca;
//
//        }
//        return null;
        if(position==0){

            return new TabWisata();

        } else if(position == 1){

            return new TabTempat();

        } else if(position == 2){

            return new TabTransportasi();

        } else return new TabCuaca();
    }

    @Override
    public int getCount() {

        return 4;
    }
//
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return tabTitles[position];
//    }
}
