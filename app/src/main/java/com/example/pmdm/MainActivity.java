package com.example.pmdm;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * Actividad: Menus contextuales
 * @author JC
 * @version 0.1
 *
 * Explica el uso de :
 *
 * 1- Menu contextual flotante
 *
 */
public class MainActivity extends AppCompatActivity {


    private Button btn_floating;
    private Button btn_action;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_floating = (Button)   findViewById(R.id.btn_floating);
        btn_action = (Button)   findViewById(R.id.btn_action);

        //Todo 1. Debemos registrar la vista donde queremos que aparezca el menu contextual
        registerForContextMenu(btn_floating);
    }

    //Todo 2. Sobrecargamos el metodo onCreateContextMenu para crear la vista del
    // menu a partir de nuestro xml.
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.floating_menu,menu);
        menu.setHeaderTitle("Menu flotante");
    }

    //Todo 3. Sobrecargamos el metodo onContextItemSelected para manejar los eventos
    // que se produzcan al ser pulsados
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.fl_blue) {
            btn_floating.setBackgroundColor(Color.BLUE);
        } else if (itemId == R.id.fl_green) {
            btn_floating.setBackgroundColor(Color.GREEN);
        } else if (itemId == R.id.fl_red) {
            btn_floating.setBackgroundColor(Color.RED);
        }

        return true;
    }

    @Override
    public void onContextMenuClosed(@NonNull Menu menu) {
        myToast("Menu cerrado");
    }

    /**
     * Método que encapsula la acción Toast, evita tener que repetir código en los parámetros que
     * nunca cambian, como puede ser la duración, el contexto o que se olvide llamar al método show()
     * @param msg Mensaje a mostrar en el toast.
     */
    public void myToast(String msg){

        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }


}