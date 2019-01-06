package com.demo.knn;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class KnnClassifierParallelGroup implements Classifier
{
    private List<?extends  Sample > dataSet;
    private int k;
    private ThreadPoolExecutor executor;
    private  int numThreads;
    private boolean parallelSort;

    public KnnClassifierParallelGroup(List<? extends Sample> dataSet, int k, ThreadPoolExecutor executor, int numThreads, boolean parallelSort,int factor) {
        this.dataSet = dataSet;
        this.k = k;
        this.executor = executor;
        this.numThreads = numThreads;
        this.parallelSort = parallelSort;
        this.dataSet = dataSet;
        this.k = k;
        numThreads = factor * ( Runtime.getRuntime().availableProcessors());
        System.out.println("number of threads : "+numThreads);
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(numThreads);
        this.parallelSort = parallelSort;
    }

    @Override
    public String classify(Sample sample) throws InterruptedException {
        Distance [] distances = new  Distance[dataSet.size()];
        CountDownLatch endController = new CountDownLatch(numThreads);
        int length = dataSet.size() / numThreads;
        int startIndex = 0, endIndex = length;
        for(int i=0; i< numThreads;i++)
        {
            GroupDistanceTask groupDistanceTask = new GroupDistanceTask(
              distances,
              startIndex,
              endIndex,
              sample,
              dataSet,
              endController
            );
            if( i < numThreads -2 ) {
                endIndex  = endIndex + length;
            }
            else  {
                endIndex = dataSet.size();
            }
            executor.execute( groupDistanceTask);

        }
        endController.await();
        if( parallelSort)
        {
            Arrays.parallelSort( distances );
        }
        else {
            Arrays.sort( distances);
        }
        Map<String, Integer> results = new HashMap<>();
        for(int i=0;i<k;i++) {
            Sample localExample = dataSet.get( distances[i].getIndex());
            String tag = localExample.getTag();
            results.merge(tag, 1, (a,b)->a+b);
        }
        return Collections.max( results.entrySet(), Map.Entry.comparingByKey()).getKey();


    }
    public void destroy()
    {
        executor.shutdown();
    }
}
