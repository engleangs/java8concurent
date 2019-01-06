package com.demo.knn;

import java.sql.SQLException;
import java.util.List;

public class BankMetaData {
    public List<String> getJobValue()
    {
        return jobValue;
    }

    private List<String> jobValue;

    private List<String>maritalValue;
    private List<String>defaultValue;

    public List<String> getMaritalValue() {
        return maritalValue;
    }

    public List<String> getDefaultValue() {
        return defaultValue;
    }
    private List<String>housingValue;
    private List<String>contactValue;
    private List<String>campaignValue;
    private List<String>pDaysValue;
    private List<String>pOutCome;
    private List<String>yValue;


    public List<String> getHousingValue() {
        return housingValue;
    }

    public List<String> getContactValue() {
        return contactValue;
    }

    public List<String>getCampaignValue(){
        return campaignValue;
    }

    public List<String> getpDaysValue() {
        return pDaysValue;
    }

    public List<String> getpOutCome() {
        return pOutCome;
    }

    public List<String> getyValue() {
        return yValue;
    }

    private static class BankMetaDataInner{
        private static BankMetaData INSTANCE = new BankMetaData();
    }
    public static BankMetaData getInstance(){
        return BankMetaDataInner.INSTANCE;
    }

    public void loadMetaData(MySqlConnection mySqlConnection) throws SQLException
    {
           maritalValue = mySqlConnection.getListValueOfColumn("marital","bank_full");
           jobValue =     mySqlConnection.getListValueOfColumn("job","bank_full");
           defaultValue = mySqlConnection.getListValueOfColumn("default","bank_full");
           housingValue = mySqlConnection.getListValueOfColumn("housing","bank_full");
           contactValue = mySqlConnection.getListValueOfColumn("contact","bank_full");
           campaignValue = mySqlConnection.getListValueOfColumn("campaign","bank_full");
           pDaysValue = mySqlConnection.getListValueOfColumn("pdays","bank_full");
           pOutCome = mySqlConnection.getListValueOfColumn("poutcome","bank_full");
           yValue = mySqlConnection.getListValueOfColumn("y","bank_full");




    }



}
