package org.edgar;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {
        String urlConexion = "jdbc:postgresql://db-dws.cqpz9tc1anof.us-east-1.rds.amazonaws.com";
        String usuario = "postgres";
        String password = "taller2014";

        try (Connection conexion = DriverManager.getConnection(urlConexion, usuario, password)) {
            try{
                conexion.setAutoCommit(false);

            } catch (Exception e) {

            }
        } catch (Exception e) {

        }
    }
}