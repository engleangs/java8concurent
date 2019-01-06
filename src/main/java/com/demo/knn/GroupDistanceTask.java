package com.demo.knn;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class GroupDistanceTask implements Runnable {

    private Distance[] distances;
    private int startIndex,endIndex;
    private Sample example;
    private List<? extends Sample> dataSet;
    private CountDownLatch endController;

    public GroupDistanceTask(Distance[] distances, int startIndex, int endIndex, Sample example, List<? extends Sample> dataSet, CountDownLatch endController) {
        this.distances = distances;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.example = example;
        this.dataSet = dataSet;
        this.endController = endController;
    }

    @Override
    public void run() {
        for( int index = startIndex;index< endIndex;index++) {
            Sample localExample = dataSet.get(index);
            distances[ index ] = new Distance(
                    index,
                    EuclideanDistanceCalculator.calculate( localExample,example));
            endController.countDown();
        }
    }
}
