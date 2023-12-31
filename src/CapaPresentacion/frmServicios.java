//Eddy Campos Jiménez
//19 Agosto 2023
package CapaPresentacion;

import CapaEntidades.Servicios;
import CapaLogica.LServicios;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class frmServicios extends javax.swing.JInternalFrame {
    
    //Variable global
    DefaultTableModel modeloTabla;

    /**
     * Creates new form frmServicios
     */
    public frmServicios() {
        initComponents();
        try {
            ListarServicios("");
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());            
        }
    }
    
    //Método para preparar la tabla y que no se modifique
    public void PrepararTabla(){
        modeloTabla = new DefaultTableModel(){
            //Sobreescribe método para que no se pueda editar
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        
        tblServicios.setModel(modeloTabla);
        modeloTabla.addColumn("Código");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Tipo");
        modeloTabla.addColumn("Nombre Responsable");
        modeloTabla.addColumn("Teléfono Responsable");
        modeloTabla.addColumn("Precio");
    }
    
    //Método para borrar las valores de las casillas
    public void LimpiarCasillas(){
        txtCodigo.setText("");
        txtNombre.setText("");
        txtNombreResponsable.setText("");
        txtTelefonoResponsable.setText("");
        txtPrecio.setText("");
        ddlTipo.setSelectedItem("MEDICINA GENERAL");
    }
    
    //Método para listar los clientes en la tabla
    public void ListarServicios(String condicion) throws Exception{
        try {
            LServicios logica = new LServicios();
            ResultSet datos;
            
            PrepararTabla();
            
            Object[] fila = new Object[6];
            datos = logica.ListarServicios(condicion, "");
            
            while (datos.next()) {
                fila[0] = datos.getInt("ID_SERVICIO");
                fila[1] = datos.getString("NOMBRE");
                fila[2] = datos.getString("TIPO");
                fila[3] = datos.getString("NOMBRE_RESPONSABLE");
                fila[4] = datos.getString("TELEFONO_RESPONSABLE");
                fila[5] = datos.getString("PRECIO");
                modeloTabla.addRow(fila);
            }
        } 
        catch (Exception e) {
            throw  e;
        }
    }
    
    //Método para generar la entidad del cliente
    public Servicios GenerarEntidadServicio(){
        Servicios cliente = new Servicios();
        
        try {
            if (txtCodigo.getText().equals("")) {
                cliente.setIdServicio(-1);
            }
            else{
                cliente.setIdServicio(Integer.parseInt(txtCodigo.getText()));
                cliente.setExiste(true);
            }
            
            cliente.setNombre(txtNombre.getText());
            cliente.setTipo((String) ddlTipo.getSelectedItem());
            cliente.setNombreResponsable(txtNombreResponsable.getText());
            cliente.setTelefonoResponsable(txtTelefonoResponsable.getText());
            cliente.setPrecio(Integer.valueOf(txtPrecio.getText()));
           
        } 
        catch (Exception e) {
            throw e;
        }
        
        return cliente;
    }
    
    
    

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtCodigo = new javax.swing.JTextPane();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNombre = new javax.swing.JTextPane();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        ddlTipo = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtNombreResponsable = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtTelefonoResponsable = new javax.swing.JTextPane();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblServicios = new javax.swing.JTable();
        btnLimpiar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtPrecio = new javax.swing.JTextPane();

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel1.setText("SERVICIOS");

        txtCodigo.setEnabled(false);
        jScrollPane1.setViewportView(txtCodigo);

        jLabel2.setText("Código");

        jScrollPane2.setViewportView(txtNombre);

        jLabel3.setText("Nombre");

        jLabel4.setText("Tipo");

        ddlTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MEDICINA GENERAL", "LABORATORIO", "CIRUGIAS", "REDIOGRAFIA", "ESTETICA" }));

        jLabel5.setText("Nombre Responsable");

        jScrollPane3.setViewportView(txtNombreResponsable);

        jScrollPane4.setViewportView(txtTelefonoResponsable);

        jLabel6.setText("Teléfono Responsable");

        jLabel7.setText("Precio ");

        tblServicios.setModel(new javax.swing.table.DefaultTableModel(
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
        tblServicios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblServiciosMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblServicios);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jScrollPane7.setViewportView(txtPrecio);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnLimpiar)
                        .addGap(35, 35, 35)
                        .addComponent(btnGuardar)
                        .addGap(37, 37, 37)
                        .addComponent(btnEliminar)
                        .addGap(36, 36, 36)
                        .addComponent(btnSalir))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jSeparator1)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)
                                    .addComponent(jScrollPane3))
                                .addComponent(jLabel5))
                            .addGap(27, 27, 27)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                                    .addComponent(jLabel3)
                                    .addComponent(jScrollPane4))
                                .addComponent(jLabel6))
                            .addGap(27, 27, 27)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel7)
                                .addComponent(jLabel4)
                                .addComponent(ddlTipo, 0, 196, Short.MAX_VALUE)
                                .addComponent(jScrollPane7)))
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(ddlTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimpiar)
                    .addComponent(btnGuardar)
                    .addComponent(btnEliminar)
                    .addComponent(btnSalir))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Click boton guardar
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            LServicios logica = new LServicios();
            
            if (!txtNombre.getText().equals("") &&
            !txtNombreResponsable.getText().equals("") &&
            !txtTelefonoResponsable.getText().equals("") &&
            !txtPrecio.getText().equals("")) {
                
                Servicios servicio = GenerarEntidadServicio();
                
                if (!servicio.isExiste()) {
                    if (logica.InsertarServicio(servicio) > 0) {
                        JOptionPane.showMessageDialog(this, logica.getMensaje());
                        LimpiarCasillas();
                        ListarServicios("");
                    }
                    else{
                        JOptionPane.showMessageDialog(this, logica.getMensaje());
                    }
                }
                else{
                     if (logica.ActualizarServicio(servicio) > 0) {
                        JOptionPane.showMessageDialog(this, logica.getMensaje());
                        LimpiarCasillas();
                        ListarServicios("");
                    }
                    else{
                        JOptionPane.showMessageDialog(this, logica.getMensaje());
                    }
                }
                
            }
            else{
                JOptionPane.showMessageDialog(this, "Debe completar todos los campos primero");
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    //Boton Salir
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    //Boton Limpiar
    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        LimpiarCasillas();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    //Click a un elemento de la tabla
    private void tblServiciosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblServiciosMouseClicked
        //Llamar a obtener un servicio de la logica        
        try {
            LServicios logica = new LServicios();
            Servicios servicio;
            String condicion;

            //Si da dos clicks
            if (evt.getClickCount() == 2) {
                //Obtiene toda la fila
                int fila = tblServicios.rowAtPoint(evt.getPoint());
                txtCodigo.setText(tblServicios.getValueAt(fila, 0).toString());//Obtiene de la tabla
                
                condicion = String.format("ID_SERVICIO= %s", txtCodigo.getText());
                servicio = logica.ObtenerServicio(condicion);//Obtiene del cliente
                
                txtCodigo.setText(String.valueOf(servicio.getIdServicio()));
                txtNombre.setText(servicio.getNombre());
                txtNombreResponsable.setText(servicio.getNombreResponsable());
                txtTelefonoResponsable.setText(servicio.getTelefonoResponsable());
                ddlTipo.setSelectedItem(servicio.getTipo());
                txtPrecio.setText(String.valueOf(servicio.getPrecio()));
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_tblServiciosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> ddlTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tblServicios;
    private javax.swing.JTextPane txtCodigo;
    private javax.swing.JTextPane txtNombre;
    private javax.swing.JTextPane txtNombreResponsable;
    private javax.swing.JTextPane txtPrecio;
    private javax.swing.JTextPane txtTelefonoResponsable;
    // End of variables declaration//GEN-END:variables
}
