package ensamblador;

/**
 *
 * @author masues
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.*;
public class Linea {
    public final String regex = "\\h*((\\w*):)?\\h*(\\w*)(\\h*(([a-zA-Z_0-9+\\-]*|\\([a-zA-Z_0-9+\\-]*\\))(,\\h*([a-zA-Z_0-9+\\-]*|\\([a-zA-Z_0-9+\\-]*\\)))?)?)?\\h*(;.*)?";//expresión regular
    public String etiqueta, operacion, operando1, operando2;
    public Boolean cumple;
    public ArrayList<String> simbolosDefecto = new ArrayList<>( 
            Arrays.asList("A","B","C","D","E","H","L","BC","DE","HL","SP","AF","IX","IY","NZ","Z","NC","C","PO","PE","P","M")); 
    public ArrayList<String> simbolos = new ArrayList<String>();
   
    
    
    
    
    
    public Linea(String lineaEntera) {//el constructor devuelve true si la linea tiene el formato adecuado
        Pattern patron = Pattern.compile(regex);//compila la expresión regular
        Matcher match = patron.matcher(lineaEntera);//busca que encaje la expresión con una cadena que mando desde los argumentos
        if(match.matches()){
            this.etiqueta = match.group(2);//el grupo 1 es la etiqueta
            this.operacion = match.group(3);//el grupo 2 la instrucción
            this.operando1 = match.group(6);//el grupo 5 es el primer operando
            this.operando2 = match.group(8);//el grupo 7 es el segundo argumento
            this.cumple=true;
        }
        else
            this.cumple=false;
    }
    
    public Boolean hayEtiquetas(){
        Boolean estado=false;
        if(!this.simbolosDefecto.contains(this.operando1)&&this.operando1!=null){
            if(!this.operando1.matches("\\(?[0-9A-Fa-fhH\\-]+\\)?")){
                //this.simbolos.add(this.operando1);
                estado=true;
            }
        }
        if(!this.simbolosDefecto.contains(this.operando2)&&this.operando2!=null){
            if(!this.operando2.matches("\\(?[0-9A-Fa-fhH\\-]+\\)?")){
                //this.simbolos.add(this.operando2);
                estado=true;
            }
        }
        return estado;
    }    
    
    public Integer longitudDeInstruccion(){
        Integer longitud=null;
        if ("ld".equals(this.operacion)||"LD".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&this.operando2.matches("[A-E]|H|L")){//LD r,r'
                longitud=1;
            }
            else if (this.operando1.matches("[A-E]|H|L")&&this.operando2.matches("[0-9a-fA-FhH\\-]*|\\w+")){//LD r,n
                longitud=2;
            }
            else if (this.operando1.matches("[A-E]|H|L")&&this.operando2.matches("HL")){//LD r,HL
                longitud=1;
            }
            else if (this.operando1.matches("[A-E]|H|L")&&this.operando2.matches("\\(IX\\+[0-9]*\\)")){//LD r,(IX+d)
                longitud=3;
            }
            else if (this.operando1.matches("[A-E]|H|L")&&this.operando2.matches("\\(IY\\+[0-9]*\\)")){//LD r,(IY+d)
                longitud=3;
            }
            else if (this.operando1.matches("\\(HL\\)")&&this.operando2.matches("[A-E]|H|L")){//LD (HL),r
                longitud=1;
            }
            else if (this.operando1.matches("\\(IX\\+[0-9]*\\)")&&this.operando2.matches("[A-E]|H|L")){//LD (IX+d),r
                longitud=3;
            }
            else if (this.operando1.matches("\\(IY\\+[0-9]*\\)")&&this.operando2.matches("[A-E]|H|L")){//LD (IY+d),r
                longitud=3;
            }
            else if (this.operando1.matches("\\(HL\\)")&&this.operando2.matches("[0-9a-fA-FhH\\-]*|\\w+")){//LD (HL),n
                longitud=2;
            }
            else if (this.operando1.matches("\\(IX\\+[0-9]*\\)")&&this.operando2.matches("[0-9a-fA-FhH\\-]*|\\w+")){//LD (IX+d),n
                longitud=4;
            }
            else if (this.operando1.matches("\\(IY\\+[0-9]*\\)")&&this.operando2.matches("([0-9a-fA-FhH\\-]*|\\w+)")){//LD (IY+d),n
                longitud=4;
            }
            else if (this.operando1.matches("A")&&this.operando2.matches("\\(BC\\)")){//LD A,(BC)
                longitud=1;
            }
            else if (this.operando1.matches("A")&&this.operando2.matches("\\(DE\\)")){//LD A,(DE)
                longitud=1;
            }
            else if (this.operando1.matches("A")&&this.operando2.matches("\\(([0-9a-fA-FhH\\-]*|\\w+)\\)")){//LD A,(nn)
                longitud=3;
            }
            else if (this.operando1.matches("\\(BC\\)")&&this.operando2.matches("A")){//LD (BC),A
                longitud=1;
            }
            else if (this.operando1.matches("\\(DE\\)")&&this.operando2.matches("A")){//LD (DE),A
                longitud=1;
            }
            else if (this.operando1.matches("\\(([0-9a-fA-FhH\\-]*|\\w+)\\)")&&this.operando2.matches("A")){//LD (nn),A
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
////////////////////////////////////////////////Primer página :'v
            else if(this.operando1.matches("BC|DE|HL|SP")&&this.operando2.matches("([0-9a-fA-FhH\\-]*|\\w+)")){//LD dd,nn              Carga de 16 bits
                longitud=3;
            }
            else if (this.operando1.matches("IX")&&this.operando2.matches("([0-9a-fA-FhH\\-]*|\\w+)")){//LD IX,nn
                longitud=4;
            }
            else if (this.operando1.matches("IY")&&this.operando2.matches("([0-9a-fA-FhH\\-]*|\\w+)")){//LD IY,nn
                longitud=4;
            }
            else if (this.operando1.matches("HL")&&this.operando2.matches("\\([([0-9a-fA-FhH\\-]*|\\w+)\\)")){//LD HL,(nn)
                longitud=3;
            }
            else if (this.operando1.matches("BC|DE|HL|SP")&&this.operando2.matches("\\(([0-9a-fA-FhH\\-]*|\\w+)\\)")){//LD dd,(nn)
                longitud=4;
            }
            else if (this.operando1.matches("IX")&&this.operando2.matches("\\(([0-9a-fA-FhH\\-]*|\\w+)\\")){//LD IX,(nn)
                longitud=4;
            }
            else if (this.operando1.matches("IY")&&this.operando2.matches("\\(([0-9a-fA-FhH\\-]*|\\w+)\\")){//LD IY,(nn)
                longitud=4;
            }
            else if (this.operando1.matches("\\(([0-9a-fA-FhH\\-]*|\\w+)\\)")&&this.operando2.matches("HL")){//LD (nn),HL
                longitud=3;
            }
            else if (this.operando1.matches("\\(([0-9a-fA-FhH\\-]*|\\w+)\\)")&&this.operando2.matches("BC|DE|HL|SP")){//LD (nn),dd
                longitud=4;
            }
            else if (this.operando1.matches("\\(([0-9a-fA-FhH\\-]*|\\w+)\\)")&&this.operando2.matches("IX")){//LD (nn),IX
                longitud=4;
            }
            else if (this.operando1.matches("\\(([0-9a-fA-FhH\\-]*|\\w+)\\)")&&this.operando2.matches("IY")){//LD (nn),IY
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
            if (this.operando1.matches("BC|DE|HL|AF")&&this.operando2==null){//PUSH qq
                longitud=1;
            }
            else if (this.operando1.matches("IX")&&this.operando2==null){//PUSH IX
                longitud=2;
            }
            else if (this.operando1.matches("IY")&&this.operando2==null){//PUSH IY
                longitud=2;
            }
        }
        else if ("pop".equals(this.operacion)||"POP".equals(this.operacion)){   ////16bits
	        if ((this.operando1.matches("BC|DE|HL|AF")&&this.operando2==null)){//POP qq
                longitud=1;
            }
            else if (this.operando1.matches("IX")&&this.operando2==null){//POP IX
                longitud=2;
            }
            else if (this.operando1.matches("IY")&&this.operando2==null){//POP IY
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
        else if(("exx".equals(this.operacion)||"EXX".equals(this.operacion))){
            if(this.operando1==null&&this.operando2==null){
                longitud=1;
            }
        }   
        else if ("ldi".equals(this.operacion)||"LDI".equals(this.operacion)){
            if(this.operando1==null&&this.operando2==null){//LDI
                longitud=2;
            }
        }
        else if ("ldir".equals(this.operacion)||"LDIR".equals(this.operacion)){
            if(this.operando1==null&&this.operando2==null){//LDIR
                longitud=2;
            }
        }
        else if ("ldd".equals(this.operacion)||"LDD".equals(this.operacion)){
            if(this.operando1==null&&this.operando2==null){//LDD
                longitud=2;
            }
        }
        else if ("lddr".equals(this.operacion)||"LDDR".equals(this.operacion)){
            if(this.operando1==null&&this.operando2==null){//LDDR
                longitud=2;
            }
        }
        else if ("cpi".equals(this.operacion)||"CPI".equals(this.operacion)){
            if(this.operando1==null&&this.operando2==null){//LDDR (DE),(HL)
                longitud=2;
            }
        }
        else if ("cpir".equals(this.operacion)||"CPIR".equals(this.operacion)){//Duda
            if(this.operando1==null&&this.operando2==null){//LDDR (DE),(HL)
                longitud=2;
            }
        }
        else if ("cpd".equals(this.operacion)||"CPD".equals(this.operacion)){//Duda
            if(this.operando1==null&&this.operando2==null){//LDDR (DE),(HL)
                longitud=2;
            }
        }
        else if ("cpdr".equals(this.operacion)||"CPDR".equals(this.operacion)){//Duda
            if(this.operando1==null&&this.operando2==null){//LDDR (DE),(HL)
                longitud=2;
            }
        }
//////////////////Tercera Hoja
        else if ("add".equals(this.operacion)||"ADD".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&this.operando2==null){//ADD r
                longitud=1;
            }
            else if(this.operando1.matches("[0-9a-fA-FhH]*")&&this.operando2==null){//ADD n
                longitud=2;
            }
            else if(this.operando1.matches("\\(HL\\)")&&this.operando2==null){//ADD (HL)
                longitud=2;
            }
            else if(this.operando1.matches("\\(IX\\+[0-9]*\\)")&&this.operando2==null){//ADD (IX+d)
                longitud=3;
            }
            else if(this.operando1.matches("\\(IY\\+[0-9]*\\)")&&this.operando2==null){//ADD (IY+d)
                longitud=3;
            }
            else if(this.operando1.matches("\\(HL\\)")&&this.operando2.matches("BC|DE|HL|SP")){//ADD HL,ss
                longitud=1;
            }
            else if(this.operando1.matches("IX")&&this.operando2.matches("BC|DE|IX|SP")){//ADD (IY+d)
                longitud=2;
            }
            else if(this.operando1.matches("IY")&&this.operando2.matches("BC|DE|IY|SP")){//ADD (IY+d)
                longitud=2;
            }
        }
        else if ("adc".equals(this.operacion)||"ADC".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&this.operando2==null){//ADC r
                longitud=1;
            }
            else if(this.operando1.matches("[0-9A-Fa-fhH]*")&&this.operando2==null){//ADC n
                longitud=2;
            }
            else if(this.operando1.matches("\\(HL\\)")&&this.operando2==null){//ADC (HL)
                longitud=1;
            }
            else if(this.operando1.matches("\\(IX\\+[0-9]*\\)")&&this.operando2==null){//ADC (IX+d)
                longitud=3;
            }
             else if(this.operando1.matches("\\(IY\\+[0-9]*\\)")&&this.operando2==null){//ADC (IY+d)
                longitud=3;
            }
            else if(this.operando1.matches("\\(HL\\)")&&this.operando2.matches("BC|DE|HL|CP|")){//ADC HL,SS
                longitud=2;
            }
        }
        else if ("sub".equals(this.operacion)||"SUB".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&this.operando2==null){//SUB r
                longitud=1;
            }
            else if(this.operando1.matches("[0-9A-Fa-fhH]*")&&this.operando2==null){//SUB n
                longitud=2;
            }
            else if(this.operando1.matches("\\(HL\\)")&&this.operando2==null){//SUB (HL)
                longitud=1;
            }
            else if(this.operando1.matches("\\(IX\\+[0-9]*\\)")&&this.operando2==null){//SUB (IX+d)
                longitud=3;
            }
             else if(this.operando1.matches("\\(IY\\+[0-9]*\\)")&&this.operando2==null){//SUB (IY+d)
                longitud=3;
            }
        }        
        else if ("sbc".equals(this.operacion)||"SBC".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&this.operando2==null){//SBC r
                longitud=1;
            }
            else if(this.operando1.matches("[0-9A-Fa-fhH]*")&&this.operando2==null){//SBC n
                longitud=2;
            }
            else if(this.operando1.matches("\\(HL\\)")&&this.operando2==null){//SBC (HL)
                longitud=1;
            }
            else if(this.operando1.matches("\\(IX\\+[0-9]*\\)")&&this.operando2==null){//SBC (IX+d)
                longitud=3;
            }
             else if(this.operando1.matches("\\(IY\\+[0-9]*\\)")&&this.operando2==null){//SBC (IY+d)
                longitud=3;
            }
            else if(this.operando1.matches("\\(HL\\)")&&this.operando2.matches("BC|DE|HL|CP|")){//SBC HL,SS
                longitud=2;
            }
        }
        else if ("and".equals(this.operacion)||"AND".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&this.operando2==null){//AND r
                longitud=1;
            }
            else if(this.operando1.matches("[0-9A-Fa-fhH]*")&&this.operando2==null){//AND n
                longitud=2;
            }
            else if(this.operando1.matches("\\(HL\\)")&&this.operando2==null){//AND (HL)
                longitud=1;
            }
            else if(this.operando1.matches("\\(IX\\+[0-9]*\\)")&&this.operando2==null){//AND (IX+d)
                longitud=3;
            }
             else if(this.operando1.matches("\\(IY\\+[0-9]*\\)")&&this.operando2==null){//AND (IY+d)
                longitud=3;
            }
        }
        else if ("or".equals(this.operacion)||"OR".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&this.operando2==null){//OR r
                longitud=1;
            }
            else if(this.operando1.matches("[0-9A-Fa-fhH]*")&&this.operando2==null){//OR n
                longitud=2;
            }
            else if(this.operando1.matches("\\(HL\\)")&&this.operando2==null){//OR (HL)
                longitud=1;
            }
            else if(this.operando1.matches("\\(IX\\+[0-9]*\\)")&&this.operando2==null){//OR (IX+d)
                longitud=3;
            }
             else if(this.operando1.matches("\\(IY\\+[0-9]*\\)")&&this.operando2==null){//OR (IY+d)
                longitud=3;
            }
        }
        else if ("xor".equals(this.operacion)||"XOR".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&this.operando2==null){//XOR r
                longitud=1;
            }
            else if(this.operando1.matches("[0-9A-Fa-fhH]*")&&this.operando2==null){//XOR n
                longitud=2;
            }
            else if(this.operando1.matches("\\(HL\\)")&&this.operando2==null){//XOR (HL)
                longitud=1;
            }
            else if(this.operando1.matches("\\(IX\\+[0-9]*\\)")&&this.operando2==null){//XOR (IX+d)
                longitud=3;
            }
             else if(this.operando1.matches("\\(IY\\+[0-9]*\\)")&&this.operando2==null){//XOR (IY+d)
                longitud=3;
            }
        }
        else if ("cp".equals(this.operacion)||"CP".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&this.operando2==null){//CP r
                longitud=1;
            }
            else if(this.operando1.matches("[0-9A-Fa-fhH]*")&&this.operando2==null){//CP n
                longitud=2;
            }
            else if(this.operando1.matches("\\(HL\\)")&&this.operando2==null){//CP (HL)
                longitud=1;
            }
            else if(this.operando1.matches("\\(IX\\+[0-9]*\\)")&&this.operando2==null){//CP (IX+d)
                longitud=3;
            }
             else if(this.operando1.matches("\\(IY\\+[0-9]*\\)")&&this.operando2==null){//ADC (IY+d)
                longitud=3;
            }
        }
        else if ("inc".equals(this.operacion)||"INC".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&this.operando2==null){//INC r
                longitud=1;
            }
            else if(this.operando1.matches("\\(HL\\)")&&this.operando2==null){//INC (HL)
                longitud=1;
            }
            else if(this.operando1.matches("\\(IX\\+[0-9]+\\)")&&this.operando2==null){//INC (IX+d)
                longitud=3;
            }
             else if(this.operando1.matches("\\(IY\\+[0-9]+\\)")&&this.operando2==null){//INC (IY+d)
                longitud=3;
            }
            else if(this.operando1.matches("BC|DE|HL|CP")&&this.operando2==null){//INC ss
                longitud=1;
            }
            else if(this.operando1.matches("IX")&&this.operando2==null){//INC IX
                longitud=2;
            }
             else if(this.operando1.matches("IY")&&this.operando2==null){//INC iy
                longitud=2;
            }
        }
        else if ("dec".equals(this.operacion)||"DEC".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&this.operando2==null){//DEC r
                longitud=1;
            }
            else if(this.operando1.matches("\\(HL\\)")&&this.operando2==null){//DEC (HL)
                longitud=1;
            }
            else if(this.operando1.matches("\\(IX\\+[0-9]+\\)")&&this.operando2==null){//DEC (IX+d)
                longitud=3;
            }
             else if(this.operando1.matches("\\(IY\\+[0-9]+\\)")&&this.operando2==null){//DEC (IY+d)
                longitud=3;
            }
            else if(this.operando1.matches("BC|DE|HL|CP")&&this.operando2==null){//INC ss
                longitud=1;
            }
            else if(this.operando1.matches("IX")&&this.operando2==null){//INC IX
                longitud=2;
            }
             else if(this.operando1.matches("IY")&&this.operando2==null){//INC iy
                longitud=2;
            }
        }
/////////////// CUARTA Y SEXTA HOJA

        else if ("daa".equals(this.operacion)||"DAA".equals(this.operacion)){
            if(this.operando1==null&&this.operando2==null){//DAA
                longitud=1;
            }
        }
        else if ("cpl".equals(this.operacion)||"CPL".equals(this.operacion)){
            if(this.operando1==null&&this.operando2==null){//CPL
                longitud=1;
            }
        }
        else if ("neg".equals(this.operacion)||"NEG".equals(this.operacion)){
            if(this.operando1==null&&this.operando2==null){//NEG
                longitud=2;
            }
        }
        else if ("ccf".equals(this.operacion)||"CCF".equals(this.operacion)){
            if(this.operando1==null&&this.operando2==null){//CCF
                longitud=1;
            }
        }
        else if ("scf".equals(this.operacion)||"SCF".equals(this.operacion)){
            if(this.operando1==null&&this.operando2==null){//SCF
                longitud=1;
            }
        }
        else if ("nop".equals(this.operacion)||"NOP".equals(this.operacion)){
            if(this.operando1==null&&this.operando2==null){//NOP
                longitud=1;
            }
        }
        else if ("halt".equals(this.operacion)||"HALT".equals(this.operacion)){
            longitud=1;
        }
        else if ("di".equals(this.operacion)||"DI".equals(this.operacion)){
            if(this.operando1==null&&this.operando2==null){//DI
                longitud=1;
            }
        }
        else if ("ei".equals(this.operacion)||"EI".equals(this.operacion)){
            if(this.operando1==null&&this.operando2==null){//EI
                longitud=1;
            }
        }

        else if ("im".equals(this.operacion)||"IM".equals(this.operacion)){
            if("0".equals(this.operando1)&&this.operando2==null){//IM
                longitud=2;
            }
            else if("1".equals(this.operando1)&&this.operando2==null){//MI
                longitud=2;
            }
            else if("2".equals(this.operando1)&&this.operando2==null){//MI
                longitud=2;
            }
        }
/////////////////// QUINTA HOJA

        else if ("rlca".equals(this.operacion)||"RLCA".equals(this.operacion)){
            if(this.operando1==null&&this.operando2==null){//RLCA
                longitud=1;
            }
        }
        else if ("rla".equals(this.operacion)||"RLA".equals(this.operacion)){
            if(this.operando1==null&&this.operando2==null){//RLA
                longitud=1;
            }
        }
        else if ("rrca".equals(this.operacion)||"RRCA".equals(this.operacion)){
            if(this.operando1==null&&this.operando2==null){//RRCA
                longitud=1;
            }
        }
        else if ("rra".equals(this.operacion)||"RRA".equals(this.operacion)){
            if(this.operando1==null&&this.operando2==null){//RRA
                longitud=1;
            }
        }
        else if ("rlc".equals(this.operacion)||"RLC".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&this.operando2==null){//RLC r
                longitud=2;
            }
            if(this.operando1.matches("\\(HL\\)")&&this.operando2==null){//RLC (HL)
                longitud=2;
            }
            if(this.operando1.matches("\\(IX\\+[0-9]*\\)")&&this.operando2==null){//RLC (IX + d)
                longitud=4;
            }
            if(this.operando1.matches("\\(IY\\+[0-9]*\\)")&&this.operando2==null){//RLC (Iy + d)
                longitud=4;
            }
        }
        else if ("rl".equals(this.operacion)||"RL".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&this.operando2==null){//RL r
                longitud=2;
            }
            else if(this.operando1.matches("\\(HL\\)")&&this.operando2==null){//RL (HL)
                longitud=2;
            }
            else if(this.operando1.matches("\\(IX\\+[0-9]*\\)")&&this.operando2==null){//RL (IX + d)
                longitud=4;
            }
            else if(this.operando1.matches("\\(IY\\+[0-9]*\\)")&&this.operando2==null){//RL (Iy + d)
                longitud=4;
            }
        }
        else if ("rrc".equals(this.operacion)||"RRC".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&this.operando2==null){//RRC r
                longitud=2;
            }
            else if(this.operando1.matches("\\(HL\\)")&&this.operando2==null){//RRC (HL)
                longitud=2;
            }
            else if(this.operando1.matches("\\(IX\\+[0-9]*\\)")&&this.operando2==null){//RRC (IX + d)
                longitud=4;
            }
            else if(this.operando1.matches("\\(IY\\+[0-9]*\\)")&&this.operando2==null){//RRC (Iy + d)
                longitud=4;
            }
        }
        else if ("rr".equals(this.operacion)||"RR".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&this.operando2==null){//RR r
                longitud=2;
            }
            else if(this.operando1.matches("\\(HL\\)")&&this.operando2==null){//RR (HL)
                longitud=2;
            }
            else if(this.operando1.matches("\\(IX\\+[0-9]*\\)")&&this.operando2==null){//RR (IX + d)
                longitud=4;
            }
            else if(this.operando1.matches("\\(IY\\+[0-9]*\\)")&&this.operando2==null){//RR (Iy + d)
                longitud=4;
            }
        }
        else if ("sla".equals(this.operacion)||"SLA".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&this.operando2==null){//SLA r
                longitud=2;
            }
            else if(this.operando1.matches("\\(HL\\)")&&this.operando2==null){//SLA (HL)
                longitud=2;
            }
            else if(this.operando1.matches("\\(IX\\+[0-9]*\\)")&&this.operando2==null){//SLA (IX + d)
                longitud=4;
            }
            else if(this.operando1.matches("\\(IY\\+[0-9]*\\)")&&this.operando2==null){//SLA (Iy + d)
                longitud=4;
            }
        }
        else if ("sra".equals(this.operacion)||"SRA".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&this.operando2==null){//SRA r
                longitud=2;
            }
            else if(this.operando1.matches("\\(HL\\)")&&this.operando2==null){//SRA (HL)
                longitud=2;
            }
            else if(this.operando1.matches("\\(IX\\+[0-9]*\\)")&&this.operando2==null){//SRA (IX + d)
                longitud=4;
            }
            else if(this.operando1.matches("\\(IY\\+[0-9]*\\)")&&this.operando2==null){//SRA (Iy + d)
                longitud=4;
            }
        }
        else if ("srl".equals(this.operacion)||"SRL".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&this.operando2==null){//SRL r
                longitud=2;
            }
            else if(this.operando1.matches("\\(HL\\)")&&this.operando2==null){//SRL (HL)
                longitud=2;
            }
            else if(this.operando1.matches("\\(IX\\+[0-9]*\\)")&&this.operando2==null){//SRL (IX + d)
                longitud=4;
            }
            else if(this.operando1.matches("\\(IY\\+[0-9]*\\)")&&this.operando2==null){//SRL (Iy + d)
                longitud=4;
            }
        }
        else if ("rld".equals(this.operacion)||"RLD".equals(this.operacion)){
            if(this.operando1==null&&this.operando2==null){//RLD
                longitud=2;
            }
        }
        else if ("rrd".equals(this.operacion)||"RRD".equals(this.operacion)){
            if(this.operando1==null&&this.operando2==null){//RRD
                longitud=2;
            }
        }
////////////// SEPTIMA HOJA
        else if("bit".equals(this.operacion)||"BIT".equals(this.operacion)){
            if(this.operando1.matches("[0-7]")&&this.operando2.matches("[A-E]|H|L")){//BIT b,r
                longitud=2;
            }
            else if(this.operando1.matches("[0-7]")&&this.operando2.matches("\\(HL\\)")){//BIT b,(HL)
                longitud=2;
            }
            else if(this.operando1.matches("[0-7]")&&this.operando2.matches("\\(IX\\+[0-9]*\\)")){//BIT b,(IX + d)
                longitud=4;
            }
            else if(this.operando1.matches("[0-7]")&&this.operando2.matches("\\(IY\\+[0-9]*\\)")){//BIT b,(IY + d)
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
            else if(this.operando1.matches("[0-7]")&&this.operando2.matches("\\(IX\\+[0-9]*\\)")){//SET b,(IX + d)
                longitud=4;
            }
            else if(this.operando1.matches("[0-7]")&&this.operando2.matches("\\(IY\\+[0-9]*\\)")){//SET b,(IY + d)
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
            else if(this.operando1.matches("[0-7]")&&this.operando2.matches("\\(IX\\+[0-9]*\\)")){//RES b,(IX + d)
                longitud=4;
            }
            else if(this.operando1.matches("[0-7]")&&this.operando2.matches("\\(IY\\+[0-9]*\\)")){//RES b,(IY + d)
                longitud=4;
            }
        }
///////////// OCTAVA HOJA
        else if("jp".equals(this.operacion)||"JP".equals(this.operacion)){
            if (this.operando1.matches("([0-9a-fA-FhH]+)|\\w+")&&this.operando2==null){//JP nn
                longitud=3;
            }
            else if (this.operando1.matches("NZ|Z|NC|C|PO|PE|P|M")&&this.operando2.matches("([0-9a-fA-FhH]+)|\\w+")){//JP cc,nn,
                longitud=3;
            }
            else if (this.operando1.matches("\\(HL\\)")&&this.operando2==null){//JP (HL)
                longitud=1;
            }
            else if (this.operando1.matches("\\(IX\\)")&&this.operando2==null){//JP (IX)
                longitud=2;
            }
            else if (this.operando1.matches("\\(IY\\)")&&this.operando2==null){//JP (IY)
                longitud=2;
            }
        }
        else if("jr".equals(this.operacion)||"JR".equals(this.operacion)){
            if (this.operando1.matches("[\\-0-9hH]+")&&this.operando2==null){//JR e
                longitud=2;
            }
            else if (this.operando1.matches("C")&&this.operando2.matches("[\\-0-9hH]+")){//JP C,e
                longitud=2;
            }
            else if (this.operando1.matches("NC")&&this.operando2.matches("[\\-0-9hH]+")){//JP NC,e
                longitud=2;
            }
            else if (this.operando1.matches("Z")&&this.operando2.matches("[\\-0-9hH]+")){//JP Z,e
                longitud=2;
            }
            else if (this.operando1.matches("ZC")&&this.operando2.matches("[\\-0-9hH]+")){//JP NZ,e
                longitud=2;
            }
            else if (this.operando1.matches("NC")&&this.operando2.matches("[\\-0-9hH]+")){//JP NC,e,
                longitud=2;
            }
        }
        else if("djn".equals(this.operacion)||"DJN".equals(this.operacion)){
            if (this.operando1.matches("Z")&&this.operando2.matches("[\\-0-9hH]+")){//DJN Z,e
                longitud=2;
            }
        }
//////////////// NOVENA HOJA

        else if("call".equals(this.operacion)||"CALL".equals(this.operacion)){
            if (this.operando1.matches("[0-9a-fA-FhH]+")&&this.operando2==null){//CALL nn
                longitud=3;
            }
            else if (this.operando1.matches("NZ|Z|NC|C|PO|PE|P|M")&&this.operando2.matches("[0-9a-fA-FhH]+")){//CALL nn,nn
                longitud=3;
            }
        }
        else if("ret".equals(this.operacion)||"RET".equals(this.operacion)){
            if (this.operando1==null&&this.operando2==null){//RET
                longitud=1;
            }
            else if (this.operando1.matches("NZ|Z|NC|C|PO|PE|P|M")&&this.operando2==null){//RET cc
                longitud=1;
            }
        }
        else if("reti".equals(this.operacion)||"RETI".equals(this.operacion)){
            if (this.operando1==null&&this.operando2==null){//RETI
                longitud=2;
            }
        }
        else if("retn".equals(this.operacion)||"RETN".equals(this.operacion)){
            if (this.operando1==null&&this.operando2==null){//RETN
                longitud=2;
            }
        }
        else if("rst".equals(this.operacion)||"RST".equals(this.operacion)){
            if (this.operando1.matches("[0-9a-fA-FhH]+")&&this.operando2==null){//RST p
                longitud=1;
            }
        }
//////////// DECIMA HOJA

        else if("in".equals(this.operacion)||"IN".equals(this.operacion)){
            if (this.operando1.matches("A")&&this.operando2.matches("\\([0-9a-fA-FhH]*\\)")){//IN A, (n)
                longitud=2;
            }
            else if (this.operando1.matches("[A-E]|H|L")&&this.operando2.matches("\\(C\\)")){//IN r, (C)
                longitud=2;
            }
        }
        else if("ini".equals(this.operacion)||"INI".equals(this.operacion)){
            if (this.operando1==null&&this.operando2==null){//INI
                longitud=2;
            }
        }
        else if("inir".equals(this.operacion)||"INIR".equals(this.operacion)){
            if (this.operando1==null&&this.operando2==null){//INIR
                longitud=2;
            }
        }
        else if("ind".equals(this.operacion)||"IND".equals(this.operacion)){
            if (this.operando1==null&&this.operando2==null){//IND
                longitud=2;
            }
        }
        else if("indr".equals(this.operacion)||"INDR".equals(this.operacion)){
            if (this.operando1==null&&this.operando2==null){//INDR
                longitud=2;
            }
        }
        else if("out".equals(this.operacion)||"OUT".equals(this.operacion)){
            if (this.operando1.matches("\\([0-9a-fA-FhH]*\\)")&&this.operando2.matches("A")){//OUT A, (n)
                longitud=2;
            }
            else if (this.operando1.matches("\\(C\\)")&&this.operando2.matches("[A-E]|H|L")){//OUT r, (C)
                longitud=2;
            }
        }
        else if("outi".equals(this.operacion)||"OUTI".equals(this.operacion)){
            if (this.operando1==null&&this.operando2==null){//outI
                longitud=2;
            }
        }
        else if("otir".equals(this.operacion)||"OTIR".equals(this.operacion)){
            if (this.operando1==null&&this.operando2==null){//OTIR
                longitud=2;
            }
        }
        else if("outd".equals(this.operacion)||"OUTD".equals(this.operacion)){
            if (this.operando1==null&&this.operando2==null){//OUTD
                longitud=2;
            }
        }
        else if("otdr".equals(this.operacion)||"OTDR".equals(this.operacion)){
            if (this.operando1==null&&this.operando2==null){//OTDR
                longitud=2;
            }
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
        String cadenaBin = num.toString(num,2);
        return cadenaBin;
    }
    
    public String getCodigoObjeto(String valorOp1,String valorOp2){
        String instruccion="";
        if ("ld".equals(this.operacion)||"LD".equals(this.operacion)){
            if(this.operando1.matches("[A-E]|H|L")&&this.operando2.matches("[A-E]|H|L")){//LD r,r'
                /*if(this.operando1.equals("A")&&this.operando2.equals("A"))
                    instruccion="7F";
                else if(this.operando1.equals("A")&&this.operando2.equals("B"))
                    instruccion="78";*/
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
            if(this.operando1.matches("A")&&this.operando2.matches("(\\([0-9A-Fa-fhHbB]+\\))|(\\(\\w\\))")){//LD r,r'
            instruccion="00111010";
            String n;
                if(this.operando2.matches("\\([0-9A-Fa-fhHbB]+\\)")){
                    n = operando2.replaceAll("\\(", "");
                    n = n.replaceAll("\\)","");
                    n = this.toBinary(n);
                    System.out.println("1_"+n);
                    while(n.length()<16){// en caso de que el número sea menor a FF, pone ceros a la izquierda
                        n="0"+n;    
                    }
                    System.out.println("2_"+n);
                    n=n.substring(n.length()-16, n.length());//en caso de que sea mayor, le quita números a la izquierda
                    System.out.println("3_"+n);
                    instruccion=instruccion+n.substring(8);
                    System.out.println(instruccion);
                    instruccion=instruccion+n.substring(0, 8);
                    System.out.println(instruccion);
                }
                else if(this.operando2==null&&valorOp2!=null){
                    n = valorOp2.replaceAll("\\(", "");
                    n = n.replaceAll("\\)","");
                    n = this.toBinary(n);
                    while(n.length()<16){// en caso de que el número sea menor a FF, pone ceros a la izquierda
                        n="0"+n;
                    }
                    n=n.substring(n.length()-16, n.length()-1);//en caso de que sea mayor, le quita números a la izquierda
                    instruccion=instruccion+n.substring(8,15);
                    instruccion=instruccion+n.substring(0, 7);
                }
            }
        }
        int insDec = Integer.parseInt(instruccion, 2);//Pasa de binario a decimal
        return Integer.toHexString(insDec);//pasa de decimal a binario
    }
}