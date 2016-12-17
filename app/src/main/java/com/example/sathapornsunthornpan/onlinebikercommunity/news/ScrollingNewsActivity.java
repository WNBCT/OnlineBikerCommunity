package com.example.sathapornsunthornpan.onlinebikercommunity.news;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sathapornsunthornpan.onlinebikercommunity.R;

public class ScrollingNewsActivity extends AppCompatActivity {

    CollapsingToolbarLayout toolbarLayout;
    ImageView imageView;
    TextView details;

    String titleString, dateString, creditString, imageString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling_news);

        Bundle bundle = getIntent().getExtras();

        titleString = bundle.getString("title", "Title");
        dateString = bundle.getString("date", "Date");
        creditString = bundle.getString("credit", "Category");
        imageString = bundle.getString("image", "");

        toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        imageView = (ImageView) findViewById(R.id.imageViewNewsCover);
        details = (TextView) findViewById(R.id.txtViewDetails);

        toolbarLayout.setTitle(titleString);
        details.setText(titleString);

        Glide
                .with(this)
                .load(imageString)
                .centerCrop()
                .placeholder(R.drawable.no_picture)
                .crossFade()
                .into(imageView);
    }
}
