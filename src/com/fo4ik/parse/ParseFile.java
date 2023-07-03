package com.fo4ik.parse;

import com.fo4ik.MainWindow;
import com.fo4ik.decode.Decode;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ParseFile {

    private JTextArea textArea;

    public void parseFileOfSite(String path) throws IOException {
        textArea = MainWindow.getTextArea();

        String line;
        BufferedReader reader = new BufferedReader(new FileReader(path));
        int count = 0;
        while ((line = reader.readLine()) != null) { // Find line with eval
            if (line.contains("eval")) {
                count++;
            }
            ;
            if (count == 2) { // Delete first 22 chars and last 6 chars
                line = deleteFirstCharOfLine(line, 22);
                line = deleteLastCharOfLine(line, 6);

                Decode decode = new Decode();
                decode.decode(line);
                break;
            }
        }
        reader.close();
    }

    public void parseFileOfDecode(String path) throws IOException {
        String line;
        List time = new ArrayList();
        List title = new ArrayList();
        BufferedReader reader = new BufferedReader(new FileReader(path)); // Read file
        textArea = MainWindow.getTextArea();
        while ((line = reader.readLine()) != null) {
            if (line.contains("time_start:")) { // Find line with time_start
                String[] split = line.split("time_start: ");
                String tmp = split[1];
                tmp = deleteLastCharOfLine(tmp, 1);
                long minute = TimeUnit.SECONDS.toMinutes(Long.parseLong(tmp)) -
                        (TimeUnit.SECONDS.toHours(Long.parseLong(tmp)) * 60);
                long second = TimeUnit.SECONDS.toSeconds(Long.parseLong(tmp)) -
                        (TimeUnit.SECONDS.toMinutes(Long.parseLong(tmp)) * 60);
                time.add(minute + ":" + second);
            }

            if (line.contains("title:")) { // Find line with title
                String[] split = line.split("title: ");
                String tmp = split[1];
                tmp = deleteLastCharOfLine(tmp, 1);
                title.add(tmp);
            }
        }
        for (int i = 0; i < time.size(); i++) { // Print time and title
            textArea.append("Time: " + time.get(i) + "\n");
        }
        reader.close();
        MainWindow.setTextArea(textArea);
    }


    public String deleteFirstCharOfLine(String line, int length) {
        return line.substring(length);
    }

    public String deleteLastCharOfLine(String line, int length) {
        return line.substring(0, line.length() - length);
    }
}
