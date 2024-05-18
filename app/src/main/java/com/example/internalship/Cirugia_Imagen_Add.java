package com.example.internalship;

import static com.example.internalship.utils.Constantes.ALETAR_OPERACION_CANCELADA;
import static com.example.internalship.utils.Constantes.TIPO_ECOGRAFIA;
import static com.example.internalship.utils.Constantes.TIPO_EXTRA;
import static com.example.internalship.utils.Constantes.TIPO_RAYOS_X;
import static com.example.internalship.utils.Constantes.TIPO_SELECCIONA;
import static com.example.internalship.utils.Constantes.TIPO_TOMOGRAFIA;
import static com.example.internalship.utils.Util.fechaVal;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.internalship.db.Funcionalidad_Cirugia;
import com.example.internalship.utils.Alertas;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Cirugia_Imagen_Add extends AppCompatActivity {

    Spinner opcTipoImagen_Cirugia;
    EditText TituloFoto_Add_Cirugia, DescripcionFoto_Add_Cirugia;
    Button btnSeleccion_Imagen_Cirugia, btnGuardar_Foto_Seleccionada_Cirugia;
    ImageView imgFotoTomadaoEncontrada_Cirugia;

    private int finalIdPac;
    private Uri uriImagen;
    private Uri imagenSeleccionada;
    private String nomImagen;
    private static final int CODIGO_GALERIA = 1;
    private static final int CODIGO_CAMARA = 2;
    private ProgressBar BarprogressBar;

    Funcionalidad_Cirugia funcionalidad_cirugia = new Funcionalidad_Cirugia(Cirugia_Imagen_Add.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.cirugia_imagen_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int idPac = 100000;
        if (bundle != null) {
            idPac = bundle.getInt("idPaciente");
        }
        finalIdPac = idPac;

    }

    public void init() {
        opcTipoImagen_Cirugia = findViewById(R.id.opcTipoImagen_Cirugia);
        btnSeleccion_Imagen_Cirugia = findViewById(R.id.btnSeleccion_Imagen_Cirugia);
        imgFotoTomadaoEncontrada_Cirugia = findViewById(R.id.imgFotoTomadaoEncontrada_Cirugia);
        btnGuardar_Foto_Seleccionada_Cirugia = findViewById(R.id.btnGuardar_Foto_Seleccionada_Cirugia);
        TituloFoto_Add_Cirugia = findViewById(R.id.TituloFoto_Add_Cirugia);
        DescripcionFoto_Add_Cirugia = findViewById(R.id.DescripcionFoto_Add_Cirugia);
        BarprogressBar = findViewById(R.id.progressBar_img_cirugia_add);


        TituloFoto_Add_Cirugia.setOnClickListener(v -> {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

            Calendar calendar = Calendar.getInstance();
            int dia = calendar.get(Calendar.DAY_OF_MONTH);
            int mes = calendar.get(Calendar.MONTH);
            int anio = calendar.get(Calendar.YEAR);

            // crea una nueva configuración de localización en español
            Locale locale = new Locale("es", "ES");
            Locale.setDefault(locale);
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = locale;
            res.updateConfiguration(conf, dm);

            // crea los diálogos con la nueva configuración
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                @SuppressLint("SetTextI18n") TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view1, hourOfDay, minute) -> {
                    String formatoHora = hourOfDay < 12 ? "AM" : "PM";
                    hourOfDay = hourOfDay % 12;
                    if (hourOfDay == 0) hourOfDay = 12;
                    TituloFoto_Add_Cirugia.setText(fechaVal(dayOfMonth) + "-" + fechaVal((month + 1)) + "-" + year + " " + fechaVal(hourOfDay) + ":" + fechaVal(minute) + " " + formatoHora);
                }, 0, 0, false); // usa el formato de 12 horas

                timePickerDialog.show();
            }, anio, mes, dia);

            datePickerDialog.show();
        });

        String[] datos = new String[]{TIPO_SELECCIONA, TIPO_RAYOS_X, TIPO_ECOGRAFIA, TIPO_TOMOGRAFIA, TIPO_EXTRA};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);

        opcTipoImagen_Cirugia.setAdapter(adapter);

        btnSeleccion_Imagen_Cirugia.setOnClickListener(v -> {
            mostrarOpciones();
        });

        btnGuardar_Foto_Seleccionada_Cirugia.setOnClickListener(v -> {
            if (opcTipoImagen_Cirugia.getSelectedItem().toString().equals("--- Selecciona ---")) {
                Toast.makeText(this, "Selecciona un tipo de imagen", Toast.LENGTH_SHORT).show();
            }
            else {

                Alertas.showConfirmationDialog(Cirugia_Imagen_Add.this, "Confirmación", "¿Está seguro que desea agregar imagen?", new Alertas.ConfirmationListener() {
                    @Override
                    public void onConfirmed() {

                        BarprogressBar.setVisibility(View.VISIBLE);

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(3000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                // Hide the ProgressBar
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        BarprogressBar.setVisibility(View.GONE);
                                    }
                                });
                            }
                        }).start();

                        String opc = opcTipoImagen_Cirugia.getSelectedItem().toString();
                        guardarImagen(String.valueOf(finalIdPac), opc);

                        Intent intent = new Intent(Cirugia_Imagen_Add.this, Cirugia_Imagen_Add.class);
                        intent.putExtra("idPaciente", finalIdPac);
                        startActivity(intent);

                    }

                    @Override
                    public void onCanceled() {

                        Toast.makeText(Cirugia_Imagen_Add.this, ALETAR_OPERACION_CANCELADA, Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        imgFotoTomadaoEncontrada_Cirugia.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), FullScreenImage.class);
            intent.putExtra("IMAGE_ID", imagenSeleccionada.toString());
            v.getContext().startActivity(intent);
        });
    }

    private void mostrarOpciones() {
        final CharSequence[] opciones = {"Tomar foto", "Elegir de galería", "Cancelar"};
        AlertDialog.Builder builder = new AlertDialog.Builder(Cirugia_Imagen_Add.this);
        builder.setTitle("Elige una opción");
        builder.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int seleccion) {
                if (opciones[seleccion] == "Tomar foto") {
                    abrirCamara();
                } else if (opciones[seleccion] == "Elegir de galería") {
                    abrirGaleria();
                } else if (opciones[seleccion] == "Cancelar") {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void abrirCamara() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "Nueva Imagen");
        values.put(MediaStore.Images.Media.DESCRIPTION, "Desde la Cámara");
        uriImagen = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriImagen);
        startActivityForResult(cameraIntent, CODIGO_CAMARA);
    }

    private void abrirGaleria() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Seleccione una imagen"), CODIGO_GALERIA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == CODIGO_GALERIA) {
            imagenSeleccionada = data.getData();

            try {
                final int MAX_SIZE = 1024;
                InputStream input = getContentResolver().openInputStream(imagenSeleccionada);
                BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
                onlyBoundsOptions.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
                input.close();

                int originalWidth = onlyBoundsOptions.outWidth;
                int originalHeight = onlyBoundsOptions.outHeight;

                int imageScale = Math.min(originalWidth/MAX_SIZE, originalHeight/MAX_SIZE);

                BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                bitmapOptions.inSampleSize = imageScale;
                input = getContentResolver().openInputStream(imagenSeleccionada);
                Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
                input.close();

                // Rotar la imagen si es necesario
                bitmap = rotateImageIfRequired(bitmap, imagenSeleccionada);

                imgFotoTomadaoEncontrada_Cirugia.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (resultCode == RESULT_OK && requestCode == CODIGO_CAMARA) {
            ImageView imageView = findViewById(R.id.imgFotoTomadaoEncontrada_Cirugia);
            imagenSeleccionada = uriImagen;
            imageView.setImageURI(uriImagen);
        }
    }

    private Bitmap rotateImageIfRequired(Bitmap img, Uri selectedImage) throws IOException {

        InputStream input = this.getContentResolver().openInputStream(selectedImage);
        ExifInterface ei;
        if (Build.VERSION.SDK_INT > 23)
            ei = new ExifInterface(input);
        else
            ei = new ExifInterface(selectedImage.getPath());

        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                return rotateImage(img, 90);
            case ExifInterface.ORIENTATION_ROTATE_180:
                return rotateImage(img, 180);
            case ExifInterface.ORIENTATION_ROTATE_270:
                return rotateImage(img, 270);
            default:
                return img;
        }
    }

    private static Bitmap rotateImage(Bitmap img, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap rotatedImg = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
        img.recycle();
        return rotatedImg;
    }

    private void guardarImagen(String codPaciente, String tipoFoto) {

        nomImagen = codPaciente.concat("_Cirugia_").concat(tipoFoto).concat("_") + new SimpleDateFormat("ddMMyyyy_HHmmss", Locale.getDefault()).format(new Date()) + ".jpg";

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.DISPLAY_NAME, nomImagen);
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        values.put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_DCIM + "/MyInternalShip");

        Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        try {

            OutputStream out = getContentResolver().openOutputStream(uri);
            Bitmap bitmap = ((BitmapDrawable) imgFotoTomadaoEncontrada_Cirugia.getDrawable()).getBitmap();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        String fecha = TituloFoto_Add_Cirugia.getText().toString();
        String descripcion = DescripcionFoto_Add_Cirugia.getText().toString();

        float code = funcionalidad_cirugia.insertar_Cirugia_Foto(fecha, descripcion, nomImagen, codPaciente, tipoFoto);

        if (code > 0) {
            Toast.makeText(this, "Imagen guardada", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al guardar la imagen", Toast.LENGTH_SHORT).show();
        }

    }

    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Cirugia_Imagen_Add.this, Cirugia_Imagenes.class);
        intent.putExtra("idPaciente", finalIdPac);
        startActivity(intent);

    };

}