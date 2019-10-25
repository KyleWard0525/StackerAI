/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stackergame;

/**
 *
 * @author user
 */
public class StackerGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GameBoard gb = new GameBoard();
        gb.setVisible(true);
        gb.drawBaseBlock();
    }
    
}
