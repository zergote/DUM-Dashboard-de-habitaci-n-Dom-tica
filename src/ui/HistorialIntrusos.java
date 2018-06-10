package ui;

import bd.Conector;
import bd.Intruso;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import notificaciones.Notificaciones;

public class HistorialIntrusos extends javax.swing.JInternalFrame implements Runnable {

    Notificaciones notificaciones;
    Conector connect = new Conector();
    Thread hilo;
    Boolean ejecucion;
    Date date;
    DateFormat horaYFecha;

    public HistorialIntrusos(Notificaciones notificaciones) throws SQLException {
        this.notificaciones = notificaciones;
        ejecucion = true;
        date = new Date();
        horaYFecha = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        initComponents();
        connect.connect();
        jTableHistorialIntrusos.setModel(connect.buildTableModel("intrusos"));
        hilo = new Thread(this);
        hilo.start();
    }

    public void reconstruirTabla() throws SQLException {
        jTableHistorialIntrusos.setModel(connect.buildTableModel("intrusos"));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelHistorialIntrusos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableHistorialIntrusos = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Historial de intrusos");

        jTableHistorialIntrusos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTableHistorialIntrusos.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableHistorialIntrusos.setRowHeight(14);
        jScrollPane1.setViewportView(jTableHistorialIntrusos);

        javax.swing.GroupLayout jPanelHistorialIntrusosLayout = new javax.swing.GroupLayout(jPanelHistorialIntrusos);
        jPanelHistorialIntrusos.setLayout(jPanelHistorialIntrusosLayout);
        jPanelHistorialIntrusosLayout.setHorizontalGroup(
            jPanelHistorialIntrusosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
        );
        jPanelHistorialIntrusosLayout.setVerticalGroup(
            jPanelHistorialIntrusosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelHistorialIntrusos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelHistorialIntrusos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanelHistorialIntrusos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableHistorialIntrusos;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        while (ejecucion) {
            try {
                //Arreglar condicion de intruso
                if (false && notificaciones.BDIntruso == false) {
                    Intruso intruso = new Intruso(horaYFecha.format(date), "Un intruso ha accedido");
                    intruso.save();
                    jTableHistorialIntrusos.setModel(connect.buildTableModel("intrusos"));
                    notificaciones.BDFuego = true;
                }
                Thread.sleep(1000);
            } catch (Exception e) {

            }

        }
    }
}
