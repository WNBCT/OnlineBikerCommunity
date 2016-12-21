package com.example.sathapornsunthornpan.onlinebikercommunity.blog;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import com.example.sathapornsunthornpan.onlinebikercommunity.R;
import com.example.sathapornsunthornpan.onlinebikercommunity.config.Constants;

public class ScrollingBlogActivity extends AppCompatActivity {

    private CollapsingToolbarLayout toolbarLayout;
    private ImageView imageView;
    private TextView details;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling_blog);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();
        String blog_id = bundle.getString("blog_id", "ID");
        String blog_title = bundle.getString("blog_title", "Title");
        String blog_desc = bundle.getString("blog_desc", "Desc");
        String blog_user = bundle.getString("blog_user", "User");
        String blog_image = bundle.getString("blog_image", "Image");


        toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        imageView = (ImageView) findViewById(R.id.imageViewBlogCover);
        details = (TextView) findViewById(R.id.txtViewDetails);


        if (!blog_image.contains("http://")) {
            blog_image = "" + Constants.RootURL(blog_image);
            Log.d("Image", "if => " + blog_image);
        }

        toolbarLayout.setTitle(blog_title);
        details.setText(
                blog_title
                        + System.getProperty("line.separator")+System.getProperty("line.separator")
                        + "โพสโดย: " + blog_user
                        + System.getProperty("line.separator")+System.getProperty("line.separator")
                        + blog_desc
        );

        Glide
                .with(this)
                .load(blog_image)
                .centerCrop()
                .placeholder(R.drawable.christ_the_redeemer)
                .crossFade()
                .into(imageView);


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setTag(R.drawable.ic_like);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if( fab.getTag().toString().equals("" + R.drawable.ic_like)){
                    fab.setTag(R.drawable.ic_liked);
                    fab.setImageResource(R.drawable.ic_liked);
                    Snackbar.make(view, "liked", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else{
                    fab.setTag(R.drawable.ic_like);
                    fab.setImageResource(R.drawable.ic_like);
                    Snackbar.make(view, "like", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }
}
