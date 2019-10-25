/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stackergame;

import cern.colt.Arrays;
import com.sun.glass.events.KeyEvent;
import java.awt.List;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import javax.swing.Timer;
import org.encog.ml.CalculateScore;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataPair;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.ml.ea.train.EvolutionaryAlgorithm;
import org.encog.neural.neat.NEATNetwork;
import org.encog.neural.neat.NEATPopulation;
import org.encog.neural.neat.NEATUtil;
import org.encog.neural.networks.training.TrainingSetScore;
import org.encog.util.simple.EncogUtility;

/**
 *
 * @author user
 */
public class NeatAI {

    private GameEngine engine;
    private NEATPopulation pop;
    private EvolutionaryAlgorithm trainer;
    public int genNumber = 0;
    private Robot robo;

    /**
     * Inputs for Network
     *
     * 1) Distance to prevBlock.x 2) Block Speed 3) Block width 4) Block height
     * 5) Block speed increase 6) Current score
     *
     *
     * Outputs for Network
     *
     * 1) Stop block 2) Don't stop block
     *
     * Population size: 1000
     */
    public NeatAI(GameEngine g) {
        this.engine = g;
        init();
    }

    public void init() {
        pop = new NEATPopulation(6, 2, 500);
        //pop.setInitialConnectionDensity(1.0);

        //Create random population
        pop.reset();
    }

    public NEATPopulation getPop() {
        return pop;
    }

    public double[] run() {
        double[] inputs = new double[pop.getInputCount()];
        Block currBlock = engine.getCurrBlock();
        Block prevBlock = engine.getPrevBlock();

        inputs[0] = Math.abs(currBlock.x - prevBlock.x);
        inputs[1] = Block.xSpeed;
        inputs[2] = currBlock.getWidth();
        inputs[3] = currBlock.getHeight();
        inputs[4] = Block.speedInc;
        inputs[5] = engine.getScore();

        double[] ideal = new double[2];

        if (inputs[0] > 16) {
            ideal = new double[]{0, 1};
        } else {
            ideal = new double[]{1, 0};
        }

        MLData inputData = new BasicMLData(inputs);
        MLData idealData = new BasicMLData(ideal);

        MLDataSet trainSet = new BasicMLDataSet();
        trainSet.add(inputData, idealData);

        CalculateScore score = new TrainingSetScore(trainSet);

        this.trainer = NEATUtil.constructNEATTrainer(pop, score);

        do {
            trainer.iteration();
        } while (trainer.getError() > 0.01);

        //Get best network
        NEATNetwork network = (NEATNetwork) trainer.getCODEC().decode(trainer.getBestGenome());

        //Save network
        if (GameBoard.gameOver == true) {
            try {
                FileOutputStream out = new FileOutputStream(new File("network.model"));
                ObjectOutputStream o = new ObjectOutputStream(out);
                o.writeObject(network);
                out.close();
                o.close();
                System.out.println("Network saved!");
                GameBoard.gameOver = false;
            } catch (Exception e) {
                System.out.println("Exception thrown when trying to save network!");
            }
        }

        return network.compute(inputData).getData();
    }

    public EvolutionaryAlgorithm getTrainer() {
        return trainer;
    }

    public ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            double[] move = run();

            if (move[0] == 1) {
                //Press Spacebar          
                try {
                    robo = new Robot();
                    robo.keyRelease(KeyEvent.VK_SPACE);
                } catch (Exception e) {
                    System.out.println("Exception thrown when AI tried to stop!");
                }
            }
        }

    };
    public Timer timer = new Timer(1, listener);
}
