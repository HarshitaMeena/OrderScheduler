package com.example.orderscheduler;

import java.util.ArrayList;
import java.util.Arrays;

public class OrderScheduler {
    public static void main(String[] args) {
        String order1 = new String();
        ArrayList<String> orders = new ArrayList<String>
                (Arrays.asList("WM001 N11W5 05:11:50", "WM002 S3E2 05:11:55", "WM003 N7E50 05:31:50", "WM004 N11E5 06:11:50"));
        CustomerOrderServer server = new CustomerOrderServer(orders);
        Drone drone = new Drone(1);

        Warehouse thewarehouse = new Warehouse(server, drone, 6*3600, 22*3600);
        thewarehouse.startProcessingOrders();
        System.out.println(server);
        System.out.println(6*3600);
        System.out.println(6*3600+7*60+13);
        System.out.println(6*3600+31*60+24);
        System.out.println(6*3600+55*60+35);
    }
}
