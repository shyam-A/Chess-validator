package chess;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    public abstract boolean validate(String[][] board, int from, int to);

    public String getOtherColor(String selfColor) {
        return selfColor.equals("W") ? "B" : "W";
    }

    public boolean isKingInCheck(String[][] board, String color) {

        int kingRow = 0;
        int kingCol = 0;

        for(int row=0; row<8; row++) {
            for(int col=0; col<8; col++) {
                if(board[row][col].equals(color+"K")) {
                    kingCol = col; kingRow = row;
                }
            }
        }

        String oppColor = getOtherColor(color);

        //check for Knight
        if(kingRow-2 >= 0 && kingCol-1 >= 0 && board[kingRow-2][kingCol-1].equals(oppColor+"H")) return true;
        if(kingRow-2 >= 0 && kingCol+1 <= 7 && board[kingRow-2][kingCol+1].equals(oppColor+"H")) return true;
        if(kingRow+2 <= 7 && kingCol-1 >= 0 && board[kingRow+2][kingCol-1].equals(oppColor+"H")) return true;
        if(kingRow+2 <= 7 && kingCol+1 <= 7 && board[kingRow+2][kingCol+1].equals(oppColor+"H")) return true;
        if(kingRow-1 >= 0 && kingCol-2 >= 0 && board[kingRow-1][kingCol-2].equals(oppColor+"H")) return true;
        if(kingRow-1 >= 0 && kingCol+2 <= 7 && board[kingRow-1][kingCol+2].equals(oppColor+"H")) return true;
        if(kingRow+1 <= 7 && kingCol-2 >= 0 && board[kingRow+1][kingCol-2].equals(oppColor+"H")) return true;
        if(kingRow+1 <= 7 && kingCol+2 <= 7 && board[kingRow+1][kingCol+2].equals(oppColor+"H")) return true;


        //check for Rook
        for(int row=kingRow-1; row >= 0; row--) {
            if(board[row][kingCol].equals(oppColor+"R")) return true;
            if(!board[row][kingCol].equals("--")) break;
        }
        for(int row=kingRow+1; row <=7 ; row++) {
            if(board[row][kingCol].equals(oppColor+"R")) return true;
            if(!board[row][kingCol].equals("--")) break;
        }
        for(int col=kingCol-1; col >= 0; col--) {
            if(board[kingRow][col].equals(oppColor+"R")) return true;
            if(!board[kingRow][col].equals("--")) break;
        }
        for(int col=kingCol+1; col <=7; col++) {
            if(board[kingRow][col].equals(oppColor+"R")) return true;
            if(!board[kingRow][col].equals("--")) break;
        }

        //check for Bishop
        for(int row=kingRow-1, col=kingCol-1; row>=0 && col>=0; row--, col--) {
            if(board[row][col].equals(oppColor+"B")) return true;
            if(!(board[row][col].equals("--"))) break;
        }
        for(int row=kingRow-1, col=kingCol+1; row>=0 && col<=7; row--, col++) {
            if(board[row][col].equals(oppColor+"B")) return true;
            if(!(board[row][col].equals("--"))) break;
        }
        for(int row=kingRow+1, col=kingCol-1; row<=7 && col>=0; row++, col--) {
            if(board[row][col].equals(oppColor+"B")) return true;
            if(!(board[row][col].equals("--"))) break;
        }
        for(int row=kingRow+1, col=kingCol+1; row<=7 && col<=7; row++, col++) {
            if(board[row][col].equals(oppColor+"B")) return true;
            if(!(board[row][col].equals("--"))) break;
        }


        //check for Queen
        for(int row=kingRow-1; row >= 0; row--) {
            if(board[row][kingCol].equals(oppColor+"Q")) return true;
            if(!board[row][kingCol].equals("--")) break;
        }
        for(int row=kingRow+1; row <=7 ; row++) {
            if(board[row][kingCol].equals(oppColor+"Q")) return true;
            if(!board[row][kingCol].equals("--")) break;
        }
        for(int col=kingCol-1; col >= 0; col--) {
            if(board[kingRow][col].equals(oppColor+"Q")) return true;
            if(!board[kingRow][col].equals("--")) break;
        }
        for(int col=kingCol+1; col <=7; col++) {
            if(board[kingRow][col].equals(oppColor+"Q")) return true;
            if(!board[kingRow][col].equals("--")) break;
        }

        for(int row=kingRow-1, col=kingCol-1; row>=0 && col>=0; row--, col--) {
            if(board[row][col].equals(oppColor+"Q")) return true;
            if(!(board[row][col].equals("--"))) break;
        }
        for(int row=kingRow-1, col=kingCol+1; row>=0 && col<=7; row--, col++) {
            if(board[row][col].equals(oppColor+"Q")) return true;
            if(!(board[row][col].equals("--"))) break;
        }
        for(int row=kingRow+1, col=kingCol-1; row<=7 && col>=0; row++, col--) {
            if(board[row][col].equals(oppColor+"Q")) return true;
            if(!(board[row][col].equals("--"))) break;
        }
        for(int row=kingRow+1, col=kingCol+1; row<=7 && col<=7; row++, col++) {
            if(board[row][col].equals(oppColor+"Q")) return true;
            if(!(board[row][col].equals("--"))) break;
        }

        //check for Pawn
        if(color.equals("W")) {
            if(kingRow+1 <= 7 && kingCol-1 >=0 && board[kingRow+1][kingCol-1].equals("BP")) return true;
            if(kingRow+1 <= 7 && kingCol+1 <=7 && board[kingRow+1][kingCol+1].equals("BP")) return true;
        } else {
            if(kingRow-1 >= 0 && kingCol-1 >=0 && board[kingRow-1][kingCol-1].equals("WP")) return true;
            if(kingRow-1 >= 0 && kingCol+1 <=7 && board[kingRow-1][kingCol+1].equals("WP")) return true;
        }

        //check for King
        if(kingRow-1 >= 0 && kingCol-1 >= 0 && board[kingRow-1][kingCol-1].equals(oppColor+"K")) return true;
        if(kingRow-1 >= 0 && board[kingRow-1][kingCol].equals(oppColor+"K")) return true;
        if(kingRow-1 >= 0 && kingCol+1 <= 7 && board[kingRow-1][kingCol+1].equals(oppColor+"K")) return true;
        if(kingCol-1 >= 0 && board[kingRow][kingCol-1].equals(oppColor+"K")) return true;
        if(kingCol+1 <= 7 && board[kingRow][kingCol+1].equals(oppColor+"K")) return true;
        if(kingRow+1 <= 7 && kingCol-1 >= 0 && board[kingRow+1][kingCol-1].equals(oppColor+"K")) return true;
        if(kingRow+1 <= 7 && board[kingRow+1][kingCol].equals(oppColor+"K")) return true;
        if(kingRow+1 <= 7 && kingCol+1 <= 7 && board[kingRow+1][kingCol+1].equals(oppColor+"K")) return true;

        return false;
    }
}


