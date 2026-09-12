package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

abstract class PieceMovesCalculator {
    private final ChessBoard board;
    private final ChessPosition position;
    protected PieceMovesCalculator(ChessBoard board, ChessPosition position) {
        this.board = board;
        this.position = position;
    }
    protected ChessBoard getBoard() { return board; }
    protected ChessPosition getPosition() { return position; }

    protected abstract Collection<ChessMove> pieceMoves();

    protected boolean inBounds(ChessPosition position) {
        return position.getRow() >=1 && position.getRow() <= 8 && position.getColumn() >= 1 && position.getColumn() <= 8;
    }
    protected boolean pieceIsNull(ChessPosition position) {
        return board.getPiece(position) == null;
    }
    protected boolean teamColorNotMatch(ChessPiece newPiece, ChessPiece myPiece) {
        return newPiece.getTeamColor() != myPiece.getTeamColor();
    }
    protected enum Direction {
        NULL(0,0),
        UP(1,0),
        UP_RIGHT(1,1),
        RIGHT(0,1),
        DOWN_RIGHT(-1,1),
        DOWN(-1,0),
        DOWN_LEFT(-1,-1),
        LEFT(0,-1),
        UP_LEFT(1,-1);

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
    protected Collection<ChessMove> moveDirection(Direction direction) {
        ChessPiece piece = board.getPiece(position);
        List<ChessMove> possibleMoves = new ArrayList<>();
        int i = 1;
        while (inBounds(direction.step(position, i))) {
            ChessPosition newPosition = direction.step(position, i);
            ChessPiece newPiece = board.getPiece(newPosition);
            if (pieceIsNull(newPosition)) {
                possibleMoves.add(new ChessMove(position, newPosition, null));
            }
            else if(teamColorNotMatch(newPiece, piece)) {
                possibleMoves.add(new ChessMove(position, newPosition, null));
                break;
            }
            else { break; }
            i++;
        }
        return possibleMoves;
    }
    protected Collection<ChessMove> moveDirection() {
        return Collections.emptyList();
    }
}
