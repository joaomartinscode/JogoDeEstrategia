package Jogo;

import Utils.InputValidation;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static Exercito exercito1;
    private static Exercito exercito2;

    public static void main(String[] args) {
        inicializarExercito();
        menuPrincipal();
    }

    private static void inicializarExercito() {
        int custoMaximo = InputValidation.validateIntGT0(scanner, "Defina o custo máximo para ambos os exércitos: ");
        exercito1 = new Exercito(custoMaximo);
        exercito2 = new Exercito(custoMaximo);
    }

    private static void menuPrincipal() {
        while (true) {
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1. Gerenciar Exército 1");
            System.out.println("2. Gerenciar Exército 2");
            System.out.println("3. Ver Estado dos Exércitos");
            System.out.println("4. Simular Combate");
            System.out.println("0. Sair");

            switch (InputValidation.validateIntBetween(scanner, ":> ", 0, 4)) {
                case 1:
                    menuExercito(exercito1, "Exército 1");
                    break;
                case 2:
                    menuExercito(exercito2, "Exército 2");
                    break;
                case 3:
                    exercito1.imprimirEstado("Exército 1");
                    exercito2.imprimirEstado("Exército 2");
                    break;
                case 4:
                    Simular.simularCombate(exercito1, exercito2, scanner);
                    break;
                case 0:
                    System.out.println("Programa encerrado.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void menuExercito(Exercito exercito, String nome) {
        while (true) {
            System.out.println("\n--- " + nome + " ---");
            System.out.println("1. Adicionar unidade");
            System.out.println("2. Listar unidades");
            System.out.println("3. Remover unidade");
            System.out.println("0. Voltar ao menu principal");

            switch (InputValidation.validateIntBetween(scanner, ":> ", 0, 3)) {
                case 1:
                    exercito.adicionarUnidade(scanner);
                    break;
                case 2:
                    exercito.listarUnidades();
                    break;
                case 3:
                    exercito.removerUnidade(scanner);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

}