class Pawn extends Piece {
    @Override
    public boolean validate(String[][] board, int from, int to) {

        if(from == to)
            return false;

        int fromRow = from/10;
        int fromCol = from%10;
        int toRow = to/10;
        int toCol = to%10;
        String selfColor = board[fromRow][fromCol].substring(0,1);
        if(Math.abs(fromRow-toRow) > 2 ) {
            return false;
        } else if(!(fromRow==1 || fromRow==6) && Math.abs(fromRow - toRow) > 1 ) {
            return false;
        } else if((selfColor.equals("W") && toRow-fromRow < 0) || selfColor.equals("B") && toRow-fromRow > 0) {
            return false;
        } else if(toCol == fromCol) {
            for(int row = fromRow+1; row <= toRow; row++) {
                if(!board[row][fromCol].equals("--"))
                    return false;
            }
        } else if(board[toRow][toCol].startsWith(selfColor)) {
            return false;
        } else if(Math.abs(toRow-fromRow)==1 && Math.abs(toCol-fromCol)==1 && board[toRow][toCol].startsWith(getOtherColor(selfColor))) {
            return true;
        } else if(Math.abs(toRow-fromRow)==1 && Math.abs(toCol-fromCol)==1 && (board[toRow][toCol].startsWith(selfColor)) || board[toRow][toCol].startsWith("--")) {
            return false;
        }

        String[][] tempBoard = new String[8][8];
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                tempBoard[i][j] = board[i][j];
        tempBoard[toRow][toCol] = tempBoard[fromRow][fromCol]; tempBoard[fromRow][fromCol] = "--";
        if(isKingInCheck(tempBoard, selfColor)) return false;

        return true;
    }
}

