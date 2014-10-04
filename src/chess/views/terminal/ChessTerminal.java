/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chess.views.terminal;

import chess.views.GameView;
import java.util.Scanner;

/**
 *
 * @author Benjin
 */
public abstract class ChessTerminal extends GameView {
    
    public String readLine () {
        Scanner scan = new Scanner (System.in);
        return scan.nextLine ();
    }
    
    public abstract void update ();
}
