package com.example.orderscheduler;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class InputFileTest {

    /**
     * Testing the base case where all orders are scheduled according to the shortest distance
     */
    public static void scenarioOne() {
        System.out.println("---------------------- InputFile Scenario One ----------------------");

        try (Scanner s = new Scanner(new File("input.txt"))) {
            ArrayList<String> orders = new ArrayList<String>();
            while (s.hasNextLine()) {
                orders.add(s.nextLine());
            }
            s.close();
            CustomerOrderServer server = new CustomerOrderServer(orders);
            Drone drone = new Drone(1);

            StrategyBasic thewarehouse = new StrategyBasic(server, drone, 6*3600, 22*3600);
            thewarehouse.startProcessingOrder();
            assert thewarehouse.getCompleteOrders().get(0).getOrderId().equals("WM002") : "Valid order1";
            assert thewarehouse.getCompleteOrders().get(1).getOrderId().equals("WM001") : "Valid order2";
            assert thewarehouse.getCompleteOrders().get(2).getOrderId().equals("WM004") : "Valid order3";
            assert thewarehouse.getCompleteOrders().get(3).getOrderId().equals("WM003")  : "Valid order4";
            assert thewarehouse.getNPSValue() == 75.0 : "NPSValue";
        } catch (Exception e) {
            System.out.println("FileNotFound");
        }

    }
}
