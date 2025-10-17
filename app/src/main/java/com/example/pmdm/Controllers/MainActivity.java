package com.example.pmdm.Controllers;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pmdm.Adapters.RecyclerAdapter;

import com.example.pmdm.Models.Pelicula;
import com.example.pmdm.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Actividad que explica los componentes necesarios para el uso de listas con RecyclerView.
 *
 * Organización previa:
 *
 *    1.- Se debe tener en cuenta la organización del proyecto separado en paquetes que sigan el patrón MVC
 *
 * @author JC
 * @version 0.1
 *
 *
 */
public class MainActivity extends AppCompatActivity {

    //Todo 1. Se declaran los tipos de datos RecyclerView, que engloba la lista, y RecyclerAdapter, que enlazará los datos
    //todo -> con cada elemento de la lista. En este caso RecyclerAdapter es una clase que debemos impelementar nosotros.
    RecyclerView recyclerView;
    RecyclerAdapter recAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Todo 3. Se inicializan los objetos recyclerView y recyclerAdapter(Ver punto 2), pasandole
        // a este último la lista (3.1)
        recyclerView = (RecyclerView) findViewById(R.id.recView);

        recAdapter = new RecyclerAdapter(getListMovie());

        //Todo 3.2 La clase layaoutManager se encarga de gestionar la disposición de los elementos
        // de la lista dentro del recyclerView. Existen diferentes opciones, como gridLayoutManager
        // que los coloca en vista de rejilla. Para este caso se ha usado Linear, para una
        // disposición básica (un elemento debajo de otro).
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        //Todo 3.2 Por último solo debemos añadir los elementos creados anteriormente a la vista
        // padre (RecyclerView) con sus respectivos métodos.
        recyclerView.setAdapter(recAdapter);
        recyclerView.setLayoutManager(layoutManager);

    }

    //Todo 3.1 Como no sabemos (todavía) recuperar datos desde una base de datos se ha creado un
    // método auxiliar que emule la obtención de datos. Para ello se han importado diferentes imágenes
    // en la carpeta Drawable y se han creado diferentes objetos de la clase Peliculas almacenandolas
    // en un arrayList que posteriormente será devuelto.
    public static List<Pelicula> getListMovie(){

        ArrayList<Pelicula> list = new ArrayList<Pelicula>();

        list.add(new Pelicula("Aquaman","Una vez se cansó de ser el mejor en juego de tronos, cogió un tridente y se fue a nadar con los peces",R.drawable.aqua));
        list.add(new Pelicula("Batman vs Superman","Un hombre disfrazado de murciélago se pelea con un hombre con la ropa interior roja por fuera",R.drawable.batvssup));
        list.add(new Pelicula("Justice League","Un hombre disfrazado, el de juego de tronos, un robot, una mujer con una cuerda y el hombre de la ropa interior se pelean con un bicho",R.drawable.justleague));
        list.add(new Pelicula("Superman: Hijo rojo","¿Y si la ropa interior la hubiera comprado en Rusia?",R.drawable.supred));
        list.add(new Pelicula("Wonder Woman","Una mujer superfuerte con un lazo, no quiere que nadie sepa que es superfuerte y que tiene un lazo",R.drawable.wonwom));
        list.add(new Pelicula("Thor","Un hombre rubio superfuerte se pelea con su hermano y se encuentra con una de las gemas del...oh wait!! que alguien despida al becario!!",R.drawable.infiltrado));

        return list;

    }


}