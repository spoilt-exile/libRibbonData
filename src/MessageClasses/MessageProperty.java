/**
 * This file is part of libRibbonData library (check README).
 * Copyright (C) 2012-2013 Stanislav Nepochatov
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
**/

package MessageClasses;

/**
 * Message property class. This properties may control 
 * message processing.
 * @since RibbonServer a2
 * @author Stanislav Nepochatov <spoilt.exile@gmail.com>
 */
public class MessageProperty extends Generic.CsvElder {
    
    /**
     * Prefix of the property. 
     * Displays type of property.
     */
    public MessagePropertyTypes PROPERTY_PREFIX;
    
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
    public MessageProperty() {
        this.baseCount = 4;
        this.currentFormat = csvFormatType.SimpleCsv;
    }
    
    /**
     * Default constructor from csv form.
     * @param givenCsv given csv line
     */
    public MessageProperty(String givenCsv) {
        this();
        java.util.ArrayList<String[]> parsedStruct = Generic.CsvFormat.fromCsv(this, givenCsv);
        String[] baseArray = parsedStruct.get(0);
        this.PROPERTY_PREFIX = MessagePropertyTypes.valueOf(baseArray[0]);
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
    public MessageProperty(MessagePropertyTypes givenPrefix, String givenUser, String givenMessage, String givenDate) {
        
    }

    @Override
    public String toCsv() {
        return this.PROPERTY_PREFIX + ",{" + this.USER + "},{" + this.TEXT_MESSAGE + "}," + this.DATE;
    }
}
