/*
 * This code is distributed under terms of GNU GPLv2.
 * *See LICENSE file.
 * ©UKRINFORM 2011-2012
 */

package DirClasses;

/**
 * Directory entry object.
 * 
 * <p>Contains directory main data and list
 * of it child directories.</p>
 * @author Stanislav Nepochatov
 */
public class dirEntry extends dirSchema {
        
    /**
     * Default constructor (empty)
     * Usually this constructor used for creating root dir
     */
    public dirEntry() {
        DIR_NAME = "";
        FULL_DIR_NAME = "";
        DIR_ACCESS = null;
    }

    /**
     * Schema-based end constructor
     * @param givenSchema schema to build directory
     */
    public dirEntry(DirClasses.dirSchema givenSchema) {
        applySchema(givenSchema);
    }

    /**
     * Chain constuctor (adapted to a2)
     * @param upperLevel all parent directories
     * @param rest rest of the creation query
     * @param givenComm commentary for directory
     * @param givenPath path for images
     * @param givenAnon anonymous mode flag
     */
    public dirEntry(String upperLevel, String rest, DirClasses.dirSchema givenSchema) {
        Integer joint;
        if ((joint = rest.indexOf(".")) != -1) {
            DIR_NAME = rest.substring(0, joint);
            if (upperLevel.isEmpty()) {
                FULL_DIR_NAME = DIR_NAME;
            } else {
                FULL_DIR_NAME = upperLevel + "." + DIR_NAME;
            }
            DIR_PATH = FULL_DIR_NAME.toLowerCase().replaceAll("\\.", "/") + "/";
            new java.io.File(DIR_PATH).mkdirs();
            COMM = "Порожній напрямок";
            FOLDED_DIR.add(new dirEntry(FULL_DIR_NAME, rest.substring(joint + 1), givenSchema));
        } else {
            applySchema(givenSchema);
            new java.io.File(DIR_PATH).mkdirs();
        }
    }

    /**
     * Short directrory name
     */
    public String DIR_NAME;

    /**
     * Arraylist of inner directiries
     */
    public java.util.ArrayList<dirEntry> FOLDED_DIR = new java.util.ArrayList<>();

    /**
     * Arraylist of messages indexes
     */
    public java.util.ArrayList<String> DIR_INDEXCES = new java.util.ArrayList<>();

    /**
     * Path to dir messages
     */
    public String DIR_PATH;

    /**
     * Access array of this directory
     * @since RibbonServer a2
     */
    public dirPermissionEntry[] DIR_ACCESS;

    /**
     * Last searched directory
     * @since RibbonServer a2
     */
    private dirEntry lastEntry;

    /**
     * Insert chain of directories in current directory
     * @param upperLevel all parent directories
     * @param rest rest of the creation query
     * @param givenComm commentary for directory
     * @param givenPath path for images
     * @param givenAnon anonymous mode flag
     */
    public void insertDir(String upperLevel, String rest, DirClasses.dirSchema givenSchema) {
        Integer joint;
        if ((joint = rest.indexOf(".")) != -1) {
            String inserted_DIR_NAME = rest.substring(0, joint);
            String inserted_FULL_DIR_NAME;
            if (upperLevel.isEmpty()) {
                inserted_FULL_DIR_NAME = upperLevel + inserted_DIR_NAME;
            } else {
                inserted_FULL_DIR_NAME = upperLevel + "." + inserted_DIR_NAME;
            }
            if (this.hasFoldDir(inserted_DIR_NAME)) {
                lastEntry.insertDir(inserted_FULL_DIR_NAME, rest.substring(joint + 1), givenSchema);
            } else {
                if (this.DIR_NAME.isEmpty()) {
                    this.FOLDED_DIR.add(new dirEntry("", rest, givenSchema));
                } else {
                    this.FOLDED_DIR.add(new dirEntry(inserted_FULL_DIR_NAME, rest.substring(joint + 1), givenSchema));
                }
            }
        } else {
            String inserted_DIR_NAME = rest;
            if (hasFoldDir(inserted_DIR_NAME)) {
                lastEntry.applySchema(givenSchema);
            } else {
                FOLDED_DIR.add(new dirEntry(upperLevel, rest, givenSchema));
            }
        }
    }

