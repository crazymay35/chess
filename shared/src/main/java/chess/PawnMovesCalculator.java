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

    /*private void add(ChessMove move, Collection<ChessMove> possibleMoves) {
        if (move != null) {
            possibleMoves.add(move);
        }
    }

    private ChessMove validMove(Direction direction) {
        if (inBounds(direction.step(getPosition()))) {
            return new ChessMove(getPosition(), direction.step(getPosition()), null);
        }
        return null;
    }

    @Override
    protected Collection<ChessMove> moveDirection() {
        List<ChessMove> possibleMoves = new ArrayList<>();

        ChessMove up = validMove(Direction.FORWARD);
        if (up != null && pieceIsNull(up.getEndPosition())) {
            add(up,possibleMoves);
        }
        ChessMove firstMove = validMove(Direction.FIRST_MOVE);
        if (up != null && pieceIsNull(up.getEndPosition()) && getPosition().getRow() == 2
                && firstMove != null && pieceIsNull(firstMove.getEndPosition())) {
            add(firstMove,possibleMoves);
        }

        ChessPiece piece = getBoard().getPiece(getPosition());
        ChessMove attackLeft = validMove(Direction.ATTACK_LEFT);
        ChessPiece enemy_left = getBoard().getPiece(attackLeft.getEndPosition());
        if (attackLeft != null && !pieceIsNull(attackLeft.getEndPosition()) && teamColorNotMatch(piece, enemy_left)) {
            add(attackLeft,possibleMoves);
        }
        ChessMove attackRight = validMove(Direction.ATTACK_LEFT);
        ChessPiece enemy_right = getBoard().getPiece(attackRight.getEndPosition());
        if (attackRight != null && !pieceIsNull(attackRight.getEndPosition()) && teamColorNotMatch(piece, enemy_right)) {
            add(attackRight,possibleMoves);
        }
        return possibleMoves;
    }*/

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
                    //it's not always going to be queen, the user would need to choose what piece
                }
                else {
                    possibleMoves.add(new ChessMove(getPosition(),forward,null));
                }
            }
            ChessPosition firstMove = Direction.FIRST_MOVE.step(getPosition(),direction);
            if (getPosition().getRow() == 2 && inBounds(forward) && pieceIsNull(forward) && inBounds(firstMove) && pieceIsNull(firstMove)) {
                possibleMoves.add(new ChessMove(getPosition(),firstMove,null));
            }
            ChessPosition attackRight = Direction.ATTACK_RIGHT.step(getPosition(),direction);
            if (inBounds(attackRight) && !pieceIsNull(attackRight)) {
                if (getPosition().getRow() == 7) {
                    possibleMoves.add(new ChessMove(getPosition(),attackRight,ChessPiece.PieceType.QUEEN));
                    //it's not always going to be queen, the user would need to choose what piece
                }
                else {
                    possibleMoves.add(new ChessMove(getPosition(),attackRight,null));
                }
            }
            ChessPosition attackLeft = Direction.ATTACK_LEFT.step(getPosition(),direction);
            if (inBounds(attackLeft) && !pieceIsNull(attackLeft)) {
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
        if (piece.getTeamColor() == ChessGame.TeamColor.BLACK) {
            direction = -1;
        }
        
        return possibleMoves;
    }
    @Override
    protected Collection<ChessMove> pieceMoves() {
        return moveDirection();
    }
}
