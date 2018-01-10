package com.example.pedro.intenciones;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

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

    @SuppressLint("MissingPermission")
    public void actionCall2(View view) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:620910311"));
        startActivity(intent);
    }


}




