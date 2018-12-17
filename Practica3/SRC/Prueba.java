import java.util.Random;

public class Prueba{
  public static void main(String[] args) {

    System.out.println("################## Cola ###################");

    Cola<Integer> prueba = new Cola<Integer>();
    Random rand = new Random();

    for(int i=0; i<25; i++){
        prueba.agrega(i);
    }

    System.out.println("-----------Agregar--------------");
    System.out.println(prueba.toString());
    System.out.println("Longitud: " + prueba.getTamanio());

    System.out.println("-----------Eliminar--------------");
    int elim;
    for(int i=0; i<rand.nextInt(25); i++){
        elim = rand.nextInt(25);
        prueba.elimina(elim);
        System.out.println(prueba.toString() + " ... Se elimino: " + elim);
    }
    System.out.println("Longitud: " + prueba.getTamanio());

    System.out.println("-----------Contiene--------------");
    int cont;
    for (int i=0; i<rand.nextInt(25); i++){
        cont = rand.nextInt(25);
        System.out.println(prueba.contiene(cont) + " ... Contiene: " + cont);
    }

    System.out.println("-----------Pop--------------");
    int tamanio = prueba.getTamanio();
    for (int i=0; i<tamanio; i++){
        System.out.println(prueba.toString() + " ... Pop: " + prueba.pop());
    }

    System.out.println("-----------Push--------------");
    for (int i=0; i<20; i++){
        System.out.println(prueba.toString() + " ... Push: " + i);
        prueba.push(i);
    }

    // Prueba iterator.
    System.out.println("----------Iterator-------------");
    for (Object a : prueba) {
        System.out.println(a.toString());
    }

    System.out.println("################## Pila ###################");

    Pila<Integer> pruebap = new Pila<Integer>();

    for(int i=0; i<25; i++){
        pruebap.agrega(i);
    }

    System.out.println("-----------Agregar--------------");
    System.out.println(pruebap.toString());
    System.out.println("Longitud: " + pruebap.getTamanio());

    System.out.println("-----------Eliminar--------------");
    for(int i=0; i<rand.nextInt(25); i++){
        elim = rand.nextInt(25);
        pruebap.elimina(elim);
        System.out.println(pruebap.toString() + " ... Se elimino: " + elim);
    }
    System.out.println("Longitud: " + pruebap.getTamanio());

    System.out.println("-----------Contiene--------------");
    for (int i=0; i<rand.nextInt(25); i++){
        cont = rand.nextInt(25);
        System.out.println(pruebap.contiene(cont) + " ... Contiene: " + cont);
    }

    System.out.println("-----------Pop--------------");
    int tamaniop = pruebap.getTamanio();
    for (int i=0; i<tamaniop; i++){
        System.out.println(pruebap.toString() + " ... Pop: " + pruebap.pop());
    }

    System.out.println("-----------Push--------------");
    for (int i=0; i<20; i++){
        System.out.println(pruebap.toString() + " ... Push: " + i);
        pruebap.push(i);
    }

    // Pruebap iterator.
    System.out.println("----------Iterator-------------");
    for (Object a : pruebap) {
        System.out.println(a.toString());
    }


  }
}
