package chess;


import java.util.Collection;
import java.util.stream.Stream;

public class RookMovesCalculator extends PieceMovesCalculator{
    public RookMovesCalculator(ChessBoard board, ChessPosition position) { super(board,position); }

    @Override
    protected Collection<ChessMove> pieceMoves() {
        Collection<ChessMove> moveUp = moveDirection(Direction.UP);
        Collection<ChessMove> moveRight = moveDirection(Direction.RIGHT);
        Collection<ChessMove> moveDown = moveDirection(Direction.DOWN);
        Collection<ChessMove> moveLeft = moveDirection(Direction.LEFT);
        return Stream.of(moveUp,moveRight,moveDown,moveLeft).flatMap(Collection::stream).toList();
    }
}
