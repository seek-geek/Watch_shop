package com.benkids.watch_shop.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.benkids.watch_shop.R;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2015/11/28.
 */
public class DetailsActivity extends Activity {
    private WebView webView;
    private SwipeRefreshLayout refreshLayout;
    private String url = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        webView = (WebView) findViewById(R.id.webview_activity_details);
        initView();
        getUrl();
    }
    public void initView(){
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl_details_title);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refreshLayout);
        TitleRelativeLayoutListener(rl);
        swipeRefreshLayoutListener();
    }
    public void getUrl(){
        Intent intent = getIntent();
        url = intent.getStringExtra("URL");
        if(url != null && !url.equals("")) {
            displayWebView(url);
        }
    }
    public void swipeRefreshLayoutListener(){
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                displayWebView(url);
            }
        });
        ViewTreeObserver vto = refreshLayout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {

                final DisplayMetrics metrics = getResources().getDisplayMetrics();
                Float mDistanceToTriggerSync = Math.min(((View) refreshLayout.getParent()).getHeight() * 0.6f, 4000 * metrics.density);

                try {
                    Field field = SwipeRefreshLayout.class.getDeclaredField("mDistanceToTriggerSync");
                    field.setAccessible(true);
                    field.setFloat(refreshLayout, mDistanceToTriggerSync);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                ViewTreeObserver obs = refreshLayout.getViewTreeObserver();
                obs.removeOnGlobalLayoutListener(this);
            }
        });
    }
    public void TitleRelativeLayoutListener(RelativeLayout rl){
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailsActivity.this.finish();
            }
        });
    }
    public void displayWebView(String url) {
        //webView.loadDataWithBaseURL(null, url, "text/html", "utf-8", null);
        webView.loadUrl(url);
        webView.setWebViewClient(new HelloWebViewClient());
        refreshLayout.setRefreshing(false);
    }
    public class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
