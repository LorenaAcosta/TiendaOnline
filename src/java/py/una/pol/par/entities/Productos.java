/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.par.entities;

/**
 *
 * @author Lore
 */
public class Productos {
    private String idProducto;
    private String descripcion;
    private int cantidad;
    private int precioUnit;
    private String imagen;
    private String categoria;

    public Productos() {
    }

    public Productos(String idProducto, int cantidad) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }
     public Productos(String idProducto, String descripcion, int cantidad, int precioUnit, String imagen) {
        this.idProducto = idProducto;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioUnit = precioUnit;
        this.imagen = imagen;
    }

    public Productos(String idProducto, String descripcion, int cantidad, int precioUnit, String imagen, String categoria) {
        this.idProducto = idProducto;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioUnit = precioUnit;
        this.imagen = imagen;
        this.categoria = categoria;
    }

    public Productos(String idProducto, String descripcion, int cantidad, int precioUnit) {
        this.idProducto = idProducto;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioUnit = precioUnit;
    }
 
    public Productos(String idProducto, String descripcion, int precioUnit, String imagen) {
        this.idProducto = idProducto;
        this.descripcion = descripcion;
        this.precioUnit = precioUnit;
        this.imagen = imagen;
    }

   

   

public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

  
    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecioUnit() {
        return precioUnit;
    }

    public void setPrecioUnit(int precioUnit) {
        this.precioUnit = precioUnit;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
     public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
