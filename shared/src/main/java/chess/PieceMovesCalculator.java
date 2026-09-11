package chess;

import java.util.Collection;

abstract class PieceMovesCalculator {
    private final ChessBoard board;
    private final ChessPosition position;
    public PieceMovesCalculator(ChessBoard board, ChessPosition position) {
        this.position = position;
        this.board = board;
    }
    public abstract Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition);

    public ChessBoard getBoard() { return board; }

    public ChessPosition getPosition() { return position; }
}
