package com.cesar.formulario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText edtnombre;
    DatePicker dpfechanacimiento;
    EditText edttelefono;
    EditText edtemail;
    EditText edtdescripcion;
    Button btnenviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtnombre = (EditText) findViewById(R.id.edtnombre);
        dpfechanacimiento = (DatePicker) findViewById(R.id.dpfechanacimiento);
        edttelefono = (EditText) findViewById(R.id.edttelefono);
        edtemail = (EditText) findViewById(R.id.edtemail);
        edtdescripcion = (EditText) findViewById(R.id.edtdescripcion);
        btnenviar=(Button)findViewById(R.id.btnenviar);


    //Boton siguiente

    //public void Enviar(View view){
      //  Intent i = new Intent(this, Datos.class);
        //i.putExtra("nombre",edtnombre.getText().toString());
        //startActivity(i);
    Bundle b = getIntent().getExtras();

        if (b != null) {
        try {
            actualizaInfo(b);
        } catch ( ParseException e) {
            e.printStackTrace();
        }
    }

        btnenviar.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            startActivity(obtenerIntent());
            finish();
        }
    });
}

        private void actualizaInfo(Bundle b) throws ParseException {

        String nombre = b.getString("nombre");
        String fecha = b.getString("fecha");
        String telefono = b.getString("telefono");
        String email = b.getString("email");
        String descripcion = b.getString("descripcion");

        edtnombre.setText(nombre);
        edttelefono.setText(telefono);
        edtemail.setText(email);
        edtdescripcion.setText(descripcion);

       //FECHANACIMIENTO
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");

        Date date = sdf.parse(fecha);   

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        dpfechanacimiento.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH ),
                calendar.get(Calendar.DAY_OF_MONTH));

    }

    private Intent obtenerIntent() {

        String nombre = null;
        if (edtnombre.getText().length()>0)
            nombre = edtnombre.getText().toString();
        else nombre = "Campo obligatorio";
//LA VISUALIZACION DE FECHA EL MES HAY QUE SUMARLE 1 (VA DE 0 A 11), PARA QUE IMPRIMA DICIEMBRE (MES 12)
        String fecha = String.valueOf(dpfechanacimiento.getDayOfMonth()) +
                "/" + String.valueOf(dpfechanacimiento.getMonth() + 1) +
                "/" + String.valueOf(dpfechanacimiento.getYear());


        String telefono = null;
        if (edttelefono.getText().length()>0)
            telefono = edttelefono.getText().toString();
        else telefono = "Campo obligatorio";

        String email = null;
        if (edtemail.getText().length()>0)
            email = edtemail.getText().toString();
        else email = "Campo obligatorio";

        String descripcion = null;
        if (edtdescripcion.getText().length()>0)
            descripcion = edtdescripcion.getText().toString();
        else descripcion = "campo obligatorio";

        Intent intent = new Intent(this,Datos.class);

        intent.putExtra("nombre", nombre);
        intent.putExtra("fecha", fecha);
        intent.putExtra("telefono", telefono);
        intent.putExtra("email", email);
        intent.putExtra("descripcion", descripcion);

        return intent;
    }
}
