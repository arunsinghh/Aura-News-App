package com.deva.minoor2;



import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class News_Full extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_full);

        // Find WebView in layout
        webView = findViewById(R.id.web_view);

        // Enable JavaScript (if required)
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Set WebViewClient to handle page navigation inside WebView
        webView.setWebViewClient(new WebViewClient());

        // Load your desired URL
        String url = getIntent().getStringExtra("url");
        webView.loadUrl(url);
    }

    // Handle back button press to navigate back in WebView history
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
