package org.rak.transaction.util;

import lombok.experimental.UtilityClass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class Utility {
    public static String detectCreditCardType(String cardNumber) {
        String visaRegex = "^4[0-9]{6,}$";
        String mastercardRegex = "^5[1-5][0-9]{5,}|222[1-9][0-9]{3,}|22[3-9][0-9]{4,}|2[3-6][0-9]{5,}|27[01][0-9]{4,}|2720[0-9]{3,}$";
        String amexRegex = "^3[47][0-9]{5,}$";
        String dinersRegex = "^3(?:0[0-5]|[68][0-9])[0-9]{4,}$";

        Pattern visaPattern = Pattern.compile(visaRegex);
        Matcher visaMatcher = visaPattern.matcher(cardNumber);

        Pattern mastercardPattern = Pattern.compile(mastercardRegex);
        Matcher mastercardMatcher = mastercardPattern.matcher(cardNumber);

        Pattern amexPattern = Pattern.compile(amexRegex);
        Matcher amexMatcher = amexPattern.matcher(cardNumber);

        Pattern dinersPattern = Pattern.compile(dinersRegex);
        Matcher dinersMatcher = dinersPattern.matcher(cardNumber);

        if (visaMatcher.matches()) {
            return "Visa";
        } else if (mastercardMatcher.matches()) {
            return "Mastercard";
        } else if (amexMatcher.matches()) {
            return "American Express";
        } else if (dinersMatcher.matches()) {
            return "Diners Club";
        } else {
            return "Unknown";
        }
    }

    public static String removeSpaces(String cardNumber) {
        return cardNumber.replaceAll("\\s", "");
    }
}