    /**
     * Find out if there is a specified dir in FOLDED_DIR
     * @param foldedDirName name of directory
     * @return true if this entry contain such directory in it's children
     */
    private Boolean hasFoldDir(String foldedDirName) {
        java.util.ListIterator<dirEntry> dirIter = FOLDED_DIR.listIterator();
        while (dirIter.hasNext()) {
            dirEntry foldedDir = dirIter.next();
            if (foldedDir.DIR_NAME.equals(foldedDirName)) {
                lastEntry = foldedDir;
                return true;
            }
        }
        return false;
    }

    /**
     * Apply schema to given directory;
     * @param givenSchema 
     */
    public void applySchema(DirClasses.dirSchema givenSchema) {
        this.FULL_DIR_NAME = givenSchema.FULL_DIR_NAME;
        this.COMM = givenSchema.COMM;
        this.DIR_LANGS = givenSchema.DIR_LANGS;
        this.DIR_EXPORTS = givenSchema.DIR_EXPORTS;
        if (givenSchema.SH_ACCESS.length == 1 && givenSchema.SH_ACCESS[0].isEmpty()) {
            this.DIR_ACCESS = null;
        } else {
            this.DIR_ACCESS = new dirPermissionEntry[givenSchema.SH_ACCESS.length];
            for (Integer accessIndex = 0; accessIndex < givenSchema.SH_ACCESS.length; accessIndex++) {
                this.DIR_ACCESS[accessIndex] = new dirPermissionEntry(givenSchema.SH_ACCESS[accessIndex]);
            }
        }
        String[] chunks = this.FULL_DIR_NAME.split("\\.");
        this.DIR_NAME = chunks[chunks.length - 1];
        this.DIR_PATH = FULL_DIR_NAME.toLowerCase().replaceAll("\\.", "/") + "/";
    }

    /**
     * Build tree from specifed inner level
     * @param inLevel inner folding level
     * @return tree formated string
     */
    public String treeReport(Integer inLevel) {
        String spaceStr = "";
        for (Integer space = 0; space < inLevel; space++) {
            spaceStr += "   ";
        }
        String returned = spaceStr + this.DIR_NAME + " (" + this.DIR_INDEXCES.size() + ") " + Generic.csvFormat.renderGroup(this.DIR_LANGS) + " : " + this.COMM + "\n";
        if (!this.FOLDED_DIR.isEmpty()) {
            java.util.ListIterator<dirEntry> foldedIter = this.FOLDED_DIR.listIterator();
            while (foldedIter.hasNext()) {
                dirEntry foldDir = foldedIter.next();
                returned += foldDir.treeReport(inLevel + 1);
            }
        }
        return returned;
    }

    /**
     * Add index to folded directory
     * @param upperLevel upper level
     * @param rest rest of add query
     * @param givenIndex index of message
     */
    public void addIndex(String upperLevel, String rest, String givenIndex) {
        Integer joint;
        if ((joint = rest.indexOf(".")) != -1) {
            String indxed_DIR_NAME = rest.substring(0, joint);
            if (this.hasFoldDir(indxed_DIR_NAME) == false) {
                return;
            } else {
                lastEntry.addIndex(this.FULL_DIR_NAME, rest.substring(joint + 1), givenIndex);
            }
        } else {
            String indxed_DIR_NAME = rest;
            if (this.hasFoldDir(indxed_DIR_NAME) == false) {
                return;
            } else {
                lastEntry.DIR_INDEXCES.add(givenIndex);
            }
        }
    }

    /**
     * Remove index from folded directory
     * @param upperLevel upper level
     * @param rest rest of remove query
     * @param givenIndex index of message
     */
    public void removeIndex(String upperLevel, String rest, String givenIndex) {
        Integer joint;
        if ((joint = rest.indexOf(".")) != -1) {
            String indxed_DIR_NAME = rest.substring(0, joint);
            if (this.hasFoldDir(indxed_DIR_NAME) == false) {
                return;
            } else {
                lastEntry.removeIndex(this.FULL_DIR_NAME, rest.substring(joint + 1), givenIndex);
            }
        } else {
            String indxed_DIR_NAME = rest;
            if (this.hasFoldDir(indxed_DIR_NAME) == false) {
                return;
            } else {
                lastEntry.DIR_INDEXCES.remove(givenIndex);
            }
        }
    }

