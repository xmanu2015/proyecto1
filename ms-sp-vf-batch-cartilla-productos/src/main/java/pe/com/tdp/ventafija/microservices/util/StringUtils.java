package pe.com.tdp.ventafija.microservices.util;


public class StringUtils {

    /**
     * null save trim
     * @return trimmed string, will return a null object if input is null
     */
    public static String trim (String s) {
        return s == null ? s : s.trim();
    }

    public static String emptyWhenNull (String s) {
        return s == null ? "" : s;
    }

    public static boolean isEmptyString (String s) {
        return s == null || s.trim().length() == 0;
    }

    /**
     * Counts occurrences of a string in other
     * @param str to search on
     * @param findStr to be searched
     * @return the number of occurrances
     */
    public static int countOccurrences (String str, String findStr) {
        int lastIndex = 0;
        int count = 0;

        while(lastIndex != -1){

            lastIndex = str.indexOf(findStr,lastIndex);

            if(lastIndex != -1){
                count ++;
                lastIndex += findStr.length();
            }
        }
        return count;
    }
}
