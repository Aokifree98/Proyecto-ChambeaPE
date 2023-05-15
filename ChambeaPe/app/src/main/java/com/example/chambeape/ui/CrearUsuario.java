package com.example.chambeape.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chambeape.R;
import com.example.chambeape.entidades.Usuario;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class CrearUsuario extends AppCompatActivity {
    EditText edtCUNombres,edtCUApellidoPat,edtCUApellidoMat,edtCUTipoID,edtCUMail, edtCUDireccion, edtCUClave, edtCUCelular;
    ImageButton btnCUCalendario;
    Spinner spCUGenero,spCUTipoID;
    Button btnCURegistrar, btnCURegGoogle;
    TextView txtCUIngresar,txtCUFechaNacimiento;

    DatePickerDialog datePickerDialog;

    private DatabaseReference miDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);

        edtCUNombres= findViewById(R.id.edtCUNombres);
        edtCUApellidoPat= findViewById(R.id.edtCUApellidoPat);
        edtCUApellidoMat= findViewById(R.id.edtCUApellidoMat);
        edtCUMail=findViewById(R.id.edtCUMail);
        edtCUDireccion= findViewById(R.id.edtCUDireccion);
        txtCUFechaNacimiento= findViewById(R.id.txtCUFechaNacimiento);
        edtCUClave=findViewById(R.id.edtCUClave);
        edtCUCelular= findViewById(R.id.edtCUCelular);
        edtCUTipoID= findViewById(R.id.edtCUTipoID);
        btnCUCalendario = findViewById(R.id.btnCUCalendario);
        spCUGenero = findViewById(R.id.spCUGenero);
        spCUTipoID = findViewById(R.id.spCUTipoID);
        btnCURegistrar = findViewById(R.id.btnCURegistrar);
        btnCURegGoogle = findViewById(R.id.btnCURegGoogle);
        txtCUIngresar = findViewById(R.id.txtCUIngresar);

        FirebaseApp.initializeApp(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        miDatabase=database.getReference();

        txtCUFechaNacimiento.setText(getTodaysDate());
        initDatePicker();

        btnCURegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                    Intent i = new Intent(CrearUsuario.this, PerfilUsuario.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                        }
                }, 3000);
            }
        });
    }
    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month +1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day,month,year);
    }
    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month +1;
                String date = makeDateString(day,month,year);
                txtCUFechaNacimiento.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year,month,day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year) {
        return month+"/"+day+"/"+year;
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }
    private void registrarUsuario() {
        //String id = miDatabase.push().getKey();
        String dni = edtCUTipoID.getText().toString();
        String nom = edtCUNombres.getText().toString();
        String apep = edtCUApellidoPat.getText().toString();
        String apem = edtCUApellidoMat.getText().toString();
        String mail = edtCUMail.getText().toString();
        String dire = edtCUDireccion.getText().toString();
        String fecha = txtCUFechaNacimiento.getText().toString();
        String pass = edtCUClave.getText().toString();
        String tel = edtCUCelular.getText().toString();
        Usuario usuario = new Usuario(dni,nom,pass,tel,apep,apem,mail,dire,fecha,"","","","0","0","","");
        miDatabase.child("Usuarios").child(dni).setValue(usuario);
        Toast.makeText(this,"Registro exitoso", Toast.LENGTH_SHORT).show();

    }

}