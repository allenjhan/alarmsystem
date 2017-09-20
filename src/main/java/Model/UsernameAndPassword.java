package Model; /**
 * Created by ahan on 5/30/17.
 */

import java.util.regex.Pattern;

public class UsernameAndPassword {
    String username;
    String password;

    public UsernameAndPassword() {
    }

    /**
     * Getter of the username of the account
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter of the username of the account
     * @param username username of the account
     * @return returns whether the username was set successfully or not
     */
    public boolean setUsername(String username) {
        if (isValidUsernameOrPassword(username)) {
            this.username = username;
            return true;
        } else
            return false;

    }

    /**
     * Getter of the password of the account
     * @return the password of the account
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter of the password of the account
     * @param password password of the account
     * @return whether the password was set successfully or not
     */
    public boolean setPassword(String password) {
        if (isValidUsernameOrPassword(password)) {
            this.password = password;
            return true;
        }else
            return false;

    }

    /**
     * Getter for whether the username or password is valid or not
     * @param toCheck the username or password to check
     * @return
     */
    public static boolean isValidUsernameOrPassword(String toCheck){
        if(Pattern.matches("\\s", toCheck))
            return false;
        else if (toCheck.equals(""))
            return false;
        else
            return true;
    }
}
