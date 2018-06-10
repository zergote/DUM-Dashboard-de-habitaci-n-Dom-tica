package ui;

import bd.Accidente;
import bd.Conector;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import notificaciones.Notificaciones;

public class HistorialAccidentes extends javax.swing.JInternalFrame implements Runnable {

    Notificaciones notificaciones;
    Conector connect = new Conector();
    Thread hilo;
    Boolean ejecucion;
    Date date;
    DateFormat horaYFecha;
    Boolean test;

    public HistorialAccidentes(Notificaciones notificaciones) throws SQLException {
        this.notificaciones = notificaciones;
        ejecucion = true;
        date = new Date();
        horaYFecha = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        initComponents();
        connect.connect();
        jTableHistorialAccidentes.setModel(connect.buildTableModel("accidentes"));
        hilo = new Thread(this);
        hilo.start();
    }

    public void reconstruirTabla() throws SQLException {
        jTableHistorialAccidentes.setModel(connect.buildTableModel("accidentes"));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelHistorialAccidentes = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableHistorialAccidentes = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Historial de accidentes");

        jTableHistorialAccidentes.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTableHistorialAccidentes.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableHistorialAccidentes.setRowHeight(14);
        jScrollPane1.setViewportView(jTableHistorialAccidentes);

        javax.swing.GroupLayout jPanelHistorialAccidentesLayout = new javax.swing.GroupLayout(jPanelHistorialAccidentes);
        jPanelHistorialAccidentes.setLayout(jPanelHistorialAccidentesLayout);
        jPanelHistorialAccidentesLayout.setHorizontalGroup(
            jPanelHistorialAccidentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
        );
        jPanelHistorialAccidentesLayout.setVerticalGroup(
            jPanelHistorialAccidentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelHistorialAccidentes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelHistorialAccidentes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanelHistorialAccidentes;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableHistorialAccidentes;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        while (ejecucion) {
            try {
                if (notificaciones.senalFuego == true && notificaciones.BDFuego == false) {
                    Accidente accidente = new Accidente(horaYFecha.format(date), "Fuego en el área");
                    accidente.save();
                    jTableHistorialAccidentes.setModel(connect.buildTableModel("accidentes"));
                    notificaciones.BDFuego = true;
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
            }
            try {
                if (notificaciones.senalHumo && notificaciones.BDHumo == false) {
                    Accidente accidente = new Accidente(horaYFecha.format(date), "Humo/Gas en el área");
                    accidente.save();
                    jTableHistorialAccidentes.setModel(connect.buildTableModel("accidentes"));
                    notificaciones.BDHumo = true;
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
            }
        }
    }
}
