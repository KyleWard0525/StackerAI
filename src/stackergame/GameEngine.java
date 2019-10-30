/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stackergame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Stack;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;

/**
 *
 * This class is the heart of the game.
 * The GameEngine handles all of the movements,
 * collision detection, and other primary functions needed
 * for the game to work
 * 
 * @author KyleWard0525
 */
public class GameEngine {
    
    private Stack<Block> blockStack = new Stack<>();
    private JPanel gamePanel;
    private Block baseBlock;
    private JPanel currBlockPanel;
    private Block currBlock;
    private JPanel leftBorder;
    private JPanel rightBorder;
    private Block prevBlock;
   
    /**
     *
     * @param gp
     * @param leftBorder
     * @param rightBorder
     */
    public GameEngine(JPanel gp, JPanel leftBorder, JPanel rightBorder)
    {
        this.gamePanel = gp;
        setGameBorders(leftBorder, rightBorder);
    }
    
    /**
     *
     * @param b
     */
    public void addBlockToStack(Block b)
    {
        blockStack.push(b);
        Block block = new Block(b.getWidth(), b.getHeight());
        block.x = gamePanel.getWidth()/2;
        block.y = b.y + b.getHeight();
        //drawBlock(block);
    }
    
    /**
     * Draw new block above the previous one
     * @param b
     */
    public void drawBlock(Block b)
    {
        timer.stop();
        Random rand = new Random();
        JPanel block = new JPanel();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float blue = rand.nextFloat();
        block.setBackground(new Color(r,g,blue,1.0f));
        block.setBounds(gamePanel.getWidth()/2, b.y, b.getWidth(), b.getHeight());
        this.currBlockPanel = block; 
        setCurrBlock(b);
        gamePanel.add(block);
        
        gamePanel.repaint();
        timer.start();
    }
    
    /**
     * Set the area in which the block can move
     * @param l
     * @param r
     */
    public void setGameBorders(JPanel l, JPanel r)
    {
        this.leftBorder = l;
        this.rightBorder = r;
    }
    
    /**
     * Check if block b is within the hit-box of 
     * the previous block
     * 
     * @param b
     * @return
     */
    public boolean isAligned(Block b)
    {
        //timer.stop();
        if((b.x < prevBlock.x - 16) || (b.x > prevBlock.x + 16))
        {
            return false;
        }
        return true;
    }
    
    /**
     *
     */
    public void resetBlockSpeed()
    {
        Block.xSpeed = 3;
    }
    
    //Movement Handler
    ActionListener listener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent ae) {
            //Check Boundaries
             if(currBlock.x > rightBorder.getX()-220)
             {
                 Block.xSpeed = -Block.xSpeed;
             }
             
             if(currBlock.x < leftBorder.getX()-130)
             {
                 Block.xSpeed = Math.abs(Block.xSpeed);
             }
             
            currBlock.x += Block.xSpeed;
             
            currBlockPanel.setLocation((int)(currBlock.x), currBlock.y);
            gamePanel.repaint();
        }
    };

    /**
     *
     */
    public Timer timer = new Timer(10, listener);

    /**
     *
     * @return
     */
    public Block getCurrBlock() {
        return currBlock;
    }

    /**
     *
     * @param currBlock
     */
    public void setCurrBlock(Block currBlock) {
        this.currBlock = currBlock;
    }

    /**
     *
     * @return
     */
    public Stack<Block> getBlockStack() {
        return blockStack;
    }

    /**
     *
     * @param gamePanel
     */
    public void setGamePanel(JPanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    /**
     *
     * @param leftBorder
     */
    public void setLeftBorder(JPanel leftBorder) {
        this.leftBorder = leftBorder;
    }

    /**
     *
     * @param rightBorder
     */
    public void setRightBorder(JPanel rightBorder) {
        this.rightBorder = rightBorder;
    }

    /**
     *
     * @return
     */
    public JPanel getCurrBlockPanel() {
        return currBlockPanel;
    }

    /**
     *
     * @param currBlockPanel
     */
    public void setCurrBlockPanel(JPanel currBlockPanel) {
        this.currBlockPanel = currBlockPanel;
    }

    /**
     *
     * @return
     */
    public Block getBaseBlock() {
        return baseBlock;
    }

    /**
     *
     * @param baseBlock
     */
    public void setBaseBlock(Block baseBlock) {
        this.baseBlock = baseBlock;
    }
    
    /**
     *
     * @return
     */
    public int getScore()
    {
        return blockStack.size();
    }

    /**
     *
     * @return
     */
    public Block getPrevBlock() {
        return prevBlock;
    }

    public void setPrevBlock(Block prevBlock) {
        this.prevBlock = prevBlock;
    }
    
    
}

