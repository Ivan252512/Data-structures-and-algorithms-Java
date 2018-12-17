import java.io.Serializable;

/**
 * <p> Interfaz para base de datos de usuarios </p> <p>Esta clase contiene las
 * operaciones elementales para una base de datos </p>
 * @author Carlos Iván Pineda Santiago <ivanpineda@ciencias.unam.mx>
 * @version 1.0
 */
public class BaseDeDatosUsuarios implements
             BaseDeDatos<Usuario>, Serializable{
    /* Arreglo donde guardaremos los usuarios */
    private Usuario [] usuarios;
    private int numUsuarios;

    /**
     * Constructor por omisión de la base de datos.
     */
    public BaseDeDatosUsuarios(){
        numUsuarios = 0;
    }

    /**
     * Constructor de la base de datos.
     * @param usuario el primero usuario.
     */
    public BaseDeDatosUsuarios(Usuario usuario){
        usuarios = new Usuario[1];
        usuarios[0] = usuario;
        numUsuarios = 1;
    }

    /**
     * Constructor de la base de datos.
     * @param usuarios un arreglo de usuarios.
     */
    public BaseDeDatosUsuarios(Usuario [] usuarios){
        this.usuarios = usuarios;
        numUsuarios = usuarios.length;
    }

    /**
     * Constructor de la base de datos.
     * @param bddUsuarios una bdd de usuarios.
     */
    public BaseDeDatosUsuarios(BaseDeDatosUsuarios bddUsuarios){
        this.usuarios = bddUsuarios.usuarios;
        numUsuarios = bddUsuarios.numUsuarios;
    }

    /**
     * Método que nos dice si la base de datos está vacía.
     * @return <code>true</code> si la bdd está vacía, <code>false</code>
     * en otro caso.
     */
    public boolean esVacio(){
       return numUsuarios == 0;
    }

    /**
     * Método para obtener el tamaño de la bdd.
     * @return número de registros en la bdd.
     */
    public int getTamanio(){
        return numUsuarios;
    }

    /**
     * Método para eliminar todos los elementos de una bdd.
     */
    public void vaciar(){
        usuarios = null;
        numUsuarios = 0;
    }

    /**
     * Método para agregar un elemento a la bdd.
     * @param usuario Objeto que se incorporara a la bdd.
     */
    public void agregar(Usuario usuario){
        if (numUsuarios>0){
            Usuario [] nuevo = new Usuario[this.numUsuarios + 1];
            int cont=0;
            while (cont<usuarios.length){
              nuevo[cont] = this.usuarios[cont++];
            }
            nuevo[nuevo.length-1] = usuario;
            this.usuarios = nuevo;
            this.numUsuarios = nuevo.length;
        }else{
            this.usuarios = new Usuario[1];
            this.usuarios[0] = usuario;
            this.numUsuarios = 1;
        }
    }

    /**
     * Método para eliminar un <code>elemento</code> de la bdd
     * @param usuario Objeto que se eliminara de la bdd.
     */
    public void eliminar(Usuario usuario){
        if (contiene(usuario)){
            Usuario [] eliminado = new Usuario[numUsuarios - 1];
            int cont=0;
            while (cont<eliminado.length){
                if (!usuario.equals(usuarios[cont]))
                   eliminado[cont] = usuarios[cont];
                cont++;
            }
            usuarios = eliminado;
            numUsuarios = eliminado.length;
        }
    }

    /**
     * Método para eliminar un elemento a la bdd.
     * @param id identificador del Objeto que se elimina de la bdd.
     */
    public void eliminar(int id){
        if (contiene(id)){
            Usuario [] eliminado = new Usuario[numUsuarios - 1];
            int cont=0;
            while (cont<eliminado.length){
                if (!(usuarios[cont].getId() == id))
                   eliminado[cont] = usuarios[cont];
                cont++;
            }
            usuarios = eliminado;
            numUsuarios = eliminado.length;
        }
    }

    /**
     * Método para ver si un elemento pertenece a la bdd.
     * @param elemento Objeto que se va a buscar en la bdd
     * @return <code>true</code> si el elemento esta en la bdd,
     * <code>false</code> en otro caso.
     */
    public boolean contiene(Usuario usuario){
          return contiene(usuario.getId());
    }

    /**
     * Método para ver si un elemento pertenece a la bdd.
     * @param id idntificador del Objeto que se va a buscar en la bdd
     * @return <code>true</code> si el elemento esta en la bdd,
     * <code>false</code> en otro caso.
     */
    public boolean contiene(int id){
        if (!esVacio()){
            int cont = 0;
            while(cont<numUsuarios){
                if (usuarios[cont].getId() == id)
                    return true;
                cont++;
            }
        }
        return false;
    }

    /**
     * Método para obtener un usuario con su número de producto.
     * @param id número del usuario.
     * @return usuario el usuario a consultar, si no esta en la bdd regresa
     * null.
     */
    public Usuario getUsuario(int id){
        int cont = 0;
        while(cont<numUsuarios){
            if(usuarios[cont].id == id)
                return usuarios[cont];
            cont++;
        }
        throw new NullPointerException();
    }

    /**
     * Método toString para la base de datos.
     * @return cadena un String con todos los elementos de la bdd.
     */
    @Override
    public String toString(){
        int cont = 0;
        String cadena = "----------------------------------------------" + "\n";
        while (cont<numUsuarios){
            cadena += usuarios[cont].toString() + "\n" +
                      "----------------------------------------------" + "\n";
            cont++;
        }
        return cadena;
    }
}
