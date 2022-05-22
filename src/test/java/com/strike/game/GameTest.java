package com.strike.game;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;


import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class GameTest {


    @Test
    void check(){
        start(3,4,6);
    }


    @Test
    public void start(int gameLength, int min, int max) {

        ArrayList<Integer> inputList = inputBall(gameLength, min, max);
        System.out.println("입력하신 숫자는 " + inputList + "입니다");
        HashSet<Integer> temp = new HashSet<>(inputList);
        if (temp.size() != gameLength) {
            System.out.println("서로 다른 " + gameLength + "개의 숫자를 입력해주세요");
            start(gameLength, min, max);
            return;
        }
        GameModel gameModel = checkResult(createBall(gameLength, min, max), inputList);
        gameModel.resultMessage();
        boolean result = gameModel.checkSuccess(gameLength);
        if (!result) {
            System.out.println("숫자를 다시 입력해주세요");
            start(gameLength, min, max);
            return;
        }
        System.out.println(gameLength + "개의 숫자를 모두 맞추셨습니다.");
        if(!exit()){
            start(gameLength, min, max);
        };
    }
    @Test
    public boolean exit() {
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요");
        int input = new Scanner(System.in).nextInt();
        switch (input) {
            case 1:
                System.out.println("게임을 다시 시작합니다.");
                return false;
            case 2:
                System.out.println("게임을 종료합니다.");
                System.exit(0);
                return true;
            default:
                exit();
                return true;
        }
    }


    @Test
    public ArrayList<Integer> createBall(int gameLength, int min, int max) {
        HashSet<Integer> temp = new HashSet<>();
        while (temp.size() < gameLength) {
            temp.add(new Random().nextInt(max) + min);
        }
        ArrayList<Integer> ball = new ArrayList<>(temp);
        Collections.shuffle(ball);
        return ball;
    }
    @Test
    public ArrayList<Integer> inputBall(int gameLength, int min, int max) {
        ArrayList<Integer> ball = new ArrayList<>();
        int input = new Scanner(System.in).nextInt();
        char[] chars = Integer.toString(input).toCharArray();
        for (char aChar : chars) {
            ball.add(Character.getNumericValue(aChar));
        }
        return ball;
    }
    @Test
    private GameModel checkResult(ArrayList<Integer> num, ArrayList<Integer> inputBall) {
        int strikeCount = 0;
        int ballCount = 0;
        for (int i = 0; i < num.size(); i++) {

            if (Objects.equals(num.get(i), inputBall.get(i))) {
                strikeCount++;
            }
            if (inputBall.contains(num.get(i))) {
                ballCount++;
            }
        }
        return new GameModel(strikeCount, ballCount);
    }
}