    /**
     * Get cascade of foded diretories.
     * @return formated string for net protocol
     */
    public String PROC_GET_DIR() {
        String returned = "RIBBON_UCTL_LOAD_DIR:" + this.toCsv() + "\n";
        java.util.ListIterator<dirEntry> foldIter = this.FOLDED_DIR.listIterator();
        while (foldIter.hasNext()) {
            returned += foldIter.next().PROC_GET_DIR();
        }
        return returned;
    }

    /**
     * Translate directory object into csv index line
     * @return csv formated string
     */
    @Override
    public String toCsv() {
        String accessStr;
        if (this.DIR_ACCESS != null) {
            String[] stringedAccess = new String[this.DIR_ACCESS.length];
            for (Integer strIndex = 0; strIndex < this.DIR_ACCESS.length; strIndex++) {
                stringedAccess[strIndex] = this.DIR_ACCESS[strIndex].toCsv();
            }
            accessStr = Generic.csvFormat.renderGroup(stringedAccess);
        } else {
            accessStr = "[]";
        }
        return this.FULL_DIR_NAME + ",{" + this.COMM + "}," + Generic.csvFormat.renderGroup(DIR_LANGS) + "," + 
                accessStr + "," + Generic.csvFormat.renderGroup(this.DIR_EXPORTS);
    }

    /**
     * Return dir with specified path
     * @param upperLevel upper level of path
     * @param rest rest of path
     * @return folded directory object
     */
    public dirEntry returnEndDir(String upperLevel, String rest) {
        Integer joint;
        if ((joint = rest.indexOf(".")) != -1) {
            String indxed_DIR_NAME = rest.substring(0, joint);
            if (this.hasFoldDir(indxed_DIR_NAME) == false) {
                return null;
            } else {
                return lastEntry.returnEndDir(this.FULL_DIR_NAME, rest.substring(joint + 1));
            }
        } else {
            String indxed_DIR_NAME = rest;
            if (this.hasFoldDir(indxed_DIR_NAME) == false) {
                return null;
            } else {
                return lastEntry;
            }
        }
    }

    /**
     * Return topmost access description array
     * @param upperLevel upper level of path
     * @param rest rest of path
     * @return array with dirEntry.dirPermissionEntry
     * @see dirEntry.dirPermissionEntry
     * @throws Exception 
     */
    public dirPermissionEntry[] getAccess(String upperLevel, String rest) throws Exception {
        Integer joint;
        if ((joint = rest.indexOf(".")) != -1) {
            String indxed_DIR_NAME = rest.substring(0, joint);
            if (this.hasFoldDir(indxed_DIR_NAME) == false) {
                throw new Exception("Неможливо знайти шлях до напрямку!");
            } else {
                dirPermissionEntry[] gainedAccess = lastEntry.getAccess(this.FULL_DIR_NAME, rest.substring(joint + 1));
                if (gainedAccess == null) {
                    return this.DIR_ACCESS;
                } else {
                    return gainedAccess;
                }
            }
        } else {
            String indxed_DIR_NAME = rest;
            if (this.hasFoldDir(indxed_DIR_NAME) == false) {
                throw new Exception("Неможливо знайти шлях до напрямку!");
            } else {
                return lastEntry.DIR_ACCESS;
            }
        }
    }
    
    /**
     * Deploy directory in base directory.
     * @param basePath path to Ribbon base.
     */
    public void deployDir(String basePath) {
        if (!this.DIR_NAME.isEmpty()) {
            this.DIR_PATH = basePath + "/" + this.DIR_PATH;
            new java.io.File(DIR_PATH).mkdirs();
        }
        java.util.ListIterator<dirEntry> foldedIter = this.FOLDED_DIR.listIterator();
        while (foldedIter.hasNext()) {
            foldedIter.next().deployDir(basePath);
        }
    }
}