class Rook extends Piece {
    @Override
    public boolean validate(String[][] board, int from, int to) {

        if(from == to) return false;

        int fromRow = from/10;
        int fromCol = from%10;
        int toRow = to/10;
        int toCol = to%10;
        String selfColor = board[fromRow][fromCol].substring(0,1);

        if(!(fromRow==toRow || fromCol==toCol)) return false;


        int startRow = fromRow-toRow <= 0 ? fromRow : toRow;
        int endRow = fromRow-toRow <= 0 ? toRow : fromRow;
        int startCol = fromCol-toCol <=0 ? fromCol : toCol;
        int endCol = fromCol - toCol <=0 ? toCol : fromCol;

        for(int row=startRow+1; row < endRow; row++) {
            if(!(board[row][fromCol].equals("--")))
                return false;
        }

        for(int col=startCol+1; col < endCol; col++) {
            if(!(board[fromRow][col].equals("--")))
                return false;
        }

        if(board[toRow][toCol].startsWith(selfColor)) return false;


        String[][] tempBoard = new String[8][8];
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                tempBoard[i][j] = board[i][j];
        tempBoard[toRow][toCol] = tempBoard[fromRow][fromCol]; tempBoard[fromRow][fromCol] = "--";
        if(isKingInCheck(tempBoard, selfColor)) return false;

        return true;
    }
}

class Horse extends Piece {
    @Override
    public boolean validate(String[][] board, int from, int to) {

        if(from == to) return false;

        List<String> validMoves = new ArrayList<String>(){{
            add("2-1");
            add("21");
            add("-2-1");
            add("-21");
            add("-1-2");
            add("-12");
            add("1-2");
            add("12");
        }};

        int fromRow = from/10;
        int fromCol = from%10;
        int toRow = to/10;
        int toCol = to%10;
        String selfColor = board[fromRow][fromCol].substring(0,1);

        if(!validMoves.contains((fromRow-toRow)+""+(fromCol-toCol))) return false;

        if(board[toRow][toCol].startsWith(selfColor)) return false;

        String[][] tempBoard = new String[8][8];
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                tempBoard[i][j] = board[i][j];
        tempBoard[toRow][toCol] = tempBoard[fromRow][fromCol]; tempBoard[fromRow][fromCol] = "--";
        if(isKingInCheck(tempBoard, selfColor)) return false;

        return true;
    }
}

class Bishop extends Piece {
    @Override
    public boolean validate(String[][] board, int from, int to) {

        if(from == to) return false;

        int fromRow = from/10;
        int fromCol = from%10;
        int toRow = to/10;
        int toCol = to%10;
        String selfColor = board[fromRow][fromCol].substring(0,1);

        if(!(Math.abs(fromRow-toRow)==Math.abs(fromCol-toCol))) return false;

        if(board[toRow][toCol].startsWith(selfColor)) return false;

        int startRow = fromRow-toRow <=0 ? fromRow : toRow;
        int endRow = fromRow-toRow <=0 ? toRow : fromRow;
        int startCol = fromCol-toCol <=0 ? fromCol : toCol;
        int endCol = fromCol - toCol <=0 ? toCol : fromCol;

        for(int row=startRow+1, col=startCol+1; row < endRow && col < endCol; row++, col++) {
            if(!(board[row][col].equals("--")))
                return false;
        }

        String[][] tempBoard = new String[8][8];
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                tempBoard[i][j] = board[i][j];
        tempBoard[toRow][toCol] = tempBoard[fromRow][fromCol]; tempBoard[fromRow][fromCol] = "--";
        if(isKingInCheck(tempBoard, selfColor)) return false;

        return true;
    }
}

