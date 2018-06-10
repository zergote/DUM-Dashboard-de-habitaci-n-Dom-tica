/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

/**
 *
 * @author xyges
 */
public class Accidente {
    private int id;
    private String fecha;
    private String tipoAccidente; 

    public Accidente(String fecha, String tipoAccidente) {
        this.fecha = fecha;
        this.tipoAccidente = tipoAccidente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipoAccidente() {
        return tipoAccidente;
    }

    public void setTipoAccidente(String tipoAccidente) {
        this.tipoAccidente = tipoAccidente;
    }
    public void save(){
        Conector con = new Conector();
        con.connect();
        con.saveAccidente(this);
        con.close();
    }
}
