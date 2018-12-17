public class Palabra implements Comparable<Palabra>{
    //String que guarda
    private String palabra;

    /**
     * Método constructor.
     * @param p la palabra.
     */
    public Palabra(String p){
        this.palabra = p;
    }

    /**
     * @return la palabra
     */
    public String getPalabra() {
        return palabra;
    }

    /**
     * Quita los símbolos más usados exclusivos del español.
     * @return el String de la palabra normalizada.
     */
    public String normaliza(){
        char toMin; // El carácter que normalizaremos.
        String norm = ""; // String con la palabra normalizada.
        
        for (int i=0; i<this.palabra.length(); i++){
            //Uno a uno los char de la palabra a normalizar.
            toMin = this.palabra.charAt(i);
            
            //Pasamos a  minúsculas.
            if(Character.isUpperCase(toMin))
                toMin = Character.toLowerCase(toMin); 

            //Cambiamos a signos del abecedario que no solo es español.
            switch ((int) toMin) {
                case 'é': // e
                    toMin = 'e';
                    break;
                case 'á': // a
                    toMin = 'a';
                    break;
                case 'í': // i
                    toMin = 'i';
                    break;
                case 'ó': // o
                    toMin = 'o';
                    break;
                case 'ú': case 'ü': // u
                    toMin = 'u';
                    break;
                case 'ñ': // ñ
                    toMin = 'n'; //n
                    break;
                default:
                    break;
            }
            if (toMin=='.' || toMin==',' || toMin=='¡' || toMin=='¿' || toMin=='?' || toMin=='!'||
                toMin=='(' || toMin==')' || toMin==':' || toMin==';' || toMin=='-' || toMin=='_' ||
                toMin==' ' || toMin=='{' || toMin=='}' || toMin=='$' || toMin=='[' || toMin==']' )
                norm = norm;
            else    
                norm += toMin;
        }
        return norm;
    }

    /**
     * Método compareTo para palabras.
     */
    @Override
    public int compareTo(Palabra p) {
        String norm = this.normaliza();
        String pnorm = p.normaliza();
        if (this.palabra.length()<=p.palabra.length()){
            for(int i=0; i<norm.length(); i++){
                if (norm.charAt(i)<pnorm.charAt(i))
                    return -1;
                else if (norm.charAt(i)>pnorm.charAt(i))
                    return 1;
            }
            return (norm.length()==pnorm.length()) ? 0:-1;
        }else{
            for(int i=0; i<pnorm.length(); i++){
                if (norm.charAt(i)<pnorm.charAt(i))
                    return -1;
                else if (norm.charAt(i)>pnorm.charAt(i))
                    return 1;
            }
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj)throws ClassCastException {
        if (!(obj instanceof Palabra)) 
            throw new ClassCastException();
        Palabra objp = (Palabra) obj;
        return this.getPalabra().equals(objp.getPalabra());
    }

    @Override
    public String toString() {
        return palabra;
    }
}
