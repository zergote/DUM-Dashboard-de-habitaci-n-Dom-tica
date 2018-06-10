/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import bd.Acceso;
import bd.Conector;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import notificaciones.Notificaciones;

/**
 *
 * @author xyges
 */
public class HistorialAcceso extends javax.swing.JInternalFrame implements Runnable {

    Notificaciones notificaciones;
    Conector connect = new Conector();
    Thread hilo;
    Boolean ejecucion;
    Date date;
    DateFormat horaYFecha;

    public HistorialAcceso(Notificaciones notificaciones) throws SQLException {
        this.notificaciones = notificaciones;
        ejecucion = true;
        date = new Date();
        horaYFecha = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        initComponents();
        connect.connect();
        jTableHistorialAccesos.setModel(connect.buildTableModel("accesos"));
        hilo = new Thread(this);
        hilo.start();
    }
    
    public void reconstruirTabla() throws SQLException {
        jTableHistorialAccesos.setModel(connect.buildTableModel("accesos"));
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelHistorialAccesos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableHistorialAccesos = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Historial de accesos");

        jTableHistorialAccesos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTableHistorialAccesos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Fecha", "Descripcion"
            }
        ));
        jTableHistorialAccesos.setRowHeight(14);
        jScrollPane1.setViewportView(jTableHistorialAccesos);

        javax.swing.GroupLayout jPanelHistorialAccesosLayout = new javax.swing.GroupLayout(jPanelHistorialAccesos);
        jPanelHistorialAccesos.setLayout(jPanelHistorialAccesosLayout);
        jPanelHistorialAccesosLayout.setHorizontalGroup(
            jPanelHistorialAccesosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
        );
        jPanelHistorialAccesosLayout.setVerticalGroup(
            jPanelHistorialAccesosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelHistorialAccesos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelHistorialAccesos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanelHistorialAccesos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableHistorialAccesos;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        while (ejecucion) {
            try {
                //Arreglar condicion de acceso
                if (false) {
                    Acceso acceso = new Acceso(horaYFecha.format(date), "Un usuario ha accedido");
                    acceso.save();
                    jTableHistorialAccesos.setModel(connect.buildTableModel("accesos"));
                }
                Thread.sleep(1000);
            } catch (Exception e) {
                
            }

        }
    }
}
