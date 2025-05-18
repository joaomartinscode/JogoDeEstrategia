package Jogo;

import java.util.Scanner;

public abstract class UnidadeMilitar {
    protected final int index;
    protected String nome;
    protected int custo;
    protected int vida;
    protected int ataque;
    protected int defesa;
    protected int alcance;
    protected int velocidade;
    protected int x;
    protected int y;

    public UnidadeMilitar(int index, Scanner sc) {
        this.index = index;

        System.out.print("Nome: ");
        this.nome = sc.nextLine();

        System.out.print("Custo de produção: ");
        this.custo = Integer.parseInt(sc.nextLine());

        System.out.print("Pontos de vida: ");
        this.vida = Integer.parseInt(sc.nextLine());

        System.out.print("Pontos de ataque: ");
        this.ataque = Integer.parseInt(sc.nextLine());

        System.out.print("Pontos de defesa: ");
        this.defesa = Integer.parseInt(sc.nextLine());

        System.out.print("Alcance: ");
        this.alcance = Integer.parseInt(sc.nextLine());

        System.out.print("Velocidade: ");
        this.velocidade = Integer.parseInt(sc.nextLine());

        this.x = 0;
        this.y = 0;
    }

    public abstract void atacar();

    public void defender(int dano) {
        int danoFinal = dano - defesa;
        if (danoFinal > 0) vida -= danoFinal;
        if (vida < 0) vida = 0;
    }

    public boolean estadoUnidade() {
        return vida > 0;
    }

    public void mover(int maxX, int maxY) {
        int dx = (Math.random() < 0.5 ? -1 : 1) * velocidade;
        int dy = (Math.random() < 0.5 ? -1 : 1) * velocidade;
        x = Math.max(0, Math.min(x + dx, maxX));
        y = Math.max(0, Math.min(y + dy, maxY));
    }

    public void setPosicao(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return nome + " #" + index + " | Vida: " + vida + " | Pos: (" + x + "," + y + ")";
    }

    public int getIndex() { return index; }
    public int getCusto() { return custo; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getAlcance() { return alcance; }
    public int getAtaque() { return ataque; }
}



