package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class KnightMovesCalculator extends PieceMovesCalculator {
    public KnightMovesCalculator(ChessBoard board, ChessPosition position) {
        super(board,position);
    }

    protected enum Direction {
        NULL(0,0),
        UP_RIGHT(2,1),
        UP_LEFT(2,-1),
        RIGHT_UP(1,2),
        RIGHT_DOWN(-1,2),
        DOWN_RIGHT(-2,1),
        DOWN_LEFT(-2,-1),
        LEFT_UP(1,-2),
        LEFT_DOWN(-1,-2);

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

    private ChessMove validMove(Direction direction) {
        ChessPiece piece = getBoard().getPiece(getPosition());
        if (inBounds(direction.step(getPosition(), 1))) {
            ChessPosition newPosition = direction.step(getPosition(), 1);
            ChessPiece newPiece = getBoard().getPiece(newPosition);
            if (pieceIsNull(newPosition) || teamColorNotMatch(newPiece, piece)) {
                return new ChessMove(getPosition(), newPosition, null);
            }
        }
        return null;
    }

    private void add(ChessMove move, Collection<ChessMove> possibleMoves) {
        if (move != null) {
            possibleMoves.add(move);
        }
    }

    @Override
    protected Collection<ChessMove> moveDirection(PieceMovesCalculator.Direction direction) {
        List<ChessMove> possibleMoves = new ArrayList<>();
        add(validMove(Direction.UP_RIGHT), possibleMoves);
        add(validMove(Direction.UP_LEFT), possibleMoves);
        add(validMove(Direction.RIGHT_UP), possibleMoves);
        add(validMove(Direction.RIGHT_DOWN), possibleMoves);
        add(validMove(Direction.DOWN_RIGHT), possibleMoves);
        add(validMove(Direction.DOWN_LEFT), possibleMoves);
        add(validMove(Direction.LEFT_UP), possibleMoves);
        add(validMove(Direction.LEFT_DOWN), possibleMoves);
        return possibleMoves;
    }

    @Override
    protected Collection<ChessMove> pieceMoves() {
        return moveDirection(PieceMovesCalculator.Direction.NULL);
    }
}
