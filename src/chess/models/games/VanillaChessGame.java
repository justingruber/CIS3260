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
    private User playerWhite;
    private User playerBlack;
    
    public VanillaChessGame () {
        this.rules = new VanillaChessRules ();
        this.rules.createBoard (Board.BoardTypes.VANILLA_CHESS_BOARD);
    }
    
    public boolean tryMove (int curX, int curY, int newX, int newY) {
        if (this.rules.tryMove (curX, curY, newX, newY)) {
            this.currentMover = (this.currentMover == ChessPiece.Colours.WHITE) ? ChessPiece.Colours.BLACK : ChessPiece.Colours.WHITE;
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public void addUser (User user) {
        if (playerWhite == null) {
            playerWhite = user;
        } else if (playerBlack == null) {
            playerBlack = user;
        } else {
            //spectators
        }
    }
}