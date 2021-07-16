package com.company;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        //Enter Login
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter user name default (root): ");
        String user =sc.nextLine();
        user = user.equals("") ? "root" : user;
        System.out.println();

        //Enter password
        System.out.print("Enter password default (empty):");
        String password = sc.nextLine().trim();
        System.out.println();

        //Create class's object of parameters for connection
        Properties props = new Properties();
        props.setProperty("user",user);
        props.setProperty("password",password);

        //Db connection
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC",props);

        //PreparedStatement
        PreparedStatement stmt = connection.prepareStatement("SElECT e.name, d.name AS 'department_name', e.salary FROM employees AS e JOIN departments AS d ON e.department_id=d.id WHERE salary>?");

        // Enter salary and start query
        System.out.print("Enter salary: ");
        String salary =sc.nextLine();

        stmt.setDouble(1,Double.parseDouble(salary));
        ResultSet rs = stmt.executeQuery();

        while(rs.next()){
            System.out.println(rs.getString("name")+ " "+ rs.getString("department_name")+" "+ rs.getString("salary"));

        }
        connection.close();
    }

}
