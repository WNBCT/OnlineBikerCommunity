package com.example.sathapornsunthornpan.onlinebikercommunity.blog;


import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sathapornsunthornpan.onlinebikercommunity.R;
import com.example.sathapornsunthornpan.onlinebikercommunity.model.BlogModel;
import com.example.sathapornsunthornpan.onlinebikercommunity.model.ListBlog;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import java.util.List;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.VersionViewHolder> {

    private OnItemClickListener itemClickListener;
    private Context context;
    private List<ListBlog> list;




    public BlogAdapter(Context context, List<ListBlog> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_items_blog, parent, false);

        return new BlogAdapter.VersionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VersionViewHolder holder, int position) {
        Glide
                .with(context)
                .load(list.get(position).getImage())
                .centerCrop()
                .placeholder(R.drawable.christ_the_redeemer)
                .crossFade()
                .into(holder.coverImageView);

        holder.titleTextView.setText(list.get(position).getTitle());
        holder.showUser.setText("โพสโดย : " + list.get(position).getUser());


    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    class VersionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView coverImageView, likeImageView;
        TextView titleTextView, showUser;


        public VersionViewHolder(View itemView) {
            super(itemView);
            coverImageView = (ImageView) itemView.findViewById(R.id.coverImageView);
            likeImageView = (ImageView) itemView.findViewById(R.id.likeImageView);
            titleTextView = (TextView) itemView.findViewById(R.id.titleTextView);
            showUser = (TextView) itemView.findViewById(R.id.textShowUser);

            itemView.setOnClickListener(this);

            likeImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int id;
                    Log.d("TEST", likeImageView.getTag() + "");
                    if (likeImageView.getTag() != null) {
                        id = (int) likeImageView.getTag();
                    } else {
                        id = 2130837622;
                    }

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

//            starButton.setLiked(true);
        }


        @Override
        public void onClick(View view) {
            itemClickListener.onItemClick(view, getPosition());
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
