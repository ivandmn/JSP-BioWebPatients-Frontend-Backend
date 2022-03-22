package adnutils;

import java.util.Random;

/**
 * 
 * @author tarda
 */
public class ADN_Manager {

    /**
     * Funcio que agafa l'atribut ADN i el retorna invertit
     * @return ADN invertit.
     */
    public String invertADN(String ADN) {
        StringBuilder builder = new StringBuilder(ADN);
        return builder.reverse().toString();
    }
    
    public boolean validaADN(String ADN) {
        int a = numAdenines(ADN);
        int c = numCitosines(ADN);
        int g = numGuanines(ADN);
        int t = numTimines(ADN);
        int total = a + c + g + t;
        return ADN.length() == total;
    }
    

    /**
     * Fa recompte de totes les A's i retorna la quantitat
     *
     * @return Numero de adenines acumulades a tota la cadena
     */
    public int numAdenines(String ADN) {
        int a = 0;
        //char[] letter = this.adn.toUpperCase().toCharArray();
        for (int i = 0; i < ADN.length(); i++) {
            if (ADN.toUpperCase().charAt(i) == 'A') {
                a++;
            }
        }
        return a;
    }

    /**
     * Fa recompte de totes les G's i retorna la quantitat
     *
     * @return Numero de adenines acumulades a tota la cadena
     */
    public int numGuanines(String ADN) {
        int g = 0;
        //char[] letter = this.adn.toUpperCase().toCharArray();
        for (int i = 0; i < ADN.length(); i++) {
            if (ADN.toUpperCase().charAt(i) == 'G') {
                g++;
            }
        }
        return g;
    }

    /**
     * Fa recompte de totes les T's i retorna la quantitat
     *
     * @return Numero de adenines acumulades a tota la cadena
     */
    public int numTimines(String ADN) {
        int t = 0;
        for (int i = 0; i < ADN.length(); i++) {
            if (ADN.toUpperCase().charAt(i) == 'T') {
                t++;
            }
        }
        return t;
    }

    /**
     * Fa recompte de totes les C's i retorna la quantitat
     *
     * @return Numero de adenines acumulades a tota la cadena
     */
    public int numCitosines(String ADN) {
        int c = 0;
        for (int i = 0; i < ADN.length(); i++) {
            if (ADN.toUpperCase().charAt(i) == 'C') {
                c++;
            }
        }
        return c;
    }
    
    public String randomADNGenerator(int lenght){
        String chain = "";
        String[] arrayADN = new String[]{"A","G","C","T"};
        Random rand = new Random();
        for(int i = 0; i < lenght; i++){
            int randomNumber = rand.nextInt(4);
            chain = chain + arrayADN[randomNumber];
        }
        return chain;
        
    }

    /**
     * Funcio que compara el recompte de totes les lletres
     *
     * @return La lletra que té mes recompte que la resta
     */
    public String maxLetter(String ADN) {
        int max = 0;
        String base;
        int a = numAdenines(ADN);
        int c = numCitosines(ADN);
        int g = numGuanines(ADN);
        int t = numTimines(ADN);

        if (a > c && a > g && a > t) {
            base = "A";
            max = a;
        } else if (c > a && c > g && c > t) {
            base = "C";
            max = c;
        } else if (g > a && g > c && g > t) {
            base = "G";
            max = g;
        } else {
            base = "T";
            max = t;
        }

        return base;
    }
 /**
     * Funcio que compara el recompte de totes les lletres
     *
     * @return La lletra que té menys recompte que la resta
     * 
     */
    public String minLetter(String ADN) {
        int min = 0;
        String base;
        int a = numAdenines(ADN);
        int c = numCitosines(ADN);
        int g = numGuanines(ADN);
        int t = numTimines(ADN);

        if (a < c && a < g && a < t) {
            base = "A";
            min = a;
        } else if (c < a && c < g && c < t) {
            base = "C";
            min = c;
        } else if (g < a && g < c && g < t) {
            base = "G";
            min = g;
        } else {
            base = "T";
            min = t;
        }

        return base;
    }
}
