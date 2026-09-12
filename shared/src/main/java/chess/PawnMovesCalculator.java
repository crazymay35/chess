package chess;

import java.util.Collection;
import java.util.List;

public class PawnMovesCalculator extends PieceMovesCalculator{
    protected enum Direction {
        FIRST_TURN(2,0),
        UP(1,0),
        ATTACK_RIGHT(1,1),
        ATTACK_LEFT(1,-1);

        private final int row;
        private final int col;

        Direction(int row, int col) {
            this.row = row;
            this.col = col;
        }
        public ChessPosition step(ChessPosition position, int length) {
            return new ChessPosition(position.getRow() + (this.row * length), position.getColumn() + (this.col * length));
        }
    }
    public PawnMovesCalculator (ChessBoard board, ChessPosition position) {
        super(board,position);
    }
    @Override
    protected Collection<ChessMove> pieceMoves() {
        return List.of();
    }
}
