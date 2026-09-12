package chess;

import java.util.Collection;
import java.util.stream.Stream;

public class BishopMovesCalculator extends PieceMovesCalculator {
    public BishopMovesCalculator(ChessBoard board, ChessPosition position) {
        super(board, position);
    }

    @Override
    public Collection<ChessMove> pieceMoves() {
        Collection<ChessMove> moveUpRight = moveDirection(Direction.UP_RIGHT);
        Collection<ChessMove> moveDownRight = moveDirection(Direction.DOWN_RIGHT);
        Collection<ChessMove> moveDownLeft = moveDirection(Direction.DOWN_LEFT);
        Collection<ChessMove> moveUpLeft = moveDirection(Direction.UP_LEFT);

        return  Stream.of(moveUpRight, moveUpLeft, moveDownRight, moveDownLeft).flatMap(Collection::stream).toList();
    }
}
