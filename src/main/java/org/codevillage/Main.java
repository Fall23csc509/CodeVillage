package org.codevillage;

import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.*;


public class Main {
    public static void main(String[] args)
    {
        System.out.println("Hello world!");
        testNeighborhoodDrawing();
    }

    public static void testNeighborhoodDrawing()
    {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        List<Neighborhood> neighborhoods = new ArrayList<>(List.of(new Neighborhood[]{
                new Neighborhood(250, 250, 100, 100),
                new Neighborhood(300, 300, 100, 100),
                new Neighborhood(350, 350, 100, 100),
                new Neighborhood(100, 100, 100, 100),
        }));

        int N = 10;
        for(int i = 0; i < N; i++)
        {
            double angle = 2 * Math.PI * i / N;
            int x = (int)(250 + 150 * Math.cos(angle));
            int y = (int)(250 + 150 * Math.sin(angle));
            neighborhoods.add(new Neighborhood(x, y, 40, 40));
        }


        JPanel canvas = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for(Neighborhood neighborhood : neighborhoods)
                {
                    neighborhood.draw(g);
                }
            }
        };
        canvas.setPreferredSize(new Dimension(500, 500));

        frame.setContentPane(canvas);
        frame.pack();
        frame.setVisible(true);
    }
}