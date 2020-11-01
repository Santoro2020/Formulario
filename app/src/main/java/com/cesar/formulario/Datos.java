package com.cesar.formulario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Datos extends AppCompatActivity {

   // private TextView tvnombre;

    //@Override
    //protected void onCreate(Bundle savedInstanceState) {
      //  super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_datos);

        //tvnombre= (TextView)findViewById(R.id.tvnombre);

        //String nombre= getIntent().getStringExtra("nombre");
        //tvnombre.setText(nombre);
    //}
    //Boton eitar

    //public void editar(View view){
      //  Intent i=new Intent(this,MainActivity.class);
        //startActivity(i);
    //}
    // Elementos de la vista
    TextView tvnombre;
    TextView tvfdn;
    TextView tvtelefono;
    TextView tvemail;
    TextView tvdescripcion;
    Button bteditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        Bundle b = getIntent().getExtras();

        tvnombre = (TextView) findViewById(R.id.tvnombre);
        tvfdn = (TextView) findViewById(R.id.tvfdn);
        tvtelefono = (TextView) findViewById(R.id.tvtelefono);
        tvemail = (TextView) findViewById(R.id.tvemail);
        tvdescripcion = (TextView) findViewById(R.id.tvdescripcion);
        bteditar = (Button) findViewById(R.id.bteditar);

        tvnombre.setText(b.getString("nombre"));
        tvfdn.setText(b.getString("fecha"));
        tvtelefono.setText(b.getString("telefono"));
        tvemail.setText(b.getString("email"));
        tvdescripcion.setText(b.getString("descripcion"));

        bteditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(creaIntent());
                finish();
            }
        });
    }

    private Intent creaIntent() {

        Intent intent = new Intent(this,MainActivity.class);

        String nombre = tvnombre.getText().toString();
        if (nombre.equals("Campo obligatorio"))
            nombre = null;

        String fecha = tvfdn.getText().toString();

        String telefono = tvtelefono.getText().toString();
        if (telefono.equals("Campo obligatorio"))
            telefono = null;

        String email = tvemail.getText().toString();
        if (email.equals("Campo obligatorio"))
            email = null;

        String descripcion = tvdescripcion.getText().toString();
        if (descripcion.equals("Campo obligatorio"))
            descripcion = null;

        intent.putExtra("nombre", nombre);
        intent.putExtra("fecha", fecha);
        intent.putExtra("telefono", telefono);
        intent.putExtra("email", email);
        intent.putExtra("descripcion", descripcion);

        return intent;
    }
}
