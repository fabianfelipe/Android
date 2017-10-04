package com.example.house.myproyect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // private ListaCliente auxListaCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // this.setAuxListaCliente(new ListaCliente());

    }

    public void guardar(View view) {
        try {

            EditText auxRut = (EditText) findViewById(R.id.txt_rut);
            EditText auxNombre = (EditText) findViewById(R.id.txt_nombre);

            Cliente auxCliente = new Cliente();
            auxCliente.setRut(auxRut.getText().toString());
            auxCliente.setNombre(auxNombre.getText().toString());

            //this.getAuxListaCliente().agregarCliente(auxCliente);

            Mantenedor_cliente auxNegocio = new Mantenedor_cliente(this);

            auxNegocio.insertarDatos(auxCliente);
            this.mensaje("Cliente registrado");

            auxRut.setText("");
            auxNombre.setText("");

            auxRut.requestFocus();

        }catch (Exception ex)
        {
            this.mensaje("Error al guardar el Cliente" + ex.getMessage());
        }
    }

    public void mostrar(View view) {

        //Intent intent = new Intent(this,listado.class);
        Mantenedor_cliente auxNegocio = new Mantenedor_cliente(this);

        List<Cliente> auxLista = auxNegocio.retornaClientes();

        String[] listaString = new String[auxLista.size()];

        Iterator iter = auxLista.iterator();

        int pos = 0;

        while(iter.hasNext())
        {
            Cliente auxCliente = new Cliente();
            auxCliente = (Cliente) iter.next();
            listaString[pos] = auxCliente.getRut() + " " +auxCliente.getNombre();
            pos++;
        }

        ListView auxListView = (ListView) findViewById(R.id.lv_lista);

        auxListView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaString));

    }

    public void eliminar(String rut)
    {
        EditText auxRut = (EditText) findViewById(R.id.txt_rut);

    }

    public void mensaje(String mensaje)
    {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

   // public ListaCliente getAuxListaCliente() {
//        return auxListaCliente;
  //  }

    //public void setAuxListaCliente(ListaCliente auxListaCliente) {
     //   this.auxListaCliente = auxListaCliente;
    //}
}
