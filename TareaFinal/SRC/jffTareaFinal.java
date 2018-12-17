import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author ivan
 */
public class jffTareaFinal extends javax.swing.JFrame {

    /**
     * Creates new form jffTareaFinal
     */
    public jffTareaFinal() {
        initComponents();
    }

 /**
     * Carga un archivo en un coleccionable.
     * @param archivo
     * @param a
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void cargaContenido(String archivo, Coleccionable<Palabra> a) throws FileNotFoundException, IOException {
        String cadena;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            a.agrega(new Palabra(cadena));
        }
        b.close();
    }
    
    /**
     * Escribe un String en un documento.
     * @param f
     * @param linea
     */
    public static void escribir(File f, String linea) {        
        //Escritura
        try{
            FileWriter w = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(w);
            PrintWriter wr = new PrintWriter(bw);	
            wr.write(linea);//escribimos en el archivo 
            wr.close();
            bw.close();
        }catch(IOException e){};
    }

    public static MaxHeap<Sugerencia> sugerencias(Palabra p,  ArbolAVL<Palabra> a) {
        Lista<Sugerencia> ls = new Lista<Sugerencia>();
        Lista<Palabra> lp = a.inOrden();

        for (Palabra ap : lp) {
            ls.agregar(new Sugerencia(ap.getPalabra(), p.getPalabra()));
        }

        MaxHeap<Sugerencia> mhp = new MaxHeap<Sugerencia>(ls);

        return mhp;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jstexto = new javax.swing.JScrollPane();
        texto = new javax.swing.JTextPane();
        menu = new javax.swing.JMenuBar();
        documento = new javax.swing.JMenu();
        nuevo = new javax.swing.JMenuItem();
        cargar = new javax.swing.JMenuItem();
        guardar = new javax.swing.JMenuItem();
        salir = new javax.swing.JMenuItem();
        editar = new javax.swing.JMenu();
        ortografia = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jstexto.setViewportView(texto);

        menu.setForeground(new java.awt.Color(0, 0, 0));

        documento.setText("Documento");

        nuevo.setText("Nuevo");
        nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoActionPerformed(evt);
            }
        });
        documento.add(nuevo);

        cargar.setText("Cargar");
        cargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarActionPerformed(evt);
            }
        });
        documento.add(cargar);

        guardar.setText("Guardar");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        documento.add(guardar);

        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        documento.add(salir);

        menu.add(documento);

        editar.setText("Editar");

        ortografia.setText("Verificar ortografía ");
        ortografia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ortografiaActionPerformed(evt);
            }
        });
        editar.add(ortografia);

        menu.add(editar);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jstexto, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jstexto, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoActionPerformed
        // TODO add your handling code here:
        texto.setText("");
        texto.setText("Nuevo documento");
    }//GEN-LAST:event_nuevoActionPerformed

    private void cargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        int chooserValue = chooser.showOpenDialog(this);
        if (chooserValue == JFileChooser.APPROVE_OPTION){
            try {
                Scanner fin = new Scanner(chooser.getSelectedFile());
                String buffer = "";
                while (fin.hasNext()){
                    buffer += fin.nextLine() + "\n";
                }
                texto.setText(buffer);
                JOptionPane.showMessageDialog(this, "Cargado: " + chooser.getSelectedFile().getAbsolutePath());
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Archivo no encontrado.");
            }
        }
    }//GEN-LAST:event_cargarActionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        int chooserValue = chooser.showSaveDialog(this);
        if (chooserValue == JFileChooser.APPROVE_OPTION){
            try {
                PrintWriter fout = new PrintWriter(chooser.getSelectedFile());
                fout.print(texto.getText());
                fout.close();
                JOptionPane.showMessageDialog(this, "Guardado: " + chooser.getSelectedFile().getAbsolutePath());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(jffTareaFinal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_guardarActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_salirActionPerformed
    

    private void ortografiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ortografiaActionPerformed
        // TODO add your handling code here:
       
        //Cargamos el diccionario en un árbol AVL.    
        ArbolAVL<Palabra> a = new ArbolAVL<Palabra>(new Cola<Palabra>());
        
        try{
            cargaContenido("diccionario.txt", a);
            String pins;
            Palabra pin;
            int replyt = 0;

            String[] espacio = texto.getText().split(" ");
            int contesp = 0;

            while(contesp<espacio.length){
                pins = espacio[contesp];
                pin = new Palabra(pins);
                if (!a.contiene(pin)){
                    Lista<String> masParecidos = new Lista<String>();
                    MaxHeap<Sugerencia> mps = sugerencias(pin, a);
                    int ind = 0;
                    for (Sugerencia s : mps) {
                        masParecidos.agregar(s.getOriginal());
                        ind++;
                        if(pin.normaliza().equals(new Palabra(s.getOriginal()).normaliza())){
                            break;
                        }
                        if(ind>20){
                            JOptionPane.showMessageDialog(null, "La palabra " + pins + " NO está en el diccionario." + "\n" +
                                                                "Sugerencias: " + "\n" + masParecidos.toString());
                            int reply;
                            reply = JOptionPane.showConfirmDialog(null, "Si su palabra no está en las sugerencias, ¿desea agregarla al diccionario?", "Agregar palabra al diccionario",
                                                                  JOptionPane.YES_NO_OPTION);
                            if(reply == JOptionPane.YES_OPTION){
                                JOptionPane.showMessageDialog(null, "La palabra " + pins + " ha sido agregada.");
                                a.agrega(pin);
                                replyt++;
                            }else
                                JOptionPane.showMessageDialog(null, "La palabra " + pins + " NO ha sido agregada.");
                            break;
                        }
                    }
                } 
                contesp++;  
            }
            if(replyt > 0){
                JOptionPane.showMessageDialog(null, "Guardando cambios. Pulse aceptar para continuar." + "\n" +
                "Puede tardar cerca de un minuto, ver progreso en terminal."); 

                Lista<Palabra> lp = a.inOrden();
        
                File f;
                f = new File("diccionario.txt");
        
                int tam = (int) lp.longitud()/100;
                int prog = 0;
                int porc = 0;
                String dic = "";
                for (Palabra p : lp) {
                    if (prog==tam){
                        prog=0;
                        System.out.println(porc + "%");
                        porc++;
                    }
                    dic += p.toString()+'\n';
                    prog++;
                }
                System.out.println("100" + "%");
        
                escribir(f, dic);
                JOptionPane.showMessageDialog(null, "Finalizado, aceptar para continuar."); 
            }
    }catch(FileNotFoundException ex){;
    }catch(IOException ioe){;}
}//GEN-LAST:event_ortografiaActionPerformed

    

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
            java.util.logging.Logger.getLogger(jffTareaFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jffTareaFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jffTareaFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jffTareaFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new jffTareaFinal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem cargar;
    private javax.swing.JMenu documento;
    private javax.swing.JMenu editar;
    private javax.swing.JMenuItem guardar;
    private javax.swing.JScrollPane jstexto;
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenuItem nuevo;
    private javax.swing.JMenuItem ortografia;
    private javax.swing.JMenuItem salir;
    private javax.swing.JTextPane texto;
    // End of variables declaration//GEN-END:variables
}
