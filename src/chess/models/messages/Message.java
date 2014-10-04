/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chess.models.messages;

/**
 *
 * @author Benjin
 */

public class Message {
    private Type type;
    private String text;
    
    public Message (Type type, String text) {
        this.type = type;
        this.text = text;
    }
    
    public Type getType () {
        return type;
    }
    
    public String getText () {
        return text;
    }
    
    public enum Type { ERROR, WARNING, SUCCESS, INFO };
}
