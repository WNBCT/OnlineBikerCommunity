package com.example.sathapornsunthornpan.onlinebikercommunity.forum;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toolbar;

import com.example.sathapornsunthornpan.onlinebikercommunity.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainForumFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private TravelListAdapter mAdapter;
    private boolean isListView;
    private Menu menu;
    View view;


    public MainForumFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main_forum, container, false);
        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.list);
        mRecyclerView.setLayoutManager(mStaggeredLayoutManager);

        mRecyclerView.setHasFixedSize(true); //Data size is fixed - improves performance
        mAdapter = new TravelListAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);


        isListView = true;
        return view;
    }



}
