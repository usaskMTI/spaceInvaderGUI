/*
  CMPT 270 Course Material
  Copyright (c) 2003-2021
  J.P. Tremblay and Grant Cheston
  All rights reserved.

  This document contains resources for homework assigned to students of
  CMPT 270 and shall not be distributed without permission.  Posting this
  file to a public or private website, or providing this file to any person
  not registered in CMPT 270 constitutes Academic Misconduct according to
  the University of Saskatchewan Policy on Academic Misconduct.
 */


package model;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The player in the space invaders game.
 */
public class Player extends GameObject {
    public static final int WIDTH = 46;
    public static final int HEIGHT = 25;

    /** The distance to move when it is time to move. */
    public static final int MOVE_DISTANCE = 15;

    /** The decrease in the score every time hit. */
    public static final int HIT_DECREMENT = 20;

    /* The initial number of lives for the Player. */
    public static final int INITIAL_NUM_LIVES = 4;

    /* The number of lives remaining for the Player. */
    protected int lives;

    /* The current score for the Player. */
    protected int score;

    /** Boolean showing if the laser is recharging */
    protected boolean recharging = false;

    /** Heat buildup */
    protected int heat = 0; // declared

    /** Overheat check */
    protected boolean overHeat = false; // declared

    /** How frequently (in terms of ticks) the player is to change image. */
    public static final int CHANGE_FREQ = 0;

    /** Timer for recharge before the next laser is fired*/
    Timer recharge_time;
    /** Timer for overheated state before player is allowed to fire or move*/
    Timer overheated_state;
    /** Timer for cool down before heat level is decreased*/
    Timer coolDown;

    /**
     * Initialize the player.
     */
    public Player(int x, int y, Game game) {
        super(x, y, game, "player");
        width = WIDTH;
        height = HEIGHT;
        lives = INITIAL_NUM_LIVES;
        score = 0;
//        heat = 0; // initialized once it solved a random error

        recharge_time = new Timer(300, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                recharging = false;
            }
        });
        overheated_state = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                heat = 0;
                overHeat = false;

            }
        });
        coolDown = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (heat >0)
                {
                    if(!overHeat)
                    {
                        heat = heat -1;
                    }
                }
            }
        });
    }


    /**
     * No actions for the player at clock ticks.
     */
    protected void update() {}

    /**
     * Move to the left.
     */
    public void moveLeft()
    {
        if(!overHeat)
        {
            x = Math.max(x - MOVE_DISTANCE, 0);
        }

    }

    /**
     * Move to the right.
     */
    public void moveRight()
    {
        if(!overHeat)
        {
            x = Math.min(x + MOVE_DISTANCE, game.getWidth() - width);
        }
    }

    /**
     * If canFire, fire a laser.
     */
    public void fire() {
        if (!this.recharging && !overHeat)
        {
            int laserX = x + (width - Laser.WIDTH) / 2;
            int laserY = y - Laser.HEIGHT;
            game.addLaser(new Laser(laserX, laserY, game));
            heat = heat + 1; //added heat increment
            if (heat >= 5)
            {
                overHeat =true;
                overheated_state.start();
            }

            this.recharging = true;
            recharge_time.start();
        }
        this.coolDown.restart();
    }

    /**
     * Handle the collision with another object.
     * 
     * @param other the object that collided with this instance
     */
    protected void collide(GameObject other) {
        lives = lives - 1;
        moveToLeftSide();
        if (lives == 0) {
            isDead = true;
        }
        score = score - HIT_DECREMENT;
    }

    /**
     * Move to the left side.
     */
    public void moveToLeftSide() {
        x = 0;
    }

    /**
     * @return the number of lives still remaining
     */
    public int getLives() {
        return lives;
    }

    /**
     * Set a new value for the number of lives.
     * 
     * @param lives the new value for the lives field
     */
    public void setLives(int lives) {
        this.lives = lives;
        if (lives == 0) {
            isDead = true;
        }
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param amount the amount by which the score is to be increased
     */
    public void increaseScore(int amount) {
        score = score + amount;
    }
    public int getHeat()
    {
        return heat;
    }
}

