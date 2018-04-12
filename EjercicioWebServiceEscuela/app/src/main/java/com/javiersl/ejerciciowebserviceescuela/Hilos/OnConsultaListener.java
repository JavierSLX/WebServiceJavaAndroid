package com.javiersl.ejerciciowebserviceescuela.Hilos;

import org.json.JSONObject;

/**
 * Created by JavierSL on 11/04/2018.
 */

public interface OnConsultaListener<T>
{
    void onSuccess(T elemento);
    void onFailed(String mensaje);
}
