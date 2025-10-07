/*
 * Enunciat
Crea un programa en Java anomenat Monoalfabetic.java que tingui un mètode
permutaAlfabet(alfabet), que generi una permutació de l’alfabet complet amb accents greus,
aguts, dièresi, «ç» i «ñ» i la retorni en un array de char.
Després crea els mètodes:
xifraMonoAlfa(cadena) que xifre la cadena passada com a paràmetre amb xifratge monoalfabètic
utilitzant la permutació generada inicialment
desxifraMonoAlfa(cadena) que desxifre la cadena del paràmetre i torni una cadena dexifrada amb
monoalfabètic.
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Monoalfabetic {
    static char[] abecedario = "aáàäbcçdeéèëfghiíìïjklmnñoóòöpqrstuúùüvwxyz".toUpperCase().toCharArray();
    static char[] alfabetoPermutado = permutaAlfabet(abecedario);

    public static void main(String[] args) {

        String[] pruebas = { "ABC", "XYZ", "Hola, Mr. calçot",
                "Perdó, per tu què és?" };

        System.out.println("------- CIFRADO ---------");
        for (int i = 0; i < pruebas.length; i++) {
            String cadena = pruebas[i];
            String cadenaCifrada = xifraMonoAlfa(cadena);
            System.out.println("(" + i + ") " + cadena + " --> " + cadenaCifrada);
        }
        System.out.println("------ DESCRIFRADO --------");
        for (int i = 0; i < pruebas.length; i++) {
            String cadena = pruebas[i];
            String cadenaCifrada = xifraMonoAlfa(cadena);
            String cadenaDescifrada = desxifrarMonoAlfa(cadenaCifrada);
            System.out.println("(" + i + ") " + cadenaCifrada + " --> " + cadenaDescifrada);
        }
    }
    /*
     * Función que le pasamos el alfabeto original 
     * y devuelve el alfabeto mezclado
     */

    public static char[] permutaAlfabet(char[] alfabet) {

        //Pasamos el alfabeto que esta en una array de char a una lista
        List<Character> lista = new ArrayList<>();
        for (char c : alfabet) {
            lista.add(c);
        }

        // Mezcla la lista original
        Collections.shuffle(lista);

        // Una vez meclada, la pasamos de nuevo a array
        char[] alfabetoPermutado = new char[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            alfabetoPermutado[i] = lista.get(i);
        }

        return alfabetoPermutado;
    }

    /*
     * Función que le pasamos la cadena
     * nos busca la posicion del caracter en el alfabeto original
     * delvuelve el texto cifrado con el alfabeto permutado
     */
    public static String xifraMonoAlfa(String cadena) {
        String textoCifrado = "";
        for (int i = 0; i < cadena.length(); i++) {
            char caracter = cadena.charAt(i);
            boolean esMinuscula = Character.isLowerCase(caracter);
            char letraMajuscula = Character.toUpperCase(caracter);
            int posicion = buscaLletra(abecedario, letraMajuscula);
            if (posicion != -1) {
                if (esMinuscula) {
                    textoCifrado += Character.toLowerCase(alfabetoPermutado[posicion]);
                } else {
                    textoCifrado += alfabetoPermutado[posicion];
                }
            } else {
                textoCifrado += caracter;
            }
        }
        return textoCifrado;
    }

    /*
     * Función que le pasamos la cadena
     * nos busca la posicion del caracter en el alfabeto permutado
     * delvuelve el texto cifrado con el alfabeto original
     */
    public static String desxifrarMonoAlfa(String cadena) {
        String textoDescifrado = "";
        for (int i = 0; i < cadena.length(); i++) {
            char caracter = cadena.charAt(i);
            boolean esMinuscula = Character.isLowerCase(caracter);
            char letraMajuscula = Character.toUpperCase(caracter);
            int posicion = buscaLletra(alfabetoPermutado, letraMajuscula);
            if (posicion != -1) {
                if (esMinuscula) {
                    textoDescifrado += Character.toLowerCase(abecedario[posicion]);
                } else {
                    textoDescifrado += abecedario[posicion];
                }
            } else {
                textoDescifrado += caracter;
            }
        }
        return textoDescifrado;
    }

    /*
     * Esta funcion le pasamos la array del abecedario y un caracter
     * Y nos devolvera una posicion segun si se encuentra esa letra en el abecedario
     */
    public static int buscaLletra(char[] abecedari, char letra) {
        for (int i = 0; i < abecedari.length; i++) {
            char caracter = abecedari[i];
            if (letra == caracter) {
                return i;
            }
        }
        return -1;
    }
}