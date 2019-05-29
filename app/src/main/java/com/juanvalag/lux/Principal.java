package com.juanvalag.lux;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Principal extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

    }

    public void onClick(View vi){
        int idtemporal= vi.getId();
        Intent cambiador;
        if(idtemporal==R.id.btn_inisesion)
        {
            cambiador = new Intent(this, MainActivity.class);
           startActivity(cambiador);
        }else if(idtemporal==R.id.btn_registrarse)
        {
           cambiador = new Intent(this, Registro.class);
            startActivity(cambiador);
        }
    }
}
