/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package CapaPresentacion;

import CapaEntidades.Empleados;
import CapaLogica.LEmpleados;
import java.sql.ResultSet;
import java.sql.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class frmEmpleados extends javax.swing.JInternalFrame {

    //Variable global
    DefaultTableModel modeloTabla;
    
    public frmEmpleados() {
        initComponents();
        
        try {
            ListarEmpleados("");
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
        
        tblEmpleados.setModel(modeloTabla);
        modeloTabla.addColumn("Código");
        modeloTabla.addColumn("Tipo");
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
    
    //Método para generar la entidad del empleado
    public Empleados GenerarEntidadEmpleado() throws Exception {
        Empleados empleado = new Empleados();
        
        try {
            if (txtCodigo.getText().equals("")) {
                empleado.setIdEmpleado(-1);
            }
            else{
                empleado.setIdEmpleado(Integer.parseInt(txtCodigo.getText()));
                empleado.setExiste(true);
            }
            
            //Recupera la fecha tipo util date y la convierte a sql date
            java.util.Date fechaDate = dtpFechaNacimiento.getDate();
            java.sql.Date fechaSQL = new java.sql.Date(fechaDate.getTime());
            
            empleado.setTipoEmpleado(ddlTipo.getSelectedItem().toString());
            empleado.setNombre(txtNombre.getText());
            empleado.setApellido01(txtApellido1.getText());
            empleado.setApellido02(txtApellido2.getText());
            empleado.setCedula(txtCedula.getText());
            empleado.setProvincia((String) ddlProvincia.getSelectedItem());
            empleado.setEstadoCivil((String) ddlEstadoCivil.getSelectedItem());
            empleado.setGenero((String) ddlGenero.getSelectedItem());
            empleado.setFechaNacimiento(fechaSQL);
            empleado.setEmail(txtEmail.getText());
            empleado.setTelefono(txtTelefono.getText());
            
        } 
        catch (Exception e) {
            throw e;
        }
        
        return empleado;
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
        ddlTipo.setSelectedItem("VENDEDOR");
    }
    
    //Método para listar los clientes en la tabla
    public void ListarEmpleados(String condicion) throws Exception{
        try {
            LEmpleados logica = new LEmpleados();
            ResultSet datos;
            
            PrepararTabla();
            
            Object[] fila = new Object[12];
            datos = logica.ListarEmpleados(condicion, "");
            
            while (datos.next()) {
                fila[0] = datos.getInt("ID_EMPLEADO");
                fila[1] = datos.getString("TIPO");
                fila[2] = datos.getString("NOMBRE");
                fila[3] = datos.getString("APELLIDO01");
                fila[4] = datos.getString("APELLIDO02");
                fila[5] = datos.getString("CEDULA");
                fila[6] = datos.getString("PROVINCIA");
                fila[7] = datos.getString("ESTADO_CIVIL");
                fila[8] = datos.getString("GENERO");
                fila[9] = datos.getString("FECHA_NACIMIENTO");
                fila[10] = datos.getString("EMAIL");
                fila[11] = datos.getString("TELEFONO");
                
                modeloTabla.addRow(fila);
            }
        } 
        catch (Exception e) {
            throw  e;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNombre = new javax.swing.JTextPane();
        jLabel10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtEmail = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtApellido1 = new javax.swing.JTextPane();
        jLabel11 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtTelefono = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtCedula = new javax.swing.JTextPane();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtApellido2 = new javax.swing.JTextPane();
        ddlProvincia = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        ddlEstadoCivil = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        ddlGenero = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtCodigo = new javax.swing.JTextPane();
        jLabel3 = new javax.swing.JLabel();
        dtpFechaNacimiento = new com.toedter.calendar.JDateChooser();
        ddlTipo = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblEmpleados = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel1.setText("EMPLEADOS");

        jScrollPane2.setViewportView(txtNombre);

        jLabel10.setText("Fecha Nacimiento");

        jLabel4.setText("Primer Apellido");

        jScrollPane6.setViewportView(txtEmail);

        jScrollPane3.setViewportView(txtApellido1);

        jLabel11.setText("Email");

        jLabel5.setText("Segundo Apellido");

        jLabel6.setText("Cédula");

        jScrollPane7.setViewportView(txtTelefono);

        jScrollPane4.setViewportView(txtCedula);

        jLabel12.setText("Teléfono");

        jScrollPane5.setViewportView(txtApellido2);

        ddlProvincia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ALAJUELA", "SAN JOSE", "CARTAGO", "HEREDIA", "PUNTARENAS", "GUANACASTE", "LIMON" }));

        jLabel7.setText("Provincia");

        ddlEstadoCivil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SOLTERO", "CASADO", "DIVORCIADO", "VIUDO" }));

        jLabel8.setText("Estado Civil");

        ddlGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MASCULINO", "FEMENINO", "NO INDICA" }));

        jLabel9.setText("Genero");

        jLabel2.setText("Código");

        txtCodigo.setEnabled(false);
        jScrollPane1.setViewportView(txtCodigo);

        jLabel3.setText("Nombre");

        ddlTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "VENDEDOR", "BODEGUERO", "MEDICO VETERINARIO", "ESTETICISTA CANINO", "OTRO" }));

        jLabel13.setText("Tipo");

        tblEmpleados.setModel(new javax.swing.table.DefaultTableModel(
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
        tblEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmpleadosMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblEmpleados);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1090, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 1090, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnLimpiar)
                                .addGap(34, 34, 34)
                                .addComponent(btnGuardar)
                                .addGap(32, 32, 32)
                                .addComponent(btnEliminar)
                                .addGap(32, 32, 32)
                                .addComponent(btnSalir)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel2)
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                                            .addComponent(jLabel7)
                                            .addComponent(ddlProvincia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(29, 29, 29)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel3)
                                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(27, 27, 27)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel4)
                                                        .addComponent(ddlGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel9)))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel12)
                                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(ddlEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel8)))
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dtpFechaNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel10)
                                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel13))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(894, 894, 894)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(ddlTipo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
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
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ddlProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ddlEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ddlGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dtpFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ddlTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimpiar)
                    .addComponent(btnGuardar)
                    .addComponent(btnEliminar)
                    .addComponent(btnSalir))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Boton Salir
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    //Click boton guardar
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            LEmpleados logica = new LEmpleados();
            
            if (!txtNombre.getText().equals("") &&
            !txtApellido1.getText().equals("") &&
            !txtApellido1.getText().equals("") &&
            !txtApellido2.getText().equals("") &&
            !txtCedula.getText().equals("") &&
            !txtEmail.getText().equals("") &&
            !txtTelefono.getText().equals("")) {
                
                Empleados empleado = GenerarEntidadEmpleado();
                
                if (!empleado.isExiste()) {
                    if (logica.InsertarEmpleados(empleado) > 0) {
                        JOptionPane.showMessageDialog(this, logica.getMensaje());
                        LimpiarCasillas();
                        ListarEmpleados("");
                        txtCedula.setEnabled(true);
                    }
                    else{
                        JOptionPane.showMessageDialog(this, logica.getMensaje());
                    }
                }
                else{
                     if (logica.ActualizarEmpleado(empleado) > 0) {
                        JOptionPane.showMessageDialog(this, logica.getMensaje());
                        LimpiarCasillas();
                        ListarEmpleados("");
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
    private void tblEmpleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmpleadosMouseClicked
        //Llamar a obtener un cliente de la logica        
        try {
            txtCedula.setEnabled(false);
            
            LEmpleados logica = new LEmpleados();
            Empleados empleado;
            String condicion;

            //Si da dos clicks
            if (evt.getClickCount() == 2) {
                //Obtiene toda la fila
                int fila = tblEmpleados.rowAtPoint(evt.getPoint());
                txtCodigo.setText(tblEmpleados.getValueAt(fila, 0).toString());//Obtiene de la tabla
                
                condicion = String.format("ID_EMPLEADO= %s", txtCodigo.getText());
                empleado = logica.ObtenerCliente(condicion);//Obtiene del cliente
                
                //Recupera la fecha tipo util date y la convierte a sql date
                java.util.Date fechaDate = dtpFechaNacimiento.getDate();
                Date fechaSQL = new Date(fechaDate.getTime());
                
                txtCodigo.setText(String.valueOf(empleado.getIdEmpleado()));
                ddlTipo.setSelectedItem(empleado.getTipoEmpleado());
                txtNombre.setText(empleado.getNombre());
                txtApellido1.setText(empleado.getApellido01());
                txtApellido2.setText(empleado.getApellido02());
                txtCedula.setText(empleado.getCedula());
                ddlProvincia.setSelectedItem(empleado.getProvincia());
                ddlEstadoCivil.setSelectedItem(empleado.getEstadoCivil());
                ddlGenero.setSelectedItem(empleado.getGenero());
                dtpFechaNacimiento.setDate(fechaSQL);
                txtEmail.setText(empleado.getEmail());
                txtTelefono.setText(empleado.getTelefono());
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_tblEmpleadosMouseClicked

    //Click boton limpiar
    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        LimpiarCasillas();
        txtCedula.setEnabled(true);
    }//GEN-LAST:event_btnLimpiarActionPerformed

    //Click boton eliminar
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        LEmpleados logica = new LEmpleados();
        Empleados empleado;
        
        try {
            empleado = GenerarEntidadEmpleado();
            
            if (empleado.isExiste()) {
                if (logica.EliminarEmpleado(empleado) > 0) {
                    JOptionPane.showMessageDialog(this, logica.getMensaje());
                    ListarEmpleados("");
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
    private javax.swing.JComboBox<String> ddlTipo;
    private com.toedter.calendar.JDateChooser dtpFechaNacimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tblEmpleados;
    private javax.swing.JTextPane txtApellido1;
    private javax.swing.JTextPane txtApellido2;
    private javax.swing.JTextPane txtCedula;
    private javax.swing.JTextPane txtCodigo;
    private javax.swing.JTextPane txtEmail;
    private javax.swing.JTextPane txtNombre;
    private javax.swing.JTextPane txtTelefono;
    // End of variables declaration//GEN-END:variables
}
