package com.example.orderscheduler;

import java.util.ArrayList;

public class CustomerOrderServer {
    ArrayList<DroneOrder> allorders;

    /**
     *
     * @param rawOrders - Orders are received in raw format of string
     *                  converted to DroneOrder objects for Warehouse processing
     */
    public CustomerOrderServer(ArrayList<String> rawOrders) {
        allorders = new ArrayList<DroneOrder>();
        for (int i = 0; i < rawOrders.size(); ++i) {
            allorders.add(new DroneOrder(rawOrders.get(i)));
        }
    }

    /**
     * function used for returning orders which should be sent to warehouse according to the elapsed time
     * @param time the current time in the warehouse
     * @return
     */
    public ArrayList<DroneOrder> getNextSetOfOrders(double time) {
        ArrayList<DroneOrder> sentOrders = new ArrayList<DroneOrder>();
        int i;
        for (i = 0; i < allorders.size(); i++) {
            if (allorders.get(i).getArrivalTime() <= time) {
                sentOrders.add(allorders.get(i));
            } else {
                break;
            }
        }

        for (int j = 0; j < i; ++j) {
            allorders.remove(allorders.get(0));
        }

        return sentOrders;
    }

    @Override
    public String toString() {
        String remainingOrders = new String();
        for (int i = 0; i < allorders.size(); ++i) {
            remainingOrders += allorders.get(i).toString() + "\n";
        }

        return remainingOrders;
    }
}
