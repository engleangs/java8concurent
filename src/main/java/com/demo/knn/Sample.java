package com.demo.knn;

public class Sample {
    private double[] example;
    private String tag;
    public Sample(){

    }

    public Sample(double[] example, String tag) {
        this.example = example;
        this.tag = tag;
    }

    public double[] getExample() {
        return example;
    }

    public void setExample(double[] example) {
        this.example = example;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
