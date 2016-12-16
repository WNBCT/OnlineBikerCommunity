package com.example.sathapornsunthornpan.onlinebikercommunity.test;



import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sathapornsunthornpan.onlinebikercommunity.R;
import com.example.sathapornsunthornpan.onlinebikercommunity.model.WonderModel;

import java.util.ArrayList;

public class CardFragment extends Fragment {

    ArrayList<WonderModel> listitems = new ArrayList<>();
    RecyclerView MyRecyclerView;
    String Wonders[] = {"Chichen Itza","Christ the Redeemer","Great Wall of China","Machu Picchu","Petra","Taj Mahal","Colosseum"};
    int  Images[] = {R.drawable.chichen_itza,R.drawable.christ_the_redeemer,R.drawable.great_wall_of_china,R.drawable.machu_picchu,R.drawable.petra,R.drawable.taj_mahal,R.drawable.colosseum};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeList();
//        getActivity().setTitle("");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_news_feed, container, false);

        MyRecyclerView = (RecyclerView) view.findViewById(R.id.cardViewNewsFeed);
        MyRecyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        MyRecyclerView.setLayoutManager(layoutManager);

        Adapter_News adapter = new Adapter_News(getActivity(), listitems);
        MyRecyclerView.setAdapter(adapter);

        adapter.SetOnItemClickListener(new Adapter_News.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Toast.makeText(getActivity(), ""+position, Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getActivity(), NewsScrollingActivity.class);

                i.putExtra("title", listitems.get(position).getCardName());
                i.putExtra("detail", listitems.get(position).getCardName());
                i.putExtra("img", listitems.get(position).getImageResourceId());

                startActivity(i);

            }
        });


        return view;
    }

    public void initializeList() {
        listitems.clear();

        for(int i =0;i<7;i++){

            WonderModel item = new WonderModel();
            item.setCardName(Wonders[i]);
            item.setImageResourceId(Images[i]);
            item.setIsfav(0);
            item.setIsturned(0);
            listitems.add(item);

        }
    }
}

