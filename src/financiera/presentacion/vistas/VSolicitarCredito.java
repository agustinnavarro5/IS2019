/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financiera.presentacion.vistas;

import financiera.dominio.Credito;
import financiera.dominio.Cuota;
import financiera.dominio.Financiera;
import financiera.dominio.Plan;
import financiera.presentacion.adaptador.IServicioPublicoCreditoInformarCreditoOtorgadoErrorServicioFaultFaultMessage;
import financiera.presentacion.adaptador.IServicioPublicoCreditoObtenerEstadoClienteErrorServicioFaultFaultMessage;
import financiera.presentacion.interfaces.ISolicitarCredito;
import financiera.presentacion.presentadores.PSolicitarCredito;
import financiera.presentacion.vistas.render.Render;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author usuario
 */
public class VSolicitarCredito extends javax.swing.JDialog implements ISolicitarCredito{

    /**
     * Creates new form VSolicitarCredito
     */
    PSolicitarCredito presentador;
    public VSolicitarCredito(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.presentador = new PSolicitarCredito(this);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    @Override
    public void crearCliente() {
        if(JOptionPane.showConfirmDialog(null, "El cliente no se encuentra registrado. ¿Desea registrarlo antes de continuar?",
                    "Cliente no registrado",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
            {
               new VRegistrarCliente(null, true, this.txtDni.getText());
            }
    }

    @Override
    public void informarDeudas() {
         JOptionPane.showMessageDialog(this, "El cliente posee deudas por lo que no es posible realizar un nuevo crédito.", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void informarCantidadCreditosActivosSuperado() {
        JOptionPane.showMessageDialog(this, "El cliente supera la cantidad de créditos activos permisible por la financiera(3).", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void cargarTablaCuotas(ArrayList<Cuota> cuotas) {
         boolean[] editable = {false,false,false};
        this.tbCuotas.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dt = new DefaultTableModel(new String[]{"NRO CUOTA", "FECHA VENCIMIENTO", "MONTO TOTAL"}, 0) {
 
            Class[] types = new Class[]{
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class
            };
 
            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
            
            @Override
            public boolean isCellEditable(int row, int column){
                return editable[column];
            }
        };
        if(cuotas.size() > 0){
            for(int i=0; i< cuotas.size(); i++){
                if(cuotas.get(i).getMontoAdeudado()>0){
                    Object fila[] = new Object[3];
                    fila[0] = cuotas.get(i).getNroCuota();
                    fila[1] = new SimpleDateFormat("dd/MM/yyyy").format(cuotas.get(i).getFechaVencimiento());
                    fila[2] = Math.round(cuotas.get(i).getMontoTotal()*100d)/100d;
                    dt.addRow(fila);
                }
            }
        }
        
        this.tbCuotas.setModel(dt);
    }

    @Override
    public void actualizarDetalleCredito(Credito credito) {
        this.lb1.setText("Monto Solicitado: $"+Math.round(credito.getMontoSolicitado()*100d)/100d);
        this.lb2.setText("Monto Total: $"+ Math.round(credito.getMontoTotal()*100d)/100d);
        this.lb3.setText("Importe de Cuota: $"+Math.round(credito.getCuotas().get(0).getMontoTotal()*100d)/100d);
        this.lb4.setText("Importe de Gastos: $"+Math.round(credito.getInteres()*100d)/100d);
        this.lb5.setText("Importe entregado al Cliente: $"+Math.round(credito.getMontoEntregado()*100d)/100d);
    }

    @Override
    public void informarRealizacion(Boolean operacionValida) {
        if(operacionValida)  JOptionPane.showMessageDialog(this, "El crédito ha sido informado al servicio externo y se ha realizado con éxito.", "Atención", JOptionPane.INFORMATION_MESSAGE);
        else  JOptionPane.showMessageDialog(this, "El crédito no se informó al servicio externo pero se realizó con éxito.", "Atención", JOptionPane.INFORMATION_MESSAGE);
            
    }

     @Override
    public void cargarTablaPlanes(ArrayList<Plan> planes) {
        boolean[] editable = {false,false,false,false,false,true};
        this.tbPlanes.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dt = new DefaultTableModel(new String[]{"NRO PLAN", "DESCRIPCIÓN", "NRO CUOTAS", "INTERÉS","GASTOS ADMIN.","PLAN OPTADO"}, 0) {
 
            Class[] types = new Class[]{
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Boolean.class
            };
 
            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
            
            @Override
            public boolean isCellEditable(int row, int column){
                return editable[column];
            }
        };
        if(planes.size() > 0){
        for(int i=0; i< planes.size(); i++){
        Object fila[] = new Object[6];
        
        fila[0] = planes.get(i).getNroPlan();
        fila[1] = planes.get(i).getDescripcion();
        fila[2] = planes.get(i).getNroCuotas();
        fila[3] = planes.get(i).getPorcentajeMensualInteres();
        fila[4] = planes.get(i).getPorcentajeGastosAdministrativo();
        fila[5] = false;
                

        dt.addRow(fila);
        
        }
        
        }
        
        this.tbPlanes.setModel(dt);
    }
    
    
     @Override
    public void bloquearAccesoTablaYMonto() {
        this.tbPlanes.setEnabled(false);
        this.txtMontoSolicitado.setEnabled(false);
        this.btnCalcular.setEnabled(false);
        this.btnAceptar.setEnabled(false);
    }
    public void permitirAccesoTablaYMonto() {
        this.tbPlanes.setEnabled(true);
        this.txtMontoSolicitado.setEnabled(true);
        this.txtDni.setEnabled(false);
        this.btnValidar.setEnabled(false);
        this.btnAceptar.setEnabled(true);
        this.btnCalcular.setEnabled(true);
    }
    
    
     public boolean esNumerico(String texto)
    {
        try{
            if(texto!=null){
                Double.parseDouble(texto);
                return true;
            }
        }catch(NumberFormatException nfe){
            return false;
        }
        return false;
    }
    public int cantidadPlanesOptados(){
        int cant = 0; 
        for(int i=0;i<this.tbPlanes.getRowCount();i++)
            {
                if((boolean)this.tbPlanes.getValueAt(i, 5))
                {
                    cant++;
                }
            }
        return cant;
    }
    public int getNumeroPlan(){
        for(int i=0;i<this.tbPlanes.getRowCount();i++)
            {
                if((boolean)this.tbPlanes.getValueAt(i, 5))
                {
                    return (int) this.tbPlanes.getValueAt(i, 0);
                }
            }
        return -1;
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnValidar = new javax.swing.JButton();
        txtDni = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPlanes = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtMontoSolicitado = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnCalcular = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbCuotas = new javax.swing.JTable();
        lb1 = new javax.swing.JLabel();
        lb2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lb4 = new javax.swing.JLabel();
        lb3 = new javax.swing.JLabel();
        lb5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Solicitar Crédito");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("DNI");

        btnValidar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnValidar.setText("Validar Cliente");
        btnValidar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValidarActionPerformed(evt);
            }
        });

        txtDni.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDni.setText("33000007");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Lista de Planes Disponibles");

        tbPlanes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tbPlanes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbPlanes);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Monto Solicitado:");

        txtMontoSolicitado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMontoSolicitado.setText("6000");
        txtMontoSolicitado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMontoSolicitadoActionPerformed(evt);
            }
        });

        btnAceptar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnCalcular.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCalcular.setText("Calcular Cuotas");
        btnCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Cuotas Mensuales");

        tbCuotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tbCuotas);

