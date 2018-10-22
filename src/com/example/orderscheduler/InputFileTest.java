package com.example.orderscheduler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class InputFileTest {

    /**
     * Testing the base case where all orders are scheduled according to the shortest distance
     */
    public static void scenarioOne(String input) {
        System.out.println("---------------------- InputFile Scenario One ----------------------");

        try (Scanner s = new Scanner(new File(input))) {
            ArrayList<String> orders = new ArrayList<String>();
            while (s.hasNextLine()) {
                orders.add(s.nextLine());
            }
            s.close();
            CustomerOrderServer server = new CustomerOrderServer(orders);
            Drone drone = new Drone(1);


            StrategyBasic thewarehouse = new StrategyBasic(server, drone, 6*3600, 22*3600);
            thewarehouse.startProcessingOrder();

            try (FileWriter fw = new FileWriter("output.txt")) {

                for (int i = 0; i < thewarehouse.output.size(); i++) {
                    fw.write(thewarehouse.output.get(i) + "\n");
                }
                fw.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        catch (Exception e) {
            System.out.println("FileNotFound");
        }

    }
}
