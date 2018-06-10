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
public class Acceso {
    private int id;
    private String fecha;
    private String tipoAcceso; 

    public Acceso(String fecha, String tipoAccidente) {
        this.fecha = fecha;
        this.tipoAcceso = tipoAccidente;
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

    public String getTipoAcceso() {
        return tipoAcceso;
    }

    public void setTipoAccidente(String tipoAcceso) {
        this.tipoAcceso= tipoAcceso;
    }
    public void save(){
        Conector con = new Conector();
        con.connect();
        con.saveAcceso(this);
        con.close();
    }
}
