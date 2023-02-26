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

package spaceinvaders;

import control.Controller;

/**
 * This is the root class for a game where the player must shoot the invaders before they kill the
 * player.
 */
public class SpaceInvaders {
    /**
     * Create and start a controller that will control the game.
     * 
     * @param args any arguments are ignored
     */
    public static void main(String[] args) {
        Controller c = new Controller();
        c.start();
    }
}
