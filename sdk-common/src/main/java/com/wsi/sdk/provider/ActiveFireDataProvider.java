package com.wsi.sdk.provider;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.wsi.sdk.model.ActiveFire;

import org.json.JSONObject;

import java.util.Collections;
import java.util.List;

/**
 * Created by Ievgen Usenko
 * Date: 6/26/14.
 */
public class ActiveFireDataProvider {
    private final RequestQueue mRequestQueue;
    private List<ActiveFire> mData = Collections.EMPTY_LIST;
    private JsonObjectRequest mCurrentRequest;

    public ActiveFireDataProvider(final Context ctx) {
        mRequestQueue = Volley.newRequestQueue(ctx);
    }

    public List<ActiveFire> get() {
        return mData;
    }

    public void set(final List<ActiveFire> data) {
        if (data == null) {
            throw new IllegalArgumentException("data must not be null");
        }
        mData = data;
    }

    public void startPooling() {
        throw new UnsupportedOperationException("implement me");
    }

    public void stopPooling() {
        throw new UnsupportedOperationException("implement me");
    }

    public synchronized void sync(/* may take parameter */) {
        if (mCurrentRequest != null && !mCurrentRequest.isCanceled()) mCurrentRequest.cancel();

        String url = "http://";
        mCurrentRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }
        );
    }
}
