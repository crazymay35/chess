package chess;

import java.util.Collection;
import java.util.List;

public class KnightMovesCalculator extends PieceMovesCalculator {
    public KnightMovesCalculator(ChessBoard board, ChessPosition position) {
        super(board,position);
    }

    @Override
    protected Collection<ChessMove> pieceMoves() {
        return List.of();
    }
}
