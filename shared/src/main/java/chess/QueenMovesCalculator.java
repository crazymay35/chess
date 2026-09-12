package chess;

import java.util.Collection;
import java.util.stream.Stream;

public class QueenMovesCalculator extends PieceMovesCalculator{
    public QueenMovesCalculator(ChessBoard board, ChessPosition position) {
        super(board, position);
    }
    @Override
    protected Collection<ChessMove> pieceMoves() {
        Collection<ChessMove> moveUp = moveDirection(Direction.UP);
        Collection<ChessMove> moveUpRight = moveDirection(Direction.UP_RIGHT);
        Collection<ChessMove> moveRight = moveDirection(Direction.RIGHT);
        Collection<ChessMove> moveDownRight = moveDirection(Direction.DOWN_RIGHT);
        Collection<ChessMove> moveDown = moveDirection(Direction.DOWN);
        Collection<ChessMove> moveDownLeft = moveDirection(Direction.DOWN_LEFT);
        Collection<ChessMove> moveLeft = moveDirection(Direction.LEFT);
        Collection<ChessMove> moveUpLeft = moveDirection(Direction.UP_LEFT);

        return Stream.of(moveUp,moveUpRight,moveRight,moveDownRight,moveDown,moveDownLeft,moveLeft,moveUpLeft).flatMap(Collection::stream).toList();
    }
}
