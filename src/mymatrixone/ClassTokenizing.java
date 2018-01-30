/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mymatrixone;

public class ClassTokenizing {

    // untuk english
    /*public static final String ALPHABET =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "abcdefghijklmnopqrstuvwxyz" + " " + "'" + "-"; */ 
    
     public static final String ALPHABET =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "abcdefghijklmnopqrstuvwxyz" + " ";

    public static boolean isAlphabetic(char c) {
        return ClassTokenizing.ALPHABET.indexOf(c) != -1;
    }

    public static String tokenizing_go(String s) {
        StringBuffer buf = new StringBuffer();
        String cek = "'";
        for (int i = 0; i < s.length(); i++) {
            if (ClassTokenizing.isAlphabetic(s.charAt(i))) {
                //menghandle seperti kata : book's NLM's
                if ((i + 1) < s.length()) {
                    if (s.charAt(i) == cek.charAt(0) && s.charAt(i + 1) == 's') {
                        buf.append(" is");
                        i++;
                    } else {
                        buf.append(s.charAt(i));
                    }
                } else {
                    buf.append(s.charAt(i));
                }
            } else {
                buf.append(' ');
            }
        }
        //remove space dikiri dan kanan
        String hasilTokenizing = RemoveSpaceInaString.lrtrim(buf.toString());

        //remove multiple space ditengah menjadi space tunggal
        hasilTokenizing = RemoveSpaceInaString.itrim(hasilTokenizing);
        return hasilTokenizing.toLowerCase();
    }   

    public static void main(String[] args) {
        //String in = "The big fat cat, said 'your funniest guy i know' to the kangaroo...?";
        String in = 
                "Dalam pelaksanaan PK2mu  -  dan pk2maba, "
                + "panitia tidak memberikan kesempatan bagi "
                + "maba yang beragama islam untuk menjalankan "
                + "ibadah sholat asar";
        System.out.println("Hasil Tokenizing  : "+ClassTokenizing.tokenizing_go(in));
    }
}

