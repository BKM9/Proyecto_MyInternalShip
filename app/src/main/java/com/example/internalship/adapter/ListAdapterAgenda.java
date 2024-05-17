package com.example.internalship.adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.internalship.R;
import com.example.internalship.db.Funcionalidad_Actividades;
import com.example.internalship.iu.menu.calendario.Menu_Agenda_Detalle;
import com.example.internalship.utils.Alertas;
import com.example.internalship.vo.activityVO.ActividadVO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ListAdapterAgenda extends RecyclerView.Adapter<ListAdapterAgenda.ViewHolder> {
    private List<ActividadVO> mData;
    private LayoutInflater mInflater;
    private Context context;

    public ListAdapterAgenda(List<ActividadVO> itemList, Context context){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount(){
        return mData.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.carga_grilla_agenda,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder (final ViewHolder holder, final int position){
        holder.bindData(mData.get(position));
    }

    public void setItem(List<ActividadVO> items){ mData = items; }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView asunto,fechaAsignada,txtDescripcionAsignadaAgenda;

        ImageButton verAgenda,eliminarAgenda;

        ViewHolder(View itemView){
            super(itemView);
            asunto = itemView.findViewById(R.id.txtAsunto_Agenda);
            fechaAsignada = itemView.findViewById(R.id.txtFechaAsignadaAgenda);
            eliminarAgenda = itemView.findViewById(R.id.imgbtneliminar_Agenda);
            verAgenda = itemView.findViewById(R.id.imgbtnVer_Agenda);
            txtDescripcionAsignadaAgenda = itemView.findViewById(R.id.txtDescripcionAsignadaAgenda);
        }

        void bindData(final ActividadVO item){

            asunto.setText(item.getAsunto());

            String fechaParse = "";

            try {

                SimpleDateFormat formatoOriginal = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat formatoNuevo = new SimpleDateFormat("dd-MM-yyyy");

                Date fecha = formatoOriginal.parse(item.getFecha());
                fechaParse = formatoNuevo.format(fecha);

            }catch (Exception e){
                e.printStackTrace();
            }

            fechaAsignada.setText("Fecha: ".concat(fechaParse));
            txtDescripcionAsignadaAgenda.setText(item.getDescripcion());

            int id = item.getId();
            String asunto = item.getAsunto();

            verAgenda.setOnClickListener(v -> {
                Bundle bundle = new Bundle();

                Intent intent = new Intent(context, Menu_Agenda_Detalle.class);
                bundle.putParcelable("ObjetoCalendario", (Parcelable) item);
                intent.putExtras(bundle);

                startActivity(context,intent,null);
            });

            eliminarAgenda.setOnClickListener(v -> Alertas.showConfirmationDialog(context, "Confirmación", "¿Está seguro que desea eliminar actividad?", new Alertas.ConfirmationListener() {
                @Override
                public void onConfirmed() {
                    Funcionalidad_Actividades funcionalidadActividades = new Funcionalidad_Actividades(context);
                    long response = funcionalidadActividades.eliminarActividad(id);
                    if (response > 0){
                        mData.remove(item);
                        Toast.makeText(context, "Actividad eliminada ".concat(String.valueOf(asunto)), Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                    }else{
                        Toast.makeText(context, "Ocurrio un error durante la eliminación", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCanceled() {
                    Toast.makeText(context, "Actividad no eliminada".concat(String.valueOf(id)), Toast.LENGTH_SHORT).show();
                }
            }));
        }
    }


}
