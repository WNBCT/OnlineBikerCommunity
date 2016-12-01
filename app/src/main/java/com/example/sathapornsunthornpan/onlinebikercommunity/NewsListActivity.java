package com.example.sathapornsunthornpan.onlinebikercommunity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sathapornsunthornpan.onlinebikercommunity.news.NewsCustomList;

public class NewsListActivity extends AppCompatActivity {

    private ListView listView;
    private String names[] = {
            "HTML",
            "CSS",
            "Java Script",
            "Wordpress"
    };

    private String desc[] = {
            "The Powerful Hypter Text Markup Language 5",
            "Cascading Style Sheets",
            "Code with Java Script",
            "Manage your content with Wordpress"
    };


    private Integer imageid[] = {
            R.drawable.icon_app_v1,
            R.drawable.icon_app_v1,
            R.drawable.icon_app_v1,
            R.drawable.icon_app_v1
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        NewsCustomList customList = new NewsCustomList(this, names, desc, imageid);

        listView = (ListView) findViewById(R.id.listViewNews);
        listView.setAdapter(customList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),"You Clicked "+names[i],Toast.LENGTH_SHORT).show();
            }
        });
    }
}
