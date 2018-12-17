/**
 * <p> Interfaz para base de datos </p> <p>Esta clase contiene las
 * operaciones elementales para una base de datos </p>
 * @author Carlos Iván Pineda Santiago <ivanpineda@ciencias.unam.mx>
 * @version 1.0
 */
public interface BaseDeDatos<T> {
    /**
     * Método que nos dice si la base de datos está vacía.
     * @return <code>true</code> si la bdd está vacía, <code>false</code>
     * en otro caso.
     */
    public boolean esVacio();

    /**
     * Método para obtener el tamaño de la bdd.
     * @return número de registros en la bdd.
     */
    public int getTamanio();

    /**
     * Método para eliminar todos los elementos de una bdd.
     */
    public void vaciar();

    /**
     * Método para agregar un elemento a la bdd.
     * @param elemento Objeto que se incorporara a la bdd.
     */
    public void agregar(T elemento);

    /**
     * Método para eliminar un <code>elemento</code> de la bdd
     * @param elemento Objeto que se eliminara de la bdd.
     */
    public void eliminar(T elemento);

    /**
     * Método para eliminar un elemento a la bdd.
     * @param id identificador del Objeto que se elimina de la bdd.
     */
    public void eliminar(int id);

    /**
     * Método para ver si un elemento pertenece a la bdd.
     * @param elemento Objeto que se va a buscar en la bdd
     * @return <code>true</code> si el elemento esta en la bdd,
     * <code>false</code> en otro caso.
     */
    public boolean contiene (T elemento);

    /**
     * Método para ver si un elemento pertenece a la bdd.
     * @param id idntificador del Objeto que se va a buscar en la bdd
     * @return <code>true</code> si el elemento esta en la bdd,
     * <code>false</code> en otro caso.
     */
    public boolean contiene (int id);
}
