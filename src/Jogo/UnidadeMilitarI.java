package Jogo;

public interface UnidadeMilitarI {
    void moverUnidade();
    void atacarUnidade(UnidadeMilitar alvo);
    void defenderUnidade(int dano);
    boolean estadoUnidade();
    void printUnidade();
}
