package kata.pkg5p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class KATA5P1 {
    public static void main(String[] args) {
        String baseDatos = "KATA5.db";
        String tabla = "EMAIL";
        String txt = "email.txt";
        //printDbContent(baseDatos, tabla);
        //createNewTable(baseDatos, tabla);
        MailListReader mailListReader = new MailListReader();
        List<String> read = mailListReader.read(txt);
        for (String email : read) {
            insert(baseDatos,tabla,email);
        }
    }

    private static void printDbContent(String baseDatos,String tabla) {
        String url = "jdbc:sqlite:" + baseDatos;
        String sql = "select * from " + tabla;
        Connection con = null;
        try {
            //establecemos conexión con la base de datos
            con = DriverManager.getConnection(url);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            //recorremos registros de la tabla
            while(rs.next()){
                System.out.println(rs.getInt("id") + "\t" +
                                    rs.getString("Name") + "\t" +
                                    rs.getString("Apellidos") + "\t" +
                                    rs.getString("Departamento"));
            }
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
    
    private static void insert(String baseDatos, String tabla, String email) {
        String url = "jdbc:sqlite:" + baseDatos;
        String sql = "INSERT INTO "+ tabla +"(Mail) VALUES(?)";
        Connection con = null;
        try {
            con = DriverManager.getConnection(url);
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.executeUpdate();
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
