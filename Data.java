/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ngoquyen.java.hacker.simulations;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author NgoQuyen
 */
public class Data {
    private static final Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
    public static int wScreen = (int) SCREEN.getWidth();
    public static int hScreen = (int) SCREEN.getHeight();
    public static int sizeChessUnit = 70;
    public static int sizeSudokuUnit = 62;
}
