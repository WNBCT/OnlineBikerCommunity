package com.example.sathapornsunthornpan.onlinebikercommunity.blog;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.example.sathapornsunthornpan.onlinebikercommunity.R;
import com.example.sathapornsunthornpan.onlinebikercommunity.config.Constants;
import com.example.sathapornsunthornpan.onlinebikercommunity.model.BlogModel;
import com.example.sathapornsunthornpan.onlinebikercommunity.model.ListBlog;
import com.example.sathapornsunthornpan.onlinebikercommunity.service.BlogAPIService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlogFragment extends Fragment {

    private SharedPreferences pref;

    private RecyclerView recyclerView;
    private ArrayList<ListBlog> listItem = new ArrayList<>();
    private BlogAdapter blogAdapter;

    public BlogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_card_blog, container, false);
        getActivity().setTitle(R.string.menu_blog);

        Log.d("Blog", "111111");
        initViews(view);

        return view;
    }


    private void initViews(View view) {
        Log.d("Blog", "2222222");
        pref = getActivity().getSharedPreferences("LOGIN", Context.MODE_PRIVATE);

        recyclerView = (RecyclerView) view.findViewById(R.id.cardViewBlog);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        blogAdapter = new BlogAdapter(getActivity(), listItem);
        recyclerView.setAdapter(blogAdapter);

        blogAdapter.SetOnItemClickListener(new BlogAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //  intent page show blog
                Intent intent = new Intent(getActivity(), ScrollingBlogActivity.class);
                intent.putExtra("blog_id", listItem.get(position).getBlogId());
                intent.putExtra("blog_title", listItem.get(position).getTitle());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("Blog", "3333333");

        String s = pref.getString(Constants.NAME, "The M");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BlogAPIService service = retrofit.create(BlogAPIService.class);
        Call<List<ListBlog>> responseCall = service.getBlogList();
        responseCall.enqueue(new Callback<List<ListBlog>>() {
            @Override
            public void onResponse(Call<List<ListBlog>> call, Response<List<ListBlog>> response) {

                List<ListBlog> blogs = response.body();

                Log.d("Blog", "Response : " + response.code());

                for (ListBlog list : blogs ) {
                    listItem.add(list);
                }
                blogAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ListBlog>> call, Throwable t) {
                Log.d("Blog", "Failure " + t.getMessage());
            }
        });
    }

}
