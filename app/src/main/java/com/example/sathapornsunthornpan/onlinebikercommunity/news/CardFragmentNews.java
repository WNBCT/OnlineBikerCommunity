package com.example.sathapornsunthornpan.onlinebikercommunity.news;



import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sathapornsunthornpan.onlinebikercommunity.R;

import java.util.ArrayList;

public class CardFragmentNews extends Fragment {

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


        if (listitems.size() > 0 & MyRecyclerView != null) {
            MyRecyclerView.setAdapter(new MyAdapter(listitems));
        }

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }



    // class MyAdapter
    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private ArrayList<WonderModel> list;

        public MyAdapter(ArrayList<WonderModel> list) {
            this.list = list;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycle_items_feed, parent, false);
            MyViewHolder holder = new MyViewHolder(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.titleTextView.setText(list.get(position).getCardName());
            holder.coverImageView.setImageResource(list.get(position).getImageResourceId());
            holder.coverImageView.setTag(list.get(position).getImageResourceId());
            holder.likeImageView.setTag(R.drawable.ic_like);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    } // End Class MyAdapter


    // class MyViewHolder
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTextView;
        public ImageView coverImageView;
        public ImageView likeImageView;
        public ImageView shareImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.titleTextView);
            coverImageView = (ImageView) itemView.findViewById(R.id.coverImageView);
            likeImageView = (ImageView) itemView.findViewById(R.id.likeImageView);
            shareImageView = (ImageView) itemView.findViewById(R.id.shareImageView);

            likeImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id = (int)likeImageView.getTag();
                    if( id == R.drawable.ic_like){

                        likeImageView.setTag(R.drawable.ic_liked);
                        likeImageView.setImageResource(R.drawable.ic_liked);

                        Toast.makeText(getActivity(),titleTextView.getText()+" added to favourites",Toast.LENGTH_SHORT).show();

                    }else{

                        likeImageView.setTag(R.drawable.ic_like);
                        likeImageView.setImageResource(R.drawable.ic_like);
                        Toast.makeText(getActivity(),titleTextView.getText()+" removed from favourites",Toast.LENGTH_SHORT).show();


                    }
                }
            });


            shareImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                            "://" + getResources().getResourcePackageName(coverImageView.getId())
                            + '/' + "drawable" + '/' + getResources().getResourceEntryName((int)coverImageView.getTag()));


                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_STREAM,imageUri);
                    shareIntent.setType("image/jpeg");
                    startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.send_to)));

                }
            }); // end shareImageView
        }
    } // end class MyViewHolder


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

