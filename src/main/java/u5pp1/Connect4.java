package u5pp1;

public class Connect4 {

    public static final int RED_WIN = 0;
    public static final int BLACK_WIN = 1;
    public static final int NO_WINNER = 2;
    public static final int BOTH_WIN = 3;

    public static final int RED = 1;
    public static final int BLACK = -1;
    public static final int EMPTY = 0;

    // implementation here
    // checks if there is a "0" value in the 2d array, if so it will return false
    public static boolean isFull(int[][] board){
        for(int r = 0; r < board.length; r++){
            for(int c = 0; c < board[r].length; c++){
                if(board[r][c] == 0){
                    return false;
                }
            }
        }
        return true;
    }
    //-------------------------------------------------------------------
    // checks from the bottom and if there is a "0" value it will check above it to see if the above value is also "0"
    public static boolean isBoardValid(int[][] board){
        for(int r = board.length - 1; r > 0; r--){
            for(int c = 0; c < board[r].length; c++){
                if(board[r][c] == 0){
                    if(board[r - 1][c] != 0){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    //-------------------------------------------------------------------
    // runs a for loop throughout the array, does checks for each row and column using helper methods
    // and returns the winner of the game
    public static int getWinner(int[][] board){
        boolean blackW = false;
        boolean redW = false;
        for(int r = 0; r < board.length; r++){
            if(checkHori(board[r]) == 1){
                redW = true;
            }
            if(checkHori(board[r]) == -1){
                blackW = true;
            }
            for(int c = 0; c < board[r].length; c++){
                if(checkVert(board, c) == 1){
                    redW = true;
                }
                if(checkVert(board, c) == -1){
                    blackW = true;
                }
            }
        }
        //checks left to right diagonals
        if(checkDiagUP(board) == 0){
            redW = true;
            blackW = true;
        }
        else if(checkDiagUP(board) == 1){
            redW = true;
        }
        else if(checkDiagUP(board) == 2){
            blackW = true;
        }
        //checks right to left diagonals
        if(checkDiagDOWN(board) == 0){
            redW = true;
            blackW = true;
        }
        else if(checkDiagDOWN(board) == 1){
            redW = true;
        }
        else if(checkDiagDOWN(board) == 2){
            blackW = true;
        }

        if(blackW && redW){
            return BOTH_WIN;
        }
        else if(blackW){
            return BLACK_WIN;
        }
        else if(redW){
            return RED_WIN;
        }
        else{
            return NO_WINNER;
        }
    }
    // It is recommended to use private helper methods
    // helper method to check whether or not there is 4 in a row in a row
    public static int checkHori(int[] row){
        int hold = 0;
        int total = 0;
        for(int i = 0; i < row.length; i++){
            if(row[i] == hold){
                total++;
            }
            else{
                total = 1;
                hold = row[i];
            }
            if(total == 4){
                return hold;
            }
        }
        return 0;
    }
    //-------------------------------------------------------------------
    //helper method to check a column and whether or not there is a 4 in a row
    public static int checkVert(int[][] board, int col){
        int hold = 0;
        int total = 0;
        for(int r = 0; r < board.length; r++){
            if(board[r][col] == hold){
                total++;
            }
            else{
                total = 1;
                hold = board[r][col];
            }
            if(total == 4){
                return hold;
            }
        }
        return 0;
    }
    //-------------------------------------------------------------------
    // helper method to check whether or not there is a 4 in a row in a diagonal
    // different from row and column in that it checks the entire board and only needs to be run once
    public static int checkDiagUP(int[][] board){
        boolean redwin = false;
        boolean blackwin = false;
        for(int r = 0; r < board.length - 3; r++){
            for(int c = 0; c < board[r].length - 3; c++){
                if(board[r][c] == board[r + 1][c + 1] && board[r][c] == board[r + 2][c + 2] && board[r][c] == board[r + 3][c + 3]){
                    if(board[r][c] == RED){
                        redwin = true;
                    }
                    if(board[r][c] == BLACK){
                        blackwin = true;
                    }
                }
            }
        }
        if(redwin && blackwin){
            return 0;
        }
        else if(redwin){
            return 1;
        }
        else if(blackwin){
            return 2;
        }
        else{
            return 3;
        }
    }
    public static int checkDiagDOWN(int[][] board){
        boolean redwin = false;
        boolean blackwin = false;
        for(int r = 0; r < board.length - 3; r++){
            for(int c = board[0].length - 1; c > 2; c--){
                if(board[r][c] == board[r + 1][c - 1] && board[r][c] == board[r + 2][c - 2] && board[r][c] == board[r + 3][c - 3]){
                    if(board[r][c] == RED){
                        redwin = true;
                    }
                    if(board[r][c] == BLACK){
                        blackwin = true;
                    }
                }
            }
        }
        if(redwin && blackwin){
            return 0;
        }
        else if(redwin){
            return 1;
        }
        else if(blackwin){
            return 2;
        }
        else{
            return 3;
        }
    }
}