class Queen extends Piece {
    @Override
    public boolean validate(String[][] board, int from, int to) {

        if(from == to) return false;

        int fromRow = from/10;
        int fromCol = from%10;
        int toRow = to/10;
        int toCol = to%10;
        String selfColor = board[fromRow][fromCol].substring(0,1);

        if(!(fromRow==toRow || fromCol==toCol) && !(Math.abs(fromRow-toRow)==Math.abs(fromCol-toCol))) {
            return false;
        }

        if(board[toRow][toCol].startsWith(selfColor)) return false;

        boolean isMovingDiagonal = false;

        if(fromRow == toRow && fromCol == toCol)
            isMovingDiagonal = true;

        int startRow = fromRow-toRow <=0 ? fromRow : toRow;
        int endRow = fromRow-toRow <=0 ? toRow : fromRow;
        int startCol = fromCol-toCol <=0 ? fromCol : toCol;
        int endCol = fromCol - toCol <=0 ? toCol : fromCol;

        if(isMovingDiagonal) {
            for (int row = startRow + 1, col = startCol + 1; row < endRow && col < endCol; row++, col++) {
                if (!(board[row][col].equals("--")))
                    return false;
            }
        } else {
            for (int row = startRow + 1; row < endRow; row++) {
                if (!(board[row][fromCol].equals("--")))
                    return false;
            }

            for (int col = startCol + 1; col < endCol; col++) {
                if (!(board[fromRow][col].equals("--")))
                    return false;
            }
        }

        String[][] tempBoard = new String[8][8];
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                tempBoard[i][j] = board[i][j];
        tempBoard[toRow][toCol] = tempBoard[fromRow][fromCol]; tempBoard[fromRow][fromCol] = "--";
        if(isKingInCheck(tempBoard, selfColor)) return false;

        return true;
    }
}

class King extends Piece {
    @Override
    public boolean validate(String[][] board, int from, int to) {

        if(from == to) return false;

        int fromRow = from/10;
        int fromCol = from%10;
        int toRow = to/10;
        int toCol = to%10;
        String selfColor = board[fromRow][fromCol].substring(0,1);

        //castling check
        if(Math.abs(fromCol-toCol)==2 && fromCol == 4) {
            if(selfColor.equals("W")) {
                if(fromRow!=0) return false;
                if(fromCol-toCol < 0 && !board[0][7].equals("WR")) return false;
                if(fromCol-toCol > 0 && !board[0][0].equals("WR")) return false;
                if(fromCol-toCol < 0 && (!board[0][5].equals("--") || !board[0][6].equals("--"))) return false;
                if(fromCol-toCol > 0 && (!board[0][1].equals("--") || !board[0][2].equals("--") || !board[0][3].equals("--"))) return false;

                //move rook
                if(fromCol - toCol < 0) {
                    board[0][5]="WR"; board[0][7]="--";
                } else {
                    board[0][3]="WR"; board[0][0]="--";
                }
            } else {
                if(fromRow!=7) return false;
                if(fromCol-toCol < 0 && !board[7][7].equals("BR")) return false;
                if(fromCol-toCol > 0 && !board[7][0].equals("BR")) return false;
                if(fromCol-toCol > 0 && (!board[7][1].equals("--") || !board[7][2].equals("--") || !board[7][3].equals("--"))) return false;
                if(fromCol-toCol < 0 && (!board[7][5].equals("--") || !board[7][6].equals("--"))) return false;

                //move rook
                if(fromCol - toCol < 0) {
                    board[7][5]="BR"; board[7][7]="--";
                } else {
                    board[7][3]="BR"; board[7][0]="--";
                }
            }
            return true;
        }

        if(Math.abs(fromRow-toRow) > 1 || Math.abs(fromCol-toCol) > 1) {
            return false;
        }

        if(board[toRow][toCol].startsWith(selfColor)) return false;

        String[][] tempBoard = board; tempBoard[toRow][toCol] = tempBoard[fromRow][fromCol]; tempBoard[fromRow][fromCol] = "--";
        if(isKingInCheck(tempBoard, selfColor)) return false;

        return true;
    }
}
