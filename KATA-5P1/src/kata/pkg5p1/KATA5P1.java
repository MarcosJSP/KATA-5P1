package kata.pkg5p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class KATA5P1 {
    public static void main(String[] args) {
        String baseDatos = "KATA5.db";
        String tabla = "PEOPLE";
        createNewTable(baseDatos,tabla);
    }
    
        private static void createNewTable(String baseDatos, String tabla) {
        String url = "jdbc:sqlite:"+ baseDatos;
        String sql = "CREATE TABLE IF NOT EXISTS " + tabla + " (\n"
                    + " Id integer PRIMARY KEY AUTOINCREMENT,\n"
                    + " Mail text NOT NULL);";
        Connection con = null;
        try {
            con = DriverManager.getConnection(url);
            Statement stmt = con.createStatement();
            stmt.execute(sql);
            System.out.println("Tabla creada");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (con != null) con.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
