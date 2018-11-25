/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ensamblador;

/**
 *
 * @author masue
 */
public class PruebaNumeros {
    public static void main(String args[]){
        String linea = " LD A,(1000H)";
        Linea lineaInstance = new Linea(linea);
        System.out.println(lineaInstance.etiqueta);
        System.out.println(lineaInstance.operando1);
        System.out.println(lineaInstance.operando2);
        System.out.println("iC:"+lineaInstance.getCodigoObjeto(null, null)+":fC");
        
    }
}
