/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ensamblador;
import java.io.*;
/**
 *
 * @author masues
 */
public class GestionArchivo {
    FileInputStream entrada;
    FileOutputStream salida;
    File archivo;
    public String abrirArchivo(File archivo){//Funciona para abrir el archivo
        String contenido = "";                
        try{
            this.entrada= new FileInputStream(archivo);
            int ascii;
            char caracter;
            while((ascii=entrada.read())!=-1){
                caracter = (char)ascii;
                contenido += caracter;
            }
        }catch(Exception e){
            System.out.println("Ocurrio una excepción en abrirArchivo");
        }
        return contenido;
    }
    public Boolean guardarArchivo(File archivo, String contenido){
        Boolean respuesta=false;
        try{
            this.salida= new FileOutputStream(archivo);
            byte[] bytesArc = contenido.getBytes();
            this.salida.write(bytesArc);
            respuesta=true;
        }catch(Exception e){
            System.out.println("Ocurrio una excepción en guardarArchivo");
        }
        return respuesta;
    }
}
