/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financiera.presentacion.vistas;

import financiera.seguridad.Sesion;

/**
 *
 * @author usuario
 */
public class VPrincipal extends javax.swing.JDialog {

    /**
     * Creates new form VPrincipal
     */
    public VPrincipal(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle(Sesion.getInstancia().getUsuarioActivo().getNombreUsuario());
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jRadioButtonMenuItem2 = new javax.swing.JRadioButtonMenuItem();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mSolicitarCredito = new javax.swing.JMenuItem();
        mAbonarCuotas = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        mGestionarCliente = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        mConfiguracion = new javax.swing.JMenuItem();
        mCerrarSesion = new javax.swing.JMenuItem();
        mSalir = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        jMenuItem2.setText("jMenuItem2");

        jMenu5.setText("File");
        jMenuBar3.add(jMenu5);

        jMenu6.setText("Edit");
        jMenuBar3.add(jMenu6);

        jRadioButtonMenuItem2.setSelected(true);
        jRadioButtonMenuItem2.setText("jRadioButtonMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jMenu1.setText("Credito");

        mSolicitarCredito.setText("Solicitar Credito");
        mSolicitarCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mSolicitarCreditoActionPerformed(evt);
            }
        });
        jMenu1.add(mSolicitarCredito);

        mAbonarCuotas.setText("Abonar Cuotas");
        mAbonarCuotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mAbonarCuotasActionPerformed(evt);
            }
        });
        jMenu1.add(mAbonarCuotas);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Cliente");

        mGestionarCliente.setText("Gestionar Cliente");
        mGestionarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mGestionarClienteActionPerformed(evt);
            }
        });
        jMenu2.add(mGestionarCliente);

        jMenuBar1.add(jMenu2);

        jMenu7.setText("Financiera");

        mConfiguracion.setText("Configuración de Parámetros");
        jMenu7.add(mConfiguracion);

        mCerrarSesion.setText("Cerrar Sesión");
        mCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mCerrarSesionActionPerformed(evt);
            }
        });
        jMenu7.add(mCerrarSesion);

        mSalir.setText("Salir");
        mSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mSalirActionPerformed(evt);
            }
        });
        jMenu7.add(mSalir);

        jMenuBar1.add(jMenu7);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mSolicitarCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mSolicitarCreditoActionPerformed
        new VSolicitarCredito(null,true);
    }//GEN-LAST:event_mSolicitarCreditoActionPerformed

    private void mAbonarCuotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mAbonarCuotasActionPerformed
        new VAbonarCuota(null,true);
    }//GEN-LAST:event_mAbonarCuotasActionPerformed

    private void mGestionarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mGestionarClienteActionPerformed
       new VGestionCliente(null,true);
    }//GEN-LAST:event_mGestionarClienteActionPerformed

    private void mSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mSalirActionPerformed

        this.dispose();
        System.out.close();
    }//GEN-LAST:event_mSalirActionPerformed

    private void mCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mCerrarSesionActionPerformed
        Sesion.getInstancia().setUsuarioActivo(null);
        this.dispose();
        new VAutenticarUsuario(null,true);
    }//GEN-LAST:event_mCerrarSesionActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem2;
    private javax.swing.JMenuItem mAbonarCuotas;
    private javax.swing.JMenuItem mCerrarSesion;
    private javax.swing.JMenuItem mConfiguracion;
    private javax.swing.JMenuItem mGestionarCliente;
    private javax.swing.JMenuItem mSalir;
    private javax.swing.JMenuItem mSolicitarCredito;
    // End of variables declaration//GEN-END:variables
}