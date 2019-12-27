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
public class Usuario {
    private int idusuario;
    private String login_name;
    private String contrasenha;
    private String apellido;
    private String nombre;
    private int tipo_usuario;
    private String correo;

    public Usuario() {
    }

    public Usuario(int idusuario, String login_name, String contrasenha, String apellido, String nombre, int tipo_usuario, String correo) {
        this.idusuario = idusuario;
        this.login_name = login_name;
        this.contrasenha = contrasenha;
        this.apellido = apellido;
        this.nombre = nombre;
        this.tipo_usuario = tipo_usuario;
        this.correo = correo;
    }

    
  
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(int tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }
    
}
