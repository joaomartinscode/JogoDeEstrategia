package Jogo;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Simulacao {
    Scanner sc = new Scanner(System.in);
    Random rand = new Random();

    private Exercito exercito1;
    private Exercito exercito2;

    private int maxX, maxY;

    public void iniciar() {
        System.out.println("=== SIMULAÇÃO DE COMBATE ===");

        criarExercitos();

        definirTabuleiro();

        posicionarUnidades(exercito1);
        posicionarUnidades(exercito2);

        executarSimulacao();
    }

    private void criarExercitos() {
        System.out.print("Limite de custo para os exércitos: ");
        int custoTotal = Integer.parseInt(sc.nextLine());

        System.out.println("Criar Exército 1");
        exercito1 = criarExercito(custoTotal);

        System.out.println("Criar Exército 2");
        exercito2 = criarExercito(custoTotal);
    }

    private Exercito criarExercito(int custoTotal) {
        Exercito ex = new Exercito(sc, custoTotal);

        boolean adicionar = true;
        while (adicionar) {
            System.out.print("Deseja adicionar unidades? (sim/nao): ");
            String resp = sc.nextLine().trim().toLowerCase();

            if (resp.equals("sim")) {
                System.out.print("Tipo da unidade (guerreiro/arqueiro/feiticeiro): ");
                String tipo = sc.nextLine();

                System.out.print("Quantidade: ");
                int qtd = Integer.parseInt(sc.nextLine());

                ex.adicionarUnidades(tipo, qtd, sc);
            } else {
                adicionar = false;
            }
        }

        return ex;
    }

    private void definirTabuleiro() {
        System.out.print("Definir tamanho do tabuleiro - Max X: ");
        maxX = Integer.parseInt(sc.nextLine());

        System.out.print("Definir tamanho do tabuleiro - Max Y: ");
        maxY = Integer.parseInt(sc.nextLine());
    }

    private void posicionarUnidades(Exercito ex) {
        System.out.println("Posicionar unidades do exército: " + ex.nome);

        System.out.print("Deseja posicionar manualmente ou aleatoriamente? (manual/aleatorio): ");
        String escolha = sc.nextLine().trim().toLowerCase();

        if (escolha.equals("manual")) {
            for (UnidadeMilitar u : ex.getUnidades()) {
                System.out.println("Posição para unidade: " + u);
                int x, y;

                while (true) {
                    System.out.print("X (0 a " + maxX + "): ");
                    x = Integer.parseInt(sc.nextLine());

                    System.out.print("Y (0 a " + maxY + "): ");
                    y = Integer.parseInt(sc.nextLine());

                    if (x >= 0 && x <= maxX && y >= 0 && y <= maxY) {
                        u.setPosicao(x, y);
                        break;
                    } else {
                        System.out.println("Coordenadas inválidas, tente novamente.");
                    }
                }
            }
        } else { // aleatório
            for (UnidadeMilitar u : ex.getUnidades()) {
                int x = rand.nextInt(maxX + 1);
                int y = rand.nextInt(maxY + 1);
                u.setPosicao(x, y);
            }
            System.out.println("Posicionamento aleatório concluído.");
        }
    }

    private void executarSimulacao() {
        System.out.print("Número de rondas para simular: ");
        int rondas = Integer.parseInt(sc.nextLine());

        for (int i = 1; i <= rondas; i++) {
            System.out.println("\n--- Passo " + i + " ---");

            movimentarExercitos();

            processarCombate();

            mostrarEstado();

            if (!exercito1.estadoExercito()) {
                System.out.println("Exército 2 venceu!");
                return;
            }
            if (!exercito2.estadoExercito()) {
                System.out.println("Exército 1 venceu!");
                return;
            }

            System.out.print("Continuar para a próximo ronda? (sim/nao): ");
            String resp = sc.nextLine().trim().toLowerCase();
            if (!resp.equals("sim")) {
                System.out.println("Simulação encerrada pelo utilizador.");
                return;
            }
        }

        System.out.println("Simulação finalizada após " + rondas + " rondas.");
    }

    private void movimentarExercitos() {
        exercito1.moverExercito(maxX, maxY);
        exercito2.moverExercito(maxX, maxY);
    }

    private void processarCombate() {
        ArrayList<UnidadeMilitar> todasUnidades = new ArrayList<>();
        todasUnidades.addAll(exercito1.getUnidades());
        todasUnidades.addAll(exercito2.getUnidades());

        // Para cada unidade, verifica unidades inimigas em alcance e aplica dano
        for (UnidadeMilitar atacante : todasUnidades) {
            int danoTotal = 0;

            for (UnidadeMilitar inimigo : todasUnidades) {
                if (inimigo == atacante) continue;

                boolean inimigosDiferentes =
                        (exercito1.getUnidades().contains(atacante) && exercito2.getUnidades().contains(inimigo)) ||
                                (exercito2.getUnidades().contains(atacante) && exercito1.getUnidades().contains(inimigo));

                if (inimigosDiferentes && distancia(atacante, inimigo) <= atacante.getAlcance()) {
                    danoTotal += inimigo.getAtaque();
                }
            }

            atacante.defender(danoTotal);
        }
    }

    private int distancia(UnidadeMilitar a, UnidadeMilitar b) {
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }

    private void mostrarEstado() {
        System.out.println("Estado do Exército 1:");
        exercito1.printExercito();

        System.out.println("Estado do Exército 2:");
        exercito2.printExercito();
    }
}
