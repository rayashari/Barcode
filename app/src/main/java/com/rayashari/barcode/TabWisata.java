package com.rayashari.barcode;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by rayasha on 2/4/2017.
 */

public class TabWisata extends Fragment {

    private FirebaseAuth mAuth;
    private TextView textView;
    private RecyclerView mWisataList;
    private DatabaseReference mDatabase;
    private DatabaseReference mDatabaseStar;
    private boolean mProcessStar = false;
    public TabWisata(){

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Wisata");
        mDatabaseStar = FirebaseDatabase.getInstance().getReference().child("Stars");
        mDatabase.keepSynced(true);
        mDatabaseStar.keepSynced(true);
//        mAuth = FirebaseAuth.getInstance();
//        FirebaseUser user = mAuth.getCurrentUser();
//        textView.setText(user.getDisplayName());
    }

    @Override
    public void onStart() {
        super.onStart();

        mAuth = FirebaseAuth.getInstance();

        final FirebaseRecyclerAdapter<Wisata, MainActivity.WisataViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Wisata, MainActivity.WisataViewHolder>(

                Wisata.class,
                R.layout.wisata_row,
                MainActivity.WisataViewHolder.class,
                mDatabase


        ){
            @Override
            protected void populateViewHolder(MainActivity.WisataViewHolder viewHolder, Wisata model, int position) {
                final String wisata_key = getRef(position).getKey();

                viewHolder.setTitle(model.getWisata_title());
                viewHolder.setDesc(model.getWisata_desc());
                viewHolder.setImage(getActivity().getApplicationContext(),model.getWisata_image());

                viewHolder.setStarBtn(wisata_key);

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getActivity(), wisata_key, Toast.LENGTH_SHORT).show();
                    }
                });

                viewHolder.mStarbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mProcessStar = true;



                            mDatabaseStar.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if(mProcessStar) {
                                        if (dataSnapshot.child(wisata_key).hasChild(mAuth.getCurrentUser().getUid())) {

                                            mDatabaseStar.child(wisata_key).child(mAuth.getCurrentUser().getUid()).removeValue();
                                            mProcessStar = false;

                                        } else {

                                            mDatabaseStar.child(wisata_key).child(mAuth.getCurrentUser().getUid()).setValue("RandomValue");

                                            mProcessStar = false;

                                        }
                                    }

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

                    }
                });

            }
        };

        mWisataList.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab_wisata,container,false);
        mWisataList = (RecyclerView) rootView.findViewById(R.id.wisata_list);
        mWisataList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        mWisataList.setLayoutManager(llm);

        return rootView;
    }


}
