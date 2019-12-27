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
public class Cabecera {
    private int id_transaccion;
    private int id_usuario;
    private String fecha;
    private int total;
    private String direccion_envio;
    private String estado;
    private int medio_pago;
    private int nro_tarjeta;
    private String estado_trans;

    public Cabecera(int id_transaccion) {
        this.id_transaccion = id_transaccion;
    }

    public Cabecera(int id_transaccion, int id_usuario, String fecha, int total) {
        this.id_transaccion = id_transaccion;
        this.id_usuario = id_usuario;
        this.fecha = fecha;
        this.total = total;
    }

    public Cabecera(int id_transaccion, int id_usuario, String fecha, int total, String direccion_envio, String estado, int medio_pago, int nro_tarjeta) {
        this.id_transaccion = id_transaccion;
        this.id_usuario = id_usuario;
        this.fecha = fecha;
        this.total = total;
        this.direccion_envio = direccion_envio;
        this.estado = estado;
        this.medio_pago = medio_pago;
        this.nro_tarjeta = nro_tarjeta;
    }

    

  

    public Cabecera() {
    }


    public int getId_transaccion() {
        return id_transaccion;
    }

    public void setId_transaccion(int id_transaccion) {
        this.id_transaccion = id_transaccion;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getDireccion_envio() {
        return direccion_envio;
    }

    public void setDireccion_envio(String direccion_envio) {
        this.direccion_envio = direccion_envio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
 public int getMedio_pago() {
        return medio_pago;
    }

    public void setMedio_pago(int medio_pago) {
        this.medio_pago = medio_pago;
    }

    public int getNro_tarjeta() {
        return nro_tarjeta;
    }

    public void setNro_tarjeta(int nro_tarjeta) {
        this.nro_tarjeta = nro_tarjeta;
    }

    public String getEstado_trans() {
        return estado_trans;
    }

    public void setEstado_trans(String estado_trans) {
        this.estado_trans = estado_trans;
    }
   
}
