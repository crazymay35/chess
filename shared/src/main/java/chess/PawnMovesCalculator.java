package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PawnMovesCalculator extends PieceMovesCalculator{
    public PawnMovesCalculator (ChessBoard board, ChessPosition position) {
        super(board,position);
    }
    protected enum Direction {
        FIRST_MOVE(2,0),
        UP(1,0),
        ATTACK_RIGHT(1,1),
        ATTACK_LEFT(1,-1);

        private final int row;
        private final int col;

        Direction(int row, int col) {
            this.row = row;
            this.col = col;
        }
        public ChessPosition step(ChessPosition position) {
            return new ChessPosition(position.getRow() + this.row, position.getColumn() + this.col);
        }
    }

    private void add(ChessMove move, Collection<ChessMove> possibleMoves) {
        if (move != null) {
            possibleMoves.add(move);
        }
    }

    private ChessMove validMove(Direction direction) {
        if (inBounds(direction.step(getPosition()))) {
            return new ChessMove(getPosition(), direction.step(getPosition()), null);
        }
        return null;
    }

    @Override
    protected Collection<ChessMove> moveDirection() {
        List<ChessMove> possibleMoves = new ArrayList<>();

        ChessMove up = validMove(Direction.UP);
        if (up != null && pieceIsNull(up.getEndPosition())) {
            add(up,possibleMoves);
        }
        ChessMove firstMove = validMove(Direction.FIRST_MOVE);
        if (up != null && pieceIsNull(up.getEndPosition()) && getPosition().getRow() == 2
                && firstMove != null && pieceIsNull(firstMove.getEndPosition())) {
            add(firstMove,possibleMoves);
        }

        ChessPiece piece = getBoard().getPiece(getPosition());
        ChessMove attackLeft = validMove(Direction.ATTACK_LEFT);
        ChessPiece enemy_left = getBoard().getPiece(attackLeft.getEndPosition());
        if (attackLeft != null && !pieceIsNull(attackLeft.getEndPosition()) && teamColorNotMatch(piece, enemy_left)) {
            add(attackLeft,possibleMoves);
        }
        ChessMove attackRight = validMove(Direction.ATTACK_LEFT);
        ChessPiece enemy_right = getBoard().getPiece(attackRight.getEndPosition());
        if (attackRight != null && !pieceIsNull(attackRight.getEndPosition()) && teamColorNotMatch(piece, enemy_right)) {
            add(attackRight,possibleMoves);
        }
        return possibleMoves;
    }
    @Override
    protected Collection<ChessMove> pieceMoves() {
        return moveDirection();
    }
}
