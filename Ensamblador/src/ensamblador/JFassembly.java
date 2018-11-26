/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ensamblador;
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
    String lst="";
    int lineasTotales;
    TablaDeSimbolos tablaSimbolos = TablaDeSimbolos.getInstance();
    
    public void recorrerCadena(){
        this.lineasTotales=1;
        //int ini=0,fin;
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
            CL+=lineaActual.longitudDeInstruccion();
            numLinea++;
        }
        
    }
    public void SegundaPasada(){
        int CL=0;
        int numLinea=1;
        this.lst=this.lst+"CL    Instruccion          Código Objeto\n";
        while(numLinea<=this.lineasTotales){
            Linea lineaActual = new Linea(leerLinea(numLinea));
            String valorOp1=null, valorOp2=null;
            if(lineaActual.operando1!=null){
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
                this.lst=this.lst+Integer.toHexString(CL)+"    "+lineaActual.operacion+" "+lineaActual.operando1+","+lineaActual.operando2;
                this.lst=this.lst+"    "+lineaActual.getCodigoObjeto(valorOp1, valorOp2)+"\n";
                CL+=lineaActual.longitudDeInstruccion();
            }
            numLinea++;
        }
    }
    
    public void guardaTablaSimbolos(){
        this.lst="Tabla de simbolos\n";
        for(int i=0; i<this.tablaSimbolos.nombre.size();i++){
            this.lst=this.lst+Integer.toHexString(this.tablaSimbolos.valor.get(i))+"  "+this.tablaSimbolos.nombre.get(i)+"  \n";
        }
    }

    /**
     * Creates new form JFassembly
     */
    public JFassembly() {
        initComponents();
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
        btnPrueba = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ensamblador Z80");

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

        btnPrueba.setText("Pruebas");
        btnPrueba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPruebaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAbrirAsm)
                    .addComponent(btnGuardarLst))
                .addContainerGap(133, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPrueba)
                .addGap(162, 162, 162))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAbrirAsm)
                .addGap(65, 65, 65)
                .addComponent(btnGuardarLst)
                .addGap(59, 59, 59)
                .addComponent(btnPrueba)
                .addContainerGap(96, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAbrirAsmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirAsmActionPerformed
        if(this.seleccionado.showDialog(null, "Abrir archivo") == JFileChooser.APPROVE_OPTION){
            this.archivo = this.seleccionado.getSelectedFile();
            if(this.archivo.canRead() && this.archivo.getName().endsWith("asm") ){
                this.contenido = admon.abrirArchivo(archivo);
                JOptionPane.showMessageDialog(null, "Se abrió el archivo con éxito");
                this.recorrerCadena();
                System.out.println("Las lineas totales son "+this.lineasTotales);
                this.primerPasada();
                this.guardaTablaSimbolos();
                this.SegundaPasada();
                //System.out.print(this.lst);
            }
            else{
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un archivo con extensión .asm");
            }
        }
    }//GEN-LAST:event_btnAbrirAsmActionPerformed

    private void btnGuardarLstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarLstActionPerformed
        if(this.seleccionado.showDialog(null, "Guardar Archivo") == JFileChooser.APPROVE_OPTION){
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

    private void btnPruebaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPruebaActionPerformed
        for(int i=0; i<this.tablaSimbolos.nombre.size();i++){
            System.out.print(this.tablaSimbolos.valor.get(i)+"fV  iN"+this.tablaSimbolos.nombre.get(i)+"fN\n");
            this.lst.concat(this.tablaSimbolos.valor.get(i)+"  "+this.tablaSimbolos.nombre.get(i)+"  \n");
        }
        this.tablaSimbolos.valor.get(8);
        System.out.print("iniciO:");
        System.out.print(this.lst);
        System.out.print(":Fin");
    }//GEN-LAST:event_btnPruebaActionPerformed

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
    private javax.swing.JButton btnGuardarLst;
    private javax.swing.JButton btnPrueba;
    // End of variables declaration//GEN-END:variables
}
