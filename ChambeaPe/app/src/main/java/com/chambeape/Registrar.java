package com.chambeape;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class Registrar extends AppCompatActivity {
    EditText nombre, apellidos, celular, fechaNac, tipoDocID, edtCotraseña;
    Button  btnregistrar, btnregGoogle;
    ImageButton btnFecNac;
    RadioButton rbFemenino, rbMasculino, rbOtros;
    Spinner tipoID;
    TextView txtIngresar;

    private int dia, mes, año;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        nombre = findViewById(R.id.edtCCNombres);
        apellidos = findViewById(R.id.edtCCApellidos);
        fechaNac = findViewById(R.id.edtCCFechaNc);
        celular = findViewById(R.id.edtCCcelular);
        edtCotraseña = findViewById(R.id.edtCostraseña);
        tipoDocID = findViewById(R.id.edtCCTipoID);
        btnFecNac = findViewById(R.id.btnCCCalendario);
        btnregistrar = findViewById(R.id.btnCCRegistrar);
        btnregGoogle = findViewById(R.id.btnCCRegGoogle);
        rbFemenino = findViewById(R.id.rbCCFemenino);
        rbMasculino = findViewById(R.id.rbCCMasculino);
        rbOtros = findViewById(R.id.rbCCOtro);
        tipoID = findViewById(R.id.spCCTipoID);
        txtIngresar = findViewById(R.id.txtCCIngresar);

        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.tipo_ID, android.R.layout.simple_spinner_item);
        tipoID.setAdapter(adapter);
    }

    public void llamar_Calendario(View view) {
        final Calendar c = Calendar.getInstance();
        dia = c.get(Calendar.DAY_OF_MONTH);
        mes = c.get(Calendar.MONTH);
        año = c.get(Calendar.YEAR);

        DatePickerDialog d = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfYear) {
                fechaNac.setText(year + "/"+(monthOfYear+1)+"/"+dayOfYear);
            }
        }
                , dia, mes, año);
        d.show();
    }

    public void registrar_usuario(View view) {
        Intent r = new Intent(this,MainActivity.class);
        startActivity(r);

    }

    public void registrar_google(View view) {
    }
}