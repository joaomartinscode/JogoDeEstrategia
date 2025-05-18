package Jogo;

import java.util.ArrayList;

public class Exercito {
    private String nome;
    private int custoMaximo;
    private ArrayList<UnidadeMilitar> unidades = new ArrayList<>();

    public Exercito(String nome, int custoMaximo) {
        this.nome = nome;
        this.custoMaximo = custoMaximo;
    }

    public boolean adicionar(UnidadeMilitar u) {
        if (getCustoTotal() + u.getCusto() <= custoMaximo) {
            unidades.add(u);
            return true;
        }
        return false;
    }

    public int getCustoTotal() {
        return unidades.stream().mapToInt(UnidadeMilitar::getCusto).sum();
    }

    public ArrayList<UnidadeMilitar> getUnidades() {
        return unidades;
    }

    public boolean estaDerrotado() {
        return unidades.stream().noneMatch(UnidadeMilitar::estaViva);
    }

    public void listar() {
        for (UnidadeMilitar u : unidades) {
            System.out.println(u);
        }
    }
}

