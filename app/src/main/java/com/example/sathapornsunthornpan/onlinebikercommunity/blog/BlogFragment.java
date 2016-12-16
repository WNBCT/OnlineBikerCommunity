package com.example.sathapornsunthornpan.onlinebikercommunity.blog;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.sathapornsunthornpan.onlinebikercommunity.R;
import com.example.sathapornsunthornpan.onlinebikercommunity.model.MainBlogModel;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class BlogFragment extends Fragment {

    ListView listView;
    HttpUrl url;
    private String dataBody;
    private View viewGo;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_news, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle(R.string.menu_blog);

        listView = (ListView) view.findViewById(R.id.listViewBlog);

        // make url
        url = new HttpUrl.Builder()
                .scheme("http")
                .host("10.71.10.147").port(80)
                .addPathSegments("android/basic-crud/getAllEmp.php")
                .build();

        viewGo = view;
        callSyncGet();
    }

    // method Synchronous Get
    private void callSyncGet() {

        // Create Instance from Anonymous Class
        new AsyncTask<Void, Void, Message>() {

            // การทำงานอีก เทรด นึง
            @Override
            protected Message doInBackground(Void... voids) {

                // เชื่อมต่อ server
                OkHttpClient okHttpClient = new OkHttpClient();

                Request.Builder builder = new Request.Builder();
                Request request = builder.url(url).build();

                Message message = new Message();

                try {
                    Response response = okHttpClient.newCall(request).execute();

                    // check status
                    if (response.isSuccessful()) {
                        message.what = 1;
                        message.obj = response.body().string(); // response body คือ ตัวข้อมูล
                    } else {
                        message.what = 0;
                        message.obj = "Not Success\ncode : " + response.code();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    message.what = 0;
                    message.obj = "Error\n" + e.getMessage();
                }

                return message;
            }


            // post จะทำงานใน thread ปัจจุบัน
            @Override
            protected void onPostExecute(Message message) {
                super.onPostExecute(message);
                Log.d("OkHttp1234: ", message.what + " " + message.obj);
//                Toast.makeText(getApplicationContext(), message.obj.toString(), Toast.LENGTH_LONG).show();

                switch (message.what) {
                    case 0:
                        dataBody = (String) message.obj;
                        break;
                    case 1:
                        getResponseData(message.obj.toString());
                        break;
                }

//                showView();

                message.recycle();
            }

        }.execute(); // end new AsyncTask

    }

    private void getResponseData(String stringJSON) {

        Gson gson = new Gson();

        MainBlogModel mainBlogModel = gson.fromJson(stringJSON, MainBlogModel.class);

        Log.d("Show BlogModel", mainBlogModel.toString());

        BlogCustomList blogCustomList = new BlogCustomList(getActivity(), mainBlogModel);
        listView = (ListView) viewGo.findViewById(R.id.listViewNews);
        listView.setAdapter(blogCustomList);


    }

}
