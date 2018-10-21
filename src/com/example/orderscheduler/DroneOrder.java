package com.example.orderscheduler;
import java.util.StringTokenizer;
import java.lang.Math;

public class DroneOrder {
    private double xCoordinate;
    private double yCoordinate;
    private double distanceFromOrigin;
    private long arrivalTime;
    private long deliveredTime = 22*3600;
    private String orderId;

    /**
     *
     * @param info - tockenize the string to get information in comprehensible format
     */
    public DroneOrder(String info) {
        StringTokenizer tokens = new StringTokenizer(info);
        if (tokens.hasMoreTokens()) {
            orderId = tokens.nextToken();
        }

        /**
         *  Get X and Y coordinates, if North direction, Y coordinate is possitive
         *  if East direction X coordinate is positive
         */
        if (tokens.hasMoreTokens()) {
            String coordinates = tokens.nextToken();
            StringTokenizer coorditokens = new StringTokenizer(coordinates, "NWES");

            if (coorditokens.hasMoreTokens()) {
                if (coordinates.indexOf('N') != -1) {
                    yCoordinate = Integer.parseInt(coorditokens.nextToken());
                } else {
                    yCoordinate = -Integer.parseInt(coorditokens.nextToken());
                }
            }

            if (coorditokens.hasMoreTokens()) {
                if (coordinates.indexOf('E') != -1) {
                    xCoordinate = Integer.parseInt(coorditokens.nextToken());
                } else {
                    xCoordinate = -Integer.parseInt(coorditokens.nextToken());
                }
            }

            distanceFromOrigin = Math.sqrt(xCoordinate * xCoordinate + yCoordinate * yCoordinate);
        }
        /**
         * Extract epoch time for a day from orders
         */
        if (tokens.hasMoreTokens()) {
            StringTokenizer time = new StringTokenizer(tokens.nextToken(), ":");
            arrivalTime = Integer.parseInt(time.nextToken())*3600+Integer.parseInt(time.nextToken())*60+Integer.parseInt(time.nextToken());

        }

    }

    public void setDeliveredTime(long dTime) {
        this.deliveredTime = dTime;
    }

    public boolean isPromoter() {
        return ((deliveredTime-arrivalTime)/3600 <= 1);
    }

    public boolean isDetractor() {
        return ((deliveredTime-arrivalTime)/3600 >= 4);
    }

    public double getxCoordinate() {
        return xCoordinate;
    }

    public double getyCoordinate() {
        return yCoordinate;
    }

    public double getDistanceFromOrigin() {
        return distanceFromOrigin;
    }

    public long getArrivalTime() {
        return arrivalTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public String timeFormat(long currenttime) {
        long time = currenttime;
        String hour = getDigitalForm(Math.toIntExact(Math.round(time / 3600)));
        time = time%3600;
        String minute = getDigitalForm(Math.toIntExact(Math.round(time / 60)));
        String second = getDigitalForm((int) (time%60));

        return hour + ":" + minute + ":" + second;
    }

    /**
     * Helper function to get pretty print for time
     */

    private String getDigitalForm(Integer time) {
        if (time/10 > 0) {
            return time.toString();
        } else {
            return "0"+time.toString();
        }
    }
    /**
     *
     * @return For printing the drone orders
     */
    @Override
    public String toString() {
        return "Order: " + orderId + ", xCoordinate: " + xCoordinate + ", yCoordinate: " + yCoordinate
                + ", distance from origin: " + distanceFromOrigin + ", arrival time : " + timeFormat(arrivalTime)
                + ", DeliveredTime: " + timeFormat(deliveredTime);
    }
}
