package Jogo;

import Utils.InputValidation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Exercito {
    private final List<UnidadeMilitar> unidades = new ArrayList<>();
    private final int custoMaximo;
    private static final Random random = new Random();

    public Exercito(int custoMaximo) {
        this.custoMaximo = custoMaximo;
    }

    public void adicionarUnidade(Scanner scanner) {
        int custoAtual = calcularCustoTotal();

        System.out.println("Escolha o tipo de unidade:");
        System.out.println("1 - Guerreiro");
        System.out.println("2 - Arqueiro");
        System.out.println("3 - Feiticeiro");
        String escolha = scanner.nextLine();

        UnidadeMilitar novaUnidade;

        switch (escolha) {
            case "1":
                novaUnidade = new Guerreiro(scanner);
                break;
            case "2":
                novaUnidade = new Arqueiro(scanner);
                break;
            case "3":
                novaUnidade = new Feiticeiro(scanner);
                break;
            default:
                System.out.println("Tipo inválido.");
                return;
        }

        if (custoAtual + novaUnidade.custoProd > custoMaximo) {
            System.out.println("Custo excede o limite do exército!");
            return;
        }

        for (int i = 0; i < unidades.size(); i++) {
            if (unidades.get(i) == null) {
                unidades.set(i, novaUnidade);
                return;
            }
        }

        unidades.add(novaUnidade);
    }

    public void removerUnidade(Scanner scanner) {
        if (unidades.isEmpty()) {
            System.out.println("Não há unidades para remover.");
            return;
        }

        listarUnidades();

        int index = InputValidation.validateIntBetween(
                scanner, "Índice da unidade a remover: ", 1, unidades.size()
        );

        UnidadeMilitar removida = unidades.remove(index - 1);
        UnidadeMilitar.liberarId(removida.id);
        System.out.println("Unidade removida: " + removida.nome);
    }

    public void listarUnidades() {
        if (unidades.isEmpty()) {
            System.out.println("Nenhuma unidade no exército.");
            return;
        }

        System.out.println("Unidades do exército:");
        for (int i = 0; i < unidades.size(); i++) {
            System.out.print((i + 1) + ". ");
            unidades.get(i).printUnidade();
        }
    }

    public boolean estaDerrotado() {
        for (UnidadeMilitar u : unidades) {
            if (u != null && u.estadoUnidade()) return false;
        }
        return true;
    }

    public boolean estaVazio() {
        return unidades.isEmpty();
    }

    public int calcularCustoTotal() {
        int total = 0;
        for (UnidadeMilitar u : unidades) {
            if (u != null) total += u.custoProd;
        }
        return total;
    }

    public void moverTodos(Tabuleiro tabuleiro) {
        for (UnidadeMilitar unidade : unidades) {
            if (unidade != null && unidade.estadoUnidade()) {
                unidade.moverUnidade(tabuleiro);
            }
        }
    }

    public void atacar(Exercito inimigo) {
        for (UnidadeMilitar alvo : inimigo.unidades) {
            if (alvo == null || !alvo.estadoUnidade()) continue;

            int danoTotal = 0;

            for (UnidadeMilitar atacante : this.unidades) {
                if (atacante == null || !atacante.estadoUnidade()) continue;

                int distancia = Math.abs(atacante.posX - alvo.posX) + Math.abs(atacante.posY - alvo.posY);
                if (distancia <= atacante.alcance) {
                    danoTotal += atacante.pontosAtaque;
                }
            }

            if (danoTotal > 0) {
                // Aplica dano: primeiro na defesa
                int danoRestante = danoTotal;

                if (alvo.pontosDefesa > 0) {
                    if (alvo.pontosDefesa >= danoRestante) {
                        alvo.pontosDefesa -= danoRestante;
                        danoRestante = 0;
                    } else {
                        danoRestante -= alvo.pontosDefesa;
                        alvo.pontosDefesa = 0;
                    }
                }

                if (danoRestante > 0) {
                    alvo.pontosVida -= danoRestante;
                    if (alvo.pontosVida <= 0) {
                        alvo.pontosVida = 0;
                        System.out.println(alvo.getClass().getSimpleName() + " foi derrotado!");
                    }
                }
            }
        }
    }

    public void posicionarUnidades(Tabuleiro tabuleiro, Scanner scanner, boolean manual) {
        for (UnidadeMilitar um : unidades) {
            if (um == null) continue;

            if (manual) {
                int x, y;
                do {
                    x = InputValidation.validateIntGE0(scanner, "Posição X para " + um.getClass().getSimpleName() + ": ");
                    y = InputValidation.validateIntGE0(scanner, "Posição Y para " + um.getClass().getSimpleName() + ": ");
                } while (tabuleiro.posInvalida(x, y));

                um.posX = x;
                um.posY = y;
            } else {
                int x, y;
                do {
                    x = random.nextInt(tabuleiro.getLargura());
                    y = random.nextInt(tabuleiro.getAltura());
                } while (tabuleiro.posInvalida(x, y));

                um.posX = x;
                um.posY = y;
            }
        }
    }

    public void imprimirEstado(String nome) {
        System.out.println("--- Estado do " + nome + " ---");
        listarUnidades();
    }
}
