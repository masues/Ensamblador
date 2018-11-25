/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ensamblador;

import java.util.ArrayList;

/**
 *
 * @author masue
 */
public class TablaDeSimbolos {//TablaDeSimbolos implementa el patrón de diseño Singleton
    public ArrayList<String> nombre = new ArrayList<String>();
    public ArrayList<Integer> valor = new ArrayList<Integer>();
    public ArrayList<Boolean> definicion = new ArrayList<Boolean>();
    private static TablaDeSimbolos instanceTablaSimbolos = null;
    private TablaDeSimbolos(){}
    public static TablaDeSimbolos getInstance(){
        if(TablaDeSimbolos.instanceTablaSimbolos==null){
            TablaDeSimbolos.instanceTablaSimbolos = new TablaDeSimbolos();
        }
        return TablaDeSimbolos.instanceTablaSimbolos;
    }
}
