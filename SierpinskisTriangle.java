/*
 * Ethan Blackwood
 */
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class SierpinskisTriangle extends Canvas {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Sierpinski's Triangle");
        frame.setSize(900,900);
        SierpinskisTriangle sp = new SierpinskisTriangle();
        frame.add(sp);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void paint(Graphics g){
       int x = 450;
       int y = 50;
       int size = 400;
       
       
       drawTriangle(g, x, y, size, 7);
    }

    public void drawTriangle(Graphics g, int x, int y, int size, int depth) {
       //num of triangles
        if (size < 4 || depth == 0) {
            return;
        }
        
        
        if (depth % 2 == 0) {//alternate between black and white
            g.setColor(Color.BLACK);
        } else {
            g.setColor(Color.WHITE);
        }
        
        //triangle corners
        int[] xPoints = { x, x - size / 2, x + size / 2 };
        int[] yPoints = { y, y + size, y + size };
        
        
        g.fillPolygon(xPoints, yPoints, 3);
        
        
        int newSize = size / 2;//smaller triangle size

        
        drawTriangle(g, x, y, newSize, depth - 1);//top triangle
        
        drawTriangle(g, x - newSize / 2, y + newSize, newSize, depth - 1);//left triangle
        
        drawTriangle(g, x + newSize / 2, y + newSize, newSize, depth - 1);//right triangle
    }
}
