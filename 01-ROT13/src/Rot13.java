/*
 * Enunciat
Crea una classe Java anomenada Rot13.java que contingui les següents funcions:
xifraRot13( cadena ): Ha de substituir cada lletra (no els espais ni els signes de puntuació) per la
lletra que està 13 posicions més a la dreta en l’abecedari (si sobrepassa ha de tornar a començar).
desxifraRot13( cadena ): Ha de fer el procés invers de la funció anterior.
Crea també un main per fer algunes proves.
 */
public class Rot13 {

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
            String cadenaCifrada = xifraRot13(cadena); // ciframos la cadena
            System.out.println(cadena + " --> " + cadenaCifrada); // printeamos el resultado por pantalla
        }
        System.out.println("------ DESCRIFRADO --------");
        for (int i = 0; i < proves.length; i++) { // recorremos la array de pruebas de string
            String cadena = proves[i]; // cogemos la string que queremos tratar
            String cadenaCifrada = xifraRot13(cadena); // ciframos la cadena
            String cadenaDescifrada = desxifraRot13(cadenaCifrada); // descriframos la cadena que tenemos cifrada
            System.out.println(cadenaCifrada + " --> " + cadenaDescifrada); // printeamos el resultado por pantalla
        }
    }

    /*
     * Esta funcion le pasamos por parametro una cadena
     * y nos devolvera el texto cifrado
     */
    public static String xifraRot13(String cadena) {
        String textoCifrado = ""; // String para guardar el cifrado
        for (int i = 0; i < cadena.length(); i++) { // Recorremos la cadena
            char caracter = cadena.charAt(i); // cogemos el caracter que queremos tratar
            // llamamos al metodo que nos busca la posicion del caracter en el abecedario de
            // minusculas
            int posicio = buscaLletra(arrayAbecedari, caracter);
            if (posicio != -1) { // si la posicion no es -1, es decir que se encuentra en el abecedario
                // cogemos la nueva posicion sumandole 13 y hacemos el modulo para no pasarnos
                // de posicion
                int posicionNueva = (posicio + 13) % arrayAbecedari.length;
                // busca esa posicion en el abecedario y añade el caracter de esa posicion en la
                // string
                textoCifrado += arrayAbecedari[posicionNueva];
                // si no se ha encontrado en el abecedario de minusculas haremos el mismo
                // proceso en el abecedario de mayusculas
            } else {
                posicio = buscaLletra(arrayAbecedariM, caracter);
                if (posicio != -1) {
                    int posicionNueva = (posicio + 13) % arrayAbecedariM.length;
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
    public static String desxifraRot13(String cadena) {
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
                int posicionNueva = (posicio - 13 + arrayAbecedari.length) % arrayAbecedari.length;
                // busca esa posicion en el abecedario y añade el caracter de esa posicion en la
                // string
                textoDescrifrado += arrayAbecedari[posicionNueva];
                // si no se ha encontrado en el abecedario de minusculas haremos el mismo
                // proceso en el abecedario de mayusculas
            } else {
                posicio = buscaLletra(arrayAbecedariM, caracter);
                if (posicio != -1) {
                    int posicionNueva = (posicio - 13 + arrayAbecedari.length) % arrayAbecedariM.length;
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
