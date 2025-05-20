package Jogo;

public interface UnidadeMilitarI {
    void moverUnidade(Tabuleiro tabuleiro);
    void atacarUnidade(UnidadeMilitar alvo);
    void defenderUnidade(int dano);
    boolean estadoUnidade();
    void printUnidade();
}
