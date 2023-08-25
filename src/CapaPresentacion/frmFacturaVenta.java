//Eddy Campos Jiménez
//21 Agosto 2023
package CapaPresentacion;

import CapaEntidades.DetallesFacturas;
import CapaEntidades.FacturaTemporal;
import CapaEntidades.FacturasVentas;
import CapaEntidades.Productos;
import CapaEntidades.Servicios;
import CapaLogica.LDetallesFactura;
import CapaLogica.LFacturaTemporal;
import CapaLogica.LFacturasVenta;
import CapaLogica.LProductos;
import CapaLogica.LServicios;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

public class frmFacturaVenta extends javax.swing.JInternalFrame {
    
    //Variable global
    DefaultTableModel modeloTabla;
    int idDetalle;

    //Variables globales
    ArrayList<FacturasVentas> listaFacturas = new ArrayList<>();
    
    //Ejecuta cada vez que se cierra el form
    public void internalFrameClosed(InternalFrameEvent e) throws Exception {
        LFacturaTemporal logicaFTemporal = new LFacturaTemporal();
            
            if (logicaFTemporal.LimpiarFacturaTemporal() > 0) {
                this.dispose();
            }
    }
        
    public frmFacturaVenta() {
        initComponents();
        lblServicio.setEnabled(false);
        btnBuscarServicio.setEnabled(false);
        btnFinalizar.setEnabled(false);
        
        PrepararTablaProductos();
        
        //Se ejecuta cuando se cierra el formulario
        JInternalFrame internalFrame = new JInternalFrame("Formulario Interno", true, true, true, true);
        
        internalFrame.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                System.out.println("InternalFrame closed: " + internalFrame.getTitle());
                // Llamar a tu método aquí
            }
        });
        
        
        
    }
    
    
    
    //Genera la entidad de la factura
    public FacturasVentas GenerarEntidadFactura(){
        FacturasVentas facturaVenta = new FacturasVentas();
        
        if (txtCodigoFactura.getText().equals("")) {
            facturaVenta.setIdFactura(-1);
        }
        else{
            facturaVenta.setIdFactura(Integer.parseInt(txtCodigoFactura.getText()));
            facturaVenta.setExiste(true);
        }
        
        facturaVenta.setIdDetalle(idDetalle);
        
        facturaVenta.setIdCliente(Integer.parseInt(txtCodigoCliente.getText()));
        
        if (!txtCodigoProducto.getText().equals("")) {
            facturaVenta.setIdProd(Integer.parseInt(txtCodigoProducto.getText()));
            facturaVenta.setidServ(0);
        }
        else{
            facturaVenta.setidServ(Integer.parseInt(txtCodigoServicio.getText()));
            facturaVenta.setIdProd(0);
        }
        
        facturaVenta.setCantidad(Integer.parseInt(txtCantidad.getText()));
        
        return facturaVenta;
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
        factura.setPrecio(producto.getPrecioVenta());
        factura.setCantidad(Integer.parseInt(txtCantidad.getText()));
        factura.setImpuesto(producto.getPrecioVenta() - (producto.getPrecioVenta() / 1.13));
        factura.setTotal(factura.getCantidad() * (producto.getPrecioVenta() + factura.getImpuesto()));
        return factura;
    }
    
     //Genera entidad factura temporal
    public FacturaTemporal GenerarEntidadFacturaTemporalServicios(Servicios servicio){
        FacturaTemporal factura = new FacturaTemporal(); 
        
        factura.setIdProducto(servicio.getIdServicio());
        factura.setNombre(servicio.getNombre());
        factura.setPeso(0);
        factura.setPrecio(servicio.getPrecio());
        factura.setCantidad(Integer.parseInt(txtCantidad.getText()));
        factura.setImpuesto(servicio.getPrecio() - (servicio.getPrecio() / 1.13));
        factura.setTotal(factura.getCantidad() * (servicio.getPrecio() + factura.getImpuesto()));
        return factura;
    }
    
    //Método para listar los prodcutos en la tabla
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
    
    //Método para preparar la tabla de los prodcutos y que no se modifique
    public void PrepararTablaProductos(){
        modeloTabla = new DefaultTableModel(){
            //Sobreescribe método para que no se pueda editar
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        
        tblArticulos.setModel(modeloTabla);
        modeloTabla.addColumn("Código");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Peso K");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Cantidad");
        modeloTabla.addColumn("Impuesto");
        modeloTabla.addColumn("Total");
    }
    
    
    //Limpia las casillas 
    public void LimpiarCasillas(){
        txtCodigoProducto.setText("");
        txtCodigoServicio.setText("");
        txtCodigoCliente.setText("");
        txtSubtotal.setText("");
        txtImpuesto.setText("");
        txtTotal.setText("");
        ddlTipoPago.setSelectedIndex(0);
        txtCantidad.setText("");
        txtCodigoVendedor.setText("");
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
        txtCodigoCliente = new javax.swing.JTextPane();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtCodigoVendedor = new javax.swing.JTextPane();
        jLabel3 = new javax.swing.JLabel();
        btnBuscarCliente = new javax.swing.JButton();
        btnBuscarVendedor = new javax.swing.JButton();
        lblProducto = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtCodigoServicio = new javax.swing.JTextPane();
        lblServicio = new javax.swing.JLabel();
        btnBuscarProducto = new javax.swing.JButton();
        btnBuscarServicio = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtCantidad = new javax.swing.JTextPane();
        lblCantidad = new javax.swing.JLabel();
        tbtnProducto = new javax.swing.JToggleButton();
        jLabel18 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane8 = new javax.swing.JScrollPane();
        txtSubtotal = new javax.swing.JTextPane();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtImpuesto = new javax.swing.JTextPane();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        txtTotal = new javax.swing.JTextPane();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtCodigoProducto = new javax.swing.JTextPane();
        btnAgregar = new javax.swing.JButton();
        ddlTipoPago = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblArticulos = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        btnFinalizar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();

        jLabel17.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel17.setText("FACTURA VENTA");

        txtCodigoFactura.setEnabled(false);
        jScrollPane1.setViewportView(txtCodigoFactura);

        jLabel1.setText("Código");

        txtCodigoCliente.setEnabled(false);
        jScrollPane2.setViewportView(txtCodigoCliente);

        jLabel2.setText("Código Cliente");

        txtCodigoVendedor.setEnabled(false);
        jScrollPane3.setViewportView(txtCodigoVendedor);

        jLabel3.setText("Código Vendedor");

        btnBuscarCliente.setText("Buscar");
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });

        btnBuscarVendedor.setText("Buscar");
        btnBuscarVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarVendedorActionPerformed(evt);
            }
        });

        lblProducto.setText("Código Producto");

        txtCodigoServicio.setEnabled(false);
        jScrollPane6.setViewportView(txtCodigoServicio);

        lblServicio.setText("Código Servicio");

        btnBuscarProducto.setText("Buscar");
        btnBuscarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProductoActionPerformed(evt);
            }
        });

        btnBuscarServicio.setText("Buscar");
        btnBuscarServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarServicioActionPerformed(evt);
            }
        });

        jScrollPane4.setViewportView(txtCantidad);

        lblCantidad.setText("Cantidad");

        tbtnProducto.setText("Producto");
        tbtnProducto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tbtnProductoItemStateChanged(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel18.setText("AGREGAR");

        txtSubtotal.setEnabled(false);
        jScrollPane8.setViewportView(txtSubtotal);

        jLabel7.setText("Subtotal");

        txtImpuesto.setEnabled(false);
        jScrollPane9.setViewportView(txtImpuesto);

        jLabel8.setText("Impuestos");

        txtTotal.setEnabled(false);
        jScrollPane10.setViewportView(txtTotal);

        jLabel9.setText("Total");

        txtCodigoProducto.setEnabled(false);
        jScrollPane5.setViewportView(txtCodigoProducto);

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        ddlTipoPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "EFECTIVO", "TARJETA", "SINPE" }));

        jLabel4.setText("Tipo Pago");

        tblArticulos.setModel(new javax.swing.table.DefaultTableModel(
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
        tblArticulos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblArticulosMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(tblArticulos);

        btnFinalizar.setText("Generar");
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Salir");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel17)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnBuscarCliente)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnBuscarVendedor))
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(tbtnProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblProducto)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnBuscarProducto)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnBuscarServicio))
                                    .addComponent(lblServicio))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane4)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblCantidad)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(56, 56, 56))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel8))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane10)
                                    .addComponent(jScrollPane9)
                                    .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                                    .addComponent(btnFinalizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAgregar)
                                .addGap(26, 26, 26)
                                .addComponent(btnLimpiar)
                                .addGap(480, 480, 480)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(ddlTipoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(28, 28, 28))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnBuscarVendedor)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnBuscarCliente, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(7, 7, 7)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscarServicio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblServicio))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblProducto))
                    .addComponent(btnBuscarProducto)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCantidad))
                    .addComponent(tbtnProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(ddlTipoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAgregar)
                        .addComponent(btnLimpiar)))
                .addGap(12, 12, 12)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Click boton Buscar Cliente
    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
        frmBuscarClientes buscarClientes = new frmBuscarClientes(null, true);
        
        //Cuando se cierra recupera el id
        buscarClientes.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosed(WindowEvent e){
                try {
                    int id = buscarClientes.ObtenerId();
                    
                    if (id > -1) {
                        txtCodigoCliente.setText(String.valueOf(id));
                    }
                    else{
                        //LimpiarCasillas();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        
        });
        buscarClientes.setVisible(true);
    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    //Click boton buscar empleado
    private void btnBuscarVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarVendedorActionPerformed
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
    }//GEN-LAST:event_btnBuscarVendedorActionPerformed

    //Activa el boton productos
    private void tbtnProductoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tbtnProductoItemStateChanged
        if (tbtnProducto.isSelected()) {
            lblServicio.setEnabled(true);
            btnBuscarServicio.setEnabled(true);
            
            btnBuscarProducto.setEnabled(false);
            lblProducto.setEnabled(false);
            tbtnProducto.setText("Servicio");
            txtCodigoProducto.setText("");
            
        }
        else{
            lblServicio.setEnabled(false);
            btnBuscarServicio.setEnabled(false);
            
            btnBuscarProducto.setEnabled(true);            
            lblProducto.setEnabled(true);
            tbtnProducto.setText("Producto");
            txtCodigoServicio.setText("");
            
        }
    }//GEN-LAST:event_tbtnProductoItemStateChanged

    //Click boton buscar producto
    private void btnBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProductoActionPerformed
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
    }//GEN-LAST:event_btnBuscarProductoActionPerformed

    //Click boton buscar servicio
    private void btnBuscarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarServicioActionPerformed
        frmBuscarServicios buscarServicios = new frmBuscarServicios(null, true);
    
        //Cuando se cierra recupera el id
        buscarServicios.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosed(WindowEvent e){
                try {
                    int id = buscarServicios.ObtenerId();
                    
                    if (id > -1) {
                        txtCodigoServicio.setText(String.valueOf(id));
                    }
                    else{
                       // LimpiarCasillas();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });
        
        buscarServicios.setVisible(true);
    }//GEN-LAST:event_btnBuscarServicioActionPerformed

    //Click boton agregar
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        String condicion = "";
        Productos producto;
        Servicios servicio;
        
        try {
            FacturasVentas factura;
            LFacturaTemporal logicaFT = new LFacturaTemporal();
            LProductos logicaP = new LProductos();
            LServicios logicaS = new LServicios();
            
            if (!txtCodigoCliente.getText().equals("") &&
            !txtCodigoVendedor.getText().equals("") &&
            !txtCantidad.getText().equals("")) {
                
                FacturasVentas facturaVenta = GenerarEntidadFactura();
                FacturaTemporal facturaTemporal;
                
                if (!facturaVenta.isExiste()) {
                    
                    //Producto
                    if (!tbtnProducto.isSelected()) {
                        //Obtiene la info del producto que agregó
                        condicion = "ID_PRODUCTO LIKE '%" + txtCodigoProducto.getText() + "%'";
                        producto = logicaP.ObtenerProducto(condicion);
                        int cant = Integer.parseInt(txtCantidad.getText());
                        
                        String nombre = producto.getNombre();
                                
                        if (cant < producto.getCantidad()) {
                            facturaTemporal = GenerarEntidadFacturaTemporalProductos(producto);
                        
                            if (logicaFT.InsertarFacturaTemporal(facturaTemporal) > 0) {
                                ListarProductosFactura("");
                                JOptionPane.showMessageDialog(this, logicaFT.getMensaje());
                                btnBuscarCliente.setEnabled(false);
                                btnBuscarVendedor.setEnabled(false);
                                btnFinalizar.setEnabled(true);
                                facturaTemporal.setEsProducto(true);
                                
                                //Genera la entidad
                                factura = GenerarEntidadFactura();

                                //Guarda en un arreglo
                                listaFacturas.add(factura);
                            }
                            else{
                                JOptionPane.showMessageDialog(this, logicaFT.getMensaje());
                            }//If Insertar
                        }
                        else{
                            JOptionPane.showMessageDialog(this, "La cantidad de productos en inventario no es suficiente");
                        }//IF Cantidad 
                       
                    }
                    //Servicio
                    else{
                        //Obtiene la info del producto que agregó
                        condicion = "ID_SERVICIO LIKE '%" + txtCodigoServicio.getText() + "%'";
                        servicio = logicaS.ObtenerServicio(condicion);
                        
                        facturaTemporal = GenerarEntidadFacturaTemporalServicios(servicio);
                        
                         if (logicaFT.InsertarFacturaTemporal(facturaTemporal) > 0) {
                            ListarProductosFactura("");
                            JOptionPane.showMessageDialog(this, logicaFT.getMensaje());
                            btnBuscarCliente.setEnabled(false);
                            btnBuscarVendedor.setEnabled(false);
                            btnFinalizar.setEnabled(true);

                            //Genera la entidad
                            factura = GenerarEntidadFactura();

                            //Guarda en un arreglo
                            listaFacturas.add(factura);
                        }
                        else{
                            JOptionPane.showMessageDialog(this, logicaFT.getMensaje());
                        }//If Insertar
                        
                    }//IF producto o servicio
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "Debe completar todos los campos primero");
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    //Click elemento de la tabla
    private void tblArticulosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblArticulosMouseClicked
        //Llamar a obtener un producto de la logica        
        try {
            LFacturaTemporal logica = new LFacturaTemporal();
            FacturaTemporal factura;
            String condicion;

            //Si da dos clicks
            if (evt.getClickCount() == 2) {
                //Obtiene toda la fila
                int fila = tblArticulos.rowAtPoint(evt.getPoint());
                txtCodigoProducto.setText(tblArticulos.getValueAt(fila, 0).toString());//Obtiene de la tabla
                
                condicion = String.format("ID_PRODUCTO= %s", txtCodigoProducto.getText());
                factura = logica.ObtenerProducto(condicion);//Obtiene del cliente
                
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_tblArticulosMouseClicked

    //Click boton cancelar
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        
        try {
            LFacturaTemporal logicaFTemporal = new LFacturaTemporal();
            
            if (logicaFTemporal.LimpiarFacturaTemporal() > 0) {
                this.dispose();
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    //Click boton finalizar
    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarActionPerformed
        try {
            FacturasVentas facturaVenta;
            DetallesFacturas detalleFactura;
            LFacturasVenta logicaVenta;
            LDetallesFactura logicaDetalles;
            LFacturaTemporal logicaFTemporal;
            
            logicaVenta = new LFacturasVenta();
            logicaDetalles = new LDetallesFactura();
            logicaFTemporal = new LFacturaTemporal();
            
            if (!txtCodigoCliente.getText().equals("") &&
            !txtCodigoVendedor.getText().equals("") &&
            !txtCantidad.getText().equals("")) {
                //Genera la entidad del detalle
                detalleFactura = GenerarEntidadDetallesFact();
                
                //Inserta los detalles de la factura
                if (logicaDetalles.InsertarDetalleFacturas(detalleFactura) > 0) {
                
                    //Recupera el id del detalle Venta
                    idDetalle = logicaDetalles.ObtenerDetalleFactura("");
                    //Genera la entidad de la factura
                    //facturaVenta = GenerarEntidadFactura();
                        
                    //Guarda el id del detalle en cada uno
                    for (FacturasVentas listaFactura : listaFacturas) {
                        listaFactura.setIdDetalle(idDetalle);
                    }

                    if (logicaVenta.InsertarFacturaVenta(listaFacturas) > 0) {
                            
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
                        JOptionPane.showMessageDialog(this, logicaVenta.getMensaje());
                    }//IF insertar factura
                    
                }
                else{
                    JOptionPane.showMessageDialog(this, logicaDetalles.getMensaje());
                }//IF insertar detalles
                
            }
            else{
                JOptionPane.showMessageDialog(this, "Debe completar todos los campos primero");
            }//IF texto completo
            
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnFinalizarActionPerformed

    //Click boton limpiar
    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        try {
            LFacturaTemporal logicaFTemporal;
            logicaFTemporal = new LFacturaTemporal();

            if (logicaFTemporal.LimpiarFacturaTemporal() > 0) {
                ListarProductosFactura("");
                LimpiarCasillas();
                btnBuscarCliente.setEnabled(true);
                btnBuscarVendedor.setEnabled(true);

            }
            else{
                JOptionPane.showMessageDialog(this, logicaFTemporal.getMensaje());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());            
        }
        
    }//GEN-LAST:event_btnLimpiarActionPerformed

   
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnBuscarProducto;
    private javax.swing.JButton btnBuscarServicio;
    private javax.swing.JButton btnBuscarVendedor;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<String> ddlTipoPago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblProducto;
    private javax.swing.JLabel lblServicio;
    private javax.swing.JTable tblArticulos;
    private javax.swing.JToggleButton tbtnProducto;
    private javax.swing.JTextPane txtCantidad;
    private javax.swing.JTextPane txtCodigoCliente;
    private javax.swing.JTextPane txtCodigoFactura;
    private javax.swing.JTextPane txtCodigoProducto;
    private javax.swing.JTextPane txtCodigoServicio;
    private javax.swing.JTextPane txtCodigoVendedor;
    private javax.swing.JTextPane txtImpuesto;
    private javax.swing.JTextPane txtSubtotal;
    private javax.swing.JTextPane txtTotal;
    // End of variables declaration//GEN-END:variables
}
