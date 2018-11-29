/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ensamblador;
import java.awt.Color;
import java.io.*;
import javax.swing.*;
/**
 *
 * @author masue
 */
public class JFassembly extends javax.swing.JFrame {
    JFileChooser seleccionado = new JFileChooser();
    File archivo;
    GestionArchivo admon = new GestionArchivo();
    String contenido;
    String lst="", hex="", nibbles="";
    int lineasTotales;
    TablaDeSimbolos tablaSimbolos = TablaDeSimbolos.getInstance();
    
    public void recorrerCadena(){
        this.lineasTotales=1;
        for(int i=0; i<this.contenido.length();i++){
            if(this.contenido.charAt(i)=='\n'){
                this.lineasTotales++;
            }
        }
    }
    
    public String leerLinea(int numLinea){
        int ini=0,fin,j=0;
        for(int i=0; i<this.contenido.length();i++){
            if(this.contenido.charAt(i)=='\n'){
                fin=i;
                j++;
                if(j==numLinea){
                    return this.contenido.substring(ini, fin-1);
                }
                ini=i+1;
            }
        }
        j++;
        if(j==this.lineasTotales)
            return this.contenido.substring(ini, this.contenido.length());
        return null;
    }
    
    
    public void primerPasada(){
        int CL=0;
        int numLinea=1;
        while(numLinea<=this.lineasTotales){
            Linea lineaActual = new Linea(leerLinea(numLinea));
            if(!lineaActual.cumple){
                JOptionPane.showMessageDialog(null, "La línea "+numLinea+" no cumple con el formato.");
                return;
            }
            if(lineaActual.etiqueta!=null){//Si hay etiqueta
                if(!this.tablaSimbolos.nombre.contains(lineaActual.etiqueta)){//Si no está en la tabla de simbolos
                    this.tablaSimbolos.nombre.add(lineaActual.etiqueta);
                    this.tablaSimbolos.valor.add(CL);
                }//Si no, etiqueta definida multiplemente
            }
            Integer longitud = lineaActual.longitudDeInstruccion();
            if (longitud==null){
                JOptionPane.showMessageDialog(null, "La línea "+numLinea+" no cumple con el formato.");
                return;
            }
            CL+=longitud;
            numLinea++;
        }
        
    }
    public void SegundaPasada(){
        int CL=0;
        int numLinea=1;
        this.lst=this.lst+"CL    Código Objeto    Instruccion\n";
        while(numLinea<=this.lineasTotales){
            Linea lineaActual = new Linea(leerLinea(numLinea));
            String valorOp1=null, valorOp2=null;
            if(lineaActual.operando1!=null){
                if(!lineaActual.operando1.equals("")){
                    if(!lineaActual.operando1.matches("\\(?[0-9A-Fa-fhHbBIXixYyNZPOpoMm\\+\\-]+\\)?")){//Si en operando 1 hay etiqueta
                        if(this.tablaSimbolos.nombre.contains(lineaActual.operando1)){//si la etiqueta está definida en la tabla
                            String operandoSinParentesis = lineaActual.operando1.replaceAll("\\(", "");
                            operandoSinParentesis = operandoSinParentesis.replaceAll("\\)", "");
                            Integer valorOp1Int=this.tablaSimbolos.valor.get(this.tablaSimbolos.nombre.indexOf(operandoSinParentesis));//valorOp 1 igual al valor de la etiqueta
                            valorOp1=valorOp1Int.toString();
                        }
                        else
                            JOptionPane.showMessageDialog(null, "La etiqueta "+lineaActual.operando1+" no está definida.");
                    }
                } 
            }           
            if(lineaActual.operando2!=null){
                if(!lineaActual.operando2.matches("\\(?[0-9A-Fa-fhHbBIXixYyNZPOpoMm\\+\\-]+\\)?")){
                if(this.tablaSimbolos.nombre.contains(lineaActual.operando2)){//si la etiqueta está definida en la tabla 
                    String operandoSinParentesis = lineaActual.operando2.replaceAll("\\(", "");
                    operandoSinParentesis = operandoSinParentesis.replaceAll("\\)", "");
                    Integer valorOp2Int=this.tablaSimbolos.valor.get(this.tablaSimbolos.nombre.indexOf(operandoSinParentesis));//valorOp 2 igual al valor de la etiqueta
                    valorOp2=valorOp2Int.toString();
                }
                else
                    JOptionPane.showMessageDialog(null, "La etiqueta "+lineaActual.operando2+" no está definida.");
                }
            }
            if(lineaActual.longitudDeInstruccion()==null)
                JOptionPane.showMessageDialog(null, "La instruccion "+lineaActual.operacion+" no es válida");
            else{
                String CLocal =Integer.toHexString(CL);
                while(CLocal.length()<4)
                        CLocal = "0"+CLocal;
                String codigoObjeto =lineaActual.getCodigoObjeto(valorOp1, valorOp2, CL);
                this.nibbles=this.nibbles+codigoObjeto;
                this.lst=this.lst+CLocal+"  "+codigoObjeto+"   "+lineaActual.fila+"\n";
                CL+=lineaActual.longitudDeInstruccion();
            }
            numLinea++;
        }
    }
    
    public void guardaTablaSimbolos(){
        this.lst=this.lst+"TABLA DE SIMBOLOS\n";
        this.lst=this.lst+"Nombre   Valor\n";
        for(int i=0; i<this.tablaSimbolos.nombre.size();i++){
            String CLocal =Integer.toHexString(this.tablaSimbolos.valor.get(i));
            while (CLocal.length()<4)
                CLocal="0"+CLocal;
            this.lst=this.lst+this.tablaSimbolos.nombre.get(i)+"  "+CLocal+"  \n";
        }
    }

