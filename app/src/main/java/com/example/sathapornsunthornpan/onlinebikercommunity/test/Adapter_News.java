package com.example.sathapornsunthornpan.onlinebikercommunity.test;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.sathapornsunthornpan.onlinebikercommunity.R;
import com.example.sathapornsunthornpan.onlinebikercommunity.model.WonderModel;

import java.util.ArrayList;



public class Adapter_News extends RecyclerView.Adapter<Adapter_News.VersionViewHolder> {
    Boolean isHomeList = false;

    Context context;
    OnItemClickListener clickListener;
    private ArrayList<WonderModel> list;

    public Adapter_News(Context applicationContext,ArrayList<WonderModel> list) {
        this.context = applicationContext;
        this.list = list;
    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycle_items_feed, viewGroup, false);

        VersionViewHolder viewHolder = new VersionViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VersionViewHolder versionViewHolder, int position) {

        versionViewHolder.titleTextView.setText(list.get(position).getCardName());
        versionViewHolder.coverImageView.setImageResource(list.get(position).getImageResourceId());
        versionViewHolder.coverImageView.setTag(list.get(position).getImageResourceId());
        versionViewHolder.likeImageView.setTag(R.drawable.ic_like);


    }

    @Override
    public int getItemCount() {

        return list == null ? 0 : list.size();
    }

    class VersionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView titleTextView;
        public ImageView coverImageView;
        public ImageView likeImageView;
        public ImageView shareImageView;


        public VersionViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.titleTextView);
            coverImageView = (ImageView) itemView.findViewById(R.id.coverImageView);
            likeImageView = (ImageView) itemView.findViewById(R.id.likeImageView);
            shareImageView = (ImageView) itemView.findViewById(R.id.shareImageView);

            if (isHomeList) {
                itemView.setOnClickListener(this);
            } else {
                itemView.setOnClickListener(this);
            }

            likeImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int id = (int)likeImageView.getTag();
                    if( id == R.drawable.ic_like){

                        likeImageView.setTag(R.drawable.ic_liked);
                        likeImageView.setImageResource(R.drawable.ic_liked);

                        Toast.makeText(context,titleTextView.getText()+" added to favourites",Toast.LENGTH_SHORT).show();

                    }else{

                        likeImageView.setTag(R.drawable.ic_like);
                        likeImageView.setImageResource(R.drawable.ic_like);
                        Toast.makeText(context,titleTextView.getText()+" removed from favourites",Toast.LENGTH_SHORT).show();

                    }
                }
            });


            shareImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                            "://" + context.getResources().getResourcePackageName(coverImageView.getId())
                            + '/' + "drawable" + '/' + context.getResources().getResourceEntryName((int)coverImageView.getTag()));


                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_STREAM,imageUri);
                    shareIntent.setType("image/jpeg");
                    context.startActivity(Intent.createChooser(shareIntent, context.getResources().getText(R.string.send_to)));

                }
            }); // end shareImageView



        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(v, getPosition());
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

}