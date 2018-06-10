package ui;

import stream.StreamData;
import stream.lockThread;
import bd.Conector;
import com.panamahitek.PanamaHitek_Arduino;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import notificaciones.Notificaciones;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

public class Escritorio extends javax.swing.JFrame {

    PanamaHitek_Arduino teensy;
    Conector con = new Conector();
    StreamData datos;
    lockThread lock;
    Puertos puertos;
    Notificaciones notificaciones;
    StreamTemperatura temperatura;
    StreamHumedad humedad;
    Monitor monitor;
    Control control;
    MonitorNotificacion notificacion;
    HistorialAccidentes hAccidentes;
    HistorialAcceso accesos;
    HistorialIntrusos intrusos;
    EliminarRegistros eliminar;
    MonitorServicios servicios;

    public Escritorio() throws SQLException {
        initComponents();
        this.teensy = new PanamaHitek_Arduino();
        lock = new lockThread(true, false);
        puertos = new Puertos(teensy, lock);
        datos = new StreamData(teensy, lock);
        notificaciones = new Notificaciones(datos, lock);
        jDEscritorio.setBorder(new BackgroundImage());
        con.connect();
        con.close();
        temperatura = new StreamTemperatura(teensy, datos);
        humedad = new StreamHumedad(teensy, datos);
        monitor = new Monitor(teensy, datos);
        control = new Control(teensy, datos);
        notificacion = new MonitorNotificacion(notificaciones);
        hAccidentes = new HistorialAccidentes(notificaciones);
        accesos = new HistorialAcceso(notificaciones);
        intrusos = new HistorialIntrusos(notificaciones);
        eliminar = new EliminarRegistros(accesos, hAccidentes, intrusos);
        servicios = new MonitorServicios(notificaciones);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDEscritorio = new javax.swing.JDesktopPane();
        jMenuBar = new javax.swing.JMenuBar();
        jMenuControl = new javax.swing.JMenu();
        jMenuItemPanel = new javax.swing.JMenuItem();
        jMenuItemPuertos = new javax.swing.JMenuItem();
        jMenuItemSalir = new javax.swing.JMenuItem();
        jMenuMonitores = new javax.swing.JMenu();
        jMenuItemMonitorEventos = new javax.swing.JMenuItem();
        jMenuItemNotificacion = new javax.swing.JMenuItem();
        jMenuItemServicios = new javax.swing.JMenuItem();
        jMenuRegistros = new javax.swing.JMenu();
        jMenuItemAccidentes = new javax.swing.JMenuItem();
        jMenuItemAccesos = new javax.swing.JMenuItem();
        jMenuItemsIntrusos = new javax.swing.JMenuItem();
        jMenuItemEliminarRegistros = new javax.swing.JMenuItem();
        jMenuStream = new javax.swing.JMenu();
        jMenuItemTemperatura = new javax.swing.JMenuItem();
        jMenuItemHumedad = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jDEscritorio.setDragMode(javax.swing.JDesktopPane.OUTLINE_DRAG_MODE);

        javax.swing.GroupLayout jDEscritorioLayout = new javax.swing.GroupLayout(jDEscritorio);
        jDEscritorio.setLayout(jDEscritorioLayout);
        jDEscritorioLayout.setHorizontalGroup(
            jDEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 869, Short.MAX_VALUE)
        );
        jDEscritorioLayout.setVerticalGroup(
            jDEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 453, Short.MAX_VALUE)
        );

        jMenuControl.setText("Control");

        jMenuItemPanel.setText("Panel de control");
        jMenuItemPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPanelActionPerformed(evt);
            }
        });
        jMenuControl.add(jMenuItemPanel);

        jMenuItemPuertos.setText("Puertos");
        jMenuItemPuertos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPuertosActionPerformed(evt);
            }
        });
        jMenuControl.add(jMenuItemPuertos);

        jMenuItemSalir.setText("Salir");
        jMenuItemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSalirActionPerformed(evt);
            }
        });
        jMenuControl.add(jMenuItemSalir);

        jMenuBar.add(jMenuControl);

        jMenuMonitores.setText("Monitores");

        jMenuItemMonitorEventos.setText("Monitor de eventos");
        jMenuItemMonitorEventos.setToolTipText("");
        jMenuItemMonitorEventos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemMonitorEventosActionPerformed(evt);
            }
        });
        jMenuMonitores.add(jMenuItemMonitorEventos);

        jMenuItemNotificacion.setText("Monitor de notificación");
        jMenuItemNotificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNotificacionActionPerformed(evt);
            }
        });
        jMenuMonitores.add(jMenuItemNotificacion);

        jMenuItemServicios.setText("Correo de servicios");
        jMenuItemServicios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemServiciosActionPerformed(evt);
            }
        });
        jMenuMonitores.add(jMenuItemServicios);

        jMenuBar.add(jMenuMonitores);

        jMenuRegistros.setText("Registros");

        jMenuItemAccidentes.setText("Accidentes");
        jMenuItemAccidentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAccidentesActionPerformed(evt);
            }
        });
        jMenuRegistros.add(jMenuItemAccidentes);

        jMenuItemAccesos.setText("Accesos a la sala");
        jMenuItemAccesos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAccesosActionPerformed(evt);
            }
        });
        jMenuRegistros.add(jMenuItemAccesos);

        jMenuItemsIntrusos.setText("Intrusos");
        jMenuItemsIntrusos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemsIntrusosActionPerformed(evt);
            }
        });
        jMenuRegistros.add(jMenuItemsIntrusos);

        jMenuItemEliminarRegistros.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItemEliminarRegistros.setForeground(new java.awt.Color(255, 51, 51));
        jMenuItemEliminarRegistros.setText("Eliminar Registros");
        jMenuItemEliminarRegistros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEliminarRegistrosActionPerformed(evt);
            }
        });
        jMenuRegistros.add(jMenuItemEliminarRegistros);

        jMenuBar.add(jMenuRegistros);

        jMenuStream.setText("Stream");

        jMenuItemTemperatura.setText("Temperatura");
        jMenuItemTemperatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemTemperaturaActionPerformed(evt);
            }
        });
        jMenuStream.add(jMenuItemTemperatura);

        jMenuItemHumedad.setText("Humedad");
        jMenuItemHumedad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemHumedadActionPerformed(evt);
            }
        });
        jMenuStream.add(jMenuItemHumedad);

        jMenuBar.add(jMenuStream);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDEscritorio)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDEscritorio)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemTemperaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTemperaturaActionPerformed
        jDEscritorio.add(temperatura);
        temperatura.show();
    }//GEN-LAST:event_jMenuItemTemperaturaActionPerformed

    private void jMenuItemHumedadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemHumedadActionPerformed
        jDEscritorio.add(humedad);
        humedad.show();
    }//GEN-LAST:event_jMenuItemHumedadActionPerformed

    private void jMenuItemMonitorEventosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemMonitorEventosActionPerformed

        jDEscritorio.add(monitor);
        monitor.show();
    }//GEN-LAST:event_jMenuItemMonitorEventosActionPerformed

    private void jMenuItemPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPanelActionPerformed
        jDEscritorio.add(control);
        control.show();
    }//GEN-LAST:event_jMenuItemPanelActionPerformed

    private void jMenuItemPuertosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPuertosActionPerformed
        jDEscritorio.add(puertos);
        puertos.show();
    }//GEN-LAST:event_jMenuItemPuertosActionPerformed

    private void jMenuItemNotificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNotificacionActionPerformed
        jDEscritorio.add(notificacion);
        notificacion.show();

    }//GEN-LAST:event_jMenuItemNotificacionActionPerformed

    private void jMenuItemAccidentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAccidentesActionPerformed
        jDEscritorio.add(hAccidentes);
        hAccidentes.show();
    }//GEN-LAST:event_jMenuItemAccidentesActionPerformed

    private void jMenuItemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItemSalirActionPerformed

    private void jMenuItemAccesosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAccesosActionPerformed
        jDEscritorio.add(accesos);
        accesos.show();
    }//GEN-LAST:event_jMenuItemAccesosActionPerformed

    private void jMenuItemsIntrusosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemsIntrusosActionPerformed
        jDEscritorio.add(intrusos);
        intrusos.show();
    }//GEN-LAST:event_jMenuItemsIntrusosActionPerformed

    private void jMenuItemEliminarRegistrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEliminarRegistrosActionPerformed
        jDEscritorio.add(eliminar);
        eliminar.show();
    }//GEN-LAST:event_jMenuItemEliminarRegistrosActionPerformed

    private void jMenuItemServiciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemServiciosActionPerformed
        jDEscritorio.add(servicios);
        servicios.show();
    }//GEN-LAST:event_jMenuItemServiciosActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Escritorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Escritorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Escritorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Escritorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible", false);
            BeautyEyeLNFHelper.translucencyAtFrameInactive = false;
            UIManager.put("ToolBar.isPaintPlainBackground", Boolean.TRUE);

        } catch (Exception e) {
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Escritorio().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Escritorio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDEscritorio;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenu jMenuControl;
    private javax.swing.JMenuItem jMenuItemAccesos;
    private javax.swing.JMenuItem jMenuItemAccidentes;
    private javax.swing.JMenuItem jMenuItemEliminarRegistros;
    private javax.swing.JMenuItem jMenuItemHumedad;
    private javax.swing.JMenuItem jMenuItemMonitorEventos;
    private javax.swing.JMenuItem jMenuItemNotificacion;
    private javax.swing.JMenuItem jMenuItemPanel;
    private javax.swing.JMenuItem jMenuItemPuertos;
    private javax.swing.JMenuItem jMenuItemSalir;
    private javax.swing.JMenuItem jMenuItemServicios;
    private javax.swing.JMenuItem jMenuItemTemperatura;
    private javax.swing.JMenuItem jMenuItemsIntrusos;
    private javax.swing.JMenu jMenuMonitores;
    private javax.swing.JMenu jMenuRegistros;
    private javax.swing.JMenu jMenuStream;
    // End of variables declaration//GEN-END:variables
}
