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
import com.example.internalship.db.Funcionalidad_Notas;
import com.example.internalship.iu.menu.notas.Menu_Notas_Detalle;
import com.example.internalship.utils.Alertas;
import com.example.internalship.vo.notevo.NoteVO;

import java.util.List;

public class ListAdapterNotes extends RecyclerView.Adapter<ListAdapterNotes.ViewHolder> {
    private List<NoteVO> mData;
    private LayoutInflater mInflater;
    private Context context;

    public ListAdapterNotes(List<NoteVO> itemList, Context context){
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
        View view = mInflater.inflate(R.layout.carga_grilla_notes,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder (final ViewHolder holder, final int position){
        holder.bindData(mData.get(position));
    }

    public void setItem(List<NoteVO> items){ mData = items; }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView asunto;

        ImageButton verNota,eliminarNota;

        ViewHolder(View itemView){
            super(itemView);
            asunto = itemView.findViewById(R.id.title_nota);
            verNota = itemView.findViewById(R.id.imgbtnVerNota);
            eliminarNota = itemView.findViewById(R.id.imgbtneliminar_nota);
        }

        void bindData(final NoteVO item){
            asunto.setText(item.getAsunto());
            int id = item.getId();
            String asunto = item.getAsunto();

            verNota.setOnClickListener(v -> {
                Bundle bundle = new Bundle();

                Intent intent = new Intent(context, Menu_Notas_Detalle.class);
                bundle.putParcelable("ObjetoNotas", (Parcelable) item);
                intent.putExtras(bundle);

                startActivity(context,intent,null);
            });

            eliminarNota.setOnClickListener(v -> Alertas.showConfirmationDialog(context, "Confirmación", "¿Está seguro que desea eliminar nota?", new Alertas.ConfirmationListener() {
                @Override
                public void onConfirmed() {
                    Funcionalidad_Notas funcionalidad_notas = new Funcionalidad_Notas(context);
                    long response = funcionalidad_notas.deleteNotas(id);
                    if (response > 0){
                        mData.remove(item);
                        Toast.makeText(context, "Nota eliminada ".concat(String.valueOf(asunto)), Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                    }else{
                        Toast.makeText(context, "Ocurrio un error durante la eliminación", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCanceled() {
                    Toast.makeText(context, "Nota no eliminada".concat(String.valueOf(id)), Toast.LENGTH_SHORT).show();
                }
            }));
        }
    }


}
