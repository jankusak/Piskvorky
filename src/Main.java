import java.util.Scanner;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private enum Player {
        X, O
    }

    private static String[][] playField = new String[3][3];
    private static Player currentPlayer = Player.X;
    private static ArrayList<Integer> chosenNumbers = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hi, welcome to the Tic-tac-toe game");

        Scanner scanner = new Scanner(System.in);

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                playField[row][column] = " ";
            }
        }
        while (true) {
            System.out.println("Player " + currentPlayer.name() + ": Chose number 1 - 9");
            String gamer = scanner.nextLine();

            try {
                Integer numberGamer = Integer.valueOf(gamer);
                if (numberGamer > 0 && numberGamer < 10) {
                    if (chosenNumbers.contains(numberGamer)) {
                        System.out.println("Number is already chosen");
                    } else {
                        applyPosition(numberGamer);
                        chosenNumbers.add(numberGamer);
                        if (testResult(currentPlayer.name())) {
                            return;
                        }
                        printTable();
                        currentPlayer = currentPlayer == Player.O ? Player.X : Player.O;
                    }
                } else {
                    System.out.println("Invalid number!!! Try number between 1 - 9");
                }
            } catch (Exception e) {
                System.out.println("Invalid  input. Try it again.");
            }
        }
    }

    private static void printTable() {
        System.out.println();
        System.out.println(playField[0][0] + "|" + playField[0][1] + "|" + playField[0][2]);
        System.out.println("-----");
        System.out.println(playField[1][0] + "|" + playField[1][1] + "|" + playField[1][2]);
        System.out.println("-----");
        System.out.println(playField[2][0] + "|" + playField[2][1] + "|" + playField[2][2]);
    }

    private static void applyPosition(int fieldNumber) {
        switch (fieldNumber) {
            case 1 -> playField[0][0] = (currentPlayer == Player.O ? Player.O.name() : Player.X.name());
            case 2 -> playField[0][1] = (currentPlayer == Player.O ? Player.O.name() : Player.X.name());
            case 3 -> playField[0][2] = (currentPlayer == Player.O ? Player.O.name() : Player.X.name());
            case 4 -> playField[1][0] = (currentPlayer == Player.O ? Player.O.name() : Player.X.name());
            case 5 -> playField[1][1] = (currentPlayer == Player.O ? Player.O.name() : Player.X.name());
            case 6 -> playField[1][2] = (currentPlayer == Player.O ? Player.O.name() : Player.X.name());
            case 7 -> playField[2][0] = (currentPlayer == Player.O ? Player.O.name() : Player.X.name());
            case 8 -> playField[2][1] = (currentPlayer == Player.O ? Player.O.name() : Player.X.name());
            case 9 -> playField[2][2] = (currentPlayer == Player.O ? Player.O.name() : Player.X.name());
            default -> System.out.println("Something wrong");
        }
    }

    public static boolean testResult(String gamerSymbol) {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                try {
                    if (playField[row][column] != gamerSymbol) {
                        continue;
                    }
//                    tohle je cunarna, ale zatim me nenapadlo, jak ten algoritmus opravit nebo vymyslet lepsi
                    if (playField[0][0].equals(playField[1][0]) && playField[1][0].equals(playField[2][0])) {
                        System.out.println("Gamer " + currentPlayer.name() + " win");
                        return true;
                    }
//                    horizontalni vyhra
                    if (playField[row][column].equals(playField[row][column - 1]) && playField[row][column].equals(playField[row][column + 1])) {
                        System.out.println("Gamer " + currentPlayer.name() + " win");
                        return true;
                    }
//                  vertikalni vyhra
                    if (playField[row][column].equals(playField[row + 1][column]) && playField[row][column].equals(playField[row + 1][column])) {
                        System.out.println("Gamer " + currentPlayer.name() + " win");
                        return true;
                    }
                    //           diagonala
                    if (row == 1 && column == 1) {
                        if (playField[row][column].equals(playField[row - 1][column - 1]) && playField[row][column].equals(playField[row + 1][column + 1])) {
                            System.out.println("Gamer " + currentPlayer.name() + " win");
                            return true;
                        }
                        if (playField[row][column].equals(playField[row + 1][column - 1]) && playField[row][column].equals(playField[row - 1][column + 1])) {
                            System.out.println("Gamer " + currentPlayer.name() + " win");
                            return true;
                        }
                    }
                } catch (Exception ArrayIndexOutOfBoundsException) {
                    //Ted nevadi, ze jsou hodnoty mimo rozsah
                }
            }
        }
        if (chosenNumbers.size() >= 9) {
            System.out.println("The match ended in a tie");
            return true;
        }
        return false;
    }
}

