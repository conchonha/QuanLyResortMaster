package com.example.quan_ly_resort.network;


import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.quan_ly_resort.interfaces.VolleyCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ServerSenderHerokuIP {

    public ServerSenderHerokuIP(String url_string, final Map<String, String> jsonObject, Context context, final VolleyCallback volleyCallback) {
        RequestQueue requstQueue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonobj = new JsonObjectRequest(Request.Method.POST, url_string, new JSONObject(jsonObject),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        volleyCallback.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        volleyCallback.onFail(error.getMessage());
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };

        requstQueue.add(jsonobj);
    }
}
