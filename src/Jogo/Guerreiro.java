package Jogo;

import Utils.InputValidation;

import java.util.Scanner;

public class Guerreiro extends UnidadeMilitar {
    protected int blindagem;

    public Guerreiro(Scanner scanner) {
        super(scanner);
        setNomeAuto("Guerreiro");
        blindagem = InputValidation.validateIntGT0(scanner, "Pontos de blindagem: ");
    }

    @Override
    public void defenderUnidade(int dano) {
        // Primeiro blindagem
        if (blindagem > 0) {
            int blindagemUsada = Math.min(blindagem, dano);
            blindagem -= blindagemUsada;
            dano -= blindagemUsada;
        }
        // Depois pontos de defesa
        if (dano > 0 && pontosDefesa > 0) {
            int defesaUsada = Math.min(pontosDefesa, dano);
            pontosDefesa -= defesaUsada;
            dano -= defesaUsada;
        }
        // Finalmente vida
        if (dano > 0) {
            pontosVida -= dano;
        }
    }

    @Override
    public void printUnidade() {
        System.out.println(nome + " | Vida: " + pontosVida + " | Defesa: " + pontosDefesa + " | Blindagem: "
                + blindagem + " | Posição: (" + posX + "," + posY + ")");
    }
}
