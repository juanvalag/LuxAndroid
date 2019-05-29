package com.juanvalag.lux;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HtmlApi {

    public static String obtenerContenidoHtml(String link)
    {
    URL objeto=null;
    HttpURLConnection conexion =null;
    String respuesta= null;
    String textofinal= null;


    try{
        objeto= new URL(link);
        URLConnection conexionConUrl = objeto.openConnection();
        BufferedReader lectorDeIngreso= new BufferedReader(new InputStreamReader(conexionConUrl.getInputStream()));
        while((respuesta = lectorDeIngreso.readLine())!= null){
            textofinal+=respuesta+" \r\n";
        }
        lectorDeIngreso.close();
    }catch(MalformedURLException e)
    {
        e.printStackTrace();
    }catch (IOException ioe)
    {
        ioe.printStackTrace();
    }

    return textofinal;
    }
}
