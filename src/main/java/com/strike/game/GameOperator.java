package com.strike.game;

import java.util.ArrayList;

public interface GameOperator {

    void start(int gameLength, int min, int max);

    ArrayList<Integer> createBall(int gameLength, int min, int max);

    ArrayList<Integer> inputBall(int gameLength, int min, int max);

    boolean exit();


}
