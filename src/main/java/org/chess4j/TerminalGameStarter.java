package org.chess4j;

import org.chess4j.exceptions.InvalidMoveException;
import org.chess4j.simple.SimpleGame;

import java.util.Scanner;

public class TerminalGameStarter {

    public static void main(String[] args) {
        SimpleGame game = new SimpleGame();
        Scanner scanner = new Scanner(System.in);

        while (!game.isGameOver()) {
            Boards.print(game.getBoardPosition());

            System.out.println("Enter move (e.g., e2e4): ");
            String move = scanner.nextLine();

            try {
                game.move(move);
            } catch (InvalidMoveException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }


        System.out.println("Game over.");
        scanner.close();
    }
}
