package com.demo;

import com.demo.knn.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) throws IOException, SQLException {
        System.out.println("application init");
        Data data = Data.getInstance();
        data.setPath("bank/bank-full.csv");
        System.out.println("begin to load data");
        List<BankData>banks = data.load();
        System.out.println("finish load from csv");
        BankMetaData bankMetaData = BankMetaData.getInstance();

        MySqlConnection mySqlConnection = new MySqlConnection("root","root","jdbc:mysql://localhost:3306/db_exp");
        System.out.println("begin to insert batch");
        //mySqlConnection.insertBatch(banks,"bank_full");
        System.out.println("finish insert batch");
        bankMetaData.loadMetaData(mySqlConnection );
        System.out.println("load data from db");
        List<Sample>samples = banks.stream().map(it->it.toSample()).collect(Collectors.toList());
        System.out.println("samples : "+samples.size());
        KnnClassifier knnClassifier = new KnnClassifier(samples,10);
        Random random = new Random();
        Sample sample = samples.get( random.nextInt( samples.size()));
        long startSequential = System.currentTimeMillis();
        String found = knnClassifier.classify( sample);
        System.out.println("sample : "+sample.getTag() +" - "+new Date() +" take "+(System.currentTimeMillis()-startSequential)+" ms");
        System.out.println("classify :" +found +" @ "+new Date());


        KnnClassifierParallelIndividual knnClassifierParallelIndividual = new KnnClassifierParallelIndividual(samples,10,2,true);
        try {
            System.out.println("begin parallel : "+new Date());
            long startParallel = System.currentTimeMillis();
            System.out.println("classify found :"+knnClassifierParallelIndividual.classify(sample) + " @"+new Date() +" take "+(System.currentTimeMillis()- startParallel)+" ms");
           knnClassifierParallelIndividual.destroy();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
