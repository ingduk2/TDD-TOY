package toy.numbergame1;

import toy.numbergame1.numberGenerator.NumberGeneratorRandom;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        NumberGameV1 numberGameV1 = new NumberGameV1(new NumberGeneratorRandom());
        numberGameV1.getOutput();

        while (!numberGameV1.isGameEnd()) {
            String next = input.nextLine();
            numberGameV1.startGame(next);
            numberGameV1.getOutput();
        }
    }
}
