package com.example.pmdm.Resources;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBAccess extends SQLiteOpenHelper {

    //Todo 1. Extendemos la clase con SQLiteOpenHelper para tener acceso a los métodos que gestiona
    //todo-> la base de datos.

    //Database name
    private static final String DB_NAME = "db_dam";

    //Table name
    private static final String DB_TABLE_NAME = "db_ciudades";

    //Database version must be >= 1
    private static final int DB_VERSION = 3;

    //Columns
    private static final String CITY_COLUMN = "cityName";

    private static final String POBLATION_COLUMN = "poblation";

    private static final String SURFACE_COLUMN = "surface";

    //Application Context
    private Context mContext;




    /**
     * Constructor de la base de datos, si no existe la base de datos la crea, sino se conecta.
     *  En el caso de que se hiciese una actualización y se cambiase la versión,
     *  el constructor llamaría al método onUpgrade para actualizar los cambios de la base de datos.
     * @param context Contexto de la aplicación
     */
    public DBAccess(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        mContext = context;

    }

    //Todo 2. Sobrecargamos onCreate, encargado de crear las tablas asociadas a la base de datos.
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Todo 2.1 Cada modificación que hagamos en la estructura de la tabla ya sea, añadir, modificar
        //todo -> o eliminar columnas, esto debemos reflejarlo tanto en el método onCreate, para los
        //todo -> que nuevos usuarios, ya se creen la BD en su última versión. Como en el onUpgrade,
        //todo -> para que los usuarios que tengan la aplicación puedan actualizar su base de datos
        //todo -> desde la última versión que tiene CADA USUARIO hasta la última versión de la BBDD que exista.

        // Version 1
        //String CREATE_USER_TABLE = "CREATE TABLE " +DB_TABLE_NAME+ "("
        //        +CITY_COLUMN+ " TEXT)";

        //Version 2
        /*String CREATE_USER_TABLE = "CREATE TABLE " +DB_TABLE_NAME+ "("
                +CITY_COLUMN+ " TEXT,
                " + POBLATION_COLUMN + " INTEGER")";*/

        //Version 3
        String CREATE_USER_TABLE =  "CREATE TABLE " +DB_TABLE_NAME+ "("
                +CITY_COLUMN+ " TEXT,"
                 + POBLATION_COLUMN + " INTEGER,"
                + SURFACE_COLUMN + " REAL" + ")";

        //Todo 2.1. Lanzamos la consulta con execSQL


        sqLiteDatabase.execSQL(CREATE_USER_TABLE);


        // Los mensajes LOG sirven para que el programación, durante el desarrollo pueda recibir mensajes
        // tecnicos sobre el funcionamiento del programa sin que el usuario las pueda ver.
        // Estos mensajes aparecen en la pestaña Logcat de Android studio.
        Log("Tablas creadas");

    }

    // Todo 3. Sobrecargamos onUpgrade, encargado de actualizar la base de datos y las tablas asociadas.

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {


        Log("onUpadre");
        Log("oldversion -> "+oldVersion);

        //Todo 3.1. En el caso de que hagamos algun cambio en la base de datos es necesario controlar
        //todo -> la versión de la BBDD que tiene el usuario e ir modificandola desde esta
        //todo -> en adelante. Ejemplo:
        //todo -> Usuario 1: versión de BD 1
        switch(oldVersion){
            case 1:
                sqLiteDatabase.execSQL("ALTER TABLE " + DB_TABLE_NAME  +" ADD COLUMN " + POBLATION_COLUMN +" INTEGER");
                Log.i("DB", "BBDD Actualizada a la versión 2");
            case 2:
                sqLiteDatabase.execSQL("ALTER TABLE " + DB_TABLE_NAME  +" ADD COLUMN " + SURFACE_COLUMN +" REAL");
                Log.i("DB", "BBDD Actualizada a la versión 3");
            /*case 3:
                sqLiteDatabase.execSQL("ALTER TABLE "+ DB_TABLE_NAME   +" ADD COLUMN gentilicio TEXT");
                Log.i("DB", "BBDD Actualizada a la versión 3");*/

        }



    }


    public void getVersionDB(){
        Log(Integer.toString(this.getReadableDatabase().getVersion()));
    }

    //Todo 4. Creamos un método para insertar un dato en la BD.
    public long insert(String city, int poblation, float superficie){

        //Todo 4.1. Pedimos acceso de escritura en la base de datos.
        SQLiteDatabase db = this.getWritableDatabase();
        long result = -1;

        // Contenedor clave,valor -> columna, valor de entrada registro
        ContentValues values = new ContentValues();

        values.put(CITY_COLUMN, city);
        values.put(POBLATION_COLUMN, poblation);
        values.put(SURFACE_COLUMN, superficie);

        //Todo 4.2. Insertamos a través del método insert, cuyos parametro son:
        //todo -> nombre de la tabla
        //todo -> nullColumnHack permite indicar si hay una columna cuyo valor pueda ser nulo.
        //todo -> valores asociados a la inserción.

        result = db.insert(DB_TABLE_NAME,null,values);

        //Se cierra la conexión de la base de datos
        db.close();

        return result;

    }

    //Todo 5. Creamos un método para recuperar datos en la BD.
    public String getFirstCity(){

        String result = null;

        //Todo 5.1. Pedimos acceso de lectura de la BD.
        SQLiteDatabase db = this.getReadableDatabase();

        //Todo 5.2. Realizamos la consulta a través del método 'query', cuyo significado de los
        // todo-> parámetros tenemos en los apuntes. Este método devuelve un cursor que nos
        // todo-> permite recorrer las tuplas del resultado.
        String[] cols = new String[]{ CITY_COLUMN };



        //Un cursor es un tipo de dato que se mueve entre los registros devueltos por una consulta de una base de datos.
        Cursor c = db.query(DB_TABLE_NAME,cols,null,null,null,null,null);
        if(c.moveToFirst()) {
            //Todo 5.4. Cogemos el valor referente a la posicion de la columna
            String city = c.getString(0);
            result = city;
        }

        //Todo 5.5. Cerramos el cursor
        if(c != null) {
            c.close();
        }

        //Todo 5.6. Cerramos la base de datos.
        db.close();

        return result;

    }


    //Todo 6. Ejemplo de acceso a base de datos con argumentos
    public ArrayList<String> getAllCities(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> resultCities = new ArrayList<>();

        String[] cols = new String[]{ CITY_COLUMN };

        //String selection = "city=? AND poblation>?"; // -> el caracter interrogación será sustituido por los valores del array 'args' en orden de aparición
        //String[] args = new String[]{"jerez", "100"};

        //Un cursor es un tipo de dato que se mueve entre los registros devueltos por una consulta de una base de datos.
        Cursor c = db.query(DB_TABLE_NAME,cols,null,null,null,null,null);

        //Todo 6.1. Movemos el iterador al primer elemento (si existe devuelve true sino false)
        if(c.moveToFirst()){

            do{
                //Todo 6.2. Cogemos el valor referente a la posicion de la columna
                String city = c.getString(0);

                resultCities.add(city);
            }while(c.moveToNext()); //Todo 6.3. Mientras exista siguientes registros el cursor va iterando sobre ellos
        }

        return resultCities;
    }


    public void Log(String msg){
        Log.d("DB", msg);
    }



}
