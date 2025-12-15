package csci2020u.lab09.components;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import csci2020u.lab09.GraphGUI;

public class CartesianPlane extends GraphComponent {

    public CartesianPlane(GraphGUI gui) {
        super(gui);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        int width = 800;
        int height = 800;
        int centerX = width / 2;
        int centerY = height / 2;

        graphics2D.setColor(Color.LIGHT_GRAY);

        for (int x = centerX % 50; x < width; x += 50) {
            graphics2D.drawLine(x, 0, x, height);
        }

        for (int y = centerY % 50; y < height; y += 50) {
            graphics2D.drawLine(0, y, width, y);
        }

        graphics2D.setColor(Color.BLACK);

        graphics2D.drawLine(0, centerY, width, centerY); //x-axis
        graphics2D.drawLine(centerX, 0, centerX, height); // y-axis

        //set the font for the axis labels
        graphics2D.setFont(new Font("Arial", Font.PLAIN, 10));


        for (int x = centerX % 50; x < width; x += 50) {
            int xValue = (x - centerX) / 50;
            if (xValue != 0) {
                String label = Integer.toString(xValue);
                graphics2D.drawString(label, x - 5, centerY + 15);
            }
        }

        for (int y = centerY % 50; y < height; y += 50) {
            int yValue = (centerY - y) / 50;
            if (yValue != 0) {
                String label = Integer.toString(yValue);
                graphics2D.drawString(label, centerX + 5, y + 5);
            }
        }
    }
}

