package com.emgs.milupainteligente;

public class HistorialItem {

    String fecha, resultado;

    public HistorialItem(String fecha,String resultado) {
        this.fecha = fecha;
        this.resultado = resultado;
    }
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getResultado() {return resultado;}

    public void setResultado(String resultado) {this.fecha = resultado;}

}
