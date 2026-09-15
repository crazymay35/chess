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
        FORWARD(1,0),
        ATTACK_RIGHT(1,1),
        ATTACK_LEFT(1,-1);

        private final int row;
        private final int col;

        Direction(int row, int col) {
            this.row = row;
            this.col = col;
        }
        public ChessPosition step(ChessPosition position, int direction) {
            return new ChessPosition(position.getRow() + (this.row * direction), position.getColumn() + (this.col * direction));
        }
    }

    private void possiblePromotions(Collection<ChessMove> possibleMoves, ChessPosition newPosition, int rowPiecePromote) {
        if (getPosition().getRow() == rowPiecePromote) {
            possibleMoves.add(new ChessMove(getPosition(),newPosition,ChessPiece.PieceType.QUEEN));
            possibleMoves.add(new ChessMove(getPosition(),newPosition,ChessPiece.PieceType.ROOK));
            possibleMoves.add(new ChessMove(getPosition(),newPosition,ChessPiece.PieceType.BISHOP));
            possibleMoves.add(new ChessMove(getPosition(),newPosition,ChessPiece.PieceType.KNIGHT));
        }
        else {
            possibleMoves.add(new ChessMove(getPosition(),newPosition,null));
        }
    }

    private void forward(Collection<ChessMove> possibleMoves, int rowPiecePromote, int direction) {
        ChessPosition forward = Direction.FORWARD.step(getPosition(),direction);
        if (inBounds(forward) && pieceIsNull(forward)) {
            possiblePromotions(possibleMoves,forward,rowPiecePromote);
        }
    }
    private void firstMove(Collection<ChessMove> possibleMoves, int rowPiecePromote, int direction) {
        ChessPosition firstMove = Direction.FIRST_MOVE.step(getPosition(),direction);
        ChessPosition forward = Direction.FORWARD.step(getPosition(),direction);
        if (getPosition().getRow() == rowPiecePromote && pieceIsNull(forward) && pieceIsNull(firstMove)) {
            possibleMoves.add(new ChessMove(getPosition(),firstMove,null));
        }
    }
    private void attack(Collection<ChessMove> possibleMoves, int rowPiecePromote, ChessPosition attackPosition) {
        ChessPiece piece = getBoard().getPiece(getPosition());
        if (inBounds(attackPosition) && !pieceIsNull(attackPosition)) {
            ChessPiece attackedRightPiece = getBoard().getPiece(attackPosition);
            if (teamColorNotMatch(piece, attackedRightPiece)) {
                possiblePromotions(possibleMoves,attackPosition,rowPiecePromote);
            }
        }
    }
    protected Collection<ChessMove> moveDirection() {
        List<ChessMove> possibleMoves = new ArrayList<>();
        ChessPiece piece = getBoard().getPiece(getPosition());
        int direction;

        if (piece.getTeamColor() == ChessGame.TeamColor.WHITE) {
            direction = 1;

            forward(possibleMoves,7,direction);
            firstMove(possibleMoves,2,direction);

            ChessPosition attackRight = Direction.ATTACK_RIGHT.step(getPosition(),direction);
            attack(possibleMoves,7, attackRight);

            ChessPosition attackLeft = Direction.ATTACK_LEFT.step(getPosition(),direction);
            attack(possibleMoves,7, attackLeft);
        }
        if (piece.getTeamColor() == ChessGame.TeamColor.BLACK) {
            direction = -1;

            forward(possibleMoves,2,direction);
            firstMove(possibleMoves,7,direction);

            ChessPosition attackRight = Direction.ATTACK_RIGHT.step(getPosition(),direction);
            attack(possibleMoves,2, attackRight);

            ChessPosition attackLeft = Direction.ATTACK_LEFT.step(getPosition(),direction);
            attack(possibleMoves,2, attackLeft);
        }
        return possibleMoves;
    }
    @Override
    protected Collection<ChessMove> pieceMoves() {
        return moveDirection();
    }
}
