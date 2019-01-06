package com.demo.knn;

public interface Classifier {
    String classify(Sample sample) throws InterruptedException;
}
