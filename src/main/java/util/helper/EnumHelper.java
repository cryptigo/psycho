package util.helper;

import java.util.ArrayList;
import java.util.List;

// Utility class to provide common utility methods work with enumerations.
public class EnumHelper {

    // Utility method to get the values of an enum as a List
    public static <T extends Enum<T>> List<T> getEnumValuesAsList(Class<T> enumClass) {
        T[] enumValues = enumClass.getEnumConstants();
        List<T> valuesList = new ArrayList<>();
        for (T value : enumValues) {
            valuesList.add(value);
        }
        return valuesList;
    }

    // Utility method to get the number of values in an enum
    public static <T extends Enum<T>> int getEnumSize(Class<T> enumClass) {
        return enumClass.getEnumConstants().length;
    }

    // Utility method to iterate over the values of an enum
    public static <T extends Enum<T>> void iterateEnumValues(Class<T> enumClass) {
        for (T value : enumClass.getEnumConstants()) {
            System.out.println(value);
        }
    }

    // Utility method to get the next value of an enum
    public static <T extends Enum<T>> T getNextEnumValue(T enumValue) {
        T[] enumValues = enumValue.getDeclaringClass().getEnumConstants();
        int index = (enumValue.ordinal() + 1) % enumValues.length;
        return enumValues[index];
    }

    // Utility method to get the previous value of an enum
    public static <T extends Enum<T>> T getPreviousEnumValue(T enumValue) {
        T[] enumValues = enumValue.getDeclaringClass().getEnumConstants();
        int index = (enumValue.ordinal() - 1 + enumValues.length) % enumValues.length;
        return enumValues[index];
    }

    // Utility method to get the values of an enum as an array
//    public static <T extends Enum<T>> T[] getEnumValues(Class<T> enumClass) {
//        return enumClass.getEnumConstants();
//    }

    // Utility method to check if an enum contains a specific value
    public static <T extends Enum<T>> boolean isEnumValue(Class<T> enumClass, String value) {
        for (T enumValue : enumClass.getEnumConstants()) {
            if (enumValue.name().equals(value)) {
                return true;
            }
        }
        return false;
    }

    // Utility method to get an enum value from a string
    public static <T extends Enum<T>> T getEnumValue(Class<T> enumClass, String value) {
        return Enum.valueOf(enumClass, value);
    }



}
