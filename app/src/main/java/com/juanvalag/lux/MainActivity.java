package com.juanvalag.lux;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txt_usuario, txt_password;
    private String nombreusuario, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.txt_usuario= (EditText) findViewById(R.id.txt_usuario);
        this.txt_password= (EditText) findViewById(R.id.txt_contrasena);
    }

    public void onClick(View vi){
        int id_temporal= vi.getId();
        if(id_temporal == R.id.btn_iniciarsesion){
            this.nombreusuario= this.txt_usuario.getText().toString();
            this.pass = this.txt_password.getText().toString();
            boolean valido=true;
            if(this.nombreusuario.isEmpty())
            {
                Toast.makeText(this, "Ingrese un nombre de usuario", Toast.LENGTH_SHORT).show();
                valido = false;
            }else if (this.pass.isEmpty())
            {
                Toast.makeText(this, "Ingrese la contraseña", Toast.LENGTH_SHORT).show();
                valido = false;
            }

            if(valido)
            {
               this.consulta();
            }

        }

    }

    private void consulta()
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "lux_basededatos", null, 1);
        SQLiteDatabase base = admin.getWritableDatabase();
     /*   Cursor fila = base.query("usuarios", *,
                "username like '%" + this.nombreusuario+"%'", null, null, null, null);*/
       Cursor fila = base.rawQuery("select contrasena, tipo from usuarios where username ='"+this.nombreusuario.trim()+"'", null);
        if(fila.moveToFirst())
        {

            if(fila.getString(0).equals(this.pass)){
               String tipo= fila.getString(1);
                base.close();
                this.envio(tipo);
            }else
                {
                Toast.makeText(this, "La contraseña no coincide con el usuario ingresado", Toast.LENGTH_SHORT).show();
            }
        }else
            {
            Toast.makeText(this, "No hay usuarios registrados con este nombre de usuario", Toast.LENGTH_LONG).show();
        }
        base.close();
    }

    private void envio(String tipo){
        Intent pasador;
        if(tipo.equals("empresa")){
           // pasador=new Intent(this, EmpresaInicio.class);
           Toast.makeText(this, "ingreso exitoso enviado a: Empresa", Toast.LENGTH_SHORT).show();
        }else{
           // pasador = new Intent(this, PersonaInicio.class);
            Toast.makeText(this, "ingreso exitoso enviado a: Persona", Toast.LENGTH_SHORT).show();
        }
       // pasador.putExtra("nombreUsuario", this.nombreusuario);

    }
}
