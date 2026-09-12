package chess;

//import java.util.ArrayList;
import java.util.Collection;
//import java.util.List;
import java.util.stream.Stream;

public class BishopMovesCalculator extends PieceMovesCalculator {
    public BishopMovesCalculator(ChessBoard board, ChessPosition position) {
        super(board, position);
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> moveUpRight = moveDirection(board, Direction.UP_RIGHT);
        Collection<ChessMove> moveDownRight = moveDirection(board, Direction.DOWN_RIGHT);
        Collection<ChessMove> moveDownLeft = moveDirection(board, Direction.DOWN_LEFT);
        Collection<ChessMove> moveUpLeft = moveDirection(board, Direction.UP_LEFT);

        return  Stream.of(moveUpRight, moveUpLeft, moveDownRight, moveDownLeft).flatMap(Collection::stream).toList();
    }
}
