package com.demo.knn;

import com.sun.deploy.util.ArrayUtil;
import com.sun.istack.internal.NotNull;
import com.sun.tools.javac.util.ArrayUtils;

import java.util.*;
import java.util.stream.DoubleStream;

public class BankData {
    private int age;
    private String job;
    private String marital;
    private String education;
    private String defaultItem;
    private int balance;
    private String housing;
    private String loan;
    private String contact;
    private String day;
    private String month;
    private String duration;
    private String campaign;
    private String pdays;
    private String previous;
    private String poutcome;
    private String y;
    private static enum DataIndex{
        Age,
        Job,
        Marital,
        Education,
        DefaultItem,
        Balance,
        Housing,
        Loan,
        Contact,
        Day,
        Month,
        Duration,
        Campaign,
        Pdays,
        Previous,
        Poutcome,
        Y,

    }
    public BankData(){

    }
    public static final String[] PREDEFINED_JOB = {"admin.","unknown","unemployed","management","housemaid","entrepreneur","student",
            "blue-collar","self-employed","retired","technician","services"};
    public static final  String[] PREDEFINED_MARITAL = {"married","divorced","single"};
    public static final String[] PREDEFINED_EDUCATION = {"unknown","secondary","primary","tertiary"};
    public static final String[] PREDEFINED_CONTACT = {"unknown","telephone","cellular"};
    public static final String[] PREDFINED_PREVIOUS_OUTCOME = {"unknown","other","failure","success"};
    public static final String[] PREDEFINED_MONTH = {
            "jan", "feb", "mar", "apr",
            "may", "jun", "jul", "aug",
            "oct", "sep", "nov", "dec",

    };


    public BankData(int age, String job, String marital, String education, String defaultItem, int balance, String housing, String loan, String contact, String day, String month, String duration, String campaign, String pdays, String previous, String poutcome, String y) {
        this.age = age;
        this.job = job;
        this.marital = marital;
        this.education = education;
        this.defaultItem = defaultItem;
        this.balance = balance;
        this.housing = housing;
        this.loan = loan;
        this.contact = contact;
        this.day = day;
        this.month = month;
        this.duration = duration;
        this.campaign = campaign;
        this.pdays = pdays;
        this.previous = previous;
        this.poutcome = poutcome;
        this.y = y;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getMarital() {
        return marital;
    }

    public void setMarital(String marital) {
        this.marital = marital;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getDefaultItem() {
        return defaultItem;
    }

    public void setDefaultItem(String defaultItem) {
        this.defaultItem = defaultItem;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getHousing() {
        return housing;
    }

    public void setHousing(String housing) {
        this.housing = housing;
    }

    public String getLoan() {
        return loan;
    }

    public void setLoan(String loan) {
        this.loan = loan;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCampaign() {
        return campaign;
    }

    public void setCampaign(String campaign) {
        this.campaign = campaign;
    }

    public String getPdays() {
        return pdays;
    }

    public void setPdays(String pdays) {
        this.pdays = pdays;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getPoutcome() {
        return poutcome;
    }

    public void setPoutcome(String poutcome) {
        this.poutcome = poutcome;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public Double[] getJobArray()
    {
        Double[] result = new Double[PREDEFINED_JOB.length];
        return assingArr( PREDEFINED_JOB , job, result);

    }
    public Double[] getMaritalArray(){
        Double[] result = new Double[PREDEFINED_MARITAL.length];
        return assingArr( PREDEFINED_MARITAL, marital,result);
    }
    public Double[] getEducationArray(){
        Double[] result = new Double[PREDEFINED_EDUCATION.length];
        return assingArr(PREDEFINED_EDUCATION,education,result);
    }
    public Double[] getContactArray(){
        Double[] result = new Double[PREDEFINED_CONTACT.length];
        return assingArr(PREDEFINED_CONTACT , contact, result);

    }
    public Double[] getPreviousOutcomeArray(){
        Double[]result = new Double[PREDFINED_PREVIOUS_OUTCOME.length];
        return assingArr( PREDFINED_PREVIOUS_OUTCOME, poutcome,result);

    }
    public Double[] getMonthArray(){
        Double[] result = new Double[PREDEFINED_MONTH.length];
        return assingArr( PREDEFINED_MONTH, month,result);
    }

    private Double[] assingArr(String[] items,String value,Double[]arr) {
        int index = 0;
        for(String item:items) {
            arr[index] = 0.0d;
            if(value.equals( item)) {
                arr[index]= 1.0d;
            }
            index++;
        }
        return arr;
    }

    public BankData parse(@NotNull String text)
    {
        if(text.equals(""))throw new IllegalArgumentException("Invalid text ");
        String[] items = text.split(";");
        this.age = Integer.parseInt( items[ DataIndex.Age.ordinal()]);
        this.job = items[DataIndex.Job.ordinal()].replace("\"","");
        this.marital = items[DataIndex.Marital.ordinal()].replace("\"","");
        this.education = items[DataIndex.Education.ordinal()].replace("\"","");
        this.defaultItem = items[DataIndex.DefaultItem.ordinal()].replace("\"","");
        this.balance =  Integer.parseInt(items[DataIndex.Balance.ordinal()]);
        this.housing = items[DataIndex.Housing.ordinal()].replace("\"","");
        this.loan = items[DataIndex.Loan.ordinal()].replace("\"","");
        this.contact = items[DataIndex.Contact.ordinal()].replace("\"","");
        this.day = items[DataIndex.Day.ordinal()].replace("\"","");
        this.month = items[DataIndex.Month.ordinal()].replace("\"","");
        this.duration = items[DataIndex.Duration.ordinal()].replace("\"","");
        this.campaign = items[DataIndex.Campaign.ordinal()].replace("\"","");
        this.pdays = items[DataIndex.Pdays.ordinal()].replace("\"","");
        this.previous = items[DataIndex.Previous.ordinal()].replace("\"","");
        this.poutcome = items[DataIndex.Poutcome.ordinal()].replace("\"","");
        this.y = items[DataIndex.Y.ordinal()].replace("\"","");
        return this;

    }

    public Sample toSample()
    {
        String tag = "tag"+System.currentTimeMillis();
        List<Double> data = new ArrayList<Double>(){
            {add((double)age);}//age 1
            {addAll(Arrays.asList(getJobArray()));}// job 12
            { addAll(Arrays.asList(getMaritalArray()));}// marital 3
            { addAll(Arrays.asList(getEducationArray()));}//education 4
            { add(getDefaultItem().equals("yes")?1.0d:0);} //default 1
            { add( (double)(getBalance()));}//balance 1
            { add( getHousing().equals("yes")?1.0d:0);}//housing 1
            { add( getLoan().equals("yes")?1.0d:0);}//loan 1
            { addAll(Arrays.asList(getContactArray()));}//contact 3
            { add(Double.parseDouble(getDay()));}//day 1
            { addAll(Arrays.asList(getMonthArray()));}//month 12
            { add( Double.parseDouble(getDuration()));}//duration 1
            { add(Double.parseDouble( getCampaign()));}//campaign 1
            { add( Double.parseDouble( getPdays()));}//days 1
            { add( Double.parseDouble( getPrevious()));}//previous 1
            { addAll( Arrays.asList( getPreviousOutcomeArray()));}//outcome 4
            { add(getY().equals("yes")?1.0d:0);}

        };
        double[] items = data.stream().flatMapToDouble(it-> DoubleStream.of(it.doubleValue())).toArray();
        Sample sample = new Sample( items ,tag);
        return sample;
    }

}
