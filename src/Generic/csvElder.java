/*
 * This code is distributed under terms of GNU GPLv2.
 * *See LICENSE file.
 * ©UKRINFORM 2011-2012
 */

package Generic;

/**
 * Additional fields and methods to represent
 * data structure as csv formated line.
 * 
 * @author Stanislav Nepochatov
 */
public abstract class csvElder {
    
    /**
     * Count of arguments which may be 
     * parsed as single string.
     * 
     * <p><b>WARNING!</b> This variable should be 
     * overriden by csvElder child classes.</p>
     */
    public Integer baseCount = -1;
    
    /**
     * Count of argument which must be
     * parsed as groups;
     * 
     * <p>Like: <code>[item0,item1,...itemN]</code></p>
     * 
     * <p><b>WARNING!</b> This variable should be 
     * overriden by csvElder child classes.</p>
     */
    public Integer groupCount = -1;
    
    /**
     * Enumeration of csv format types.
     */
    public static enum csvFormatType {
        
        /**
         * Simple csv format.
         * 
         * <p>Example: <code>word1,word2...</code></p>
         */
        SimpleCsv,
        
        /**
         * Complex csv format (with groups).
         * 
         * <p>Example: <code>word1,[gword1,gword2]...</code></p>
         */
        ComplexCsv,
        
        /**
         * Double struct (identeficator and value).
         * 
         * <p>Example: <code>id:value</code></p>
         */
        DoubleStruct
    }
    
    /**
     * Current format type of object.
     * 
     * <p><b>WARNING!</b> This variable should be 
     * overriden by csvElder child classes.</p>
     */
    public csvFormatType currentFormat;
    
    /**
     * Return csv representation of data object.
     * 
     * <p><b>WARNING!</b> This method should be 
     * overriden by csvElder child classes.</p>
     * @return csv formated string
     */
    public abstract String toCsv();
    
}
