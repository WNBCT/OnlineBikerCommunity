package com.example.sathapornsunthornpan.onlinebikercommunity.blog;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.sathapornsunthornpan.onlinebikercommunity.R;
import com.example.sathapornsunthornpan.onlinebikercommunity.config.Constants;

import okhttp3.HttpUrl;


public class AddBlogFragment extends Fragment implements View.OnClickListener {

    // session preferences
    private SharedPreferences preferences;
    private HttpUrl url;
    private ProgressBar progressBar;

    // field
    private EditText titleEditText, descEditText, imageEditText;
    private CheckBox[] ch = new CheckBox[6];
    private AppCompatButton btnSave;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_blog, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        preferences = getActivity().getSharedPreferences("first", Context.MODE_PRIVATE);

        progressBar = (ProgressBar) view.findViewById(R.id.progress);
        btnSave = (AppCompatButton) view.findViewById(R.id.btnInsertBlog);

        titleEditText = (EditText) view.findViewById(R.id.editBlogTitle);
        descEditText = (EditText) view.findViewById(R.id.editBlogDescription);
        imageEditText = (EditText) view.findViewById(R.id.editBlogImage);
        ch[0] = (CheckBox) view.findViewById(R.id.checkBoxBlog1);
        ch[1] = (CheckBox) view.findViewById(R.id.checkBoxBlog2);
        ch[2] = (CheckBox) view.findViewById(R.id.checkBoxBlog3);
        ch[3] = (CheckBox) view.findViewById(R.id.checkBoxBlog4);
        ch[4] = (CheckBox) view.findViewById(R.id.checkBoxBlog5);
        ch[5] = (CheckBox) view.findViewById(R.id.checkBoxBlog6);

        for (int i = 0; i < ch.length; i++) {
            if (ch[i].isChecked()){

            }
        }


        btnSave.setOnClickListener(this);

        // make url
        url = new HttpUrl.Builder()
                .scheme(Constants.SCHEMA)
                .host(Constants.HOST).port(Constants.PORT)
                .addPathSegments(Constants.URI)
                .build();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnInsertBlog:
                insertBlog();
        }
    }

    private void insertBlog() {

    }
}
