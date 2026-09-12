package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class KingMovesCalculator extends PieceMovesCalculator {

    public KingMovesCalculator(ChessBoard board, ChessPosition position) {
        super(board,position);
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
    protected Collection<ChessMove> moveDirection(Direction direction) {
        List<ChessMove> possibleMoves = new ArrayList<>();
        add(validMove(Direction.UP), possibleMoves);
        add(validMove(Direction.UP_RIGHT), possibleMoves);
        add(validMove(Direction.RIGHT), possibleMoves);
        add(validMove(Direction.DOWN_RIGHT), possibleMoves);
        add(validMove(Direction.DOWN), possibleMoves);
        add(validMove(Direction.DOWN_LEFT), possibleMoves);
        add(validMove(Direction.LEFT), possibleMoves);
        add(validMove(Direction.UP_LEFT), possibleMoves);
        return possibleMoves;
    }
    @Override
    protected Collection<ChessMove> pieceMoves() {
        return moveDirection(Direction.NULL);
    }
}
