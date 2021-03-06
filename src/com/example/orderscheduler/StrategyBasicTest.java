package com.example.orderscheduler;

import java.util.ArrayList;
import java.util.Arrays;

public class StrategyBasicTest {

    /**
     * Testing the base case where all orders are scheduled according to the shortest distance
     */
    public static void scenarioOne() {
        System.out.println("---------------------- StrategyBasic Scenario One ----------------------");
        String order1 = new String();
        ArrayList<String> orders = new ArrayList<String>
                (Arrays.asList("WM001 N11W5 05:11:50", "WM002 S3E2 05:11:55", "WM003 N7E50 05:31:50", "WM004 N11E5 06:11:50"));
        CustomerOrderServer server = new CustomerOrderServer(orders);
        Drone drone = new Drone(1);

        StrategyBasic thewarehouse = new StrategyBasic(server, drone, 6*3600, 22*3600);
        thewarehouse.startProcessingOrder();
        assert thewarehouse.getCompleteOrders().get(0).getOrderId().equals("WM002") : "Valid order1";
        assert thewarehouse.getCompleteOrders().get(1).getOrderId().equals("WM001") : "Valid order2";
        assert thewarehouse.getCompleteOrders().get(2).getOrderId().equals("WM004") : "Valid order3";
        assert thewarehouse.getCompleteOrders().get(3).getOrderId().equals("WM003")  : "Valid order4";
        assert thewarehouse.getNPSValue() == 75.0 : "NPSValue";
    }


    /**
     * Testing the base case where all orders except one is scheduled when its past 1 hour limit
     */
    public static void scenarioTwo() {
        System.out.println("---------------------- StrategyBasic Scenario Two ----------------------");
        String order1 = new String();
        ArrayList<String> orders = new ArrayList<String>
                (Arrays.asList("WM001 N11W5 05:11:50", "WM002 S3E2 05:11:55", "WM003 N7E50 05:11:50", "WM004 N11E5 06:11:50"));
        CustomerOrderServer server = new CustomerOrderServer(orders);
        Drone drone = new Drone(1);

        StrategyBasic thewarehouse = new StrategyBasic(server, drone, 6*3600, 22*3600);
        thewarehouse.startProcessingOrder();
        assert thewarehouse.getCompleteOrders().get(0).getOrderId().equals("WM002") : "Valid order1";
        assert thewarehouse.getCompleteOrders().get(1).getOrderId().equals("WM001") : "Valid order2";
        assert thewarehouse.getCompleteOrders().get(2).getOrderId().equals("WM003") : "Valid order3";
        assert thewarehouse.getCompleteOrders().get(3).getOrderId().equals("WM004")  : "Valid order4";
        assert thewarehouse.getNPSValue() == 50.0 : "NPSValue";
    }

    /**
     * Testing the base case where all orders are at same distance
     */
    public static void scenarioThree() {
        System.out.println("---------------------- StrategyBasic Scenario Three ----------------------");
        String order1 = new String();
        ArrayList<String> orders = new ArrayList<String>
                (Arrays.asList("WM001 N2W5 05:11:50", "WM002 S2E5 05:11:55", "WM003 N2E5 05:30:50", "WM004 N5E2 06:11:50"));
        CustomerOrderServer server = new CustomerOrderServer(orders);
        Drone drone = new Drone(1);

        StrategyBasic thewarehouse = new StrategyBasic(server, drone, 6*3600, 22*3600);
        thewarehouse.startProcessingOrder();
        assert thewarehouse.getCompleteOrders().get(0).getOrderId().equals("WM001") : "Valid order1";
        assert thewarehouse.getCompleteOrders().get(1).getOrderId().equals("WM002") : "Valid order2";
        assert thewarehouse.getCompleteOrders().get(2).getOrderId().equals("WM003") : "Valid order3";
        assert thewarehouse.getCompleteOrders().get(3).getOrderId().equals("WM004")  : "Valid order4";
        assert thewarehouse.getNPSValue() == 100.0 : "NPSValue";
    }



    /**
     * Testing the base case where all orders are at same time
     */
    public static void scenarioFour() {
        System.out.println("---------------------- StrategyBasic Scenario Four ----------------------");
        String order1 = new String();
        ArrayList<String> orders = new ArrayList<String>
                (Arrays.asList("WM001 N2W5 05:11:50", "WM002 S2E5 05:11:50", "WM003 N2E5 05:11:50", "WM004 N5E2 05:11:50"));
        CustomerOrderServer server = new CustomerOrderServer(orders);
        Drone drone = new Drone(1);

        StrategyBasic thewarehouse = new StrategyBasic(server, drone, 6*3600, 22*3600);
        thewarehouse.startProcessingOrder();
        assert thewarehouse.getCompleteOrders().get(0).getOrderId().equals("WM001") : "Valid order1";
        assert thewarehouse.getCompleteOrders().get(1).getOrderId().equals("WM004") : "Valid order2";
        assert thewarehouse.getCompleteOrders().get(2).getOrderId().equals("WM003") : "Valid order3";
        assert thewarehouse.getCompleteOrders().get(3).getOrderId().equals("WM002")  : "Valid order4";
        assert thewarehouse.getNPSValue() == 100.0 : "NPSValue";
    }

