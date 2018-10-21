package com.example.orderscheduler;

import java.util.ArrayList;
import java.util.Arrays;

public class OrderScheduler {
    public static void main(String[] args) {
        /**
         * Tests for the basic strategy that maintains a queue for delayed orders and shortest distance orders
         */
        StrategyBasicTest.scenarioOne();
        StrategyBasicTest.scenarioTwo();
        StrategyBasicTest.scenarioThree();
        StrategyBasicTest.scenarioFour();
        StrategyBasicTest.scenarioFive();
        StrategyBasicTest.scenarioSix();


        HeuristicStrategyTest.scenarioOne();
        HeuristicStrategyTest.scenarioTwo();
        HeuristicStrategyTest.scenarioThree();
    }
}
