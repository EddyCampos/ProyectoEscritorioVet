/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package CapaPresentacion;

import CapaEntidades.Productos;
import CapaLogica.LProductos;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class frmProductos extends javax.swing.JInternalFrame {
    
    //Variable global
    DefaultTableModel modeloTabla;

    /**
     * Creates new form Productos
     */
    public frmProductos() {
        initComponents();
        
        try {
            ListarProductos("");
            ListarAlimentos("");
            pnlAlimentos.setVisible(false);
            pack();
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
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
        
        tblProductos.setModel(modeloTabla);
        modeloTabla.addColumn("Código");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Marca");
        modeloTabla.addColumn("Tipo");
        modeloTabla.addColumn("Cantidad");
        modeloTabla.addColumn("Fecha Ingreso");
        modeloTabla.addColumn("Nombre Proveedor");
        modeloTabla.addColumn("Teléfono Proveedor");
        modeloTabla.addColumn("Precio Compra");
        modeloTabla.addColumn("Precio Venta");
        modeloTabla.addColumn("Disponible");
    }
    
    //Método para preparar la tabla de los alimentos y que no se modifique
    public void PrepararTablaAlimentos(){
        modeloTabla = new DefaultTableModel(){
            //Sobreescribe método para que no se pueda editar
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        
        tblAlimentos.setModel(modeloTabla);
        modeloTabla.addColumn("Código");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Marca");
        modeloTabla.addColumn("Tipo");
        modeloTabla.addColumn("Cantidad");
        modeloTabla.addColumn("Fecha Ingreso");
        modeloTabla.addColumn("Nombre Proveedor");
        modeloTabla.addColumn("Teléfono Proveedor");
        modeloTabla.addColumn("Precio Compra");
        modeloTabla.addColumn("Precio Venta");
        modeloTabla.addColumn("Disponible");
        modeloTabla.addColumn("Fecha Caducidad");
        modeloTabla.addColumn("Peso Kilos");
    }
    
    //Método para listar los clientes en la tabla
    public void ListarProductos(String condicion) throws Exception{
        try {
            LProductos logica = new LProductos();
            ResultSet datos;
            
            PrepararTablaProductos();
            
            Object[] fila = new Object[11];
            datos = logica.ListarProductos(condicion, "");
            
            while (datos.next()) {
                fila[0] = datos.getInt("ID_PRODUCTO");
                fila[1] = datos.getString("NOMBRE");
                fila[2] = datos.getString("MARCA");
                fila[3] = datos.getString("TIPO");
                fila[4] = datos.getString("CANTIDAD");
                fila[5] = datos.getString("FECHA_INGRESO");
                fila[6] = datos.getString("NOMBRE_PROVEEDOR");
                fila[7] = datos.getString("TELEFONO_PROVEEDOR");
                fila[8] = datos.getString("PRECIO_COMPRA");
                fila[9] = datos.getString("PRECIO_VENTA");
                fila[10] = datos.getString("DISPONIBLE");
                modeloTabla.addRow(fila);
            }
        } 
        catch (Exception e) {
            throw  e;
        }
    }
    
     //Método para listar los clientes en la tabla
    public void ListarAlimentos(String condicion) throws Exception{
        try {
            LProductos logica = new LProductos();
            ResultSet datos;
            
            PrepararTablaAlimentos();
            
            Object[] fila = new Object[13];
            datos = logica.ListarAlimentos(condicion, "");
            
            while (datos.next()) {
                fila[0] = datos.getInt("ID_PRODUCTO");
                fila[1] = datos.getString("NOMBRE");
                fila[2] = datos.getString("MARCA");
                fila[3] = datos.getString("TIPO");
                fila[4] = datos.getString("CANTIDAD");
                fila[5] = datos.getString("FECHA_INGRESO");
                fila[6] = datos.getString("NOMBRE_PROVEEDOR");
                fila[7] = datos.getString("TELEFONO_PROVEEDOR");
                fila[8] = datos.getString("PRECIO_COMPRA");
                fila[9] = datos.getString("PRECIO_VENTA");
                fila[10] = datos.getString("DISPONIBLE");
                fila[11] = datos.getString("FECHA_CADUCIDAD");
                fila[12] = datos.getString("PESO");
                
                modeloTabla.addRow(fila);
            }
        } 
        catch (Exception e) {
            throw  e;
        }
    }
    
    //Método para generar la entidad del producto
    public Productos GenerarEntidadProducto(){
        Productos producto = new Productos();
        
        try {
            if (txtCodigo.getText().equals("")) {
                producto.setIdProducto(-1);
            }
            else{
                producto.setIdProducto(Integer.parseInt(txtCodigo.getText()));
                producto.setExiste(true);
            }
            
            //Convierte la fecha de date a localDate
            java.sql.Date fechaIngreso = java.sql.Date.valueOf(producto.getFechaIngreso());
            java.sql.Date fechaCaducidad = java.sql.Date.valueOf(producto.getFechaIngreso());
            
            producto.setNombre(txtNombre.getText());
            producto.setMarca(txtMarca.getText());
            producto.setTipo(ddlTipo.getSelectedItem().toString());
            producto.setCantidad(Integer.parseInt(txtCantidad.getText()));
            producto.setFechaIngreso(fechaIngreso.toLocalDate());
            producto.setNombreProveedor(txtNombrePro.getText());
            producto.setTelefonoProveedor(txtTelefonoPro.getText());
            producto.setPrecioCompra(Integer.parseInt(txtPrecioCompra.getText()));
            producto.setPrecioVenta(Integer.parseInt(txtPrecioVenta.getText()));
            producto.setDisponible(ddlDisponible.getSelectedItem().toString());
            producto.setFechaCaducidad(fechaCaducidad.toLocalDate());
            producto.setPeso(Integer.parseInt(ddlPeso.getSelectedItem().toString()));
        } 
        catch (Exception e) {
            throw e;
        }
        
        return producto;
    }
    
     //Método para borrar las valores de las casillas
    public void LimpiarCasillas(){
        txtCodigo.setText("");
        txtNombre.setText("");
        txtMarca.setText("");
        ddlTipo.setSelectedItem("JUGUETES");
        txtCantidad.setText("");
        dtpFIngreso.setDate(new Date());
        txtNombrePro.setText("");
        txtTelefonoPro.setText("");
        txtPrecioCompra.setText("");
        txtPrecioVenta.setText("");
        ddlDisponible.setSelectedItem("DISPONIBLE");
        ddlPeso.setSelectedItem("500");
        dtpFechaCaducidad.setDate(new Date());
    }
    

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtCodigo = new javax.swing.JTextPane();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtMarca = new javax.swing.JTextPane();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        ddlTipo = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtCantidad = new javax.swing.JTextPane();
        jLabel6 = new javax.swing.JLabel();
        dtpFIngreso = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtNombrePro = new javax.swing.JTextPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtTelefonoPro = new javax.swing.JTextPane();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtPrecioVenta = new javax.swing.JTextPane();
        jLabel11 = new javax.swing.JLabel();
        ddlDisponible = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        btnLimpiar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        txtNombre = new javax.swing.JTextPane();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtPrecioCompra = new javax.swing.JTextPane();
        jLabel17 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        pnlAlimentos = new javax.swing.JPanel();
        dtpFechaCaducidad = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        ddlPeso = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblAlimentos = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();

        txtCodigo.setEnabled(false);
        jScrollPane1.setViewportView(txtCodigo);

        jLabel2.setText("Código");

        jLabel3.setText("Nombre");

        jScrollPane3.setViewportView(txtMarca);

        jLabel4.setText("Marca");

        jLabel5.setText("Tipo");

        ddlTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "JUGUETES", "HIGIENE", "MEDICO", "ALIMENTOS" }));
        ddlTipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ddlTipoItemStateChanged(evt);
            }
        });

        jScrollPane2.setViewportView(txtCantidad);

        jLabel6.setText("Cantidad");

        jLabel7.setText("Fecha Ingreso");

        jScrollPane4.setViewportView(txtNombrePro);

        jScrollPane5.setViewportView(txtTelefonoPro);

        jLabel8.setText("Nombre Proveedor");

        jLabel9.setText("Teléfono Proveedor");

        jLabel10.setText("Precio Compra");

        jScrollPane7.setViewportView(txtPrecioVenta);

        jLabel11.setText("Precio Venta");

        ddlDisponible.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DISPONIBLE", "NO DISPONIBLE" }));

        jLabel12.setText("Disponible");

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
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
        tblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblProductos);

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

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jScrollPane8.setViewportView(txtNombre);

        jScrollPane9.setViewportView(txtPrecioCompra);

        jLabel17.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel17.setText("PRODUCTOS");

        jLabel13.setText("Fecha Caducidad");

        ddlPeso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "3", "5", "10", "20" }));

        jLabel15.setText("Peso Kilos");

        javax.swing.GroupLayout pnlAlimentosLayout = new javax.swing.GroupLayout(pnlAlimentos);
        pnlAlimentos.setLayout(pnlAlimentosLayout);
        pnlAlimentosLayout.setHorizontalGroup(
            pnlAlimentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAlimentosLayout.createSequentialGroup()
                .addGroup(pnlAlimentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dtpFechaCaducidad, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(27, 27, 27)
                .addGroup(pnlAlimentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(ddlPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 21, Short.MAX_VALUE))
        );
        pnlAlimentosLayout.setVerticalGroup(
            pnlAlimentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAlimentosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlAlimentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dtpFechaCaducidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ddlPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAlimentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel15)))
        );

        tblAlimentos.setModel(new javax.swing.table.DefaultTableModel(
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
        tblAlimentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAlimentosMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tblAlimentos);

        jLabel18.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel18.setText("PRODUCTOS");

        jLabel19.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel19.setText("CONCENTRADOS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnLimpiar)
                                .addGap(35, 35, 35)
                                .addComponent(btnGuardar)
                                .addGap(38, 38, 38)
                                .addComponent(btnSalir))
                            .addComponent(jSeparator1)
                            .addComponent(jScrollPane6)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addGap(742, 742, 742)
                                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel2))
                                                .addGap(26, 26, 26)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel3)
                                                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(28, 28, 28)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                                                    .addComponent(jLabel4)
                                                    .addComponent(jLabel9)
                                                    .addComponent(jScrollPane5)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(dtpFIngreso, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                                                    .addComponent(jLabel7))
                                                .addGap(28, 28, 28)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(26, 26, 26)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel5)
                                            .addComponent(ddlTipo, 0, 196, Short.MAX_VALUE)
                                            .addComponent(jLabel10)
                                            .addComponent(jScrollPane9)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(ddlDisponible, 0, 196, Short.MAX_VALUE)
                                                .addComponent(jLabel12))
                                            .addComponent(jLabel17))
                                        .addGap(28, 28, 28)
                                        .addComponent(pnlAlimentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                                        .addComponent(jLabel11))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                                        .addComponent(jLabel6))))
                            .addComponent(jScrollPane10))
                        .addGap(19, 19, 19))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ddlTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6))
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dtpFIngreso, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(ddlDisponible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(pnlAlimentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel19)
                .addGap(1, 1, 1)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimpiar)
                    .addComponent(btnGuardar)
                    .addComponent(btnSalir))
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Click boton guardar
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            LProductos logica = new LProductos();
            
            if (!txtNombre.getText().equals("") &&
            !txtMarca.getText().equals("") &&
            !txtCantidad.getText().equals("") &&
            !txtNombrePro.getText().equals("") &&
            !txtTelefonoPro.getText().equals("") &&
            !txtPrecioVenta.getText().equals("") &&
            !txtPrecioCompra.getText().equals("")) {
                
                Productos producto = GenerarEntidadProducto();
                
                if (!producto.isExiste()) {
                    if (logica.InsertarProducto(producto) > 0) {
                        JOptionPane.showMessageDialog(this, logica.getMensaje());
                        LimpiarCasillas();
                        ListarProductos("");
                        ListarAlimentos("");
                    }
                    else{
                        JOptionPane.showMessageDialog(this, logica.getMensaje());
                    }
                }
                else{
                     if (logica.ActualizarProducto(producto) > 0) {
                        JOptionPane.showMessageDialog(this, logica.getMensaje());
                        LimpiarCasillas();
                        ListarProductos("");
                        ListarAlimentos("");
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

    //Click boton Salir
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    //Click boton limpiaar
    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        LimpiarCasillas();
        ddlTipo.setEnabled(true);
    }//GEN-LAST:event_btnLimpiarActionPerformed

    //Click a un elemento de la tabla
    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseClicked
        //Llamar a obtener un cliente de la logica        
        try {
            LProductos logica = new LProductos();
            Productos producto;
            String condicion;

            //Si da dos clicks
            if (evt.getClickCount() == 2) {
                
                ddlTipo.setEnabled(false);
                
                //Obtiene toda la fila
                int fila = tblProductos.rowAtPoint(evt.getPoint());
                txtCodigo.setText(tblProductos.getValueAt(fila, 0).toString());//Obtiene de la tabla
                
                condicion = String.format("ID_PRODUCTO= %s", txtCodigo.getText());
                producto = logica.ObtenerProducto(condicion);//Obtiene del cliente
                
                 //Convierte la fecha de date a localDate
                java.sql.Date fechaBaseDatos = java.sql.Date.valueOf(producto.getFechaIngreso());
                
                txtCodigo.setText(String.valueOf(producto.getIdProducto()));
                txtNombre.setText(producto.getNombre());
                txtMarca.setText(producto.getMarca());
                ddlTipo.setSelectedItem(producto.getTipo());
                txtCantidad.setText(String.valueOf(producto.getCantidad()));
                dtpFIngreso.setDate(fechaBaseDatos);
                txtNombrePro.setText(producto.getNombreProveedor());
                txtTelefonoPro.setText(producto.getTelefonoProveedor());
                txtPrecioCompra.setText(String.valueOf(producto.getPrecioCompra()));
                txtPrecioVenta.setText(String.valueOf(producto.getPrecioVenta()));
                ddlDisponible.setSelectedItem(producto.getDisponible());
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_tblProductosMouseClicked

    //Cuando selecciona alimentos muestra el panel
    private void ddlTipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ddlTipoItemStateChanged
        if (ddlTipo.getSelectedItem().equals("ALIMENTOS")) {
            pnlAlimentos.setVisible(true);
pnlAlimentos.setPreferredSize(null);            
        }
        else{
            pnlAlimentos.setVisible(false);
            pnlAlimentos.setPreferredSize(new java.awt.Dimension(0, 0));
        }
    }//GEN-LAST:event_ddlTipoItemStateChanged

    //Click a un elemento de la tabla alimentos
    private void tblAlimentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAlimentosMouseClicked
        //Llamar a obtener un cliente de la logica        
        try {
            LProductos logica = new LProductos();
            Productos producto;
            String condicion;

            //Si da dos clicks
            if (evt.getClickCount() == 2) {
                ddlTipo.setEnabled(false);
                
                //Obtiene toda la fila
                int fila = tblAlimentos.rowAtPoint(evt.getPoint());
                txtCodigo.setText(tblAlimentos.getValueAt(fila, 0).toString());//Obtiene de la tabla
                
                condicion = String.format("ID_PRODUCTO= %s", txtCodigo.getText());
                producto = logica.ObtenerProducto(condicion);//Obtiene del cliente
                
                 //Convierte la fecha de date a localDate
                java.sql.Date fechaIngreso = java.sql.Date.valueOf(producto.getFechaIngreso());
                java.sql.Date fechaCaducidad = java.sql.Date.valueOf(producto.getFechaIngreso());
                
                txtCodigo.setText(String.valueOf(producto.getIdProducto()));
                txtNombre.setText(producto.getNombre());
                txtMarca.setText(producto.getMarca());
                ddlTipo.setSelectedItem(producto.getTipo());
                txtCantidad.setText(String.valueOf(producto.getCantidad()));
                dtpFIngreso.setDate(fechaIngreso);
                txtNombrePro.setText(producto.getNombreProveedor());
                txtTelefonoPro.setText(producto.getTelefonoProveedor());
                txtPrecioCompra.setText(String.valueOf(producto.getPrecioCompra()));
                txtPrecioVenta.setText(String.valueOf(producto.getPrecioVenta()));
                ddlDisponible.setSelectedItem(producto.getDisponible());
                dtpFechaCaducidad.setDate(fechaCaducidad);
                ddlPeso.setSelectedItem(producto.getPeso());
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_tblAlimentosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> ddlDisponible;
    private javax.swing.JComboBox<String> ddlPeso;
    private javax.swing.JComboBox<String> ddlTipo;
    private com.toedter.calendar.JDateChooser dtpFIngreso;
    private com.toedter.calendar.JDateChooser dtpFechaCaducidad;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
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
    private javax.swing.JPanel pnlAlimentos;
    private javax.swing.JTable tblAlimentos;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextPane txtCantidad;
    private javax.swing.JTextPane txtCodigo;
    private javax.swing.JTextPane txtMarca;
    private javax.swing.JTextPane txtNombre;
    private javax.swing.JTextPane txtNombrePro;
    private javax.swing.JTextPane txtPrecioCompra;
    private javax.swing.JTextPane txtPrecioVenta;
    private javax.swing.JTextPane txtTelefonoPro;
    // End of variables declaration//GEN-END:variables
}
