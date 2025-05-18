package Jogo;

public class Guerreiro extends UnidadeMilitar {
    private int blindagem;

    public Guerreiro(String nome, int custo, int vida, int ataque, int defesa, int alcance, int x, int y, int velocidade, int blindagem) {
        super(nome, custo, vida, ataque, defesa, alcance, x, y, velocidade);
        this.blindagem = blindagem;
    }

    @Override
    public void receberDano(int dano) {
        int danoFinal = Math.max(dano - blindagem, 0);
        vida -= danoFinal;
    }

    @Override
    public String toString() {
        return super.toString() + " | Blindagem: " + blindagem;
    }
}

