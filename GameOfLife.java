public class GameOfLife implements Board {

    // Integers: 0 or 1 for alive or dead
    private int[][] board;

    public GameOfLife(int x, int y) {
        board = new int[x][y];
    }

    // Set values on the board
    public void set(int x, int y, int[][] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                board[i + x][j + y] = data[i][j];
            }
        }
    }

    // Run the simulation for a number of turns
    public void run(int turns) {
        for (int i = 0; i < turns; i++) {
            step();
        }
    }

    // Step the simulation forward one turn.
    public void step() {
        int[][] nextBoard = new int[board.length][board[0].length];
        for (int j = 0; j < board.length; j++) {
            for (int k = 0; k < board[0].length; k++) {
                int neighbors = countNeighbors(j, k);
                if (board[j][k] == 1) {
                    if (neighbors < 2 || neighbors > 3) {
                        nextBoard[j][k] = 0; 
                    } else {
                        nextBoard[j][k] = 1;                    }
                } else {
                    if (neighbors == 3) {
                        nextBoard[j][k] = 1;  
                    }
                }
            }
        }
        
        board = nextBoard;


    }

    public int countNeighbors(int x, int y) {
        int count = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue; 
                }
                if (get(x + i, y + j) == 1) {
                    count++;
                }
            }
        }

        return count;
    }

    public int get(int x, int y) {
        int xLimit = board.length;
        int yLimit = board[0].length;
        return board[(x + xLimit) % xLimit][(y + yLimit) % yLimit];
    }

    public int[][] get() {
        return board;
    }
    
    public void print() {
        System.out.print("\n ");
        for (int y = 0; y < board[0].length; y++) {
            System.out.print(y % 10 + " ");
        }

        for (int x = 0; x < board.length; x++) {
            System.out.print("\n" + x % 10);
            for (int y = 0; y < board[x].length; y++) {
                if (board[x][y] == 1) {
                    System.out.print("⬛");
                } else {
                    System.out.print("⬜");
                }
            }
        }
        System.out.println();
    }
}
