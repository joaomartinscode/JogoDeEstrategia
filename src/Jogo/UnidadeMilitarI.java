package Jogo;

public interface UnidadeMilitarI {
    void moverUnidade();
    void atacarUnidade();
    void defenderUnidade(int dano);
    boolean estadoUnidade();
    void printUnidade();
}
