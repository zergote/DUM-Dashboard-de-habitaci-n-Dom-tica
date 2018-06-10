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
public class Intruso {
    private int id;
    private String fecha;
    private String tipoIntruso; 

    public Intruso(String fecha, String tipoAccidente) {
        this.fecha = fecha;
        this.tipoIntruso = tipoAccidente;
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

    public String getTipoIntruso() {
        return tipoIntruso;
    }

    public void setTipoIntruso(String tipoIntruso) {
        this.tipoIntruso = tipoIntruso;
    }
    public void save(){
        Conector con = new Conector();
        con.connect();
        con.saveIntruso(this);
        con.close();
    }
}
