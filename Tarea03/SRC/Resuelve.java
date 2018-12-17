
public class Resuelve{

    public static Laberinto conSolucion(int m, int n){

        Pila<Integer[]> p = new Pila<Integer[]>();
        Laberinto l = new Laberinto(m,n);
        Laberinto copia = new Laberinto(l);


        Integer [] posIn = new Integer[2];
        posIn[0] = 1;
        posIn[1] = 1;

        Integer [] posFin = new Integer[2];
        posFin[0] = m - 2;
        posFin[1] = n - 2;

        while(!(posIn[0] == posFin[0] && posIn[1] == posFin[1])){
            
            if(l.libreIzqV(posIn))
                posIn = l.mueveIzq(posIn);

            if(l.libreDerV(posIn))
                posIn = l.mueveDer(posIn);

            if(l.libreAbajoV(posIn))
                posIn = l.mueveAbajo(posIn);

            if(l.libreArribaV(posIn))
                posIn = l.mueveArriba(posIn);
            
            l.visitada(posIn);
            p.agrega(posIn);


            while(!l.libreV(posIn)){
                posIn = p.pop();
                if(p.esVacio()){
                    l = new Laberinto(m,n);
                    copia = new Laberinto(l);
                    posIn[0] = 1;
                    posIn[1] = 1;
                    break;
                }
            }
        }
        return copia;
    }
}