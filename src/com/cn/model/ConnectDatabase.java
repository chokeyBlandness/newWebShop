package com.cn.model;

import java.sql.*;

public class ConnectDatabase {
    private Connection connection;
    private PreparedStatement preparedStatement;
    public ConnectDatabase(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop?" +
                    "user=root&password=mysql");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createMemberTable(){
        try {
            preparedStatement=connection.prepareStatement("CREATE TABLE member(" +
                    "account VARCHAR (20)," +
                    "password VARCHAR (20))");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertNewMember(String account,String password){
        try {
            preparedStatement=connection.prepareStatement("INSERT INTO member VALUES (?,?)");
            preparedStatement.setString(1,account);
            preparedStatement.setString(2,password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ResultSet searchMember(String account){
        try {
            preparedStatement=connection.prepareStatement("SELECT * FROM member WHERE " +
                    "account=?");
            preparedStatement.setString(1,account);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void createMerchandiseTable(){
        try {
            preparedStatement=connection.prepareStatement("CREATE TABLE merchandises(" +
                    "id VARCHAR (20) PRIMARY KEY ," +
                    "name VARCHAR (20) NOT NULL ," +
                    "price DOUBLE NOT NULL ," +
                    "source VARCHAR (40))");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ResultSet readAllMerchandises(){
        try {
            preparedStatement=connection.prepareStatement("SELECT * FROM merchandises");
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public ResultSet searchMerchandiseById(String id){
        try {
            preparedStatement=connection.prepareStatement("SELECT * FROM " +
                    "merchandises WHERE id=?");
            preparedStatement.setString(1,id);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Merchandise changeResultToMerchandise(ResultSet resultSet){
        Merchandise newMerchandise=new Merchandise();
        try {
        newMerchandise.setId(resultSet.getString("id"));
        newMerchandise.setName(resultSet.getString("name"));
        newMerchandise.setPrice(resultSet.getDouble("price"));
            newMerchandise.setPictureSource(resultSet.getString("source"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newMerchandise;
    }
    public void closeConnection(){
        try {
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
