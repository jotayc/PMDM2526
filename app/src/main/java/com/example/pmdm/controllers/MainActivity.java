package com.example.pmdm.controllers;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pmdm.R;
import com.example.pmdm.Resources.DBAccess;

/**
 * Actividad SQLite En esta actividad se explica una versión simplificada de cómo funcionan las bases
 * de datos SQLite en Android.
 * @author JC
 * @version 0.2
 *
 *  CONSIDERACIONES PREVIAS:
 *   1. El diseño de las tablas no es correcto, puesto que faltaría una clave primaria en la tabla 'ciudades'
 *   2. El funcionamiento de este ejemplo no está completo (no se muestra toda la información de las tablas de la BBDD)
 *   3. Por simplificación en la explicación, este ejemplo, no sigue el patrón MVC (Habría que crearse la clase 'City' o 'Ciudad')
 *
 */
public class MainActivity extends AppCompatActivity {

    EditText mEtPobl;
    EditText mEtCity;
    EditText mEtSurf;
    TextView mTvView;
    Button mButton;
    Button mBtnMostrar;
    DBAccess mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton         = (Button)   findViewById(R.id.btnInsert);
        mBtnMostrar     = (Button)   findViewById(R.id.btnShow);
        mEtPobl = (EditText) findViewById(R.id.etPoblation);
        mEtSurf = (EditText) findViewById(R.id.etSurface);
        mEtCity = (EditText) findViewById(R.id.idCity);

        mTvView         = (TextView) findViewById(R.id.txtVw);

        mDB = new DBAccess(this);
        mDB.getVersionDB();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String city = mEtCity.getText().toString();

                int pobl    = Integer.parseInt(mEtPobl.getText().toString());
                float surf   = Float.valueOf(mEtSurf.getText().toString());

                if(mDB.insert(city, pobl, surf) != -1){
                    Toast("Ciudad insertada correctamente");
                }else{
                    Toast("Error! Ciudad no insertada. Comprueba LogCat");
                }
            }
        });

        mBtnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ciudad = mDB.getFirstCity();

                if(ciudad != null){
                    mTvView.setText(ciudad);
                }else{
                    Toast("Problema al recuperar la ciudad");
                }

            }
        });

    }

    public void Toast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}