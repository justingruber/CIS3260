/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chess.models.games;

import chess.models.Board;
import chess.models.ChessPiece;
import chess.models.User;
import chess.models.VanillaChessRules;

/**
 *
 * @author Benjin
 */
public class VanillaChessGame extends Game {
    private State state;
    private User playerWhite;
    private User playerBlack;
    
    public VanillaChessGame () {
        state = State.NORMAL;
        this.setRules (new VanillaChessRules ());
        this.getRules ().createBoard (Board.BoardTypes.VANILLA_CHESS_BOARD);
    }
    
    public boolean tryMove (int curX, int curY, int newX, int newY) {
        if (this.getRules ().tryMove (this.getCurrentMover ().getColour (), curX, curY, newX, newY)) {
            this.setCurrentMover ((this.getCurrentMover () == playerWhite) ? playerBlack : playerWhite);
            
            if (((VanillaChessRules) this.getRules ()).isGameOver ()) {
                state = State.GAME_OVER;
            }
            
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public void addUser (User user) {
        if (playerWhite == null) {
            playerWhite = user;
            playerWhite.setColour (ChessPiece.Colours.WHITE);
            this.setCurrentMover (playerWhite);
        } else if (playerBlack == null) {
            playerBlack = user;
            playerBlack.setColour (ChessPiece.Colours.BLACK);
            //this.setCurrentMover (playerBlack);
        } else {
            //spectators
        }
    }
    
    public State getState () {
        return state;
    }
    
    public enum State { NORMAL, GAME_OVER };
}
