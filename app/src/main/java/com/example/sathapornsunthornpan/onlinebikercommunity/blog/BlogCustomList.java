package com.example.sathapornsunthornpan.onlinebikercommunity.blog;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sathapornsunthornpan.onlinebikercommunity.R;

import java.util.List;


public class BlogCustomList extends ArrayAdapter<Blog> {

    public Activity context;
    public List<Blog> blogs;

    public BlogCustomList(Activity context, MainBlog mainBlog) {
        super(context, R.layout.blog_list_layout, mainBlog.result);
        this.context = context;
        this.blogs = mainBlog.result;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.blog_list_layout, null, true);
        TextView textViewID = (TextView) listViewItem.findViewById(R.id.txtBlogID);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.txtBlogName);

        textViewID.setText(blogs.get(position).id);
        textViewName.setText(blogs.get(position).name);

        return  listViewItem;
    }
}
