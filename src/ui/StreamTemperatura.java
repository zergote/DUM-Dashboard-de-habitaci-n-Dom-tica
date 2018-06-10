/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import stream.StreamData;
import com.panamahitek.PanamaHitek_Arduino;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author xyges
 */
public class StreamTemperatura extends javax.swing.JInternalFrame implements Runnable {

    PanamaHitek_Arduino teensy;
    XYSeries series;
    XYSeriesCollection coleccion;
    JFreeChart graficaTemp;
    XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
    final XYPlot plot;
    Thread hilo;
    StreamData datos;
    int contador = 0;
    double i = 0;
    int j = 0;

    public StreamTemperatura(PanamaHitek_Arduino teensy, StreamData datos) {
        this.teensy = teensy;
        this.datos = datos;
        initComponents();
        series = new XYSeries("Temperatura");
        coleccion = new XYSeriesCollection();
        series.add(0, 0);
        coleccion.addSeries(series);
        graficaTemp = ChartFactory.createXYLineChart("Temperatura (Stream)", "Tiempo Seg", "Temperatura °C", coleccion, PlotOrientation.VERTICAL, true, true, true);
        jPanelGraficaTemperatura.setLayout(new java.awt.BorderLayout());
        ChartPanel cp = new ChartPanel(graficaTemp);
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setShapesVisible(false);
        renderer.setSeriesStroke(0, new BasicStroke(1.5f));
        plot = (XYPlot) graficaTemp.getPlot();
        plot.setRenderer(renderer);
        jPanelGraficaTemperatura.add(cp, BorderLayout.CENTER);
        jPanelGraficaTemperatura.validate();
        hilo = new Thread(this);
        this.hilo.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelGraficaTemperatura = new javax.swing.JPanel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        javax.swing.GroupLayout jPanelGraficaTemperaturaLayout = new javax.swing.GroupLayout(jPanelGraficaTemperatura);
        jPanelGraficaTemperatura.setLayout(jPanelGraficaTemperaturaLayout);
        jPanelGraficaTemperaturaLayout.setHorizontalGroup(
            jPanelGraficaTemperaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );
        jPanelGraficaTemperaturaLayout.setVerticalGroup(
            jPanelGraficaTemperaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 282, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelGraficaTemperatura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelGraficaTemperatura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanelGraficaTemperatura;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        while (true) {
            try {
                Random r = new Random();
                contador++;
                if (contador >= 19) {
                    series.remove(0);
                    contador = 19;
                }
                i = i + 0.0166666666666667;
//                j = r.nextInt(100 - 10) + 10;
                series.add(i, datos.temperatura);

                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(StreamTemperatura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
