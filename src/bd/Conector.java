/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author xyges
 */
public class Conector {

    String url = "C:\\Users\\xyges\\Documents\\NetBeansProjects\\DUMPanel\\bd\\DUMPanel.db";
    Connection connect;

    public Conector() {
    }

    public void connect() {
        try {
            connect = DriverManager.getConnection("jdbc:sqlite:" + url);
            if (connect != null) {
                //System.out.println("Conectado");
            }
        } catch (SQLException e) {
            System.err.println("No se ha podido conectar a la base de datos \n" + e.getMessage());
        }
    }

    public void close() {
        try {
            connect.close();
        } catch (SQLException e) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, e);

        }
    }

    public void saveAccidente(Accidente accidente) {
        try {
            PreparedStatement st = connect.prepareStatement("insert into accidentes (fecha, descripcion) values (?,?)");
            st.setString(1, accidente.getFecha());
            st.setString(2, accidente.getTipoAccidente());
            st.execute();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void saveAcceso(Acceso acceso) {
        try {
            PreparedStatement st = connect.prepareStatement("insert into accesos (fecha, descripcion) values (?,?)");
            st.setString(1, acceso.getFecha());
            st.setString(2, acceso.getTipoAcceso());
            st.execute();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void saveIntruso(Intruso intruso) {
        try {
            PreparedStatement st = connect.prepareStatement("insert into intrusos (fecha, descripcion) values (?,?)");
            st.setString(1, intruso.getFecha());
            st.setString(2, intruso.getTipoIntruso());
            st.execute();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public DefaultTableModel buildTableModel(String tabla) throws SQLException {
        ResultSet rs = null;
        PreparedStatement st = connect.prepareStatement("select * from " + tabla);
        rs = st.executeQuery();
        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        //int columnCount = 3;
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);

    }

    public void eliminarDatos(String tabla) throws SQLException {
        String deleteSQL = "DELETE  FROM "+tabla;
        PreparedStatement st = connect.prepareStatement(deleteSQL);
        st.executeUpdate();
    }
}
