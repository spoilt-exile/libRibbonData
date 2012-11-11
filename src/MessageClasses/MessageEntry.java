/*
 * This code is distributed under terms of GNU GPLv2.
 * *See LICENSE file.
 * ©UKRINFORM 2011-2012
 */

package MessageClasses;

/**
 * Message index entry class. 
 * Contains all message fields but it's content.
 * @author Stanislav Nepochatov <spoilt.exile@gmail.com>
 */
public class MessageEntry extends Generic.CsvElder {

    /**
     * Index of the original message
     * @since RibbonServer a2
     */
    public String ORIG_INDEX;
    
    /**
     * Index of message.
     */
    public String INDEX;

    /**
     * Message's directories.
     */
    public String[] DIRS;

    /**
     * Header of message.
     */
    public String HEADER;

    /**
     * Date of message release.
     */
    public String DATE;

    /**
     * Name of the original author. 
     * 
     * <p>Once message appears in the system name of original author 
     * cannot be changed by ordinary user.</p>
     * @since RibbonServer a2
     */
    public String ORIG_AUTHOR;
    
    /**
     * Author of message.
     */
    public String AUTHOR;

    /**
     * Message's tags.
     */
    public String[] TAGS;
    
    /**
     * System properties of this message.
     * @since RibbonServer a2
     */
    public java.util.ArrayList<MessageClasses.MessageProperty> PROPERTIES = new java.util.ArrayList<MessageClasses.MessageProperty>();
    
    /**
     * Message language.
     * @since RibbonServer a2
     */
    public String LANG;

    /**
     * Default empty constructor.
     */
    public MessageEntry() {
        this.baseCount = 8;
        this.groupCount = 2;
        this.currentFormat = csvFormatType.ComplexCsv;
    }

    /**
     * Default constructor from csv form.
     * @param givenCsv csv line
     */
    public MessageEntry(String givenCsv) {
        this();
        java.util.ArrayList<String[]> parsedStruct = Generic.CsvFormat.fromCsv(this, givenCsv);
        String[] baseArray = parsedStruct.get(0);
        this.INDEX = baseArray[0];
        this.ORIG_INDEX = baseArray[1];
        this.DIRS = parsedStruct.get(1);
        this.LANG = baseArray[2];
        this.HEADER = baseArray[3];
        this.DATE = baseArray[4];
        this.ORIG_AUTHOR = baseArray[5];
        this.AUTHOR = baseArray[6];
        this.TAGS = parsedStruct.get(2);
        String[] rawPropertiesArray = baseArray[7].split("$");
        if ((rawPropertiesArray.length > 1) || (!rawPropertiesArray[0].isEmpty())) {
            for (String rawProperty : rawPropertiesArray) {
                this.PROPERTIES.add(new MessageClasses.MessageProperty(rawProperty));
            }
        }
    }
    
    @Override
    public String toCsv() {
        return this.INDEX + "," + this.ORIG_INDEX + "," + Generic.CsvFormat.renderGroup(DIRS) + "," 
                + this.LANG + ",{" + this.HEADER + "}," + this.DATE + ",{" + this.ORIG_AUTHOR + "},{" + this.AUTHOR + "}," 
                + Generic.CsvFormat.renderGroup(TAGS) + "," + Generic.CsvFormat.renderMessageProperties(PROPERTIES);
    }
}
