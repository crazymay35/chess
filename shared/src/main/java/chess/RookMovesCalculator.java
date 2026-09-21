package chess;


import java.util.ArrayList;
import java.util.Collection;

public class RookMovesCalculator extends PieceMovesCalculator{
    public RookMovesCalculator(ChessBoard board, ChessPosition position) {
        super(board,position);
    }

    @Override
    protected Collection<ChessMove> pieceMoves() {
        Collection<ChessMove> allMoves = new ArrayList<>();
        allMoves.addAll(moveDirection(Direction.UP,8));
        allMoves.addAll(moveDirection(Direction.RIGHT,8));
        allMoves.addAll(moveDirection(Direction.DOWN,8));
        allMoves.addAll(moveDirection(Direction.LEFT,8));
        return allMoves;
    }
}
