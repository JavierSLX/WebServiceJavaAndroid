package com.javiersl.ejerciciowebserviceescuela.Hilos;

import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by JavierSL on 11/04/2018.
 */

public class HiloVolleyStringDELETE
{
    private static HiloVolleyStringDELETE hiloVolleyStringDELETE;
    private ProgressDialog progressDialog;

    private HiloVolleyStringDELETE()
    {
    }

    public static HiloVolleyStringDELETE getInstance()
    {
        if(hiloVolleyStringDELETE == null)
            hiloVolleyStringDELETE = new HiloVolleyStringDELETE();

        return hiloVolleyStringDELETE;
    }

    public void getResponse(Context context, String url, final OnConsultaListener<Boolean> listener)
    {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Cargando");
        progressDialog.setMessage("Un momento...");
        progressDialog.show();

        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                progressDialog.dismiss();
                listener.onSuccess(true);
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