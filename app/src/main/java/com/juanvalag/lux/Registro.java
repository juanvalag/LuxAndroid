package com.juanvalag.lux;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity {

    EditText nombre, apellidos, username, password, confirmacontra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        this.nombre= (EditText) findViewById(R.id.txt_nombres);
        this.apellidos=(EditText) findViewById(R.id.txt_apellidos);
        this.username=(EditText) findViewById(R.id.txt_usuario);
        this.password=(EditText) findViewById(R.id.txt_contrasena);
        this.confirmacontra=(EditText) findViewById(R.id.txt_confircontra);
    }

    public void onClick(View vi){
        if(vi.getId() == R.id.btn_iniciarsesion){
            String nombres, apelli, usuario, contraseña, conficontra;
            nombres= this.nombre.getText().toString();
            apelli= this.apellidos.getText().toString();
            usuario = this.username.getText().toString();
            contraseña = this.password.getText().toString();
            conficontra= this.confirmacontra.getText().toString();
            boolean valido=true;

            if(nombres.equals(""))
            {
                Toast.makeText(this, "Por favor ingrese los nombres", Toast.LENGTH_SHORT).show();
                valido = false;
            }else if(apelli.equals(""))
            {
                Toast.makeText(this, "Por favor ingrese los apellidos", Toast.LENGTH_SHORT).show();
                valido = false;
            }else if(usuario.equals(""))
            {
                Toast.makeText(this, "Por favor ingrese el nombre de usuario", Toast.LENGTH_SHORT).show();
                valido = false;
            }else if(contraseña.equals(""))
            {
                Toast.makeText(this, "Por favor ingrese la contraseña", Toast.LENGTH_SHORT).show();
                valido = false;
            }else if(conficontra.equals("")){
                Toast.makeText(this, "Por favor confirme la contraseña", Toast.LENGTH_SHORT).show();
                valido=false;
            }else if(!contraseña.equals(conficontra)){
                Toast.makeText(this, "Las contrseñas no coinciden, por favor verifique", Toast.LENGTH_SHORT).show();
                valido=false;
            }

            if(valido)
            {
                AdminSQLiteOpenHelper admin =new AdminSQLiteOpenHelper(this, "lux_basededatos", null, 1);
                SQLiteDatabase base = admin.getWritableDatabase();
                ContentValues contenedor = new ContentValues();
                contenedor.put("numero", 1);
                contenedor.put("nombre", nombres);
                contenedor.put("apellidos", apelli);
                contenedor.put("username", usuario);
                contenedor.put("contrasena", contraseña);
                contenedor.put("tipo", "empresa");

                base.insert("usuarios", null, contenedor);
                base.close();
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                this.reinicio();
            }
        }
    }

    private void reinicio(){
        Intent pasador = new Intent(this, Principal.class);
        startActivity(pasador);
    }
}
