package com.example.sathapornsunthornpan.onlinebikercommunity.news.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sathapornsunthornpan.onlinebikercommunity.R;
import com.example.sathapornsunthornpan.onlinebikercommunity.news.model.FeedModel;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.VersionViewHolder> {

    private OnItemClickListener itemClickListener;
    private Context context;
    private List<FeedModel> list;


    public NewsAdapter(Context context, List<FeedModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_items_feed_news, parent, false);

        return new NewsAdapter.VersionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VersionViewHolder holder, int position) {
//        holder.coverImageView;
        Glide
                .with(context)
                .load(list.get(position).getImage())
                .centerCrop()
                .placeholder(R.drawable.no_picture)
                .crossFade()
                .into(holder.coverImageView);

        holder.titleNewsTextView.setText(list.get(position).getTitle());
        holder.dateNewsTextView.setText(list.get(position).getDate());
        holder.creditNewsTextView.setText(list.get(position).getCategory());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class VersionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView coverImageView;
        TextView titleNewsTextView;
        TextView dateNewsTextView;
        TextView creditNewsTextView;

        public VersionViewHolder(View itemView) {
            super(itemView);
            coverImageView = (ImageView) itemView.findViewById(R.id.imageViewNews);
            titleNewsTextView = (TextView) itemView.findViewById(R.id.txtItemTitleNews);
            dateNewsTextView = (TextView) itemView.findViewById(R.id.txtItemDateNews);
            creditNewsTextView = (TextView) itemView.findViewById(R.id.txtItemCreditNews);

            itemView.setOnClickListener(this);
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
