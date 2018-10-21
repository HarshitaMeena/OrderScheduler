package com.example.orderscheduler;

import java.util.ArrayList;
import java.util.Arrays;

public class StrategyBasicTest {

    /**
     * Testing the base case where all orders are scheduled according to the shortest distance
     */
    public static void scenarioOne() {
        System.out.println("---------------------- Scenario One ----------------------");
        String order1 = new String();
        ArrayList<String> orders = new ArrayList<String>
                (Arrays.asList("WM001 N11W5 05:11:50", "WM002 S3E2 05:11:55", "WM003 N7E50 05:31:50", "WM004 N11E5 06:11:50"));
        CustomerOrderServer server = new CustomerOrderServer(orders);
        Drone drone = new Drone(1);

        StrategyBasic thewarehouse = new StrategyBasic(server, drone, 6*3600, 22*3600);
        thewarehouse.startProcessingOrder();
        assert thewarehouse.getCompleteOrders().get(0).getOrderId() == "WM001" : "Valid order";
    }


    /**
     * Testing the base case where all orders except one is scheduled when its past 1 hour limit
     */
    public static void scenarioTwo() {
        System.out.println("---------------------- Scenario Two ----------------------");
        String order1 = new String();
        ArrayList<String> orders = new ArrayList<String>
                (Arrays.asList("WM001 N11W5 05:11:50", "WM002 S3E2 05:11:55", "WM003 N7E50 05:11:50", "WM004 N11E5 06:11:50"));
        CustomerOrderServer server = new CustomerOrderServer(orders);
        Drone drone = new Drone(1);

        StrategyBasic thewarehouse = new StrategyBasic(server, drone, 6*3600, 22*3600);
        thewarehouse.startProcessingOrder();
    }

    /**
     * Testing the base case where all orders are at same distance
     */
    public static void scenarioThree() {
        System.out.println("---------------------- Scenario Three ----------------------");
        String order1 = new String();
        ArrayList<String> orders = new ArrayList<String>
                (Arrays.asList("WM001 N2W5 05:11:50", "WM002 S2E5 05:11:55", "WM003 N2E5 05:30:50", "WM004 N5E2 06:11:50"));
        CustomerOrderServer server = new CustomerOrderServer(orders);
        Drone drone = new Drone(1);

        StrategyBasic thewarehouse = new StrategyBasic(server, drone, 6*3600, 22*3600);
        thewarehouse.startProcessingOrder();
    }



    /**
     * Testing the base case where all orders are at same time
     */
    public static void scenarioFour() {
        System.out.println("---------------------- Scenario Four ----------------------");
        String order1 = new String();
        ArrayList<String> orders = new ArrayList<String>
                (Arrays.asList("WM001 N2W5 05:11:50", "WM002 S2E5 05:11:50", "WM003 N2E5 05:11:50", "WM004 N5E2 05:11:50"));
        CustomerOrderServer server = new CustomerOrderServer(orders);
        Drone drone = new Drone(1);

        StrategyBasic thewarehouse = new StrategyBasic(server, drone, 6*3600, 22*3600);
        thewarehouse.startProcessingOrder();
    }

    /**
     * Testing the base case where there is huge gap in arrival of lastorder
     */
    public static void scenarioFive() {
        System.out.println("---------------------- Scenario Five ----------------------");
        String order1 = new String();
        ArrayList<String> orders = new ArrayList<String>
                (Arrays.asList("WM001 N1W5 05:11:50", "WM002 S2E4 05:12:50", "WM003 N2E3 06:11:50", "WM004 N4E2 08:11:50"));
        CustomerOrderServer server = new CustomerOrderServer(orders);
        Drone drone = new Drone(1);

        StrategyBasic thewarehouse = new StrategyBasic(server, drone, 6*3600, 22*3600);
        thewarehouse.startProcessingOrder();
    }


    /**
     * Testing the base case where there is one detractor we get
     */
    public static void scenarioSix() {
        System.out.println("---------------------- Scenario Six ----------------------");
        String order1 = new String();
        ArrayList<String> orders = new ArrayList<String>
                (Arrays.asList("WM005 N12E14 05:08:50", "WM004 N4E2 05:10:50", "WM001 N1W5 05:11:50", "WM002 S2E4 05:12:50", "WM003 N112E113 05:15:50"));
        CustomerOrderServer server = new CustomerOrderServer(orders);
        Drone drone = new Drone(1);

        StrategyBasic thewarehouse = new StrategyBasic(server, drone, 6*3600, 22*3600);
        thewarehouse.startProcessingOrder();
    }
}
