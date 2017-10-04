package com.example.house.myproyect;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by house on 30-09-2017.
 */

public class Mantenedor_cliente extends SQLiteOpenHelper {

    private static final int VERSION_BASEDATOS = 1;
    private static final String NOMBRE_BD = "pruebaDb";
    private static final String TABLA_CLIENTE = "CREATE TABLE cliente" +
                                                " (rut TEXT PRIMARY KEY , nombre TEXT)";

    public Mantenedor_cliente(Context context)
    {
        super(context, NOMBRE_BD,null, VERSION_BASEDATOS);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
         db.execSQL(TABLA_CLIENTE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
           db.execSQL("drop table if exists " + TABLA_CLIENTE);
           onCreate(db);
    }

    public void insertarDatos(Cliente cliente)
    {
        SQLiteDatabase db= getWritableDatabase();

        if(db != null)
        {
            db.execSQL("INSERT INTO cliente "
                       + " (rut, nombre)"
                       + "VALUES ("
                       + "'"+cliente.getRut()
                       + "','" + cliente.getNombre()
                       + "');");
        }

        db.close();
    }

    public void actualizarCliente(Cliente cliente)
    {
        SQLiteDatabase db = getWritableDatabase();

        if(db != null)
        {
            db.execSQL("UPDATE cliente "
                    + " SET"
                    + " nombre = '" + cliente.getNombre()
                    + "' WHERE rut = '"+cliente.getRut()
                    + "';");
        }

        db.close();
    }

    public void eliminarCliente(String rut)
    {
        SQLiteDatabase db = getWritableDatabase();

        if(db != null)
        {
            db.execSQL("DELETE FROM cliente "
                    + " WHERE rut = '" + rut + "';");

        }

        db.close();
    }

    public void eliminarTodosLosClientes()
    {
        SQLiteDatabase db = getWritableDatabase();

        if(db != null)
        {
            db.execSQL("DELETE FROM cliente ;");
        }

        db.close();
    }

    public List<Cliente> retornaClientes()
    {
        SQLiteDatabase db = getWritableDatabase();
        List<Cliente> auxListaCliente = new ArrayList<>();

        Cursor auxCursor = db.rawQuery("SELECT * FROM cliente;",null);

        auxCursor.moveToFirst();

        do {
            Cliente auxCliente = new Cliente();
            auxCliente.setRut(auxCursor.getString(0));
            auxCliente.setNombre(auxCursor.getString(1));
            auxListaCliente.add(auxCliente);

        }while (auxCursor.moveToNext());

        auxCursor.close();
        db.close();

        return auxListaCliente;
    }

    public Cliente buscarCliente(String rut)
    {
        SQLiteDatabase db = getWritableDatabase();
        Cliente auxCliente = new Cliente();

        Cursor auxCursor = db.rawQuery("SELECR * FROM cliente" + "WHERE rut = '" + rut + "';", null);

        auxCursor.moveToFirst();

        if(auxCursor != null)
        {
            auxCliente.setRut(auxCursor.getString(0));
            auxCliente.setNombre(auxCursor.getString(1));

        }
        else
        {
            auxCliente.setRut("");
            auxCliente.setNombre("");
        }
        auxCursor.close();
        db.close();
        return auxCliente;
    }
}
