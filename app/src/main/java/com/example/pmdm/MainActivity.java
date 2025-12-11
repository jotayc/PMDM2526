package com.example.pmdm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Actividad AsyncTask: En esta actividad se el tema de las tareas asíncronas a través de la api de
 * Android, AsyncTask
 *
 * @author JC
 * @version 0.1
 *
 *
 */
public class MainActivity extends AppCompatActivity {

    private Button btnStart;
    private Button btnCancel;
    private TextView mTextView;

    //Todo 2. Declaramos un tipo de dato de la clase que hereda de AsyncTask
    private PublishTask mTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart    = (Button) findViewById(R.id.btn_calc);
        btnCancel   = (Button) findViewById(R.id.btn_cancel);
        mTextView   = (TextView) findViewById(R.id.textView);



        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Todo 2.1 .Cada instancia solo se puede ejecutar una vez, por ello tendrémos que
                // crearnos una nueva instancia si queremos ejecutarla más de una vez
                mTask = new PublishTask();
                mTask.execute();

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mTask.cancel(true);

            }
        });
    }

    //Todo 1. Creo una clase que herede de la clase ABSTRACTA AsyncTask. Esta clase recibe tres
    // tipos de datos segun los parámetros que necesitemos para realizar nuestra tarea:
    // 1º Es el tipo de dato necesario para el proceso asincrono, el que se realizará en el proceso secundario.
    // 2º Es el tipo de dato de los paramatros que actualizarán el progreso de la tarea asincrona, en la interfaz.
    // 3º Es el tipo de dato del resultado que devuelve la tarea asincrona una vez finalizada.


    private class PublishTask extends AsyncTask<Void, Integer, Void>{

        //Todo 1.1. Este metodo se utiliza para realizar cualquier preparación de la vista antes de
        // realizar la tarea asincrona.
        // SE EJECUTA EN EL HILO PRINCIPAL
        @Override
        protected void onPreExecute() {
            btnStart.setEnabled(false);
            btnCancel.setEnabled(true);
        }

        //Todo 1.2 Este metodo se utiliza para realizar la tarea asincrona.
        // El resultado que retorna  posteriormente es recogido por el método OnPostExecute como
        // parametro. (En este ejemplo al no devolver nada es Void)
        // SE EJECUTA EN EL HILO SECUNDARIO
        @Override
        protected Void doInBackground(Void... voids) {

            for(int i=0; i<=100 && !isCancelled(); i++){
                try {
                    Thread.sleep(1000);
                    //Todo 1.3. Método que llama a onProgressUpdate() pasandole como parametro el
                    // elemento necesario para actualizar la vista
                    publishProgress(i);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        //Todo 1.4. Método que nos permite mostrar progresos en la vista durante la ejecución de la
        // tarea  -> asincrona, en caso de que se pueda usar información del proceso ejecutandose en el
        // Todo -> hilo secundario. Este método se activara CADA VEZ que se llame al método publishProgress().
        // Todo ->SE EJECUTA EN EL HILO PRINCIPAL
        @Override
        protected void onProgressUpdate(Integer... values) { //Esta sintaxis en los parámetros quiere decir que el metodo puede recibir un numero indeterminado de parámetros. Se usa como un array estático.
            super.onProgressUpdate(values);
            Log.d("AT", "OnPublish"+values[0]);
            mTextView.setText(values[0] + "%");

        }

        //Todo 1.5 Método que se llama en el caso que se cancele el proceso.
        // Este método será llamado a través del método cancel().
        // Si este método es llamado NO SE LLAMARÁ AL METODO onPostExecute()
        // SE EJECUTA EN EL HILO PRINCIPAL.
        @Override
        protected void onCancelled(Void unused) {
            super.onCancelled();
            Toast.makeText(MainActivity.this,"Proceso cancelado", Toast.LENGTH_SHORT).show();
            btnStart.setEnabled(true);
            btnCancel.setEnabled(false);
        }

        //Todo 1.6. Método que nos permite realizar acciones cuando el proceso asincrono haya finalizado.
        // Este método recibirá por parámetro el resultado del valor retornado en el método
        // doInBackground.
        // SE EJECUTA EN EL HILO PRINCIPAL
        @Override
        protected void onPostExecute(Void unused) {
            btnStart.setEnabled(true);
            btnCancel.setEnabled(false);
        }
    }



}