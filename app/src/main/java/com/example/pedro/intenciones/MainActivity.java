package com.example.pedro.intenciones;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int SOLICITUD_PERMISO_CALL_PHONE = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pgWeb(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.androidcurso.com"));
        startActivity(intent);
    }

    //CALL realiza la llamada directa al numero de la URI, y DIAL solo lo marca, pero no realiza la llamada
    @SuppressLint("MissingPermission")
    public void llamadaTelefonoCALL(View view) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:620910311"));
        startActivity(intent);
    }

    public void googleMaps(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:41.656313,-0.877351"));
        startActivity(intent);
    }

    public void tomarFoto(View view) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivity(intent);
    }

    public void mandarCorreo(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "asunto");
        intent.putExtra(Intent.EXTRA_TEXT, "texto del correo");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"demo@upv.es"});
        startActivity(intent);
    }

    // CALL realiza la llamada directa al numero de la URI, y DIAL solo lo marca, pero no realiza la llamada
    public void llamadaTelefonoDial(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:620910311"));

        startActivity(intent);

    }

    public void streetView(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.streetview:cbll=46.414382,10.013988"));
        startActivity(intent);
    }

    public void webSearch(View view) {
        Intent intent = new Intent(Intent.ACTION_SEARCH);
        intent.putExtra(SearchManager.QUERY, "cross skating");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    public void actionCall2(View view) {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE)
                == PackageManager.PERMISSION_GRANTED) {
            //si tenemos el permiso ejecutamos
            hacerLLamada2();
        } else {
            solicitarPermiso(Manifest.permission.CALL_PHONE, "Sin el permiso" +
                            " no puedo hacer llamadas.",
                    SOLICITUD_PERMISO_CALL_PHONE, this);
        }
    }

    public void solicitarPermiso(final String permiso, String justificacion, final int request_code, final MainActivity actividad) {

        if (ActivityCompat.shouldShowRequestPermissionRationale(actividad, permiso)) {
            new AlertDialog.Builder(actividad)
                    .setTitle("Solicitud de permiso")
                    .setMessage(justificacion)
                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(actividad, new String[]{permiso}, request_code);
                        }
                    })
                    .show();
        } else {
            ActivityCompat.requestPermissions(actividad, new String[]{permiso}, request_code);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == SOLICITUD_PERMISO_CALL_PHONE) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                hacerLLamada2();
            } else {
                Toast.makeText(this, "permiso de llamada no permitido", Toast.LENGTH_LONG).show();
            }
        }
    }

    @SuppressLint("MissingPermission")
    public void hacerLLamada2() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:620910311"));
        startActivity(intent);

    }


}




