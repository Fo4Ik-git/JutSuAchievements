package com.fo4ik;

import com.fo4ik.parse.ParseUrl;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MainWindow extends JFrame {

    private static JTextArea textArea;

    public MainWindow() {
        setTitle("Jut.Su Achievements");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

    }

    public void init() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        //Text field at the top
        JTextField textField = new JTextField();
        textField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(e -> {
            String text = textField.getText();

            ParseUrl parseUrl = new ParseUrl(); // Create a ParseUrl object
            try {
                parseUrl.parseUrl(text); // Parse url
            } catch (Exception ex) {
                textArea.append("Error in parseUrl \n");
            }


            textField.setText("");
            String[] fileNames = {"decode.txt", "index.html"};

            for (String fileName : fileNames) {
                File file = new File(fileName);
                if (file.exists()) {
                    file.delete();
                }
            }

        });

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.add(sendButton, BorderLayout.EAST);
        inputPanel.add(textField, BorderLayout.CENTER);

        //Main panel to see time
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //Add components to main panel
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);

        setVisible(true);
    }

    public static JTextArea getTextArea() {
        return textArea;
    }

    public static void setTextArea(JTextArea textArea) {
        MainWindow.textArea = textArea;
    }
}
