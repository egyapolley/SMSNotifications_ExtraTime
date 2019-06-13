package com.dbutil;


import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DButility {

    private static DataSource datasource;

    static {
        try {
            InitialContext initialContext = new InitialContext();
            datasource=(DataSource) initialContext.lookup("jdbc/MysqlSMS");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }



    public static  String getMessage(int id)
    {

           // InitialContext initiContext();
        //DataSource datasource = (DataSource) initialContext.lookup("jdbc/MysqlSMS");alContext=new Initial
            try (Connection connection = datasource.getConnection()) {
                if (connection !=null){
                    System.out.println("connection SUCCESS");
                    String sql="select MessageBody from Messages where id=?";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1,id);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next())
                    {
                        String messageBody = resultSet.getString("MessageBody");
                        if (messageBody !=null) return messageBody;
                    }
                    preparedStatement.close();
                    resultSet.close();


                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        return null;
    }


    public static boolean insert(String code, String phoneContact, String surflineNumber,String body, String status) {
        PreparedStatement preparedStatement=null;
        try (Connection connection = datasource.getConnection()) {

            if (connection!=null)
            {
                String sql="insert into MessagesExtraTime"+"(message, code, phoneContact,surflineMsisdn,MessageStatus) values(?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,body);
                preparedStatement.setString(2,code);
                preparedStatement.setString(3,phoneContact);
                preparedStatement.setString(4,surflineNumber);
                preparedStatement.setString(5,status);
                int i = preparedStatement.executeUpdate();
                if (i==1) return true;
            }

            }
        catch (SQLException e) {
            e.printStackTrace();
            return false;

        }finally {
            if (preparedStatement!=null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }
}
