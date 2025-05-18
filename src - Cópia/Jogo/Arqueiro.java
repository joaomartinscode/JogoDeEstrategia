package Jogo;

public class Arqueiro extends UnidadeMilitar {
    private String armamento;

    public Arqueiro(String nome, int custo, int vida, int ataque, int defesa, int alcance, int x, int y, int velocidade, String armamento) {
        super(nome, custo, vida, ataque, defesa, alcance, x, y, velocidade);
        this.armamento = armamento;
    }

    @Override
    public void receberDano(int dano) {
        vida -= dano;
    }

    @Override
    public String toString() {
        return super.toString() + " Armamento: " + armamento;
    }
}
