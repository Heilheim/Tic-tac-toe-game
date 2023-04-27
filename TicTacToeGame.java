import java.util.Random;
import java.util.Scanner;

public class TicTacToeGame {
    public static void main(String[] args) {
        gameStart();
    }
        private static void gameStart() {

            char[][] gameTable = {
                    {' ', ' ', ' '},
                    {' ', ' ', ' '},
                    {' ', ' ', ' '}
            };

            if (new Random().nextBoolean()) {
                computerMakeMove(gameTable);
                printChangedGameBoard(gameTable);
            }
            System.out.println("Use the following mapping table to specify a cell using numbers from 1 to 9:");
            printGameBoard();
            while (true) {
                playerMakeMove(gameTable);
                printChangedGameBoard(gameTable);
                if (isPlayerWin(gameTable)) {
                    System.out.println("YOU WIN!");
                    break;
                }
                if (isDraw(gameTable)) {
                    System.out.println("DRAW!");
                    break;
                }
                computerMakeMove(gameTable);
                printChangedGameBoard(gameTable);
                if (isComputerWin(gameTable)) {
                    System.out.println("COMPUTER WIN!");
                    break;
                }
                if (isDraw(gameTable)) {
                    System.out.println("DRAW!");
                    break;
                }
            }
            System.out.println("GAME OVER");
        }

        private static void printGameBoard() {
            char[][] gameTable = {
                    {'7', '8', '9'},
                    {'4', '5', '6'},
                    {'1', '2', '3'}
            };
            printChangedGameBoard(gameTable);
        }

        private static void printChangedGameBoard(char[][] gameTable) {
            System.out.println("-------------");
            System.out.println("| " + gameTable[0][0] + " | " + gameTable[0][1] + " | " + gameTable[0][2] + " |");
            System.out.println("-------------");
            System.out.println("| " + gameTable[1][0] + " | " + gameTable[1][1] + " | " + gameTable[1][2] + " |");
            System.out.println("-------------");
            System.out.println("| " + gameTable[2][0] + " | " + gameTable[2][1] + " | " + gameTable[2][2] + " |");
            System.out.println("-------------");
        }

        private static void playerMakeMove(char[][] gameTable) {
            while (true) {
                System.out.println("Please type number between 1 and 9:");
                String string = new Scanner(System.in).nextLine();
                if (string.length() == 1) {
                    char number = string.charAt(0);
                    int[] element = getCellFromPlayerNumber(number);
                    if (number <= '0' || number > '9') {
                        System.out.println("Wrong number!");
                        continue;
                    }
                    if (!isEmpty(element, gameTable)) {
                        System.out.println("Can't make a move, because the cell is not free! Try again:");
                        continue;
                    }
                    gameTable[element[0]][element[1]] = 'X';
                    break;
                }
            }

        }

        private static int[] getCellFromPlayerNumber(char number) {
            int count = 0;
            int[] element = new int[2];
            outerLoop:
            for (int i = 2; i >= 0; i--) {
                for (int j = 0; j < 3; j++) {
                    count++;
                    if (count == Character.getNumericValue(number)) {
                        element[0] = i;
                        element[1] = j;
                        break outerLoop;
                    }
                }
            }
            return element;
        }

        private static void computerMakeMove(char[][] gameTable) {
            Random random = new Random();
            while (true) {
                int row = random.nextInt(3);
                int col = random.nextInt(3);
                if (gameTable[row][col] == ' ') {
                    gameTable[row][col] = 'O';
                    return;
                }
            }
        }

        private static boolean isWinner(char[][] gameTable, char ch) {
            if ((gameTable[0][0] == ch && gameTable[0][1] == ch && gameTable[0][2] == ch) ||
                    (gameTable[1][0] == ch && gameTable[1][1] == ch && gameTable[1][2] == ch) ||
                    (gameTable[2][0] == ch && gameTable[2][1] == ch && gameTable[2][2] == ch) ||
                    (gameTable[0][0] == ch && gameTable[1][0] == ch && gameTable[2][0] == ch) ||
                    (gameTable[0][1] == ch && gameTable[1][1] == ch && gameTable[2][1] == ch) ||
                    (gameTable[0][2] == ch && gameTable[1][2] == ch && gameTable[2][2] == ch) ||
                    (gameTable[0][0] == ch && gameTable[1][1] == ch && gameTable[2][2] == ch) ||
                    (gameTable[0][2] == ch && gameTable[1][1] == ch && gameTable[2][0] == ch))
                return true;
            else
                return false;
        }

        private static boolean isPlayerWin(char[][] gameTable) {
            return isWinner(gameTable, 'X');
        }

        private static boolean isComputerWin(char[][] gameTable) {
            return isWinner(gameTable, 'O');
        }

        private static boolean isDraw(char[][] gameTable) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (gameTable[i][j] == ' ') {
                        return false;
                    }
                }
            }
            return true;
        }

        private static boolean isEmpty(int[] element, char[][] gameTable) {
            return gameTable[element[0]][element[1]] == ' ';
        }
    }

