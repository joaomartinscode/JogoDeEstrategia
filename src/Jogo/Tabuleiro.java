package Jogo;

public class Tabuleiro {
    private int largura;
    private int altura;

    public Tabuleiro(int largura, int altura) {
        this.largura = largura;
        this.altura = altura;
    }

    public boolean posicaoValida(int x, int y) {
        return x >= 0 && x < largura && y >= 0 && y < altura;
    }

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }
}



