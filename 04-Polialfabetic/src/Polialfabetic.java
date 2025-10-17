/*
 * Enunciat

Crea un programa en Java anomenat Polialfabètic.java que tingui un mètode
public static void permutaAlfabet(), que generi una permutació de l’alfabet complet amb accents greus, aguts, dieresi, «ç» i «ñ» i ho emmagatzemi en una variable global.

Després crea els mètodes:

public static String xifraPoliAlfa(String msg)


que xifri la cadena passada com a paràmetre amb xifratge polialfabètic.

public static String desxifraPoliAlfa(String msgXifrat)


que desxifri la cadena del paràmetre i torni una cadena desxifrada amb polialfabètic.

Si ho necessites pots fer mètodes addicionals.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Polialfabetic {

    private static long clauSecreta = 1234;
    static char[] abecedario = "aáàäbcçdeéèëfghiíìïjklmnñoóòöpqrstuúùüvwxyz".toUpperCase().toCharArray();
    static char[] alfabetoPermutado = new char[abecedario.length];
    private static Random random = null;

    public static void main(String[] args) {
        String msgs[] = { "Test 01 àrbitre, coixí, Perímetre",
                "Test 02 Taüll, DÍA, año",
                "Test 03 Peça, Òrrius, Bòliva" };
        String msgsXifrats[] = new String[msgs.length];

        System.out.println("Xifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }

        System.out.println("Desxifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msgs[i]);
        }

    }

    public static void initRandom(long clauSecreta) {
        random = new Random(clauSecreta);
    }

    /*
     * Función que le pasamos el alfabeto original
     * y nos mezcla el alfabeto
     */

    public static void permutaAlfabet(char[] alfabet) {

        // Pasamos el alfabeto que esta en una array de char a una lista
        List<Character> lista = new ArrayList<>();
        for (char c : alfabet) {
            lista.add(c);
        }

        // Mezcla la lista original
        Collections.shuffle(lista, random);

        // Una vez meclada, la pasamos de nuevo a array
        for (int i = 0; i < lista.size(); i++) {
            alfabetoPermutado[i] = lista.get(i);
        }

    }
    /*
     * Función que le pasamos la cadena
     * permuta el alfabeto cada vez cogemos una letra
     * nos busca la posicion del caracter en el alfabeto original
     * delvuelve el texto cifrado con el alfabeto permutado
     */

    public static String xifraPoliAlfa(String msg) {
        String textoCifrado = "";
        for (int i = 0; i < msg.length(); i++) {
            char caracter = msg.charAt(i);
            permutaAlfabet(abecedario);
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
     * Permuta el alfabeto cada vez que cogemos una letra
     * nos busca la posicion del caracter en el alfabeto permutado
     * delvuelve el texto cifrado con el alfabeto original
     */

    public static String desxifraPoliAlfa(String msgXifrat) {
        String textoDescifrado = "";
        for (int i = 0; i < msgXifrat.length(); i++) {
            char caracter = msgXifrat.charAt(i);
            permutaAlfabet(abecedario);
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
