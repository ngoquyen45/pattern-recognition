/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ngoquyen.java.hacker.simulations;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

/**
 *
 * @author NgoQuyen
 */
public class PatternRecognition extends JFrame {
    public JLabel[][] boxs = new JLabel[9][9];
    public Color X = new Color(170, 204, 246);
    public Color Y = new Color(241, 241, 241);
    
    public int[][] values = {
            {0, 0, 0, 0, 1, 6, 1, 4, 8},
            {0, 1, 6, 8, 6, 0, 1, 3, 2},
            {5, 6, 2, 4, 8, 2, 9, 7, 2},
            {6, 5, 4, 1, 3, 9, 1, 6, 2},
            {1, 4, 6, 1, 2, 0, 7, 2, 2},
            {4, 3, 3, 0, 1, 0, 0, 1, 3},
            {3, 8, 3, 1, 3, 9, 5, 0, 0},
            {1, 8, 7, 5, 1, 2, 5, 3, 0},
            {6, 0, 2, 4, 4, 4, 4, 1, 3},
        };
    
    // save data
    public boolean[][] d = new boolean[9][9];
    
    public Border b1 = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
    public Border b2 = BorderFactory.createLineBorder(Color.DARK_GRAY, 2);
    public static void main(String[] args) {
        PatternRecognition p = new PatternRecognition();
        p.simulationConsecutiveFour();
    }
    
    private PatternRecognition() {
        settingSimulation();
        settingWindow();
        
    }

    private void settingWindow() {
        // icons
        ArrayList<Image> icons = new ArrayList<>();
        icons.add(new ImageIcon("src/img/chess16.png").getImage());
        icons.add(new ImageIcon("src/img/chess32.png").getImage());
        setIconImages(icons);
        
        // decorate window
        setTitle("Pattern Recognition");
        setSize(8 * Data.sizeChessUnit + 6,8 * Data.sizeChessUnit + 29);
        setLocation((Data.wScreen - getWidth()) / 2,
                      (Data.hScreen - getHeight()) / 2);
        setLayout(null);//using no layout managers  
        setVisible(true);//making the frame visible
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public final void settingSimulation() {
        // boxs
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                boxs[i][j] = new JLabel();
                boxs[i][j].setSize(Data.sizeSudokuUnit, Data.sizeSudokuUnit);
                boxs[i][j].setLocation(j*Data.sizeSudokuUnit, i*Data.sizeSudokuUnit);
                boxs[i][j].setBackground(Y);
                boxs[i][j].setBorder(b1);
                boxs[i][j].setOpaque(true);
                boxs[i][j].setText("" + values[i][j]);
                boxs[i][j].setHorizontalAlignment(JLabel.CENTER);
                boxs[i][j].setFont(new Font("Arial", Font.BOLD, 35));
                // events
                add(boxs[i][j]);
                //sudoku[i][j].addMouseListener(this);
            }
        }
    }
    
    void simulationConsecutiveFour() {
        int m = values.length;
        int n = values[0].length;
        // Điều kiện để hàm này có thể thực thi
        if (m < 4 && n < 4) {
            return;
        }
        // checking rows
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n - 3; j++) {
                setEffect(X, b2, boxs[i][j], boxs[i][j+1], boxs[i][j+2], boxs[i][j+3]);         
                if (values[i][j] == values[i][j+1] &&
                    values[i][j+1] == values[i][j+2] &&
                    values[i][j+2] == values[i][j+3]) {
                    d[i][j] = d[i][j+1] = d[i][j+2] = d[i][j+3] = true;
                    setEffect(b1, boxs[i][j], boxs[i][j+1], boxs[i][j+2], boxs[i][j+3]);         
                    continue;
                }
                try {Thread.sleep(500);} catch (InterruptedException ex) {}
                cancelEffect(Y, b1, boxs[i][j], boxs[i][j+1], boxs[i][j+2], boxs[i][j+3]);         
            }
        }
        
        // checking cols
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m - 3; i++) {
                setEffect(X, b2, boxs[i][j], boxs[i+1][j], boxs[i+2][j], boxs[i+3][j]);
                if (values[i][j]   == values[i+1][j] &&
                    values[i+1][j] == values[i+2][j] &&
                    values[i+2][j] == values[i+3][j]) {
                    d[i][j] = d[i+1][j] = d[i+2][j] = d[i+3][j] = true;
                    setEffect(b1, boxs[i][j], boxs[i+1][j], boxs[i+2][j], boxs[i+3][j]);
                    continue;
                }
                try {Thread.sleep(500);} catch (InterruptedException ex) {}
                cancelEffect(Y, b1, boxs[i][j], boxs[i+1][j], boxs[i+2][j], boxs[i+3][j]);
            }
        }
        
        // checking diagonal
        for (int i = 0; i <= m - 4; i++) {
            for (int j = 0; j <= n - 4; j++) {
                setEffect(X, b2, boxs[i][j], boxs[i+1][j+1], boxs[i+2][j+2], boxs[i+3][j+3]);
                if (values[i][j]   == values[i+1][j+1] &&
                    values[i+1][j+1] == values[i+2][j+2] &&
                    values[i+2][j+2] == values[i+3][j+3]) {
                        setEffect(b1, boxs[i][j], boxs[i+1][j+1], boxs[i+2][j+2], boxs[i+3][j+3]);
                        d[i][j] = d[i+1][j+1] = d[i+2][j+2] = d[i+3][j+3] = true;
                        continue;
                }
                try {Thread.sleep(500);} catch (InterruptedException ex) {}
                cancelEffect(Y, b1, boxs[i][j], boxs[i+1][j+1], boxs[i+2][j+2], boxs[i+3][j+3]);
            }
        }
    
        // checking  sub diagonal
        for (int i = 0; i <= m - 4; i++) {
            for (int j = 3; j < n; j++) {
                setEffect(X, b2, boxs[i][j], boxs[i+1][j-1], boxs[i+2][j-2], boxs[i+3][j-3]);
                if (values[i][j]   == values[i+1][j-1] &&
                    values[i+1][j-1] == values[i+2][j-2] &&
                    values[i+2][j-2] == values[i+3][j-3]) {
                        d[i][j] = d[i+1][j-1] = d[i+2][j-2] = d[i+3][j-3] = true;
                        setEffect(b1, boxs[i][j], boxs[i+1][j-1], boxs[i+2][j-2], boxs[i+3][j-3]);
                        continue;
                }
                try {Thread.sleep(500);} catch (InterruptedException ex) {}
                cancelEffect(Y, b1, boxs[i][j], boxs[i+1][j-1], boxs[i+2][j-2], boxs[i+3][j-3]);
            }
        }
    }
    void setEffect(Color c, Border b, JLabel... l) {
        for (JLabel l1 : l) {
            l1.setBackground(c);
            l1.setBorder(b);
        }
    }
    void cancelEffect(Color c, Border b, JLabel... l) {
        for (JLabel l1 : l) {
            l1.setBackground(c);
            l1.setBorder(b);
        }
        updateData();
    }
    void setEffect(Color c, JLabel... l) {
        for (JLabel l1 : l) {
            l1.setBackground(c);
        }
    }
    
    void setEffect(Border b, JLabel... l) {
        for (JLabel l1 : l) {
            l1.setBorder(b);
        }
    }

    private void updateData() {
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[i].length; j++) {
                if (d[i][j])
                    setEffect(X, boxs[i][j]);
            }
        }
    }
}
