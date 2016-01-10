package chess;

import java.util.HashMap;
import java.util.Map;

public class PieceFactory {
    static Map<String, Piece> pieceMap = new HashMap<String, Piece>();
    static {
        pieceMap.put("P", new Pawn());
        pieceMap.put("R", new Rook());
        pieceMap.put("H", new Horse());
        pieceMap.put("B", new Bishop());
        pieceMap.put("Q", new Queen());
        pieceMap.put("K", new King());
    }

    public static Piece getPiece(String piece) {
        return pieceMap.get(piece);
    }
}
