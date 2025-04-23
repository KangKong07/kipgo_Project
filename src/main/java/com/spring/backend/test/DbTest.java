package com.spring.backend.test;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbTest {
    public static void main(String[] args) {
        try {
            String url = "jdbc:mariadb://158.247.202.97:3306/kipgo";
            String user = "kipgo_user";
            String password = "kipgo123!@#";

            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("DB 연결 성공!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
