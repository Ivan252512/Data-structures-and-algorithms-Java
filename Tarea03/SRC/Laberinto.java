import java.util.Random;

public class Laberinto{
    /* Arreglo de dos dimensiones para crear el laberinto.*/
    private Integer[][] laberinto;
    /* Acceso más fácil al tamaño del arreglo */
    private int m;
    private int n;

    /**
     * Método constructor para un laberinto, casilla en cero es casilla abierta, 
     * -1 es casilla cerrada.
     */
    public Laberinto(int m, int n){
        this.m = m;
        this.n = n;
        laberinto = new Integer[m][n];
        Random rand = new Random();
        for (int i=0; i<m; i++){
            for (int j=0; j<n; j++){
                laberinto[i][j] = (-1) *  rand.nextInt(2);
                // Creamos una pared alrededor de todo el laberinto
                if (i==0 || j==0 || i==m-1 || j == n-1)
                    laberinto[i][j] = -1;
            }
        }
        // Garantizamos que el inicio y el fin estén abiertos.
        laberinto[1][1]=0;
        laberinto[m-2][n-2]=0;
    }

        /**
     * Método constructor para un laberinto, casilla en cero es casilla abierta, 
     * -1 es casilla cerrada.
     */
    public Laberinto(Laberinto l){
        this.m = l.getM();
        this.n = l.getN();
        laberinto = new Integer[l.getM()][l.getN()];
        for (int i=0; i<l.getM(); i++){
            for (int j=0; j<l.getN(); j++){
                laberinto[i][j] = l.laberinto[i][j];
            }
        }
    }

    /**
     * @return m
     */
    public int getM() {
        return m;
    }

    /**
     * @return n
     */
    public int getN() {
        return n;
    }

    /**
     * @return el laberinto
     */
    public Integer[][] getLaberinto() {
        return laberinto;
    }

    /**
     * Método para cambiar casilla a visitada, representado con -2.
     * @param casilla la casilla visitada.
     */
    public void visitada(Integer[] casilla) {
        if (this.laberinto[casilla[0]][casilla[1]] != -1)
            this.laberinto[casilla[0]][casilla[1]] = -2;
    }

    /**
     * Saber si una casilla ya fue visitada.
     * @param casilla
     * @return true sí ya fue visitada.
     * @return false en caso contrario. 
     */
    public boolean yaVisitada(Integer[] casilla){
        return this.laberinto[casilla[0]][casilla[1]] == -2;
    }

    /**
     * Saber si una casilla tiene al menos un vecino libre y sin visitar.
     * @param casilla
     * @return true sí tiene vecino libre y sin visitar.
     * @return false en caso contrario. 
     */
    public boolean libreV(Integer[] casilla){
        return (libreIzqV(casilla) || libreDerV(casilla) ||
                libreAbajoV(casilla) || libreArribaV(casilla));
    }

    /**
     * Saber si una casilla tiene al vecino izquierdo libre.
     * @param casilla
     * @return true sí tiene vecino libre y sin visitar.
     * @return false en caso contrario. 
     */
    public boolean libreIzq(Integer[] casilla){
        return this.laberinto[casilla[0]-1][casilla[1]]!=-1;
    }

    /**
     * Saber si una casilla tiene al vecino derecho libre.
     * @param casilla
     * @return true sí tiene vecino libre y sin visitar.
     * @return false en caso contrario. 
     */
    public boolean libreDer(Integer[] casilla){
        return this.laberinto[casilla[0]+1][casilla[1]]!=-1;
    }

    /**
     * Saber si una casilla tiene al vecino superior libre.
     * @param casilla
     * @return true sí tiene vecino libre y sin visitar.
     * @return false en caso contrario. 
     */
    public boolean libreArriba(Integer[] casilla){
        return this.laberinto[casilla[0]][casilla[1]-1]!=-1;
    }

    /**
     * Saber si una casilla tiene al vecino inferior libre.
     * @param casilla
     * @return true sí tiene vecino libre y sin visitar.
     * @return false en caso contrario. 
     */
    public boolean libreAbajo(Integer[] casilla){
        return this.laberinto[casilla[0]][casilla[1]+1]!=-1;
    }

/**
     * Saber si una casilla tiene al vecino izquierdo libre y sin visitar.
     * @param casilla
     * @return true sí tiene vecino libre y sin visitar.
     * @return false en caso contrario. 
     */
    public boolean libreIzqV(Integer[] casilla){
        return this.laberinto[casilla[0]-1][casilla[1]]>-1;
    }

    /**
     * Saber si una casilla tiene al vecino derecho libre y sin visitar.
     * @param casilla
     * @return true sí tiene vecino libre y sin visitar.
     * @return false en caso contrario. 
     */
    public boolean libreDerV(Integer[] casilla){
        return this.laberinto[casilla[0]+1][casilla[1]]>-1;
    }

