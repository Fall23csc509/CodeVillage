// package org.codevillage;

// import javax.swing.*;
// import java.awt.*;

// public class Main extends JFrame {

//     public Main(){
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setSize(1000, 1000);
//         setVisible(true);
//     }
//     public static void main(String[] args) {
//         CanvasData canvasData = CanvasData.getInstance();
//         Canvas canvas = new Canvas();
//         canvasData.addPropertyChangeListener(canvas);

//         // Create JFrame
//         Main frame = new Main();
//         frame.add(canvas);

//         System.out.println("Hello world!");

//     }
// }

package org.codevillage;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String directoryPath = "./src/org/codevillage";

        FileChecker checker = new SourceFileChecker();
        List<Path> javaFiles = checker.getFilesToParse(Paths.get(directoryPath));

        System.out.println("Found Java files:");
        for (Path file : javaFiles) {
            System.out.println(file);
        }
    }
}