    public void guardarHex(){
        int numNibbles = this.nibbles.length()/2;
        String nibblesAux=this.nibbles;
        int numBloques = numNibbles/16;
        int numResto = numNibbles % 16;
        int chkSum;
        String dir="0000";
        for (int i=0; i<numBloques; i++){
            dir = Integer.toHexString(i*16);
            while(dir.length()<4)
                dir="0"+dir;
            chkSum=0;
            String datos ="10"+dir+"00"+nibblesAux.substring(0,32);
            nibblesAux=nibblesAux.substring(32);
            for(int j=0; j<datos.length(); j+=2){
                chkSum+=Integer.parseInt(datos.substring(j, j+2), 16);
            }
            String chkSumComA2 = Integer.toHexString(-chkSum);
            this.hex=this.hex+":"+datos+chkSumComA2.substring(chkSumComA2.length()-2, chkSumComA2.length())+"\n";
            dir = Integer.toHexString((i+1)*16);        
        }
        if(numResto!=0){
            chkSum=0;
            while(dir.length()<4)
                dir="0"+dir;
            String numRestoHex = Integer.toHexString(numResto);
            while(numRestoHex.length()<2)
                numRestoHex="0"+numRestoHex;
            String datos =numRestoHex+dir+"00"+nibblesAux;
            for(int j=0; j<datos.length(); j+=2){
                chkSum+=Integer.parseInt(datos.substring(j, j+2), 16);
            }
            String chkSumComA2 = Integer.toHexString(-chkSum);
            this.hex=this.hex+":"+datos+chkSumComA2.substring(chkSumComA2.length()-2)+"\n";
        }
        this.hex=this.hex+":00000001FF";
    }
    
    /**
     * Creates new form JFassembly
     */
    public JFassembly() {
        initComponents();
        this.getContentPane().setBackground(Color.white);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAbrirAsm = new javax.swing.JButton();
        btnGuardarLst = new javax.swing.JButton();
        btnGuardarHEX = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ensamblador Z80");
        setBackground(new java.awt.Color(255, 102, 102));

        btnAbrirAsm.setText("Abrir archivo fuente");
        btnAbrirAsm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirAsmActionPerformed(evt);
            }
        });

        btnGuardarLst.setText("Guardar Archivo LST");
        btnGuardarLst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarLstActionPerformed(evt);
            }
        });

        btnGuardarHEX.setText("Guardar Archivo HEX");
        btnGuardarHEX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarHEXActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagen/escudofi_color.jpg"))); // NOI18N

        jLabel2.setText("Equipo conformado por:");

        jLabel3.setText("Díaz Ramírez Yoeli y Oserina");

        jLabel4.setText("Herrera Sánchez Diego Cesar");

        jLabel5.setText("Suárez Espinoza Mario Alberto");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAbrirAsm, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(btnGuardarLst, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGuardarHEX, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAbrirAsm)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnGuardarLst)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnGuardarHEX)
                        .addGap(60, 60, 60))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(22, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAbrirAsmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirAsmActionPerformed
        if(this.seleccionado.showDialog(null, "Abrir archivo ASM") == JFileChooser.APPROVE_OPTION){
            this.archivo = this.seleccionado.getSelectedFile();
            if(this.archivo.canRead() && this.archivo.getName().endsWith("asm") ){
                this.contenido = admon.abrirArchivo(archivo);
                JOptionPane.showMessageDialog(null, "Se abrió el archivo con éxito");
                this.recorrerCadena();
                this.primerPasada();
                this.guardaTablaSimbolos();
                this.SegundaPasada();
                this.guardarHex();
                this.lst=this.lst.toUpperCase();
                this.hex=this.hex.toUpperCase();
            }
            else{
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un archivo con extensión .asm");
            }
        }
    }//GEN-LAST:event_btnAbrirAsmActionPerformed

    private void btnGuardarLstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarLstActionPerformed
        if(this.seleccionado.showDialog(null, "Guardar Archivo LST") == JFileChooser.APPROVE_OPTION){
            this.archivo = this.seleccionado.getSelectedFile();
            if(archivo.getName().endsWith("lst")){
                if(admon.guardarArchivo(archivo, this.lst))
                    JOptionPane.showMessageDialog(null, "Se guardo el archivo con éxito");
                else
                    JOptionPane.showMessageDialog(null, "Error al guardar archivo");
            }else{
                JOptionPane.showMessageDialog(null, "El archivo debe guardarse con extensión .lst");
            }
        }
    }//GEN-LAST:event_btnGuardarLstActionPerformed

    private void btnGuardarHEXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarHEXActionPerformed
        if(this.seleccionado.showDialog(null, "Guardar Archivo HEX") == JFileChooser.APPROVE_OPTION){
            this.archivo = this.seleccionado.getSelectedFile();
            if(archivo.getName().endsWith("hex")){
                if(admon.guardarArchivo(archivo, this.hex))
                    JOptionPane.showMessageDialog(null, "Se guardo el archivo con éxito");
                else
                    JOptionPane.showMessageDialog(null, "Error al guardar archivo");
            }else{
                JOptionPane.showMessageDialog(null, "El archivo debe guardarse con extensión .hex");
            }
        }
    }//GEN-LAST:event_btnGuardarHEXActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFassembly.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFassembly.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFassembly.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFassembly.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFassembly().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrirAsm;
    private javax.swing.JButton btnGuardarHEX;
    private javax.swing.JButton btnGuardarLst;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
}
