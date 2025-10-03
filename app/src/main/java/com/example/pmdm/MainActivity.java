package com.example.pmdm;

import static com.example.pmdm.SecondActivity.INFO_ID;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText edTxtMain;
    Button btnMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edTxtMain = (EditText) findViewById(R.id.edtxt_main);
        btnMain = (Button) findViewById(R.id.btn_main);

        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,SecondActivity.class);

                String msg = edTxtMain.getText().toString();
                if(msg != null && msg != "")
                    i.putExtra(INFO_ID, msg);

                startActivity(i);
            }
        });


    }
}