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
import android.widget.Toast;

import com.chambeape.entidades.Usuario;
import com.chambeape.ui.Perfil.PerfilFragment;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Registrar extends AppCompatActivity {
    EditText nombre, appParterno, appMaterno, celular, fechaNac, tipoDocID, edtCotraseña, correo, direccion;
    Button  btnregistrar, btnregGoogle;
    ImageButton btnFecNac;
    Spinner tipoID, genero;
    TextView txtIngresar;

    private int dia, mes, año;

    private DatabaseReference miDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        nombre = findViewById(R.id.edtCCNombres);
        appParterno = findViewById(R.id.edtCCAppPaterno);
        appMaterno = findViewById(R.id.edtCCAppMaterno);
        fechaNac = findViewById(R.id.edtCCFechaNc);
        celular = findViewById(R.id.edtCCcelular);
        edtCotraseña = findViewById(R.id.edtCostraseña);
        tipoDocID = findViewById(R.id.edtCCTipoID);
        btnFecNac = findViewById(R.id.btnCCCalendario);
        btnregistrar = findViewById(R.id.btnCCRegistrar);
        btnregGoogle = findViewById(R.id.btnCCRegGoogle);
        correo = findViewById(R.id.edtCCCorreo);
        direccion = findViewById(R.id.edtCCDireccion);
        genero = findViewById(R.id.spCCGenero);
        tipoID = findViewById(R.id.spCCTipoID);
        txtIngresar = findViewById(R.id.txtCCIngresar);

        FirebaseApp.initializeApp(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        miDatabase = database.getReference();
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

    public void registrarUsuario(View view) {
        registrar();
        Intent r = new Intent(this, MenuDesplegable.class);
        startActivity(r);

    }

    private void registrar() {
        String dni = tipoDocID.getText().toString();
        String nom = nombre.getText().toString();
        String apep = appParterno.getText().toString();
        String apem = appMaterno.getText().toString();
        String mail = correo.getText().toString();
        String dire = direccion.getText().toString();
        String fecha = fechaNac.getText().toString();
        String pass = edtCotraseña.getText().toString();
        String tel = celular.getText().toString();
        Usuario usuario = new Usuario(dni,nom,pass,tel,apep,apem,mail,dire,fecha,"","","","0","0","","");
        miDatabase.child("Usuarios").child(dni).setValue(usuario);
        Toast.makeText(this,"Registro exitoso", Toast.LENGTH_SHORT).show();
    }

    public void registrarGoogle(View view) {
    }
}