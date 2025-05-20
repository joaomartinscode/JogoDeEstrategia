package Jogo;

import Utils.InputValidation;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class UnidadeMilitar implements UnidadeMilitarI {
    private static int contadorGlobal = 0;
    private static final ArrayList<Integer> indices = new ArrayList<>();

    protected String nome;
    protected int custoProd, pontosVida, pontosAtaque, pontosDefesa, alcance;
    protected int posX, posY, velocidade;
    protected int id;

    public UnidadeMilitar(Scanner scanner) {
        this.id = gerarId();

        // Coleta dados comuns
        custoProd = InputValidation.validateIntGT0(scanner, "Custo de produção: ");
        pontosVida = InputValidation.validateIntGT0(scanner, "Pontos de vida: ");
        pontosAtaque = InputValidation.validateIntGT0(scanner, "Pontos de ataque: ");
        pontosDefesa = InputValidation.validateIntGT0(scanner, "Pontos de defesa: ");
        alcance = InputValidation.validateIntGT0(scanner, "Alcance: ");
        velocidade = InputValidation.validateIntGT0(scanner, "Velocidade: ");
        posX = 0;
        posY = 0;
    }

    private static int gerarId() {
        if (!indices.isEmpty()) {
            int menorIndex = indices.getFirst();
            for (int i = 1; i < indices.size(); i++) {
                if (indices.get(i) < menorIndex) {
                    menorIndex = indices.get(i);
                }
            }
            indices.remove(Integer.valueOf(menorIndex));
            return menorIndex;
        }
        return ++contadorGlobal;
    }

    public static void liberarId(int id) {
        indices.add(id);
    }

    protected void setNomeAuto(String tipo) {
        this.nome = tipo + id;
    }

    @Override
    public void moverUnidade(Tabuleiro tabuleiro) {
        int deslocaX = (int) (Math.random() * (2 * velocidade + 1)) - velocidade;
        int deslocaY = (int) (Math.random() * (2 * velocidade + 1)) - velocidade;

        int novaX = posX + deslocaX;
        int novaY = posY + deslocaY;

        // Ajustar para bordas do tabuleiro
        novaX = Math.max(0, Math.min(novaX, tabuleiro.getLargura() - 1));
        novaY = Math.max(0, Math.min(novaY, tabuleiro.getAltura() - 1));

        posX = novaX;
        posY = novaY;
        System.out.println(nome + " moveu-se para (" + posX + ", " + posY + ")");
    }

    @Override
    public void atacarUnidade(UnidadeMilitar alvo) {
        int distancia = Math.abs(this.posX - alvo.posX) + Math.abs(this.posY - alvo.posY);
        if (distancia <= this.alcance) {
            System.out.println(this.nome + " está a atacar o " + alvo.nome);
            alvo.defenderUnidade(this.pontosAtaque);
        } else {
            System.out.println(this.nome + " não está no alcance para atacar " + alvo.nome);
        }
    }

    @Override
    public boolean estadoUnidade() {
        return pontosVida > 0;
    }

    public abstract void defenderUnidade(int dano);
    public abstract void printUnidade();

}
