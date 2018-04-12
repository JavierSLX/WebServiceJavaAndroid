package com.javiersl.ejerciciowebserviceescuela.Hilos;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by JavierSL on 11/04/2018.
 */

public class HiloVolleyJsonPUT
{
    private static HiloVolleyJsonPUT hiloVolleyJsonPUT;
    private ProgressDialog progressDialog;

    private HiloVolleyJsonPUT()
    {
    }

    public static HiloVolleyJsonPUT getInstance()
    {
        if(hiloVolleyJsonPUT == null)
            hiloVolleyJsonPUT = new HiloVolleyJsonPUT();

        return hiloVolleyJsonPUT;
    }

    public void getResponse(final Context context, String url, JSONObject elementos, final OnConsultaListener<Boolean> listener)
    {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Cargando");
        progressDialog.setMessage("Un momento...");
        progressDialog.show();
        final String parametros = elementos.toString();

        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>()
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
                listener.onFailed("Error en el servicio " + error.toString());
            }
        })
        {
            @Override
            public byte[] getBody() throws AuthFailureError
            {
                try
                {
                    return parametros.getBytes("utf-8");
                } catch (UnsupportedEncodingException e)
                {
                    e.printStackTrace();
                    Toast.makeText(context, "Error al convertir en UTF-8", Toast.LENGTH_SHORT).show();
                }

                return null;
            }

            @Override
            public String getBodyContentType()
            {
                return "application/json; charset=utf-8";
            }
        };

        queue.add(request);
    }
}
