package com.fo4ik.parse;


import com.fo4ik.saveToFile.SaveToFile;
import org.jsoup.Jsoup;

import java.io.IOException;

public class ParseUrl {

    public void parseUrl(String url) throws IOException {
        String html = Jsoup.connect(url).get().html(); // Get html from url

        SaveToFile saveToFile = new SaveToFile();
        saveToFile.saveToFile(html);
    }

}
