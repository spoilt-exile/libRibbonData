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
 * Types of message properties.
 * @author Stanislav Nepochatov <spoilt.exile@gmail.com>
 * @deprecated this class unsatisfied new specs.<br>
 * Use <code>MessageProperty.Types</code> implementation.
 * @see MessageClasses.MessageProperty.Types
 */
public enum MessagePropertyTypes {
    
    /** Generic properties **/
    URGENT,                     //Message was marked as urgent
    MARK_USER,                  //User was marked message with custom text message
    MARK_ADM,                   //Admin was marked message with custom text message
    PROCESSING_FORBIDDEN,       //User forbid any processing of message
    NIGHT_EMBARGO,              //Message export will be performed at night
    
    /** System properties **/
    CORRUPTED_AND_RESTORED,     //Message lost one of the link and it's link was restored by system
    CORRUPTED_AND_LOST,         //Message lost all links and system cann't restore message
    RELOCATED,                  //Message was relocated during deletion of directory
    
    /** Import properties **/
    IMPORT_MAIL,                //Message was imported via e-mail
    IMPORT_RSS,                 //Message was imported via rss
    IMPORT_ASOP,                //Message was imported via asop2 system
    
    /** Export properties **/
    EXPORT_MAIL,                //Message was exported to e-mail
    EXPORT_RSS,                 //Message was exported to rss
    EXPORT_ASOP,                //Message was exported to asop2 system
    EXPORT_PLAIN,               //Message was exported to plain text file
    EXPORT_HTML                 //Message was exported to html file
    
}