        lb1.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        lb1.setText("Monto Solicitado:");

        lb2.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        lb2.setText("Monto Total:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Detalle de Crédito");

        lb4.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        lb4.setText("Monto Solicitado:");

        lb3.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        lb3.setText("Monto Solicitado:");

        lb5.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        lb5.setText("Monto Solicitado:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(413, 413, 413))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(625, 625, 625)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtMontoSolicitado, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(85, 85, 85)
                                .addComponent(btnCalcular, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 923, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel1)
                .addGap(74, 74, 74)
                .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(148, 148, 148)
                .addComponent(btnValidar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lb2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lb3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addComponent(lb1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lb4, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                                    .addContainerGap()))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(lb5, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnValidar))
                .addGap(33, 33, 33)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMontoSolicitado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCalcular))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(lb1)
                        .addGap(38, 38, 38)
                        .addComponent(lb2)
                        .addGap(38, 38, 38)
                        .addComponent(lb3)
                        .addGap(38, 38, 38)
                        .addComponent(lb4)
                        .addGap(38, 38, 38)
                        .addComponent(lb5))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAceptar)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnCancelar)
                        .addGap(32, 32, 32))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnValidarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValidarActionPerformed
        String dni = this.txtDni.getText();
        boolean puedeRealizarCredito = false;
        try {
            puedeRealizarCredito = this.presentador.validarCliente(dni);
        } catch (IServicioPublicoCreditoObtenerEstadoClienteErrorServicioFaultFaultMessage ex) {
            Logger.getLogger(VSolicitarCredito.class.getName()).log(Level.SEVERE, null, ex);
        }
         if(puedeRealizarCredito){
             //Se habilitan las opciones de solo lectura
             this.permitirAccesoTablaYMonto();
         }
    }//GEN-LAST:event_btnValidarActionPerformed

    private void txtMontoSolicitadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMontoSolicitadoActionPerformed
       
    }//GEN-LAST:event_txtMontoSolicitadoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
       this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        try {
            this.presentador.finalizarCredito();
            this.dispose();
        } catch (IServicioPublicoCreditoInformarCreditoOtorgadoErrorServicioFaultFaultMessage ex) {
            Logger.getLogger(VSolicitarCredito.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularActionPerformed
        String monto = this.txtMontoSolicitado.getText();
        if(monto.equals("") || !this.esNumerico(monto)){
            JOptionPane.showMessageDialog(this, "Ingrese un monto correcto.", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            Double montoNumero = Double.parseDouble(monto);
            if( montoNumero<=Financiera.getInstance().getMontoSolicitadoMaximo() && this.cantidadPlanesOptados()==1){
                int nroPlan = this.getNumeroPlan();
                this.presentador.ingresarCredito(nroPlan, montoNumero);
            }else{
                    if( montoNumero>Financiera.getInstance().getMontoSolicitadoMaximo()){
                         JOptionPane.showMessageDialog(this, "Ingrese un monto menor a "+Financiera.getInstance().getMontoSolicitadoMaximo(), "Error", JOptionPane.ERROR_MESSAGE);
                    }else{
                        if(this.cantidadPlanesOptados()!=1){
                            JOptionPane.showMessageDialog(this, "Seleccione un plan para poder realizar el crédito.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
            }
        }
    }//GEN-LAST:event_btnCalcularActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCalcular;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnValidar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lb1;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lb3;
    private javax.swing.JLabel lb4;
    private javax.swing.JLabel lb5;
    private javax.swing.JTable tbCuotas;
    private javax.swing.JTable tbPlanes;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtMontoSolicitado;
    // End of variables declaration//GEN-END:variables

    
   

   
}
