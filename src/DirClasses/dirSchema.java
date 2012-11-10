/*
 * This code is distributed under terms of GNU GPLv2.
 * *See LICENSE file.
 * ©UKRINFORM 2011-2012
 */

package DirClasses;

/**
 * Directory schema object.
 * 
 * <p>Using for transporting through directories
 * tree and making dirEntry object.</p>
 * @author Stanislav Nepochatov
 */
public class dirSchema extends Generic.csvElder {
    
    /**
     * Default constructor.
     * 
     * <p>Using for defining csv format options.</p>
     */
    public dirSchema() {
        this.baseCount = 2;
        this.groupCount = 3;
        this.currentFormat = Generic.csvElder.csvFormatType.ComplexCsv;
    }
    
    /**
     * Default constructor from csv form.
     * @param givenCsv given csv line
     * @since RibbonServer a2
     */
    public dirSchema(String givenCsv) {
        this();
        java.util.ArrayList<String[]> parsedStruct = Generic.csvFormat.fromCsv(this, givenCsv);
        FULL_DIR_NAME = parsedStruct.get(0)[0];
        COMM = parsedStruct.get(0)[1];
        DIR_LANGS = parsedStruct.get(1);
        SH_ACCESS = parsedStruct.get(2);
        DIR_EXPORTS = parsedStruct.get(3);
    }

    /**
     * Parametrick costructor.
     * @param givenPath full path of directory
     * @param givenComm comment for directory
     * @param givenFlag anonymous flag for this directory
     */
    public dirSchema(String givenPath, String givenComm) {
        this();
        FULL_DIR_NAME = givenPath;
        COMM = givenComm;
        DIR_LANGS = new String[] {"ALL"};
        DIR_EXPORTS = null;
        SH_ACCESS = null;
    }

    /**
     * Full directory path
     */
    public String FULL_DIR_NAME;

    /**
     * Commentary for directory
     */
    public String COMM;

    /**
     * Directory's supported languages
     * @since RibbonServer a2
     */
    public String[] DIR_LANGS;

    /**
     * Access list for directory
     * @since RibbonServer a2
     */
    public String[] SH_ACCESS;

    /**
     * Directory's exports list
     * @since RibbonServer a2
     */
    public String[] DIR_EXPORTS;

    @Override
    public String toCsv() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
