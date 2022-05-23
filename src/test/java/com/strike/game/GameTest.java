package com.strike.game;

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
        checkResult(createBall(3,4,6), createBall(3,4,6));
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