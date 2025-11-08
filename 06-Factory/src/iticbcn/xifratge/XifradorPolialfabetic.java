package iticbcn.xifratge;

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

public class XifradorPolialfabetic implements Xifrador {
    private static long clauSecreta = 1234;
    static char[] abecedario = "aáàäbcçdeéèëfghiíìïjklmnñoóòöpqrstuúùüvwxyz".toUpperCase().toCharArray();
    static char[] alfabetoPermutado = new char[abecedario.length];
    private static Random random = null;

        @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada{
        try {
            String msgXifrat = xifraPoliAlfa(msg, clau);
            return new TextXifrat(msgXifrat.getBytes());
            
        } catch (Exception e) {
            throw new ClauNoSuportada(e.getMessage());
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada{
        
        if (xifrat == null) {
            System.out.println("La clau de Polialfabètic ha de ser un String convertible a long");
            return null;
        }
        try {
            return desxifraPoliAlfa(new String(xifrat.getBytes()), clau);
        } catch (Exception e) {
            throw new ClauNoSuportada(e.getMessage());
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

    public String xifraPoliAlfa(String msg, String clau) throws Exception {
        long clauL;
        try {
            clauL = Long.parseLong(clau);
        }
        catch (NumberFormatException e){
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        }
        initRandom(clauL);
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

    public String desxifraPoliAlfa(String msgXifrat, String clau) throws Exception {
        long clauL;
        try {
            clauL = Long.parseLong(clau);
        }
        catch (NumberFormatException e){
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        }
        initRandom(clauL);
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
    public int buscaLletra(char[] abecedari, char letra) {
        for (int i = 0; i < abecedari.length; i++) {
            char caracter = abecedari[i];
            if (letra == caracter) {
                return i;
            }
        }
        return -1;
    }
}
