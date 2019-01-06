package com.demo.knn;

import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.jdbc.JdbcConnection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlConnection {
    private JdbcConnection connection;
    public MySqlConnection(String username,String password,String url) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = (JdbcConnection) DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public int[] insertBatch(List<BankData> data,String table) throws SQLException {

        String sql = "INSERT INTO "+table+" (age,job,marital,education,`default`,balance,housing,loan,contact,day,month,duration,campaign,pdays,previous,poutcome,y)"
                +" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
       PreparedStatement preparedStatement =  connection.prepareStatement(sql);
        for (BankData bank: data)
        {
            int index = 1;
            preparedStatement.setInt(index++,bank.getAge());
            preparedStatement.setString(index++,bank.getJob());
            preparedStatement.setString(index++,bank.getMarital());
            preparedStatement.setString(index++,bank.getEducation());
            preparedStatement.setString(index++,bank.getDefaultItem());
            preparedStatement.setInt(index++,bank.getBalance());
            preparedStatement.setString(index++,bank.getHousing());
            preparedStatement.setString(index++,bank.getLoan());
            preparedStatement.setString(index++,bank.getContact());
            preparedStatement.setString(index++,bank.getDay());
            preparedStatement.setString(index++,bank.getMonth());
            preparedStatement.setString(index++,bank.getDuration());
            preparedStatement.setString(index++,bank.getCampaign());
            preparedStatement.setString(index++,bank.getPdays());
            preparedStatement.setString(index++,bank.getPrevious());
            preparedStatement.setString(index++,bank.getPoutcome());
            preparedStatement.setString(index++,bank.getY());
            preparedStatement.addBatch();
            preparedStatement.clearParameters();


        }
        int result[] = preparedStatement.executeBatch();
        return result;



    }
    public int[] insertBatch(List<BankData> data) throws SQLException {
        return insertBatch(data,"bank");
    }
    public void close() throws SQLException {
        connection.close();
    }
    public List<String> getListValueOfColumn(String column, String table) throws SQLException {
        String sql = " SELECT `"+column+"` FROM "+table +" GROUP BY `"+column+"`";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<String>result = new ArrayList<>();
        while (resultSet.next())
        {
             result.add(   resultSet.getString(1) );
        }
        return result;
    }



}
