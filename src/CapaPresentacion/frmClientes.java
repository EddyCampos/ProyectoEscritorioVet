//Eddy Campos Jiménez
//19 Agosto 2023
package CapaPresentacion;

import CapaEntidades.Clientes;
import CapaLogica.LClientes;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;

public class frmClientes extends javax.swing.JInternalFrame {

    //Variable global
    DefaultTableModel modeloTabla;
    
    public frmClientes() {
        initComponents();
        
        try {
            ListarClientes("");
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
        
        tblClientes.setModel(modeloTabla);
        modeloTabla.addColumn("Código");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("1° Apellido");
        modeloTabla.addColumn("2° Apellido");
        modeloTabla.addColumn("Cédula");
        modeloTabla.addColumn("Provincia");
        modeloTabla.addColumn("Estado Civil");
        modeloTabla.addColumn("Genero");
        modeloTabla.addColumn("Fecha Nacimiento");
        modeloTabla.addColumn("Email");
        modeloTabla.addColumn("Teléfono");
    }
    
    //Método para listar los clientes en la tabla
    public void ListarClientes(String condicion) throws Exception{
        try {
            LClientes logica = new LClientes();
            ResultSet datos;
            
            PrepararTabla();
            
            Object[] fila = new Object[11];
            datos = logica.ListarClientes(condicion, "");
            
            while (datos.next()) {
                fila[0] = datos.getInt("ID_CLIENTE");
                fila[1] = datos.getString("NOMBRE");
                fila[2] = datos.getString("APELLIDO01");
                fila[3] = datos.getString("APELLIDO02");
                fila[4] = datos.getString("CEDULA");
                fila[5] = datos.getString("PROVINCIA");
                fila[6] = datos.getString("ESTADO_CIVIL");
                fila[7] = datos.getString("GENERO");
                fila[8] = datos.getString("FECHA_NACIMIENTO");
                fila[9] = datos.getString("EMAIL");
                fila[10] = datos.getString("TELEFONO");
                modeloTabla.addRow(fila);
            }
        } 
        catch (Exception e) {
            throw  e;
        }
    }
    
    //Método para generar la entidad del cliente
    public Clientes GenerarEntidadCliente(){
        Clientes cliente = new Clientes();
        Date fechaBaseDatos;
        
        try {
            if (txtCodigo.getText().equals("")) {
                cliente.setIdCLiente(-1);
            }
            else{
                cliente.setIdCLiente(Integer.parseInt(txtCodigo.getText()));
                cliente.setExiste(true);
            }
            
            //Recupera la fecha tipo util date y la convierte a sql date
            java.util.Date fechaDate = dtpFechaNacimiento.getDate();
            Date fechaSQL = new Date(fechaDate.getTime());
            
            cliente.setNombre(txtNombre.getText());
            cliente.setApellido01(txtApellido1.getText());
            cliente.setApellido02(txtApellido2.getText());
            cliente.setCedula(txtCedula.getText());
            cliente.setProvincia((String) ddlProvincia.getSelectedItem());
            cliente.setEstadoCivil((String) ddlEstadoCivil.getSelectedItem());
            cliente.setGenero((String) ddlGenero.getSelectedItem());
            cliente.setFechaNacimiento(fechaSQL);
            cliente.setEmail(txtEmail.getText());
            cliente.setTelefono(txtTelefono.getText());
        } 
        catch (Exception e) {
            throw e;
        }
        
        return cliente;
    }
    
    //Método para borrar las valores de las casillas
    public void LimpiarCasillas(){
        txtCodigo.setText("");
        txtNombre.setText("");
        txtApellido1.setText("");
        txtApellido2.setText("");
        txtCedula.setText("");
        ddlProvincia.setSelectedItem("ALAJUELA");
        ddlEstadoCivil.setSelectedItem("SOLTERO");
        ddlGenero.setSelectedItem("MASCULINO");
        dtpFechaNacimiento.setDate(null);
        txtEmail.setText("");
        txtTelefono.setText("");
    }
    

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane8 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtCodigo = new javax.swing.JTextPane();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNombre = new javax.swing.JTextPane();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtApellido1 = new javax.swing.JTextPane();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtCedula = new javax.swing.JTextPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtApellido2 = new javax.swing.JTextPane();
        ddlProvincia = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        ddlEstadoCivil = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        ddlGenero = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        dtpFechaNacimiento = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtEmail = new javax.swing.JTextPane();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtTelefono = new javax.swing.JTextPane();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        btnLimpiar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane8.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel1.setText("CLIENTES");

        jLabel2.setText("Código");

        txtCodigo.setEnabled(false);
        jScrollPane1.setViewportView(txtCodigo);

        jLabel3.setText("Nombre");

        jScrollPane2.setViewportView(txtNombre);

        jLabel4.setText("Primer Apellido");

        jScrollPane3.setViewportView(txtApellido1);

        jLabel5.setText("Segundo Apellido");

        jLabel6.setText("Cédula");

        jScrollPane4.setViewportView(txtCedula);

        jScrollPane5.setViewportView(txtApellido2);

        ddlProvincia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ALAJUELA", "SAN JOSE", "CARTAGO", "HEREDIA", "PUNTARENAS", "GUANACASTE", "LIMON" }));

        jLabel7.setText("Provincia");

        ddlEstadoCivil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SOLTERO", "CASADO", "DIVORCIADO", "VIUDO" }));

        jLabel8.setText("Estado Civil");

        ddlGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MASCULINO", "FEMENINO", "NO INDICA" }));

        jLabel9.setText("Genero");

        jLabel10.setText("Fecha Nacimiento");

        jScrollPane6.setViewportView(txtEmail);

        jLabel11.setText("Email");

        jScrollPane7.setViewportView(txtTelefono);

        jLabel12.setText("Teléfono");

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
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
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tblClientes);

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
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnLimpiar)
                        .addGap(33, 33, 33)
                        .addComponent(btnGuardar)
                        .addGap(36, 36, 36)
                        .addComponent(btnEliminar)
                        .addGap(32, 32, 32)
                        .addComponent(btnSalir))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 867, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(dtpFechaNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel6)
                                    .addComponent(jScrollPane4)
                                    .addComponent(jScrollPane1))
                                .addComponent(jLabel10))
                            .addGap(29, 29, 29)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(ddlProvincia, 0, 196, Short.MAX_VALUE)
                                            .addComponent(jLabel3)
                                            .addComponent(jScrollPane2)
                                            .addComponent(jLabel7))
                                        .addGap(27, 27, 27)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(ddlEstadoCivil, javax.swing.GroupLayout.Alignment.LEADING, 0, 196, Short.MAX_VALUE)
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
                                            .addComponent(jLabel8)))
                                    .addComponent(jScrollPane6))
                                .addComponent(jLabel11))
                            .addGap(27, 27, 27)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel5)
                                    .addComponent(jScrollPane5)
                                    .addComponent(ddlGenero, 0, 196, Short.MAX_VALUE)
                                    .addComponent(jScrollPane7))))
                        .addComponent(jSeparator1)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3))
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ddlProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ddlEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ddlGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dtpFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimpiar)
                    .addComponent(btnGuardar)
                    .addComponent(btnEliminar)
                    .addComponent(btnSalir))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Boton salir
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    //Boton Guardar
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            LClientes logica = new LClientes();
            
            if (!txtNombre.getText().equals("") &&
            !txtApellido1.getText().equals("") &&
            !txtApellido2.getText().equals("") &&
            !txtCedula.getText().equals("") &&
            !txtEmail.getText().equals("") &&
            !txtTelefono.getText().equals("")) {
                
                Clientes cliente = GenerarEntidadCliente();
                
                if (!cliente.isExiste()) {
                    if (logica.InsertarCliente(cliente) > 0) {
                        JOptionPane.showMessageDialog(this, logica.getMensaje());
                        LimpiarCasillas();
                        ListarClientes("");
                        txtCedula.setEnabled(true);
                    }
                    else{
                        JOptionPane.showMessageDialog(this, logica.getMensaje());
                    }
                }
                else{
                     if (logica.ActualizarCliente(cliente) > 0) {
                        JOptionPane.showMessageDialog(this, logica.getMensaje());
                        LimpiarCasillas();
                        ListarClientes("");
                        txtCedula.setEnabled(true);
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

    //Click a un elemento de la tabla
    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        //Llamar a obtener un cliente de la logica        
        try {
            txtCedula.setEnabled(false);
            LClientes logica = new LClientes();
            Clientes cliente;
            String condicion;

            //Si da dos clicks
            if (evt.getClickCount() == 2) {
                //Obtiene toda la fila
                int fila = tblClientes.rowAtPoint(evt.getPoint());
                txtCodigo.setText(tblClientes.getValueAt(fila, 0).toString());//Obtiene de la tabla
                
                condicion = String.format("ID_CLIENTE= %s", txtCodigo.getText());
                cliente = logica.ObtenerCliente(condicion);//Obtiene del cliente
                
                //Recupera la fecha tipo util date y la convierte a sql date
                java.util.Date fechaDate = dtpFechaNacimiento.getDate();
                Date fechaSQL = new Date(fechaDate.getTime());
                
                txtCodigo.setText(String.valueOf(cliente.getIdCLiente()));
                txtNombre.setText(cliente.getNombre());
                txtApellido1.setText(cliente.getApellido01());
                txtApellido2.setText(cliente.getApellido02());
                txtCedula.setText(cliente.getCedula());
                ddlProvincia.setSelectedItem(cliente.getProvincia());
                ddlEstadoCivil.setSelectedItem(cliente.getEstadoCivil());
                ddlGenero.setSelectedItem(cliente.getGenero());
                dtpFechaNacimiento.setDate(fechaSQL);
                txtEmail.setText(cliente.getEmail());
                txtTelefono.setText(cliente.getTelefono());
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_tblClientesMouseClicked

    //Click Boton Limpiar
    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        LimpiarCasillas();
        txtCedula.setEnabled(true);
    }//GEN-LAST:event_btnLimpiarActionPerformed

    //Click Boton Eliminar
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        LClientes logica = new LClientes();
        Clientes cliente;
        
        try {
            cliente = GenerarEntidadCliente();
            
            if (cliente.isExiste()) {
                if (logica.EliminarCliente(cliente) > 0) {
                    JOptionPane.showMessageDialog(this, logica.getMensaje());
                    ListarClientes("");
                    LimpiarCasillas();
                }
                else{
                    JOptionPane.showMessageDialog(this, logica.getMensaje());
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "Debe seleccionar un cliente");
                
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> ddlEstadoCivil;
    private javax.swing.JComboBox<String> ddlGenero;
    private javax.swing.JComboBox<String> ddlProvincia;
    private com.toedter.calendar.JDateChooser dtpFechaNacimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextPane txtApellido1;
    private javax.swing.JTextPane txtApellido2;
    private javax.swing.JTextPane txtCedula;
    private javax.swing.JTextPane txtCodigo;
    private javax.swing.JTextPane txtEmail;
    private javax.swing.JTextPane txtNombre;
    private javax.swing.JTextPane txtTelefono;
    // End of variables declaration//GEN-END:variables
}
