package com.example.pmdm;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //Todo 1. Declaración de los elementos de la interfaz
    private Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Todo 2. Inicialización de los elementos de la interfaz.
        this.spinner = (Spinner) findViewById(R.id.spinner);
        //Todo 3. Creamos un adaptador que unirá la lista de elementos, con el diseño que se usará para mostrar cada fila (Android ya trae un disñeño por defecto)
        ArrayAdapter adaptador = ArrayAdapter.createFromResource(this,R.array.provincias, android.R.layout.simple_spinner_item);

        //Todo 4. Elegimos el diseño por defecto, de como se quiere mostrar el desplegable.
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Todo 5. Asigamos el adaptador al spinner
        this.spinner.setAdapter(adaptador);

        //Todo 6. Creamos el listener correpondiente para que capture el evento de selección
        this.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String provincia = (String) adapterView.getItemAtPosition(i);
                myToast("Mi provincia es: " + provincia);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                myToast("No has seleccionado nada");
            }
        });

    }

    //Todo 3. Creamos una notificación simple
    public void myToast(String msg){
        Toast.makeText(this,msg, Toast.LENGTH_LONG).show();
    }
}