    /**
     * Saber si una casilla tiene al vecino superior libre y sin visitar.
     * @param casilla
     * @return true sí tiene vecino libre y sin visitar.
     * @return false en caso contrario. 
     */
    public boolean libreArribaV(Integer[] casilla){
        return this.laberinto[casilla[0]][casilla[1]-1]>-1;
    }

    /**
     * Saber si una casilla tiene al vecino inferior libre y sin visitar.
     * @param casilla
     * @return true sí tiene vecino libre y sin visitar.
     * @return false en caso contrario. 
     */
    public boolean libreAbajoV(Integer[] casilla){
        return this.laberinto[casilla[0]][casilla[1]+1]>-1;
    }

    /**
     * Método para moverse a la casilla de la izquierda.
     * @param casilla la casilla origen.
     * @return la casilla de la izquierda.
     */
    public Integer[] mueveIzq(Integer[] casilla){
        Integer[] r = casilla;
        if(libreIzq(casilla)){
            r[0] = casilla[0]-1; 
            r[1] = casilla[1];
        }
        return r;
    }


    /**
     * Método para moverse a la casilla de la derecha.
     * @param casilla la casilla origen.
     * @return la casilla de la derecha.
     */
    public Integer[] mueveDer(Integer[] casilla){
        Integer[] r = casilla;
        if(libreDer(casilla)){
            r[0] = casilla[0]+1; 
            r[1] = casilla[1];
        }
        return r;
    }


    /**
     * Método para moverse a la casilla de arriba.
     * @param casilla la casilla origen.
     * @return la casilla de arriba.
     */
    public Integer[] mueveArriba(Integer[] casilla){
        Integer[] r = casilla;
        if(libreArriba(casilla)){
            r[0] = casilla[0]; 
            r[1] = casilla[1]-1;
        }
        return r;
    }


    /**
     * Método para moverse a la casilla de abajo.
     * @param casilla la casilla origen.
     * @return la casilla de abajo.
     */
    public Integer[] mueveAbajo(Integer[] casilla){
        Integer[] r = casilla;
        if(libreAbajo(casilla)){
            r[0] = casilla[0]; 
            r[1] = casilla[1]+1;
        }
        return r;
    }

    public LabRap evaluaCaminos(){
        Laberinto copia = new Laberinto(this);;

        Cola<Integer[]> c = new Cola<Integer[]>();
        Random rand = new Random();
        int r;

        int m = copia.getM();
        int n = copia.getN();

        Integer [] posIn = new Integer[2];
        posIn[0] = 1;
        posIn[1] = 1;

        Integer [] posFin = new Integer[2];
        posFin[0] = m - 2;
        posFin[1] = n - 2;

        copia.visitada(posIn);
        c.agrega(posIn);

        while(!(posIn[0] == posFin[0] && posIn[1] == posFin[1])){
            r = rand.nextInt(4);

            if(copia.libreIzqV(posIn) && r==0){
                posIn = copia.mueveIzq(posIn);
                copia.visitada(posIn);
                c.agrega(posIn);
            }

            if(copia.libreDerV(posIn) && r==1){
                posIn = copia.mueveDer(posIn);
                copia.visitada(posIn);
                c.agrega(posIn);
            }

            if(copia.libreAbajoV(posIn) && r==2){
                posIn = copia.mueveAbajo(posIn);
                copia.visitada(posIn);
                c.agrega(posIn);
            }

            if(copia.libreArribaV(posIn) && r==3){
                posIn = copia.mueveArriba(posIn);
                copia.visitada(posIn);
                c.agrega(posIn);
            }

            if(!copia.libreV(posIn)){
                copia = new Laberinto(this);
                c = new Cola<Integer[]>();
                posIn[0] = 1;
                posIn[1] = 1;
            }
        }
        return new LabRap(c.getTamanio(), copia);
    }

    public Laberinto masRapida(){
        Laberinto copia = new Laberinto(this);
        LabRap masChico = copia.evaluaCaminos();
        LabRap nueva =  copia.evaluaCaminos();

        for (int i=0; i<100; i++){
            copia = new Laberinto(this);
            nueva = copia.evaluaCaminos();
            if(nueva.getRap()<masChico.getRap())
                masChico = nueva;
        }
        return nueva.getL();
    }

    private class LabRap{
        int rap;
        Laberinto l;

        public LabRap(int rap, Laberinto l ){
            this.rap = rap;
            this.l = l;
        }

        /**
         * @return the rap
         */
        public int getRap() {
            return rap;
        }

        /**
         * @return the l
         */
        public Laberinto getL() {
            return l;
        }
    }

    @Override
    public String toString() {
        String s = "";
        for (int i=0; i<m; i++){
            s += "\n";
            for (int j=0; j<n; j++){
                if (laberinto[i][j]==-1)
                    s+="*";
                else if (laberinto[i][j]==-2)
                    s+="x";
                else
                    s+="o";
            }
        }
        return s;
    }
}
