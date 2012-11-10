/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MessageClasses;

/**
 * Message class.
 * Extends messageEntry class with additional field for message content.
 * @author Stanislav Nepochatov <spoilt.exile@gmail.com>
 * @see messageEntry
 */
public class Message extends MessageClasses.messageEntry {
    
    /**
     * Message's content
     */
    public String CONTENT;
    
    /**
     * Empty constructor.
     */
    Message() {
        super();
    }
    
    /**
     * Default constructor from csv line.
     * @param givenCsv 
     */
    Message(String givenCsv) {
        super(givenCsv);
    }
    
    /**
     * Return message entry from Message
     * @return messageEntry object
     */
    public MessageClasses.messageEntry returnEntry() {
        return (MessageClasses.messageEntry) this;
    }
}
