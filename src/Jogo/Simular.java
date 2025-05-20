package Jogo;

import Utils.InputValidation;

import java.util.Scanner;

public class Simular {
    public static void simularCombate(Exercito ex1, Exercito ex2, Scanner scanner) {
        boolean continuar = true;

        if (ex1.estaVazio() || ex2.estaVazio()) {
            System.out.println("Ambos os exércitos devem ter pelo menos uma unidade para iniciar a simulação.");
            return;
        }

        int largura = InputValidation.validateIntGT0(scanner, "Tamanho máximo do tabuleiro (X): ");
        int altura = InputValidation.validateIntGT0(scanner, "Tamanho máximo do tabuleiro (Y): ");
        Tabuleiro tabuleiro = new Tabuleiro(largura, altura);

        System.out.print("Deseja posicionar manualmente as unidades? (s/n): ");
        boolean manual = scanner.nextLine().trim().equalsIgnoreCase("s");

        ex1.posicionarUnidades(tabuleiro, scanner, manual);
        ex2.posicionarUnidades(tabuleiro, scanner, manual);

        while (continuar) {
            System.out.print("Quantos passos deseja simular? ");
            int passos = scanner.nextInt();
            scanner.nextLine();

            for (int passo = 1; passo <= passos; passo++) {
                System.out.println("\n--- Passo " + passo + " ---");

                ex1.moverTodos(tabuleiro);
                ex2.moverTodos(tabuleiro);

                ex1.atacar(ex2);
                ex2.atacar(ex1);

                ex1.imprimirEstado("Exército 1");
                ex2.imprimirEstado("Exército 2");

                if (ex1.estaDerrotado()) {
                    System.out.println("Exército 1 foi derrotado!");
                    continuar = false;
                    break;
                }
                if (ex2.estaDerrotado()) {
                    System.out.println("Exército 2 foi derrotado!");
                    continuar = false;
                    break;
                }
            }

            if (continuar) {
                System.out.print("Deseja continuar a simular mais passos? (s/n): ");
                String resposta = scanner.nextLine();
                if (!resposta.equalsIgnoreCase("s")) {
                    continuar = false;
                }
            }
        }

        System.out.println("Simulação encerrada.");
    }
}
