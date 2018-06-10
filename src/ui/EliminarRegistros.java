package ui;

import bd.Conector;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EliminarRegistros extends javax.swing.JInternalFrame {

    Conector connect;
    HistorialAcceso accesos;
    HistorialAccidentes accidentes;
    HistorialIntrusos intrusos;

    public EliminarRegistros(HistorialAcceso accesos, HistorialAccidentes accidentes, HistorialIntrusos intrusos) {
        this.accesos = accesos;
        this.accidentes = accidentes;
        this.intrusos = intrusos;
        initComponents();
        connect = new Conector();
        connect.connect();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBoxAccidentes = new javax.swing.JCheckBox();
        jCheckBoxIntrusos = new javax.swing.JCheckBox();
        jCheckBoxAccesos = new javax.swing.JCheckBox();
        jButtonEliminar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Eliminar Registros");

        jCheckBoxAccidentes.setText("Accidentes");

        jCheckBoxIntrusos.setText("Intrusos");

        jCheckBoxAccesos.setText("Accesos");

        jButtonEliminar.setForeground(new java.awt.Color(0, 0, 0));
        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheckBoxAccidentes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 4, Short.MAX_VALUE)
                .addComponent(jCheckBoxIntrusos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 5, Short.MAX_VALUE)
                .addComponent(jCheckBoxAccesos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addComponent(jButtonEliminar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxAccidentes)
                    .addComponent(jCheckBoxIntrusos)
                    .addComponent(jCheckBoxAccesos)
                    .addComponent(jButtonEliminar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed

        if (jCheckBoxAccesos.isSelected()) {
            try {
                connect.eliminarDatos("accesos");
                accesos.reconstruirTabla();
            } catch (SQLException ex) {
                Logger.getLogger(EliminarRegistros.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (jCheckBoxAccidentes.isSelected()) {
            try {
                connect.eliminarDatos("accidentes");
                accidentes.reconstruirTabla();
            } catch (SQLException ex) {
                Logger.getLogger(EliminarRegistros.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (jCheckBoxIntrusos.isSelected()) {
            try {
                connect.eliminarDatos("intrusos");
                intrusos.reconstruirTabla();
            } catch (SQLException ex) {
                Logger.getLogger(EliminarRegistros.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //connect.close();
    }//GEN-LAST:event_jButtonEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JCheckBox jCheckBoxAccesos;
    private javax.swing.JCheckBox jCheckBoxAccidentes;
    private javax.swing.JCheckBox jCheckBoxIntrusos;
    // End of variables declaration//GEN-END:variables
}
