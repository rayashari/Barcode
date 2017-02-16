package com.rayashari.barcode;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

//import com.firebase.client.ValueEventListener;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener ,TabLayout.OnTabSelectedListener {

    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;
    private View header;
    private TextView name;
    private TextView email;
    private ImageView photo;
    private DrawerLayout drawer;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String[] pageTitle = {"Wisata","Tempat","Transportasi","Cuaca"};
//    private RecyclerView mWisataList;
//    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */,
                        this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        viewPager = (ViewPager)findViewById(R.id.pager);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        for( int i=0;i<4;i++){
            tabLayout.addTab(tabLayout.newTab().setText(pageTitle[i]));
        }
        //set gravity for tab bar
        {
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        }

        Pager pagerAdapter = new Pager(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setupWithViewPager(viewPager);


//        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
//
//        tabLayout.addTab(tabLayout.newTab().setText("Wisata"));
//        tabLayout.addTab(tabLayout.newTab().setText("Tempat"));
//        tabLayout.addTab(tabLayout.newTab().setText("Transportasi"));
//        tabLayout.addTab(tabLayout.newTab().setText("Cuaca"));
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//
//        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        viewPager = (ViewPager) findViewById(R.id.pager);
//
//        Pager adapter = new Pager(getSupportFragmentManager(), tabLayout.getTabCount());
//
//        viewPager.setAdapter(adapter);
////        tabLayout.addOnTabSelectedListener(this);
//        tabLayout.setupWithViewPager(viewPager);

//        mDatabase = FirebaseDatabase.getInstance().getReference().child("Wisata");

//        mWisataList = (RecyclerView) findViewById(R.id.wisata_list);
//        mWisataList.setHasFixedSize(true);
//        mWisataList.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });




        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

        //set viewpager adapter

//        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });


        header = navigationView.getHeaderView(0);
        name = (TextView) header.findViewById(R.id.name_user);
        email = (TextView) header.findViewById(R.id.email_user);
        photo = (ImageView) header.findViewById(R.id.image_user);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        name.setText(user.getDisplayName());
        email.setText(user.getEmail());
        Picasso.with(this).load(user.getPhotoUrl()).resize(200,200).into(photo);


    }


//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        FirebaseRecyclerAdapter<Wisata, WisataViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Wisata, WisataViewHolder>(
//
//                Wisata.class,
//                R.layout.wisata_row,
//                WisataViewHolder.class,
//                mDatabase
//
//
//        ){
//            @Override
//            protected void populateViewHolder(WisataViewHolder viewHolder, Wisata model, int position) {
//
//                viewHolder.setTitle(model.getWisata_title());
//                viewHolder.setDesc(model.getWisata_desc());
//            }
//        };
//
//        mWisataList.setAdapter(firebaseRecyclerAdapter);
//    }

    public static class WisataViewHolder extends RecyclerView.ViewHolder{

        View mView;

        ImageButton mStarbtn;
        DatabaseReference mDatabaseStar;
        FirebaseAuth mAuth;

        public WisataViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

            mStarbtn = (ImageButton) mView.findViewById(R.id.star_btn);

            mDatabaseStar = FirebaseDatabase.getInstance().getReference().child("Stars");
            mAuth = FirebaseAuth.getInstance();

            mDatabaseStar.keepSynced(true);

        }

        public void setStarBtn(final String wisata_key){

            mDatabaseStar.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if(dataSnapshot.child(wisata_key).hasChild(mAuth.getCurrentUser().getUid())){

                        mStarbtn.setImageResource(R.drawable.ic_star_black_24dp);

                    } else {

                        mStarbtn.setImageResource(R.drawable.ic_star_border_black_24dp);

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

        public void setTitle(String title){

            TextView wisata_title = (TextView) mView.findViewById(R.id.wisata_title);
            wisata_title.setText(title);
        }
        public void setDesc(String desc){
            TextView wisata_desc = (TextView) mView.findViewById(R.id.wisata_desc);
            wisata_desc.setText(desc);
        }
        public void setImage(final Context ctx, final String image){

            final ImageView wisata_image = (ImageView) mView.findViewById(R.id.wisata_image);
//            Picasso.with(ctx).load(image).into(wisata_image);

            Picasso.with(ctx).load(image).networkPolicy(NetworkPolicy.OFFLINE).into(wisata_image, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError() {
                    Picasso.with(ctx).load(image).into(wisata_image);
                }
            });

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    private void displaySelectedScreen(int itemId) {
//
//        //creating fragment object
//        Fragment fragment = null;
//
//
//        //initializing the fragment object which is selected
//        switch (itemId) {
//
//            case R.id.nav_favorites:
//                fragment = new FavoritesActivity();
//                break;
//            case R.id.nav_logout:
//                FirebaseAuth mAuth = FirebaseAuth.getInstance();
//                mAuth.signOut();
//                Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
//                        new ResultCallback<Status>() {
//                            @Override
//                            public void onResult(@NonNull Status status) {
//                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                                startActivity(intent);
//                                finish();
//                            }
//                        });
//                break;
//        }
//
//        //replacing the fragment
//        if (fragment != null) {
//            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            ft.replace(R.id.content_frame, fragment);
//            ft.commit();
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dashboard) {
            viewPager.setCurrentItem(0);
            // Handle the camera action
        } else if (id == R.id.nav_favorites) {
            Intent intent = new Intent(this, FavoritesActivity.class);
//            Intent.putExtra("string","Go Go Go");
        } else if (id == R.id.nav_logout) {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signOut();
            Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(@NonNull Status status) {
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });

        }

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
//        displaySelectedScreen(item.getItemId());
        return true;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
