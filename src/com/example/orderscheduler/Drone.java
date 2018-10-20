package com.example.orderscheduler;

public class Drone {
    private double xCoordinate;
    private double yCoordinate;
    private double distanceFromOrigin;
    private long speed;
    private DroneOrder currentOrder;

    public Drone(long speed) {
        this.xCoordinate = 0;
        this.yCoordinate = 0;
        this.speed = speed;
        this.currentOrder = null;
    }

    /**
     *
     * @return the arrival time for the drone when it is assigned a drone order
     */
    public long getArrivalTime() {
        return (int)Math.ceil(60*((2*distanceFromOrigin)/speed));
    }

    /**
     *
     * @param order sets the drone paramters to current order parameters
     */
    public void setOrder(DroneOrder order) {
        this.xCoordinate = order.getxCoordinate();
        this.yCoordinate = order.getyCoordinate();
        this.distanceFromOrigin = order.getDistanceFromOrigin();
    }

    /**
     *
     * @param droneOrder sets the order drone is currently serving
     */
    public void setCurrentOrder(DroneOrder droneOrder) {
        this.currentOrder = droneOrder;
    }
}
