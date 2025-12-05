package com.example.pmdm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Actividad Hilos: En esta actividad se introducirán el tema de hilos secundarios dentro de las
 * aplicaciones Android.
 *
 * @author JC
 * @version 0.1
 *
 *CONSIDERACIONES PREVIAS:
 *  1. Se ha preparado 3 métodos donde se vea el uso de hilos en Android, con un supuesto suceso
 *  costoso en tiempo y que debe realizarse en un hilo secundario.
 *  2. Este proceso será emulado a través del método Thread.sleep() el cual hace esperar al
 *  procesador un timepo determinado (en este caso 1 segundo).
 *
 */
public class MainActivity extends AppCompatActivity {

    private Button btnStart;
    private Button btnCancel;
    private TextView mTextView;
    private Thread hilo;
    private int i = 0;


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

                btnStart.setEnabled(false);
                btnCancel.setEnabled(true);

                //Todo 1. Este contandor hace que la aplicación se quede colgada
                //contador();

                //Todo 2. Este contador da error porque el hilo secundario no puede acceder a la interfaz.
                //contadorConHilo();

                //Todo 3. Este contador funciona ya que el hilo secundario realiza
                // la acción y le pasa el resultado al hilo principal
                contadorConUI();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnStart.setEnabled(true);
                btnCancel.setEnabled(false);

                if(hilo.isAlive()) {
                    hilo.interrupt();
                }
            }
        });
    }

    public void contador(){

        for(int i=0; i<=100; i++){
            try {
                Thread.sleep(1000);
                mTextView.setText(i+"%");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Método de ejemplo que ejecuta un proceso en un hilo secundario introduciendo este el resultado
     * en la vista.
     */
    public void contadorConHilo(){

        hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean fin = false;
                for(int i=0; i<=100 && !fin; i++){
                    try {
                        Thread.sleep(1000);
                        mTextView.setText(i+"%");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        fin = true;
                    }
                }
            }
        });
        hilo.start();
    }


    /**
     * Método en el que un hilo secundario realiza un proceso y le pasa el resultado al hilo principal
     * a través de runOnUiThread().
     */
    public void contadorConUI(){

        hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean fin = false;
                for(i=0; i<=100 && !fin; i++){
                    try {
                        Thread.sleep(1000);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mTextView.setText(i+"%");
                            }
                        });

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        fin = true;
                    }
                }
            }
        });
        hilo.start();

    }

}