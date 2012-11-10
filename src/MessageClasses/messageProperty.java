/*
 * This code is distributed under terms of GNU GPLv2.
 * *See LICENSE file.
 * ©UKRINFORM 2011-2012
 */

package MessageClasses;

/**
 * Message property class. This properties may control 
 * message processing.
 * @since RibbonServer a2
 * @author Stanislav Nepochatov <spoilt.exile@gmail.com>
 */
public class messageProperty extends Generic.csvElder {
    
    /**
     * Prefix of the property. 
     * Displays type of property.
     */
    public messagePropertyTypes PROPERTY_PREFIX;
    
    /**
     * Owner of this property.
     */
    public String USER;
    
    /**
     * Short decription message for property (one line).
     */
    public String TEXT_MESSAGE;
    
    /**
     * Date of marking.
     */
    public String DATE;
    
    /**
     * Empty configuration constructor.
     */
    messageProperty() {
        this.baseCount = 4;
        this.currentFormat = csvFormatType.SimpleCsv;
    }
    
    /**
     * Default constructor from csv form.
     * @param givenCsv given csv line
     */
    messageProperty(String givenCsv) {
        this();
        java.util.ArrayList<String[]> parsedStruct = Generic.csvFormat.fromCsv(this, givenCsv);
        String[] baseArray = parsedStruct.get(0);
        this.PROPERTY_PREFIX = messagePropertyTypes.valueOf(baseArray[0]);
        this.USER = baseArray[1];
        this.TEXT_MESSAGE = baseArray[2];
        this.DATE = baseArray[3];
    }
    
    /**
     * All parametrick constructor for internal usage.
     * @param givenPrefix
     * @param givenUser
     * @param givenMessage
     * @param givenDate 
     */
    messageProperty(messagePropertyTypes givenPrefix, String givenUser, String givenMessage, String givenDate) {
        
    }

    @Override
    public String toCsv() {
        return this.PROPERTY_PREFIX + ",{" + this.USER + "},{" + this.TEXT_MESSAGE + "}," + this.DATE;
    }
}
