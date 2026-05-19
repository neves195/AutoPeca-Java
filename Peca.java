// Classe que representa uma Peça Automotiva
public class Peca {
 
    private String nome;
    private String marca;
    private double preco;
    private int quantidade;
 
    // Construtor
    public Peca(String nome, String marca, double preco, int quantidade) {
        this.nome = nome;
        this.marca = marca;
        this.preco = preco;
        this.quantidade = quantidade;
    }
 
    // Getters
    public String getNome() { return nome; }
    public String getMarca() { return marca; }
    public double getPreco() { return preco; }
    public int getQuantidade() { return quantidade; }
}
 