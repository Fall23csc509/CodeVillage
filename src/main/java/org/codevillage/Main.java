package org.codevillage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import org.codevillage.fetching.*;

public class Main extends JFrame {
    public static void main(String[] args) {
        Main window = new Main();
        window.setSize(1200, 1200);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("509 Project 1");
    }

    Main() {

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel(new GridLayout(4, 2));

        // top panel components
        JLabel linkLabel = new JLabel("Link:");
        JLabel targetPathLabel = new JLabel("Target Path:");
        JLabel dataTypeLabel = new JLabel("Select Data Type:");
        JTextField linkTextField = new JTextField(20);
        JTextField targetPathTextField = new JTextField(20);

        String[] dataTypes = {"GitHub", "Subversion", "Local Drive"};
        JComboBox<String> dataTypeDropdown = new JComboBox<>(dataTypes);
        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedDataType = (String) dataTypeDropdown.getSelectedItem();
                String link = linkTextField.getText();
                String targetPath = targetPathTextField.getText();
                JOptionPane.showMessageDialog(Main.this, "Selected Data Type: " + selectedDataType + "\nLink: " + link + "\nTarget Path: " + targetPath);
            }
        });

        topPanel.add(linkLabel);
        topPanel.add(linkTextField);
        topPanel.add(targetPathLabel);
        topPanel.add(targetPathTextField);
        topPanel.add(dataTypeLabel);
        topPanel.add(dataTypeDropdown);
        topPanel.add(submitButton);

        Canvas canvas = new Canvas();
        CanvasData data = CanvasData.getInstance();
        // data.addObserver(canvas);
        canvas.setBackground(Color.BLUE);   // testing canvas is there

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(canvas, BorderLayout.CENTER);
        add(mainPanel);

        // logic
        DataFetcher fetch;
        dataTypeDropdown.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedDataType = (String) e.getItem();
                    if ("GitHub".equals(selectedDataType)) {
                        fetch = new GithubDataFetcher();
                        fetch.downloadPackage(linkTextField.getText(), targetPathTextField.getText());
                    } else if ("Subversion".equals(selectedDataType)) {
                        fetch = new SVNDataFetcher();
                        fetch.downloadPackage(linkTextField.getText(), targetPathTextField.getText());
                    } else {
                        fetch = new LocalDataFetcher();
                        fetch.downloadPackage(linkTextField.getText(), targetPathTextField.getText());
                    }
                }
            }
        });
    }
}
