package com.espol.group05.grupo_05.Utilities;

import java.io.File;

public class DirectoryCreator {
    
    public static boolean createDirectory(String path) {
        File directory = new File(path);
        if (!directory.exists()) {
            return directory.mkdir();
        }
        return false;
    }
}
