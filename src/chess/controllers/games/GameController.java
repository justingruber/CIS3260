/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chess.controllers.games;

import java.util.Observable;
import chess.models.games.Game;
import chess.views.terminals.ChessTerminal;
/**
 *
 * @author Benjin
 */
public abstract class GameController extends Observable {
    public abstract void start ();
    
    public static final int QUIT = 0;
}
