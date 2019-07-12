package utilities;

import java.sql.*;
import java.util.*;

public class mysqlutils {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static void establishConnection() throws SQLException{
        connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/aidinaDBaiu2z?serverTimezone="+
                        TimeZone.getDefault().getID(),
                "aidinaDBaiu2z","7grmu2PXgd");
    }

    public static List<Map<String, Object>> runSqlQuery(String query) throws SQLException{
        statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        resultSet=statement.executeQuery(query);

        ResultSetMetaData resultSetMetaData=resultSet.getMetaData();

        int rowCount=resultSetMetaData.getColumnCount();
        List<Map<String, Object>> dbData=new ArrayList<>();

        while(resultSet.next()){
            Map<String, Object> rowMap=new HashMap<>();

            for(int i=1; i<=rowCount; i++){
                rowMap.put(resultSetMetaData.getColumnName(i), resultSet.getObject(resultSetMetaData.getColumnName(i)));
            }
            dbData.add(rowMap);
        }
        return dbData;
    }

    public static void closeConnections() throws SQLException{
        if(connection!=null){
            connection.close();
        }

        if(statement!=null){
            statement.close();
        }

        if (resultSet!=null){
            resultSet.close();
        }
    }

    public static int rowCount(String query) throws SQLException{
        statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        resultSet=statement.executeQuery(query);
        ResultSet resultSet1=resultSet;
        resultSet1.last();
        int numOfRows=resultSet1.getRow();
        return numOfRows;
    }
}
