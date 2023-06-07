package util.helper;

// Utility class that provides additional functionality for working with strings.
public class StringHelper {

    // Utility method to check if a string is null or empty.
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    // Utility method to check if a string is null, empty, or consists of only whitespace.
    public static boolean isNullOrWhitespace(String str) {
        return str == null || str.trim().isEmpty();
    }

    // Utility method to reverse a string
    public static String reverseString(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder reversed = new StringBuilder(str);
        return reversed.reverse().toString();
    }

    // Utility method to cound the occurrences of a character in a string
    public static int countCharOccurrences(String str, char ch) {
        int count = 0;
        for (int i=0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                count++;
            }
        }
        return count;
    }

    // Utility method to capitalize the first letter of a string
    public static String capitalizeFirstLetter(String str) {
        if (isNullOrWhitespace(str)) {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    // Utility method to convert a string to title case (capitalize first letter of each word)
    public static String toTitleCase(String str) {
        if (isNullOrWhitespace(str)) {
            return str;
        }

        StringBuilder result = new StringBuilder(str.length());
        String[] words = str.split("\\s");
        for (String word : words) {
            if (!isNullOrWhitespace(word)) {
                result.append(capitalizeFirstLetter(word)).append(" ");
            }
        }
        return result.toString();
    }

    // Utility method to get line ending from string.
    public static String getStringLineEnding(String text) {
        if (text.contains("\r\n")) {
            return "CRLF";
        } else if(text.contains("\n")) {
            return "LF";
        } else if (text.contains("\r")) {
            return "CR";
        } else if (text.contains("\u001E")) {
            return "RS";
        } else {
            return null;
        }
    }

}
