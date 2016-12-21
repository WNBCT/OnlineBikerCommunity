package com.example.sathapornsunthornpan.onlinebikercommunity.forum.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sathapornsunthornpan.onlinebikercommunity.R;
import com.example.sathapornsunthornpan.onlinebikercommunity.model.FeedForumModel;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ForumAdapter extends RecyclerView.Adapter<ForumAdapter.VersionViewHolder> {

    private OnItemClickListener itemClickListener;
    private Context context;
    private List<FeedForumModel> list;


    public ForumAdapter(Context context, List<FeedForumModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_items_feed_forum, parent, false);
        return new ForumAdapter.VersionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VersionViewHolder holder, int position) {
//        holder.coverImageView;

        holder.titleForumTextView.setText(list.get(position).getTitle());
        holder.creditForumTextView.setText("โดย: " + list.get(position).getName());

        long longTimeAgo    = timeStringtoMilis(list.get(position).getDate());
        PrettyTime prettyTime = new PrettyTime();

//        holder.dateForumTextView.setText(list.get(position).getDate());
        holder.dateForumTextView.setText("เมื่อ: " + prettyTime.format(new Date(longTimeAgo)));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    private long timeStringtoMilis(String time) {
        long milis = 0;

        try {
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date 	= sd.parse(time);
            milis 		= date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return milis;
    }



    class VersionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView titleForumTextView;
        TextView dateForumTextView;
        TextView creditForumTextView;

        public VersionViewHolder(View itemView) {
            super(itemView);
            titleForumTextView = (TextView) itemView.findViewById(R.id.txtItemTitleForum);
            dateForumTextView = (TextView) itemView.findViewById(R.id.txtItemDateForum);
            creditForumTextView = (TextView) itemView.findViewById(R.id.txtItemCreditForum);

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
