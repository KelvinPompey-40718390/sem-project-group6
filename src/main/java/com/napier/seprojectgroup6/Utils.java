package com.napier.seprojectgroup6;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * Utility methods for reading input
 * @author Kelvin Pompey
 */
public final class Utils {

    private Utils () {

    }

    /**
     * Helper method for reading user input
     * @param prompt The message displayed to the user
     * @return The message returned to the user
     */
    public static String readInput(String prompt) {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));


            System.out.println(prompt);
            return reader.readLine();
        }
        catch (Exception e) {
            System.out.println("Error getting input");
        }
        return "";
    }
}
