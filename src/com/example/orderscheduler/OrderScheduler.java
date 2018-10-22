package com.example.orderscheduler;


public class OrderScheduler {
    public static void main(String[] args) {

        DroneOrderTest.scenarioOne();

        InputFileTest.scenarioOne();

        /**
         * Tests for the basic strategy that maintains a queue for delayed orders and shortest distance orders
         */
        StrategyBasicTest.scenarioOne();
        StrategyBasicTest.scenarioTwo();
        StrategyBasicTest.scenarioThree();
        StrategyBasicTest.scenarioFour();

        StrategyBasicTest.scenarioFive();
        StrategyBasicTest.scenarioSix();
        StrategyBasicTest.scenarioSeven();
        StrategyBasicTest.scenarioEight();


        /**
         * Tests for the basic strategy that maintains a priority queue based on heuristic score
         */
        HeuristicStrategyTest.scenarioOne();
        HeuristicStrategyTest.scenarioTwo();
        HeuristicStrategyTest.scenarioThree();

        HeuristicStrategyTest.scenarioFour();
        HeuristicStrategyTest.scenarioFive();
        HeuristicStrategyTest.scenarioSix();
        HeuristicStrategyTest.scenarioSeven();

        HeuristicStrategyTest.scenarioEight();

    }
}
