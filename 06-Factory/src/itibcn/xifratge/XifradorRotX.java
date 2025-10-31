package itibcn.xifratge;
/*
 * Enunciat
Crea una classe Java anomenada RotX.java que contingui les següents funcions:
xifraRotX( cadena, desplaçament ): Ha de substituir cada lletra (no els espais ni els signes de
puntuació) per la lletra que està "desplaçament" posicions més a la dreta en l’abecedari (si
sobrepassa ha de tornar a començar).
desxifraRotX( cadena, desplaçament ): Ha de fer el procés invers de la funció anterior.
forcaBrutaRotX( cadenaXifrada ): Ha de provar tots els desplaçaments possibles i mostrar el
missatge resultant de desxifrar amb desplaçaments de 1,2,3,... fins la longitud de l’abecedari.
Crea també un main per fer algunes proves.
 */
public class XifradorRotX implements Xifrador {

    static char[] arrayAbecedari = "aáàäbcçdeéèëfghiíìïjklmnñoóòöpqrstuúùüvwxyz".toCharArray();
    static char[] arrayAbecedariM = "aáàäbcçdeéèëfghiíìïjklmnñoóòöpqrstuúùüvwxyz".toUpperCase().toCharArray();

    public static void main(String[] args) {
        String[] proves = { "ABC", "XYZ", "Hola, Mr. calçot",
                "Perdó, per tu què és?" };

        System.out.println("------- CIFRADO ---------");
        for (int i = 0; i < proves.length; i++) {
            String cadena = proves[i];
            String cadenaCifrada = xifraRotX(cadena, i * 2);
            System.out.println("(" + i * 2 + ")-" + cadena + " --> " + cadenaCifrada);
        }
        System.out.println("------ DESCRIFRADO --------");
        for (int i = 0; i < proves.length; i++) {
            String cadena = proves[i];
            String cadenaCifrada = xifraRotX(cadena, i * 2);
            String cadenaDescifrada = desxifraRotX(cadenaCifrada, i * 2);
            System.out.println("(" + i * 2 + ")-" + cadenaCifrada + " --> " + cadenaDescifrada);
            if (i == proves.length - 1) {
                forcaBrutaRotX(cadenaCifrada);
            }
        }
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada){

    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada{

    }
    /*
     * Esta funcion le pasamos por parametro una cadena
     * y nos devolvera el texto cifrado
     */
    public String xifraRotX(String cadena, int desplaçament) {
        String textoCifrado = "";
        for (int i = 0; i < cadena.length(); i++) {
            char caracter = cadena.charAt(i);
            int posicio = buscaLletra(arrayAbecedari, caracter);
            if (posicio != -1) { // si la posicion no es -1, es decir que se encuentra en el abecedario
                // cogemos la nueva posicion sumandole 13 y hacemos el modulo para no pasarnos
                // de posicion
                int posicionNueva = (posicio + desplaçament) % arrayAbecedari.length;
                textoCifrado += arrayAbecedari[posicionNueva];
            } else {
                posicio = buscaLletra(arrayAbecedariM, caracter);
                if (posicio != -1) {
                    int posicionNueva = (posicio + desplaçament) % arrayAbecedariM.length;
                    textoCifrado += arrayAbecedariM[posicionNueva];
                } else {
                    textoCifrado += caracter;
                }
            }
        }
        return textoCifrado;
    }

    /*
     * Esta funcion le pasamos por parametro una cadena
     * y nos devolvera el texto descrifrado
     */
    public String desxifraRotX(String cadena, int desplaçament) {
        String textoDescrifrado = "";
        for (int i = 0; i < cadena.length(); i++) {
            char caracter = cadena.charAt(i);
            int posicio = buscaLletra(arrayAbecedari, caracter);
            if (posicio != -1) { // si la posicion no es -1, es decir que se encuentra en el abecedario
                // Cogemos la nueva posicion restandole 13 para descrifrar y sumandole la
                // longitud del abecedario para que no se quede en negativo
                // Hacemos el modulo por si la suma de la longitud se pasa de la longitud de la
                // array del abecedario
                int posicionNueva = (posicio - desplaçament + arrayAbecedari.length) % arrayAbecedari.length;
                textoDescrifrado += arrayAbecedari[posicionNueva];
            } else {
                posicio = buscaLletra(arrayAbecedariM, caracter);
                if (posicio != -1) {
                    int posicionNueva = (posicio - desplaçament + arrayAbecedari.length) % arrayAbecedariM.length;
                    textoDescrifrado += arrayAbecedariM[posicionNueva];
                } else {
                    textoDescrifrado += caracter;
                }

            }

        }
        return textoDescrifrado;
    }
    /*
     * Esta funcion le pasamos la string cifrada por parametro
     * Imprimira esa string
     * Recorrera la longitud del abecedario e imprimira cada descifrado que se vaya haciendo
     */

    public void forcaBrutaRotX(String cadenaCifrada) {
        System.out.println("\nMissatge xifrat: " + cadenaCifrada + "\n");
        for (int i = 0; i < arrayAbecedari.length; i++) {
            System.out.println("(" + i + ") " + desxifraRotX(cadenaCifrada, i));
        }
    }

    /*
     * Esta funcion le pasamos la array del abecedario y un caracter
     * Y nos devolvera una posicion segun si se encuentra esa letra en el abecedario
     */
    
    public int buscaLletra(char[] abecedari, char lletra) {
        for (int i = 0; i < abecedari.length; i++) { 
            char caracter = abecedari[i];
            if (lletra == caracter) {
                return i; 
            }
        }
        return -1;
    }
}