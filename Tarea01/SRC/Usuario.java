import java.io.Serializable;

/**
 * <p> Interfaz para usuarios </p> <p>Esta clase contiene las
 * operaciones elementales para implementar un usuario como administrador
 * o vendedor </p>
 * @author Carlos Iván Pineda Santiago <ivanpineda@ciencias.unam.mx>
 * @version 1.0
 */
public abstract class Usuario implements Serializable{
    /* Identindtificación de usuario. */
    protected static int idnum = 0;
    protected int id = idnum;
    /* Nombre del usuario. */
    protected String nombre;
    /* contraseña del vendedor. */
    protected String contrasenia;

    /**
     * Método constructor para administrador.
     * @param nombre el nombre del administrador.
     * @param contrasenia la contraseña del administrador.
     */
    public Usuario(String nombre, String contrasenia){
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        idnum++;
    }

    /**
     * Método getter para el número de producto.
     * @return noProducto el número del producto.
     */
    public int getId(){
        return id;
    }

    /**
     *Método setter para el nombre de usuario.
     *@param nombre el nombre del usaurio.
     */
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    /**
     *Método getter para el nombre de usuario.
     return nombre;
     *@return nombre el nombre del usaurio.
     */
    public String getNombre(){
        return nombre;
    }

    /**
     *Método setter para la contraseña de usuario.
     *@param contrasenia la contraseña del usaurio.
     */
    public void setContrasenia(String contrasenia){
        this.contrasenia = contrasenia;
    }

    /**
     *Método getter para la contrasenia de usuario.
     *@return contrasenia la contrasenia del usaurio.
     */
    public String getContrasenia(){
        return contrasenia;
    }

    /**
     *Método getter para el tipo de usuario.
     *@return tipo el tipo del usaurio.
     */
    public abstract String getTipoDeUsuario();

    /**
     * Método equals para un usuario.
     * @param o el objeto con el que se va a comparar
     * @return <tt>true</tt> sí los objetos a comparar son iguales.
     *         <tt>false</tt> en otro caso.
     */
     @Override
     public boolean equals(Object o){
        if (!(o instanceof Usuario))
            return false;
        Usuario us = (Usuario) o;
        return id == us.getId();
     }

}
