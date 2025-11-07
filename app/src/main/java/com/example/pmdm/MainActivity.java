package com.example.pmdm;

import android.graphics.Color;
import android.os.Bundle;
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
 *
 * @author JC
 * @version 0.1
 * <p>
 * Explica el uso de :
 * <p>
 * 1- Menu de acción
 *
 *  Documentación -> https://developer.android.com/develop/ui/views/components/menus?hl=es-419#CAB
 */
public class MainActivity extends AppCompatActivity {

    private androidx.appcompat.view.ActionMode mActionMode;

    private Button btn_floating;
    private Button btn_action;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_floating = (Button) findViewById(R.id.btn_floating);
        btn_action = (Button) findViewById(R.id.btn_action);

        btn_action.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                boolean res = false;
                if (mActionMode == null) {
                    mActionMode = startSupportActionMode(mActionCallback);
                    res = true;
                }

                return res;
            }
        });


    }


    /**
     * Uso de la clase anónima Callback, como siempre, la clase anonima devuelve una instancia de
     * una clase creada por java (nos da igual cuál). Dicha instancia es la encargada de implementar
     * la interfaz. A diferencia de otras veces, en las que se resuelve en el propio método, por ejemplo
     *  en setOnClickListener(new OnCliclister....), esta vez se ha guardado la instance en una variable
     * mActionCallback por mejora de la legibilidad de código.
     *
     */
    private ActionMode.Callback mActionCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.action_menu, menu);
            mode.setTitle("Action Menu");
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            int itemId = item.getItemId();

            if (itemId == R.id.act_light_blue) {
                btn_action.setBackgroundColor(Color.CYAN);
            } else if (itemId == R.id.act_purple) {
                btn_action.setBackgroundColor(Color.MAGENTA);
            } else if (itemId == R.id.act_yellow) {
                btn_action.setBackgroundColor(Color.YELLOW);
            }

            mode.finish();
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;
        }
    };


    /**
     * Método que encapsula la acción Toast, evita tener que repetir código en los parámetros que
     * nunca cambian, como puede ser la duración, el contexto o que se olvide llamar al método show()
     *
     * @param msg Mensaje a mostrar en el toast.
     */
    public void myToast(String msg) {

        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}