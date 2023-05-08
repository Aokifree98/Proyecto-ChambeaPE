package com.example.chambeape.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.chambeape.R;
import com.example.chambeape.entidades.Usuario;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

public class MenuInicio extends AppCompatActivity {
    ImageView imgMIPerfil;
    ImageButton imgBtnNotificaciones;
    Button btnMIBuscarTra, btnMIBuscarOfer,btnMICreATrab,btnMICreAOfer;

    private DatabaseReference mDatabase;
    private FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicio);

        FirebaseApp.initializeApp(this);
        FirebaseDatabase bd = FirebaseDatabase.getInstance();
        mDatabase = bd.getReference();

        imgMIPerfil = findViewById(R.id.imgMIPerfil);
        imgBtnNotificaciones=findViewById(R.id.imgBtnNotificaciones);
        btnMIBuscarTra= findViewById(R.id.btnMIBuscarTra);
        btnMIBuscarOfer= findViewById(R.id.btnMIBuscarOfer);
        btnMICreATrab= findViewById(R.id.btnMICreATrab);
        btnMICreAOfer= findViewById(R.id.btnMICreaAOfer);

        String dni = getIntent().getExtras().getString("dni");
        Toast.makeText(this,dni,Toast.LENGTH_SHORT).show();

    }


}