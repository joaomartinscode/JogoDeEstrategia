package Jogo;

public class Feiticeiro extends UnidadeMilitar {
    private int maxFeiticos;

    public Feiticeiro(String nome, int custo, int vida, int ataque, int defesa, int alcance, int x, int y, int velocidade, int maxFeiticos) {
        super(nome, custo, vida, ataque, defesa, alcance, x, y, velocidade);
        this.maxFeiticos = maxFeiticos;
    }

    @Override
    public void receberDano(int dano) {
        vida -= dano;
    }

    @Override
    public String toString() {
        return super.toString() + " Máximo de Feitiços: " + maxFeiticos;
    }
}

