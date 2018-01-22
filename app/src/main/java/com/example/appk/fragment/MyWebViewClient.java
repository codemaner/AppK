package com.example.appk.fragment;

import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by 那个谁 on 2018/1/21.
 * 奥特曼打小怪兽
 * 作用：
 */

class MyWebViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        Log.d("WebView","onPageFinished ");
        view.loadUrl("javascript:window.local_obj.showSource('<head>'+"+
                "document.getElementsByTagName('html')[0].innerHTML+'</head>');");
        super.onPageFinished(view, url);
    }

}
