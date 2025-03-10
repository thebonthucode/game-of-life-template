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
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int neighbors = countNeighbors(i, k);
                if (board[i][j] == 1) {
                    if (neighbors < 2 || neighbors > 3) {
                        nextBoard[i][j] = 0; 
                    } else {
                        nextBoard[i][j] = 1;                    }
                } else {
                    if (neighbors == 3) {
                        nextBoard[i][j] = 1;  
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
        int xLim = board.length;
        int yLim = board[0].length;
        return board[(x + xLim) % xLim][(y + yLim) % yLim];
    }

    public int[][] get() {
        return board;
    }
    
    public void print() {
        System.out.print("\n ");
        for (int i = 0; i < board[0].length; i++) {
            System.out.print(i % 10 + " ");
        }

        for (int i = 0; i < board.length; i++) {
            System.out.print("\n" + x % 10);
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 1) {
                    System.out.print("⬛");
                } else {
                    System.out.print("⬜");
                }
            }
        }
        System.out.println();
    }
}
