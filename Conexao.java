import java.sql.Connection;
import java.sql.DriverManager;
 
// Classe responsável por conectar ao banco de dados MySQL
public class Conexao {
 
    public static Connection conectar() {
        try {
            String url = "jdbc:mysql://localhost:3306/AutoPeca";
            String usuario = "root";
            String senha = "";
            return DriverManager.getConnection(url, usuario, senha);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}