package com.example.augusto.localizacao;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, LocationListener {

    private TextView atualLatitude;
    private TextView atualLongitude;
    private Button btnSalvar;
    private Button btnRecarregar;
    private TextView ultimaLatitude;
    private TextView ultimaLongitude;

    private SQLiteDatabase db = null;
    private Cursor cur = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        atualLatitude = (TextView) findViewById(R.id.atualLatitude);
        atualLongitude = (TextView) findViewById(R.id.atualLongitude);
        btnSalvar = (Button)  findViewById(R.id.btnSalvar);
        btnRecarregar = (Button)  findViewById(R.id.btnRecarregar);
        ultimaLatitude = (TextView) findViewById(R.id.ultimaLatitude);
        ultimaLongitude = (TextView) findViewById(R.id.ultimaLongitude);

        db = new DataBaseManager(this, "localizacao", null, 1).getWritableDatabase();

        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

            return;
        }
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

        //recarregarLocal();
        buscarUltimoLocal();

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues dados = new ContentValues();
                dados.put("latitude", atualLatitude.getText().toString());
                dados.put("longitude", atualLongitude.getText().toString());
                db.insert("local", null, dados);
                buscarUltimoLocal();
            }
        });

        btnRecarregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recarregarLocal();
            }
        });
    }

    public void buscarUltimoLocal() {
        cur = db.query("local", new String[]{"id", "latitude", "longitude"}, null, null, null, null, null);
        cur.moveToLast();
        ultimaLatitude.setText(cur.getString(1));
        ultimaLongitude.setText(cur.getString(2));
    }

    public void recarregarLocal() {
        Random rand = new Random();
        atualLatitude.setText(rand.nextInt(1000) + 1); // APAGAR
        atualLongitude.setText(rand.nextInt(1000) + 1); // APAGAR
    }

    @Override
    public void onLocationChanged(Location location) {
        atualLatitude.setText(location.getLatitude() + "");
        atualLongitude.setText(location.getLongitude() + "");
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onClick(View view) {

    }
}
