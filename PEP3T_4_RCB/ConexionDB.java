package PEP3T_4_RCB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static final String ADD_DRIVER = "org.mariadb.jdbc.Driver";
    private static final String URL_CONEXION = "jdbc:mariadb://localhost:3306/PUNTU4";
    private Connection conexBd;

    public void conexiondb(){
        final String usuario = "root";
        final String contrasenia = "dam2223";

        try {
            Class.forName(ADD_DRIVER);
            conexBd = DriverManager.getConnection(URL_CONEXION, usuario, contrasenia);


        } catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Connection getConexBd() {
        return conexBd;
    }
}
