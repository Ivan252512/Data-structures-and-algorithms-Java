import java.util.Random;

public class Prueba{
    public static void main(String[] args) {
        Conjunto prueba = new Conjunto();
        Conjunto prueba1 = new Conjunto();
        Random rand = new Random();
        int randomNum = rand.nextInt(50);
        int randomNum1 = rand.nextInt(50);


        /*Agregamos elementos al conjunto*/
        for(int i=0; i<randomNum; i++){
            prueba.agregar(rand.nextInt(50));
        }

        /*Agregamos elementos al conjunto*/
        for(int i=0; i<randomNum1; i++){
            prueba1.agregar(rand.nextInt(50));
        }

        System.out.println("-----------Conunto 1--------------");
        System.out.println(prueba.toString());
        Conjunto pruebaCopia = new Conjunto(prueba);

        System.out.println("-----------Conunto 2--------------");
        System.out.println(prueba1.toString());

        // Probamos el método para eliminar.
        System.out.println("-----------Eliminación--------------");
        for (int i=0; i<rand.nextInt(randomNum);i++){
            int elim = rand.nextInt(randomNum);
            prueba.eliminar(elim);
            System.out.println(prueba.toString() + "  Se eliminó: " + elim);
        }

        // Prueba para la unión.
        System.out.println("-----------Unión--------------");
        System.out.println(prueba.union(prueba1).toString());

        // Prueba para la intersección.
        System.out.println("-----------Intersección--------------");
        System.out.println(prueba.interseccion(prueba1).toString());

        // Prueba para la diferencia.
        System.out.println("-----------Diferencia--------------");
        System.out.println(prueba.diferencia(prueba1).toString());

        // Prueba para la diferencia simétrica.
        System.out.println("-----------Diferencia simétrica--------------");
        System.out.println(prueba.diferenciaSimetrica(prueba1).toString());

        // Prueba para subconjunto.
        System.out.println("-----------Subconjunto--------------");
        System.out.println(prueba.subconjunto(pruebaCopia));
        System.out.println(prueba.subconjunto(prueba));
        System.out.println(prueba.subconjunto(prueba1));

        // Prueba iterator.
        System.out.println("----------Iterator------------");
        for (Object a : prueba) {
            System.out.println(a);
        }
    }
}
