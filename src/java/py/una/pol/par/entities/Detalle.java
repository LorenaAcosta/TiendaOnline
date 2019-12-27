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
public class Detalle {
    private int id_transaccion;
    private String id_producto;
    private int cantidad;
    private int precio;
    private int subtotal;
    private int id_detalle;

    public Detalle(int id_transaccion, String id_producto, int cantidad, int precio, int subtotal, int id_detalle) {
        this.id_transaccion = id_transaccion;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.subtotal = subtotal;
        this.id_detalle = id_detalle;
    }

    public Detalle() {
    }

    public int getId_detalle() {
        return id_detalle;
    }

    public void setId_detalle(int id_detalle) {
        this.id_detalle = id_detalle;
    }

    public int getId_transaccion() {
        return id_transaccion;
    }

    public void setId_transaccion(int id_transaccion) {
        this.id_transaccion = id_transaccion;
    }

    public String getId_producto() {
        return id_producto;
    }

    public void setId_producto(String id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }
    
}
