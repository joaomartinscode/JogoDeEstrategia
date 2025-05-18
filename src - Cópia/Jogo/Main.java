package Jogo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Dimensão do tabuleiro (largura altura): ");
        int largura = sc.nextInt(), altura = sc.nextInt();
        Tabuleiro tabuleiro = new Tabuleiro(largura, altura);

        System.out.print("Custo máximo do Exército 1: ");
        Exercito e1 = new Exercito("Exército 1", sc.nextInt());

        System.out.print("Custo máximo do Exército 2: ");
        Exercito e2 = new Exercito("Exército 2", sc.nextInt());

        System.out.println("Adicione unidades ao Exército 1:");
        criarUnidades(e1, sc);

        System.out.println("Adicione unidades ao Exército 2:");
        criarUnidades(e2, sc);

        Simulacao sim = new Simulacao(e1, e2, tabuleiro);

        int ronda = 1;
        while (!sim.fimDeJogo()) {
            System.out.println("\n--- Ronda " + (ronda++) + " ---");
            sim.executarPasso();
            sim.mostrarEstado();
        }

        System.out.println("\nFim da simulação.");
        sc.close();
    }

    private static void criarUnidades(Exercito exercito, Scanner sc) {
        while (true) {
            System.out.print("Tipo (guerreiro/arqueiro/feiticeiro ou sair): ");
            String tipo = sc.next();
            if (tipo.equalsIgnoreCase("sair")) break;

            System.out.print("Nome: ");
            String nome = sc.next();

            System.out.print("Custo, Vida, Ataque, Defesa, Alcance, X, Y, Velocidade: ");
            int custo = sc.nextInt(), vida = sc.nextInt(), ataque = sc.nextInt(), defesa = sc.nextInt();
            int alcance = sc.nextInt(), x = sc.nextInt(), y = sc.nextInt(), velocidade = sc.nextInt();

            UnidadeMilitar u = null;

            if (tipo.equalsIgnoreCase("guerreiro")) {
                System.out.print("Blindagem: ");
                u = new Guerreiro(nome, custo, vida, ataque, defesa, alcance, x, y, velocidade, sc.nextInt());
            } else if (tipo.equalsIgnoreCase("arqueiro")) {
                System.out.print("Armamento (arco/besta): ");
                u = new Arqueiro(nome, custo, vida, ataque, defesa, alcance, x, y, velocidade, sc.next());
            } else if (tipo.equalsIgnoreCase("feiticeiro")) {
                System.out.print("Máximo de feitiços: ");
                u = new Feiticeiro(nome, custo, vida, ataque, defesa, alcance, x, y, velocidade, sc.nextInt());
            }

            if (u != null) {
                if (!exercito.adicionar(u)) {
                    System.out.println("Custo excede o limite! Unidade não adicionada.");
                }
            }
        }
    }
}

