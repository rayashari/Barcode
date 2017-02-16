package com.rayashari.barcode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

/**
 * Created by rayasha on 2/15/2017.
 */

public class TabTempatRestoran extends FragmentActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_tempat_restoran);

        TabTempat fragmentTab = new TabTempat();
        getSupportFragmentManager().beginTransaction().add(R.id.item_detail_container_restoran,fragmentTab).commit();
    }
}
