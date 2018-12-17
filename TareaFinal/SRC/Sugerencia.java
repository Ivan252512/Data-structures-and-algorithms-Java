public class Sugerencia implements Comparable<Sugerencia> {
    //String de la cual se quiere encontrar una corrección ortográfica.
    private String original;

    //String con la sugerencia.
    private String sugerencia;

    //Medida del parecido entre la original y la sugerencia.
    private double parecido;

    public Sugerencia(String original, String sugerencia){
        this.original = original;
        this.sugerencia = sugerencia;
        this.parecido = parecido();
    }

    /**
     * @return the original
     */
    public String getOriginal() {
        return original;
    }

    /**
     * @return the sugerencia
     */
    public String getSugerencia() {
        return sugerencia;
    }

    /**
     * @return the parecido
     */
    public double getParecido() {
        return parecido;
    }

    private double parecido(){
        int difTam = Math.abs(original.length()-sugerencia.length());
        int parecidoLetras = 0;
        int casiParecidoOrden = 0;
        int parecidoOrden = 0;

        for (int i=0; i<original.length(); i++){
            for (int j=0; j<sugerencia.length(); j++){
                if(original.charAt(i)==sugerencia.charAt(j))
                    parecidoLetras++;
            }
        }

        if (original.length()<=sugerencia.length()){
            for (int i=1; i<original.length()-1; i++){
                if(original.charAt(i)==sugerencia.charAt(i)
                || original.charAt(i)==sugerencia.charAt(i))
                    parecidoOrden++;
            }
        }else{
            for (int i=1; i<sugerencia.length()-1; i++){
                if(original.charAt(i)==sugerencia.charAt(i)
                || original.charAt(i)==sugerencia.charAt(i))
                    parecidoOrden++;
            }
        }        

        if (original.length()>2 && sugerencia.length()>2){
            if (original.length()<=sugerencia.length()){
                for (int i=1; i<original.length()-1; i++){
                    if(original.charAt(i+1)==sugerencia.charAt(i+1)
                    || original.charAt(i-1)==sugerencia.charAt(i-1))
                        casiParecidoOrden++;
                }
            }else{
                for (int i=1; i<sugerencia.length()-1; i++){
                    if(original.charAt(i+1)==sugerencia.charAt(i+1)
                    || original.charAt(i-1)==sugerencia.charAt(i-1))
                        casiParecidoOrden++;
                }
            }
        }

        return (10*parecidoOrden + 5*casiParecidoOrden + 2*parecidoLetras)/(3*(difTam+1));
    }

    @Override
    public int compareTo(Sugerencia s) {
        if (this.parecido<s.parecido)
            return -1;
        else if (this.parecido>s.parecido)
            return 1;
        else
            return 0;
    }

    @Override
    public boolean equals(Object obj) throws ClassCastException{
        if (!(obj instanceof Sugerencia)) 
            throw new ClassCastException();
        Sugerencia objs = (Sugerencia) obj;
        return original == objs.original && sugerencia == objs.sugerencia;
    }

}