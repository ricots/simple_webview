package com.acer.webviewapp;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
    private WebView view;
    private ProgressDialog progressDialog;
    public final String url = "https://teknojurnal.com/cara-membuat-aplikasi-web-android/";
    EditText url_text;
    Button cari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //url_text = (EditText) findViewById(R.id.url);

        ;
        view = (WebView) findViewById(R.id.webView);
        view.setWebViewClient(new WebViewClient() {

            // This method will be triggered when the Page Started Loading

            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progressDialog = ProgressDialog.show(MainActivity.this, null,
                        "Please Wait...Page is Loading...");
                progressDialog.setCancelable(true);
                super.onPageStarted(view, url, favicon);
            }

            // This method will be triggered when the Page loading is completed

            public void onPageFinished(WebView view, String url) {
                progressDialog.dismiss();
                super.onPageFinished(view, url);
            }

            // This method will be triggered when error page appear

            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                progressDialog.dismiss();
                // You can redirect to your own page instead getting the default
                // error page
                Toast.makeText(MainActivity.this,
                        "The Requested Page Does Not Exist", Toast.LENGTH_LONG).show();
                view.loadUrl(url);
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });
        view.loadUrl(url);
        view.getSettings().setLoadWithOverviewMode(true);
        view.getSettings().setUseWideViewPort(true);
    }



    /*private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            progressDialog.dismiss();
            view.loadUrl(url);
            return true;
        }
    }*/

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //ketika disentuh tombol back
        if ((keyCode == KeyEvent.KEYCODE_BACK) && view.canGoBack()) {
            view.goBack(); //method goback() dieksekusi untuk kembali pada halaman sebelumnya
            return true;
        }
        // Jika tidak ada history (Halaman yang sebelumnya dibuka)
        // maka akan keluar dari activity
        return super.onKeyDown(keyCode, event);
    }

}