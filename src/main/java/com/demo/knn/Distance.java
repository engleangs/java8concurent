package com.demo.knn;

public class Distance implements Comparable {
    private int index;
    private double distance;
    public Distance(){

    }

    public Distance(int index, double distance) {
        this.index = index;
        this.distance = distance;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public int compareTo(Object o) {
        Distance o2 = (Distance)o;
        if( o2.getDistance() == this.getDistance())
        {
            return 0;
        }
        else if (o2.getDistance() > this.getDistance())
        {
            return 1;
        }
        return -1;

    }
}
