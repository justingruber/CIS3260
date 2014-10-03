/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chess.models;

/**
 *
 * @author Benjin
 */
public class UserManager {
    private static User currentUser;
    
    public static void setCurrentUser (String username) {
        currentUser = new User ();
        currentUser.userName = username;
    }
    
    public static User getCurrentUser () {
        return currentUser;
    }
}
