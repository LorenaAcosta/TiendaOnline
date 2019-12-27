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
public class Categoria {
    String id_categoria;
    String descripcion;
    String imagen;
    

    @Override
    public String toString() {
        return "Categoria{" + "id_categoria=" + id_categoria + ", descripcion=" + descripcion + '}';
    }

    public Categoria(String id_categoria, String descripcion, String imagen) {
        this.id_categoria = id_categoria;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    

    public Categoria() {
    }

    public String getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(String id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String nombre_categoria) {
        this.descripcion = nombre_categoria;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
   
    
}
