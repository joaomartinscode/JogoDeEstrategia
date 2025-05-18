package Jogo;

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
        System.out.print("Custo de produção: ");
        custoProd = Integer.parseInt(scanner.nextLine());

        System.out.print("Pontos de vida: ");
        pontosVida = Integer.parseInt(scanner.nextLine());

        System.out.print("Pontos de ataque: ");
        pontosAtaque = Integer.parseInt(scanner.nextLine());

        System.out.print("Pontos de defesa: ");
        pontosDefesa = Integer.parseInt(scanner.nextLine());

        System.out.print("Alcance: ");
        alcance = Integer.parseInt(scanner.nextLine());

        System.out.print("Velocidade: ");
        velocidade = Integer.parseInt(scanner.nextLine());

        System.out.print("Posição X: ");
        posX = Integer.parseInt(scanner.nextLine());

        System.out.print("Posição Y: ");
        posY = Integer.parseInt(scanner.nextLine());
    }

    private static int gerarId() {
        if (!indices.isEmpty()) {
            int menorIndice = indices.getFirst();
            for (int i = 1; i < indices.size(); i++) {
                if (indices.get(i) < menorIndice) {
                    menorIndice = indices.get(i);
                }
            }
            indices.remove(Integer.valueOf(menorIndice));
            return menorIndice;
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
    public void moverUnidade() {
        posX += (int)(Math.random() * (velocidade + 1));
        posY += (int)(Math.random() * (velocidade + 1));
    }

    @Override
    public void atacarUnidade(UnidadeMilitar alvo) {
        int distancia = Math.abs(this.posX - alvo.posX) + Math.abs(this.posY - alvo.posY);
        if (distancia <= this.alcance) {
            System.out.println(this.nome + " atacando " + alvo.nome);
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
