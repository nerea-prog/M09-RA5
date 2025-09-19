/*
 * Enunciat
Crea una classe Java anomenada Rot13.java que contingui les següents funcions:
xifraRot13( cadena ): Ha de substituir cada lletra (no els espais ni els signes de puntuació) per la
lletra que està 13 posicions més a la dreta en l’abecedari (si sobrepassa ha de tornar a començar).
desxifraRot13( cadena ): Ha de fer el procés invers de la funció anterior.
Crea també un main per fer algunes proves.
 */
public class Rot13 {
    static char[] arrayAbecedari = "aáàäbcçdeéèëfghiíìïjklmnñoóòöpqrstuúùüvwxyz".toCharArray();
    static char[] arrayAbecedariM = "AÁÀÄBCÇDEÉÈËFGHIÍÌÏJKLMNÑOÓÒÖPQRSTUÚÙÜVWXYZ".toCharArray();
    public static void main(String[] args) {

        String[] proves = {"ABC", "XYZ", "Hola, Mr. calçot", 
                            "Perdó, per tu què és?"};
        String[] provesD = {"IÏJ", "FGH", "Òwúi, Ùá. jiúkwb", 
                            "Zmálx, zmá bc acñ nà?"};

        
        for (int i = 0; i < proves.length; i++) {
            String cadena = proves[i];
            String cadenaCifrada = xifraRot13(cadena);   
        }
        for (int i = 0; i < provesD.length; i++) {
            String cadena = provesD[i];
            String cadenaDescifrada = xifraRot13(cadena);   
        }

    }
    public static String xifraRot13(String cadena){
        String textoCodificado = "";
        for (int i = 0; i < cadena.length(); i++) {
            char caracter = cadena.charAt(i);
            int posicio = buscaLletra(arrayAbecedari, caracter);   
        }
        return textoCodificado;
    }
    public String desxifraRot13(String cadena){
        String textoDescifrado = "";
        return textoDescifrado;
    }
    public static int buscaLletra(char[] abecedari, char lletra){
        for (int i = 0; i < abecedari.length; i++) {
            char caracter = abecedari[i];
            if (lletra == caracter) {
                return i;
            } else {
                return -1;
            }
        }
        return 0;
    }
}
