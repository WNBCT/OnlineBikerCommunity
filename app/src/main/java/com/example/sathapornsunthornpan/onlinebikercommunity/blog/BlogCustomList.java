package com.example.sathapornsunthornpan.onlinebikercommunity.blog;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sathapornsunthornpan.onlinebikercommunity.R;
import com.example.sathapornsunthornpan.onlinebikercommunity.model.BlogModel;
import com.example.sathapornsunthornpan.onlinebikercommunity.model.MainBlogModel;

import java.util.List;


public class BlogCustomList extends ArrayAdapter<BlogModel> {

    public Activity context;
    public List<BlogModel> blogModels;

    public BlogCustomList(Activity context, MainBlogModel mainBlogModel) {
        super(context, R.layout.blog_list_layout, mainBlogModel.result);
        this.context = context;
        this.blogModels = mainBlogModel.result;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.blog_list_layout, null, true);
        TextView textViewID = (TextView) listViewItem.findViewById(R.id.txtBlogID);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.txtBlogName);

        textViewID.setText(blogModels.get(position).id);
        textViewName.setText(blogModels.get(position).name);

        return  listViewItem;
    }
}
