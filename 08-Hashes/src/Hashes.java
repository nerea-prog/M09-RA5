import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HexFormat;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {

    private int npass = 0;
    private final String[] algorismes = {"SHA-512", "PBKDF2WithHmacSHA512"};
    
    // Metodo hash
    public String getSHA512AmbSalt(String pw, String salt) throws Exception{
        MessageDigest messageDigest = MessageDigest.getInstance(algorismes[0]); // Implementa algorisme
        messageDigest.update(salt.getBytes(StandardCharsets.UTF_8)); //añade información a la calculadora hash
        byte bytes[] = messageDigest.digest(pw.getBytes(StandardCharsets.UTF_8)); // finaliza el proceso hash
        HexFormat hex = HexFormat.of(); 
        return hex.formatHex(bytes); // convierte la cadena bytes[] en string
    }

    // Metodo hash
    public String getPBKDF2AmbSalt(String pw, String salt) throws Exception{
        int iteraciones = 1000;
        int longContraseña = 256;
        PBEKeySpec PbeKeySpec = new PBEKeySpec(pw.toCharArray(), salt.getBytes(), iteraciones, longContraseña);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(algorismes[1]);
        byte[] bytes = secretKeyFactory.generateSecret(PbeKeySpec).getEncoded();
        HexFormat hex = HexFormat.of();
        return hex.formatHex(bytes);
    }


    public String forcaBruta(String alg, String hash, String salt) throws Exception{
        String charset = "abcdefABCDEF1234567890!";
        char aRw[] = new char[6];
        String pw = null;
        //1
        for (int i = 0; i < charset.length(); i++) {
            if ((pw = pwTrobat(alg, aRw, 0, charset.charAt(i), hash, salt)) != null) {
                return pw;
            }
             for (int i2 = 0; i2 < charset.length(); i2++) {
                if ((pw = pwTrobat(alg, aRw, 1, charset.charAt(i2), hash, salt)) != null) {
                    return pw;
                }
                for (int i3 = 0; i3 < charset.length(); i3++) {
                    if ((pw = pwTrobat(alg, aRw, 2, charset.charAt(i3), hash, salt)) != null) {
                        return pw;
                    }
                    for (int i4 = 0; i4 < charset.length(); i4++) {
                        if ((pw = pwTrobat(alg, aRw, 3, charset.charAt(i4), hash, salt)) != null) {
                            return pw;
                        }
                        for (int i5 = 0; i5 < charset.length(); i5++) {
                            if ((pw = pwTrobat(alg, aRw, 4, charset.charAt(i5), hash, salt)) != null) {
                                return pw;
                            }
                            for (int i6 = 0; i6 < charset.length(); i6++) {
                                if ((pw = pwTrobat(alg, aRw, 5, charset.charAt(i6), hash, salt)) != null) {
                                    return pw;
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
                                                
                                                    

    public String pwTrobat(String alg, char[] aRw, int i, char caracter, String hash, String salt) throws Exception {
        aRw[i] = caracter;
        String contraseñaActual = new String(aRw);
        npass++;
        String hashed = null;

        if (alg.equals(algorismes[0])){
            hashed = getSHA512AmbSalt(contraseñaActual, salt);
        } else if (alg.equals(algorismes[1])){
            hashed = getPBKDF2AmbSalt(contraseñaActual, salt);
        } else{
            return null;
        }

        if (hashed.equals(hash)) {
            return contraseñaActual;
        }
        return null;
    }

    public String getInterval(long t1, long t2){
        long diff = t2 - t1;

        long dies = diff / 86_400_000;
        diff %= 86_400_000;

        long hores = diff / 3600000;
        diff %= 3600000;

        long minuts = diff / 60000;
        diff %= 60000;

        long segons = diff / 1000;
        long milis = diff % 1000;

        return String.format("temps: %d dies / %d hores / %d minuts / %d segons / %d milis", 
            dies, hores, minuts, segons, milis);
    }
    public static void main(String[] args) throws Exception {
        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";
        Hashes h = new Hashes();
        String[] aHashes = { h.getSHA512AmbSalt(pw, salt), h.getPBKDF2AmbSalt(pw, salt)};
        String pwTrobat = null;
        String[] algorismes ={"SHA-512", "PBKDF2WithHmacSHA512"};
        for (int i = 0; i < aHashes.length; i++) {
            System.out.printf("===========================\n");
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            System.out.printf("Hash: %s\n", aHashes[i]);
            System.out.printf("---------------------------\n");
            System.out.printf("-- Inici de força bruta ---\n");

            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();

            System.out.printf("Pass  : %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps  : %s\n", h.getInterval(t1, t2));
            System.out.printf("---------------------------\n\n");
        }
    }
}
