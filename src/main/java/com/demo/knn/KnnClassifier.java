package com.demo.knn;

import java.util.*;

public class KnnClassifier {
    private List<? extends Sample>dataSet;
    private int k;
    public KnnClassifier(List<? extends  Sample> dataSet, int k)
    {
     this.dataSet = dataSet;
     this.k = k;
    }
    public String classify(Sample sample)
    {
        Distance[] distances = new Distance[ dataSet.size() ];
        int index = 0;
        for( Sample localExample : dataSet) {
            distances[index] = new Distance(index,EuclideanDistanceCalculator.calculate(localExample,sample));
            index++;

        }
        Arrays.sort(distances);
        Map<String, Integer>results = new HashMap<>();
        for(int i=0;i<k;i++) {
            Sample localExample = dataSet.get( distances[i].getIndex());
            String tag = localExample.getTag();
            results.merge(tag, 1, (a,b)->a+b);
        }
        return Collections.max( results.entrySet(), Map.Entry.comparingByKey()).getKey();

    }
}