    /**
     * Testing the base case where there is huge gap in arrival of lastorder
     */
    public static void scenarioFive() {
        System.out.println("---------------------- StrategyBasic Scenario Five ----------------------");
        String order1 = new String();
        ArrayList<String> orders = new ArrayList<String>
                (Arrays.asList("WM001 N1W5 05:11:50", "WM002 S2E4 05:12:50", "WM003 N2E3 06:11:50", "WM004 N4E2 08:11:50"));
        CustomerOrderServer server = new CustomerOrderServer(orders);
        Drone drone = new Drone(1);

        StrategyBasic thewarehouse = new StrategyBasic(server, drone, 6*3600, 22*3600);
        thewarehouse.startProcessingOrder();
        assert thewarehouse.getCompleteOrders().get(0).getOrderId().equals("WM002") : "Valid order1";
        assert thewarehouse.getCompleteOrders().get(1).getOrderId().equals("WM001") : "Valid order2";
        assert thewarehouse.getCompleteOrders().get(2).getOrderId().equals("WM003") : "Valid order3";
        assert thewarehouse.getCompleteOrders().get(3).getOrderId().equals("WM004")  : "Valid order4";
        assert thewarehouse.getNPSValue() == 100.0 : "NPSValue";
    }


    /**
     * Testing the base case where there is one detractor we get
     */
    public static void scenarioSix() {
        System.out.println("---------------------- StrategyBasic Scenario Six ----------------------");
        String order1 = new String();
        ArrayList<String> orders = new ArrayList<String>
                (Arrays.asList("WM005 N12E14 05:08:50", "WM004 N4E2 05:10:50", "WM001 N1W5 05:11:50", "WM002 S2E4 05:12:50", "WM003 N112E113 05:15:50"));
        CustomerOrderServer server = new CustomerOrderServer(orders);
        Drone drone = new Drone(1);

        StrategyBasic thewarehouse = new StrategyBasic(server, drone, 6*3600, 22*3600);
        thewarehouse.startProcessingOrder();
        assert thewarehouse.getCompleteOrders().get(0).getOrderId().equals("WM004") : "Valid order1";
        assert thewarehouse.getCompleteOrders().get(1).getOrderId().equals("WM005") : "Valid order2";
        assert thewarehouse.getCompleteOrders().get(2).getOrderId().equals("WM001") : "Valid order3";
        assert thewarehouse.getCompleteOrders().get(3).getOrderId().equals("WM002")  : "Valid order4";
        assert thewarehouse.getCompleteOrders().get(4).getOrderId().equals("WM003")  : "Valid order5";
        assert thewarehouse.getNPSValue() == 40.0 : "NPSValue";
    }


    /**
     * Testing Evenly distributed orders throughout the day
     */
    public static void scenarioSeven() {
        System.out.println("---------------------- StrategyBasic Scenario Seven ----------------------");
        String order1 = new String();
        ArrayList<String> orders = new ArrayList<String>
                (Arrays.asList("WM001 N1W5 06:11:50", "WM002 S2E4 07:11:50", "WM003 N2E3 08:11:50", "WM004 N4E2 09:11:50"));
        CustomerOrderServer server = new CustomerOrderServer(orders);
        Drone drone = new Drone(1);

        StrategyBasic thewarehouse = new StrategyBasic(server, drone, 6*3600, 22*3600);
        thewarehouse.startProcessingOrder();
        assert thewarehouse.getCompleteOrders().get(0).getOrderId().equals("WM001") : "Valid order1";
        assert thewarehouse.getCompleteOrders().get(1).getOrderId().equals("WM002") : "Valid order2";
        assert thewarehouse.getCompleteOrders().get(2).getOrderId().equals("WM003") : "Valid order3";
        assert thewarehouse.getCompleteOrders().get(3).getOrderId().equals("WM004")  : "Valid order4";
        assert thewarehouse.getNPSValue() == 100.0 : "NPSValue";
    }

    /**
     *  First order is delaying the next set of orders
     */
    public static void scenarioEight() {
        System.out.println("---------------------- StrategyBasic Scenario Eight ----------------------");
        String order1 = new String();
        ArrayList<String> orders = new ArrayList<String>
                (Arrays.asList("WM001 N111W115 06:10:50", "WM002 S2E4 06:12:50", "WM003 N2E3 06:13:50", "WM004 N4E2 09:11:50"));
        CustomerOrderServer server = new CustomerOrderServer(orders);
        Drone drone = new Drone(1);

        StrategyBasic thewarehouse = new StrategyBasic(server, drone, 6*3600, 22*3600);
        thewarehouse.startProcessingOrder();
        assert thewarehouse.getCompleteOrders().get(0).getOrderId().equals("WM001") : "Valid order1";
        assert thewarehouse.getCompleteOrders().get(1).getOrderId().equals("WM002") : "Valid order2";
        assert thewarehouse.getCompleteOrders().get(2).getOrderId().equals("WM003") : "Valid order3";
        assert thewarehouse.getCompleteOrders().get(3).getOrderId().equals("WM004")  : "Valid order4";
        assert thewarehouse.getNPSValue() == -50.0 : "NPSValue";
    }
}
