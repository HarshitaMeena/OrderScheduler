package com.example.orderscheduler;

import java.util.ArrayList;

public class Warehouse {
    private long openTime;
    private long closeTime;
    private long currentTime;
    private int promoters;
    private int detractors;
    private int totalOrders;
    private static double timeLimit = 3600;
    Drone deliveryDrone;

    CustomerOrderServer orderServer;
    ArrayList<DroneOrder> completeOrders;

    ArrayList<DroneOrder> incomingOrders;
    ArrayList<DroneOrder> priorityOrders;

    /**
     *
     * @param orderServer - The warehouse gets all the orders from the server which is active at all times
     * @param startTime - specifies the opening time of warehouse
     * @param endTime - specifies the closing time of the warehouse
     */
    public Warehouse(CustomerOrderServer orderServer, Drone drone, long startTime, long endTime) {
        this.openTime = startTime;
        this.closeTime = endTime;
        this.orderServer = orderServer;
        this.currentTime = startTime;
        this.deliveryDrone = drone;
        this.promoters = 0;
        this.detractors = 0;


        incomingOrders = new ArrayList<DroneOrder>();
        priorityOrders = new ArrayList<DroneOrder>();
        completeOrders = new ArrayList<DroneOrder>();
    }


    /**
     * The main function for processing the orders -
     * 1 ) Get order from the server in the currenttime constraint
     * 2 ) Get next order from the priority queue or the other queue based on least distance
     *      Assign the order to the drone
     * 3 ) Advance time accordingly, else advance time in seconds
     * 4 ) Keep on doing this till the end of day.
     */
    public void startProcessingOrders() {

        while (currentTime < closeTime) {
            setPriorityOrders();
            getOrdersFromServer();
            DroneOrder nextorder = getNextOrder();
            if (nextorder != null) {
                deliveryDrone.setOrder(nextorder);
                nextorder.setDeliveredTime(currentTime + (deliveryDrone.getArrivalTime() / 2));
                System.out.println(nextorder);
                System.out.println(nextorder.droneDepartureTime(currentTime));
                checkPromoterOrDetractor(nextorder);
                currentTime += deliveryDrone.getArrivalTime();
            }
            else {
                currentTime += 1;
            }
        }
        System.out.println(getNPSValue());

    }

    /**
     *
     * @return The next order to process, if the priority list which contains non-promoter orders
     * is not empty we give priority to that first
     * else we look into the order which could be delivered fastest
     */
    private DroneOrder getNextOrder() {
        DroneOrder nextorder = null;
        if (priorityOrders.size() > 0) {
            nextorder = priorityOrders.get(0);
            priorityOrders.remove(0);
        } else {
            int index = -1;
            for (int i = 0; i < incomingOrders.size(); ++i) {
                if (nextorder == null || incomingOrders.get(i).getDistanceFromOrigin() < nextorder.getDistanceFromOrigin()) {
                    nextorder = incomingOrders.get(i);
                    index = i;
                }
            }
            if (index != -1) {
                incomingOrders.remove(index);
            }

        }

        if (nextorder != null) {
            completeOrders.add(nextorder);
        }
        return nextorder;
    }


    /**
     * This function separates order which are past the expected best time to
     * the priority queue where all the delayed orders are kept.
     */

    private void setPriorityOrders() {
        int i = 0;
        while (i < incomingOrders.size()) {
            if (currentTime - incomingOrders.get(0).getArrivalTime() > timeLimit) {
                priorityOrders.add(incomingOrders.get(0));
                incomingOrders.remove(0);
            } else {
                break;
            }
        }
    }
    /**
     * Gets all the orders from the server queue that have elapsed the current time
     * and distribute accordingly in priority queue or the other queue
     */
    private void getOrdersFromServer() {
        ArrayList<DroneOrder> toserve = orderServer.getNextSetOfOrders(currentTime);
        for (int i = 0; i < toserve.size(); ++i) {
            totalOrders++;
            if (currentTime - toserve.get(i).getArrivalTime() > timeLimit) {
                priorityOrders.add(toserve.get(i));
            } else {
                incomingOrders.add(toserve.get(i));
            }
        }
    }

    /**
     *
     * @param order Update function to collect the number of promoters/detractors
     */
    private void checkPromoterOrDetractor(DroneOrder order) {
        if (order.isPromoter()) {
            promoters++;
        } else if (order.isDetractor()) {
            detractors++;
        }
    }

    private double getNPSValue() {
        totalOrders += priorityOrders.size();
        totalOrders += incomingOrders.size();
        detractors += priorityOrders.size();
        detractors += incomingOrders.size();
        return 100.0*(promoters-detractors)/totalOrders;
    }
}
