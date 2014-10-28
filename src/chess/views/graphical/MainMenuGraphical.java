/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.views.graphical;

import chess.models.GameType;
import chess.models.messages.Message;
import chess.views.MainMenuView;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Justin
 */
public class MainMenuGraphical extends MainMenuView {

    private State state = State.MAIN;
    JFrame mainFrame = null;



    public void MainMenuGraphical (){
    	initializeMainMenu();
    }


    @Override
    public void update() {
        if (state == State.MAIN) {
            //Display play / quit
        } else if (state == State.PLAY) {
            int i = 1;

			//Ask which variant is wanting to be played
            for (GameType type : GameType.values()) {
				//"[" + i + "] " + type + " - " + type.getDescription ()
                //i++;
            }
        }
    }

    public void showTitle() {
        //Load an image with this?
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    @Override
    public void displayMessage(Message message) {
        //message.getType () + ": " + message.getText ()
    }

    public enum State {

        MAIN, PLAY
    };

    public void initializeMainMenu(){
        mainFrame = initializeFrame("Main Menu", this.WINDOWHEIGHT, this.WINDOWWIDTH, new BorderLayout());

        JMenuBar menuBar = initializeMenuBar(mainFrame.getWidth(), this.MENUBARHEIGHT);
        
        initializeMenu("File", menuBar, "");
        initializeMenu("Help", menuBar, "Displays the information about the game");
        initializeMenuItem("File", "Exit", menuBar);
        
        mainFrame.setJMenuBar(menuBar);
        
        mainFrame.validate();
    }

    private JFrame initializeFrame(String name, int height, int width, BorderLayout layout){
        JFrame frame = new JFrame(name);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(height, width);
        frame.setLayout(layout);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

    private void initializeMenu(String itemName, JMenuBar menuBar, String toolTipText) {
        JMenu item = new JMenu(itemName);
        item.setSize(this.MENUHEIGHT,this.MENUWIDTH);
        item.setToolTipText(toolTipText);
        item.setVisible(true);
        menuBar.add(item);
    }

    private JMenuBar initializeMenuBar(int width, int height){
        JMenuBar bar = new JMenuBar();
        bar.setVisible(true);
        bar.setSize(width,height);
        return bar;
    }

    private void initializeMenuItem(String menuName, String itemName, JMenuBar bar){
        int count = bar.getComponentCount();
        JMenuItem item = new JMenuItem(itemName);
        item.setVisible(true);
        
        //item.setSize(this.MENUITEMHEIGHT, menuName.length() * 20);
        
        for(int i = 0; i < count; i++){
            
            if(bar.getMenu(i).getText().equals(menuName)){
                bar.getMenu(i).add(item);
                break;
            }
        }
        bar.validate();
    }
    
    private final int MENUHEIGHT = 50;
    private final int MENUWIDTH = 100;
    private final int MENUITEMHEIGHT = 50;
    private final int MENUITEMWIDTH = 50;
    private final int MENUBARHEIGHT = 25;
    private final int WINDOWHEIGHT = 600;
    private final int WINDOWWIDTH = 600;
    
}
