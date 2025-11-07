package com.example.pmdm;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * Actividad Menu simple
 * @author JC
 * @version 0.2
 *
 *  En esta rama se explicará los conceptos:
 *  1- Crear un menu simple e intercatuar con los items que lo componen.
 *
 *  Consideraciones previas:
 *   1.Se debe crear una carpeta nueva en res/menu
 *   2.Dentro de esta, se crea un xml cuyo elemento raiz es <menu></menu>
 *   3. Importante el atributo android:showAsAction cuyo fin es indicar cómo se debe mostrar los item
 *      en el menu. Existen diferentes formas que podéis verlo en la documentación oficial
 *      https://developer.android.com/guide/topics/resources/menu-resource?hl=es-419
 *
 */
public class MainActivity extends AppCompatActivity {

    private ConstraintLayout constraintLayout;
    int selectedColor = Color.WHITE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        constraintLayout = (ConstraintLayout) findViewById(R.id.root_const_lay);

    }

    //Todo 1. Se sobreescribe el metodo onCreateOptionsMenu para indicar que nuestra app tendra
    // un menu personalizado.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Todo 1.1 Se usa un inflater para construir la vista y se pasa el menu por defecto para
        // que Android se encargue de colocarlo en la vista
        getMenuInflater().inflate(R.menu.simple_menu,menu);

        return true;
    }

    //Todo 2. Se sobreescribe el metodo onOptionsItemSelected para manejar las selecciones a través
    // de los diferentes item del menu.
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.item_blue) {
            selectedColor = Color.BLUE;
            myToast("Azul seleccionado");
        } else if (itemId == R.id.item_green) {
            selectedColor = Color.GREEN;
            myToast("Verde seleccionado");
        } else if (itemId == R.id.item_red) {
            selectedColor = Color.RED;
            myToast("Rojo seleccionado");
        } else if (itemId == R.id.item_apply) {
            constraintLayout.setBackgroundColor(selectedColor);
        }

        return true;
    }

    public void myToast(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }
}