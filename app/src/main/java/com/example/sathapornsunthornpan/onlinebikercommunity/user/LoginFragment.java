package com.example.sathapornsunthornpan.onlinebikercommunity.user;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.sathapornsunthornpan.onlinebikercommunity.MainActivity;
import com.example.sathapornsunthornpan.onlinebikercommunity.R;
import com.example.sathapornsunthornpan.onlinebikercommunity.config.Constants;
import com.example.sathapornsunthornpan.onlinebikercommunity.user.service.RequestInterface;
import com.example.sathapornsunthornpan.onlinebikercommunity.user.models.ServerResponse;
import com.example.sathapornsunthornpan.onlinebikercommunity.user.models.User;

import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginFragment extends Fragment implements View.OnClickListener{

    private AppCompatButton btn_login;
    private EditText et_email,et_password;
    private TextView tv_register;
    private ProgressBar progress;
    private SharedPreferences pref;
    private HttpUrl url;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login,container,false);
        initViews(view);
        return view;
    }

    private void initViews(View view){

        pref = getActivity().getSharedPreferences("LOGIN" , Context.MODE_PRIVATE);

        btn_login = (AppCompatButton)view.findViewById(R.id.btn_login);
        tv_register = (TextView)view.findViewById(R.id.tv_register);
        et_email = (EditText)view.findViewById(R.id.et_email);
        et_password = (EditText)view.findViewById(R.id.et_password);

        progress = (ProgressBar)view.findViewById(R.id.progress);

        btn_login.setOnClickListener(this);
        tv_register.setOnClickListener(this);

        // make url
        url = new HttpUrl.Builder()
                .scheme(Constants.SCHEMA)
                .host(Constants.HOST).port(Constants.PORT)
                .addPathSegments(Constants.URI)
                .build();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.tv_register:
                goToRegister();
                break;

            case R.id.btn_login:
                String email = et_email.getText().toString();
                String password = et_password.getText().toString();

                if(!email.isEmpty() && !password.isEmpty()) {

                    progress.setVisibility(View.VISIBLE);
                    loginProcess(email,password);

                } else {

                    Snackbar.make(getView(), "Fields are empty !", Snackbar.LENGTH_LONG).show();
                }
                break;

        }
    }
    private void loginProcess(String email,String password){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        Call<ServerResponse> responseCall = requestInterface.loginUser(user);

        Log.d("Retrofit","failed " + responseCall.request().url());

        responseCall.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse resp = response.body();
                Snackbar.make(getView(), resp.getMessage(), Snackbar.LENGTH_LONG).show();

                if(resp.getResult().equals(Constants.SUCCESS)){

                    SharedPreferences.Editor editor = pref.edit();

                    editor.putBoolean(Constants.IS_LOGGED_IN,true);
                    editor.putString(Constants.USER_ID, resp.getUser().getUser_id());
                    editor.putString(Constants.EMAIL, resp.getUser().getEmail());
                    editor.putString(Constants.NAME, resp.getUser().getName());
                    editor.putString(Constants.UNIQUE_ID, resp.getUser().getUnique_id());
                    editor.apply();

                    goToMain();
                }
                progress.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                progress.setVisibility(View.INVISIBLE);
                Log.d(Constants.TAG,"failed");
                Snackbar.make(getView(), t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();
            }
        });

    }

    private void goToRegister(){

        Fragment register = new RegisterFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame,register);
        ft.commit();
    }

    private void goToMain(){

        Intent in = new Intent(getActivity(),MainActivity.class);

        startActivity(in);
    }
}