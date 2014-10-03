/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chess.models;


import chess.models.Rules;
import chess.models.VanillaChessRules;
import chess.models.Board;
/**
 *
 * @author Benjin
 */
public class Game {
    private Rules rules;
    private Board board;
    private User playerWhite;
    private User playerBlack;
    
    public Game (Rules.RuleTypes type) {
        if (type == Rules.RuleTypes.VANILLA_CHESS_RULES) {
            rules = new VanillaChessRules ();
            rules.createBoard (Board.BoardTypes.VANILLA_CHESS_BOARD);
            
        }
    }
    
    public boolean tryMove (int curX, int curY, int newX, int newY) {
        return true;
    }
    
    public void addUser (User user) {
        if (playerWhite == null) {
            playerWhite = user;
        } else if (playerBlack == null) {
            playerBlack = user;
        }
    }
    
    public void getSpectators (){
        //
    }
    
    public Board getBoard () {
        return rules.getBoardInstance ();
    }
}
