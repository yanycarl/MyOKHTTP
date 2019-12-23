package com.example.http;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Url url = new Url.Builder().setScheme("http")
//                .setHost("t.weather.sojson.com")
//                .setPath("/api/weather/city/101030100")
//                .addQueryParam("cool", "jira")
//                .addQueryParam("hello", "yes")
//                .build();
        Url url = new Url.Builder()
                .setScheme("https")
                .setHost("www.baidu.com")
                .setPath("/")
                .build();

        Log.d("Url", url.getUrl());

        final Request request = new Request.Builder()
                .setMethod("GET")
                .setUrl(url)
                .build();

        HTTPClient client = new HTTPClient.Builder()
                .build();

        final Call call = client.newCall(request);

        final ExecutorService mCacheExcutor = Executors.newCachedThreadPool();

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCacheExcutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0; i<10; i++){
                            call.enqueue(new Callback() {
                                @Override
                                public void onFailed(Exception e) {
                                    if(e != null){
                                        Log.d("ERROR", e.getMessage());
                                    }
                                }

                                @Override
                                public void onResult(Responce responce) {
                                    Log.d("MainActivity", responce.toString());
                                }
                            });
                        }
                    }
                });

                mCacheExcutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0; i<10; i++){
                            call.enqueue(new Callback() {
                                @Override
                                public void onFailed(Exception e) {
                                    if(e != null){
                                        Log.d("ERROR", e.getMessage());
                                    }
                                }

                                @Override
                                public void onResult(Responce responce) {
                                    Log.d("MainActivity", responce.toString());
                                }
                            });
                        }
                    }
                });

                mCacheExcutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0; i<10; i++){
                            call.enqueue(new Callback() {
                                @Override
                                public void onFailed(Exception e) {
                                    if(e != null){
                                        Log.d("ERROR", e.getMessage());
                                    }
                                }

                                @Override
                                public void onResult(Responce responce) {
                                    Log.d("MainActivity", responce.toString());
                                }
                            });
                        }
                    }
                });

                mCacheExcutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0; i<10; i++){
                            call.enqueue(new Callback() {
                                @Override
                                public void onFailed(Exception e) {
                                    if(e != null){
                                        Log.d("ERROR", e.getMessage());
                                    }
                                }

                                @Override
                                public void onResult(Responce responce) {
                                    Log.d("MainActivity", responce.toString());
                                }
                            });
                        }
                    }
                });

            }
        });


//        ExecutorService mCacheExcutor = Executors.newCachedThreadPool();
//
//        mCacheExcutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                RealInterceptorChain chain = new RealInterceptorChain(request);
//                final Responce responce = chain.proceed(request , null);
//                Log.d("yanyao2", responce.toString());
//            }
//        });
    }
}
