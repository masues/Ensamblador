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
        String linea = " JR Z, eti1";
        Linea lineaInstance = new Linea(linea);
        System.out.println("ini"+lineaInstance.operacion+"fin");
        System.out.println(lineaInstance.operando1);
        System.out.println(lineaInstance.operando2);
        System.out.println("iC:"+lineaInstance.getCodigoObjeto(null, Integer.toString(35),17)+":fC");
        System.out.println("esto es una prueba".toUpperCase());
        //Pasa cualquier número (base 2,10 o 16) a binario+*/
        /*String cadena="-1";
        Integer num;
        if (cadena.endsWith("h")||cadena.endsWith("H")){//es hexadecimal
            cadena = cadena.substring(0, cadena.length()-1);
            num = Integer.parseInt(cadena,16);
        }
        else if(cadena.endsWith("b")||cadena.endsWith("B")){
            cadena = cadena.substring(0, cadena.length()-1);
            num = Integer.parseInt(cadena,2);
        }
        else
            num = Integer.parseInt(cadena);
        String cadenaBin = Integer.toBinaryString(num);
        System.out.println("En binario:"+cadenaBin);
        Integer cadenaDec = Integer.parseInt(cadenaBin, 2);
        System.out.println("En decimal:"+cadenaDec);
        String cadenaHex = Integer.toHexString(cadenaDec);
        System.out.println("En hexa:"+cadenaHex);
        if(cadenaHex.length()<2){
            while(cadenaHex.length()<2)
                cadenaHex = "0"+cadenaHex;
        }
        else if(2<cadenaHex.length()&&cadenaHex.length()<4){
            while(cadenaHex.length()<4)
                cadenaHex = "0"+cadenaHex;
        }
        else if(4<cadenaHex.length()&&cadenaHex.length()<6){
            while(cadenaHex.length()<6)
                cadenaHex = "0"+cadenaHex;
        }
        System.out.println("En hexa2:"+cadenaHex);
        //System.out.println(Integer.toHexString(Integer.parseInt(cadenaBin,2)));*/
    
    }
}
