package com.fo4ik.saveToFile;

import com.fo4ik.parse.ParseFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveToFile {

    public void saveToFile(String html) throws IOException { // Save html to file
        File file = new File("index.html");
        FileWriter fileWriter = new FileWriter(file);

        if(file.exists()){ // If file exist delete it
            file.delete();
           // file.createNewFile();
        } else {
            file.createNewFile();
        }

        fileWriter.write(html);
        fileWriter.flush();
        fileWriter.close();

        ParseFile parseFile = new ParseFile();
        parseFile.parseFileOfSite("index.html");
    }

}
