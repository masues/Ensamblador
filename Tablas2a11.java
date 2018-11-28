else if ("ld".equals(this.operacion)||"LD".equals(this.operacion)){
  if(this.operando1.matches("BC|DE|HL|SP")&&this.operando2.matches("([0-9a-fA-FhHbB\\-]+)|\\w+")){//LD dd,nn
    instruccion="00";
    switch(this.operando1.charAt(0)){//r
      case 'BC':
      instruccion=instruccion+"00";
      break;
      case 'DE':
      instruccion=instruccion+"01";
      break;
      case 'HL':
      instruccion=instruccion+"10";
      break;
      case 'SP':
      instruccion=instruccion+"11";
      break;
    }
    instruccion=instruccion+"0";
    String n;
    if(valorOp2!=null){                                   //Si el valor de entrada en entero
      n = valorOp2.replaceAll("", "");
      n = n.replaceAll("","");
      n = this.toBinary(n);
      while(n.length()<16){                             // en caso de que el número sea menor a 16, pone ceros a la izquierda
        n="0"+n;
      }
      n=n.substring(n.length()-16, n.length());         //en caso de que sea mayor, le quita números a la izquierda
      instruccion=instruccion+n.substring(8);
      instruccion=instruccion+n.substring(0, 8);
    }
    else if(this.operando2.matches("[0-9a-fA-FhHbB]")){   //Si el valor de entrada en hex o bin
      n = operando1.replaceAll("\\(", "");
      n = n.replaceAll("\\)","");
      n = this.toBinary(n);
      while(n.length()<16){                            // en caso de que el número sea menor a 16 bits, pone ceros a la izquierda
        n="0"+n;
      }
      n=n.substring(n.length()-16, n.length());       //en caso de que sea mayor, le quita números a la izquierda
      instruccion=instruccion+n.substring(8);
      instruccion=instruccion+n.substring(0, 8);
    }
  }
  else if(this.operando1.matches("IX")&&this.operando2.matches("([0-9a-fA-FhHbB\\-]+)|\\w+")){//LD IX,nn
    instruccion="1101110100100001";
    String n;
    if(valorOp2!=null){                                   //Si el valor de entrada en entero
      n = valorOp2.replaceAll("", "");
      n = n.replaceAll("","");
      n = this.toBinary(n);
      while(n.length()<16){                             // en caso de que el número sea menor a 16, pone ceros a la izquierda
        n="0"+n;
      }
      n=n.substring(n.length()-16, n.length());         //en caso de que sea mayor, le quita números a la izquierda
      instruccion=instruccion+n.substring(8);
      instruccion=instruccion+n.substring(0, 8);
    }
    else if(this.operando2.matches("[0-9a-fA-FhHbB]")){   //Si el valor de entrada en hex o bin
      n = operando1.replaceAll("", "");
      n = n.replaceAll("","");
      n = this.toBinary(n);
      while(n.length()<16){                            // en caso de que el número sea menor a 16 bits, pone ceros a la izquierda
        n="0"+n;
      }
      n=n.substring(n.length()-16, n.length());       //en caso de que sea mayor, le quita números a la izquierda
      instruccion=instruccion+n.substring(8);
      instruccion=instruccion+n.substring(0, 8);
    }
  }
  else if(this.operando1.matches("IY")&&this.operando2.matches("([0-9a-fA-FhHbB\\-]+)|\\w+")){  //LD IY,nn
    instruccion="1111110100100101";
    String n;
    if(valorOp2!=null){                                   //Si el valor de entrada en entero
      n = valorOp2.replaceAll("", "");
      n = n.replaceAll("","");
      n = this.toBinary(n);
      while(n.length()<16){                             // en caso de que el número sea menor a 16, pone ceros a la izquierda
        n="0"+n;
      }
      n=n.substring(n.length()-16, n.length());         //en caso de que sea mayor, le quita números a la izquierda
      instruccion=instruccion+n.substring(8);
      instruccion=instruccion+n.substring(0, 8);
    }
    else if(this.operando2.matches("[0-9a-fA-FhHbB]")){   //Si el valor de entrada en hex o bin
      n = operando1.replaceAll("","");
      n = n.replaceAll("","");
      n = this.toBinary(n);
      while(n.length()<16){                            // en caso de que el número sea menor a 16 bits, pone ceros a la izquierda
        n="0"+n;
      }
      n=n.substring(n.length()-16, n.length());       //en caso de que sea mayor, le quita números a la izquierda
      instruccion=instruccion+n.substring(8);
      instruccion=instruccion+n.substring(0, 8);
    }
  }
  else if (this.operando1.matches("HL")&&this.operando2.matches("(\\([0-9a-fA-FhHbB\\-]+\\))|(\\(\\w\\))")){//LD HL,(nn)
    instruccion="00101010";
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
    else if(this.operando2.matches("\\([0-9a-fA-FhHbB\\-]+\\)")){ //Si el valor de entrada en hex o bin
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
  else if (this.operando1.matches("BC|DE|HL|SP")&&this.operando2.matches("(\\([0-9a-fA-FhHbB\\-]+\\))|(\\(\\w\\))")){//LD dd,(nn)
    instruccion="1110110101";
    switch(this.operando1.charAt(0)){//r
      case 'BC':
      instruccion=instruccion+"00";
      break;
      case 'DE':
      instruccion=instruccion+"01";
      break;
      case 'HL':
      instruccion=instruccion+"10";
      break;
      case 'SP':
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
    else if(this.operando2.matches("\\([0-9a-fA-FhHbB\\-]+\\)")){ //Si el valor de entrada en hex o bin
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
  else if (this.operando1.matches("IX")&&this.operando2.matches("(\\([0-9a-fA-FhHbB\\-]+\\))|(\\(\\w\\))")){//LD IX,(nn)
    instruccion="1101110100101010";
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
    else if(this.operando2.matches("\\([0-9a-fA-FhHbB\\-]+\\)")){ //Si el valor de entrada en hex o bin
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
  else if (this.operando1.matches("IY")&&this.operando2.matches("(\\([0-9a-fA-FhHbB\\-]+\\))|(\\(\\w\\))")){//LD IY,(nn)
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
    else if(this.operando2.matches("\\([0-9a-fA-FhHbB\\-]+\\)")){ //Si el valor de entrada en hex o bin
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
  else if (this.operando1.matches("(\\([0-9a-fA-FhHbB\\-]+\\))|(\\(\\w\\))")&&this.operando2.matches("HL")){//LD (nn),HL
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
    else if(this.operando1.matches("\\([0-9a-fA-FhHbB\\-]+\\)")){ //Si el valor de entrada en hex o bin
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
  else if (this.operando1.matches("(\\([0-9a-fA-FhHbB\\-]+\\))|(\\(\\w\\))")&&this.operando2.matches("BC|DE|HL|SP")){//LD (nn),dd
    instruccion="1110110101";
    switch(this.operando2.charAt(0)){               //dd
      case 'BC':
      instruccion=instruccion+"00";
      break;
      case 'DE':
      instruccion=instruccion+"01";
      break;
      case 'HL':
      instruccion=instruccion+"10";
      break;
      case 'SP':
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
    else if(this.operando1.matches("\\([0-9a-fA-FhHbB\\-]+\\)")){ //Si el valor de entrada en hex o bin
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
  else if (this.operando1.matches("(\\([0-9a-fA-FhHbB\\-]+\\))|(\\(\\w\\))")&&this.operando2.matches("IX")){//LD (nn),IX
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
    else if(this.operando1.matches("\\([0-9a-fA-FhHbB\\-]+\\)")){ //Si el valor de entrada en hex o bin
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
  else if (this.operando1.matches("(\\([0-9a-fA-FhHbB\\-]+\\))|(\\(\\w\\))")&&this.operando2.matches("IY")){//LD (nn),IY
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
    else if(this.operando1.matches("\\([0-9a-fA-FhHbB\\-]+\\)")){ //Si el valor de entrada en hex o bin
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
  else if (this.operando1.matches("SP")&&this.operando2.matches("HL")){//LD SP,HL
    instruccion="11111001";
  }
  else if (this.operando1.matches("SP")&&this.operando2.matches("IX")){//LD SP,IX
    instruccion="1101110111111001";
  }
  else if (this.operando1.matches("SP")&&this.operando2.matches("IY")){//LD SP,IY
    instruccion="1111110111111001";
  }
  else if ("push".equals(this.operacion)||"PUSH".equals(this.operacion)){
    if(this.operando1.matches("BC|DE|HL|AF")&&this.operando2==null){//PUSH qq
      instruccion="11";
      switch(this.operando1.charAt(0)){
        case 'BC':
        case 'bc':
        instruccion=instruccion+"00";
        break;
        case 'DE':
        case 'de':
        instruccion=instruccion+"01";
        break;
        case 'HL':
        case 'hl':
        instruccion=instruccion+"10";
        break;
        case 'AF':
        case 'af':
        instruccion=instruccion+"11";
        break;
      }
      instruccion=instruccion+"0101";
    }
    else if(this.operando1.matches("IX")&&this.operando2==null){//PUSH IX
      instruccion="11011101"+"11100101";
    }
    else if(this.operando1.matches("IY")&&this.operando2==null){//PUSH IY
      instruccion="11111101"+"11100101";
    }
  }
  else if ("pop".equals(this.operacion)||"POP".equals(this.operacion)){
    if(this.operando1.matches("BC|DE|HL|AF")&&this.operando2==null){//POP qq
      instruccion="11";
      switch(this.operando1.charAt(0)){
        case 'BC':
        case 'bc':
        instruccion=instruccion+"00";
        break;
        case 'DE':
        case 'de':
        instruccion=instruccion+"01";
        break;
        case 'HL':
        case 'hl':
        instruccion=instruccion+"10";
        break;
        case 'AF':
        case 'af':
        instruccion=instruccion+"11";
        break;
      }
      instruccion=instruccion+"0001";
    }
    else if(this.operando1.matches("IX")&&this.operando2==null){//POP IX
      instruccion="11011101"+"11100001";
    }
    else if(this.operando1.matches("IY")&&this.operando2==null){//POP IY
      instruccion="11111101"+"11100001";
    }
  }
  ////////////////SEGUNDA TABLA
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
  ////////////////TERCERA TABLA
  else if ("add".equals(this.operacion)||"ADD".equals(this.operacion)){
    if(this.operando1.matches("[A-E]|H|L")&&this.operando2==null){//ADD r
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
    else if(this.operando1.matches("[0-9a-fA-FhHbB\\+\\-]+")&&this.operando2==null){//ADD n
      instruccion="11000110";
      String n;
      if(valorOp1!=null){
        n = this.toBinary(valorOp1);
        while(n.length()<8){// en caso de que el n�mero sea menor a 8, pone ceros a la izquierda
          n="0"+n;
        }
        n=n.substring(n.length()-8, n.length());//en caso de que sea mayor, le quita n�meros a la izquierda
        instruccion=instruccion+n;
      }
      else if(this.operando1.matches("[0-9a-fA-FhHbB\\-]+")){
        n = this.toBinary(this.operando1);
        while(n.length()<8){// en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
          n="0"+n;
        }
        n=n.substring(n.length()-8, n.length());//en caso de que sea mayor, le quita n�meros a la izquierda
        instruccion=instruccion+n;
      }
      else if(this.operando1.matches("\\(HL\\)")&&this.operando2==null){//ADD (HL)
        instruccion="10000110";
      }
      else if(this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\)")&&this.operando2==null){//ADD (IX+d)
        instruccion="11011101"+"10000110";
        String d = this.operando1.replaceAll("\\(IX", "");//quita la parte (IX
        d = d.replaceAll("\\)", "");//quita la parte ). Al final queda +-d
        d = this.toBinary(d);
        while(d.length()<8){// en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
          d="0"+d;
        }
        d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita n�meros a la izquierda
        instruccion=instruccion+d;
      }
      else if(this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&this.operando2==null){//ADD (IY+d)
        instruccion="11111101"+"10000110";
        String d = this.operando1.replaceAll("\\(IY", "");//quita la parte (IY
        d = d.replaceAll("\\)", "");//quita la parte ). Al final queda +-d
        d = this.toBinary(d);
        while(d.length()<8){// en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
          d="0"+d;
        }
        d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita n�meros a la izquierda
        instruccion=instruccion+d;
      }
      else if(this.operando1.matches("HL")&&this.operando2.matches("BC|DE|HL|SP")){//ADD HL,ss     Aritm�tico de 16 bits
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
    if(this.operando1.matches("[A-E]|H|L")&&this.operando2==null){//ADC r
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
    else if(this.operando1.matches("[0-9A-Fa-fhHbB\\+\\-]+")&&this.operando2==null){//ADC n
      instruccion="11001110";
      String n;
      if(valorOp1!=null){
        n = this.toBinary(valorOp1);
        while(n.length()<8){// en caso de que el n�mero sea menor a 8, pone ceros a la izquierda
          n="0"+n;
        }
        n=n.substring(n.length()-8, n.length());//en caso de que sea mayor, le quita n�meros a la izquierda
        instruccion=instruccion+n;
      }
      else if(this.operando1.matches("[0-9a-fA-FhHbB\\-]+")){
        n = this.toBinary(this.operando1);
        while(n.length()<8){// en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
          n="0"+n;
        }
        n=n.substring(n.length()-8, n.length());//en caso de que sea mayor, le quita n�meros a la izquierda
        instruccion=instruccion+n;
      }
      else if(this.operando1.matches("\\(HL\\)")&&this.operando2==null){//ADC (HL)
        instruccion="10001110";
      }
      else if(this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\)")&&this.operando2==null){//ADC (IX+d)
        instruccion="11011101"+"10001110";
        String d = this.operando1.replaceAll("\\(IX", "");//quita la parte (IX
        d = d.replaceAll("\\)", "");//quita la parte ). Al final queda +-d
        d = this.toBinary(d);
        while(d.length()<8){// en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
          d="0"+d;
        }
        d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita n�meros a la izquierda
        instruccion=instruccion+d;
      }
      else if(this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&this.operando2==null){//ADC (IY+d)
        instruccion="11111101"+"10000110";
        String d = this.operando1.replaceAll("\\(IY", "");//quita la parte (IY
        d = d.replaceAll("\\)", "");//quita la parte ). Al final queda +-d
        d = this.toBinary(d);
        while(d.length()<8){// en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
          d="0"+d;
        }
        d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita n�meros a la izquierda
        instruccion=instruccion+d;
      }
      else if(this.operando1.matches("HL")&&this.operando2.matches("BC|DE|HL|SP")){//ADC HL,ss    Aritm�tico 16 bits
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
    else if(this.operando1.matches("[0-9A-Fa-fhHbB\\-]+")&&this.operando2==null){//SUB n
      instruccion="11010110";
      String n;
      if(valorOp1!=null){
        n = this.toBinary(valorOp1);
        while(n.length()<8){// en caso de que el n�mero sea menor a 8, pone ceros a la izquierda
          n="0"+n;
        }
        n=n.substring(n.length()-8, n.length());//en caso de que sea mayor, le quita n�meros a la izquierda
        instruccion=instruccion+n;
      }
      else if(this.operando1.matches("[0-9a-fA-FhHbB\\-]+")){
        n = this.toBinary(this.operando1);
        while(n.length()<8){// en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
          n="0"+n;
        }
        n=n.substring(n.length()-8, n.length());//en caso de que sea mayor, le quita n�meros a la izquierda
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
      while(d.length()<8){// en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
        d="0"+d;
      }
      d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita n�meros a la izquierda
      instruccion=instruccion+d;
    }
    else if(this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&this.operando2==null){//SUB (IY+d)
      instruccion="11111101"+"10010110";
      String d = this.operando1.replaceAll("\\(IY", "");//quita la parte (IY
      d = d.replaceAll("\\)", "");//quita la parte ). Al final queda +-d
      d = this.toBinary(d);
      while(d.length()<8){// en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
        d="0"+d;
      }
      d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita n�meros a la izquierda
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
    else if(this.operando1.matches("[0-9A-Fa-fhHbB\\-]+")&&this.operando2==null){//SBC n
      instruccion="11011110";
      String n;
      if(valorOp1!=null){
        n = this.toBinary(valorOp1);
        while(n.length()<8){// en caso de que el n�mero sea menor a 8, pone ceros a la izquierda
          n="0"+n;
        }
        n=n.substring(n.length()-8, n.length());//en caso de que sea mayor, le quita n�meros a la izquierda
        instruccion=instruccion+n;
      }
      else if(this.operando1.matches("[0-9a-fA-FhHbB\\-]+")){
        n = this.toBinary(this.operando1);
        while(n.length()<8){// en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
          n="0"+n;
        }
        n=n.substring(n.length()-8, n.length());//en caso de que sea mayor, le quita n�meros a la izquierda
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
      while(d.length()<8){// en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
        d="0"+d;
      }
      d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita n�meros a la izquierda
      instruccion=instruccion+d;
    }
    else if(this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&this.operando2==null){//SBC (IY+d)
      instruccion="11111101"+"10011110";
      String d = this.operando1.replaceAll("\\(IY", "");//quita la parte (IY
      d = d.replaceAll("\\)", "");//quita la parte ). Al final queda +-d
      d = this.toBinary(d);
      while(d.length()<8){// en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
        d="0"+d;
      }
      d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita n�meros a la izquierda
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
  else if(this.operando1.matches("[0-9A-Fa-fhHbB\\-]+")&&this.operando2==null){//AND n
    instruccion="11100110";
    String n;
    if(valorOp1!=null){
      n = this.toBinary(valorOp1);
      while(n.length()<8){// en caso de que el n�mero sea menor a 8, pone ceros a la izquierda
        n="0"+n;
      }
      n=n.substring(n.length()-8, n.length());//en caso de que sea mayor, le quita n�meros a la izquierda
      instruccion=instruccion+n;
    }
    else if(this.operando1.matches("[0-9a-fA-FhHbB\\-]+")){
      n = this.toBinary(this.operando1);
      while(n.length()<8){// en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
        n="0"+n;
      }
      n=n.substring(n.length()-8, n.length());//en caso de que sea mayor, le quita n�meros a la izquierda
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
    while(d.length()<8){// en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
      d="0"+d;
    }
    d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita n�meros a la izquierda
    instruccion=instruccion+d;
  }
  else if(this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&this.operando2==null){//AND (IY+d)
    instruccion="11111101"+"10100110";
    String d = this.operando1.replaceAll("\\(IY", "");//quita la parte (IY
    d = d.replaceAll("\\)", "");//quita la parte ). Al final queda +-d
    d = this.toBinary(d);
    while(d.length()<8){// en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
      d="0"+d;
    }
    d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita n�meros a la izquierda
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
  else if(this.operando1.matches("[0-9A-Fa-fhHbB\\-]+")&&this.operando2==null){//OR n
    instruccion="11110110";
    String n;
    if(valorOp1!=null){
      n = this.toBinary(valorOp1);
      while(n.length()<8){// en caso de que el n�mero sea menor a 8, pone ceros a la izquierda
        n="0"+n;
      }
      n=n.substring(n.length()-8, n.length());//en caso de que sea mayor, le quita n�meros a la izquierda
      instruccion=instruccion+n;
    }
    else if(this.operando1.matches("[0-9a-fA-FhHbB\\-]+")){
      n = this.toBinary(this.operando1);
      while(n.length()<8){// en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
        n="0"+n;
      }
      n=n.substring(n.length()-8, n.length());//en caso de que sea mayor, le quita n�meros a la izquierda
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
    while(d.length()<8){// en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
      d="0"+d;
    }
    d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita n�meros a la izquierda
    instruccion=instruccion+d;
  }
  else if(this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&this.operando2==null){//OR (IY+d)
    instruccion="11111101"+"10110110";
    String d = this.operando1.replaceAll("\\(IY", "");//quita la parte (IY
    d = d.replaceAll("\\)", "");//quita la parte ). Al final queda +-d
    d = this.toBinary(d);
    while(d.length()<8){// en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
      d="0"+d;
    }
    d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita n�meros a la izquierda
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
  else if(this.operando1.matches("[0-9A-Fa-fhHbB\\-]+")&&this.operando2==null){//XOR n
    instruccion="11101110";
    String n;
    if(valorOp1!=null){
      n = this.toBinary(valorOp1);
      while(n.length()<8){// en caso de que el n�mero sea menor a 8, pone ceros a la izquierda
        n="0"+n;
      }
      n=n.substring(n.length()-8, n.length());//en caso de que sea mayor, le quita n�meros a la izquierda
      instruccion=instruccion+n;
    }
    else if(this.operando1.matches("[0-9a-fA-FhHbB\\-]+")){
      n = this.toBinary(this.operando1);
      while(n.length()<8){// en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
        n="0"+n;
      }
      n=n.substring(n.length()-8, n.length());//en caso de que sea mayor, le quita n�meros a la izquierda
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
    while(d.length()<8){// en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
      d="0"+d;
    }
    d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita n�meros a la izquierda
    instruccion=instruccion+d;
  }
  else if(this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&this.operando2==null){//XOR (IY+d)
    instruccion="11111101"+"10101110";
    String d = this.operando1.replaceAll("\\(IY", "");//quita la parte (IY
    d = d.replaceAll("\\)", "");//quita la parte ). Al final queda +-d
    d = this.toBinary(d);
    while(d.length()<8){// en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
      d="0"+d;
    }
    d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita n�meros a la izquierda
    instruccion=instruccion+d;
  }
}
else if ("cp".equals(this.operacion)||"CP".equals(this.operacion)){
  if(this.operando1.matches("[A-E]|H|L")&&this.operando2==null){//CP r
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
  else if(this.operando1.matches("[0-9A-Fa-fhHbB\\-]+")&&this.operando2==null){//CP n
    instruccion="11111110";
    String n;
    if(valorOp1!=null){
      n = this.toBinary(valorOp1);
      while(n.length()<8){// en caso de que el n�mero sea menor a 8, pone ceros a la izquierda
        n="0"+n;
      }
      n=n.substring(n.length()-8, n.length());//en caso de que sea mayor, le quita n�meros a la izquierda
      instruccion=instruccion+n;
    }
    else if(this.operando1.matches("[0-9a-fA-FhHbB\\-]+")){
      n = this.toBinary(this.operando2);
      while(n.length()<8){// en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
        n="0"+n;
      }
      n=n.substring(n.length()-8, n.length());//en caso de que sea mayor, le quita n�meros a la izquierda
      instruccion=instruccion+n;
    }
    else if(this.operando1.matches("\\(HL\\)")&&this.operando2==null){//CP (HL)
      instruccion="10111110";
    }
    else if(this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\)")&&this.operando2==null){//CP (IX+d)
      instruccion="11011101"+"10111110";
      String d = this.operando1.replaceAll("\\(IX", "");//quita la parte (IX
      d = d.replaceAll("\\)", "");//quita la parte ). Al final queda +-d
      d = this.toBinary(d);
      while(d.length()<8){// en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
        d="0"+d;
      }
      d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita n�meros a la izquierda
      instruccion=instruccion+d;
    }
    else if(this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&this.operando2==null){//CP (IY+d)
      instruccion="11111101"+"10111110";
      String d = this.operando1.replaceAll("\\(IY", "");//quita la parte (IY
      d = d.replaceAll("\\)", "");//quita la parte ). Al final queda +-d
      d = this.toBinary(d);
      while(d.length()<8){// en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
        d="0"+d;
      }
      d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita n�meros a la izquierda
      instruccion=instruccion+d;
    }
  }
  else if ("inc".equals(this.operacion)||"INC".equals(this.operacion)){
    if(this.operando1.matches("[A-E]|H|L")&&this.operando2==null){//INC r
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
    else if(this.operando1.matches("\\(HL\\)")&&this.operando2==null){//INC (HL)
      instruccion="00110100";
    }
    else if(this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\)")&&this.operando2==null){//INC (IX+d)
      instruccion="11011101"+"00110100";
      String d = this.operando1.replaceAll("\\(IX", "");//quita la parte (IX
      d = d.replaceAll("\\)", "");//quita la parte ). Al final queda +-d
      d = this.toBinary(d);
      while(d.length()<8){// en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
        d="0"+d;
      }
      d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita n�meros a la izquierda
      instruccion=instruccion+d;
    }
    else if(this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&this.operando2==null){//INC (IY+d)
      instruccion="11111101"+"00110100";
      String d = this.operando1.replaceAll("\\(IY", "");//quita la parte (IY
      d = d.replaceAll("\\)", "");//quita la parte ). Al final queda +-d
      d = this.toBinary(d);
      while(d.length()<8){// en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
        d="0"+d;
      }
      d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita n�meros a la izquierda
      instruccion=instruccion+d;
    }
    else if(this.operando1.matches("BC|DE|HL|SP")&&this.operando2==null){//INC ss       Aritm�tico 16 bits
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
    else if(this.operando1.matches("IX")&&this.operando2==null){//INC IX
      instruccion="11011101"+"00100011";
    }
    else if(this.operando1.matches("IY")&&this.operando2==null){//INC IY
      instruccion="11111101"+"00100011";
    }
  }
  else if ("dec".equals(this.operacion)||"DEC".equals(this.operacion)){
    if(this.operando1.matches("[A-E]|H|L")&&this.operando2==null){//DEC r
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
    else if(this.operando1.matches("\\(HL\\)")&&this.operando2==null){//DEC (HL)
      instruccion="00110101";
    }
    else if(this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\)")&&this.operando2==null){//DEC (IX+d)
      instruccion="11011101"+"00110101";
      String d = this.operando1.replaceAll("\\(IX", "");//quita la parte (IX
      d = d.replaceAll("\\)", "");//quita la parte ). Al final queda +-d
      d = this.toBinary(d);
      while(d.length()<8){// en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
        d="0"+d;
      }
      d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita n�meros a la izquierda
      instruccion=instruccion+d;
    }
    else if(this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&this.operando2==null){//DEC (IY+d)
      instruccion="11111101"+"00110101";
      String d = this.operando1.replaceAll("\\(IY", "");//quita la parte (IY
      d = d.replaceAll("\\)", "");//quita la parte ). Al final queda +-d
      d = this.toBinary(d);
      while(d.length()<8){// en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
        d="0"+d;
      }
      d=d.substring(d.length()-8, d.length());//en caso de que sea mayor, le quita n�meros a la izquierda
      instruccion=instruccion+d;
    }
    else if(this.operando1.matches("BC|DE|HL|SP")&&this.operando2==null){//DEC ss      Aritm�tico 16 bits
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
    else if(this.operando1.matches("IX")&&this.operando2==null){//DEC IX
      instruccion="11011101"+"00101011";
    }
    else if(this.operando1.matches("IY")&&this.operando2==null){//DEC IY
      instruccion="11111101"+"00101011";
    }
  }
  /////////////// CUARTA Y SEXTA TABLA. Grupo aritmitico de 8 y de 16 bits
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
  else if ("add".equals(this.operacion)||"ADD".equals(this.operacion)){
    if(this.operando1.matches("[A-E]|H|L")&&this.operando2==null){//ADD r
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
    else if(this.operando1.matches("[0-9a-fA-FhHbB\\+\\-]+")&&this.operando2==null){//ADD n
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
      else if(this.operando1.matches("[0-9a-fA-FhHbB\\-]+")){
        n = this.toBinary(this.operando1);
        while(n.length()<8){// en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
          n="0"+n;
        }
        n=n.substring(n.length()-8, n.length());//en caso de que sea mayor, le quita números a la izquierda
        instruccion=instruccion+n;
      }
      else if(this.operando1.matches("\\(HL\\)")&&this.operando2==null){//ADD (HL)
        instruccion="10000110";
      }
      else if(this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\)")&&this.operando2==null){//ADD (IX+d)
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
      else if(this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&this.operando2==null){//ADD (IY+d)
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
    if(this.operando1.matches("[A-E]|H|L")&&this.operando2==null){//ADC r
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
    else if(this.operando1.matches("[0-9A-Fa-fhHbB\\+\\-]+")&&this.operando2==null){//ADC n
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
      else if(this.operando1.matches("[0-9a-fA-FhHbB\\-]+")){
        n = this.toBinary(this.operando1);
        while(n.length()<8){// en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
          n="0"+n;
        }
        n=n.substring(n.length()-8, n.length());//en caso de que sea mayor, le quita números a la izquierda
        instruccion=instruccion+n;
      }
      else if(this.operando1.matches("\\(HL\\)")&&this.operando2==null){//ADC (HL)
        instruccion="10001110";
      }
      else if(this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\)")&&this.operando2==null){//ADC (IX+d)
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
      else if(this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&this.operando2==null){//ADC (IY+d)
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
    else if(this.operando1.matches("[0-9A-Fa-fhHbB\\-]+")&&this.operando2==null){//SUB n
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
      else if(this.operando1.matches("[0-9a-fA-FhHbB\\-]+")){
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
    else if(this.operando1.matches("[0-9A-Fa-fhHbB\\-]+")&&this.operando2==null){//SBC n
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
      else if(this.operando1.matches("[0-9a-fA-FhHbB\\-]+")){
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
  else if(this.operando1.matches("[0-9A-Fa-fhHbB\\-]+")&&this.operando2==null){//AND n
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
    else if(this.operando1.matches("[0-9a-fA-FhHbB\\-]+")){
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
  else if(this.operando1.matches("[0-9A-Fa-fhHbB\\-]+")&&this.operando2==null){//OR n
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
    else if(this.operando1.matches("[0-9a-fA-FhHbB\\-]+")){
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
  else if(this.operando1.matches("[0-9A-Fa-fhHbB\\-]+")&&this.operando2==null){//XOR n
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
    else if(this.operando1.matches("[0-9a-fA-FhHbB\\-]+")){
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
  if(this.operando1.matches("[A-E]|H|L")&&this.operando2==null){//CP r
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
  else if(this.operando1.matches("[0-9A-Fa-fhHbB\\-]+")&&this.operando2==null){//CP n
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
    else if(this.operando1.matches("[0-9a-fA-FhHbB\\-]+")){
      n = this.toBinary(this.operando2);
      while(n.length()<8){// en caso de que el número sea menor a 8 bits, pone ceros a la izquierda
        n="0"+n;
      }
      n=n.substring(n.length()-8, n.length());//en caso de que sea mayor, le quita números a la izquierda
      instruccion=instruccion+n;
    }
    else if(this.operando1.matches("\\(HL\\)")&&this.operando2==null){//CP (HL)
      instruccion="10111110";
    }
    else if(this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\)")&&this.operando2==null){//CP (IX+d)
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
    else if(this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&this.operando2==null){//CP (IY+d)
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
    if(this.operando1.matches("[A-E]|H|L")&&this.operando2==null){//INC r
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
    else if(this.operando1.matches("\\(HL\\)")&&this.operando2==null){//INC (HL)
      instruccion="00110100";
    }
    else if(this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\)")&&this.operando2==null){//INC (IX+d)
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
    else if(this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&this.operando2==null){//INC (IY+d)
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
    else if(this.operando1.matches("BC|DE|HL|SP")&&this.operando2==null){//INC ss       Aritmético 16 bits
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
    else if(this.operando1.matches("IX")&&this.operando2==null){//INC IX
      instruccion="11011101"+"00100011";
    }
    else if(this.operando1.matches("IY")&&this.operando2==null){//INC IY
      instruccion="11111101"+"00100011";
    }
  }
  else if ("dec".equals(this.operacion)||"DEC".equals(this.operacion)){
    if(this.operando1.matches("[A-E]|H|L")&&this.operando2==null){//DEC r
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
    else if(this.operando1.matches("\\(HL\\)")&&this.operando2==null){//DEC (HL)
      instruccion="00110101";
    }
    else if(this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\)")&&this.operando2==null){//DEC (IX+d)
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
    else if(this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\)")&&this.operando2==null){//DEC (IY+d)
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
    else if(this.operando1.matches("BC|DE|HL|SP")&&this.operando2==null){//DEC ss      Aritmético 16 bits
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
    else if(this.operando1.matches("IX")&&this.operando2==null){//DEC IX
      instruccion="11011101"+"00101011";
    }
    else if(this.operando1.matches("IY")&&this.operando2==null){//DEC IY
      instruccion="11111101"+"00101011";
    }
  }
  /////////////// CUARTA Y SEXTA HOJA. Grupo aritmético de 8 y de 16 bits
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
    if (this.operando1.matches("[A-E|H|L]")&&this.operando2==null){         //r
      switch(this.operando1.charAt(0)){
        instruccion="1100101100000";
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
    else if (this.operando1.matches("\\(HL\\)")&&this.operando2==null){     // (HL)
      instruccion="1100101100000110";
    }
    else if (this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\")&&this.operando2==null){     // (IX + d)
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
  else if ("rl".equals(this.operacion)||"RL".equals(this.operacion)){     //RL
    if (this.operando1.matches("[A-E|H|L]")&&this.operando2==null){         //RL r
      instruccion="11001011"+"00010";
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
    else if (this.operando1.matches("\\(HL\\)")&&this.operando2==null){//RL (HL)
      instruccion="11001011"+"00010110";
    }
    else if (this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\")&&this.operando2==null){//RL (IX + d)
      instruccion="11011101"+"11001011";
      String d = this.operando1.replaceAll("\\(IX", "");                    //quita la parte (IX
      d = d.replaceAll("\\)", "");                                    //quita la parte ). Al final queda +-d
      d = this.toBinary(d);
      while(d.length()<8){                                            // en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
        d="0"+d;
      }
      d=d.substring(d.length()-8, d.length());                        //en caso de que sea mayor, le quita n�meros a la izquierda
      instruccion=instruccion+d;
      instruccion=instruccion+"00010110";
    }
    else if (this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\")&&this.operando2==null){//RL (IY + d)
      instruccion="11111101"+"11001011";
      String d = this.operando1.replaceAll("\\(IY", "");                    //quita la parte (IY
      d = d.replaceAll("\\)", "");                                    //quita la parte ). Al final queda +-d
      d = this.toBinary(d);
      while(d.length()<8){                                            // en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
        d="0"+d;
      }
      d=d.substring(d.length()-8, d.length());                        //en caso de que sea mayor, le quita n�meros a la izquierda
      instruccion=instruccion+d;
      instruccion=instruccion+"00010110";
    }
  }
  else if ("rrc".equals(this.operacion)||"RRC".equals(this.operacion)){     //RRC
    if (this.operando1.matches("[A-E|H|L]")&&this.operando2==null){         //RRC r
      instruccion="11001011"+"00001";
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
    else if (this.operando1.matches("\\(HL\\)")&&this.operando2==null){//RRC (HL)
      instruccion="11001011"+"00001110";
    }
    else if (this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\")&&this.operando2==null){//RRC (IX + d)
      instruccion="11011101"+"11001011";
      String d = this.operando1.replaceAll("\\(IX", "");                    //quita la parte (IX
      d = d.replaceAll("\\)", "");                                    //quita la parte ). Al final queda +-d
      d = this.toBinary(d);
      while(d.length()<8){                                            // en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
        d="0"+d;
      }
      d=d.substring(d.length()-8, d.length());                        //en caso de que sea mayor, le quita n�meros a la izquierda
      instruccion=instruccion+d;
      instruccion=instruccion+"00001110";
    }
    else if (this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\")&&this.operando2==null){//RRC (IY + d)
      instruccion="11111101"+"11001011";
      String d = this.operando1.replaceAll("\\(IY", "");                    //quita la parte (IY
      d = d.replaceAll("\\)", "");                                    //quita la parte ). Al final queda +-d
      d = this.toBinary(d);
      while(d.length()<8){                                            // en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
        d="0"+d;
      }
      d=d.substring(d.length()-8, d.length());                        //en caso de que sea mayor, le quita n�meros a la izquierda
      instruccion=instruccion+d;
      instruccion=instruccion+"00001110";
    }
  }
  else if ("rr".equals(this.operacion)||"RR".equals(this.operacion)){     //RR
    if (this.operando1.matches("[A-E|H|L]")&&this.operando2==null){         //RR r
      instruccion="11001011"+"00011";
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
    else if (this.operando1.matches("\\(HL\\)")&&this.operando2==null){//RR (HL)
      instruccion="11001011"+"00011110";
    }
    else if (this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\")&&this.operando2==null){//RR (IX + d)
      instruccion="11011101"+"11001011";
      String d = this.operando1.replaceAll("\\(IX", "");                    //quita la parte (IX
      d = d.replaceAll("\\)", "");                                    //quita la parte ). Al final queda +-d
      d = this.toBinary(d);
      while(d.length()<8){                                            // en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
        d="0"+d;
      }
      d=d.substring(d.length()-8, d.length());                        //en caso de que sea mayor, le quita n�meros a la izquierda
      instruccion=instruccion+d;
      instruccion=instruccion+"00011110";
    }
    else if (this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\")&&this.operando2==null){//RR (IY + d)
      instruccion="11111101"+"11001011";
      String d = this.operando1.replaceAll("\\(IY", "");                    //quita la parte (IY
      d = d.replaceAll("\\)", "");                                    //quita la parte ). Al final queda +-d
      d = this.toBinary(d);
      while(d.length()<8){                                            // en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
        d="0"+d;
      }
      d=d.substring(d.length()-8, d.length());                        //en caso de que sea mayor, le quita n�meros a la izquierda
      instruccion=instruccion+d;
      instruccion=instruccion+"00011110";
    }
  }
  else if ("sla".equals(this.operacion)||"SLA".equals(this.operacion)){     //SLA
    if (this.operando1.matches("[A-E|H|L]")&&this.operando2==null){         //SLA r
      instruccion="11001011"+"00100";
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
    else if (this.operando1.matches("\\(HL\\)")&&this.operando2==null){//SLA (HL)
      instruccion="11001011"+"00100110";
    }
    else if (this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\")&&this.operando2==null){//SLA (IX + d)
      instruccion="11011101"+"11001011";
      String d = this.operando1.replaceAll("\\(IX", "");                    //quita la parte (IX
      d = d.replaceAll("\\)", "");                                    //quita la parte ). Al final queda +-d
      d = this.toBinary(d);
      while(d.length()<8){                                            // en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
        d="0"+d;
      }
      d=d.substring(d.length()-8, d.length());                        //en caso de que sea mayor, le quita n�meros a la izquierda
      instruccion=instruccion+d;
      instruccion=instruccion+"00100110";
    }
    else if (this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\")&&this.operando2==null){//SLA (IY + d)
      instruccion="11111101"+"11001011";
      String d = this.operando1.replaceAll("\\(IY", "");                    //quita la parte (IY
      d = d.replaceAll("\\)", "");                                    //quita la parte ). Al final queda +-d
      d = this.toBinary(d);
      while(d.length()<8){                                            // en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
        d="0"+d;
      }
      d=d.substring(d.length()-8, d.length());                        //en caso de que sea mayor, le quita n�meros a la izquierda
      instruccion=instruccion+d;
      instruccion=instruccion+"00100110";
    }
  }
  else if ("sra".equals(this.operacion)||"SRA".equals(this.operacion)){     //SRA
    if (this.operando1.matches("[A-E|H|L]")&&this.operando2==null){         //SRA r
      instruccion="11001011"+"00101";
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
    else if (this.operando1.matches("\\(HL\\)")&&this.operando2==null){//SRA (HL)
      instruccion="11001011"+"00101110";
    }
    else if (this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\")&&this.operando2==null){//SRA (IX + d)
      instruccion="11011101"+"11001011";
      String d = this.operando1.replaceAll("\\(IX", "");                    //quita la parte (IX
      d = d.replaceAll("\\)", "");                                    //quita la parte ). Al final queda +-d
      d = this.toBinary(d);
      while(d.length()<8){                                            // en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
        d="0"+d;
      }
      d=d.substring(d.length()-8, d.length());                        //en caso de que sea mayor, le quita n�meros a la izquierda
      instruccion=instruccion+d;
      instruccion=instruccion+"00101110";
    }
    else if (this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\")&&this.operando2==null){//SRA (IY + d)
      instruccion="11111101"+"11001011";
      String d = this.operando1.replaceAll("\\(IY", "");                    //quita la parte (IY
      d = d.replaceAll("\\)", "");                                    //quita la parte ). Al final queda +-d
      d = this.toBinary(d);
      while(d.length()<8){                                            // en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
        d="0"+d;
      }
      d=d.substring(d.length()-8, d.length());                        //en caso de que sea mayor, le quita n�meros a la izquierda
      instruccion=instruccion+d;
      instruccion=instruccion+"00101110";
    }
  }
  else if ("srl".equals(this.operacion)||"SRL".equals(this.operacion)){     //SRL
    if (this.operando1.matches("[A-E|H|L]")&&this.operando2==null){         //SRL r
      instruccion="11001011"+"00111";
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
    else if (this.operando1.matches("\\(HL\\)")&&this.operando2==null){//SRL (HL)
      instruccion="11001011"+"00111110";
    }
    else if (this.operando1.matches("\\(IX(\\+|\\-)[0-9]+\\")&&this.operando2==null){//SRL (IX + d)
      instruccion="11011101"+"11001011";
      String d = this.operando1.replaceAll("\\(IX", "");                    //quita la parte (IX
      d = d.replaceAll("\\)", "");                                    //quita la parte ). Al final queda +-d
      d = this.toBinary(d);
      while(d.length()<8){                                            // en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
        d="0"+d;
      }
      d=d.substring(d.length()-8, d.length());                        //en caso de que sea mayor, le quita n�meros a la izquierda
      instruccion=instruccion+d;
      instruccion=instruccion+"00111110";
    }
    else if (this.operando1.matches("\\(IY(\\+|\\-)[0-9]+\\")&&this.operando2==null){//SRL (IY + d)
      instruccion="11111101"+"11001011";
      String d = this.operando1.replaceAll("\\(IY", "");                    //quita la parte (IY
      d = d.replaceAll("\\)", "");                                    //quita la parte ). Al final queda +-d
      d = this.toBinary(d);
      while(d.length()<8){                                            // en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
        d="0"+d;
      }
      d=d.substring(d.length()-8, d.length());                        //en caso de que sea mayor, le quita n�meros a la izquierda
      instruccion=instruccion+d;
      instruccion=instruccion+"00111110";
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
      instruccion="1100101101";
      switch(this.operando1.charAt(0)){
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
      switch(this.operando2.charAt(0)){                                         //r
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
      instruccion="1100101101";
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
      instruccion="1101110111001011";
      String d = this.operando2.replaceAll("\\(IX", "");                    //quita la parte (IX
      d = d.replaceAll("\\)", "");                                    //quita la parte ). Al final queda +-d
      d = this.toBinary(d);
      while(d.length()<8){                                            // en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
        d="0"+d;
      }
      d=d.substring(d.length()-8, d.length());                        //en caso de que sea mayor, le quita n�meros a la izquierda
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
      instruccion="1111110111001011";
      String d = this.operando2.replaceAll("\\(IY", "");                    //quita la parte (IX
      d = d.replaceAll("\\)", "");                                    //quita la parte ). Al final queda +-d
      d = this.toBinary(d);
      while(d.length()<8){                                            // en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
        d="0"+d;
      }
      d=d.substring(d.length()-8, d.length());                        //en caso de que sea mayor, le quita n�meros a la izquierda
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
      instruccion="1100101111";
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
      instruccion="1100101111";
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
      while(d.length()<8){                                            // en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
        d="0"+d;
      }
      d=d.substring(d.length()-8, d.length());                        //en caso de que sea mayor, le quita n�meros a la izquierda
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
      while(d.length()<8){                                            // en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
        d="0"+d;
      }
      d=d.substring(d.length()-8, d.length());                        //en caso de que sea mayor, le quita n�meros a la izquierda
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
      while(d.length()<8){                                            // en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
        d="0"+d;
      }
      d=d.substring(d.length()-8, d.length());                        //en caso de que sea mayor, le quita n�meros a la izquierda
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
      while(d.length()<8){                                            // en caso de que el n�mero sea menor a 8 bits, pone ceros a la izquierda
        d="0"+d;
      }
      d=d.substring(d.length()-8, d.length());                        //en caso de que sea mayor, le quita n�meros a la izquierda
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
  ////////////////OCTAVA TABLA
  else if("jp".equals(this.operacion)||"JP".equals(this.operacion)){
    if (this.operando1.matches("([0-9a-fA-FhHbB\\-]+)|\\w+")&&this.operando2==null){//JP nn
      instruccion="11000011";
      String n;
      if(valorOp1!=null){
        n = this.toBinary(valorOp1);
        while(n.length()<16){                                       //num sea menor a 16, pone ceros a la izquierda
          n="0"+n;
        }
        n=n.substring(n.length()-16, n.length());                   //num sea mayor, le quita números a la izquierda
        instruccion=instruccion+n.substring(8);
        instruccion=instruccion+n.substring(0, 8);
      }
      else if(this.operando1.matches("[0-9a-fA-FhHbB\\-]+")){
        n = this.toBinary(this.operando1);
        while(n.length()<16){                                       //num sea menor a 16 bits, pone ceros a la izquierda
          n="0"+n;
        }
        n=n.substring(n.length()-16, n.length());                   //num sea mayor, le quita números a la izquierda
        instruccion=instruccion+n.substring(8);
        instruccion=instruccion+n.substring(0, 8);
      }
    }
    else if (this.operando1.matches("NZ|Z|NC|C|PO|PE|P|M")&&this.operando2.matches("([0-9a-fA-FhHbB\\-]+)|\\w+")){//JP cc,nn
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
      else if(this.operando2.matches("[0-9a-fA-FhHbB\\-]+")){
        n = this.toBinary(this.operando2);
        while(n.length()<16){// en caso de que el número sea menor a 16 bits, pone ceros a la izquierda
          n="0"+n;
        }
        n=n.substring(n.length()-16, n.length());//en caso de que sea mayor, le quita números a la izquierda
        instruccion=instruccion+n.substring(8);
        instruccion=instruccion+n.substring(0, 8);
      }
    }
    else if (this.operando1.matches("\\(HL\\)")&&this.operando2==null){//JP (HL)
      instruccion="11101001";
    }
    else if (this.operando1.matches("\\(IX\\)")&&this.operando2==null){//JP (IX)
      instruccion="11011101"+"11101001";
    }
    else if (this.operando1.matches("\\(IY\\)")&&this.operando2==null){//JP (IY)
      instruccion="11111101"+"11101001";
    }
  }

  /*  Encontrar forma de colocar "e", es un num en complemento a 2
  else if ("djn".equals(this.operacion)||"DJN".equals(this.operacion)){     //DJN
  if(this.operando1.matches("Z")&&this.operando2.matches("[\\-0-9hHbB]+|\\w+"))
  instruccion="00010000";
  INTRUCCION PARA CONVERTIRLO A BINARIO

}*/

////////////////NOVENA TABLA
else if ("call".equals(this.operacion)||"CALL".equals(this.operacion)){       //CALL nn
  if(this.operando1.matches("([0-9a-fA-FhHbB\\-]+)|\\w+")&&this.operando2==null){
    instruccion="11001101";
    String n;
    if(valorOp1!=null){                                   //Si el valor de entrada en entero
      n = valorOp1.replaceAll("", "");
      n = n.replaceAll("","");
      n = this.toBinary(n);
      while(n.length()<16){                             // en caso de que el número sea menor a 16, pone ceros a la izquierda
        n="0"+n;
      }
      n=n.substring(n.length()-16, n.length());         //en caso de que sea mayor, le quita números a la izquierda
      instruccion=instruccion+n.substring(8);
      instruccion=instruccion+n.substring(0, 8);
    }
    else if(this.operando1.matches("[0-9a-fA-FhHbB]")){   //Si el valor de entrada en hex o bin
      n = operando1.replaceAll("\\(", "");
      n = n.replaceAll("\\)","");
      n = this.toBinary(n);
      while(n.length()<16){                            // en caso de que el número sea menor a 16 bits, pone ceros a la izquierda
        n="0"+n;
      }
      n=n.substring(n.length()-16, n.length());       //en caso de que sea mayor, le quita números a la izquierda
      instruccion=instruccion+n.substring(8);
      instruccion=instruccion+n.substring(0, 8);
    }
  }
  else if (this.operando1.matches("NZ|Z|NC|C|PO|PE|P|M")&&this.operando2.matches("([0-9a-fA-FhHbB\\-]+)|\\w+")){//CALL cc,nn
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
      while(n.length()<16){                                     // en caso de que el número sea menor a 16, pone ceros a la izquierda
        n="0"+n;
      }
      n=n.substring(n.length()-16, n.length());                 //en caso de que sea mayor, le quita números a la izquierda
      instruccion=instruccion+n.substring(8);
      instruccion=instruccion+n.substring(0, 8);
    }
    else if(this.operando2.matches("[0-9a-fA-FhHbB\\-]+")){
      n = this.toBinary(this.operando2);
      while(n.length()<16){                                     // en caso de que el número sea menor a 16 bits, pone ceros a la izquierda
        n="0"+n;
      }
      n=n.substring(n.length()-16, n.length());                 //en caso de que sea mayor, le quita números a la izquierda
      instruccion=instruccion+n.substring(8);
      instruccion=instruccion+n.substring(0, 8);
    }
  }
}
else if ("ret".equals(this.operacion)||"RET".equals(this.operacion)){     //RET
  if(this.operando1==null&&this.operando2==null) {
    instruccion="11001001";
  }
  else if(this.operando1.matches("NZ|Z|NC|C|PO|PE|P|M"))&&this.operando2==null){    //RET cc
    instruccion="11";
    switch(this.operando1){
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
else if ("reti".equals(this.operacion)||"RETI".equals(this.operacion)){     //RETI
  instruccion="1110110101001101";
}
else if ("retn".equals(this.operacion)||"RETIN".equals(this.operacion)){     //RETIN
  instruccion="1110110101000101";
}
else if ("rst".equals(this.operacion)||"RST".equals(this.operacion)){     //RST p
  if(this.operando1.matches("[0-9hH]")&&this.operando2==null){
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
}
////////////////DECIMA TABLA
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
