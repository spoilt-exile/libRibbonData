/*
 * This code is distributed under terms of GNU GPLv2.
 * *See LICENSE file.
 * ©UKRINFORM 2011-2012
 */

package MessageClasses;

/**
 * Types of message properties.
 * @author Stanislav Nepochatov <spoilt.exile@gmail.com>
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
