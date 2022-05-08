package com.example.quan_ly_resort.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.quan_ly_resort.interfaces.VolleyCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ServerReceiverHerokuIP {
    public ServerReceiverHerokuIP(final String url_string,int method, Context context, final VolleyCallback volleyCallback) {
        RequestQueue requstQueue = Volley.newRequestQueue(context);

        StringRequest jsonobj = new StringRequest(method,url_string,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        volleyCallback.onSuccess(jsonObject);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        volleyCallback.onFail(error.getMessage());
                    }
                }
        ){
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                if(jsonObject != null){
////                    Map<String, String> myParams = new HashMap<>();
////                    myParams.put("state",jsonObject.toString());
//                    Log.d("SangTB", "getParams: "+jsonObject+" ----- url: "+url_string);
//                    return jsonObject;
//                }
//                return super.getParams();
//            }
        };

        requstQueue.add(jsonobj);
    }
}
