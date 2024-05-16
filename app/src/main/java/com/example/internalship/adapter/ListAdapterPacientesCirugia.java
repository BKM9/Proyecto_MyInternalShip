package com.example.internalship.adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.internalship.Cirugia_Imagenes;
import com.example.internalship.R;
import com.example.internalship.iu.opciones.cirugia.Cirugia_Detalle;
import com.example.internalship.vo.cirugiaVO.CPacienteVO;

import java.util.List;

public class ListAdapterPacientesCirugia extends RecyclerView.Adapter<ListAdapterPacientesCirugia.ViewHolder> {
    private List<CPacienteVO> mData;
    private LayoutInflater mInflater;
    private Context context;

    public ListAdapterPacientesCirugia(List<CPacienteVO> itemList, Context context){
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
        View view = mInflater.inflate(R.layout.list_element,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder (final ViewHolder holder, final int position){
        holder.bindData(mData.get(position));
    }

    public void setItem(List<CPacienteVO> items){ mData = items; }

    public String colorRamdom(){
        int color = (int) (Math.random() * 0xFFFFFF);
        String colorHex = String.format("#%06X", color);
        return colorHex;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImage;
        TextView nombrePaciente,camaPaciente,hcPaciente,fipaciente, edadpaciente;
        ImageButton btnInformation, btnImg;


        ViewHolder(View itemView){
            super(itemView);
            iconImage = itemView.findViewById(R.id.iconImageView);
            nombrePaciente = itemView.findViewById(R.id.nombrePacienteTextView);
            hcPaciente = itemView.findViewById(R.id.hcTextView);
            camaPaciente = itemView.findViewById(R.id.camaTextView);
            fipaciente = itemView.findViewById(R.id.fiTextView);
            edadpaciente = itemView.findViewById(R.id.edadTextView);
            btnInformation = itemView.findViewById(R.id.imageButtonInformation);
            btnImg = itemView.findViewById(R.id.imageButtonImagenes);
        }

        void bindData(final CPacienteVO item){
            iconImage.setColorFilter(Color.parseColor(colorRamdom()), PorterDuff.Mode.SRC_IN);
            nombrePaciente.setText(item.getNombre());
            hcPaciente.setText("HC: ".concat(item.getHc()));
            fipaciente.setText("FI: ".concat(item.getFi()));
            edadpaciente.setText("Edad: ".concat(item.getEdad()));
            camaPaciente.setText(item.getCama());

            btnInformation.setOnClickListener(v -> {
                Intent intent = new Intent(context, Cirugia_Detalle.class);
                Bundle bundle = new Bundle();

                bundle.putParcelable("ObjetoPaciente", (Parcelable) item);

                intent.putExtras(bundle);

                startActivity(context,intent,bundle);
            });

            btnImg.setOnClickListener(v -> {

                Intent intent = new Intent(context, Cirugia_Imagenes.class);

                Bundle bundle = new Bundle();

                bundle.putParcelable("ObjetoPaciente", (Parcelable) item);

                intent.putExtras(bundle);

                startActivity(context,intent,bundle);
            });
        }
    }


}
