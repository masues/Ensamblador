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
        String linea = " HALT";
        Linea lineaInstance = new Linea(linea);
        System.out.println("ini"+lineaInstance.operacion+"fin");
        if ("dec".equals(lineaInstance.operacion)||"DEC".equals(lineaInstance.operacion)) {
            System.out.println("Si son iguanas");
        }
        System.out.println(lineaInstance.operando1);
        System.out.println(lineaInstance.operando2);
        System.out.println("iC:"+lineaInstance.getCodigoObjeto(null, null)+":fC");
        //Pasa cualquier número (base 2,10 o 16) a binario
        /*String cadena="-2h";
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
        System.out.println(cadenaBin);
        //System.out.println(Integer.toHexString(Integer.parseInt(cadenaBin,2)));*/
    
    }
}
