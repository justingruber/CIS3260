/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.views.graphical;

import chess.models.*;
import chess.models.ChessPiece;
import chess.models.games.Game;
import chess.models.games.VanillaChessGame;
import java.util.ArrayList;

/**
 *
 * @author Justin
 */
public class VanillaChessGraphical extends ChessGraphical {

    public VanillaChessGraphical() {
        this.addHelpLine("legend", "legend - Shows what the characters on the board represent.");
        this.addHelpLine("settings", "settings - Shows the current settings of the game.");
    }

    public void stateChanged(int state, ArrayList<GameSetting> settings) {

        if (state == Game.STATE_LOBBY) {
            this.addHelpLine("start", "start - Starts the game.");
            this.addHelpLine("setsettings", "setsettings - Set the settings for the game.");
        } else if (state == VanillaChessGame.STATE_NORMAL) {
            this.removeHelpLine("start");
            this.removeHelpLine("setsettings");
            this.addHelpLine("move", "a1 a2 - Moves the piece from a1 to a2. Change to the piece you actually want.");

            if (settings.contains(GameSetting.DRAW_BY_AGREEMENT)) {
                this.addHelpLine("draw", "draw - Calls a draw by agreement. The other player must accept the draw.");
            }

            if (settings.contains(GameSetting.FIFTY_MOVE_RULE)) {
                this.addHelpLine("fifty", "fifty - Invokes the fifty-move rule.");
            }

        } else if (state == VanillaChessGame.STATE_GAME_OVER) {
            this.removeHelpLine("draw");
            this.removeHelpLine("fifty");
            this.removeHelpLine("move");
        }
    }

    public void displayDrawRequest(User request) {
    	//Put this in a popup
    	/*System.out.println ("==============DRAW==============");
         System.out.println ("Player " + requester.getColour () + "/" + requester.getUsername () + " requested a draw.");
         System.out.println ("[1] Yes");
         System.out.println ("[2] No");
         System.out.println ("================================");
         System.out.println ("Do you agree to a draw?");*/
    }

    public void displayPromotion() {

    	//Display a popup with player name / ID and possible promotions
        ArrayList<ChessPiece.ChessPieces> pieces = new ArrayList<>();
        pieces.add(ChessPiece.ChessPieces.BISHOP);
        pieces.add(ChessPiece.ChessPieces.ROOK);
        pieces.add(ChessPiece.ChessPieces.QUEEN);
        pieces.add(ChessPiece.ChessPieces.KNIGHT);

        for (int i = 0; i < pieces.size(); i++) {
            //System.out.println ("[" + (i + 1) + "] " + pieces.get (i));
        }
    }

    public void update(User currentMover, int state) {
        this.displayMessages();

        if (state == VanillaChessGame.STATE_NORMAL) {
            //Display the current mover "Player " + currentMover.getColour () + "/" + currentMover.getUsername () + "'s turn."	
        } else if (state == VanillaChessGame.STATE_GAME_OVER) {

        }
    }

    public void displayBoard() {
        updateBoard();
    }

    public void updateBoard() {

    }

    public void displayLegend() {
        /*System.out.println ("===============LEGEND===============");
         System.out.println ("The first letter of each pair represents the color. W is white. B is black.");
         System.out.println ("The second letter of each pair represents the type of piece.");
         System.out.println ("K - King");
         System.out.println ("Q - Queen");
         System.out.println ("B - Bishop");
         System.out.println ("N - Knight");
         System.out.println ("R - Rook");
         System.out.println ("P - Pawn");
         System.out.println ("====================================");*/
    }

}
