import java.util.regex.*;
public class FormatoLineaASM{
    public static void main(String args[]){
        String regex = "\\h*(\\w*:)?\\h+(\\w*)(\\h+((\\w*|\\(\\w*\\))(,(\\w*|\\(\\w*\\)))?)?)?\\h*(;.*)?";//expresión regular
        Pattern patron = Pattern.compile(regex);//compila la expresión regular
        Matcher match = patron.matcher(args[0]);//busca que encaje la expresión con una cadena que mando desde los argumentos
        if(match.matches()){
            System.out.println("La cadena SI tiene el formato adecuado");
            String etiqueta = match.group(1);//el grupo 1 debe ser la etiqueta
            String instruccion = match.group(2);//el grupo 2 la instrucción
            String argumentos = match.group(4);//el grupo 4 los argumentos
            System.out.println("etiqueta: "+"\'"+etiqueta+"\'");
            System.out.println("instruccion: "+"\'"+instruccion+"\'");
            System.out.println("argumentos: "+"\'"+argumentos+"\'");
        }
        else
            System.out.println("La cadena NO tiene el formato adecuado");        
	}
}