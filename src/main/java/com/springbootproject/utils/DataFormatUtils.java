package com.springbootproject.utils;

import com.springbootproject.exception.InvalidInputDataException;

public class DataFormatUtils {

    /**
     * Method to convert string representation of the courseDtoId number into integer.
     * If string does not represent a valid number, InvalidInputDataException is thrown.
     * @param stringId - string value of the courseDtoId number
     * @return - integer value of the courseDtoId number
     *
     */
    public static int parseStringIdNumber(String stringId) {
        try {
            return Integer.parseInt(stringId);
        } catch (NumberFormatException exception) {
            throw new InvalidInputDataException("'Id' should be a valid number.");
        }

    }
}
