package com.example.sathapornsunthornpan.onlinebikercommunity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.sathapornsunthornpan.onlinebikercommunity.blog.AddBlogActivity;
import com.example.sathapornsunthornpan.onlinebikercommunity.blog.BlogFragment;
import com.example.sathapornsunthornpan.onlinebikercommunity.config.Constants;
import com.example.sathapornsunthornpan.onlinebikercommunity.forum.MainForumFragment;
import com.example.sathapornsunthornpan.onlinebikercommunity.news.NewsCardFragment;
import com.example.sathapornsunthornpan.onlinebikercommunity.user.LoginFragment;
import com.example.sathapornsunthornpan.onlinebikercommunity.user.ProfileFragment;
import com.example.sathapornsunthornpan.onlinebikercommunity.user.RegisterFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private FloatingActionButton fab;
    private SharedPreferences pref;
    private int pageFab;
    private boolean fabOnOff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);

        // --------- Toolbar Layout -----------
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // -------- End Drawer Layout ---------

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fabOnClick(view);
            }
        });
        fabHide();
    }


    private void fabOnClick(View view) {
        switch (pageFab) {
            case 1 :
                // page news
                break;
            case 2 :
                // page blog
                startActivity(new Intent(getApplicationContext(), AddBlogActivity.class));
                break;
            case 3 :
                // page forum

                break;

            default:
                // page main
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
        }

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        //creating fragment object
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.content_frame);


        if (id == R.id.nav_news) {
            pageFab = 1; // page news
            fabHide();

            fragment = new NewsCardFragment();
        } else if (id == R.id.nav_blog) {
            pageFab = 2; // page blog
            if (checkLogin() && !fabOnOff) {
                fab.show();
            }
            fragment = new BlogFragment();
        } else if (id == R.id.nav_forum) {
            pageFab = 3; // page forum
            fragment = new MainForumFragment();

        } else if (id == R.id.nav_manage) {
            pageFab = 4; // page manage setting
            fabHide();
            fragment = new ProfileFragment();

        } else if (id == R.id.nav_register) {
            pageFab = 5; // page register
            fabHide();
            fragment = new RegisterFragment();

        } else if (id == R.id.nav_login) {
            pageFab = 6; // page login
            fabHide();
            fragment = new LoginFragment();

        } else {
            fabHide();
        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private boolean checkLogin() {
        return pref.getBoolean(Constants.IS_LOGGED_IN, false);
    }

    private void fabHide() {
        if (!fabOnOff){
            fab.hide();
            fabOnOff = false;
        }
    }
}
