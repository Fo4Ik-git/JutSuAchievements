package com.fo4ik.decode;


import com.fo4ik.parse.ParseFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;

public class Decode {

    public void decode(String line) throws IOException { // Decode base64
        byte[] decodedBytes = Base64.getDecoder().decode(line);
        String encode1 = new String(decodedBytes);

        ParseFile parseFile = new ParseFile();
        encode1 = parseFile.deleteFirstCharOfLine(encode1, 22); // Delete first 22 chars
        encode1 = parseFile.deleteLastCharOfLine(encode1, 2); // Delete last 2 chars

        decodedBytes = Base64.getDecoder().decode(encode1);
        String encode2 = new String(decodedBytes);
        File file = new File("decode.txt"); // Save decode to file
        FileWriter fileWriter = new FileWriter(file);
        if (file.exists()) { // If file exist delete it
            file.delete();
            file.createNewFile();
        } else {
            file.createNewFile();
        }

        fileWriter.write(encode2); // Write decode to file
        fileWriter.flush();
        fileWriter.close();

        parseFile.parseFileOfDecode("decode.txt"); // Parse decode file

        // System.out.println(encode2);


    }
}
