package com.javiersl.ejerciciowebserviceescuela.Hilos;

import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

/**
 * Created by JavierSL on 09/04/2018.
 */

//Clase Singleton de un hilo volley de tipo GET
public class HiloVolleyArrayGET
{
    private static HiloVolleyArrayGET hiloVolleyArrayGET;
    private ProgressDialog dialog;

    private HiloVolleyArrayGET()
    {
    }

    public static HiloVolleyArrayGET getInstance()
   {
       if (hiloVolleyArrayGET == null)
           hiloVolleyArrayGET = new HiloVolleyArrayGET();

       return hiloVolleyArrayGET;
   }

    //Metodo que recibe los elementos y una interface (Callback)
    public void getResponse(Context context, String url, final OnConsultaListener<JSONArray> listener)
    {
        dialog = new ProgressDialog(context);
        dialog.setTitle("Cargando");
        dialog.setMessage("Un momento...");
        dialog.show();

        RequestQueue queue = Volley.newRequestQueue(context);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>()
        {
            @Override
            public void onResponse(JSONArray response)
            {
                dialog.dismiss();
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                dialog.dismiss();
                listener.onFailed("Error la realizar el proceso " + error.toString());
            }
        });

        queue.add(request);
    }
}
