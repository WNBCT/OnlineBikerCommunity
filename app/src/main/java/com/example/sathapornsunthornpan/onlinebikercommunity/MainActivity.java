package com.example.sathapornsunthornpan.onlinebikercommunity;

import android.content.Intent;
import android.os.Bundle;
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

import com.example.sathapornsunthornpan.onlinebikercommunity.blog.BlogFragment;
import com.example.sathapornsunthornpan.onlinebikercommunity.forum.ForumFragment;
import com.example.sathapornsunthornpan.onlinebikercommunity.news.CardFragmentNews;
import com.example.sathapornsunthornpan.onlinebikercommunity.news.NewsFragment;
import com.example.sathapornsunthornpan.onlinebikercommunity.setting.SettingFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.content_frame, new NewsFragment());
//        ft.commit();

        // FloatingAction
        /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        // End FloatingAction
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
//            fragment = new NewsFragment();
            fragment = new CardFragmentNews();
        } else if (id == R.id.nav_blog) {
            fragment = new BlogFragment();
        } else if (id == R.id.nav_forum) {
            fragment = new ForumFragment();
        } else if (id == R.id.nav_manage) {
            fragment = new SettingFragment();
        } else if (id == R.id.nav_register) {

        } else if (id == R.id.nav_login) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_logout) {

        } else {
            fragment = new NewsFragment();
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
}
