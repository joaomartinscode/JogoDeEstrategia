package Jogo;

import java.util.Random;
import java.util.Scanner;

public class Simulacao {
    private static int maxX, maxY;
    private static Random random = new Random();

    public static void simularCombate(Exercito ex1, Exercito ex2, Scanner scanner) {
        System.out.print("Tamanho máximo do tabuleiro (X): ");
        maxX = Integer.parseInt(scanner.nextLine());

        System.out.print("Tamanho máximo do tabuleiro (Y): ");
        maxY = Integer.parseInt(scanner.nextLine());

        System.out.print("Deseja posicionar manualmente as unidades? (s/n): ");
        boolean manual = scanner.nextLine().trim().equalsIgnoreCase("s");

        posicionarUnidades(ex1, scanner, manual);
        posicionarUnidades(ex2, scanner, manual);

        System.out.print("Número de passos da simulação: ");
        int passos = Integer.parseInt(scanner.nextLine());

        for (int passo = 1; passo <= passos; passo++) {
            System.out.println("\n--- Passo " + passo + " ---");

            moverUnidades(ex1);
            moverUnidades(ex2);

            interacoes(ex1, ex2);

            imprimirEstado(ex1, "Exército 1");
            imprimirEstado(ex2, "Exército 2");

            if (ex1.estaDerrotado()) {
                System.out.println("\nExército 1 foi derrotado!");
                break;
            } else if (ex2.estaDerrotado()) {
                System.out.println("\nExército 2 foi derrotado!");
                break;
            }
        }
    }

    private static void posicionarUnidades(Exercito ex, Scanner scanner, boolean manual) {
        for (UnidadeMilitar um : ex.getTodasUnidades()) {
            if (um == null) continue;

            if (manual) {
                System.out.println("Posicionando unidade: " + um.nome);
                System.out.print("Posição X (0 a " + maxX + "): ");
                um.posX = Integer.parseInt(scanner.nextLine());

                System.out.print("Posição Y (0 a " + maxY + "): ");
                um.posY = Integer.parseInt(scanner.nextLine());
            } else {
                um.posX = random.nextInt(maxX + 1);
                um.posY = random.nextInt(maxY + 1);
            }
        }
    }

    private static void moverUnidades(Exercito ex) {
        for (UnidadeMilitar um : ex.getTodasUnidades()) {
            if (um != null && um.estadoUnidade()) {
                um.moverUnidade();
            }
        }
    }

    private static void interacoes(Exercito ex1, Exercito ex2) {
        for (UnidadeMilitar atacante : ex1.getTodasUnidades()) {
            if (atacante == null || !atacante.estadoUnidade()) continue;

            for (UnidadeMilitar alvo : ex2.getTodasUnidades()) {
                if (alvo == null || !alvo.estadoUnidade()) continue;

                if (emAlcance(atacante, alvo)) {
                    alvo.defenderUnidade(atacante.pontosAtaque);
                }
            }
        }

        for (UnidadeMilitar atacante : ex2.getTodasUnidades()) {
            if (atacante == null || !atacante.estadoUnidade()) continue;

            for (UnidadeMilitar alvo : ex1.getTodasUnidades()) {
                if (alvo == null || !alvo.estadoUnidade()) continue;

                if (emAlcance(atacante, alvo)) {
                    alvo.defenderUnidade(atacante.pontosAtaque);
                }
            }
        }
    }

    private static boolean emAlcance(UnidadeMilitar a, UnidadeMilitar b) {
        int dx = Math.abs(a.posX - b.posX);
        int dy = Math.abs(a.posY - b.posY);
        return dx <= a.alcance && dy <= a.alcance;
    }

    private static void imprimirEstado(Exercito ex, String nome) {
        System.out.println("--- Estado do " + nome + " ---");
        ex.listarUnidades();
    }
}
