package leetcode;

/**
 * Created by: HungCQ
 * Date: 16-Jun-20
 */
public class Sudoku {

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        Sudoku sudoku = new Sudoku();
        sudoku.solveSudoku(board);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + ", ");
            }
            System.out.print("\n");
        }
    }

    boolean isFinished = false;

    public void solveSudoku(char[][] board) {
        backtrack(0, 0, board);
    }

    void backtrack(int row, int col, char[][] board) {
        if (row == 9) {
            isFinished = true;
            return;
        }
        int newRow = row + (col + 1) / 9;
        int newCol = col + 1;
        newCol = newCol % 9;
        if (board[row][col] != '.') {
            backtrack(newRow, newCol, board);
        } else {
            for (char c = '1'; c <= '9'; c++) {
                if (isValid(row, col, c, board)) {
                    board[row][col] = c;
                    backtrack(newRow, newCol, board);
                    if (!isFinished) {
                        board[row][col] = '.';
                    }
                }
            }
        }
    }

    boolean isValid(int row, int col, char value, char[][] board) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == value) {
                return false;
            }
            if (board[i][col] == value) {
                return false;
            }
        }
        int m = (row / 3) * 3;
        int n = (col / 3) * 3;
        for (int i = m; i < m + 3; i++) {
            for (int j = n; j < n + 3; j++) {
                if (board[i][j] == value) {
                    return false;
                }
            }
        }
        return true;
    }
}
