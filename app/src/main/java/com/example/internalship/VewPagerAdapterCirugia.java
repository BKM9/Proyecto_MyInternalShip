package com.example.internalship;

import static com.example.internalship.utils.Constantes.ALETAR_OPERACION_CANCELADA;
import static com.example.internalship.utils.Constantes.RUTA_ALMACENAMIENTO_IMG_RAIZ;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.internalship.db.Funcionalidad_Cirugia;
import com.example.internalship.utils.Alertas;

import java.io.File;
import java.util.ArrayList;

public class VewPagerAdapterCirugia extends RecyclerView.Adapter<VewPagerAdapterCirugia.ViewHolder> {

    ArrayList<ViewPageItem> viewPageItems;

    private Context context;

    public VewPagerAdapterCirugia(ArrayList<ViewPageItem> viewPageItems, Context context) {
        this.viewPageItems = viewPageItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewpager_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewPageItem viewPageItem = viewPageItems.get(position);

        String rutaImagen = RUTA_ALMACENAMIENTO_IMG_RAIZ.concat(viewPageItem.imageID);
        File archivo = new File(rutaImagen);

        if(archivo.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(rutaImagen);
            holder.imageView.setImageBitmap(bitmap);
        } else {
            //Toast.makeText(this, "No existe el archivo", Toast.LENGTH_SHORT).show();
        }

        holder.tvHeading.setText(viewPageItem.heading);
        holder.tvDescription.setText(viewPageItem.description);
    }

    @Override
    public int getItemCount() {
        return viewPageItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tvHeading, tvDescription;
        ImageButton btnCirugia_Eliminar_IMG;

        Funcionalidad_Cirugia funcionalidad_cirugia = new Funcionalidad_Cirugia(context);

        @SuppressLint("NotifyDataSetChanged")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.ivimage);
            tvHeading = itemView.findViewById(R.id.tvHeading);
            tvDescription = itemView.findViewById(R.id.tvDesc);
            btnCirugia_Eliminar_IMG = itemView.findViewById(R.id.btnCirugia_Eliminar_IMG);

            itemView.setOnClickListener(v -> {

                String rutaImagen = RUTA_ALMACENAMIENTO_IMG_RAIZ.concat(viewPageItems.get(getAdapterPosition()).imageID);

                File archivo = new File(rutaImagen);
                Uri uriImagen = Uri.fromFile(archivo);

                Intent intent = new Intent(v.getContext(), FullScreenImage.class);
                intent.putExtra("IMAGE_ID", uriImagen.toString());
                v.getContext().startActivity(intent);
            });


            btnCirugia_Eliminar_IMG.setOnClickListener(v -> {


                Alertas.showConfirmationDialog(context, "Confirmación", "¿Está seguro que desea eliminar?", new Alertas.ConfirmationListener() {
                    @Override
                    public void onConfirmed() {

                        float code = funcionalidad_cirugia.eliminar_Foto(viewPageItems.get(getAdapterPosition()).idPac);

                        if(code >= 0) {

                            String rutaImagen = RUTA_ALMACENAMIENTO_IMG_RAIZ.concat(viewPageItems.get(getAdapterPosition()).imageID);
                            File archivo = new File(rutaImagen);
                            archivo.delete();
                            viewPageItems.remove(getAdapterPosition());
                            notifyDataSetChanged();

                            notifyItemRemoved(getAdapterPosition());
                            Toast.makeText(v.getContext(), "Foto eliminada", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(v.getContext(), "Error al eliminar foto", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCanceled() {
                        Toast.makeText(v.getContext(), ALETAR_OPERACION_CANCELADA, Toast.LENGTH_SHORT).show();
                    }
                });

            });

        }
    }

}
