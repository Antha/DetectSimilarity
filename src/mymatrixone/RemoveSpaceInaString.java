/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mymatrixone;

public class RemoveSpaceInaString {

    private RemoveSpaceInaString () {}

    /* remove leading whitespace */
    public static String ltrim(String source) {
        return source.replaceAll("^\\s+", "");
    }

    /* remove trailing whitespace */
    public static String rtrim(String source) {
        return source.replaceAll("\\s+$", "");
    }

    public static String rtrimExt(String source) {
        return source.replaceAll(".txt+$", "");
    }

    /* replace multiple whitespaces between words with single blank */
    public static String itrim(String source) {
        return source.replaceAll("\\b\\s{2,}\\b", " ");
    }

    /* remove all superfluous whitespaces in source string */
    public static String trim(String source) {
        return itrim(ltrim(rtrim(source)));
    }

    public static String lrtrim(String source){
        return ltrim(rtrim(source));
    }

    public static void main(String[] args){
        String oldStr =
         "------[1-2-1-2-1-2-1-2-1-2-1-----2-1-2-1---2-1-2-1-2-1-2-1-2]----";
        String newStr = oldStr.replaceAll("-", " ");
        System.out.println(newStr);
        System.out.println("*************************************************");
        System.out.println("*" + RemoveSpaceInaString.ltrim(newStr) + "*");
        System.out.println("*" + RemoveSpaceInaString.rtrim(newStr) + "*");
        System.out.println("*" + RemoveSpaceInaString.itrim(newStr) + "*");
        System.out.println("*" + RemoveSpaceInaString.lrtrim(newStr) + "*");
        System.out.println("*" + RemoveSpaceInaString.trim(newStr) + "*");

       
    }
}