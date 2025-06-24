package com.espol.group05.grupo_05.Utilities;

import java.io.IOException;
import java.nio.file.*;

public class ContactFileManager {
    private static final String BASE_DIR = "contacts_data/";
    
    static {
        try {
            Files.createDirectories(Paths.get(BASE_DIR));
        } catch (IOException e) {
            throw new RuntimeException("Couldn create base directory", e);
        }
    }
    
    public static String saveListToFile(String contactId, String listType, Object list)
        throws IOException {
        String filename = String.format("%s_%s.txt", contactId, listType);
        Path filePath = Paths.get(BASE_DIR + filename);
        String content = serializeList(list);
        
        Files.write(filePath, content.getBytes(), StandardOpenOption.CREATE);
        return filePath.toString();
    }
    
    private static String serializeList(Object list) {
        if(list instanceof DoubleCircularList) {
            StringBuilder sb = new StringBuilder();
            for(Object item : (DoubleCircularList<?>)list) {
                sb.append(item.toString()).append("\n");
            }
            return sb.toString();
        }
        return "";
    }
}
