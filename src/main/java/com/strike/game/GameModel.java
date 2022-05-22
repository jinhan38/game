package com.strike.game;

public class GameModel {

    int strikeCount;

    int ballCount;

    public GameModel(int strikeCount, int ballCount) {
        this.strikeCount = strikeCount;
        this.ballCount = ballCount;
    }

    public void resultMessage() {
        System.out.println("게임 결과 : " + (ballCount - strikeCount) + "볼 " + strikeCount + "스트라이크");
    }

    public boolean checkSuccess(int gameLength) {
        return  strikeCount == gameLength;
    }
}
