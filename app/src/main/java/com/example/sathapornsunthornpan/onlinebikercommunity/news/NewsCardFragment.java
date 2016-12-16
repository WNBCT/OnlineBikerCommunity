package com.example.sathapornsunthornpan.onlinebikercommunity.news;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sathapornsunthornpan.onlinebikercommunity.R;
import com.example.sathapornsunthornpan.onlinebikercommunity.adapter.NewsAdapter;
import com.example.sathapornsunthornpan.onlinebikercommunity.model.FeedModel;
import com.example.sathapornsunthornpan.onlinebikercommunity.model.NewsFeedModel;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsCardFragment extends Fragment {

    private HttpUrl url;
    private RecyclerView recyclerView;
    private ArrayList<FeedModel> listItem = new ArrayList<>();
    private NewsAdapter newsAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("News & Activity");


        // make url
        url = new HttpUrl.Builder()
                .scheme("http")
                .host("10.71.27.157").port(80)
                .addPathSegments("project/csc4202/test/getFeed.php")
                .build();

        callAsync();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_news_feed, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.cardViewNewsFeed);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        newsAdapter = new NewsAdapter(getActivity(), listItem);
        recyclerView.setAdapter(newsAdapter);


        // on item click
        newsAdapter.SetOnItemClickListener(new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();



            }
        });

        return view;
    }

    private void callAsync() {
        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient okHttpClient = new OkHttpClient();

                Request.Builder builder = new Request.Builder();
                Request request = builder.url(url).build();

                try {
                    Response response = okHttpClient.newCall(request).execute();
                    if (response.isSuccessful()) {
                        return response.body().string();
                    } else {
                        return "Not Success - code : " + response.code();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error - " + e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                getResponseData(s);
            }
        }.execute();
    }


    private void getResponseData(String s) {
        // Gson
        Gson gson = new Gson();

        NewsFeedModel newsFeedModel = gson.fromJson(s, NewsFeedModel.class);

        Log.d("gsonTEST", newsFeedModel.getFeed() + "");

        List<FeedModel> lists = newsFeedModel.getFeed();

        for (FeedModel list : lists ) {
            listItem.add(list);
        }

        newsAdapter.notifyDataSetChanged();
    }
}
