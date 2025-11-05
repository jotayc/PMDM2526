package com.example.pmdm;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

/**
 * Actividad: Notificaciones_Toast_Alerts_Logs
 * @author JC
 * @version 0.1
 *
 * Explica el uso de :
 *
 * 1- Toast como comunicación sin interacción con el usuario
 * 2- Alerts como medio de comunicación con el usuario
 * 3- Logs como medio de depuración.
 *
 */
public class MainActivity extends AppCompatActivity {


    Button btn_toast;
    Button btn_alert;
    Button btn_snack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_toast = (Button)   findViewById(R.id.btn_toast);
        btn_alert = (Button)   findViewById(R.id.btn_alert);
        btn_snack = (Button)   findViewById(R.id.btn_snack);


        btn_toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myLongToast("Esto es un toast igual que el primero que vimos");
            }
        });

        btn_alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo 2.4 Solo debemos crear un objeto AlertDialog y llamar al método que lo crea.
                //Todo -> Posteriormente lo mostramos.
                AlertDialog alertDialog = createAlertDialog("Mi titulo", "Esto es un mensaje de alerta");
                alertDialog.show();
            }
        });

        btn_snack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo 3. Creamos el snackbar y almacenamos su instancia
                Snackbar snackbar = Snackbar.make(view,"Esto es un mensaje snackbar",Snackbar.LENGTH_LONG);
                //Todo 3.1 OPCIONAL. Un snackbar puede tener asociado un botón. Esto es útil, por ejemplo
                //Todo -> para darle al usuario la opción de deshacer la ultima acción realizada.
                snackbar.setAction("Deshacer", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myLongToast("Aquí deshariamos lo que sea que hayamos hecho!!!");
                    }
                });

                snackbar.show();
            }
        });


    }


    /**
     * Método que encapsula la acción Toast, evita tener que repetir código en los parámetros que
     * nunca cambian, como puede ser la duración, el contexto o que se olvide llamar al método show()
     * @param msg Mensaje a mostrar en el toast.
     */
    public void myLongToast(String msg){
        //Todo 1 Un toast no es más que un mensaje de duración determinada SIN necesidad de interacción
        //Todo -> por parte del usuario. Hay que tener en cuenta que puede que el usuario no lo lea
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
    }

    /**
     * Esto es un método que encapsula la creación de cuadros de dialogos simples
     * @param titulo Contiene el título del cuadro de dialogo
     * @param mensaje Contiene el mensaje dentro del cuadro de dialogo
     * @return Devuelve el objeto AlertDialog
     */
    public AlertDialog createAlertDialog(String titulo, String mensaje){
        // Todo 2.1 Creamos un 'builder' o constructor que nos ayude a configurar el cuadro de dialogo
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        // Todo 2.2 Este objeto builder nos permitirá añadir todas las configuraciones que se necesiten
        // Todo -> antes de crear el alert. En este ejemplo se añade el mensaje y el titulo del alert
        builder.setMessage(mensaje)
                .setTitle(titulo);

        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Ha pulsado SIII", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "NOOOOO", Toast.LENGTH_SHORT).show();
            }
        });



        // Todo 2.3 Una vez hemos añadido todas las configuraciones creamos el alertDialog. En este
        // Todo -> caso, devolvemos el objeto creado.
        return builder.create();
    }

}