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
 * Created by JavierSL on 10/04/2018.
 */

//Clase Singleton de un hilo volley de tipo POST
public class HiloVolleyStringPOST
{
    private static HiloVolleyStringPOST hiloVolleyStringPOST;
    private ProgressDialog progressDialog;

    private HiloVolleyStringPOST()
    {
    }

    public static HiloVolleyStringPOST getInstance()
    {
        if (hiloVolleyStringPOST == null)
            hiloVolleyStringPOST = new HiloVolleyStringPOST();

        return hiloVolleyStringPOST;
    }

    //Método que recibe sus elementos y una interface
    public void getResponse(final Context context, String url, JSONObject elementos, final OnConsultaListener<Boolean> listener)
    {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Cargando");
        progressDialog.setMessage("Un momento...");
        progressDialog.show();
        final String parametros = elementos.toString();

        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
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
                listener.onFailed("Error en la consulta " + error.toString());
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
                    Toast.makeText(context, "Imposible codificar a UTF-8", Toast.LENGTH_SHORT).show();
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
