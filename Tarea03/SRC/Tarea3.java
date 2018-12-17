
public class Tarea3{
    public static void main(String[] args) {
        Laberinto l = Resuelve.conSolucion(
                        Integer.parseInt(args[0]),
                        Integer.parseInt(args[1]));

        System.out.println("--------Camino más rápido---------");
        System.out.println(l.masRapida().toString());
    }
}