package Jogo;

public class Simulacao {
    private Exercito e1, e2;
    private Tabuleiro tabuleiro;

    public Simulacao(Exercito e1, Exercito e2, Tabuleiro tabuleiro) {
        this.e1 = e1;
        this.e2 = e2;
        this.tabuleiro = tabuleiro;
    }

    public void executarPasso() {
        mover(e1);
        mover(e2);
        atacar(e1, e2);
        atacar(e2, e1);
    }

    private void mover(Exercito e) {
        for (UnidadeMilitar u : e.getUnidades()) {
            if (u.estaViva()) u.mover(tabuleiro);
        }
    }

    private void atacar(Exercito atacantes, Exercito defensores) {
        for (UnidadeMilitar at : atacantes.getUnidades()) {
            if (!at.estaViva()) continue;
            for (UnidadeMilitar def : defensores.getUnidades()) {
                if (!def.estaViva()) continue;
                if (at.distanciaPara(def) <= at.alcance) {
                    def.receberDano(at.ataque);
                }
            }
        }
    }

    public void mostrarEstado() {
        System.out.println("Estado do Exército 1:");
        e1.listar();
        System.out.println("Estado do Exército 2:");
        e2.listar();
    }

    public boolean fimDeJogo() {
        return e1.estaDerrotado() || e2.estaDerrotado();
    }
}
