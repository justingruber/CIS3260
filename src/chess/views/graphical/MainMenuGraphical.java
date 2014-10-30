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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Justin
 */
public class MainMenuGraphical extends MainMenuView {

    private State state = State.MAIN;
    protected JFrame mainFrame = null;
    protected JPanel introScreen = new JPanel();
    protected GridBagConstraints constraints = new GridBagConstraints();
    public String selectedGameType = ""; 

    public void MainMenuGraphical (){
    	initializeMainMenu();
    }


    @Override
    public void update() {
        if (state == State.MAIN) {
            //Display play / quit
        } else if (state == State.PLAY) {
            
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
        initializeMenuItem("File", "Exit", menuBar, new AbstractAction(""){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        System.exit(0);
                    }
                }
                );
        initializeMenuItem("Help", "About", menuBar, new AbstractAction(""){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        createAboutDialogue();
                    }
                }
                );
        mainFrame.setJMenuBar(menuBar);
        
        constraints.gridx = 3;
        constraints.ipady = 10;
        constraints.anchor = GridBagConstraints.CENTER;
        
        introScreen.setBackground(Color.lightGray);
        introScreen.setLayout(new GridBagLayout());
        
        JLabel titleImage = new JLabel(new ImageIcon(getPath(this.TITLEIMAGE)));
        titleImage.setBorder(BorderFactory.createLineBorder(Color.gray, 2, true));
        introScreen.add(titleImage, constraints);
        
        
        JButton btnOne = new JButton("Play");
        btnOne.addActionListener(new AbstractAction(){
            @Override 
            public void actionPerformed(ActionEvent e){
                state = State.PLAY; 
                displayVariants();
            }});
        
        JButton btnTwo = new JButton("Quit");
        btnTwo.addActionListener(new AbstractAction(){
            @Override 
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }});
        
        introScreen.add(btnOne, constraints);
        introScreen.add(btnTwo, constraints);
        
        mainFrame.add(introScreen);
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

    private void initializeMenuItem(String menuName, String itemName, JMenuBar bar, Action a){
        int count = bar.getComponentCount();
        JMenuItem item = new JMenuItem(itemName);
        item.setVisible(true);
        item.addActionListener(a);
        
        for(int i = 0; i < count; i++){
            
            if(bar.getMenu(i).getText().equals(menuName)){
                bar.getMenu(i).add(item);
                break;
            }
        }
        bar.validate();
    }
    
    private void createAboutDialogue(){
        System.out.println("23");
    }
    
    private void displayVariants(){
        int i = 1;
        
        introScreen.removeAll();
        introScreen.setBackground(Color.lightGray);
        introScreen.setLayout(new GridBagLayout());
        introScreen.updateUI();
        
        JLabel instructions = new JLabel("Select a game mode you want to play.");
        JLabel toolTipInstructions = new JLabel("Mouse over for a description of the game type.");
        
        introScreen.add(instructions, constraints);
        introScreen.add(toolTipInstructions, constraints);
        
        //Ask which variant is wanting to be played
        for (GameType type : GameType.values()) {
            JRadioButton btn = new JRadioButton(type.toString());
            btn.setBackground(Color.lightGray);
            btn.setToolTipText(type.getDescription());
            btn.addActionListener(new AbstractAction(){
                @Override
                public void actionPerformed(ActionEvent e){
                    selectedGameType = btn.getText();
                }
            });
            introScreen.add(btn, constraints);
        }
        
        JButton nextBtn = new JButton("Next");
        nextBtn.addActionListener(new AbstractAction(){
           @Override
           public void actionPerformed(ActionEvent e){
               
           }
        });
        introScreen.add(nextBtn,constraints);
    }
    
    private String getPath(String piece) {
        String path = getClass().getResource(piece).toString();

        if (path.startsWith("file:/")) {
            path = path.substring(path.indexOf('/') + 1, path.length());
        }
        return path;
    }
    
    private final int MENUHEIGHT = 50;
    private final int MENUWIDTH = 100;
    private final int MENUITEMHEIGHT = 50;
    private final int MENUITEMWIDTH = 50;
    private final int MENUBARHEIGHT = 25;
    private final int WINDOWHEIGHT = 600;
    private final int WINDOWWIDTH = 600;
    private final String TITLEIMAGE = "./Assets/Logo/banner_25-pitch.png";
    
}