package chess;

import chess.Exceptions.IllegalMoveException;
import chess.Exceptions.NotValidPositionException;

import java.util.Arrays;

public class ChessBoard {
    String [][] board;

    public ChessBoard() {
        board = new String[][]{ {"WR", "WH", "WB", "WQ", "WK", "WB", "WH", "WR"},
                {"WP", "WP", "WP", "WP", "WP", "WP", "WP", "WP"},
                {"--", "--", "--", "--", "--", "--", "--", "--"},
                {"--", "--", "--", "--", "--", "--", "--", "--"},
                {"--", "--", "--", "--", "--", "--", "--", "--"},
                {"--", "--", "--", "--", "--", "--", "--", "--"},
                {"BP", "BP", "BP", "BP", "BP", "BP", "BP", "BP"},
                {"BR", "BH", "BB", "BQ", "BK", "BB", "BH", "BR"}};

    }

    public void move(int from, int to) throws Exception {
        if(from > 77 || to > 77)
            throw new NotValidPositionException("Not a valid position");
        String pieceToBeMoved = this.board[from/10][from%10];
        if(pieceToBeMoved == null || pieceToBeMoved.equals("--"))
            throw new NotValidPositionException("No piece available at from location");
        Piece p = PieceFactory.getPiece(pieceToBeMoved.substring(1));

        boolean isValid = p.validate(this.board, from, to);
        if(isValid) {
            if(pieceToBeMoved.endsWith("P") && (to/10 == 0 || to/10 ==7)) {
                this.board[to/10][to%10] = this.board[from / 10][from % 10].substring(0,1)+"Q"; //default promotion to queen
            } else {
                this.board[to/10][to % 10] = this.board[from / 10][from % 10];
            }
            this.board[from/10][from%10] = "--";
            System.out.println("Moved successfully. Piece: "+this.board[to/10][to%10]);
            System.out.println(Arrays.deepToString(this.board));
        } else {
            throw new IllegalMoveException("Move is not valid");
        }

    }

    public static void main(String[] args) {
        try {
            ChessBoard chessBoard = new ChessBoard();
            int from = 14; //from row*10 + from col
            int to = 34; //to row*10 + to col
            chessBoard.move(from, to);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


