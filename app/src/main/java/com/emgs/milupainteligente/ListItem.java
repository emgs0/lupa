package com.emgs.milupainteligente;

public class ListItem {

    private String descripcion;
    private int iconResource;

    public ListItem(int iconResource,String descripcion) {
        this.iconResource = iconResource;
        this.descripcion = descripcion;
    }

    public int getIconResource() {
        return iconResource;
    }

    public void setIconResource(int iconResource) {
        this.iconResource = iconResource;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
