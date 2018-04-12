package com.javiersl.ejerciciowebserviceescuela.Adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.javiersl.ejerciciowebserviceescuela.R;

/**
 * Created by JavierSL on 09/04/2018.
 */

public class AlumnoHolder extends RecyclerView.ViewHolder
{
    private ImageView imgLabel;
    private TextView txtEstado;
    private TextView txtID;
    private TextView txtNombre;
    private TextView txtApellido;
    private CardView cardElemento;

    public AlumnoHolder(View itemView)
    {
        super(itemView);
        imgLabel = (ImageView)itemView.findViewById(R.id.imgLabel);
        txtEstado = (TextView)itemView.findViewById(R.id.txtEstado);
        txtID = (TextView)itemView.findViewById(R.id.txtID);
        txtNombre = (TextView)itemView.findViewById(R.id.txtNombre);
        txtApellido = (TextView)itemView.findViewById(R.id.txtApellido);
        cardElemento = (CardView)itemView.findViewById(R.id.cardElemento);
    }

    public ImageView getImgLabel()
    {
        return imgLabel;
    }

    public TextView getTxtEstado()
    {
        return txtEstado;
    }

    public TextView getTxtID()
    {
        return txtID;
    }

    public TextView getTxtNombre()
    {
        return txtNombre;
    }

    public TextView getTxtApellido()
    {
        return txtApellido;
    }

    public CardView getCardElemento()
    {
        return cardElemento;
    }
}
