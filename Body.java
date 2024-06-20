
import java.util.Arrays;
import java.util.Scanner;

public class Assignment {

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int black = 0;
        int white = 0;
        int[][] board = {{0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0},
        {0, 0, 1, 2, 0, 0},
        {0, 0, 2, 1, 0, 0},
        {0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0}};

        int rows = 0;
        int cols = 0;
        boolean check = false;
        int opponent = 0;

        // Main body looping
        for (int main = 0; main < 33; main++) {

            // Print the board
            System.out.println("    0 1 2 3 4 5");
            System.out.println("    -----------");

            for (int theBoard = 0; theBoard < board.length; theBoard++) {
                System.out.print(theBoard + " |");
                for (int boardDigital = 0; boardDigital < board[theBoard].length; boardDigital++) {
                    System.out.print(" " + board[theBoard][boardDigital]);
                }
                System.out.print(" ");
                System.out.println();
            }

            // if main can divisible by 2 and < 32, it is A player turn
            if (main % 2 == 0 && main < 32) {                
                for (; check = true;) {
                    opponent = 2;
                    System.out.println();
                    System.out.print("Please enter the position of '1', Example: 1 3 (X Y):");
                    rows = kb.nextInt();
                    cols = kb.nextInt();

                    if (checkInputbound(rows, cols, board, opponent, main) == true) {
                        check = true;
                    } else {
                        continue;
                    }
                    placeMove(main, rows, cols, board, opponent);
                    break;
                }

                board[rows][cols] = 1;
                check = false;
            }

            // if main can not divisible by 2 and < 32, it is B player turn
            if (main % 2 == 1 && main < 32) {
                for (; check = true;) {
                    opponent = 1;
                    System.out.println();
                    System.out.print("Please enter the position of '2' , Example: 1 3 (X Y):");
                    rows = kb.nextInt();
                    cols = kb.nextInt();

                    if (checkInputbound(rows, cols, board, opponent, main) == true) {
                        check = true;
                    } else {
                        continue;
                    }
                    placeMove(main, rows, cols, board, opponent);
                    break;
                }

                board[rows][cols] = 2;
                check = false;
            }
        }
        //End Game
        System.out.println();
        System.out.println("Game Finishes.");
        for (int y = 0; y < 6; y++) {
            for (int x = 0; x < 6; x++) {
                if (board[y][x] == 1) {
                    black += 1;
                } else if (board[y][x] == 2) {
                    white += 1;
                }
            }
        }
        System.out.println("  '1' - " + black);
        System.out.println("  '2' - " + white);
        if (black > white) {
            System.out.println("Black wins.");
        } else {
            System.out.println("White wins.");
        }
    }

    public static boolean checkInputbound(int rows, int cols, int[][] board, int opponent, int main) {
        // Check input must be 0 to 5
        if (rows > 5 || rows < 0 || cols > 5 || cols < 0) {
            System.out.println("Error - input number shoule be 0 to 5.");
            return false;
        } // Check the input is empty
        else if (board[rows][cols] != 0) {
            System.out.println("Error - input cell is not empty");
            return false;
        } // Find the input location
        else if (playerLocation(main, rows, cols, opponent, board) == false) {
            System.out.println("Error - invalid move.");
            return false;
        }
        return true;
    }

    public static boolean playerLocation(int main, int rows, int cols, int opponent, int[][] board) {
        int player;
        int r = rows;
        int c = cols;
        // Find the player
        if (main % 2 == 0) {
            player = 1;
        } else {
            player = 2;
        }
        // Start to find the input location
        for (int j = c - 1; j >= 0; j--) {
            // [W] direction
            if (board[r][c - 1] == opponent && board[r][j] == player) {
                return true;
            }
        }
        for (int j = c + 1; j <= 5; j++) {
            // [E] direction
            if (board[r][c + 1] == opponent && board[r][j] == player) {
                return true;
            }
        }
        for (int j = r - 1; j >= 0; j--) {
            // [N] direction
            if (board[r - 1][c] == opponent && board[j][c] == player) {
                return true;
            }
        }
        for (int j = r + 1; j <= 5; j++) {
            // [S] direction
            if (board[r + 1][c] == opponent && board[j][c] == player) {
                return true;
            }
        }
        for (int j = r - 1, k = c + 1; j >= 0 && k <= 5; j--, k++) {
            // [NE] direction
            if (board[r - 1][c + 1] == opponent && board[j][k] == player) {
                return true;
            }
        }
        for (int j = r + 1, k = c + 1; j <= 5 && k <= 5; j++, k++) {
            // [SE] direction
            if (board[r + 1][c + 1] == opponent && board[j][k] == player) {
                return true;
            }
        }
        for (int j = r - 1, k = c - 1; j >= 0 & k >= 0; j--, k--) {
            if (board[r - 1][c - 1] == opponent && board[j][k] == player) {
                return true;
            }
        }
        for (int j = r + 1, k = c - 1; j <= 5 && k >= 0; j++, k--) {
            // [SW] direction
            if (board[r + 1][c - 1] == opponent && board[j][k] == player) {
                return true;
            }
        }
        return false;
    }

    public static void placeMove(int main, int rows, int cols, int[][] board, int opponent) {

        // checking player is A or B
        int player;
        int r = rows;
        int c = cols;
        if (main % 2 == 0) {
            player = 1;
        } else {
            player = 2;
        }
        for (int j = c - 1; j >= 0; j--) {
            // [W] direction
            if (board[r][c - 1] == opponent && board[r][j] == player) {
                c = c - 1;
                while (board[r][c] == opponent) {
                    board[r][c] = player;
                    c--;
                }
                break;
            }
        }
        r = rows;
        c = cols;
        for (int j = c + 1; j <= 5; j++) {
            // [E] direction
            if (board[r][c + 1] == opponent && board[r][j] == player) {
                c = c + 1;
                while (board[r][c] == opponent) {
                    board[r][c] = player;
                    c++;
                }
                break;
            }
        }
        r = rows;
        c = cols;
        for (int j = r - 1; j >= 0; j--) {
            // [N] direction
            if (board[r - 1][c] == opponent && board[j][c] == player) {
                r = r - 1;
                while (board[r][c] == opponent) {
                    board[r][c] = player;
                    r--;
                }
            break;
            }
        }
        r = rows;
        c = cols;
        for (int j = r + 1; j <= 5; j++) {
            // [S] direction
            if (board[r + 1][c] == opponent && board[j][c] == player) {
                r = r + 1;
                while (board[r][c] == opponent) {
                    board[r][c] = player;
                    r++;
                }
                break;
            }
        }
        r = rows;
        c = cols;
        for (int j = r - 1, k = c + 1; j >= 0 && k <= 5; j--, k++) {
            // [NE] direction
            if (board[r - 1][c + 1] == opponent && board[j][k] == player) {
                r = r - 1;
                c = c + 1;
                while (board[r][c] == opponent) {
                    board[r][c] = player;
                    r--;
                    c++;
                }
                break;
            }
        }
        r = rows;
        c = cols;
        for (int j = r + 1, k = c + 1; j <= 5 && k <= 5; j++, k++) {
            // [SE] direction
            if (board[r + 1][c + 1] == opponent && board[j][k] == player) {
                r = r + 1;
                c = c + 1;
                while (board[r][c] == opponent) {
                    board[r][c] = player;
                    r++;
                    c++;
                }
                break;
            }
        }
        r = rows;
        c = cols;
        // [NW] direction
        for (int j = r - 1, k = c - 1; j >= 0 & k >= 0; j--, k--) {
            if (board[r - 1][c - 1] == opponent && board[j][k] == player) {
                r = r - 1;
                c = c - 1;
                while (board[r][c] == opponent) {
                    board[r][c] = player;
                    r--;
                    c--;
                }
                break;
            }
        }
        r = rows;
        c = cols;
        for (int j = r + 1, k = c - 1; j <= 5 && k >= 0; j++, k--) {
            // [SW] direction
            if (board[r + 1][c - 1] == opponent && board[j][k] == player) {
                r = r + 1;
                c = c - 1;
                while (board[r][c] == opponent) {
                    board[r][c] = player;
                    r++;
                    c--;
                }
                break;
            }
        }
    }
}
