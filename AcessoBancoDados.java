import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
 
// Classe responsável por todas as operações no banco de dados
public class AcessoBancoDados {
 
    // INCLUSÃO - Cadastrar nova peça
    public void inserir(Peca peca) {
        try {
            Connection conectar = Conexao.conectar();
            String sql = "INSERT INTO pecas (nome, marca, preco, quantidade) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conectar.prepareStatement(sql);
            ps.setString(1, peca.getNome());
            ps.setString(2, peca.getMarca());
            ps.setDouble(3, peca.getPreco());
            ps.setInt(4, peca.getQuantidade());
            ps.executeUpdate();
            conectar.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    // CONSULTA - Listar todas as peças
    public ArrayList<Peca> listar() {
        ArrayList<Peca> lista = new ArrayList<>();
        try {
            Connection conectar = Conexao.conectar();
            String sql = "SELECT nome, marca, preco, quantidade FROM pecas";
            Statement st = conectar.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                lista.add(new Peca(
                    rs.getString("nome"),
                    rs.getString("marca"),
                    rs.getDouble("preco"),
                    rs.getInt("quantidade")
                ));
            }
            conectar.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
 
    // CONSULTA PERSONALIZADA - Buscar peça por nome
    public ArrayList<Peca> buscarPorNome(String nomeBusca) {
        ArrayList<Peca> lista = new ArrayList<>();
        try {
            Connection conectar = Conexao.conectar();
            String sql = "SELECT nome, marca, preco, quantidade FROM pecas WHERE nome LIKE ?";
            PreparedStatement ps = conectar.prepareStatement(sql);
            ps.setString(1, "%" + nomeBusca + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Peca(
                    rs.getString("nome"),
                    rs.getString("marca"),
                    rs.getDouble("preco"),
                    rs.getInt("quantidade")
                ));
            }
            conectar.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
 
    // ALTERAÇÃO - Atualizar peça pelo nome original
    public void alterar(String nomeOriginal, Peca peca) {
        try {
            Connection conectar = Conexao.conectar();
            String sql = "UPDATE pecas SET nome=?, marca=?, preco=?, quantidade=? WHERE nome=?";
            PreparedStatement ps = conectar.prepareStatement(sql);
            ps.setString(1, peca.getNome());
            ps.setString(2, peca.getMarca());
            ps.setDouble(3, peca.getPreco());
            ps.setInt(4, peca.getQuantidade());
            ps.setString(5, nomeOriginal);
            ps.executeUpdate();
            conectar.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    // EXCLUSÃO - Excluir peça pelo nome
    public void excluir(String nome) {
        try {
            Connection conectar = Conexao.conectar();
            String sql = "DELETE FROM pecas WHERE nome=?";
            PreparedStatement ps = conectar.prepareStatement(sql);
            ps.setString(1, nome);
            ps.executeUpdate();
            conectar.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}