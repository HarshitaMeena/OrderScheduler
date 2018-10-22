package com.example.orderscheduler;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class StrategyBasic extends Warehouse {

    private PriorityQueue<DroneOrder> incomingOrders;
    private PriorityQueue<DroneOrder> priorityOrders;

    public StrategyBasic(CustomerOrderServer orderServer, Drone drone, long startTime, long endTime) {
        super(orderServer, drone, startTime, endTime);

        incomingOrders = new PriorityQueue<>(10, new Comparator<DroneOrder>() {
            @Override
            public int compare(DroneOrder o1, DroneOrder o2) {
                if (o1.getDistanceFromOrigin() == o2.getDistanceFromOrigin()) {
                    return (int)(o1.getArrivalTime()-o2.getArrivalTime());
                } else if (o1.getDistanceFromOrigin() > o2.getDistanceFromOrigin()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });

        priorityOrders = new PriorityQueue<>(10, new Comparator<DroneOrder>() {
            @Override
            public int compare(DroneOrder o1, DroneOrder o2) {
                return (int)(o1.getArrivalTime()-o2.getArrivalTime());
            }
        });
    }


    /**
     * The main function for processing the orders -
     * 1 ) Get order from the server in the currenttime constraint
     * 2 ) Get next order from the priority queue or the other queue based on least distance
     *      Assign the order to the drone
     * 3 ) Advance time accordingly, else advance time in seconds
     * 4 ) Keep on doing this till the end of day.
     */
    @Override
    public void startProcessingOrder() {

        while (currentTime < closeTime) {

            setPriorityOrders();
            getOrdersFromServer();
            if (currentTime < 7*3600) {
                //System.out.println(incomingOrders);
                //System.out.println(priorityOrders);
            }
            DroneOrder nextorder = getNextOrder();

            if (nextorder != null) {
                //System.out.println(nextorder);
                deliveryDrone.setOrder(nextorder);
                nextorder.setDeliveredTime(currentTime + (deliveryDrone.getArrivalTime() / 2));
                System.out.println(nextorder.getOrderId()+" "+nextorder.timeFormat(currentTime));
                output.add(nextorder.getOrderId()+" "+nextorder.timeFormat(currentTime));
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
        Iterator<DroneOrder> it = priorityOrders.iterator();

        if(it.hasNext()) {
            nextorder = priorityOrders.poll();
        } else {
            Iterator<DroneOrder> itio = incomingOrders.iterator();
            if (itio.hasNext()) {
                nextorder = incomingOrders.poll();
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
        ArrayList<DroneOrder> toremove = new ArrayList<DroneOrder>();
        Iterator<DroneOrder> it = incomingOrders.iterator();
        while (it.hasNext()) {
            DroneOrder order = it.next();
            if (currentTime - order.getArrivalTime()  > timeLimit) {
                priorityOrders.add(order);
                toremove.add(order);
            }
        }

        for (int i = 0; i < toremove.size(); ++i) {
            incomingOrders.remove(toremove.get(i));
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

    public double getNPSValue() {
        totalOrders += priorityOrders.size();
        totalOrders += incomingOrders.size();
        detractors += priorityOrders.size();
        detractors += incomingOrders.size();
        return 100.0*(promoters-detractors)/totalOrders;
    }
}
