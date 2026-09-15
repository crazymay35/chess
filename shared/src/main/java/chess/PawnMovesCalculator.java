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

    protected Collection<ChessMove> moveDirection() {
        List<ChessMove> possibleMoves = new ArrayList<>();
        //step 1, get the pawn piece at the stated position
        ChessPiece piece = getBoard().getPiece(getPosition());
        //if it's white... int direction = 1
        //if it's black... int direction = -1
        int direction;
        if (piece.getTeamColor() == ChessGame.TeamColor.WHITE) {
            direction = 1;
            ChessPosition forward = Direction.FORWARD.step(getPosition(),direction);
            if (inBounds(forward) && pieceIsNull(forward)) {
                if (getPosition().getRow() == 7) {
                    possibleMoves.add(new ChessMove(getPosition(),forward,ChessPiece.PieceType.QUEEN));
                    possibleMoves.add(new ChessMove(getPosition(),forward,ChessPiece.PieceType.ROOK));
                    possibleMoves.add(new ChessMove(getPosition(),forward,ChessPiece.PieceType.BISHOP));
                    possibleMoves.add(new ChessMove(getPosition(),forward,ChessPiece.PieceType.KNIGHT));
                    //it's not always going to be queen, the user would need to choose what piece
                }
                else {
                    possibleMoves.add(new ChessMove(getPosition(),forward,null));
                }
            }
            ChessPosition firstMove = Direction.FIRST_MOVE.step(getPosition(),direction);
            if (getPosition().getRow() == 2 && pieceIsNull(forward) && pieceIsNull(firstMove)) {
                possibleMoves.add(new ChessMove(getPosition(),firstMove,null));
            }
            ChessPosition attackRight = Direction.ATTACK_RIGHT.step(getPosition(),direction);
            if (inBounds(attackRight) && !pieceIsNull(attackRight)) {
                ChessPiece attackedRightPiece = getBoard().getPiece(attackRight);
                if (teamColorNotMatch(piece, attackedRightPiece)) {
                    if (getPosition().getRow() == 7) {
                        possibleMoves.add(new ChessMove(getPosition(),attackRight,ChessPiece.PieceType.QUEEN));
                        possibleMoves.add(new ChessMove(getPosition(),attackRight,ChessPiece.PieceType.ROOK));
                        possibleMoves.add(new ChessMove(getPosition(),attackRight,ChessPiece.PieceType.BISHOP));
                        possibleMoves.add(new ChessMove(getPosition(),attackRight,ChessPiece.PieceType.KNIGHT));
                        //it's not always going to be queen, the user would need to choose what piece
                    }
                    else {
                        possibleMoves.add(new ChessMove(getPosition(),attackRight,null));
                    }
                }
            }
            ChessPosition attackLeft = Direction.ATTACK_LEFT.step(getPosition(),direction);

            if (inBounds(attackLeft) && !pieceIsNull(attackLeft)) {
                ChessPiece attackedLeftPiece = getBoard().getPiece(attackLeft);
                if (teamColorNotMatch(piece, attackedLeftPiece)) {
                    if (getPosition().getRow() == 7) {
                        possibleMoves.add(new ChessMove(getPosition(),attackLeft,ChessPiece.PieceType.QUEEN));
                        possibleMoves.add(new ChessMove(getPosition(),attackLeft,ChessPiece.PieceType.ROOK));
                        possibleMoves.add(new ChessMove(getPosition(),attackLeft,ChessPiece.PieceType.BISHOP));
                        possibleMoves.add(new ChessMove(getPosition(),attackLeft,ChessPiece.PieceType.KNIGHT));
                        //it's not always going to be queen, the user would need to choose what piece
                    }
                    else {
                        possibleMoves.add(new ChessMove(getPosition(),attackLeft,null));
                    }
                }
            }
        }
        if (piece.getTeamColor() == ChessGame.TeamColor.BLACK) {
            direction = -1;
            ChessPosition forward = Direction.FORWARD.step(getPosition(),direction);
            if (inBounds(forward) && pieceIsNull(forward)) {
                if (getPosition().getRow() == 2) {
                    possibleMoves.add(new ChessMove(getPosition(),forward,ChessPiece.PieceType.QUEEN));
                    possibleMoves.add(new ChessMove(getPosition(),forward,ChessPiece.PieceType.ROOK));
                    possibleMoves.add(new ChessMove(getPosition(),forward,ChessPiece.PieceType.BISHOP));
                    possibleMoves.add(new ChessMove(getPosition(),forward,ChessPiece.PieceType.KNIGHT));
                    //it's not always going to be queen, the user would need to choose what piece
                }
                else {
                    possibleMoves.add(new ChessMove(getPosition(),forward,null));
                }
            }
            ChessPosition firstMove = Direction.FIRST_MOVE.step(getPosition(),direction);
            if (getPosition().getRow() == 7 && pieceIsNull(forward) && pieceIsNull(firstMove)) {
                possibleMoves.add(new ChessMove(getPosition(),firstMove,null));
            }
            ChessPosition attackRight = Direction.ATTACK_RIGHT.step(getPosition(),direction);
            if (inBounds(attackRight) && !pieceIsNull(attackRight)) {
                ChessPiece attackedRightPiece = getBoard().getPiece(attackRight);
                if (teamColorNotMatch(piece, attackedRightPiece)) {
                    if (getPosition().getRow() == 2) {
                        possibleMoves.add(new ChessMove(getPosition(),attackRight,ChessPiece.PieceType.QUEEN));
                        possibleMoves.add(new ChessMove(getPosition(),attackRight,ChessPiece.PieceType.ROOK));
                        possibleMoves.add(new ChessMove(getPosition(),attackRight,ChessPiece.PieceType.BISHOP));
                        possibleMoves.add(new ChessMove(getPosition(),attackRight,ChessPiece.PieceType.KNIGHT));
                        //it's not always going to be queen, the user would need to choose what piece
                    }
                    else {
                        possibleMoves.add(new ChessMove(getPosition(),attackRight,null));
                    }
                }
            }
            ChessPosition attackLeft = Direction.ATTACK_LEFT.step(getPosition(),direction);

            if (inBounds(attackLeft) && !pieceIsNull(attackLeft)) {
                ChessPiece attackedLeftPiece = getBoard().getPiece(attackLeft);
                if (teamColorNotMatch(piece, attackedLeftPiece)) {
                    if (getPosition().getRow() == 2) {
                        possibleMoves.add(new ChessMove(getPosition(),attackLeft,ChessPiece.PieceType.QUEEN));
                        possibleMoves.add(new ChessMove(getPosition(),attackLeft,ChessPiece.PieceType.ROOK));
                        possibleMoves.add(new ChessMove(getPosition(),attackLeft,ChessPiece.PieceType.BISHOP));
                        possibleMoves.add(new ChessMove(getPosition(),attackLeft,ChessPiece.PieceType.KNIGHT));
                        //it's not always going to be queen, the user would need to choose what piece
                    }
                    else {
                        possibleMoves.add(new ChessMove(getPosition(),attackLeft,null));
                    }
                }
            }
        }
        
        return possibleMoves;
    }
    @Override
    protected Collection<ChessMove> pieceMoves() {
        return moveDirection();
    }
}
