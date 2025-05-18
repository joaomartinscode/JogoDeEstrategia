package Jogo;

public interface Combatente {
    void mover(Tabuleiro tabuleiro);  // Método de movimentação
    void receberDano(int dano);       // Método para receber dano
    boolean estaViva();              // Verifica se a unidade ainda está viva
}
