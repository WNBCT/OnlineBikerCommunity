package com.example.sathapornsunthornpan.onlinebikercommunity.news;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.sathapornsunthornpan.onlinebikercommunity.R;

public class NewsWebViewActivity extends AppCompatActivity {

    private WebView webView;
    private String url, title;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_webview);


        webView = (WebView) findViewById(R.id.webview_news);

        Bundle bundle = getIntent().getExtras();

        url = bundle.getString("url", "https://www.google.co.th");
        title = bundle.getString("title", "Title");

        setTitle(title);

        // Apply defaults including useWideViewport which us required
        // to make the text auto size to work
        setUpWebViewDefaults(webView);


        // Make the WebView handle all loaded URLs
        webView.setWebViewClient(new WebViewClient());

        webView.loadUrl(url);
    }



    /**
     * Convenience method to set some generic defaults for a
     * given WebView
     *
     * @param webView
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setUpWebViewDefaults(WebView webView) {
        WebSettings settings = webView.getSettings();

        // Enable Javascript
        settings.setJavaScriptEnabled(true);

        // Use WideViewport and Zoom out if there is no viewport defined
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        // Enable pinch to zoom without the zoom buttons
        settings.setBuiltInZoomControls(true);

        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
            // Hide the zoom controls for HONEYCOMB+
            settings.setDisplayZoomControls(false);
        }

        // Enable remote debugging via chrome://inspect
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
    }


}
