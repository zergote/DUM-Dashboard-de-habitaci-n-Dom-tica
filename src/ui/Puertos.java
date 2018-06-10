/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import stream.StreamData;
import stream.lockThread;
import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.awt.Window;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 *
 * @author xyges
 */
public class Puertos extends javax.swing.JInternalFrame {

    PanamaHitek_Arduino teensy;
    StreamData datos;
    lockThread lock;
    SerialPortEventListener evento = new SerialPortEventListener() {
        @Override
        public void serialEvent(SerialPortEvent spe) {
        }
    };

    public Puertos(PanamaHitek_Arduino teensy, lockThread lock) {
        initComponents();
        this.lock = lock;
        this.teensy = teensy;
        jToggleButtonConectar.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBoxPuertos = new javax.swing.JComboBox();
        jButtonActualizar = new javax.swing.JButton();
        jToggleButtonConectar = new javax.swing.JToggleButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Seleccionar Puerto");

        jComboBoxPuertos.setMaximumRowCount(100);

        jButtonActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/refreshicon.png"))); // NOI18N
        jButtonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActualizarActionPerformed(evt);
            }
        });

        jToggleButtonConectar.setText("Conectar");
        jToggleButtonConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonConectarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jToggleButtonConectar, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jComboBoxPuertos, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonActualizar)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxPuertos, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jToggleButtonConectar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarActionPerformed
        jComboBoxPuertos.removeAllItems();
        if (teensy.getPortsAvailable() > 0) {
            teensy.getSerialPorts().forEach(i -> jComboBoxPuertos.addItem(i));
            jToggleButtonConectar.setEnabled(true);
        } else {
            jToggleButtonConectar.setEnabled(false);
        }

    }//GEN-LAST:event_jButtonActualizarActionPerformed

    private void jToggleButtonConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonConectarActionPerformed
        if (jToggleButtonConectar.getText().equals("Desconectar")) {
            try {
                teensy.killArduinoConnection();
                jToggleButtonConectar.setText("Conectar");
                lock.activacionDelIf = false;
                //lock.ejecucionStream = false;
            } catch (Exception e) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, e);
            }
        } else {
            try {
                teensy.arduinoRXTX(jComboBoxPuertos.getSelectedItem().toString(), 9600, evento);
                lock.ejecucionStream = true;
                lock.activacionDelIf = true;
                jToggleButtonConectar.setText("Desconectar");
            } catch (Exception e) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }//GEN-LAST:event_jToggleButtonConectarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonActualizar;
    private javax.swing.JComboBox jComboBoxPuertos;
    private javax.swing.JToggleButton jToggleButtonConectar;
    // End of variables declaration//GEN-END:variables
}
