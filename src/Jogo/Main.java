package Jogo;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static Exercito exercito1;
    private static Exercito exercito2;

    public static void main(String[] args) {
        inicializarExercitos();
        menuPrincipal();
    }

    private static void inicializarExercitos() {
        System.out.print("Defina o custo máximo para ambos os exércitos: ");
        int custoMaximo = lerInteiro();
        exercito1 = new Exercito(custoMaximo);
        exercito2 = new Exercito(custoMaximo);
    }

    private static void menuPrincipal() {
        while (true) {
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1. Gerenciar Exército 1");
            System.out.println("2. Gerenciar Exército 2");
            System.out.println("3. Simular Combate");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            switch (scanner.nextLine().trim()) {
                case "1":
                    menuExercito(exercito1, "Exército 1");
                    break;
                case "2":
                    menuExercito(exercito2, "Exército 2");
                    break;
                case "3":
                    menuSimulacao();
                    break;
                case "0":
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
            System.out.print("Escolha uma opção: ");

            switch (scanner.nextLine().trim()) {
                case "1":
                    exercito.adicionarUnidade(scanner);
                    break;
                case "2":
                    exercito.listarUnidades();
                    break;
                case "3":
                    exercito.listarUnidades();
                    if (!exercito.getTodasUnidades().isEmpty()) {
                        System.out.print("Índice da unidade a remover: ");
                        int indice = lerInteiro() - 1;
                        exercito.removerUnidade(indice);
                    }
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void menuSimulacao() {
        if (exercito1.getTodasUnidades().isEmpty() || exercito2.getTodasUnidades().isEmpty()) {
            System.out.println("Ambos os exércitos devem ter pelo menos uma unidade para iniciar a simulação.");
            return;
        }

        Simulacao.simularCombate(exercito1, exercito2, scanner);
    }

    private static int lerInteiro() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Digite um número inteiro: ");
            }
        }
    }
}
