package ensamblador;

/**
 *
 * @author masues
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.*;
public class Linea {
    public final String regex = "\\h*((\\w*):)?\\h*((\\w*)(\\h*(([a-zA-Z_0-9+\\-]*|\\([a-zA-Z_0-9+\\-]*\\))(,\\h*([a-zA-Z_0-9+\\-]*|\\([a-zA-Z_0-9+\\-]*\\)))?)?)?)\\h*(;.*)?";//expresión regular
    public String etiqueta, operacion, operando1, operando2, fila;
    public Boolean cumple;

    public Linea(String lineaEntera) {//el constructor devuelve true si la linea tiene el formato adecuado
        Pattern patron = Pattern.compile(regex);//compila la expresión regular
        Matcher match = patron.matcher(lineaEntera);//busca que encaje la expresión con una cadena que mando desde los argumentos
        if(match.matches()){
            this.etiqueta = match.group(2);//el grupo 2 es la etiqueta
            this.operacion = match.group(4);//el grupo 4 la instrucción
            this.operando1 = match.group(7);//el grupo 7 es el primer operando
            this.operando2 = match.group(9);//el grupo 9 es el segundo argumento
            this.fila = match.group(3);//el grupo 3 es la fila completa
            this.cumple=true;
        }
        else
            this.cumple=false;
    }
     
    public Integer longitudDeInstruccion(){
        Integer longitud=null;
        if ("ld".equals(this.operacion)||"LD".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&this.operando2.matches("[A-E]|H|L")){//LD r,r'
                longitud=1;
            }
            else if (this.operando1.matches("[A-E]|H|L")&&this.operando2.matches("[0-9a-fA-FhHbB\\+\\-]+|\\w+")){//LD r,n
                longitud=2;
            }
            else if (this.operando1.matches("[A-E]|H|L")&&this.operando2.matches("HL")){//LD r,HL
                longitud=1;
            }
            else if (this.operando1.matches("[A-E]|H|L")&&this.operando2.matches("\\(IX(\\+|\\-)[0-9]+\\)")){//LD r,(IX+d)
                longitud=3;
            }
            else if (this.operando1.matches("[A-E]|H|L")&&this.operando2.matches("\\(IY(\\+|\\-)[0-9]+\\)")){//LD r,(IY+d)
                longitud=3;
            }
            else if (this.operando1.matches("\\(HL\\)")&&this.operando2.matches("[A-E]|H|L")){//LD (HL),r
                longitud=1;
            }
            else if (this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\)")&&this.operando2.matches("[A-E]|H|L")){//LD (IX+d),r
                longitud=3;
            }
            else if (this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&this.operando2.matches("[A-E]|H|L")){//LD (IY+d),r
                longitud=3;
            }
            else if (this.operando1.matches("\\(HL\\)")&&this.operando2.matches("[0-9a-fA-FhHbB\\+\\-]+|\\w+")){//LD (HL),n
                longitud=2;
            }
            else if (this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\)")&&this.operando2.matches("[0-9a-fA-FhHbB\\+\\-]+|\\w+")){//LD (IX+d),n
                longitud=4;
            }
            else if (this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&this.operando2.matches("[0-9a-fA-FhHbB\\+\\-]+|\\w+")){//LD (IY+d),n
                longitud=4;
            }
            else if (this.operando1.matches("A")&&this.operando2.matches("\\(BC\\)")){//LD A,(BC)
                longitud=1;
            }
            else if (this.operando1.matches("A")&&this.operando2.matches("\\(DE\\)")){//LD A,(DE)
                longitud=1;
            }
            else if (this.operando1.matches("A")&&this.operando2.matches("\\(([0-9a-fA-FhHbB\\+\\-]+|\\w+)\\)")){//LD A,(nn)
                longitud=3;
            }
            else if (this.operando1.matches("\\(BC\\)")&&this.operando2.matches("A")){//LD (BC),A
                longitud=1;
            }
            else if (this.operando1.matches("\\(DE\\)")&&this.operando2.matches("A")){//LD (DE),A
                longitud=1;
            }
            else if (this.operando1.matches("\\(([0-9a-fA-FhHbB\\+\\-]+|\\w+)\\)")&&this.operando2.matches("A")){//LD (nn),A
                longitud=3;
            }
            else if (this.operando1.matches("A")&&this.operando2.matches("I")){//LD A,I
                longitud=2;
            }
            else if (this.operando1.matches("A")&&this.operando2.matches("R")){//LD A,R
                longitud=2;
            }
            else if (this.operando1.matches("I")&&this.operando2.matches("A")){//LD I,A
                longitud=2;
            }
            else if (this.operando1.matches("R")&&this.operando2.matches("A")){//LD R,A
                longitud=2;
            }
////////////////////////////////////////////////Primer página
            else if(this.operando1.matches("BC|DE|HL|SP")&&this.operando2.matches("([0-9a-fA-FhHbB\\+\\-]+|\\w+)")){//LD dd,nn              Carga de 16 bits
                longitud=3;
            }
            else if (this.operando1.matches("IX")&&this.operando2.matches("([0-9a-fA-FhHbB\\+\\-]+|\\w+)")){//LD IX,nn    
                longitud=4;
            }
            else if (this.operando1.matches("IY")&&this.operando2.matches("([0-9a-fA-FhHbB\\+\\-]+|\\w+)")){//LD IY,nn
                longitud=4;
            }
            else if (this.operando1.matches("HL")&&this.operando2.matches("\\(([0-9a-fA-FhHbB\\+\\-]+|\\w+)\\)")){//LD HL,(nn)
                longitud=3;
            }
            else if (this.operando1.matches("BC|DE|HL|SP")&&this.operando2.matches("\\(([0-9a-fA-FhHbB\\+\\-]+|\\w+)\\)")){//LD dd,(nn)
                longitud=4;
            }
            else if (this.operando1.matches("IX")&&this.operando2.matches("\\(([0-9a-fA-FhHbB\\+\\-]+|\\w+)\\")){//LD IX,(nn)
                longitud=4;
            }
            else if (this.operando1.matches("IY")&&this.operando2.matches("\\(([0-9a-fA-FhHbB\\+\\-]+|\\w+)\\")){//LD IY,(nn)
                longitud=4;
            }
            else if (this.operando1.matches("\\(([0-9a-fA-FhHbB\\+\\-]+|\\w+)\\)")&&this.operando2.matches("HL")){//LD (nn),HL
                longitud=3;
            }
            else if (this.operando1.matches("\\(([0-9a-fA-FhHbB\\+\\-]+|\\w+)\\)")&&this.operando2.matches("BC|DE|HL|SP")){//LD (nn),dd
                longitud=4;
            }
            else if (this.operando1.matches("\\(([0-9a-fA-FhHbB\\+\\-]+|\\w+)\\)")&&this.operando2.matches("IX")){//LD (nn),IX
                longitud=4;
            }
            else if (this.operando1.matches("\\(([0-9a-fA-FhHbB\\+\\-]+|\\w+)\\)")&&this.operando2.matches("IY")){//LD (nn),IY
                longitud=4;
            }
            else if (this.operando1.matches("SP")&&this.operando2.matches("HL")){//LD SP,HL
                longitud=1;
            }
            else if (this.operando1.matches("SP")&&this.operando2.matches("IX")){//LD SP,IX
                longitud=2;
            }
            else if (this.operando1.matches("SP")&&this.operando2.matches("IY")){//LD SP,IY
                longitud=2;
            }
        }
        else if ("push".equals(this.operacion)||"PUSH".equals(this.operacion)){   ////16bits
            if (this.operando1.matches("BC|DE|HL|AF")&&(this.operando2==null||"".equals(this.operando2))){//PUSH qq
                longitud=1;
            }
            else if (this.operando1.matches("IX")&&(this.operando2==null||"".equals(this.operando2))){//PUSH IX
                longitud=2;
            }
            else if (this.operando1.matches("IY")&&(this.operando2==null||"".equals(this.operando2))){//PUSH IY
                longitud=2;
            }
        }
        else if ("pop".equals(this.operacion)||"POP".equals(this.operacion)){   ////16bits
	    if ((this.operando1.matches("BC|DE|HL|AF")&&(this.operando2==null||"".equals(this.operando2)))){//POP qq
                longitud=1;
            }
            else if (this.operando1.matches("IX")&&(this.operando2==null||"".equals(this.operando2))){//POP IX
                longitud=2;
            }
            else if (this.operando1.matches("IY")&&(this.operando2==null||"".equals(this.operando2))){//POP IY
                longitud=2;
            }
        }
/////////////////////////// Segunda Página
        else if ("ex".equals(this.operacion)||"EX".equals(this.operacion)){
            if(this.operando1.matches("DE")&&this.operando2.matches("HL")){//EX DE,HL
                longitud=1;
            }
            else if (this.operando1.matches("AF")&&this.operando2.matches("AF´")){//EX AF,AF´
                longitud=1;
            }
            else if (this.operando1.matches("\\(SP\\)")&&this.operando2.matches("HL")){//EX (SP),HL
                longitud=1;
            }
            else if (this.operando1.matches("\\(SP\\)")&&this.operando2.matches("IX")){//EX (SP),IX
                longitud=2;
            }
            else if (this.operando1.matches("\\(SP\\)")&&this.operando2.matches("IY")){//EX (SP),IY
                longitud=2;
            }
        }
        else if(("exx".equals(this.operacion)||"EXX".equals(this.operacion))){//EXX
            longitud=1;
        }   
        else if ("ldi".equals(this.operacion)||"LDI".equals(this.operacion)){//LDI
            longitud=2;
        }
        else if ("ldir".equals(this.operacion)||"LDIR".equals(this.operacion)){//LDIR
            longitud=2;
        }
        else if ("ldd".equals(this.operacion)||"LDD".equals(this.operacion)){//LDD
            longitud=2;
        }
        else if ("lddr".equals(this.operacion)||"LDDR".equals(this.operacion)){//LDDR
            longitud=2;
        }
        else if ("cpi".equals(this.operacion)||"CPI".equals(this.operacion)){//CPI
            longitud=2;
        }
        else if ("cpir".equals(this.operacion)||"CPIR".equals(this.operacion)){//CPIR
            longitud=2;
        }
        else if ("cpd".equals(this.operacion)||"CPD".equals(this.operacion)){//CPD
            longitud=2;
        }
        else if ("cpdr".equals(this.operacion)||"CPDR".equals(this.operacion)){//CPDR
            longitud=2;
        }
//////////////////Tercera Hoja
        else if ("add".equals(this.operacion)||"ADD".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&(this.operando2==null||"".equals(this.operando2))){//ADD r
                longitud=1;
            }
            else if(this.operando1.matches("[0-9a-fA-FhHbB\\+\\-]+|\\w+")&&(this.operando2==null||"".equals(this.operando2))){//ADD n   
                longitud=2;
            }
            else if(this.operando1.matches("\\(HL\\)")&&(this.operando2==null||"".equals(this.operando2))){//ADD (HL)
                longitud=1;
            }
            else if(this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//ADD (IX+d)
                longitud=3;
            }
            else if(this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//ADD (IY+d)
                longitud=3;
            }
            else if(this.operando1.matches("HL")&&this.operando2.matches("BC|DE|HL|SP")){//ADD HL,ss     Aritmético de 16 bits
                longitud=1;
            }
            else if(this.operando1.matches("IX")&&this.operando2.matches("BC|DE|IX|SP")){//ADD IX,pp
                longitud=2;
            }
            else if(this.operando1.matches("IY")&&this.operando2.matches("BC|DE|IY|SP")){//ADD IY,rr
                longitud=2;
            }
        }
        else if ("adc".equals(this.operacion)||"ADC".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&(this.operando2==null||"".equals(this.operando2))){//ADC r
                longitud=1;
            }
            else if(this.operando1.matches("[0-9A-Fa-fhHbB\\+\\-]+|\\w+")&&(this.operando2==null||"".equals(this.operando2))){//ADC n
                longitud=2;
            }
            else if(this.operando1.matches("\\(HL\\)")&&(this.operando2==null||"".equals(this.operando2))){//ADC (HL)
                longitud=1;
            }
            else if(this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//ADC (IX+d)
                longitud=3;
            }
            else if(this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//ADC (IY+d)
                longitud=3;
            }
            else if(this.operando1.matches("HL")&&this.operando2.matches("BC|DE|HL|SP")){//ADC HL,ss    Aritmético 16 bits
                longitud=2;
            }
        }
        else if ("sub".equals(this.operacion)||"SUB".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&(this.operando2==null||"".equals(this.operando2))){//SUB r
                longitud=1;
            }
            else if(this.operando1.matches("[0-9A-Fa-fhHbB\\-\\+]+")&&(this.operando2==null||"".equals(this.operando2))){//SUB n
                longitud=2;
            }
            else if(this.operando1.matches("\\(HL\\)")&&(this.operando2==null||"".equals(this.operando2))){//SUB (HL)
                longitud=1;
            }
            else if(this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//SUB (IX+d)
                longitud=3;
            }
            else if(this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//SUB (IY+d)
                longitud=3;
            }
        }        
        else if ("sbc".equals(this.operacion)||"SBC".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&(this.operando2==null||"".equals(this.operando2))){//SBC r
                longitud=1;
            }
            else if(this.operando1.matches("[0-9A-Fa-fhHbB\\-\\+]+")&&(this.operando2==null||"".equals(this.operando2))){//SBC n
                longitud=2;
            }
            else if(this.operando1.matches("\\(HL\\)")&&(this.operando2==null||"".equals(this.operando2))){//SBC (HL)
                longitud=1;
            }
            else if(this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//SBC (IX+d)
                longitud=3;
            }
            else if(this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//SBC (IY+d)
                longitud=3;
            }
            else if(this.operando1.matches("HL")&&this.operando2.matches("BC|DE|HL|SP")){//SBC HL,ss
                longitud=2;
            }
        }
        else if ("and".equals(this.operacion)||"AND".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&(this.operando2==null||"".equals(this.operando2))){//AND r
                longitud=1;
            }
            else if(this.operando1.matches("[0-9A-Fa-fhHbB\\-\\+]+|\\w+")&&(this.operando2==null||"".equals(this.operando2))){//AND n  
                longitud=2;
            }
            else if(this.operando1.matches("\\(HL\\)")&&(this.operando2==null||"".equals(this.operando2))){//AND (HL)
                longitud=1;
            }
            else if(this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//AND (IX+d)
                longitud=3;
            }
            else if(this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//AND (IY+d)
                longitud=3;
            }
        }
        else if ("or".equals(this.operacion)||"OR".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&(this.operando2==null||"".equals(this.operando2))){//OR r
                longitud=1;
            }
            else if(this.operando1.matches("[0-9A-Fa-fhHbB\\-\\+]+|\\w+")&&(this.operando2==null||"".equals(this.operando2))){//OR n
                longitud=2;
            }
            else if(this.operando1.matches("\\(HL\\)")&&(this.operando2==null||"".equals(this.operando2))){//OR (HL)
                longitud=1;
            }
            else if(this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//OR (IX+d)
                longitud=3;
            }
            else if(this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//OR (IY+d)
                longitud=3;
            }
        }
        else if ("xor".equals(this.operacion)||"XOR".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&(this.operando2==null||"".equals(this.operando2))){//XOR r
                longitud=1;
            }
            else if(this.operando1.matches("[0-9A-Fa-fhHbB\\-\\+]+|\\w+")&&(this.operando2==null||"".equals(this.operando2))){//XOR n
                longitud=2;
            }
            else if(this.operando1.matches("\\(HL\\)")&&(this.operando2==null||"".equals(this.operando2))){//XOR (HL)
                longitud=1;
            }
            else if(this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//XOR (IX+d)
                longitud=3;
            }
            else if(this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//XOR (IY+d)
                longitud=3;
            }
        }
        else if ("cp".equals(this.operacion)||"CP".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&(this.operando2==null||"".equals(this.operando2))){//CP r
                longitud=1;
            }
            else if(this.operando1.matches("[0-9A-Fa-fhHbB\\-\\+]+|\\w+")&&(this.operando2==null||"".equals(this.operando2))){//CP n
                longitud=2;
            }
            else if(this.operando1.matches("\\(HL\\)")&&(this.operando2==null||"".equals(this.operando2))){//CP (HL)
                longitud=1;
            }
            else if(this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//CP (IX+d)
                longitud=3;
            }
            else if(this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//CP (IY+d)
                longitud=3;
            }
        }
        else if ("inc".equals(this.operacion)||"INC".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&(this.operando2==null||"".equals(this.operando2))){//INC r
                longitud=1;
            }
            else if(this.operando1.matches("\\(HL\\)")&&(this.operando2==null||"".equals(this.operando2))){//INC (HL)
                longitud=1;
            }
            else if(this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//INC (IX+d)
                longitud=3;
            }
            else if(this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//INC (IY+d)
                longitud=3;
            }
            else if(this.operando1.matches("BC|DE|HL|SP")&&(this.operando2==null||"".equals(this.operando2))){//INC ss       Aritmético 16 bits
                longitud=1;
            } 
            else if(this.operando1.matches("IX")&&(this.operando2==null||"".equals(this.operando2))){//INC IX
                longitud=2;
            }
            else if(this.operando1.matches("IY")&&(this.operando2==null||"".equals(this.operando2))){//INC IY
                longitud=2;
            }
        }
        else if ("dec".equals(this.operacion)||"DEC".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&(this.operando2==null||"".equals(this.operando2))){//DEC r
                longitud=1;
            }
            else if(this.operando1.matches("\\(HL\\)")&&(this.operando2==null||"".equals(this.operando2))){//DEC (HL)
                longitud=1;
            }
            else if(this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//DEC (IX+d)
                longitud=3;
            }
            else if(this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//DEC (IY+d)
                longitud=3;
            }
            else if(this.operando1.matches("BC|DE|HL|SP")&&(this.operando2==null||"".equals(this.operando2))){//DEC ss      Aritmético 16 bits
                longitud=1;
            }
            else if(this.operando1.matches("IX")&&(this.operando2==null||"".equals(this.operando2))){//DEC IX
                longitud=2;
            }
            else if(this.operando1.matches("IY")&&(this.operando2==null||"".equals(this.operando2))){//DEC IY
                longitud=2;
            }
        }
/////////////// CUARTA Y SEXTA HOJA

        else if ("daa".equals(this.operacion)||"DAA".equals(this.operacion)){//DAA
            longitud=1;
        }
        else if ("cpl".equals(this.operacion)||"CPL".equals(this.operacion)){//CPL
            longitud=1;
        }
        else if ("neg".equals(this.operacion)||"NEG".equals(this.operacion)){//NEG
            longitud=2;
        }
        else if ("ccf".equals(this.operacion)||"CCF".equals(this.operacion)){//CCF
            longitud=1;
        }
        else if ("scf".equals(this.operacion)||"SCF".equals(this.operacion)){//SCF
            longitud=1;
        }
        else if ("nop".equals(this.operacion)||"NOP".equals(this.operacion)){//NOP
            longitud=1;
        }
        else if ("halt".equals(this.operacion)||"HALT".equals(this.operacion)){//HALT
            longitud=1;
        }
        else if ("di".equals(this.operacion)||"DI".equals(this.operacion)){//DI
            longitud=1;
        }
        else if ("ei".equals(this.operacion)||"EI".equals(this.operacion)){//EI
            longitud=1;
        }
        else if ("im".equals(this.operacion)||"IM".equals(this.operacion)){//IM
            if(this.operando1.matches("0")&&(this.operando2==null||"".equals(this.operando2))){//IM 0
                longitud=2;
            }
            else if(this.operando1.matches("1")&&(this.operando2==null||"".equals(this.operando2))){//IM 1
                longitud=2;
            }
            else if(this.operando1.matches("2")&&(this.operando2==null||"".equals(this.operando2))){//IM 2
                longitud=2;
            }
        }
/////////////////// QUINTA HOJA

        else if ("rlca".equals(this.operacion)||"RLCA".equals(this.operacion)){//RLCA
            longitud=1;
        }
        else if ("rla".equals(this.operacion)||"RLA".equals(this.operacion)){//RLA
            longitud=1;
        }
        else if ("rrca".equals(this.operacion)||"RRCA".equals(this.operacion)){//RRCA
            longitud=1;
        }
        else if ("rra".equals(this.operacion)||"RRA".equals(this.operacion)){//RRA
            longitud=1;
        }
        else if ("rlc".equals(this.operacion)||"RLC".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&(this.operando2==null||"".equals(this.operando2))){//RLC r
                longitud=2;
            }
            if(this.operando1.matches("\\(HL\\)")&&(this.operando2==null||"".equals(this.operando2))){//RLC (HL)
                longitud=2;
            }
            if(this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//RLC (IX+d)
                longitud=4;
            }
            if(this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//RLC (IY+d)
                longitud=4;
            }
        }
        else if ("rl".equals(this.operacion)||"RL".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&(this.operando2==null||"".equals(this.operando2))){//RL r
                longitud=2;
            }
            else if(this.operando1.matches("\\(HL\\)")&&(this.operando2==null||"".equals(this.operando2))){//RL (HL)
                longitud=2;
            }
            else if(this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//RL (IX+d)
                longitud=4;
            }
            else if(this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//RL (IY+d)
                longitud=4;
            }
        }
        else if ("rrc".equals(this.operacion)||"RRC".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&(this.operando2==null||"".equals(this.operando2))){//RRC r
                longitud=2;
            }
            else if(this.operando1.matches("\\(HL\\)")&&(this.operando2==null||"".equals(this.operando2))){//RRC (HL)
                longitud=2;
            }
            else if(this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//RRC (IX+d)
                longitud=4;
            }
            else if(this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//RRC (IY+d)
                longitud=4;
            }
        }
        else if ("rr".equals(this.operacion)||"RR".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&(this.operando2==null||"".equals(this.operando2))){//RR r
                longitud=2;
            }
            else if(this.operando1.matches("\\(HL\\)")&&(this.operando2==null||"".equals(this.operando2))){//RR (HL)
                longitud=2;
            }
            else if(this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//RR (IX+d)
                longitud=4;
            }
            else if(this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//RR (IY+d)
                longitud=4;
            }
        }
        else if ("sla".equals(this.operacion)||"SLA".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&(this.operando2==null||"".equals(this.operando2))){//SLA r
                longitud=2;
            }
            else if(this.operando1.matches("\\(HL\\)")&&(this.operando2==null||"".equals(this.operando2))){//SLA (HL)
                longitud=2;
            }
            else if(this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//SLA (IX+d)
                longitud=4;
            }
            else if(this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//SLA (IY+d)
                longitud=4;
            }
        }
        else if ("sra".equals(this.operacion)||"SRA".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&(this.operando2==null||"".equals(this.operando2))){//SRA r
                longitud=2;
            }
            else if(this.operando1.matches("\\(HL\\)")&&(this.operando2==null||"".equals(this.operando2))){//SRA (HL)
                longitud=2;
            }
            else if(this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//SRA (IX+d)
                longitud=4;
            }
            else if(this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//SRA (IY+d)
                longitud=4;
            }
        }
        else if ("srl".equals(this.operacion)||"SRL".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&(this.operando2==null||"".equals(this.operando2))){//SRL r
                longitud=2;
            }
            else if(this.operando1.matches("\\(HL\\)")&&(this.operando2==null||"".equals(this.operando2))){//SRL (HL)
                longitud=2;
            }
            else if(this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//SRL (IX+d)
                longitud=4;
            }
            else if(this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//SRL (IY+d)
                longitud=4;
            }
        }
        else if ("rld".equals(this.operacion)||"RLD".equals(this.operacion)){//RLD
            longitud=2;
        }
        else if ("rrd".equals(this.operacion)||"RRD".equals(this.operacion)){//RRD
            longitud=2;
        }
////////////// SEPTIMA HOJA
        else if("bit".equals(this.operacion)||"BIT".equals(this.operacion)){
            if(this.operando1.matches("[0-7]")&&this.operando2.matches("[A-E]|H|L")){//BIT b,r
                longitud=2;
            }
            else if(this.operando1.matches("[0-7]")&&this.operando2.matches("\\(HL\\)")){//BIT b,(HL)
                longitud=2;
            }
            else if(this.operando1.matches("[0-7]")&&this.operando2.matches("\\(IX(\\+|\\-)[0-9]+\\)")){//BIT b,(IX+d)
                longitud=4;
            }
            else if(this.operando1.matches("[0-7]")&&this.operando2.matches("\\(IY(\\+|\\-)[0-9]+\\)")){//BIT b,(IY+d)
                longitud=4;
            }
        }
        else if("set".equals(this.operacion)||"SET".equals(this.operacion)){
            if(this.operando1.matches("[0-7]")&&this.operando2.matches("[A-E]|H|L")){//SET b,r
                longitud=2;
            }
            else if(this.operando1.matches("[0-7]")&&this.operando2.matches("\\(HL\\)")){//SET b,(HL)
                longitud=2;
            }
            else if(this.operando1.matches("[0-7]")&&this.operando2.matches("\\(IX(\\+|\\-)[0-9]+\\)")){//SET b,(IX+d)
                longitud=4;
            }
            else if(this.operando1.matches("[0-7]")&&this.operando2.matches("\\(IY(\\+|\\-)[0-9]+\\)")){//SET b,(IY+d)
                longitud=4;
            }
        }
        else if("res".equals(this.operacion)||"RES".equals(this.operacion)){
            if(this.operando1.matches("[0-7]")&&this.operando2.matches("[A-E]|H|L")){//RES b,r
                longitud=2;
            }
            else if(this.operando1.matches("[0-7]")&&this.operando2.matches("\\(HL\\)")){//RES b,(HL)
                longitud=2;
            }
            else if(this.operando1.matches("[0-7]")&&this.operando2.matches("\\(IX(\\+|\\-)[0-9]+\\)")){//RES b,(IX+d)
                longitud=4;
            }
            else if(this.operando1.matches("[0-7]")&&this.operando2.matches("\\(IY(\\+|\\-)[0-9]+\\)")){//RES b,(IY+d)
                longitud=4;
            }
        }
///////////// OCTAVA HOJA
        else if("jp".equals(this.operacion)||"JP".equals(this.operacion)){
            if (this.operando1.matches("([0-9a-fA-FhHbB\\-\\+]+)|\\w+")&&(this.operando2==null||"".equals(this.operando2))){//JP nn
                longitud=3;
            }
            else if (this.operando1.matches("NZ|Z|NC|C|PO|PE|P|M")&&this.operando2.matches("([0-9a-fA-FhHbB\\-\\+]+)|\\w+")){//JP cc,nn,
                longitud=3;
            }
            else if (this.operando1.matches("\\(HL\\)")&&(this.operando2==null||"".equals(this.operando2))){//JP (HL)
                longitud=1;
            }
            else if (this.operando1.matches("\\(IX\\)")&&(this.operando2==null||"".equals(this.operando2))){//JP (IX)
                longitud=2;
            }
            else if (this.operando1.matches("\\(IY\\)")&&(this.operando2==null||"".equals(this.operando2))){//JP (IY)
                longitud=2;
            }
        }
        else if("jr".equals(this.operacion)||"JR".equals(this.operacion)){
            if (this.operando1.matches("[\\-0-9hHbB]+|\\w+")&&(this.operando2==null||"".equals(this.operando2))){//JR e
                longitud=2;
            }
            else if (this.operando1.matches("C")&&this.operando2.matches("[\\-0-9hHbB]+|\\w+")){//JR C,e
                longitud=2;
            }
            else if (this.operando1.matches("NC")&&this.operando2.matches("[\\-0-9hHbB]+|\\w+")){//JR NC,e
                longitud=2;
            }
            else if (this.operando1.matches("Z")&&this.operando2.matches("[\\-0-9hHbB]+|\\w+")){//JR Z,e
                longitud=2;
            }
            else if (this.operando1.matches("NZ")&&this.operando2.matches("[\\-0-9hHbB]+|\\w+")){//JR NZ,e
                longitud=2;
            }
        }
        else if("djn".equals(this.operacion)||"DJN".equals(this.operacion)){
            if (this.operando1.matches("Z")&&this.operando2.matches("[\\-0-9hHbB]+|\\w+")){//DJN Z,e
                longitud=2;
            }
        }
//////////////// NOVENA HOJA

        else if("call".equals(this.operacion)||"CALL".equals(this.operacion)){
            if (this.operando1.matches("[0-9a-fA-FhHbB\\-\\+]+|\\w+")&&(this.operando2==null||"".equals(this.operando2))){//CALL nn
                longitud=3;
            }
            else if (this.operando1.matches("NZ|Z|NC|C|PO|PE|P|M")&&this.operando2.matches("[0-9a-fA-FhHbB\\-\\+]+|\\w+")){//CALL cc,nn
                longitud=3;
            }
        }
        else if("ret".equals(this.operacion)||"RET".equals(this.operacion)){
            if ((this.operando2==null||"".equals(this.operando2))){//RET
                longitud=1;
            }
            else if (this.operando1.matches("NZ|Z|NC|C|PO|PE|P|M")&&(this.operando2==null||"".equals(this.operando2))){//RET cc
                longitud=1;
            }
        }
        else if("reti".equals(this.operacion)||"RETI".equals(this.operacion)){//RETI
            longitud=2;
        }
        else if("retn".equals(this.operacion)||"RETN".equals(this.operacion)){//RETN
            longitud=2;
        }
        else if("rst".equals(this.operacion)||"RST".equals(this.operacion)){
            if (this.operando1.matches("[0-9a-fA-FhHbB]+")&&(this.operando2==null||"".equals(this.operando2))){//RST p
                longitud=1;
            }
        }
//////////// DECIMA HOJA

        else if("in".equals(this.operacion)||"IN".equals(this.operacion)){
            if (this.operando1.matches("A")&&this.operando2.matches("\\([0-9a-fA-FhHbB\\-\\+]+\\)")){//IN A,(n)
                longitud=2;
            }
            else if (this.operando1.matches("[A-E]|H|L")&&this.operando2.matches("\\(C\\)")){//IN r,(C)
                longitud=2;
            }
        }
        else if("ini".equals(this.operacion)||"INI".equals(this.operacion)){//INI
            longitud=2;
        }
        else if("inir".equals(this.operacion)||"INIR".equals(this.operacion)){//INIR
            longitud=2;
        }
        else if("ind".equals(this.operacion)||"IND".equals(this.operacion)){//IND
            longitud=2;
        }
        else if("indr".equals(this.operacion)||"INDR".equals(this.operacion)){//INDR
            longitud=2;
        }
        else if("out".equals(this.operacion)||"OUT".equals(this.operacion)){
            if (this.operando1.matches("\\([0-9a-fA-FhHbB\\-\\+]+\\)")&&this.operando2.matches("A")){//OUT (n),A
                longitud=2;
            }
            else if (this.operando1.matches("\\(C\\)")&&this.operando2.matches("[A-E]|H|L")){//OUT (C),r
                longitud=2;
            }
        }
        else if("outi".equals(this.operacion)||"OUTI".equals(this.operacion)){//OUTI
            longitud=2;
        }
        else if("otir".equals(this.operacion)||"OTIR".equals(this.operacion)){//OTIR
            longitud=2;
        }
        else if("outd".equals(this.operacion)||"OUTD".equals(this.operacion)){//OUTD
            longitud=2;
        }
        else if("otdr".equals(this.operacion)||"OTDR".equals(this.operacion)){//OTDR
            longitud=2;
        }        
        return longitud;
    } 
    
    private String toBinary(String cadena){//Pasa cualquier número (base 2,10 o 16) a binario
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
        return cadenaBin;
    }
    
    public String getCodigoObjeto(String valorOp1,String valorOp2,int CL){
        String instruccion="";
        if ("ld".equals(this.operacion)||"LD".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&this.operando2.matches("[A-E]|H|L")){//LD r,r'
                instruccion="01";
                switch(this.operando1.charAt(0)){//r
                    case 'A':
                    case 'a':
                        instruccion=instruccion+"111";
                        break;
                    case 'b':
                    case 'B':
                        instruccion=instruccion+"000";
                        break;
                    case 'C':
                    case 'c':
                        instruccion=instruccion+"001";
                        break;
                    case 'd':
                    case 'D':
                        instruccion=instruccion+"010";
                        break;
                    case 'e':
                    case 'E':
                        instruccion=instruccion+"011";
                        break;
                    case 'h':
                    case 'H':
                        instruccion=instruccion+"100";
                        break;
                    case 'l':
                    case 'L':
                        instruccion=instruccion+"101";
                        break;
                }
                switch(this.operando2.charAt(0)){//r'
                    case 'A':
                    case 'a':
                        instruccion=instruccion+"111";
                        break;
                    case 'b':
                    case 'B':
                        instruccion=instruccion+"000";
                        break;
                    case 'C':
                    case 'c':
                        instruccion=instruccion+"001";
                        break;
                    case 'd':
                    case 'D':
                        instruccion=instruccion+"010";
                        break;
                    case 'e':
                    case 'E':
                        instruccion=instruccion+"011";
                        break;
                    case 'h':
                    case 'H':
                        instruccion=instruccion+"100";
                        break;
                    case 'l':
                    case 'L':
                        instruccion=instruccion+"101";
                        break;
                }
            }
	        else if(this.operando1.matches("[A-E]|H|L")&&this.operando2.matches("[0-9A-Fa-fhHbB\\-\\+]+|\\w+")){//LD r,n
		        instruccion="00";
                switch(this.operando1.charAt(0)){//r
                    case 'A':
                    case 'a':
                        instruccion=instruccion+"111";
                        break;
                    case 'b':
                    case 'B':
                        instruccion=instruccion+"000";
                        break;
                    case 'C':
                    case 'c':
                        instruccion=instruccion+"001";
                        break;
                    case 'd':
                    case 'D':
                        instruccion=instruccion+"010";
                        break;
                    case 'e':
                    case 'E':
                        instruccion=instruccion+"011";
                        break;
                    case 'h':
                    case 'H':
                        instruccion=instruccion+"100";
                        break;
                    case 'l':
                    case 'L':
                        instruccion=instruccion+"101";
                        break;
                }
                instruccion=instruccion+"110";
                String n;
                if(valorOp2!=null){
                    n = this.toBinary(valorOp2);
                    while(n.length()<8){// en caso de que el número sea menor a 8, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-8, n.length());//en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n;
                }
                else if(this.operando2.matches("[0-9a-fA-FhHbB\\-\\+]+|\\w+")){
                    n = this.toBinary(this.operando2);
                    while(n.length()<8){// en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
                        n="0"+n;    
                    }
                    n=n.substring(n.length()-8, n.length());//en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n;
                }
	        }
            else if(this.operando1.matches("[A-E]|H|L")&&this.operando2.matches("\\(HL\\)")){//LD r,(HL)
		        instruccion="01";
                switch(this.operando1.charAt(0)){//r
                    case 'A':
                    case 'a':
                        instruccion=instruccion+"111";
                        break;
                    case 'b':
                    case 'B':
                        instruccion=instruccion+"000";
                        break;
                    case 'C':
                    case 'c':
                        instruccion=instruccion+"001";
                        break;
                    case 'd':
                    case 'D':
                        instruccion=instruccion+"010";
                        break;
                    case 'e':
                    case 'E':
                        instruccion=instruccion+"011";
                        break;
                    case 'h':
                    case 'H':
                        instruccion=instruccion+"100";
                        break;
                    case 'l':
                    case 'L':
                        instruccion=instruccion+"101";
                        break;
                }
                instruccion=instruccion+"110";
	        }
            else if (this.operando1.matches("[A-E]|H|L")&&this.operando2.matches("\\(IX(\\+|\\-)[0-9]+\\)")){//LD r,(IX+d)
                instruccion="1101110101";
                switch(this.operando1.charAt(0)){//r
                    case 'A':
                    case 'a':
                        instruccion=instruccion+"111";
                        break;
                    case 'b':
                    case 'B':
                        instruccion=instruccion+"000";
                        break;
                    case 'C':
                    case 'c':
                        instruccion=instruccion+"001";
                        break;
                    case 'd':
                    case 'D':
                        instruccion=instruccion+"010";
                        break;
                    case 'e':
                    case 'E':
                        instruccion=instruccion+"011";
                        break;
                    case 'h':
                    case 'H':
                        instruccion=instruccion+"100";
                        break;
                    case 'l':
                    case 'L':
                        instruccion=instruccion+"101";
                        break;
                }
                instruccion=instruccion+"110";
                String d = this.operando2.replaceAll("\\(IX", "");//quita la parte (IX
                d = d.replaceAll("\\)", "");//quita la parte ). Al final queda +-d
                d = this.toBinary(d);
                while(d.length()<8){// en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
                    d="0"+d;    
                }
                d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita números a la izquierda
                instruccion=instruccion+d;
            }
            else if (this.operando1.matches("[A-E]|H|L")&&this.operando2.matches("\\(IY(\\+|\\-)[0-9]+\\)")){//LD r,(IY+d)
                instruccion="1111110101";
                switch(this.operando1.charAt(0)){//r
                    case 'A':
                    case 'a':
                        instruccion=instruccion+"111";
                        break;
                    case 'b':
                    case 'B':
                        instruccion=instruccion+"000";
                        break;
                    case 'C':
                    case 'c':
                        instruccion=instruccion+"001";
                        break;
                    case 'd':
                    case 'D':
                        instruccion=instruccion+"010";
                        break;
                    case 'e':
                    case 'E':
                        instruccion=instruccion+"011";
                        break;
                    case 'h':
                    case 'H':
                        instruccion=instruccion+"100";
                        break;
                    case 'l':
                    case 'L':
                        instruccion=instruccion+"101";
                        break;
                }
                instruccion=instruccion+"110";
                String d = this.operando2.replaceAll("\\(IY", "");//quita la parte (IY
                d = d.replaceAll("\\)", "");//quita la parte ). Al final queda +-d
                d = this.toBinary(d);
                while(d.length()<8){// en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
                    d="0"+d;    
                }
                d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita números a la izquierda
                instruccion=instruccion+d;
            }
            else if (this.operando1.matches("\\(HL\\)")&&this.operando2.matches("[A-E]|H|L")){//LD (HL),r
                instruccion="01110";
                switch(this.operando2.charAt(0)){//r
                    case 'A':
                    case 'a':
                        instruccion=instruccion+"111";
                        break;
                    case 'b':
                    case 'B':
                        instruccion=instruccion+"000";
                        break;
                    case 'C':
                    case 'c':
                        instruccion=instruccion+"001";
                        break;
                    case 'd':
                    case 'D':
                        instruccion=instruccion+"010";
                        break;
                    case 'e':
                    case 'E':
                        instruccion=instruccion+"011";
                        break;
                    case 'h':
                    case 'H':
                        instruccion=instruccion+"100";
                        break;
                    case 'l':
                    case 'L':
                        instruccion=instruccion+"101";
                        break;
                }
            }
            else if (this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\)")&&this.operando2.matches("[A-E]|H|L")){//LD (IX+d),r
                instruccion="11011101"+"01110";
                switch(this.operando2.charAt(0)){//r
                    case 'A':
                    case 'a':
                        instruccion=instruccion+"111";
                        break;
                    case 'b':
                    case 'B':
                        instruccion=instruccion+"000";
                        break;
                    case 'C':
                    case 'c':
                        instruccion=instruccion+"001";
                        break;
                    case 'd':
                    case 'D':
                        instruccion=instruccion+"010";
                        break;
                    case 'e':
                    case 'E':
                        instruccion=instruccion+"011";
                        break;
                    case 'h':
                    case 'H':
                        instruccion=instruccion+"100";
                        break;
                    case 'l':
                    case 'L':
                        instruccion=instruccion+"101";
                        break;
                }
                String d = this.operando1.replaceAll("\\(IX", "");//quita la parte (IX
                d = d.replaceAll("\\)", "");//quita la parte ). Al final queda +-d
                d = this.toBinary(d);
                while(d.length()<8){// en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
                    d="0"+d;    
                }
                d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita números a la izquierda
                instruccion=instruccion+d;
            }
            else if (this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&this.operando2.matches("[A-E]|H|L")){//LD (IY+d),r
                instruccion="11111101"+"01110";
                switch(this.operando2.charAt(0)){//r
                    case 'A':
                    case 'a':
                        instruccion=instruccion+"111";
                        break;
                    case 'b':
                    case 'B':
                        instruccion=instruccion+"000";
                        break;
                    case 'C':
                    case 'c':
                        instruccion=instruccion+"001";
                        break;
                    case 'd':
                    case 'D':
                        instruccion=instruccion+"010";
                        break;
                    case 'e':
                    case 'E':
                        instruccion=instruccion+"011";
                        break;
                    case 'h':
                    case 'H':
                        instruccion=instruccion+"100";
                        break;
                    case 'l':
                    case 'L':
                        instruccion=instruccion+"101";
                        break;
                }
                String d = this.operando1.replaceAll("\\(IY", "");//quita la parte (IY
                d = d.replaceAll("\\)", "");//quita la parte ). Al final queda +-d
                d = this.toBinary(d);
                while(d.length()<8){// en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
                    d="0"+d;    
                }
                d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita números a la izquierda
                instruccion=instruccion+d;
            }
            else if (this.operando1.matches("\\(HL\\)")&&this.operando2.matches("[0-9a-fA-FhHbB\\+\\-]+|\\w+")){//LD (HL),n
                instruccion="00110110";
                String n;
                if(valorOp2!=null){
                    n = this.toBinary(valorOp2);
                    while(n.length()<8){// en caso de que el número sea menor a 8, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-8, n.length());//en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n;
                }
                else if(this.operando2.matches("[0-9a-fA-FhHbB\\-\\+]+|\\w+")){
                    n = this.toBinary(this.operando2);
                    while(n.length()<8){// en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
                        n="0"+n;    
                    }
                    n=n.substring(n.length()-8, n.length());//en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n;
                }
            }
            else if (this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\)")&&this.operando2.matches("[0-9a-fA-FhHbB\\+\\-]+|\\w+")){//LD (IX+d),n
                instruccion="11011101"+"00110110";
                String d = this.operando1.replaceAll("\\(IX", "");//quita la parte (IX
                d = d.replaceAll("\\)", "");//quita la parte ). Al final queda +-d
                d = this.toBinary(d);
                while(d.length()<8){// en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
                    d="0"+d;    
                }
                d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita números a la izquierda
                instruccion=instruccion+d;
                String n;
                if(valorOp2!=null){
                    n = this.toBinary(valorOp2);
                    while(n.length()<8){// en caso de que el número sea menor a 8, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-8, n.length());//en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n;
                }
                else if(this.operando2.matches("[0-9a-fA-FhHbB\\-\\+]+")){
                    n = this.toBinary(this.operando2);
                    while(n.length()<8){// en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
                        n="0"+n;    
                    }
                    n=n.substring(n.length()-8, n.length());//en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n;
                }
            }
            else if (this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&this.operando2.matches("[0-9a-fA-FhHbB\\+\\-]+|\\w+")){//LD (IY+d),n
                instruccion="11111101"+"00110110";
                String d = this.operando1.replaceAll("\\(IY", "");//quita la parte (IY
                d = d.replaceAll("\\)", "");//quita la parte ). Al final queda +-d
                d = this.toBinary(d);
                while(d.length()<8){// en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
                    d="0"+d;    
                }
                d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita números a la izquierda
                instruccion=instruccion+d;
                String n;
                if(valorOp2!=null){
                    n = this.toBinary(valorOp2);
                    while(n.length()<8){// en caso de que el número sea menor a 8, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-8, n.length());//en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n;
                }
                else if(this.operando2.matches("[0-9a-fA-FhHbB\\-\\+]+")){
                    n = this.toBinary(valorOp2);
                    while(n.length()<8){// en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
                        n="0"+n;    
                    }
                    n=n.substring(n.length()-8, n.length());//en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n;
                }
            }
	        else if(this.operando1.matches("A")&&this.operando2.matches("\\(BC\\)")){//LD A,(BC)
                instruccion="00001010";
            }
	        else if(this.operando1.matches("A")&&this.operando2.matches("\\(DE\\)")){//LD A,(DE)
                instruccion="00011010";
            }
            else if(this.operando1.matches("A")&&this.operando2.matches("(\\([0-9a-fA-FhHbB\\-\\+]+\\))|(\\(\\w+\\))")){//LD A,(nn)
                instruccion="00111010";
                String n;
                if(valorOp2!=null){
                    n = valorOp2.replaceAll("\\(", "");
                    n = n.replaceAll("\\)","");
                    n = this.toBinary(n);
                    while(n.length()<16){// en caso de que el número sea menor a 16, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-16, n.length());//en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n.substring(8);
                    instruccion=instruccion+n.substring(0, 8);
                }
                else if(this.operando2.matches("\\([0-9a-fA-FhHbB\\-\\+]+\\)")){
                    n = operando2.replaceAll("\\(", "");
                    n = n.replaceAll("\\)","");
                    n = this.toBinary(n);
                    while(n.length()<16){// en caso de que el número sea menor a 16 bits, pone ceros a la izquierda
                        n="0"+n;    
                    }
                    n=n.substring(n.length()-16, n.length());//en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n.substring(8);
                    instruccion=instruccion+n.substring(0, 8);
                }
            }
	        else if(this.operando1.matches("\\(BC\\)")&&this.operando2.matches("A")){//LD (BC),A
                instruccion="00000010";
            }
	        else if(this.operando1.matches("\\(DE\\)")&&this.operando2.matches("A")){//LD (DE),A
                instruccion="00010010";
            }
            else if(this.operando1.matches("(\\([0-9a-fA-FhHbB\\-\\+]+\\))|(\\(\\w+\\))")&&this.operando2.matches("A")){//LD (nn),A
                instruccion="00110010";
                String n;
                if(valorOp1!=null){
                    n = valorOp1.replaceAll("\\(", "");
                    n = n.replaceAll("\\)","");
                    n = this.toBinary(n);
                    while(n.length()<16){// en caso de que el número sea menor a 16, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-16, n.length());//en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n.substring(8);
                    instruccion=instruccion+n.substring(0, 8);
                }
                else if(this.operando1.matches("\\([0-9a-fA-FhHbB\\-\\+]+\\)")){
                    n = operando1.replaceAll("\\(", "");
                    n = n.replaceAll("\\)","");
                    n = this.toBinary(n);
                    while(n.length()<16){// en caso de que el número sea menor a 16 bits, pone ceros a la izquierda
                        n="0"+n;    
                    }
                    n=n.substring(n.length()-16, n.length());//en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n.substring(8);
                    instruccion=instruccion+n.substring(0, 8);
                }
            }
            else if (this.operando1.matches("A")&&this.operando2.matches("I")){//LD A,I
                instruccion="11101101"+"01010111";
            }
            else if (this.operando1.matches("A")&&this.operando2.matches("R")){//LD A,R
                instruccion="11101101"+"01011111";
            }
            else if (this.operando1.matches("I")&&this.operando2.matches("A")){//LD I,A
                instruccion="11101101"+"01000111";
            }
            else if (this.operando1.matches("R")&&this.operando2.matches("A")){//LD R,A
                instruccion="11101101"+"01001111";
            }
///////////////////////////////////Primer página, carga de 8 bits
            else if (this.operando1.matches("BC|DE|HL|SP")&&this.operando2.matches("([0-9a-fA-FhHbB\\-\\+]+)|\\w+")){//LD dd,nn
                instruccion="00";
                switch(this.operando1){//r
                    case "BC":
                        instruccion=instruccion+"00";
                        break;
                    case "DE":
                        instruccion=instruccion+"01";
                        break;
                    case "HL":
                        instruccion=instruccion+"10";
                        break;
                    case "SP'":
                        instruccion=instruccion+"11";
                        break;
                }
                instruccion=instruccion+"001";
                String n;
                if(valorOp2!=null){                                   //Si tiene etiquetas, se debió haber manadado un parámetro
                    n = this.toBinary(valorOp2);
                    while(n.length()<16){                             // en caso de que el número sea menor a 16, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-16, n.length());         //en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n.substring(8);
                    instruccion=instruccion+n.substring(0, 8);
                }
                else if(this.operando2.matches("[0-9a-fA-FhHbB]")){   //Si nn no es una etiqueta
                    n = this.toBinary(this.operando2);
                    while(n.length()<16){                            // en caso de que el número sea menor a 16 bits, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-16, n.length());       //en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n.substring(8);
                    instruccion=instruccion+n.substring(0, 8);
                }
            }
///////////
            else if(this.operando1.matches("IX")&&this.operando2.matches("([0-9a-fA-FhHbB\\-\\+]+)|\\w+")){//LD IX,nn
                instruccion="11011101"+"00100001";
                String n;
                if(valorOp2!=null){                                   //Si el valor de entrada en entero
                    n = this.toBinary(valorOp2);
                    while(n.length()<16){                             // en caso de que el número sea menor a 16, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-16, n.length());         //en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n.substring(8);
                    instruccion=instruccion+n.substring(0, 8);
                }
                else if(this.operando2.matches("[0-9a-fA-FhHbB\\-\\+]+")){   //Si el valor de entrada en hex o bin o dec
                    n = this.toBinary(this.operando2);
                    while(n.length()<16){                            // en caso de que el número sea menor a 16 bits, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-16, n.length());       //en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n.substring(8);
                    instruccion=instruccion+n.substring(0, 8);
                }
            }
///////////
            else if(this.operando1.matches("IY")&&this.operando2.matches("([0-9a-fA-FhHbB\\-\\+]+)|\\w+")){  //LD IY,nn
                instruccion="11111101"+"00100001";
                String n;
                if(valorOp2!=null){                                   //Si el valor de entrada en entero
                    n = this.toBinary(valorOp2);
                    while(n.length()<16){                             // en caso de que el número sea menor a 16, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-16, n.length());         //en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n.substring(8);
                    instruccion=instruccion+n.substring(0, 8);
                }
                else if(this.operando2.matches("[0-9a-fA-FhHbB]")){   //Si el valor de entrada en hex o bin
                    n = this.toBinary(valorOp2);
                while(n.length()<16){                            // en caso de que el número sea menor a 16 bits, pone ceros a la izquierda
                    n="0"+n;
                }
                n=n.substring(n.length()-16, n.length());       //en caso de que sea mayor, le quita números a la izquierda
                instruccion=instruccion+n.substring(8);
                instruccion=instruccion+n.substring(0, 8);
                }
            }
///////////
            else if (this.operando1.matches("HL")&&this.operando2.matches("(\\([0-9a-fA-FhHbB\\-\\+]+\\))|(\\(\\w+\\))")){//LD HL,(nn)
                instruccion="00101010";
                String n;
                if(valorOp2!=null){                             //Si el valor de entrada en entero
                    n=valorOp2.replaceAll("\\(","");
                    n=valorOp2.replaceAll("\\)","");
                    n = this.toBinary(n);
                    while(n.length()<16){                        // en caso de que el número sea menor a 16, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-16, n.length());   //en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n.substring(8);
                    instruccion=instruccion+n.substring(0, 8);
                }
                else if(this.operando2.matches("\\([0-9a-fA-FhHbB\\-\\+]+\\)")){ //Si el valor de entrada en hex o bin
                    n = operando2.replaceAll("\\(","");
                    n = n.replaceAll("\\)","");
                    n = this.toBinary(n);
                    while(n.length()<16){                       // en caso de que el número sea menor a 16 bits, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-16, n.length());   //en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n.substring(8);
                    instruccion=instruccion+n.substring(0, 8);
                }
            }
///////////
            else if (this.operando1.matches("BC|DE|HL|SP")&&this.operando2.matches("(\\([0-9a-fA-FhHbB\\-\\+]+\\))|(\\(\\w+\\))")){//LD dd,(nn)
                instruccion="1110110101";
                switch(this.operando1){//r
                    case "BC":
                        instruccion=instruccion+"00";
                        break;
                    case "DE":
                        instruccion=instruccion+"01";
                        break;
                    case "HL":
                        instruccion=instruccion+"10";
                        break;
                    case "SP":
                        instruccion=instruccion+"11";
                        break;
                }
                instruccion=instruccion+"1011";
                String n;
                if(valorOp2!=null){                             //Si el valor de entrada en entero
                    n = valorOp2.replaceAll("\\(", "");
                    n = n.replaceAll("\\)","");
                    n = this.toBinary(n);
                    while(n.length()<16){                        // en caso de que el número sea menor a 16, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-16, n.length());   //en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n.substring(8);
                    instruccion=instruccion+n.substring(0, 8);
                }
                else if(this.operando2.matches("\\([0-9a-fA-FhHbB\\-\\+]+\\)")){ //Si el valor de entrada en hex o bin
                    n = operando2.replaceAll("\\(","");
                    n = n.replaceAll("\\)","");
                    n = this.toBinary(n);
                    while(n.length()<16){                       // en caso de que el número sea menor a 16 bits, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-16, n.length());   //en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n.substring(8);
                    instruccion=instruccion+n.substring(0, 8);
                }
            }
///////////
            else if (this.operando1.matches("IX")&&this.operando2.matches("(\\([0-9a-fA-FhHbB\\-\\+]+\\))|(\\(\\w+\\))")){//LD IX,(nn)
                instruccion="11011101"+"00101010";
                String n;
                if(valorOp2!=null){                             //Si el valor de entrada en entero
                    n = valorOp2.replaceAll("\\(", "");
                    n = n.replaceAll("\\)","");
                    n = this.toBinary(n);
                    while(n.length()<16){                        // en caso de que el número sea menor a 16, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-16, n.length());   //en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n.substring(8);
                    instruccion=instruccion+n.substring(0, 8);
                }
                else if(this.operando2.matches("\\([0-9a-fA-FhHbB\\-\\+]+\\)")){ //Si el valor de entrada en hex o bin
                    n = operando2.replaceAll("\\(","");
                    n = n.replaceAll("\\)","");
                    n = this.toBinary(n);
                    while(n.length()<16){                       // en caso de que el número sea menor a 16 bits, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-16, n.length());   //en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n.substring(8);
                    instruccion=instruccion+n.substring(0, 8);
                }
            }
///////////
            else if (this.operando1.matches("IY")&&this.operando2.matches("(\\([0-9a-fA-FhHbB\\-\\+]+\\))|(\\(\\w+\\))")){//LD IY,(nn)
                instruccion="1111110100101010";
                String n;
                if(valorOp2!=null){                             //Si el valor de entrada en entero
                    n = valorOp2.replaceAll("\\(", "");
                    n = n.replaceAll("\\)","");
                    n = this.toBinary(n);
                    while(n.length()<16){                        // en caso de que el número sea menor a 16, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-16, n.length());   //en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n.substring(8);
                    instruccion=instruccion+n.substring(0, 8);
                }
                else if(this.operando2.matches("\\([0-9a-fA-FhHbB\\-\\+]+\\)")){ //Si el valor de entrada en hex o bin
                    n = operando2.replaceAll("\\(","");
                    n = n.replaceAll("\\)","");
                    n = this.toBinary(n);
                    while(n.length()<16){                       // en caso de que el número sea menor a 16 bits, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-16, n.length());   //en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n.substring(8);
                    instruccion=instruccion+n.substring(0, 8);
                }
            }
///////////
            else if (this.operando1.matches("(\\([0-9a-fA-FhHbB\\-\\+]+\\))|(\\(\\w+\\))")&&this.operando2.matches("HL")){//LD (nn),HL
                instruccion="00100010";
                String n;
                if(valorOp1!=null){                             //Si el valor de entrada en entero
                    n = valorOp1.replaceAll("\\(", "");
                    n = n.replaceAll("\\)","");
                    n = this.toBinary(n);
                    while(n.length()<16){                        // en caso de que el número sea menor a 16, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-16, n.length());   //en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n.substring(8);
                    instruccion=instruccion+n.substring(0, 8);
                }
                else if(this.operando1.matches("\\([0-9a-fA-FhHbB\\-\\+]+\\)")){ //Si el valor de entrada en hex o bin
                    n = operando1.replaceAll("\\(","");
                    n = n.replaceAll("\\)","");
                    n = this.toBinary(n);
                    while(n.length()<16){                       // en caso de que el número sea menor a 16 bits, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-16, n.length());   //en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n.substring(8);
                    instruccion=instruccion+n.substring(0, 8);
                }
            }
///////////
            else if (this.operando1.matches("(\\([0-9a-fA-FhHbB\\-\\+]+\\))|(\\(\\w+\\))")&&this.operando2.matches("BC|DE|HL|SP")){//LD (nn),dd
                instruccion="1110110101";
                switch(this.operando2){               //dd
                    case "BC":
                        instruccion=instruccion+"00";
                        break;
                    case "DE":
                        instruccion=instruccion+"01";
                        break;
                    case "HL":
                        instruccion=instruccion+"10";
                        break;
                    case "SP":
                        instruccion=instruccion+"11";
                        break;
                }
                instruccion=instruccion+"0011";
                String n;
                if(valorOp1!=null){                             //Si el valor de entrada en entero
                    n = valorOp1.replaceAll("\\(", "");
                    n = n.replaceAll("\\)","");
                    n = this.toBinary(n);
                    while(n.length()<16){                        // en caso de que el número sea menor a 16, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-16, n.length());   //en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n.substring(8);
                    instruccion=instruccion+n.substring(0, 8);
                }
                else if(this.operando1.matches("\\([0-9a-fA-FhHbB\\-\\+]+\\)")){ //Si el valor de entrada en hex o bin
                    n = operando1.replaceAll("\\(","");
                    n = n.replaceAll("\\)","");
                    n = this.toBinary(n);
                    while(n.length()<16){                       // en caso de que el número sea menor a 16 bits, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-16, n.length());   //en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n.substring(8);
                    instruccion=instruccion+n.substring(0, 8);
                }
            }
///////////
            else if (this.operando1.matches("(\\([0-9a-fA-FhHbB\\-\\+]+\\))|(\\(\\w+\\))")&&this.operando2.matches("IX")){//LD (nn),IX
                instruccion="1101110100100010";
                String n;
                if(valorOp1!=null){                             //Si el valor de entrada en entero
                    n = valorOp1.replaceAll("\\(", "");
                    n = n.replaceAll("\\)","");
                    n = this.toBinary(n);
                    while(n.length()<16){                        // en caso de que el número sea menor a 16, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-16, n.length());   //en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n.substring(8);
                    instruccion=instruccion+n.substring(0, 8);
                }
                else if(this.operando1.matches("\\([0-9a-fA-FhHbB\\-\\+]+\\)")){ //Si el valor de entrada en hex o bin
                    n = operando1.replaceAll("\\(","");
                    n = n.replaceAll("\\)","");
                    n = this.toBinary(n);
                    while(n.length()<16){                       // en caso de que el número sea menor a 16 bits, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-16, n.length());   //en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n.substring(8);
                    instruccion=instruccion+n.substring(0, 8);
                }
            }
///////////
            else if (this.operando1.matches("(\\([0-9a-fA-FhHbB\\-\\+]+\\))|(\\(\\w+\\))")&&this.operando2.matches("IY")){//LD (nn),IY
                instruccion="1111110100100010";
                String n;
                if(valorOp1!=null){                             //Si el valor de entrada en entero
                    n = valorOp1.replaceAll("\\(", "");
                    n = n.replaceAll("\\)","");
                    n = this.toBinary(n);
                    while(n.length()<16){                        // en caso de que el número sea menor a 16, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-16, n.length());   //en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n.substring(8);
                    instruccion=instruccion+n.substring(0, 8);
                }
                else if(this.operando1.matches("\\([0-9a-fA-FhHbB\\-\\+]+\\)")){ //Si el valor de entrada en hex o bin
                    n = operando1.replaceAll("\\(","");
                    n = n.replaceAll("\\)","");
                    n = this.toBinary(n);
                    while(n.length()<16){                       // en caso de que el número sea menor a 16 bits, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-16, n.length());   //en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n.substring(8);
                    instruccion=instruccion+n.substring(0, 8);
                }
            }
///////////
            else if (this.operando1.matches("SP")&&this.operando2.matches("HL")){//LD SP,HL
                instruccion="11111001";
            }
///////////
            else if (this.operando1.matches("SP")&&this.operando2.matches("IX")){//LD SP,IX
                instruccion="1101110111111001";
            }
///////////
            else if (this.operando1.matches("SP")&&this.operando2.matches("IY")){//LD SP,IY
                instruccion="1111110111111001";
            }
///////////
        }
        else if ("push".equals(this.operacion)||"PUSH".equals(this.operacion)){
            if(this.operando1.matches("BC|DE|HL|AF")&&(this.operando2==null||"".equals(this.operando2))){//PUSH qq
                instruccion="11";
                switch(this.operando1){
		            case "BC":
                    case "bc":
                        instruccion=instruccion+"00";
                        break;
                    case "DE":
                    case "de":
                        instruccion=instruccion+"01";
                        break;
                    case "HL":
                    case "hl":
                        instruccion=instruccion+"10";
                        break;
                    case "AF":
                    case "af":
                        instruccion=instruccion+"11";
                        break;
	            }
                instruccion=instruccion+"0101";
	        }
            else if(this.operando1.matches("IX")&&(this.operando2==null||"".equals(this.operando2))){//PUSH IX
		        instruccion="11011101"+"11100101";
            }
	        else if(this.operando1.matches("IY")&&(this.operando2==null||"".equals(this.operando2))){//PUSH IY
		        instruccion="11111101"+"11100101";
            }
	    }
 //////////////
        else if ("pop".equals(this.operacion)||"POP".equals(this.operacion)){
            if(this.operando1.matches("BC|DE|HL|AF")&&(this.operando2==null||"".equals(this.operando2))){//POP qq
                instruccion="11";
                switch(this.operando1){
		            case "BC":
                    case "bc":
                        instruccion=instruccion+"00";
                        break;
                    case "DE":
                    case "de":
                        instruccion=instruccion+"01";
                        break;
                    case "HL":
                    case "hl":
                        instruccion=instruccion+"10";
                        break;
                    case "AF":
                    case "af":
                        instruccion=instruccion+"11";
                        break;
		        }
                instruccion=instruccion+"0001";
	        }
	        else if(this.operando1.matches("IX")&&(this.operando2==null||"".equals(this.operando2))){//POP IX
		        instruccion="11011101"+"11100001";
            }
	        else if(this.operando1.matches("IY")&&(this.operando2==null||"".equals(this.operando2))){//POP IY
		        instruccion="11111101"+"11100001";
            }
        }
    ////////////////////////Hoja 2. Grupo de carga de 16 bits
        else if ("ex".equals(this.operacion)||"EX".equals(this.operacion)){
            if(this.operando1.matches("DE")&&this.operando2.matches("HL")){             //EX DE,HL
      	        instruccion="11101011";
            }
            else if(this.operando1.matches("AF")&&this.operando2.matches("AF")){     //EX AF,AF
                instruccion="00001000";
            }
            else if(this.operando1.matches("\\(SP\\)")&&this.operando2.matches("HL")){ //EX (SP),HL
                instruccion="11100011";
            }
            else if(this.operando1.matches("\\(SP\\)")&&this.operando2.matches("IX")){ //EX (SP),IX
                instruccion="11011101"+"11100011";
            }
            else if(this.operando1.matches("\\(SP\\)")&&this.operando2.matches("IY")){ //EX (SP),IY
                instruccion="11111101"+"11100011";
            }
        }
        else if ("exx".equals(this.operacion)||"EXX".equals(this.operacion)){     //EXX
            instruccion="11011001";
        }
        else if ("ldi".equals(this.operacion)||"LDI".equals(this.operacion)){     //LDI
            instruccion="1110110110100000";
        }
        else if ("ldir".equals(this.operacion)||"LDIR".equals(this.operacion)){   //LDIR
            instruccion="1110110110110000";
        }
        else if ("ldd".equals(this.operacion)||"LDD".equals(this.operacion)){     //LDD
            instruccion="1110110110101000";
        }
        else if ("lddr".equals(this.operacion)||"LDDR".equals(this.operacion)){   //LDDR
            instruccion="1110110110111000";
        }
        else if ("cpi".equals(this.operacion)||"CPI".equals(this.operacion)){     //CPI
            instruccion="1110110110100001";
        }
        else if ("cpir".equals(this.operacion)||"CPIR".equals(this.operacion)){     //CPIR
            instruccion="1110110110110001";
        }
        else if ("cpd".equals(this.operacion)||"CPD".equals(this.operacion)){     //CPD
            instruccion="1110110110101001";
        }
        else if ("cpdr".equals(this.operacion)||"CPDR".equals(this.operacion)){     //CPDR
            instruccion="1110110110111001";
        }
////////////////Hoja 3. Grupo de intercambio, transferencia y búsqueda de bloques
        else if ("add".equals(this.operacion)||"ADD".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&(this.operando2==null||"".equals(this.operando2))){//ADD r
                instruccion="10000";
                switch(this.operando1.charAt(0)){//r
                    case 'A':
                    case 'a':
                        instruccion=instruccion+"111";
                        break;
                    case 'b':
                    case 'B':
                        instruccion=instruccion+"000";
                        break;
                    case 'C':
                    case 'c':
                        instruccion=instruccion+"001";
                        break;
                    case 'd':
                    case 'D':
                        instruccion=instruccion+"010";
                        break;
                    case 'e':
                    case 'E':
                        instruccion=instruccion+"011";
                        break;
                    case 'h':
                    case 'H':
                        instruccion=instruccion+"100";
                        break;
                    case 'l':
                    case 'L':
                        instruccion=instruccion+"101";
                        break;
                }
            }
            else if(this.operando1.matches("[0-9a-fA-FhHbB\\+\\-]+|\\w+")&&(this.operando2==null||"".equals(this.operando2))){//ADD n   
                instruccion="11000110";
                String n;
                if(valorOp1!=null){
                    n = this.toBinary(valorOp1);
                    while(n.length()<8){// en caso de que el número sea menor a 8, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-8, n.length());//en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n;
                }
                else if(this.operando1.matches("[0-9a-fA-FhHbB\\-\\+]+")){
                    n = this.toBinary(this.operando2);
                    while(n.length()<8){// en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
                        n="0"+n;    
                    }
                    n=n.substring(n.length()-8, n.length());//en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n;
            }
            else if(this.operando1.matches("\\(HL\\)")&&(this.operando2==null||"".equals(this.operando2))){//ADD (HL)
                instruccion="10000110";
            }
            else if(this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//ADD (IX+d)
                instruccion="11011101"+"10000110";
                String d = this.operando1.replaceAll("\\(IX", "");//quita la parte (IX
                d = d.replaceAll("\\)", "");//quita la parte ). Al final queda +-d
                d = this.toBinary(d);
                while(d.length()<8){// en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
                    d="0"+d;    
                }
                d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita números a la izquierda
                instruccion=instruccion+d;
            }
            else if(this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//ADD (IY+d)
                instruccion="11111101"+"10000110";
                String d = this.operando1.replaceAll("\\(IY", "");//quita la parte (IY
                d = d.replaceAll("\\)", "");//quita la parte ). Al final queda +-d
                d = this.toBinary(d);
                while(d.length()<8){// en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
                    d="0"+d;    
                }
                d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita números a la izquierda
                instruccion=instruccion+d;
            }
            else if(this.operando1.matches("HL")&&this.operando2.matches("BC|DE|HL|SP")){//ADD HL,ss     Aritmético de 16 bits
                instruccion="00";
                switch(this.operando2){//ss
                    case "BC":
                    case "bc":
                        instruccion=instruccion+"00";
                        break;
                    case "DE":
                    case "de":
                        instruccion=instruccion+"01";
                        break;
                    case "HL":
                    case "hl":
                        instruccion=instruccion+"10";
                        break;
                    case "SP":
                    case "sp":
                        instruccion=instruccion+"11";
                        break;
                }
                instruccion=instruccion+"1001";
            }
            else if(this.operando1.matches("IX")&&this.operando2.matches("BC|DE|IX|SP")){//ADD IX,pp
                instruccion="11011101"+"00";
                switch(this.operando2){//pp
                    case "BC":
                    case "bc":
                        instruccion=instruccion+"00";
                        break;
                    case "DE":
                    case "de":
                        instruccion=instruccion+"01";
                        break;
                    case "IX":
                    case "ix":
                        instruccion=instruccion+"10";
                        break;
                    case "SP":
                    case "sp":
                        instruccion=instruccion+"11";
                        break;
                }
                instruccion=instruccion+"1001";
            }
            else if(this.operando1.matches("IY")&&this.operando2.matches("BC|DE|IY|SP")){//ADD IY,rr
                instruccion="11111101"+"00";
                switch(this.operando2){//rr
                    case "BC":
                    case "bc":
                        instruccion=instruccion+"00";
                        break;
                    case "DE":
                    case "de":
                        instruccion=instruccion+"01";
                        break;
                    case "IY":
                    case "iy":
                        instruccion=instruccion+"10";
                        break;
                    case "SP":
                    case "sp":
                        instruccion=instruccion+"11";
                        break;
                    }
                    instruccion=instruccion+"1001";
                }
            }    
        }
        else if ("adc".equals(this.operacion)||"ADC".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&(this.operando2==null||"".equals(this.operando2))){//ADC r
                instruccion="10001";
                switch(this.operando1.charAt(0)){//r
                    case 'A':
                    case 'a':
                        instruccion=instruccion+"111";
                        break;
                    case 'b':
                    case 'B':
                        instruccion=instruccion+"000";
                        break;
                    case 'C':
                    case 'c':
                        instruccion=instruccion+"001";
                        break;
                    case 'd':
                    case 'D':
                        instruccion=instruccion+"010";
                        break;
                    case 'e':
                    case 'E':
                        instruccion=instruccion+"011";
                        break;
                    case 'h':
                    case 'H':
                        instruccion=instruccion+"100";
                        break;
                    case 'l':
                    case 'L':
                        instruccion=instruccion+"101";
                        break;
                }
            }
            else if(this.operando1.matches("[0-9A-Fa-fhHbB\\+\\-]+|\\w+")&&(this.operando2==null||"".equals(this.operando2))){//ADC n
                instruccion="11001110";
                String n;
                if(valorOp1!=null){
                    n = this.toBinary(valorOp1);
                    while(n.length()<8){// en caso de que el número sea menor a 8, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-8, n.length());//en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n;
                }
                else if(this.operando1.matches("[0-9a-fA-FhHbB\\-\\+]+")){
                    n = this.toBinary(this.operando2);
                    while(n.length()<8){// en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
                        n="0"+n;    
                    }
                    n=n.substring(n.length()-8, n.length());//en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n;
            }
            else if(this.operando1.matches("\\(HL\\)")&&(this.operando2==null||"".equals(this.operando2))){//ADC (HL)
                instruccion="10001110";
            }
            else if(this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//ADC (IX+d)
                instruccion="11011101"+"10001110";
                String d = this.operando1.replaceAll("\\(IX", "");//quita la parte (IX
                d = d.replaceAll("\\)", "");//quita la parte ). Al final queda +-d
                d = this.toBinary(d);
                while(d.length()<8){// en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
                    d="0"+d;    
                }
                d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita números a la izquierda
                instruccion=instruccion+d;
            }
            else if(this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//ADC (IY+d)
                instruccion="11111101"+"10000110";
                String d = this.operando1.replaceAll("\\(IY", "");//quita la parte (IY
                d = d.replaceAll("\\)", "");//quita la parte ). Al final queda +-d
                d = this.toBinary(d);
                while(d.length()<8){// en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
                    d="0"+d;    
                }
                d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita números a la izquierda
                instruccion=instruccion+d;
            }
            else if(this.operando1.matches("HL")&&this.operando2.matches("BC|DE|HL|SP")){//ADC HL,ss    Aritmético 16 bits
                instruccion="11101101"+"01";
                 switch(this.operando2){//ss
                    case "BC":
                    case "bc":
                        instruccion=instruccion+"00";
                        break;
                    case "DE":
                    case "de":
                        instruccion=instruccion+"01";
                        break;
                    case "HL":
                    case "hl":
                        instruccion=instruccion+"10";
                        break;
                    case "SP":
                    case "sp":
                        instruccion=instruccion+"11";
                        break;
                    }
                    instruccion=instruccion+"1010";
                }
            }
        }
        else if ("sub".equals(this.operacion)||"SUB".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&this.operando2==null){//SUB r
                instruccion="10010";
		switch(this.operando1.charAt(0)){//r
                    case 'A':
                    case 'a':
                        instruccion=instruccion+"111";
                        break;
                    case 'b':
                    case 'B':
                        instruccion=instruccion+"000";
                        break;
                    case 'C':
                    case 'c':
                        instruccion=instruccion+"001";
                        break;
                    case 'd':
                    case 'D':
                        instruccion=instruccion+"010";
                        break;
                    case 'e':
                    case 'E':
                        instruccion=instruccion+"011";
                        break;
                    case 'h':
                    case 'H':
                        instruccion=instruccion+"100";
                        break;
                    case 'l':
                    case 'L':
                        instruccion=instruccion+"101";
                        break;
                }
            }
            else if(this.operando1.matches("[0-9A-Fa-fhHbB\\-\\+]+")&&this.operando2==null){//SUB n
                instruccion="11010110";
                String n;
                if(valorOp1!=null){
                    n = this.toBinary(valorOp1);
                    while(n.length()<8){// en caso de que el número sea menor a 8, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-8, n.length());//en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n;
                }
                else if(this.operando1.matches("[0-9a-fA-FhHbB\\-\\+]+")){
                    n = this.toBinary(this.operando1);
                    while(n.length()<8){// en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-8, n.length());//en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n;
            	}
            }
            else if(this.operando1.matches("\\(HL\\)")&&this.operando2==null){//SUB (HL)
                instruccion="10010110";
            }
            else if(this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\)")&&this.operando2==null){//SUB (IX+d)
                instruccion="11011101"+"10010110";
                String d = this.operando1.replaceAll("\\(IX", "");//quita la parte (IX
                d = d.replaceAll("\\)", "");//quita la parte ). Al final queda +-d
                d = this.toBinary(d);
                while(d.length()<8){// en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
                    d="0"+d;
                }
                d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita números a la izquierda
                instruccion=instruccion+d;
            }
            else if(this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&this.operando2==null){//SUB (IY+d)
                instruccion="11111101"+"10010110";
                String d = this.operando1.replaceAll("\\(IY", "");//quita la parte (IY
                d = d.replaceAll("\\)", "");//quita la parte ). Al final queda +-d
                d = this.toBinary(d);
                while(d.length()<8){// en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
                    d="0"+d;
                }
                d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita números a la izquierda
                instruccion=instruccion+d;
            }
        }
        else if ("sbc".equals(this.operacion)||"SBC".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&this.operando2==null){//SBC r
                instruccion="10011";
		switch(this.operando1.charAt(0)){//r
                    case 'A':
                    case 'a':
                        instruccion=instruccion+"111";
                        break;
                    case 'b':
                    case 'B':
                        instruccion=instruccion+"000";
                        break;
                    case 'C':
                    case 'c':
                        instruccion=instruccion+"001";
                        break;
                    case 'd':
                    case 'D':
                        instruccion=instruccion+"010";
                        break;
                    case 'e':
                    case 'E':
                        instruccion=instruccion+"011";
                        break;
                    case 'h':
                    case 'H':
                        instruccion=instruccion+"100";
                        break;
                    case 'l':
                    case 'L':
                        instruccion=instruccion+"101";
                        break;
                }
            }
            else if(this.operando1.matches("[0-9A-Fa-fhHbB\\-\\+]+")&&this.operando2==null){//SBC n
                instruccion="11011110";
                String n;
                if(valorOp1!=null){
                    n = this.toBinary(valorOp1);
                    while(n.length()<8){// en caso de que el número sea menor a 8, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-8, n.length());//en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n;
                }
                else if(this.operando1.matches("[0-9a-fA-FhHbB\\-\\+]+")){
                    n = this.toBinary(this.operando1);
                    while(n.length()<8){// en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-8, n.length());//en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n;
            	}
            }
            else if(this.operando1.matches("\\(HL\\)")&&this.operando2==null){//SBC (HL)
                instruccion="10011110";
            }
            else if(this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\)")&&this.operando2==null){//SBC (IX+d)
                instruccion="11011101"+"10011110";
                String d = this.operando1.replaceAll("\\(IX", "");//quita la parte (IX
                d = d.replaceAll("\\)", "");//quita la parte ). Al final queda +-d
                d = this.toBinary(d);
                while(d.length()<8){// en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
                    d="0"+d;
                }
                d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita números a la izquierda
                instruccion=instruccion+d;
            }
            else if(this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&this.operando2==null){//SBC (IY+d)
                instruccion="11111101"+"10011110";
                String d = this.operando1.replaceAll("\\(IY", "");//quita la parte (IY
                d = d.replaceAll("\\)", "");//quita la parte ). Al final queda +-d
                d = this.toBinary(d);
                while(d.length()<8){// en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
                    d="0"+d;
                }
                d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita números a la izquierda
                instruccion=instruccion+d;
            }
            else if(this.operando1.matches("HL")&&this.operando2.matches("BC|DE|HL|SP")){//SBC HL,ss
                instruccion="11101101"+"01";
                 switch(this.operando2){//ss
                    case "BC":
                    case "bc":
                        instruccion=instruccion+"00";
                        break;
                    case "DE":
                    case "de":
                        instruccion=instruccion+"01";
                        break;
                    case "HL":
                    case "hl":
                        instruccion=instruccion+"10";
                        break;
                    case "SP":
                    case "sp":
                        instruccion=instruccion+"11";
                        break;
                    }
                    instruccion=instruccion+"0010";
                }
        }
        
        else if ("and".equals(this.operacion)||"AND".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&this.operando2==null){//AND r
                instruccion="10100";
		        switch(this.operando1.charAt(0)){//r
                    case 'A':
                    case 'a':
                        instruccion=instruccion+"111";
                        break;
                    case 'b':
                    case 'B':
                        instruccion=instruccion+"000";
                        break;
                    case 'C':
                    case 'c':
                        instruccion=instruccion+"001";
                        break;
                    case 'd':
                    case 'D':
                        instruccion=instruccion+"010";
                        break;
                    case 'e':
                    case 'E':
                        instruccion=instruccion+"011";
                        break;
                    case 'h':
                    case 'H':
                        instruccion=instruccion+"100";
                        break;
                    case 'l':
                    case 'L':
                        instruccion=instruccion+"101";
                        break;
                }
            }
            else if(this.operando1.matches("[0-9A-Fa-fhHbB\\-\\+]+")&&this.operando2==null){//AND n
                instruccion="11100110";
                String n;
                if(valorOp1!=null){
                    n = this.toBinary(valorOp1);
                    while(n.length()<8){// en caso de que el número sea menor a 8, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-8, n.length());//en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n;
                }
                else if(this.operando1.matches("[0-9a-fA-FhHbB\\-\\+]+")){
                    n = this.toBinary(this.operando1);
                    while(n.length()<8){// en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-8, n.length());//en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n;
            	}
            }
            else if(this.operando1.matches("\\(HL\\)")&&this.operando2==null){//AND (HL)
                instruccion="10100110";
            }
            else if(this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\)")&&this.operando2==null){//AND (IX+d)
                instruccion="11011101"+"10100110";
                String d = this.operando1.replaceAll("\\(IX", "");//quita la parte (IX
                d = d.replaceAll("\\)", "");//quita la parte ). Al final queda +-d
                d = this.toBinary(d);
                while(d.length()<8){// en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
                    d="0"+d;
                }
                d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita números a la izquierda
                instruccion=instruccion+d;
            }
            else if(this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&this.operando2==null){//AND (IY+d)
                instruccion="11111101"+"10100110";
                String d = this.operando1.replaceAll("\\(IY", "");//quita la parte (IY
                d = d.replaceAll("\\)", "");//quita la parte ). Al final queda +-d
                d = this.toBinary(d);
                while(d.length()<8){// en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
                    d="0"+d;
                }
                d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita números a la izquierda
                instruccion=instruccion+d;
            }
        }
        else if ("or".equals(this.operacion)||"OR".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&this.operando2==null){//OR r
                instruccion="10110";
		        switch(this.operando1.charAt(0)){//r
                    case 'A':
                    case 'a':
                        instruccion=instruccion+"111";
                        break;
                    case 'b':
                    case 'B':
                        instruccion=instruccion+"000";
                        break;
                    case 'C':
                    case 'c':
                        instruccion=instruccion+"001";
                        break;
                    case 'd':
                    case 'D':
                        instruccion=instruccion+"010";
                        break;
                    case 'e':
                    case 'E':
                        instruccion=instruccion+"011";
                        break;
                    case 'h':
                    case 'H':
                        instruccion=instruccion+"100";
                        break;
                    case 'l':
                    case 'L':
                        instruccion=instruccion+"101";
                        break;
                }
            }
            else if(this.operando1.matches("[0-9A-Fa-fhHbB\\-\\+]+")&&this.operando2==null){//OR n
                instruccion="11110110";
                String n;
                if(valorOp1!=null){
                    n = this.toBinary(valorOp1);
                    while(n.length()<8){// en caso de que el número sea menor a 8, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-8, n.length());//en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n;
                }
                else if(this.operando1.matches("[0-9a-fA-FhHbB\\-\\+]+")){
                    n = this.toBinary(this.operando1);
                    while(n.length()<8){// en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-8, n.length());//en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n;
            	}
            }
            else if(this.operando1.matches("\\(HL\\)")&&this.operando2==null){//OR (HL)
                instruccion="10110110";
            }
            else if(this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\)")&&this.operando2==null){//OR (IX+d)
                instruccion="11011101"+"10110110";
                String d = this.operando1.replaceAll("\\(IX", "");//quita la parte (IX
                d = d.replaceAll("\\)", "");//quita la parte ). Al final queda +-d
                d = this.toBinary(d);
                while(d.length()<8){// en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
                    d="0"+d;
                }
                d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita números a la izquierda
                instruccion=instruccion+d;
            }
            else if(this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&this.operando2==null){//OR (IY+d)
                instruccion="11111101"+"10110110";
                String d = this.operando1.replaceAll("\\(IY", "");//quita la parte (IY
                d = d.replaceAll("\\)", "");//quita la parte ). Al final queda +-d
                d = this.toBinary(d);
                while(d.length()<8){// en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
                    d="0"+d;
                }
                d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita números a la izquierda
                instruccion=instruccion+d;
            }
        }
        else if ("xor".equals(this.operacion)||"XOR".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&this.operando2==null){//XOR r
                instruccion="10101";
		switch(this.operando1.charAt(0)){//r
                    case 'A':
                    case 'a':
                        instruccion=instruccion+"111";
                        break;
                    case 'b':
                    case 'B':
                        instruccion=instruccion+"000";
                        break;
                    case 'C':
                    case 'c':
                        instruccion=instruccion+"001";
                        break;
                    case 'd':
                    case 'D':
                        instruccion=instruccion+"010";
                        break;
                    case 'e':
                    case 'E':
                        instruccion=instruccion+"011";
                        break;
                    case 'h':
                    case 'H':
                        instruccion=instruccion+"100";
                        break;
                    case 'l':
                    case 'L':
                        instruccion=instruccion+"101";
                        break;
                }
            }
            else if(this.operando1.matches("[0-9A-Fa-fhHbB\\-\\+]+")&&this.operando2==null){//XOR n
                instruccion="11101110";
                String n;
                if(valorOp1!=null){
                    n = this.toBinary(valorOp1);
                    while(n.length()<8){// en caso de que el número sea menor a 8, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-8, n.length());//en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n;
                }
                else if(this.operando1.matches("[0-9a-fA-FhHbB\\-\\+]+")){
                    n = this.toBinary(this.operando1);
                    while(n.length()<8){// en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-8, n.length());//en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n;
            	}
            }
            else if(this.operando1.matches("\\(HL\\)")&&this.operando2==null){//XOR (HL)
                instruccion="10101110";
            }
            else if(this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\)")&&this.operando2==null){//XOR (IX+d)
                instruccion="11011101"+"10101110";
                String d = this.operando1.replaceAll("\\(IX", "");//quita la parte (IX
                d = d.replaceAll("\\)", "");//quita la parte ). Al final queda +-d
                d = this.toBinary(d);
                while(d.length()<8){// en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
                    d="0"+d;
                }
                d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita números a la izquierda
                instruccion=instruccion+d;
            }
            else if(this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&this.operando2==null){//XOR (IY+d)
                instruccion="11111101"+"10101110";
                String d = this.operando1.replaceAll("\\(IY", "");//quita la parte (IY
                d = d.replaceAll("\\)", "");//quita la parte ). Al final queda +-d
                d = this.toBinary(d);
                while(d.length()<8){// en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
                    d="0"+d;
                }
                d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita números a la izquierda
                instruccion=instruccion+d;
            }
        }
        else if ("cp".equals(this.operacion)||"CP".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&(this.operando2==null||"".equals(this.operando2))){//CP r
                instruccion="10111";
                switch(this.operando1.charAt(0)){//r
                    case 'A':
                    case 'a':
                        instruccion=instruccion+"111";
                        break;
                    case 'b':
                    case 'B':
                        instruccion=instruccion+"000";
                        break;
                    case 'C':
                    case 'c':
                        instruccion=instruccion+"001";
                        break;
                    case 'd':
                    case 'D':
                        instruccion=instruccion+"010";
                        break;
                    case 'e':
                    case 'E':
                        instruccion=instruccion+"011";
                        break;
                    case 'h':
                    case 'H':
                        instruccion=instruccion+"100";
                        break;
                    case 'l':
                    case 'L':
                        instruccion=instruccion+"101";
                        break;
                }
            }
            else if(this.operando1.matches("[0-9A-Fa-fhHbB\\-\\+]+")&&(this.operando2==null||"".equals(this.operando2))){//CP n
                instruccion="11111110";
                String n;
                if(valorOp1!=null){
                    n = this.toBinary(valorOp1);
                    while(n.length()<8){// en caso de que el número sea menor a 8, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-8, n.length());//en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n;
                }
                else if(this.operando1.matches("[0-9a-fA-FhHbB\\-\\+]+")){
                    n = this.toBinary(this.operando1);
                    while(n.length()<8){// en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
                        n="0"+n;    
                    }
                    n=n.substring(n.length()-8, n.length());//en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n;
                }
            }
            else if(this.operando1.matches("\\(HL\\)")&&(this.operando2==null||"".equals(this.operando2))){//CP (HL)
                instruccion="10111110";
            }
            else if(this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//CP (IX+d)
                instruccion="11011101"+"10111110";
                String d = this.operando1.replaceAll("\\(IX", "");//quita la parte (IX
                d = d.replaceAll("\\)", "");//quita la parte ). Al final queda +-d
                d = this.toBinary(d);
                while(d.length()<8){// en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
                    d="0"+d;    
                }
                d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita números a la izquierda
                instruccion=instruccion+d;
            }
            else if(this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//CP (IY+d)
                instruccion="11111101"+"10111110";
                String d = this.operando1.replaceAll("\\(IY", "");//quita la parte (IY
                d = d.replaceAll("\\)", "");//quita la parte ). Al final queda +-d
                d = this.toBinary(d);
                while(d.length()<8){// en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
                    d="0"+d;    
                }
                d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita números a la izquierda
                instruccion=instruccion+d;
            }
        
        }
        else if ("inc".equals(this.operacion)||"INC".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&(this.operando2==null||"".equals(this.operando2))){//INC r
                instruccion="00";
                switch(this.operando1.charAt(0)){//r
                    case 'A':
                    case 'a':
                        instruccion=instruccion+"111";
                        break;
                    case 'b':
                    case 'B':
                        instruccion=instruccion+"000";
                        break;
                    case 'C':
                    case 'c':
                        instruccion=instruccion+"001";
                        break;
                    case 'd':
                    case 'D':
                        instruccion=instruccion+"010";
                        break;
                    case 'e':
                    case 'E':
                        instruccion=instruccion+"011";
                        break;
                    case 'h':
                    case 'H':
                        instruccion=instruccion+"100";
                        break;
                    case 'l':
                    case 'L':
                        instruccion=instruccion+"101";
                        break;
                }
                instruccion=instruccion+"100";
            }
            else if(this.operando1.matches("\\(HL\\)")&&(this.operando2==null||"".equals(this.operando2))){//INC (HL)
                instruccion="00110100";
            }
            else if(this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//INC (IX+d)
                instruccion="11011101"+"00110100";
                String d = this.operando1.replaceAll("\\(IX", "");//quita la parte (IX
                d = d.replaceAll("\\)", "");//quita la parte ). Al final queda +-d
                d = this.toBinary(d);
                while(d.length()<8){// en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
                    d="0"+d;    
                }
                d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita números a la izquierda
                instruccion=instruccion+d;
            }
            else if(this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//INC (IY+d)
                instruccion="11111101"+"00110100";
                String d = this.operando1.replaceAll("\\(IY", "");//quita la parte (IY
                d = d.replaceAll("\\)", "");//quita la parte ). Al final queda +-d
                d = this.toBinary(d);
                while(d.length()<8){// en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
                    d="0"+d;    
                }
                d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita números a la izquierda
                instruccion=instruccion+d;
            }
            else if(this.operando1.matches("BC|DE|HL|SP")&&(this.operando2==null||"".equals(this.operando2))){//INC ss       Aritmético 16 bits
                instruccion="00";
                switch(this.operando2){//ss
                    case "BC":
                    case "bc":
                        instruccion=instruccion+"00";
                        break;
                    case "DE":
                    case "de":
                        instruccion=instruccion+"01";
                        break;
                    case "HL":
                    case "hl":
                        instruccion=instruccion+"10";
                        break;
                    case "SP":
                    case "sp":
                        instruccion=instruccion+"11";
                        break;
                }
                instruccion=instruccion+"0011";
            } 
            else if(this.operando1.matches("IX")&&(this.operando2==null||"".equals(this.operando2))){//INC IX
                instruccion="11011101"+"00100011";
            }
            else if(this.operando1.matches("IY")&&(this.operando2==null||"".equals(this.operando2))){//INC IY
                instruccion="11111101"+"00100011";
            }
        }
        else if("dec".equals(this.operacion)||"DEC".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&(this.operando2==null||"".equals(this.operando2))){//DEC r
                instruccion="00";
                switch(this.operando1.charAt(0)){//r
                    case 'A':
                    case 'a':
                        instruccion=instruccion+"111";
                        break;
                    case 'b':
                    case 'B':
                        instruccion=instruccion+"000";
                        break;
                    case 'C':
                    case 'c':
                        instruccion=instruccion+"001";
                        break;
                    case 'd':
                    case 'D':
                        instruccion=instruccion+"010";
                        break;
                    case 'e':
                    case 'E':
                        instruccion=instruccion+"011";
                        break;
                    case 'h':
                    case 'H':
                        instruccion=instruccion+"100";
                        break;
                    case 'l':
                    case 'L':
                        instruccion=instruccion+"101";
                        break;
                }
                instruccion=instruccion+"101";
            }
            else if(this.operando1.matches("\\(HL\\)")&&(this.operando2==null||"".equals(this.operando2))){//DEC (HL)
                instruccion="00110101";
            }
            else if(this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//DEC (IX+d)
                instruccion="11011101"+"00110101";
                String d = this.operando1.replaceAll("\\(IX", "");//quita la parte (IX
                d = d.replaceAll("\\)", "");//quita la parte ). Al final queda +-d
                d = this.toBinary(d);
                while(d.length()<8){// en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
                    d="0"+d;    
                }
                d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita números a la izquierda
                instruccion=instruccion+d;
            }
            else if(this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&(this.operando2==null||"".equals(this.operando2))){//DEC (IY+d)
                instruccion="11111101"+"00110101";
                String d = this.operando1.replaceAll("\\(IY", "");//quita la parte (IY
                d = d.replaceAll("\\)", "");//quita la parte ). Al final queda +-d
                d = this.toBinary(d);
                while(d.length()<8){// en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
                    d="0"+d;    
                }
                d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita números a la izquierda
                instruccion=instruccion+d;
            }
            else if(this.operando1.matches("BC|DE|HL|SP")&&(this.operando2==null||"".equals(this.operando2))){//DEC ss      Aritmético 16 bits
                instruccion="00";
                switch(this.operando2){//ss
                    case "BC":
                    case "bc":
                        instruccion=instruccion+"00";
                        break;
                    case "DE":
                    case "de":
                        instruccion=instruccion+"01";
                        break;
                    case "HL":
                    case "hl":
                        instruccion=instruccion+"10";
                        break;
                    case "SP":
                    case "sp":
                        instruccion=instruccion+"11";
                        break;
                }
                instruccion=instruccion+"1011";
            }
            else if(this.operando1.matches("IX")&&(this.operando2==null||"".equals(this.operando2))){//DEC IX
                instruccion="11011101"+"00101011";
            }
            else if(this.operando1.matches("IY")&&(this.operando2==null||"".equals(this.operando2))){//DEC IY
                instruccion="11111101"+"00101011";
            }
            }
/////////////// CUARTA Y SEXTA HOJA. Grupo aritmético de 8 y de 16 bits
        else if ("daa".equals(this.operacion)||"DAA".equals(this.operacion)){     //DAA
            instruccion="00100111";
        }
        else if ("cpl".equals(this.operacion)||"CPL".equals(this.operacion)){     //CPL
            instruccion="00101111";
        }
        else if ("neg".equals(this.operacion)||"NEG".equals(this.operacion)){     //NEG
            instruccion="1110110101000100";
        }
        else if ("ccf".equals(this.operacion)||"CCF".equals(this.operacion)){     //CCF
            instruccion="00111111";
        }
        else if ("scf".equals(this.operacion)||"SCF".equals(this.operacion)){     //SCF
            instruccion="00110111";
        }
        else if ("nop".equals(this.operacion)||"NOP".equals(this.operacion)){     //NOP
            instruccion="00000000";
        }
        else if ("halt".equals(this.operacion)||"HALT".equals(this.operacion)){    //HALT
            instruccion="01110110";
        }
        else if ("di".equals(this.operacion)||"DI".equals(this.operacion)){       //DI
            instruccion="11110011";
        }
        else if ("ei".equals(this.operacion)||"EI".equals(this.operacion)){       //EI
            instruccion="11111011";
        }
        else if ("im".equals(this.operacion)||"IM".equals(this.operacion)){     //IM
            if (this.operando1.matches("0|1|2")&&this.operando2==null){
                switch(this.operando1){
                    case "0":                                                           //IM 0
                        instruccion="1110110101000110";
                        break;
                    case "1":                                                           //IM 1
                        instruccion="1110110101010110";
                        break;
                    case "2":                                                           //IM 2
                        instruccion="1110110101011110";
                        break;
                }
            }
        }
////////////////QUINTA TABLA
        else if ("rlca".equals(this.operacion)||"RLCA".equals(this.operacion)){   //RLCA
            instruccion="00000111";
        }
        else if ("rla".equals(this.operacion)||"RLA".equals(this.operacion)){     //RLA
            instruccion="00010111";
        }
        else if ("rrca".equals(this.operacion)||"RRCA".equals(this.operacion)){   //RRCA
            instruccion="00001111";
        }
        else if ("rra".equals(this.operacion)||"RRA".equals(this.operacion)){     //RRA
            instruccion="00011111";
        }
        else if ("rlc".equals(this.operacion)||"RLC".equals(this.operacion)){     //RLC
            if (this.operando1.matches("[A-E|H|L]")&&this.operando2==null){         //RLC r
                instruccion="1100101100000";
                switch(this.operando1.charAt(0)){
                    case 'A':
                    case 'a':
                        instruccion=instruccion+"111";
                        break;
                    case 'b':
                    case 'B':
                        instruccion=instruccion+"000";
                        break;
                    case 'C':
                    case 'c':
                        instruccion=instruccion+"001";
                        break;
                    case 'd':
                    case 'D':
                        instruccion=instruccion+"010";
                        break;
                    case 'e':
                    case 'E':
                        instruccion=instruccion+"011";
                        break;
                    case 'h':
                    case 'H':
                        instruccion=instruccion+"100";
                        break;
                    case 'l':
                    case 'L':
                        instruccion=instruccion+"101";
                        break;
                }
            }
            else if (this.operando1.matches("\\(HL\\)")&&this.operando2==null){     //RLC (HL)
                instruccion="1100101100000110";
            }
            else if (this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\")&&this.operando2==null){     //RLC (IX + d)
                instruccion="1101110111001011";
                String d = this.operando1.replaceAll("\\(IX", "");                    //quita la parte (IX
                d = d.replaceAll("\\)", "");                                    //quita la parte ). Al final queda +-d
                d = this.toBinary(d);
                while(d.length()<8){                                            // en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
                    d="0"+d;
                }
                d=d.substring(d.length()-8, d.length());                        //en caso de que sea mayor, le quita números a la izquierda
                instruccion=instruccion+d;
                instruccion=instruccion+"00000110";
            }
            else if (this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\")&&this.operando2==null){     // (IY + d)
                instruccion="1111110111001011";
                String d = this.operando1.replaceAll("\\(IY", "");                    //quita la parte (IX
                d = d.replaceAll("\\)", "");                                    //quita la parte ). Al final queda +-d
                d = this.toBinary(d);
                while(d.length()<8){                                            // en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
                    d="0"+d;
                }
                d=d.substring(d.length()-8, d.length());                        //en caso de que sea mayor, le quita números a la izquierda
                instruccion=instruccion+d;
                instruccion=instruccion+"00000110";
            }
        }
        else if ("rld".equals(this.operacion)||"RLD".equals(this.operacion)){     //RLD
            instruccion="1110110101101111";
        }
        else if ("rrd".equals(this.operacion)||"RRD".equals(this.operacion)){     //RRD
            instruccion="1110110101100111";
        }
////////////////SEPTIMA TABLA
        else if("bit".equals(this.operacion)||"BIT".equals(this.operacion)){
            if(this.operando1.matches("[0-7]")&&this.operando2.matches("[A-E]|H|L")){//BIT b,r
                instruccion="11001011"+"01";
                switch(this.operando1.charAt(0)){//b
                    case '0':
	                    instruccion=instruccion+"000";
		                break;
                    case '1':
                        instruccion=instruccion+"001";
                        break;
                    case '2':
		                instruccion=instruccion+"010";
		                break;
                    case '3':
                        instruccion=instruccion+"011";
                        break;
                    case '4':
		                instruccion=instruccion+"100";
		                break;
                    case '5':
                        instruccion=instruccion+"101";
                        break;
                    case '6':
                        instruccion=instruccion+"110";
                        break;
                    case '7':
                        instruccion=instruccion+"111";
                        break;
                }
                switch(this.operando2.charAt(0)){//r
                    case 'A':
                    case 'a':
                        instruccion=instruccion+"111";
                        break;
                    case 'b':
                    case 'B':
                        instruccion=instruccion+"000";
                        break;
                    case 'C':
                    case 'c':
                        instruccion=instruccion+"001";
                        break;
                    case 'd':
                    case 'D':
                        instruccion=instruccion+"010";
                        break;
                    case 'e':
                    case 'E':
                        instruccion=instruccion+"011";
                        break;
                    case 'h':
                    case 'H':
                        instruccion=instruccion+"100";
                        break;
                    case 'l':
                    case 'L':
                        instruccion=instruccion+"101";
                        break;
                }
            }
            else if(this.operando1.matches("[0-7]")&&this.operando2.matches("\\(HL\\)")){//BIT b,(HL)
                instruccion="11001011"+"01";
                switch(this.operando1.charAt(0)){//b
                    case '0':
                        instruccion=instruccion+"000";
		                break;
                    case '1':
                        instruccion=instruccion+"001";
                        break;
                    case '2':
		                instruccion=instruccion+"010";
		                break;
                    case '3':
                        instruccion=instruccion+"011";
                        break;
                    case '4':
		                instruccion=instruccion+"100";
		                break;
                    case '5':
                        instruccion=instruccion+"101";
                        break;
                    case '6':
                        instruccion=instruccion+"110";
                        break;
                    case '7':
                        instruccion=instruccion+"111";
                        break;
                    }
	                instruccion=instruccion+"110";
            }
            else if(this.operando1.matches("[0-7]")&&this.operando2.matches("\\(IX(\\+|\\-)[0-9]+\\)")){//BIT b,(IX+d)
                instruccion="11011101"+"11001011";
        	    String d = this.operando2.replaceAll("\\(IX", "");                    //quita la parte (IX
                d = d.replaceAll("\\)", "");                                    //quita la parte ). Al final queda +-d
                d = this.toBinary(d);
                while(d.length()<8){                                            // en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
                    d="0"+d;
                }
                d=d.substring(d.length()-8, d.length());                        //en caso de que sea mayor, le quita números a la izquierda
                instruccion=instruccion+d;
                instruccion=instruccion+"01";
                switch(this.operando1.charAt(0)){//b
                    case '0':
		                instruccion=instruccion+"000";
		                break;
                    case '1':
                        instruccion=instruccion+"001";
                        break;
                    case '2':
		                instruccion=instruccion+"010";
        		        break;
                    case '3':
                        instruccion=instruccion+"011";
                        break;
                    case '4':
		                instruccion=instruccion+"100";
        		        break;
                    case '5':
                        instruccion=instruccion+"101";
                        break;
                    case '6':
                        instruccion=instruccion+"110";
                        break;
                    case '7':
                        instruccion=instruccion+"111";
                        break;
                }
	            instruccion=instruccion+"110";	
            }
            else if(this.operando1.matches("[0-7]")&&this.operando2.matches("\\(IY(\\+|\\-)[0-9]+\\)")){//BIT b,(IY+d)
                instruccion="11111101"+"11001011";
                String d = this.operando2.replaceAll("\\(IY", "");                    //quita la parte (IX
                d = d.replaceAll("\\)", "");                                    //quita la parte ). Al final queda +-d
                d = this.toBinary(d);
                while(d.length()<8){                                            // en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
                    d="0"+d;
                }
                d=d.substring(d.length()-8, d.length());                        //en caso de que sea mayor, le quita números a la izquierda
                instruccion=instruccion+d;
                instruccion=instruccion+"01";
	            switch(this.operando1.charAt(0)){//b
                    case '0':
                		instruccion=instruccion+"000";
                		break;
                    case '1':
                        instruccion=instruccion+"001";
                        break;
                    case '2':
                		instruccion=instruccion+"010";
        		        break;
                    case '3':
                        instruccion=instruccion+"011";
                        break;
                    case '4':
		                instruccion=instruccion+"100";
                		break;
                    case '5':
                        instruccion=instruccion+"101";
                        break;
                    case '6':
                        instruccion=instruccion+"110";
                        break;
                    case '7':
                        instruccion=instruccion+"111";
                        break;
                }
	            instruccion=instruccion+"110";
            }
        }     
        else if("set".equals(this.operacion)||"SET".equals(this.operacion)){
            if(this.operando1.matches("[0-7]")&&this.operando2.matches("[A-E]|H|L")){//SET b,r
                instruccion="11001011"+"11";
                switch(this.operando1.charAt(0)){//b
                    case '0':
        		        instruccion=instruccion+"000";
        		        break;
                    case '1':
                        instruccion=instruccion+"001";
                        break;
                    case '2':
		                instruccion=instruccion+"010";
                		break;
                    case '3':
                        instruccion=instruccion+"011";
                        break;
                    case '4':
		                instruccion=instruccion+"100";
		                break;
                    case '5':
                        instruccion=instruccion+"101";
                        break;
                    case '6':
                        instruccion=instruccion+"110";
                        break;
                    case '7':
                        instruccion=instruccion+"111";
                        break;
                }
                switch(this.operando2.charAt(0)){//r
                    case 'A':
                    case 'a':
                        instruccion=instruccion+"111";
                        break;
                    case 'b':
                    case 'B':
                        instruccion=instruccion+"000";
                        break;
                    case 'C':
                    case 'c':
                        instruccion=instruccion+"001";
                        break;
                    case 'd':
                    case 'D':
                        instruccion=instruccion+"010";
                        break;
                    case 'e':
                    case 'E':
                        instruccion=instruccion+"011";
                        break;
                    case 'h':
                    case 'H':
                        instruccion=instruccion+"100";
                        break;
                    case 'l':
                    case 'L':
                        instruccion=instruccion+"101";
                        break;
                }    
            }
            else if(this.operando1.matches("[0-7]")&&this.operando2.matches("\\(HL\\)")){//SET b,(HL)
            instruccion="11001011"+"11";
            switch(this.operando1.charAt(0)){//b
                case '0':
	                instruccion=instruccion+"000";
		            break;
                case '1':
                    instruccion=instruccion+"001";
                    break;
                case '2':
            		instruccion=instruccion+"010";
		            break;
                case '3':
                    instruccion=instruccion+"011";
                    break;
                case '4':
            		instruccion=instruccion+"100";
		            break;
                case '5':
                    instruccion=instruccion+"101";
                    break;
                case '6':
                    instruccion=instruccion+"110";
                    break;
                case '7':
                        instruccion=instruccion+"111";
                        break;
            }
	        instruccion=instruccion+"110";
        }
    else if(this.operando1.matches("[0-7]")&&this.operando2.matches("\\(IX(\\+|\\-)[0-9]+\\)")){//SET b,(IX+d)
        instruccion="11011101"+"11001011";
	String d = this.operando2.replaceAll("\\(IX", "");                    //quita la parte (IX
        d = d.replaceAll("\\)", "");                                    //quita la parte ). Al final queda +-d
        d = this.toBinary(d);
        while(d.length()<8){                                            // en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
            d="0"+d;
        }
        d=d.substring(d.length()-8, d.length());                        //en caso de que sea mayor, le quita números a la izquierda
        instruccion=instruccion+d;
        instruccion=instruccion+"11";
        switch(this.operando1.charAt(0)){//b
            case '0':
		instruccion=instruccion+"000";
		break;
            case '1':
                instruccion=instruccion+"001";
                break;
            case '2':
		instruccion=instruccion+"010";
		break;
            case '3':
                instruccion=instruccion+"011";
                break;
            case '4':
		instruccion=instruccion+"100";
		break;
            case '5':
                instruccion=instruccion+"101";
                break;
            case '6':
                instruccion=instruccion+"110";
                break;
            case '7':
                instruccion=instruccion+"111";
                break;
        }
	instruccion=instruccion+"110";
    }
    else if(this.operando1.matches("[0-7]")&&this.operando2.matches("\\(IY(\\+|\\-)[0-9]+\\)")){//SET b,(IY+d)
        instruccion="11111101"+"11001011";
        String d = this.operando2.replaceAll("\\(IY", "");                    //quita la parte (IX
        d = d.replaceAll("\\)", "");                                    //quita la parte ). Al final queda +-d
        d = this.toBinary(d);
        while(d.length()<8){                                            // en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
            d="0"+d;
        }
        d=d.substring(d.length()-8, d.length());                        //en caso de que sea mayor, le quita números a la izquierda
        instruccion=instruccion+d;
        instruccion=instruccion+"11";
	switch(this.operando1.charAt(0)){//b
            case '0':
	        instruccion=instruccion+"000";
		break;
            case '1':
                instruccion=instruccion+"001";
                break;
            case '2':
		instruccion=instruccion+"010";
		break;
            case '3':
                instruccion=instruccion+"011";
                break;
            case '4':
	        instruccion=instruccion+"100";
		break;
            case '5':
                instruccion=instruccion+"101";
                break;
            case '6':
                instruccion=instruccion+"110";
                break;
            case '7':
                instruccion=instruccion+"111";
                break;
        }
	instruccion=instruccion+"110";
    }
}
else if("res".equals(this.operacion)||"RES".equals(this.operacion)){
    if(this.operando1.matches("[0-7]")&&this.operando2.matches("[A-E]|H|L")){//RES b,r
        instruccion="11001011"+"10";
        switch(this.operando1.charAt(0)){//b
            case '0':
		instruccion=instruccion+"000";
		break;
            case '1':
                instruccion=instruccion+"001";
                break;
            case '2':
		instruccion=instruccion+"010";
		break;
            case '3':
                instruccion=instruccion+"011";
                break;
            case '4':
		instruccion=instruccion+"100";
		break;
            case '5':
                instruccion=instruccion+"101";
                break;
            case '6':
                instruccion=instruccion+"110";
                break;
            case '7':
                instruccion=instruccion+"111";
                break;
        }
        switch(this.operando2.charAt(0)){//r
             case 'A':
             case 'a':
                instruccion=instruccion+"111";
                break;
             case 'b':
             case 'B':
                instruccion=instruccion+"000";
                break;
             case 'C':
             case 'c':
                instruccion=instruccion+"001";
                break;
             case 'd':
             case 'D':
                instruccion=instruccion+"010";
                break;
             case 'e':
             case 'E':
                instruccion=instruccion+"011";
                break;
             case 'h':
             case 'H':
                instruccion=instruccion+"100";
                break;
             case 'l':
             case 'L':
                instruccion=instruccion+"101";
                break;
        }
    }
    else if(this.operando1.matches("[0-7]")&&this.operando2.matches("\\(HL\\)")){//RES b,(HL)
        instruccion="11001011"+"11";
        switch(this.operando1.charAt(0)){//b
            case '0':
		instruccion=instruccion+"000";
		break;
            case '1':
                instruccion=instruccion+"001";
                break;
            case '2':
		instruccion=instruccion+"010";
		break;
            case '3':
                instruccion=instruccion+"011";
                break;
            case '4':
		instruccion=instruccion+"100";
		break;
            case '5':
                  instruccion=instruccion+"101";
                  break;
              case '6':
                  instruccion=instruccion+"110";
                  break;
              case '7':
                  instruccion=instruccion+"111";
                  break;
            }
	    instruccion=instruccion+"110";
            }
            else if(this.operando1.matches("[0-7]")&&this.operando2.matches("\\(IX(\\+|\\-)[0-9]+\\)")){//RES b,(IX+d)
                instruccion="11011101"+"11001011";
	    String d = this.operando2.replaceAll("\\(IX", "");                    //quita la parte (IX
            d = d.replaceAll("\\)", "");                                    //quita la parte ). Al final queda +-d
            d = this.toBinary(d);
            while(d.length()<8){                                            // en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
               d="0"+d;
            }
            d=d.substring(d.length()-8, d.length());                        //en caso de que sea mayor, le quita números a la izquierda
            instruccion=instruccion+d;
            instruccion=instruccion+"10";
            switch(this.operando1.charAt(0)){//b
              case '0':
		  instruccion=instruccion+"000";
		  break;
              case '1':
                  instruccion=instruccion+"001";
                  break;
              case '2':
		  instruccion=instruccion+"010";
		  break;
              case '3':
                  instruccion=instruccion+"011";
                  break;
              case '4':
		  instruccion=instruccion+"100";
		  break;
              case '5':
                  instruccion=instruccion+"101";
                  break;
              case '6':
                  instruccion=instruccion+"110";
                  break;
              case '7':
                  instruccion=instruccion+"111";
                  break;
            }
	    instruccion=instruccion+"110";
            }
            else if(this.operando1.matches("[0-7]")&&this.operando2.matches("\\(IY(\\+|\\-)[0-9]+\\)")){//RES b,(IY+d)
                instruccion="11111101"+"11001011";
            String d = this.operando2.replaceAll("\\(IY", "");                    //quita la parte (IX
            d = d.replaceAll("\\)", "");                                    //quita la parte ). Al final queda +-d
            d = this.toBinary(d);
            while(d.length()<8){                                            // en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
                d="0"+d;
            }
            d=d.substring(d.length()-8, d.length());                        //en caso de que sea mayor, le quita números a la izquierda
            instruccion=instruccion+d;
            instruccion=instruccion+"10";
	    switch(this.operando1.charAt(0)){//b
              case '0':
		  instruccion=instruccion+"000";
		  break;
              case '1':
                  instruccion=instruccion+"001";
                  break;
              case '2':
		  instruccion=instruccion+"010";
		  break;
              case '3':
                  instruccion=instruccion+"011";
                  break;
              case '4':
		  instruccion=instruccion+"100";
		  break;
              case '5':
                  instruccion=instruccion+"101";
                  break;
              case '6':
                  instruccion=instruccion+"110";
                  break;
              case '7':
                  instruccion=instruccion+"111";
                  break;
            }
	    instruccion=instruccion+"110";
            }
      }
//////////////////////////////Octava hoja





        else if("jp".equals(this.operacion)||"JP".equals(this.operacion)){
            if (this.operando1.matches("([0-9a-fA-FhHbB\\-\\+]+)|\\w+")&&(this.operando2==null||"".equals(this.operando2))){//JP nn
                instruccion="11000011";
                String n;
                if(valorOp1!=null){
                    n = this.toBinary(valorOp1);
                    while(n.length()<16){// en caso de que el número sea menor a 16, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-16, n.length());//en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n.substring(8);
                    instruccion=instruccion+n.substring(0, 8);
                }
                else if(this.operando1.matches("[0-9a-fA-FhHbB\\-\\+]+")){
                    n = this.toBinary(this.operando1);
                    while(n.length()<16){// en caso de que el número sea menor a 16 bits, pone ceros a la izquierda
                        n="0"+n;    
                    }
                    n=n.substring(n.length()-16, n.length());//en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n.substring(8);
                    instruccion=instruccion+n.substring(0, 8);
                }
            }
            else if (this.operando1.matches("NZ|Z|NC|C|PO|PE|P|M")&&this.operando2.matches("([0-9a-fA-FhHbB\\-\\+]+)|\\w+")){//JP cc,nn,
                instruccion="11";
                switch(this.operando1){//cc
                    case "NZ"://no cero
                    case "nz":
                        instruccion=instruccion+"000";
                        break;
                    case "Z"://cero
                    case "z":
                        instruccion=instruccion+"001";
                        break;
                    case "NC"://no arrastre
                    case "nc":
                        instruccion=instruccion+"010";
                        break;
                    case "C"://arrastre
                    case "c":
                        instruccion=instruccion+"011";
                        break;
                    case "PO"://polaridad impar
                    case "po":
                        instruccion=instruccion+"100";
                        break;
                    case "PE"://polaridad par
                    case "pe":
                        instruccion=instruccion+"101";
                        break;
                    case "P"://signo positivo
                    case "p":
                        instruccion=instruccion+"110";
                        break;
                    case "M"://signo negativo
                    case "m":
                        instruccion=instruccion+"111";
                        break;
                }
                instruccion=instruccion+"010";
                String n;
                if(valorOp2!=null){
                    n = this.toBinary(valorOp2);
                    while(n.length()<16){// en caso de que el número sea menor a 16, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-16, n.length());//en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n.substring(8);
                    instruccion=instruccion+n.substring(0, 8);
                }
                else if(this.operando2.matches("[0-9a-fA-FhHbB\\-\\+]+")){
                    n = this.toBinary(this.operando2);
                    while(n.length()<16){// en caso de que el número sea menor a 16 bits, pone ceros a la izquierda
                        n="0"+n;    
                    }
                    n=n.substring(n.length()-16, n.length());//en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n.substring(8);
                    instruccion=instruccion+n.substring(0, 8);
                }
            }
            else if (this.operando1.matches("\\(HL\\)")&&(this.operando2==null||"".equals(this.operando2))){//JP (HL)
                instruccion="11101001";
            }
            else if (this.operando1.matches("\\(IX\\)")&&(this.operando2==null||"".equals(this.operando2))){//JP (IX)
                instruccion="11011101"+"11101001";
            }
            else if (this.operando1.matches("\\(IY\\)")&&(this.operando2==null||"".equals(this.operando2))){//JP (IY)
                instruccion="11111101"+"11101001";
            }
        }
        else if("jr".equals(this.operacion)||"JR".equals(this.operacion)){
            if (this.operando1.matches("[\\-0-9hHbB]+|\\w+")&&(this.operando2==null||"".equals(this.operando2))){//JR e
                instruccion="00011000";
                if(valorOp1!=null){//si es diferente de nulo, hay valor de etiqueta
                    int CLnuevo = Integer.parseInt(valorOp1);
                    int CLactual = CL;
                    Integer e= CLnuevo-CLactual-2;
                    if(e<-126)
                        e=126;
                    else if(e>129)
                        e=129;
                    String e2 = this.toBinary(e.toString());
                    while (e2.length()<8)
                        e2="0"+e2;
                    instruccion=instruccion+e2;
                }
                else if(this.operando1.matches("[\\-\\+0-9hHbB]+")){
                    String CLnuevo0 = this.toBinary(this.operando1);
                    CLnuevo0 = CLnuevo0.substring(CLnuevo0.length()-8,CLnuevo0.length());
                    int CLnuevo = Integer.parseInt(CLnuevo0);
                    int CLactual = CL;
                    Integer e= CLnuevo-CLactual-2;
                    if(e<-126)
                        e=126;
                    else if(e>129)
                        e=129;
                    String e2 = this.toBinary(e.toString());
                    while (e2.length()<8)
                        e2="0"+e2;
                    instruccion=instruccion+e2;
                }
            }
            else if (this.operando1.matches("C")&&this.operando2.matches("[\\-0-9hHbB]+|\\w+")){//JR C,e
                instruccion="00111000";
                if(valorOp2!=null){//si es diferente de nulo, hay valor de etiqueta
                    int CLnuevo = Integer.parseInt(valorOp2);
                    int CLactual = CL;
                    Integer e= CLnuevo-CLactual-2;
                    if(e<-126)
                        e=126;
                    else if(e>129)
                        e=129;
                    String e2 = this.toBinary(e.toString());
                    while (e2.length()<8)
                        e2="0"+e2;
                    instruccion=instruccion+e2;
                }
                else if(this.operando2.matches("[\\-\\+0-9hHbB]+")){
                    String CLnuevo0 = this.toBinary(this.operando2);
                    CLnuevo0 = CLnuevo0.substring(CLnuevo0.length()-8,CLnuevo0.length());
                    int CLnuevo = Integer.parseInt(CLnuevo0);
                    int CLactual = CL;
                    Integer e= CLnuevo-CLactual-2;
                    if(e<-126)
                        e=126;
                    else if(e>129)
                        e=129;
                    String e2 = this.toBinary(e.toString());
                    while (e2.length()<8)
                        e2="0"+e2;
                    instruccion=instruccion+e2;
                }  
            }
            else if (this.operando1.matches("NC")&&this.operando2.matches("[\\-0-9hHbB]+|\\w+")){//JR NC,e
                instruccion="00110000";
                if(valorOp2!=null){//si es diferente de nulo, hay valor de etiqueta
                    int CLnuevo = Integer.parseInt(valorOp2);
                    int CLactual = CL;
                    Integer e= CLnuevo-CLactual-2;
                    if(e<-126)
                        e=126;
                    else if(e>129)
                        e=129;
                    String e2 = this.toBinary(e.toString());
                    while (e2.length()<8)
                        e2="0"+e2;
                    instruccion=instruccion+e2;
                }
                else if(this.operando2.matches("[\\-\\+0-9hHbB]+")){
                    String CLnuevo0 = this.toBinary(this.operando2);
                    CLnuevo0 = CLnuevo0.substring(CLnuevo0.length()-8,CLnuevo0.length());
                    int CLnuevo = Integer.parseInt(CLnuevo0);
                    int CLactual = CL;
                    Integer e= CLnuevo-CLactual-2;
                    if(e<-126)
                        e=126;
                    else if(e>129)
                        e=129;
                    String e2 = this.toBinary(e.toString());
                    while (e2.length()<8)
                        e2="0"+e2;
                    instruccion=instruccion+e2;
                }
            }
            else if (this.operando1.matches("Z")&&this.operando2.matches("[\\-0-9hHbB]+|\\w+")){//JR Z,e
                instruccion="00101000";
                if(valorOp2!=null){//si es diferente de nulo, hay valor de etiqueta
                    int CLnuevo = Integer.parseInt(valorOp2);
                    int CLactual = CL;
                    Integer e= CLnuevo-CLactual-2;
                    if(e<-126)
                        e=126;
                    else if(e>129)
                        e=129;
                    String e2 = this.toBinary(e.toString());
                    while (e2.length()<8)
                        e2="0"+e2;
                    instruccion=instruccion+e2;
                }
                else if(this.operando2.matches("[\\-\\+0-9hHbB]+")){
                    String CLnuevo0 = this.toBinary(this.operando2);
                    CLnuevo0 = CLnuevo0.substring(CLnuevo0.length()-8,CLnuevo0.length());
                    int CLnuevo = Integer.parseInt(CLnuevo0);
                    int CLactual = CL;
                    Integer e= CLnuevo-CLactual-2;
                    if(e<-126)
                        e=126;
                    else if(e>129)
                        e=129;
                    String e2 = this.toBinary(e.toString());
                    while (e2.length()<8)
                        e2="0"+e2;
                    instruccion=instruccion+e2;
                }
            }
            else if (this.operando1.matches("NZ")&&this.operando2.matches("[\\-0-9hHbB]+|\\w+")){//JR NZ,e
                instruccion="00100000";
                if(valorOp2!=null){//si es diferente de nulo, hay valor de etiqueta
                    int CLnuevo = Integer.parseInt(valorOp2);
                    int CLactual = CL;
                    Integer e= CLnuevo-CLactual-2;
                    if(e<-126)
                        e=126;
                    else if(e>129)
                        e=129;
                    String e2 = this.toBinary(e.toString());
                    while (e2.length()<8)
                        e2="0"+e2;
                    instruccion=instruccion+e2;
                }
                else if(this.operando2.matches("[\\-\\+0-9hHbB]+")){
                    String CLnuevo0 = this.toBinary(this.operando2);
                    CLnuevo0 = CLnuevo0.substring(CLnuevo0.length()-8,CLnuevo0.length());
                    int CLnuevo = Integer.parseInt(CLnuevo0);
                    int CLactual = CL;
                    Integer e= CLnuevo-CLactual-2;
                    if(e<-126)
                        e=126;
                    else if(e>129)
                        e=129;
                    String e2 = this.toBinary(e.toString());
                    while (e2.length()<8)
                        e2="0"+e2;
                    instruccion=instruccion+e2;
                }
            }
        }
        else if("djn".equals(this.operacion)||"DJN".equals(this.operacion)){
            if (this.operando1.matches("Z")&&this.operando2.matches("[\\-0-9hHbB]+|\\w+")){//DJN Z,e
                instruccion="00010000";
                if(valorOp1!=null){//si es diferente de nulo, hay valor de etiqueta
                    int CLnuevo = Integer.parseInt(valorOp1);
                    int CLactual = CL;
                    Integer e= CLnuevo-CLactual-2;
                    if(e<-126)
                        e=126;
                    else if(e>129)
                        e=129;
                    String e2 = this.toBinary(e.toString());
                    while (e2.length()<8)
                        e2="0"+e2;
                    instruccion=instruccion+e2;
                }
                else if(this.operando1.matches("[\\-\\+0-9hHbB]+")){
                    String CLnuevo0 = this.toBinary(this.operando1);
                    CLnuevo0 = CLnuevo0.substring(CLnuevo0.length()-8,CLnuevo0.length());
                    int CLnuevo = Integer.parseInt(CLnuevo0);
                    int CLactual = CL;
                    Integer e= CLnuevo-CLactual-2;
                    if(e<-126)
                        e=126;
                    else if(e>129)
                        e=129;
                    String e2 = this.toBinary(e.toString());
                    while (e2.length()<8)
                        e2="0"+e2;
                    instruccion=instruccion+e2;
                }
            }
        }
//////////////// NOVENA HOJA
else if("call".equals(this.operacion)||"CALL".equals(this.operacion)){
            if (this.operando1.matches("[0-9a-fA-FhHbB\\-\\+]+|\\w+")&&(this.operando2==null||"".equals(this.operando2))){//CALL nn
                instruccion="11001101";
                String n;
                if(valorOp1!=null){
                    n = this.toBinary(valorOp1);
                    while(n.length()<16){// en caso de que el número sea menor a 16, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-16, n.length());//en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n.substring(8);
                    instruccion=instruccion+n.substring(0, 8);
                }
                else if(this.operando1.matches("[0-9a-fA-FhHbB\\-\\+]+")){
                    n = this.toBinary(this.operando1);
                    while(n.length()<16){// en caso de que el número sea menor a 16 bits, pone ceros a la izquierda
                        n="0"+n;    
                    }
                    n=n.substring(n.length()-16, n.length());//en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n.substring(8);
                    instruccion=instruccion+n.substring(0, 8);
                }
            }
            else if (this.operando1.matches("NZ|Z|NC|C|PO|PE|P|M")&&this.operando2.matches("[0-9a-fA-FhHbB\\-\\+]+|\\w+")){//CALL cc,nn
                instruccion="11";
                switch(this.operando1){//cc
                    case "NZ"://no cero
                    case "nz":
                        instruccion=instruccion+"000";
                        break;
                    case "Z"://cero
                    case "z":
                        instruccion=instruccion+"001";
                        break;
                    case "NC"://no arrastre
                    case "nc":
                        instruccion=instruccion+"010";
                        break;
                    case "C"://arrastre
                    case "c":
                        instruccion=instruccion+"011";
                        break;
                    case "PO"://polaridad impar
                    case "po":
                        instruccion=instruccion+"100";
                        break;
                    case "PE"://polaridad par
                    case "pe":
                        instruccion=instruccion+"101";
                        break;
                    case "P"://signo positivo
                    case "p":
                        instruccion=instruccion+"110";
                        break;
                    case "M"://signo negativo
                    case "m":
                        instruccion=instruccion+"111";
                        break;
                }
                instruccion=instruccion+"100";
                String n;
                if(valorOp2!=null){
                    n = this.toBinary(valorOp2);
                    while(n.length()<16){// en caso de que el número sea menor a 16, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-16, n.length());//en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n.substring(8);
                    instruccion=instruccion+n.substring(0, 8);
                }
                else if(this.operando2.matches("[0-9a-fA-FhHbB\\-\\+]+")){
                    n = this.toBinary(this.operando2);
                    while(n.length()<16){// en caso de que el número sea menor a 16 bits, pone ceros a la izquierda
                        n="0"+n;    
                    }
                    n=n.substring(n.length()-16, n.length());//en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n.substring(8);
                    instruccion=instruccion+n.substring(0, 8);
                }
            }
        }
        else if("ret".equals(this.operacion)||"RET".equals(this.operacion)){
            if ((this.operando2==null||"".equals(this.operando2))){//RET
                instruccion="11001001";
            }
            else if (this.operando1.matches("NZ|Z|NC|C|PO|PE|P|M")&&(this.operando2==null||"".equals(this.operando2))){//RET cc
                instruccion="11";
                switch(this.operando1){//cc
                    case "NZ"://no cero
                    case "nz":
                        instruccion=instruccion+"000";
                        break;
                    case "Z"://cero
                    case "z":
                        instruccion=instruccion+"001";
                        break;
                    case "NC"://no arrastre
                    case "nc":
                        instruccion=instruccion+"010";
                        break;
                    case "C"://arrastre
                    case "c":
                        instruccion=instruccion+"011";
                        break;
                    case "PO"://polaridad impar
                    case "po":
                        instruccion=instruccion+"100";
                        break;
                    case "PE"://polaridad par
                    case "pe":
                        instruccion=instruccion+"101";
                        break;
                    case "P"://signo positivo
                    case "p":
                        instruccion=instruccion+"110";
                        break;
                    case "M"://signo negativo
                    case "m":
                        instruccion=instruccion+"111";
                        break;
                }
                instruccion=instruccion+"000";
            }
        }
        else if("reti".equals(this.operacion)||"RETI".equals(this.operacion)){//RETI
            instruccion="1110110101001101";
        }
        else if("retn".equals(this.operacion)||"RETN".equals(this.operacion)){//RETN
            instruccion="1110110101000101";
        }
        else if("rst".equals(this.operacion)||"RST".equals(this.operacion)){
            instruccion="11";
            switch(this.operando2){//pp
                case "00H":
                case "00h":
                    instruccion=instruccion+"000";
                    break;
                case "08H":
                case "08h":
                    instruccion=instruccion+"001";
                    break;
                case "10H":
                case "10h":
                    instruccion=instruccion+"010";
                    break;
                case "18H":
                case "18h":
                    instruccion=instruccion+"011";
                    break;
                case "20H":
                case "20h":
                    instruccion=instruccion+"100";
                    break;
                case "28H":
                case "28h":
                    instruccion=instruccion+"101";
                    break;
                case "30H":
                case "30h":
                    instruccion=instruccion+"110";
                    break;
                case "38H":
                case "38h":
                    instruccion=instruccion+"111";
                    break;
            }
            instruccion=instruccion+"111";
        }
//////////// DECIMA HOJA
        else if ("in".equals(this.operacion)||"IN".equals(this.operacion)){       //IN A,(n)
            if(this.operando1.matches("A")&&this.operando2.matches("\\([0-9]\\)")){
                instruccion="11011011";
                String n;
                if(valorOp2!=null){
      n = valorOp2.replaceAll("\\(", "");
      n = n.replaceAll("\\)","");
      n = this.toBinary(n);
      while(n.length()<8){                                          // en caso de que el número sea menor a 8, pone ceros a la izquierda
        n="0"+n;
      }
      n=n.substring(n.length()-8, n.length());                      //en caso de que sea mayor, le quita números a la izquierda
      instruccion=instruccion+n;
    }
  }
  else if(this.operando1.matches("[A-E|H|L]")&&this.operando2.matches("\\(C\\)")){ //IN r,(C)
    instruccion="1110110101";
    switch(this.operando1.charAt(0)){
      case 'A':
      case 'a':
      instruccion=instruccion+"111";
      break;
      case 'b':
      case 'B':
      instruccion=instruccion+"000";
      break;
      case 'C':
      case 'c':
      instruccion=instruccion+"001";
      break;
      case 'd':
      case 'D':
      instruccion=instruccion+"010";
      break;
      case 'e':
      case 'E':
      instruccion=instruccion+"011";
      break;
      case 'h':
      case 'H':
      instruccion=instruccion+"100";
      break;
      case 'l':
      case 'L':
      instruccion=instruccion+"101";
      break;
    }
    instruccion=instruccion+"000";
  }
}
else if ("ini".equals(this.operacion)||"INI".equals(this.operacion)){   //INI
  instruccion="1110110110100010";
}
else if ("inir".equals(this.operacion)||"INIR".equals(this.operacion)){ //INIR
  instruccion="1110110110110010";
}
else if ("ind".equals(this.operacion)||"IND".equals(this.operacion)){   //IND
  instruccion="1110110110101010";
}
else if ("indr".equals(this.operacion)||"INDR".equals(this.operacion)){ //INDR
  instruccion="1110110110111010";
}
else if ("out".equals(this.operacion)||"OUT".equals(this.operacion)){
  if(this.operando1.matches("\\([0-9]\\)")&&this.operando2.matches("A")){       //OUT (n),A
    instruccion="11010011";
  }
  else if(this.operando1.matches("\\(C\\)")&&this.operando2.matches("[A-E|H|L]")){ //OUT (C),r
    instruccion="1110110101";
    switch(this.operando2.charAt(0)){
      case 'A':
      case 'a':
      instruccion=instruccion+"111";
      break;
      case 'b':
      case 'B':
      instruccion=instruccion+"000";
      break;
      case 'C':
      case 'c':
      instruccion=instruccion+"001";
      break;
      case 'd':
      case 'D':
      instruccion=instruccion+"010";
      break;
      case 'e':
      case 'E':
      instruccion=instruccion+"011";
      break;
      case 'h':
      case 'H':
      instruccion=instruccion+"100";
      break;
      case 'l':
      case 'L':
      instruccion=instruccion+"101";
      break;
    }
    instruccion=instruccion+"001";
  }
}
else if ("outi".equals(this.operacion)||"OUTI".equals(this.operacion)){         //OUTI
  instruccion="1110110110100011";
}
else if ("otir".equals(this.operacion)||"OTIR".equals(this.operacion)){         //OTIR
  instruccion="1110110110110011";
}
else if ("outd".equals(this.operacion)||"OUTD".equals(this.operacion)){         //OUTD
  instruccion="1110110110101011";
}
else if ("otdr".equals(this.operacion)||"OTDR".equals(this.operacion)){         //OTDR
  instruccion="1110110110111011";
}
////////////////TABLA ONCE


        if ("".equals(instruccion))
            return "error";
        int insDec;
        String insHex;
        if (instruccion.length()>12){//En caso de que el número binario sea muy grande
            String instruccion1 = instruccion.substring(0,instruccion.length()-4);
            int insDec1 = Integer.parseInt(instruccion1, 2);//Pasa de binario a decimal sólo los dígitos más significativos (arriba de 8)
            String instruccion2 = instruccion.substring(instruccion.length()-4);
            int insDec2 = Integer.parseInt(instruccion2, 2);//Pasa de binario a decimal los dígitos restantes
            insHex = Integer.toHexString(insDec1)+Integer.toHexString(insDec2);
        }
        else{
            insDec = Integer.parseInt(instruccion, 2);//Pasa de binario a decimal
            insHex=Integer.toHexString(insDec);//pasa de decimal a hexadecimal
        }
        if(insHex.length()<2){//En caso de que la instruccion quede menor que 2, le agrega ceros hasta formar un byte
            while(insHex.length()<2)
                insHex = "0"+insHex;
        }
        else if(2<insHex.length()&&insHex.length()<4){//hasta formar dos bytes
            while(insHex.length()<4)
                insHex = "0"+insHex;
        }
        else if(4<insHex.length()&&insHex.length()<6){//hasta formar tres bytes
            while(insHex.length()<6)
                insHex = "0"+insHex;
        }
        else if(6<insHex.length()&&insHex.length()<8){//hasta formar cuatro bytes
            while(insHex.length()<8)
                insHex = "0"+insHex;
        }
        return insHex;
    }
}