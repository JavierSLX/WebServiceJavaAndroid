package com.javiersl.ejerciciowebserviceescuela.Hilos;

import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by JavierSL on 10/04/2018.
 */

public class HiloVolleyJsonGET
{
    private static HiloVolleyJsonGET hiloVolleyJsonGET;
    private ProgressDialog progressDialog;

    private HiloVolleyJsonGET()
    {
    }

    public static HiloVolleyJsonGET getInstance()
    {
        if(hiloVolleyJsonGET == null)
            hiloVolleyJsonGET = new HiloVolleyJsonGET();

        return hiloVolleyJsonGET;
    }

    public void getResponse(Context context, String url, final OnConsultaListener<JSONObject> listener)
    {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Cargando");
        progressDialog.setMessage("Un momento...");
        progressDialog.show();

        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                progressDialog.dismiss();
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                progressDialog.dismiss();
                listener.onFailed("Error en el proceso " + error.toString());
            }
        });

        queue.add(request);
    }
}
