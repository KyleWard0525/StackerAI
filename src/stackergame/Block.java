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
public class Block {
    
    public static double xSpeed = 3;
    protected static double speedInc = 1.05; //Increase speed by 5%
    private int width;
    private int height;
    private static int blockID = 0;
    private int[] position = new int[2];
    public int x;
    public int y;
    public boolean isBase = false;
    
    public Block(int w, int h)
    {
        this.width = w;
        this.height = h;
        blockID++;
        xSpeed *= speedInc;
    }
    
    public Block()
    {
        this.width = 50;
        this.height = 50;
        blockID++;
        xSpeed *= speedInc;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public static double getxSpeed() {
        return xSpeed;
    }

    public static int getBlockID() {
        return blockID;
    }   

    public static void setSpeedInc(double speed) {
        speedInc = speed;
    }
    
    
}
