/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stream;

/**
 *
 * @author xyges
 */
public class lockThread {
        public Boolean ejecucionStream;
        public Boolean activacionDelIf;

    public lockThread(Boolean ejecucionStream, Boolean activacionDelIf) {
        this.ejecucionStream = ejecucionStream;
        this.activacionDelIf = activacionDelIf;
    }

    public Boolean getEjecucionStream() {
        return ejecucionStream;
    }

    public void setEjecucionStream(Boolean ejecucionStream) {
        this.ejecucionStream = ejecucionStream;
    }

    public Boolean getActivacionDelIf() {
        return activacionDelIf;
    }

    public void setActivacionDelIf(Boolean activacionDelIf) {
        this.activacionDelIf = activacionDelIf;
    }
        
}
