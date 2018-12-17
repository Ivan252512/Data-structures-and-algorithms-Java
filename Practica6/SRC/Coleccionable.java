public abstract interface Coleccionable<T>
  extends Iterable<T>
{
  public abstract void agrega(T paramT);
  
  public abstract void elimina(T paramT);
  
  public abstract boolean contiene(T paramT);
  
  public abstract boolean esVacio();
  
  public abstract int getTamanio();
}
