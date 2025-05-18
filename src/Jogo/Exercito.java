package Jogo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exercito {
    private List<UnidadeMilitar> unidades = new ArrayList<>();
    private int custoMaximo;

    public Exercito(int custoMaximo) {
        this.custoMaximo = custoMaximo;
    }

    public boolean adicionarUnidade(UnidadeMilitar unidade) {
        int custoAtual = calcularCustoTotal();
        if (custoAtual + unidade.custoProd > custoMaximo) {
            System.out.println("Custo excede o limite do exército!");
            return false;
        }

        // Reaproveitar espaço vazio (null)
        for (int i = 0; i < unidades.size(); i++) {
            if (unidades.get(i) == null) {
                unidades.set(i, unidade);
                return true;
            }
        }

        unidades.add(unidade);
        return true;
    }

    public void removerUnidade(int indice) {
        if (indice >= 0 && indice < unidades.size() && unidades.get(indice) != null) {
            UnidadeMilitar um = unidades.get(indice);
            UnidadeMilitar.liberarId(um.id);
            unidades.set(indice, null);
            System.out.println("Unidade removida.");
        } else {
            System.out.println("Índice inválido.");
        }
    }


    public void listarUnidades() {
        boolean vazia = true;
        for (int i = 0; i < unidades.size(); i++) {
            UnidadeMilitar u = unidades.get(i);
            if (u != null) {
                System.out.print((i + 1) + " - ");
                u.printUnidade();
                vazia = false;
            }
        }
        if (vazia) System.out.println("Exército sem unidades.");
    }

    public boolean estaDerrotado() {
        for (UnidadeMilitar u : unidades) {
            if (u != null && u.estadoUnidade()) return false;
        }
        return true;
    }

    public int calcularCustoTotal() {
        int total = 0;
        for (UnidadeMilitar u : unidades) {
            if (u != null) total += u.custoProd;
        }
        return total;
    }

    public List<UnidadeMilitar> getUnidadesAtivas() {
        List<UnidadeMilitar> ativas = new ArrayList<>();
        for (UnidadeMilitar u : unidades) {
            if (u != null && u.estadoUnidade()) ativas.add(u);
        }
        return ativas;
    }

    public List<UnidadeMilitar> getTodasUnidades() {
        return unidades;
    }

    public void adicionarUnidade(Scanner scanner) {
        System.out.println("Escolha o tipo de unidade:");
        System.out.println("1 - Guerreiro");
        System.out.println("2 - Arqueiro");
        System.out.println("3 - Feiticeiro");
        String escolha = scanner.nextLine();

        UnidadeMilitar novaUnidade = null;

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

        if (novaUnidade != null) {
            adicionarUnidade(novaUnidade);
        }
    }
}
