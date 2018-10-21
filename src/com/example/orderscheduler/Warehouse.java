package com.example.orderscheduler;

import java.util.ArrayList;

abstract public class Warehouse {
    protected long openTime;
    protected long closeTime;
    protected long currentTime;
    protected int promoters;
    protected int detractors;
    protected int totalOrders;
    protected static double timeLimit = 3600;
    protected  Drone deliveryDrone;

    protected CustomerOrderServer orderServer;
    protected ArrayList<DroneOrder> completeOrders;

    abstract public void startProcessingOrder();

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
    }

    /**
     *
     * @param order Update function to collect the number of promoters/detractors
     */
    protected void checkPromoterOrDetractor(DroneOrder order) {
        if (order.isPromoter()) {
            promoters++;
        } else if (order.isDetractor()) {
            detractors++;
        }
    }


    public ArrayList<DroneOrder> getCompleteOrders() {
        return completeOrders;
    }

}
