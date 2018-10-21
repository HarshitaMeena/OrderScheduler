package com.example.orderscheduler;

import java.util.ArrayList;
import java.util.Arrays;

public class HeuristicStrategyTest {

    /**
     * Testing the base case where all orders are scheduled according to the shortest distance
     */
    public static void scenarioOne() {
        System.out.println("---------------------- HeuristicStrategy Scenario One ----------------------");
        String order1 = new String();
        ArrayList<String> orders = new ArrayList<String>
                (Arrays.asList("WM001 N11W5 05:11:50", "WM002 S3E2 05:11:55", "WM003 N7E50 05:31:50", "WM004 N11E5 06:11:50"));
        CustomerOrderServer server = new CustomerOrderServer(orders);
        Drone drone = new Drone(1);

        HeuristicStrategy thewarehouse = new HeuristicStrategy(server, drone, 6*3600, 22*3600);
        thewarehouse.startProcessingOrder();
        assert thewarehouse.getCompleteOrders().get(0).getOrderId().equals("WM002") : "Valid order1";
        assert thewarehouse.getCompleteOrders().get(1).getOrderId().equals("WM001") : "Valid order2";
        assert thewarehouse.getCompleteOrders().get(2).getOrderId().equals("WM004") : "Valid order3";
        assert thewarehouse.getCompleteOrders().get(3).getOrderId().equals("WM003")  : "Valid order4";
    }


    /**
     * Testing the base case where all orders except one is scheduled when its past 1 hour limit
     */
    public static void scenarioTwo() {
        System.out.println("---------------------- HeuristicStrategy Scenario Two ----------------------");
        String order1 = new String();
        ArrayList<String> orders = new ArrayList<String>
                (Arrays.asList("WM001 N11W5 05:11:50", "WM002 S3E2 05:11:55", "WM003 N7E50 05:11:50", "WM004 N11E5 06:11:50"));
        CustomerOrderServer server = new CustomerOrderServer(orders);
        Drone drone = new Drone(1);

        HeuristicStrategy thewarehouse = new HeuristicStrategy(server, drone, 6*3600, 22*3600);
        thewarehouse.startProcessingOrder();
        assert thewarehouse.getCompleteOrders().get(0).getOrderId().equals("WM002") : "Valid order1";
        assert thewarehouse.getCompleteOrders().get(1).getOrderId().equals("WM001") : "Valid order2";
        assert thewarehouse.getCompleteOrders().get(2).getOrderId().equals("WM004") : "Valid order3";
        assert thewarehouse.getCompleteOrders().get(3).getOrderId().equals("WM003")  : "Valid order4";
    }


    /**
     * Testing the base case where all orders are at same distance
     */
    public static void scenarioThree() {
        System.out.println("---------------------- HeuristicStrategy Scenario Three ----------------------");
        String order1 = new String();
        ArrayList<String> orders = new ArrayList<String>
                (Arrays.asList("WM001 N2W5 05:11:50", "WM002 S2E5 05:11:55", "WM003 N2E5 05:30:50", "WM004 N5E2 06:11:50"));
        CustomerOrderServer server = new CustomerOrderServer(orders);
        Drone drone = new Drone(1);

        HeuristicStrategy thewarehouse = new HeuristicStrategy(server, drone, 6*3600, 22*3600);
        thewarehouse.startProcessingOrder();
        assert thewarehouse.getCompleteOrders().get(0).getOrderId().equals("WM001") : "Valid order1";
        assert thewarehouse.getCompleteOrders().get(1).getOrderId().equals("WM002") : "Valid order2";
        assert thewarehouse.getCompleteOrders().get(2).getOrderId().equals("WM003") : "Valid order3";
        assert thewarehouse.getCompleteOrders().get(3).getOrderId().equals("WM004")  : "Valid order4";
    }

}
