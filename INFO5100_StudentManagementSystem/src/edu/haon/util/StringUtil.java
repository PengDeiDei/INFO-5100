package edu.haon.util;

public class StringUtil {
    /*
     * @brief: function to check whether the input String
     * is empty or not
     *
     * @param: input String
     *
     * @return: true if input String is empty;
     *      otherwise, false
     */
    public static boolean isEmpty(String input){
        if("".equals(input) || input == null){
            return true;
        }
        return false;
    }
}
