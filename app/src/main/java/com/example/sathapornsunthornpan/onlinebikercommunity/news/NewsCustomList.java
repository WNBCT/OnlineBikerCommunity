package com.example.sathapornsunthornpan.onlinebikercommunity.news;

import android.app.Activity;
import android.graphics.Picture;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sathapornsunthornpan.onlinebikercommunity.R;

/**
 * Created by sathapornsunthornpan on 12/2/16.
 */

public class NewsCustomList extends ArrayAdapter<String> {


    private String[] names;
    private String[] desc;
    private Integer[] imageid;
    private Activity context;

    public NewsCustomList(Activity context, String[] names, String[] desc, Integer[] imageid) {
        super(context, R.layout.news_list_layout, names);
        this.context = context;
        this.names = names;
        this.desc = desc;
        this.imageid = imageid;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.news_list_layout, null, true);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.txtVNewsTitle);
        TextView textViewDesc = (TextView) listViewItem.findViewById(R.id.txtVNewsDesc);
        ImageView image =  (ImageView) listViewItem.findViewById(R.id.imgVNews);

        textViewName.setText(names[position]);
        textViewDesc.setText(desc[position]);
        image.setImageResource(imageid[position]);
        return  listViewItem;
    }
}
