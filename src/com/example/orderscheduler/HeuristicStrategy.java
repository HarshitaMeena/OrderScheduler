package com.example.orderscheduler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class HeuristicStrategy extends Warehouse {

    PriorityQueue<DroneOrder> priorityOrders;
    int timeWeight;
    int distanceWeight;


    public HeuristicStrategy(CustomerOrderServer orderServer, Drone drone, long startTime, long endTime) {
        super(orderServer, drone, startTime, endTime);

        timeWeight = 1;
        distanceWeight = 1;

        priorityOrders = new PriorityQueue<DroneOrder>(10, new Comparator<DroneOrder>() {
            @Override
            public int compare(DroneOrder o1, DroneOrder o2) {
                float scoreo1 = timeWeight*3600/((int)(currentTime-o1.getArrivalTime()));
                scoreo1 += distanceWeight*o1.getDistanceFromOrigin();
                float scoreo2 = timeWeight*3600/((int)(currentTime-o2.getArrivalTime()));
                scoreo2 += distanceWeight*o2.getDistanceFromOrigin();
                return (int)(scoreo1-scoreo2);
            }
        });

    }

    @Override
    public void startProcessingOrder() {

        while (currentTime < closeTime) {
            getOrdersFromServer();
            DroneOrder nextorder = getNextOrder();
            if (nextorder != null) {
                deliveryDrone.setOrder(nextorder);
                nextorder.setDeliveredTime(currentTime + (deliveryDrone.getArrivalTime() / 2));
                System.out.println(nextorder);
                System.out.println(nextorder.timeFormat(currentTime));
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
     * @return The next order to process from the priority queue
     */
    private DroneOrder getNextOrder() {
        DroneOrder nextorder = null;
        Iterator<DroneOrder> it = priorityOrders.iterator();
        if(it.hasNext()) {
            nextorder = it.next();
            priorityOrders.poll();
        }

        if (nextorder != null) {
            completeOrders.add(nextorder);
        }

        return nextorder;
    }

    /**
     * Gets all the orders from the server queue that have elapsed the current time
     * and distribute accordingly in priority queue or the other queue
     */
    private void getOrdersFromServer() {
        ArrayList<DroneOrder> toserve = orderServer.getNextSetOfOrders(currentTime);
        for (int i = 0; i < toserve.size(); ++i) {
            totalOrders++;
            priorityOrders.add(toserve.get(i));
        }
    }

    private double getNPSValue() {
        return 100.0*(promoters-detractors)/totalOrders;
    }

}
