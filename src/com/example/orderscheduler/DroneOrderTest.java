package com.example.orderscheduler;

public class DroneOrderTest {

    /**
     * Testing all the formats in which a string can arrive
     */
    public static void scenarioOne() {
        System.out.println("---------------------- Drone Order Tests----------------------");

        DroneOrder baseOrder1 = new DroneOrder("WM001 N11W5 05:11:50");
        assert baseOrder1.getArrivalTime() == 18710 : "Valid epoch arrival time";
        assert baseOrder1.timeFormat(18710).equals("05:11:50") : "Valid epoch digital format";
        assert baseOrder1.getxCoordinate() == -5 : "XCoordinate";
        assert baseOrder1.getyCoordinate() == 11 : "YCoordinate";
        assert baseOrder1.getOrderId().equals("WM001") : "OrderId";


        DroneOrder baseOrder2 = new DroneOrder("WM002 S112E53 20:11:50");
        assert baseOrder2.getArrivalTime() == 20*3600+11*60+50 : "Valid epoch arrival time";
        assert baseOrder2.timeFormat(20*3600+11*60+50 ).equals("20:11:50") : "Valid epoch digital format";
        assert baseOrder2.getxCoordinate() == 53 : "XCoordinate";
        assert baseOrder2.getyCoordinate() == -112 : "YCoordinate";
        assert baseOrder2.getOrderId().equals("WM002") : "OrderId";


        DroneOrder baseOrder3 = new DroneOrder("WM012 N1E5 07:00:00");
        assert baseOrder3.getArrivalTime() == 7*3600: "Valid epoch arrival time";
        assert baseOrder3.timeFormat(7*3600).equals("07:00:00") : "Valid epoch digital format";
        assert baseOrder3.getxCoordinate() == 5 : "XCoordinate";
        assert baseOrder3.getyCoordinate() == 1 : "YCoordinate";
        assert baseOrder3.getOrderId().equals("WM012") : "OrderId";
    }
}
