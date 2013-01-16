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
public class Message extends MessageClasses.MessageEntry {
    
    /**
     * Message's content
     */
    public String CONTENT;
    
    /**
     * Empty constructor.
     */
    public Message() {
        super();
    }
    
    /**
     * Default constructor from csv line.
     * @param givenCsv 
     */
    public Message(String givenCsv) {
        super(givenCsv);
    }
    
    /**
     * Constructor for internal post procedure;
     * @param givenHeader header of message;
     * @param givenAuthor author of message;
     * @param givenLang language of message;
     * @param givenDirs destination dirs for message;
     * @param givenTags tag marks for message;
     * @param givenContent content of the message;
     */
    public Message (String givenHeader, String givenAuthor, String givenLang, String[] givenDirs, String[] givenTags, String givenContent) {
        HEADER = givenHeader;
        AUTHOR = givenAuthor;
        ORIG_AUTHOR = "";
        LANG = givenLang;
        DIRS = givenDirs;
        TAGS = givenTags;
        CONTENT = givenContent;
        ORIG_INDEX = "-1";
    }
    
    /**
     * Return message entry from Message
     * @return messageEntry object
     */
    public MessageClasses.MessageEntry returnEntry() {
        return (MessageClasses.MessageEntry) this;
    }
}
