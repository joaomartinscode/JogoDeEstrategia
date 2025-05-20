package Jogo;

import java.util.Scanner;

public class Arqueiro extends UnidadeMilitar {
    protected String armamento;
    protected int bonusAtaque = 0;

    public Arqueiro(Scanner scanner) {
        super(scanner);
        setNomeAuto("Arqueiro");

        System.out.print("Tipo de armamento (arco e flecha / besta): ");
        armamento = scanner.nextLine();

        // Define o bônus de ataque conforme o tipo
        if (armamento.equalsIgnoreCase("arco e flecha")) {
            bonusAtaque = 5;  // exemplo de bônus
        } else if (armamento.equalsIgnoreCase("besta")) {
            bonusAtaque = 8;  // exemplo de bônus maior
        } else {
            System.out.println("Tipo de armamento inválido");
        }

        // Ajustar pontos de ataque da unidade somando bônus
        pontosAtaque += bonusAtaque;
    }


    @Override
    public void defenderUnidade(int dano) {
        // Apenas pontos de defesa e vida
        if (pontosDefesa > 0) {
            int defesaUsada = Math.min(pontosDefesa, dano);
            pontosDefesa -= defesaUsada;
            dano -= defesaUsada;
        }
        if (dano > 0) {
            pontosVida -= dano;
        }
    }

    @Override
    public void printUnidade() {
        System.out.println(nome + " | Armamento: " + armamento + " | Vida: " + pontosVida +
                " | Defesa: " + pontosDefesa + " | Posição: (" + posX + "," + posY + ")");
    }
}
