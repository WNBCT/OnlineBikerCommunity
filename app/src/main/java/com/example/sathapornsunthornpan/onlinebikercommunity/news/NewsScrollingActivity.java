package com.example.sathapornsunthornpan.onlinebikercommunity.news;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sathapornsunthornpan.onlinebikercommunity.R;

public class NewsScrollingActivity extends AppCompatActivity {
    CollapsingToolbarLayout toolbar_layout;
    TextView detail_news;
    ImageView image;
    String title, detail;
    int img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle i = getIntent().getExtras();

        title = i.getString("title", "");
        detail = i.getString("detail", "");
        img = i.getInt("img", 0);


        toolbar_layout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        detail_news = (TextView) findViewById(R.id.detail);
        image = (ImageView) findViewById(R.id.image);

        toolbar_layout.setTitle(title);
        detail_news.setText(detail);
        image.setImageResource(img);


    }
}
