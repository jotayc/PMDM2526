package com.example.pmdm.controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pmdm.R;


/**
 * Actividad Preferencias: En esta actividad se estudiará la persistencia de datos simples como son
 * las configuraciones de usuario a través de la API propia de Android.
 *
 * @author JC
 * @version 0.1
 *
 *  Consideraciones previas:
 *
 *    1 - El diseño de la configuración debe estar en la carpeta res/xml
 *    2 - Se debe importar la biblioteca preferences añadiendo a gradle la instrucción
 *          implementation 'androidx.preference:preference:1.1.1'
 *    3 - Consultar la guia oficial https://developer.android.com/guide/topics/ui/settings?hl=es-419
 *
 */
public class MainActivity extends AppCompatActivity {

    private TextView txtView;
    private Button   mPrefBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtView = (TextView) findViewById(R.id.txtView);
        mPrefBtn = (Button)  findViewById(R.id.prefBtn);

        mPrefBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo 0. Se debe crear la actividad que contendrá la funcionalidad de la
                // configuración. En este caso se ha creado la actividad SettingActivity y su
                // layout, activity_setting.xml

                Intent i = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(i);
            }
        });

        // Todo 5. Recordad que esto es solo un ejemplo y que al ponerlo en el método onCreate solo se va a
        //  precargar al inicio de la aplicación. Ustedes deberéis cargarlo en el método que creáis
        //  oportuno según las necesidades de vuestra aplicación.
        loadPreferences();

    }


    public void loadPreferences(){

        //Todo 4. Una vez creado todo, solo debemos de preocuparnos de acceder a la información,
        // ya  que Android se encarga del almacenamiento de los datos que introduce el usuario en
        // la ventana de preferencias.

        //Todo 4.1 Utilizamos PreferenceManager para obtener las preferencias compartidas de nuestra
        // aplicación. TENEIS QUE TENER EN CUENTA QUE ESTE ES EL MISMO PARA TODA LA APP (PATRÓN SINGLETON)


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        // Todo 4.2 Una vez tenemos acceso a las preferencias compartidas, solo debemos acceder mediante la clave para obtener su valor
        String emailText = sharedPreferences.getString("emailPreference","Valor por defecto");
        txtView.setText(emailText);
    }
}