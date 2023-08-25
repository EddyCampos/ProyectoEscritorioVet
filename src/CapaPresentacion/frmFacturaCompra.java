/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package CapaPresentacion;

import CapaEntidades.DetallesFacturas;
import CapaEntidades.FacturaTemporal;
import CapaEntidades.FacturasCompras;
import CapaEntidades.FacturasVentas;
import CapaEntidades.Productos;
import CapaLogica.LDetallesFactura;
import CapaLogica.LFacturaTemporal;
import CapaLogica.LFacturasCompra;
import CapaLogica.LProductos;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class frmFacturaCompra extends javax.swing.JInternalFrame {
    
    //Variable global
    DefaultTableModel modeloTabla;
    int idDetalle;

    //Variables globales
    ArrayList<FacturasCompras> listaFacturas = new ArrayList<>();
   
    public frmFacturaCompra() {
        initComponents();
        btnGenerar.setEnabled(false);
        try {
            PrepararTablaProductos();
        } 
        catch (Exception e) {
            
        }
    }
    
     //Genera entidad detalles facturas
    public DetallesFacturas GenerarEntidadDetallesFact(){
        DetallesFacturas detallesFacturas = new DetallesFacturas();
        detallesFacturas.setIdDetalle(-1);
        
        detallesFacturas.setIdEmpleado(Integer.parseInt(txtCodigoVendedor.getText()));
        detallesFacturas.setTipoPago(String.valueOf(ddlTipoPago.getSelectedItem()));
        detallesFacturas.setFecha(LocalDate.now());
        detallesFacturas.setTotal(Integer.parseInt(txtTotal.getText()));
        
        return detallesFacturas;
    }
    
    //Genera entidad factura temporal
    public FacturaTemporal GenerarEntidadFacturaTemporalProductos(Productos producto){
        FacturaTemporal factura = new FacturaTemporal(); 
        
        factura.setIdProducto(producto.getIdProducto());
        factura.setNombre(producto.getNombre());
        factura.setPeso(producto.getPeso());
        factura.setPrecio(producto.getPrecioCompra());
        factura.setCantidad(Integer.parseInt(txtCantidad.getText()));
        factura.setImpuesto(producto.getPrecioCompra()- (producto.getPrecioCompra()/ 1.13));
        factura.setTotal(factura.getCantidad() * (producto.getPrecioCompra() + factura.getImpuesto()));
        return factura;
    }
    
    //Genera la entidad de la factura
    public FacturasCompras GenerarEntidadFactura(){
        FacturasCompras facturaCompra = new FacturasCompras();
        
        if (txtCodigoFactura.getText().equals("")) {
            facturaCompra.setIdFactura(-1);
        }
        else{
            facturaCompra.setIdFactura(Integer.parseInt(txtCodigoFactura.getText()));
            facturaCompra.setExiste(true);
        }
        
        facturaCompra.setIdDetalle(idDetalle);
        facturaCompra.setIdProducto(Integer.parseInt(txtCodigoProducto.getText()));
        facturaCompra.setNombreProveedor(txtNombrePro.getText());
        facturaCompra.setTelefonoProveedor(txtTelefonoPro.getText());
        facturaCompra.setCantidad(Integer.parseInt(txtCantidad.getText()));
        
        return facturaCompra;
    }
    
    //Método para preparar la tabla de los prodcutos y que no se modifique
    public void PrepararTablaProductos(){
        modeloTabla = new DefaultTableModel(){
            //Sobreescribe método para que no se pueda editar
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        
        tblFacturasCompra.setModel(modeloTabla);
        modeloTabla.addColumn("Código");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Peso K");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Cantidad");
        modeloTabla.addColumn("Impuesto");
        modeloTabla.addColumn("Total");
    }
    
    //Método limpia las casillas
    public void LimpiarCasillas(){
        txtCodigoProducto.setText("");
        txtCodigoVendedor.setText("");
        txtNombrePro.setText("");
        txtTelefonoPro.setText("");
        txtCantidad.setText("");
        txtSubtotal.setText("");
        txtImpuesto.setText("");
        txtTotal.setText("");
        ddlTipoPago.setSelectedIndex(0);
    }
    
    //Método para listar los clientes en la tabla
    public void ListarProductosFactura(String condicion) throws Exception{
        try {
            LFacturaTemporal logica = new LFacturaTemporal();
            ResultSet datos;
            int sumaTotal = 0;
            int sumaImpuesto = 0;
            int sumaSubtotal = 0;
            
            PrepararTablaProductos();
            
            Object[] fila = new Object[7];
            datos = logica.ListarProductosFactura(condicion, "");
            
            while (datos.next()) {
                fila[0] = datos.getInt("ID_PRODUCTO");
                fila[1] = datos.getString("NOMBRE");
                fila[2] = datos.getString("PESO");
                fila[3] = datos.getString("PRECIO");
                fila[4] = datos.getString("CANTIDAD");
                fila[5] = datos.getString("IMPUESTO");
                fila[6] = datos.getString("TOTAL");
                modeloTabla.addRow(fila);
                
                sumaSubtotal += datos.getInt("PRECIO");
                sumaImpuesto += (datos.getInt("IMPUESTO") * datos.getInt("CANTIDAD"));
                sumaTotal += datos.getInt("TOTAL");
            }
            
            txtTotal.setText(String.valueOf(sumaTotal));
            txtSubtotal.setText(String.valueOf(sumaSubtotal));
            txtImpuesto.setText(String.valueOf(sumaImpuesto));
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

        jLabel17 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtCodigoFactura = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtCodigoProducto = new javax.swing.JTextPane();
        jLabel2 = new javax.swing.JLabel();
        btnBuscarPro = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtNombrePro = new javax.swing.JTextPane();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtTelefonoPro = new javax.swing.JTextPane();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtCantidad = new javax.swing.JTextPane();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblFacturasCompra = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtSubtotal = new javax.swing.JTextPane();
        jScrollPane8 = new javax.swing.JScrollPane();
        txtImpuesto = new javax.swing.JTextPane();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtTotal = new javax.swing.JTextPane();
        btnGenerar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        txtCodigoVendedor = new javax.swing.JTextPane();
        jLabel9 = new javax.swing.JLabel();
        btnBuscarEmpledo = new javax.swing.JButton();
        ddlTipoPago = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();

        jLabel17.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel17.setText("FACTURA COMPRA");

        txtCodigoFactura.setEnabled(false);
        jScrollPane1.setViewportView(txtCodigoFactura);

        jLabel1.setText("Código");

        txtCodigoProducto.setEnabled(false);
        jScrollPane2.setViewportView(txtCodigoProducto);

        jLabel2.setText("Código Producto");

        btnBuscarPro.setText("Buscar");
        btnBuscarPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProActionPerformed(evt);
            }
        });

        jScrollPane3.setViewportView(txtNombrePro);

        jLabel3.setText("Nombre Proveedor");

        jScrollPane4.setViewportView(txtTelefonoPro);

        jLabel4.setText("Teléfono Proveedor");

        jScrollPane5.setViewportView(txtCantidad);

        jLabel5.setText("Cantidad");

        tblFacturasCompra.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane6.setViewportView(tblFacturasCompra);

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        txtSubtotal.setEnabled(false);
        jScrollPane7.setViewportView(txtSubtotal);

        txtImpuesto.setEnabled(false);
        jScrollPane8.setViewportView(txtImpuesto);

        txtTotal.setEnabled(false);
        jScrollPane9.setViewportView(txtTotal);

        btnGenerar.setText("Generar");
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jLabel6.setText("Subtotal");

        jLabel7.setText("Impuestos");

        jLabel8.setText("Total");

        txtCodigoVendedor.setEnabled(false);
        jScrollPane10.setViewportView(txtCodigoVendedor);

        jLabel9.setText("Código Vendedor");

        btnBuscarEmpledo.setText("Buscar");
        btnBuscarEmpledo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarEmpledoActionPerformed(evt);
            }
        });

        ddlTipoPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "EFECTIVO", "TARJETA", "SINPE" }));

        jLabel10.setText("Tipo Pago");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAgregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ddlTipoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnBuscarPro))
                                        .addComponent(jScrollPane4))
                                    .addComponent(jLabel2))
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel5)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnBuscarEmpledo))
                                    .addComponent(jScrollPane5)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnGenerar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnBuscarPro)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBuscarEmpledo)
                        .addGap(1, 1, 1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel9)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(ddlTipoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(12, 12, 12)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Click boton buscar producto
    private void btnBuscarProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProActionPerformed
         frmBuscarProductos buscarProductos = new frmBuscarProductos(null, true);
    
        //Cuando se cierra recupera el id
        buscarProductos.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosed(WindowEvent e){
                try {
                    int id = buscarProductos.ObtenerId();
                    
                    if (id > -1) {
                        txtCodigoProducto.setText(String.valueOf(id));
                    }
                    else{
                       // LimpiarCasillas();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        
        });
        
        buscarProductos.setVisible(true);
    }//GEN-LAST:event_btnBuscarProActionPerformed

    //Cllick boton buscar empleado
    private void btnBuscarEmpledoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarEmpledoActionPerformed
        frmBuscarEmpleados buscarEmpleados = new frmBuscarEmpleados(null, true);
    
        //Cuando se cierra recupera el id
        buscarEmpleados.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosed(WindowEvent e){
                try {
                    int id = buscarEmpleados.ObtenerId();
                    
                    if (id > -1) {
                        txtCodigoVendedor.setText(String.valueOf(id));
                    }
                    else{
                       // LimpiarCasillas();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        
        });
        
        buscarEmpleados.setVisible(true);
    }//GEN-LAST:event_btnBuscarEmpledoActionPerformed

    //Click boton agregar
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        String condicion = "";
        Productos producto;
        
        try {
            FacturasCompras factura;
            LFacturaTemporal logicaFT = new LFacturaTemporal();
            LProductos logicaP = new LProductos();
            
            if (!txtCodigoProducto.getText().equals("") &&
            !txtCodigoVendedor.getText().equals("") &&
            !txtNombrePro.getText().equals("") &&
            !txtTelefonoPro.getText().equals("") &&
            !txtCantidad.getText().equals("")) {
                FacturasCompras facturaCompra = GenerarEntidadFactura();
                FacturaTemporal facturaTemporal;
                
                //Obtiene la info del producto que agregó
                condicion = "ID_PRODUCTO LIKE '%" + txtCodigoProducto.getText() + "%'";
                producto = logicaP.ObtenerProducto(condicion);

                facturaTemporal = GenerarEntidadFacturaTemporalProductos(producto);
                
                if (logicaFT.InsertarFacturaTemporal(facturaTemporal) > 0) {
                    ListarProductosFactura("");
                    JOptionPane.showMessageDialog(this, logicaFT.getMensaje());
                    btnBuscarEmpledo.setEnabled(false);
                    btnGenerar.setEnabled(true);

                    //Genera la entidad
                    factura = GenerarEntidadFactura();

                    //Guarda en un arreglo
                    listaFacturas.add(factura);
                }
                else{
                    JOptionPane.showMessageDialog(this, logicaFT.getMensaje());
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "Debe completar todos los campos primero");
            }//IF Datos completos
            
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    //Click boton salir
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        LFacturaTemporal logicaFTemporal = new LFacturaTemporal();
            
        try {
            if (logicaFTemporal.LimpiarFacturaTemporal() > 0) {
                this.dispose();
            }
        } 
        catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    //Click boton generar
    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
        try {
            FacturasCompras facturaCompra;
            DetallesFacturas detalleFactura;
            LFacturasCompra logicaCompra;
            LDetallesFactura logicaDetalles;
            LFacturaTemporal logicaFTemporal;
            
            logicaCompra = new LFacturasCompra();
            logicaDetalles = new LDetallesFactura();
            logicaFTemporal = new LFacturaTemporal();
            
            if (!txtCodigoProducto.getText().equals("") &&
            !txtCodigoVendedor.getText().equals("") &&
            !txtNombrePro.getText().equals("") &&
            !txtTelefonoPro.getText().equals("") &&
            !txtCantidad.getText().equals("")) {
                //Genera la entidad del detalle
                detalleFactura = GenerarEntidadDetallesFact();
                
                if (logicaDetalles.InsertarDetalleFacturas(detalleFactura) > 0) {
                    //Recupera el id del detalle compra
                    idDetalle = logicaDetalles.ObtenerDetalleFactura("");
                    //Genera la entidad de la factura
                    facturaCompra = GenerarEntidadFactura();
                    
                    //Guarda el id del detalle en cada uno
                    for (FacturasCompras listaFactura : listaFacturas) {
                        listaFactura.setIdDetalle(idDetalle);
                    }
                    
                    if (logicaCompra.InsertarFacturaCompra(listaFacturas) > 0) {
                        if (logicaFTemporal.LimpiarFacturaTemporal() > 0) {
                            JOptionPane.showMessageDialog(this, "Se registró correctamente la factura");
                            //Limpia la tabla y las casillas
                            ListarProductosFactura("");
                            LimpiarCasillas();
                        }
                        else{
                           JOptionPane.showMessageDialog(this, logicaFTemporal.getMensaje());
                        }//IF borrar tabla
                    }
                    else{
                        JOptionPane.showMessageDialog(this, logicaCompra.getMensaje());
                        
                    }//IF insertar factura
                }
                else{
                    JOptionPane.showMessageDialog(this, logicaDetalles.getMensaje());
                }//IF inserta detalle
            }
            else{
                JOptionPane.showMessageDialog(this, "Debe completar todos los campos primero");
            }//IF texto completo
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnGenerarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscarEmpledo;
    private javax.swing.JButton btnBuscarPro;
    private javax.swing.JButton btnGenerar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> ddlTipoPago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable tblFacturasCompra;
    private javax.swing.JTextPane txtCantidad;
    private javax.swing.JTextPane txtCodigoFactura;
    private javax.swing.JTextPane txtCodigoProducto;
    private javax.swing.JTextPane txtCodigoVendedor;
    private javax.swing.JTextPane txtImpuesto;
    private javax.swing.JTextPane txtNombrePro;
    private javax.swing.JTextPane txtSubtotal;
    private javax.swing.JTextPane txtTelefonoPro;
    private javax.swing.JTextPane txtTotal;
    // End of variables declaration//GEN-END:variables
}
