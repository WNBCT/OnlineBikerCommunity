package com.example.sathapornsunthornpan.onlinebikercommunity.blog;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.sathapornsunthornpan.onlinebikercommunity.R;
import com.example.sathapornsunthornpan.onlinebikercommunity.config.Constants;
import com.example.sathapornsunthornpan.onlinebikercommunity.model.Blog2;
import com.example.sathapornsunthornpan.onlinebikercommunity.model.MyResponse;
import com.example.sathapornsunthornpan.onlinebikercommunity.service.BlogAPIService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddBlogActivity extends AppCompatActivity implements View.OnClickListener {

    // field
    private EditText titleEditText, descEditText, imageEditText;
    private CheckBox[] ch = new CheckBox[6];
    private Button buttonSave;

    private SweetAlertDialog pDialog;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_blog);
        setTitle(R.string.menu_add_blog);

        context = this;


        titleEditText = (EditText) findViewById(R.id.editBlogTitle);
        descEditText = (EditText) findViewById(R.id.editBlogDescription);
        imageEditText = (EditText) findViewById(R.id.editBlogImage);
        buttonSave = (Button) findViewById(R.id.btnInsertBlog);

        ch[0] = (CheckBox) findViewById(R.id.checkBoxBlog1);
        ch[1] = (CheckBox) findViewById(R.id.checkBoxBlog2);
        ch[2] = (CheckBox) findViewById(R.id.checkBoxBlog3);
        ch[3] = (CheckBox) findViewById(R.id.checkBoxBlog4);
        ch[4] = (CheckBox) findViewById(R.id.checkBoxBlog5);
        ch[5] = (CheckBox) findViewById(R.id.checkBoxBlog6);




        pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);


        buttonSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String title, desc, img;
        title = titleEditText.getText().toString();
        desc = descEditText.getText().toString();
        img = imageEditText.getText().toString();


        if (!title.isEmpty() && !desc.isEmpty() && !img.isEmpty()) {
            process();
        } else {
            pDialog = new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
            pDialog.setTitleText("Oops...");
            pDialog.setContentText("กรุณาใส่ข้อมูลด้วยครับ");
            pDialog.show();
        }

    }

    private void process() {
        pDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL())
                .addConverterFactory(GsonConverterFactory.create()).build();

        BlogAPIService service = retrofit.create(BlogAPIService.class);

        SharedPreferences pref = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);

        Blog2 blog = new Blog2();
        blog.setTitle(titleEditText.getText().toString());
        blog.setDesc(descEditText.getText().toString());
        blog.setImg(imageEditText.getText().toString());
        blog.setUser_id(pref.getString(Constants.USER_ID, "255900004")); // TODO
        blog.setTags(getSelecetCheckBox());


        Log.d("Insert", "onResponse " + blog);
        Log.d("Insert", "onResponse " + getSelecetCheckBox().toString());

        Call<MyResponse> responseCall = service.insertBlog(blog);

        responseCall.enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                Log.d("Insert", "onResponse " + response.message());
                MyResponse serve = response.body();

                if (serve.getResult().equals("success")) {
                    Log.d("Insert", "onResponse " + response.body().getMessage());
                    pDialog.hide();

                    pDialog = new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
                    pDialog.setTitleText("Success!");
                    pDialog.setContentText("บันทึกข้อมูลสำเร็จ");
                    pDialog.show();

                    pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            pDialog.hide();
                            finish();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<MyResponse> call, Throwable t) {
                Log.d("Insert", "onFailure " + call.request().toString());
                Log.d("Insert", "onFailure " + t.getMessage());
                pDialog.hide();

                pDialog = new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
                pDialog.setTitleText("Oops...");
                pDialog.setContentText("Something went wrong!");
                pDialog.show();
            }
        });
    }


    public ArrayList<Integer> getSelecetCheckBox() {
        ArrayList<Integer> integers = new ArrayList<>();

        for (int i = 0; i < ch.length; i++) {
            if (ch[i].isChecked()){
                integers.add(101 + i);
            }
        }
        return integers;
    }
}






