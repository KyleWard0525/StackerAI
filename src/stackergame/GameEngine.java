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
 * @author user
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
   
    
    public GameEngine(JPanel gp, JPanel leftBorder, JPanel rightBorder)
    {
        this.gamePanel = gp;
        setGameBorders(leftBorder, rightBorder);
        
    }
    
    public void addBlockToStack(Block b)
    {
        blockStack.push(b);
        Block block = new Block(b.getWidth(), b.getHeight());
        block.x = gamePanel.getWidth()/2;
        block.y = b.y + b.getHeight();
        //drawBlock(block);
    }
    
    public void drawBlock(Block b)
    {
        Random rand = new Random();
        JPanel block = new JPanel();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float blue = rand.nextFloat();
        block.setBackground(new Color(r,g,blue));
        block.setBounds(gamePanel.getWidth()/2, b.y, b.getWidth(), b.getHeight());
        this.currBlockPanel = block; 
        setCurrBlock(b);
        gamePanel.add(block);
        
        gamePanel.repaint();
        timer.start();
    }
    
    public void setGameBorders(JPanel l, JPanel r)
    {
        this.leftBorder = l;
        this.rightBorder = r;
    }
    
    public boolean isAligned(Block b)
    {
        //timer.stop();
        if((b.x < prevBlock.x - 16) || (b.x > prevBlock.x + 16))
        {
            return false;
        }
        return true;
    }
    
    public void resetBlockSpeed()
    {
        Block.xSpeed = 3;
    }
    
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
    public Timer timer = new Timer(10, listener);

    public Block getCurrBlock() {
        return currBlock;
    }

    public void setCurrBlock(Block currBlock) {
        this.currBlock = currBlock;
    }

    public Stack<Block> getBlockStack() {
        return blockStack;
    }

    public void setGamePanel(JPanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setLeftBorder(JPanel leftBorder) {
        this.leftBorder = leftBorder;
    }

    public void setRightBorder(JPanel rightBorder) {
        this.rightBorder = rightBorder;
    }

    public JPanel getCurrBlockPanel() {
        return currBlockPanel;
    }

    public void setCurrBlockPanel(JPanel currBlockPanel) {
        this.currBlockPanel = currBlockPanel;
    }

    public Block getBaseBlock() {
        return baseBlock;
    }

    public void setBaseBlock(Block baseBlock) {
        this.baseBlock = baseBlock;
    }
    
    public int getScore()
    {
        return blockStack.size();
    }

    public Block getPrevBlock() {
        return prevBlock;
    }

    public void setPrevBlock(Block prevBlock) {
        this.prevBlock = prevBlock;
    }
}
