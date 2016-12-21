package com.example.sathapornsunthornpan.onlinebikercommunity.forum;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sathapornsunthornpan.onlinebikercommunity.R;
import com.example.sathapornsunthornpan.onlinebikercommunity.config.Constants;
import com.example.sathapornsunthornpan.onlinebikercommunity.forum.adapter.ForumAdapter;
import com.example.sathapornsunthornpan.onlinebikercommunity.model.FeedForumModel;
import com.example.sathapornsunthornpan.onlinebikercommunity.model.ForumModel;
import com.example.sathapornsunthornpan.onlinebikercommunity.model.MyResponse;
import com.example.sathapornsunthornpan.onlinebikercommunity.service.ForumAPIService;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForumFragment extends Fragment {

    private HttpUrl url;
    private RecyclerView recyclerView;
    private ArrayList<FeedForumModel> listItem = new ArrayList<>();
    private ForumAdapter forumAdapter;
    private EditText et_forum_topic, et_forum_detail;
    private TextView tv_message;
    private ProgressBar progress;
    private AlertDialog dialog;
    private SharedPreferences pref;
    public static final String ROOT_URL = "10.0.2.2";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.menu_forum);

        callAsync();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forum, container, false);
        initview(view);


        return view;
    }

    public void initview(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewForum);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        forumAdapter = new ForumAdapter(getActivity(), listItem);
        recyclerView.setAdapter(forumAdapter);


        // on item click
        forumAdapter.SetOnItemClickListener(new ForumAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();


            }
        });
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                showDialog();
            }
        });
    }

    private void callAsync() {
        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient okHttpClient = new OkHttpClient();

                Request.Builder builder = new Request.Builder();
                Request request = builder.url(Constants.URL("forum/all")).build();

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

        ForumModel forumModel = gson.fromJson(s, ForumModel.class);

        List<FeedForumModel> lists = forumModel.getFeed();

        for (FeedForumModel list : lists) {
            listItem.add(list);
        }

        forumAdapter.notifyDataSetChanged();
    }

    private void showDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_create_forum, null);
        et_forum_topic = (EditText) view.findViewById(R.id.et_forum_topic);
        et_forum_detail = (EditText) view.findViewById(R.id.et_forum_detail);
        tv_message = (TextView) view.findViewById(R.id.tv_message);
        progress = (ProgressBar) view.findViewById(R.id.progress);
        builder.setView(view);
        builder.setTitle("Create Topic");
        builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog = builder.create();
        dialog.show();

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pref = getActivity().getSharedPreferences("first", Context.MODE_PRIVATE);
                String forum_topic = et_forum_topic.getText().toString();
                String forum_detail = et_forum_detail.getText().toString();

                if (!forum_topic.isEmpty() && !forum_detail.isEmpty()) {
                    progress.setVisibility(View.VISIBLE);
                    pref = getActivity().getSharedPreferences("LOGIN", Context.MODE_PRIVATE);

                    String user_id = pref.getString(Constants.USER_ID, "");

                    createForumProcess(user_id, forum_topic,forum_detail);
                } else {

                    tv_message.setVisibility(View.VISIBLE);
                    tv_message.setText("Fields are empty");
                }
            }
        });
    }

    private void createForumProcess(String user_id, String forum_topic, String forum_detail) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ForumAPIService service = retrofit.create(ForumAPIService.class);

        Call<MyResponse> responseCall = service.insertForum(user_id, forum_topic, forum_detail);

        responseCall.enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(Call<MyResponse> call, retrofit2.Response<MyResponse> response) {
                Log.d("Forum", "onResponse :" +  response.message() + " " + response.code());
                if (response.body().getResult().equals("success")) {
                    progress.setVisibility(View.INVISIBLE);
                    dialog.dismiss();
                    Snackbar.make(getView() , "Success" , Snackbar.LENGTH_LONG).show();
                    
                }
            }

            @Override
            public void onFailure(Call<MyResponse> call, Throwable t) {
                Log.d("Forum", "onFailure :" + t.getMessage());
            }
        });

    }
}
