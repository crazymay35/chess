package chess;

import java.util.Collection;
import java.util.List;

public class KingMovesCalculator extends PieceMovesCalculator {

    public KingMovesCalculator(ChessBoard board, ChessPosition position) {
        super(board,position);
    }
    @Override
    protected Collection<ChessMove> pieceMoves() {
        return List.of();
    }
}
