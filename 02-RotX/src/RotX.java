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
public class RotX {

    // Array de las letras del abecedario en minusculas
    static char[] arrayAbecedari = "aáàäbcçdeéèëfghiíìïjklmnñoóòöpqrstuúùüvwxyz".toCharArray();
    // Array de las letras del abecedario en mayusculas
    static char[] arrayAbecedariM = "aáàäbcçdeéèëfghiíìïjklmnñoóòöpqrstuúùüvwxyz".toUpperCase().toCharArray();

    public static void main(String[] args) {
        // Array de strings con las pruebas
        String[] proves = { "ABC", "XYZ", "Hola, Mr. calçot",
                "Perdó, per tu què és?" };

        System.out.println("------- CIFRADO ---------");
        for (int i = 0; i < proves.length; i++) { // recorremos la array de pruebas de string
            String cadena = proves[i]; // cogemos la string que queremos tratar
            String cadenaCifrada = xifraRotX(cadena, i * 2); // ciframos la cadena
            System.out.println("(" + i * 2 + ")-" + cadena + " --> " + cadenaCifrada); // printeamos el resultado por
                                                                                       // pantalla
        }
        System.out.println("------ DESCRIFRADO --------");
        for (int i = 0; i < proves.length; i++) { // recorremos la array de pruebas de string
            String cadena = proves[i]; // cogemos la string que queremos tratar
            String cadenaCifrada = xifraRotX(cadena, i * 2); // ciframos la cadena
            String cadenaDescifrada = desxifraRotX(cadenaCifrada, i * 2); // descriframos la cadena que tenemos cifrada
            System.out.println("(" + i * 2 + ")-" + cadenaCifrada + " --> " + cadenaDescifrada); // printeamos el
                                                                                                 // resultado por
                                                                                                 // pantalla
            if (i == proves.length - 1) {
                forcaBrutaRotX(cadenaCifrada);
            }
        }
    }

    /*
     * Esta funcion le pasamos por parametro una cadena
     * y nos devolvera el texto cifrado
     */
    public static String xifraRotX(String cadena, int desplaçament) {
        String textoCifrado = ""; // String para guardar el cifrado
        for (int i = 0; i < cadena.length(); i++) { // Recorremos la cadena
            char caracter = cadena.charAt(i); // cogemos el caracter que queremos tratar
            // llamamos al metodo que nos busca la posicion del caracter en el abecedario de
            // minusculas
            int posicio = buscaLletra(arrayAbecedari, caracter);
            if (posicio != -1) { // si la posicion no es -1, es decir que se encuentra en el abecedario
                // cogemos la nueva posicion sumandole 13 y hacemos el modulo para no pasarnos
                // de posicion
                int posicionNueva = (posicio + desplaçament) % arrayAbecedari.length;
                // busca esa posicion en el abecedario y añade el caracter de esa posicion en la
                // string
                textoCifrado += arrayAbecedari[posicionNueva];
                // si no se ha encontrado en el abecedario de minusculas haremos el mismo
                // proceso en el abecedario de mayusculas
            } else {
                posicio = buscaLletra(arrayAbecedariM, caracter);
                if (posicio != -1) {
                    int posicionNueva = (posicio + desplaçament) % arrayAbecedariM.length;
                    textoCifrado += arrayAbecedariM[posicionNueva];
                    // Si no se ha encontrado en ninguno de los dos abecedarios, añadiremos el
                    // caracter tal cual en caso de espacios, comas...
                } else {
                    textoCifrado += caracter;
                }
            }
        }
        return textoCifrado; // devuelve la cadena cifrada
    }

    /*
     * Esta funcion le pasamos por parametro una cadena
     * y nos devolvera el texto descrifrado
     */
    public static String desxifraRotX(String cadena, int desplaçament) {
        String textoDescrifrado = ""; // String para guardar el cifrado
        for (int i = 0; i < cadena.length(); i++) { // Recorremos la cadena
            char caracter = cadena.charAt(i); // cogemos el caracter que queremos tratar
            // llamamos al metodo que nos busca la posicion del caracter en el abecedario de
            // minusculas
            int posicio = buscaLletra(arrayAbecedari, caracter);
            if (posicio != -1) { // si la posicion no es -1, es decir que se encuentra en el abecedario
                // Cogemos la nueva posicion restandole 13 para descrifrar y sumandole la
                // longitud del abecedario para que no se quede en negativo
                // Hacemos el modulo por si la suma de la longitud se pasa de la longitud de la
                // array del abecedario
                int posicionNueva = (posicio - desplaçament + arrayAbecedari.length) % arrayAbecedari.length;
                // busca esa posicion en el abecedario y añade el caracter de esa posicion en la
                // string
                textoDescrifrado += arrayAbecedari[posicionNueva];
                // si no se ha encontrado en el abecedario de minusculas haremos el mismo
                // proceso en el abecedario de mayusculas
            } else {
                posicio = buscaLletra(arrayAbecedariM, caracter);
                if (posicio != -1) {
                    int posicionNueva = (posicio - desplaçament + arrayAbecedari.length) % arrayAbecedariM.length;
                    textoDescrifrado += arrayAbecedariM[posicionNueva];
                    // Si no se ha encontrado en ninguno de los dos abecedarios, añadiremos el
                    // caracter tal cual en caso de espacios, comas...
                } else {
                    textoDescrifrado += caracter;
                }

            }

        }
        return textoDescrifrado; // devuelve la cadena descrifrada
    }

    public static void forcaBrutaRotX(String cadenaCifrada) {
        System.out.println("\nMissatge xifrat: " + cadenaCifrada + "\n");
        String textoDescifrado = "";
        for (int i = 0; i < arrayAbecedari.length; i++) {
            System.out.println("(" + i + ") " + desxifraRotX(cadenaCifrada, i));
        }
    }

    /*
     * Esta funcion le pasamos la array del abecedario y un caracter
     * Y nos devolvera una posicion segun si se encuentra esa letra en el abecedario
     */
    
    public static int buscaLletra(char[] abecedari, char lletra) {
        for (int i = 0; i < abecedari.length; i++) { // recorremos el abecedario
            char caracter = abecedari[i]; // Cogemos el caracter del abecedario
            if (lletra == caracter) { // Si la letra de nuestra cadena coincide con la del abecedario
                return i; // devolvemos la posicion en la que esta
            }
        }
        return -1; // si no se encuentra devolveremos -1
    }
}