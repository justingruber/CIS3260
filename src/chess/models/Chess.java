package chess.models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import chess.models.messages.Message;

public class Chess {
    
   public static void drawBoard(Board board){
        char letter = 'a';
        int count = 0;
        Boolean boolReturn;
        
        System.out.println("**1****2****3****4****5****6****7****8***");
        for (int i = 1; i <= 8; i++){
            //System.out.print("*   *   *   *   *   *   *   *   *\n");
            
            for(int j = 1; j <= 8; j++){
                ChessPiece piece = board.getPieceAtPosition(j, i);

                if(piece == null){
                    System.out.print("*    ");
                }else{
                    if(piece.getState() == 1){
                        System.out.print("*    ");
                    }else{
                        
                        ChessPiece.ChessPieces name = piece.getChessPieceName();
                        ChessPiece.Colours colour = piece.getChessPieceColour();
                    
                        if(colour == ChessPiece.Colours.WHITE){
                            //System.out.print("*W  ");
                            System.out.print("*w");
                        }else{
                            //System.out.print("*B  ");
                            System.out.print("*b");
                        }
                    
                        if(name == ChessPiece.ChessPieces.KING){
                            System.out.print("K");
                        }else if(name == ChessPiece.ChessPieces.QUEEN){
                            System.out.print("Q");   
                        }else if(name == ChessPiece.ChessPieces.ROOK){
                            System.out.print("R");   
                        }else if(name == ChessPiece.ChessPieces.BISHOP){
                            System.out.print("B");   
                        }else if(name == ChessPiece.ChessPieces.KNIGHT){
                            System.out.print("N");   
                        }else if(name == ChessPiece.ChessPieces.PAWN){
                            System.out.print("P");   
                        }
                    
                        System.out.print("  ");
                        
                    }   
                }
            }
            System.out.print((char)(letter + count)+ " = " + (count+1));
            
            if(count == 7){
               System.out.println("\n**1****2****3****4****5****6****7****8***");
            }else{
                System.out.println("\n*****************************************");
            }
            
            count ++;
        }
        //System.out.println("**1****2****3****4****5****6****7****8***");
        
    }
    

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        VanillaChessRules rules = new VanillaChessRules();
        
        rules.createBoard(Board.BoardTypes.VANILLA_CHESS_BOARD);
        Board board = rules.getBoardInstance();
        
        Boolean boolReturn;
        String exit = new String("");
        
        
        Scanner streamInput = null;
        String fileInput;
        String[] splitFileInput;
        String delimeters = "[ ]+";
        Integer curX1 = 0;
        Integer curY1 = 0;
        Integer newX1 = 0;
        Integer newY1 = 0;
        
        try{
            streamInput = new Scanner(new FileInputStream("stalemateTest.txt"));
        }catch(FileNotFoundException e){
            System.out.println("ERORR: FILE NOT FOUND!\n");
            System.exit(0);
        }
        
        
        /*while(streamInput.hasNextLine()){
            
            System.out.println("/////////////////////////////////////////");
            fileInput = streamInput.nextLine();
            splitFileInput = fileInput.split(delimeters);
            curX1 = new Integer(splitFileInput[0]);
            curY1 = new Integer(splitFileInput[1]);
            newX1 = new Integer(splitFileInput[2]);
            newY1 = new Integer(splitFileInput[3]);
            rules.tryMove(ChessPiece.Colours.BLACK,curX1, curY1, newX1, newY1);
            drawBoard(board);
            
            ArrayList <Message> messages = rules.getMessages();
            
            for (int i = 0; i < messages.size();i++){
                System.out.print(messages.get(i).getType() + ": " + messages.get(i).getText()+ "\n\n\n");
            }
        }*/
        
        streamInput.close();
        
        
        
        while(true){
            drawBoard(board);
        
            System.out.println("\nEnter curX curY newX newY(Seperate with spaces)");
            int curX = in.nextInt();
            int curY = in.nextInt();
            int newX = in.nextInt();
            int newY = in.nextInt();
            exit = in.nextLine();
            //System.out.println("\nMoving piece from: (" + curX + "," + curY + ")" + " to: (" + newX + "," + newY + ")" );
    
            boolReturn = rules.tryMove(ChessPiece.Colours.BLACK, curX,curY,newX,newY);
            
            if(boolReturn == true){
                drawBoard(board);
            }
            
            ArrayList<Message> messages = rules.getMessages();
            
            for (int i = 0; i < messages.size();i++){
                System.out.print(messages.get(i).getType() + ": " + messages.get(i).getText());
            }
            
            
            
            System.out.println("\nExit?(Y/N)");
        
            exit = in.nextLine();
        
            if(exit.equalsIgnoreCase("Y")){
                System.exit(0);
            }
        }
        
    }
}
