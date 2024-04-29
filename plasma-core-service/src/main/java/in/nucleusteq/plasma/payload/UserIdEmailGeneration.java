package in.nucleusteq.plasma.payload;

import java.util.Random;

import in.nucleusteq.plasma.constants.UserConstants;
/**
 * Utility class for generating user IDs, emails, and passwords.
 */
public class UserIdEmailGeneration {
    /**
     * Generates a new user ID based on the last user ID provided.
     * @param lastUserId The last user ID generated
     * @return A new user ID
     */
    public static String generateUserId(String lastUserId) {
        int lastUserIdNumber = 0;
        if (lastUserId != null && !lastUserId.isEmpty()) {
            lastUserIdNumber = Integer
                    .parseInt(lastUserId.replaceAll("[^0-9]", ""));
        }
        int newUserIdNumber = lastUserIdNumber + 1;
        return "N" + String.format("%04d", newUserIdNumber);
    }
    /**
     * Generates a new email address based on the provided first name and last name.
     * @param firstName The first name of the user
     * @param lastName  The last name of the user
     * @return A new email address
     */
    public static String generateEmail(String firstName, String lastName) {
        return firstName.toLowerCase() + "." + lastName.toLowerCase()
                + UserConstants.EMAIL_DOMAIN;
    }
    /**
     * Generates a new random password.
     * @return A new random password
     */
    public static String generatePassword() {
        StringBuilder password = new StringBuilder();
        password.append(UserConstants.UPPERCASE_CHARS
                .charAt(new Random().nextInt(UserConstants.UPPERCASE_CHARS.length())));
        password.append(UserConstants.SPECIAL_CHARS
                .charAt(new Random().nextInt(UserConstants.SPECIAL_CHARS.length())));
        password.append(UserConstants.NUMERIC_CHARS
                .charAt(new Random().nextInt(UserConstants.NUMERIC_CHARS.length())));
        for (int i = 0; i < 5; i++) {
            String allChars = UserConstants.UPPERCASE_CHARS + UserConstants.LOWERCASE_CHARS
                    + UserConstants.NUMERIC_CHARS + UserConstants.SPECIAL_CHARS;
            password.append(
                    allChars.charAt(new Random().nextInt(allChars.length())));
        }
        return password.toString();
    }
    /**
     * Generates a new user ID based on the last user ID provided.
     * @param lastVendorId The last user ID generated
     * @return A new user ID
     */
    public static String generateVendorId(String lastVendorId) {
        int lastVendorIdNumber = 0;
        if (lastVendorId != null && !lastVendorId.isEmpty()) {
            lastVendorIdNumber = Integer
                    .parseInt(lastVendorId.replaceAll("[^0-9]", ""));
        }
        int newUserIdNumber = lastVendorIdNumber + 1;
        return "V" + String.format("%04d", newUserIdNumber);
    }
}
