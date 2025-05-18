package Jogo;

public abstract class UnidadeMilitar implements Combatente {
    protected String nome;
    protected int custo;
    protected int vida;
    protected int ataque;
    protected int defesa;
    protected int alcance;
    protected int x;
    protected int y;
    protected int velocidade;

    public UnidadeMilitar(String nome, int custo, int vida, int ataque, int defesa, int alcance, int x, int y, int velocidade) {
        this.nome = nome;
        this.custo = custo;
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.alcance = alcance;
        this.x = x;
        this.y = y;
        this.velocidade = velocidade;
    }

    // Implementação comum para todas as unidades
    public void mover(Tabuleiro tabuleiro) {
        int direcao = (int) (Math.random() * 4);  // 0: direita, 1: esquerda, 2: cima, 3: baixo

        switch (direcao) {
            case 0:  // Mover para a direita
                if (x + velocidade <= tabuleiro.getMaxX()) x += velocidade;
                break;
            case 1:  // Mover para a esquerda
                if (x - velocidade >= 0) x -= velocidade;
                break;
            case 2:  // Mover para cima
                if (y - velocidade >= 0) y -= velocidade;
                break;
            case 3:  // Mover para baixo
                if (y + velocidade <= tabuleiro.getMaxY()) y += velocidade;
                break;
        }
    }

    public int getCusto() {
        return custo;
    }

    public abstract void receberDano(int dano);

    public boolean estaViva() {
        return vida > 0;
    }

    public double distanciaPara(UnidadeMilitar outra) {
        return Math.sqrt(Math.pow(this.x - outra.x, 2) + Math.pow(this.y - outra.y, 2));
    }

    public String toString() {
        return "Unidade " + nome + " - Vida: " + vida + " Custo: " + custo + " Ataque: " + ataque + " Defesa: " + defesa;
    }
}
