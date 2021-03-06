package com.example.counselor;

import android.app.Activity;
import android.content.ContentValues;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class PHPRequest{

//    how to use class:

//        PHPRequest p = new PHPRequest();
//        p.Request(MainActivity.this, "cars.php");
//
//        ContentValues values = new ContentValues();
//        values.put("brand","bmw");
//        values.put("brand","toyota")
//        p.RequestWithParameters(MainActivity.this, "cars2.php",values);

//        The functions set a json array data to the result so you loop over and do whatever
    //    for example p.data after p.RequestWithParameters(MainActivity.this, "cars2.php",values);
    //    will have the result

    String prefix = "https://lamp.ms.wits.ac.za/home/s2094007/";

    public void PHPRequest(){

    }

    public void RequestWithParameters(final Activity a, String file, ContentValues params) {

        OkHttpClient client = new OkHttpClient();

        FormBody.Builder builder = new FormBody.Builder();

        for(String key:params.keySet()){
            builder.add(key, params.getAsString(key));
        }


        Request request = new Request.Builder().url(prefix + file).post(builder.build()).build();



        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull okhttp3.Call call, Response response) throws IOException {
                final String responseData = response.body().string();

                a.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        System.out.println("success request");

                    }
                });

            }

            @Override
            public void onFailure(@NotNull okhttp3.Call call, @NotNull IOException e) {
                e.printStackTrace();
            }


        });


    }

}
