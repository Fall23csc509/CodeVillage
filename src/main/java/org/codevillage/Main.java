package org.codevillage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.codevillage.fetching.*;
import java.io.IOException;

public class Main extends JFrame {
  public static void main(String[] args) {
    Main window = new Main();
    window.setSize(1000, 1000);
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

    String[] dataTypes = { "GitHub", "Subversion", "Local Drive" };
    JComboBox<String> dataTypeDropdown = new JComboBox<>(dataTypes);
    JButton submitButton = new JButton("Submit");
    // logic
    DataFetcher[] fetch = { null };
    fetch[0] = new GithubDataFetcher();
    submitButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String selectedDataType = (String) dataTypeDropdown.getSelectedItem();
        String link = linkTextField.getText();
        String targetPath = targetPathTextField.getText();
        JOptionPane.showMessageDialog(Main.this,
            "Selected Data Type: " + selectedDataType + "\nLink: " + link + "\nTarget Path: " + targetPath);
//        fetch[0].downloadPackage(link, targetPath);
        File directory = new File("C:/Users/liamh/OneDrive/Desktop/csc509/CodeVillage/temp/src/main/java/org/codevillage");
        SourceCodeParser sourceCodeParser = new SourceCodeParser();
        try {
          ArrayList<JavaEntity> entities = sourceCodeParser.parseSourceFiles(directory);
          NeighborhoodGroupingLink neighborhoodGroupingLink = new NeighborhoodGroupingLink(
                  new InterfaceInsertLink(
                          new ShapeInitLink(
                                  new ShapesRelativePositioningLink(
                                          new NeighborhoodDimensionsLink(
                                                  new NeighborhoodAbsolutePositioningLink(
                                                          new ShapesAbsolutePositioningLink(
                                                                  new PutShapesChainEnd()
                                                          )
                                                  )
                                          )
                                  )
                          ))
          );
          neighborhoodGroupingLink.position(entities);
        } catch (IOException ioError) {
          ioError.printStackTrace();
        }
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
    data.addPropertyChangeListener(canvas);
    canvas.setBackground(Color.BLUE); // testing canvas is there

    // implement camera
    Camera cameraInstance = Camera.getInstance();
    addKeyListener(cameraInstance.getKeyListener());

    mainPanel.add(topPanel, BorderLayout.NORTH);
    mainPanel.add(canvas, BorderLayout.CENTER);
    add(mainPanel);

    dataTypeDropdown.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
          String selectedDataType = (String) e.getItem();
          if ("GitHub".equals(selectedDataType)) {
            fetch[0] = new GithubDataFetcher();
          } else if ("Subversion".equals(selectedDataType)) {
            fetch[0] = new SVNDataFetcher();
          } else {
            fetch[0] = new LocalDataFetcher();
          }
        }
      }
    });
  }
}
