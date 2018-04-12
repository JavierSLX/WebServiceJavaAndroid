package com.javiersl.ejerciciowebserviceescuela.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.javiersl.ejerciciowebserviceescuela.Clases.Alumno;
import com.javiersl.ejerciciowebserviceescuela.DAO.DAOAlumno;
import com.javiersl.ejerciciowebserviceescuela.R;

import java.util.List;

/**
 * Created by JavierSL on 09/04/2018.
 */

public class AlumnoAdapter extends RecyclerView.Adapter<AlumnoHolder>
{
    private List<Alumno> lista;
    private Context context;
    private String categoria;
    private int alumno_id, posicion;

    public AlumnoAdapter(List<Alumno> lista)
    {
        this.lista = lista;
    }

    @Override
    public AlumnoHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_alumno, parent, false);
        return new AlumnoHolder(view);
    }

    @Override
    public void onBindViewHolder(AlumnoHolder holder, final int position)
    {
        final Alumno alumno = lista.get(position);
        if(alumno.isEstado())
        {
            holder.getImgLabel().setColorFilter(ContextCompat.getColor(context, android.R.color.holo_blue_dark));
            holder.getTxtEstado().setTextColor(ContextCompat.getColor(context, android.R.color.holo_blue_dark));
            holder.getTxtEstado().setText("Activo");
            categoria = "Eliminar";
        }
        else
        {
            holder.getImgLabel().setColorFilter(ContextCompat.getColor(context, android.R.color.holo_red_dark));
            holder.getTxtEstado().setTextColor(ContextCompat.getColor(context, android.R.color.holo_red_dark));
            holder.getTxtEstado().setText("Inactivo");
            categoria = "Restaurar";
        }

        if (alumno.getId() != null)
            holder.getTxtID().setText(String.valueOf(alumno.getId().intValue()));
        else
            holder.getTxtID().setText("0");

        holder.getTxtNombre().setText(alumno.getNombre());
        holder.getTxtApellido().setText(alumno.getApellido());

        //Ejecuta el menú
        holder.getCardElemento().setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                alumno_id = alumno.getId();
                posicion = position;
                dialogoOpciones().show();
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return lista.size();
    }

    private AlertDialog dialogoOpciones()
    {
        String items[] =  {"Actualizar", categoria};
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Opciones");
        builder.setItems(items, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();

                //Elige una de las opciones
                if(which == 1)
                    dialogoConfirmacion(categoria).show();
                else
                    dialogoEdicion().show();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
            }
        });

        return builder.create();
    }

    private AlertDialog dialogoConfirmacion(final String proceso)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Confirmación");
        builder.setMessage("¿Esta seguro que desea " + proceso.toLowerCase() + "?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                if(proceso.equals("Eliminar"))
                {

                    //Elimina el alumno (lo cambia de estado)
                    DAOAlumno.getInstance().eliminar(context, alumno_id, new DAOAlumno.OnEstadoListener()
                    {
                        @Override
                        public void procesoCorrecto(Boolean estado)
                        {
                            Toast.makeText(context, "Elemento eliminado correctamente", Toast.LENGTH_SHORT).show();

                            //Notifica que el adaptador fue cambiado
                            lista.remove(posicion);
                            notifyDataSetChanged();
                        }

                        @Override
                        public void error(String mensaje)
                        {
                            Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else if(proceso.equals("Restaurar"))
                {
                    //Restaura el alumno (lo cambia de estado)
                    DAOAlumno.getInstance().restaurar(context, alumno_id, new DAOAlumno.OnEstadoListener()
                    {
                        @Override
                        public void procesoCorrecto(Boolean estado)
                        {
                            Toast.makeText(context, "Elemento restaurado correctamente", Toast.LENGTH_SHORT).show();

                            //Notifica que el adaptador fue cambiado
                            lista.remove(posicion);
                            notifyDataSetChanged();
                        }

                        @Override
                        public void error(String mensaje)
                        {
                            Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
            }
        });

        return builder.create();
    }

    private AlertDialog dialogoEdicion()
    {
        //Saca el nombre
        final Alumno alumno = lista.get(posicion);

        //Crea un dialogo con 2 EditText
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setPadding(16, 16, 16, 16);

        final EditText edtNombre = new EditText(context);
        edtNombre.setText(alumno.getNombre());
        linearLayout.addView(edtNombre);

        final EditText edtApellido = new EditText(context);
        edtApellido.setText(alumno.getApellido());
        linearLayout.addView(edtApellido);

        builder.setView(linearLayout);
        builder.setTitle("Edite el alumno");
        builder.setPositiveButton("Editar", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                if(!alumno.getNombre().equals(edtNombre.getText().toString()) || !alumno.getApellido().equals(edtApellido.getText().toString()))
                {
                    final Alumno nuevo = new Alumno(alumno_id, edtNombre.getText().toString(), edtApellido.getText().toString(), alumno.isEstado());

                    //Actualiza la BD y la lista
                    DAOAlumno.getInstance().actualizar(context, nuevo, new DAOAlumno.OnEstadoListener()
                    {
                        @Override
                        public void procesoCorrecto(Boolean estado)
                        {
                            Toast.makeText(context, "Elemento actualizado correctamente", Toast.LENGTH_SHORT).show();

                            lista.set(posicion, nuevo);
                            notifyDataSetChanged();
                        }

                        @Override
                        public void error(String mensaje)
                        {
                            Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
            }
        });

        return builder.create();
    }
}
