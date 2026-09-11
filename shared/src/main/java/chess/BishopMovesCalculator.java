package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BishopMovesCalculator extends PieceMovesCalculator {
    public BishopMovesCalculator(ChessBoard board, ChessPosition position) {
        super(board,position);
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        ChessPiece myPiece = board.getPiece(myPosition);
        List<ChessMove> possibleMoves = new ArrayList<>();
        if (myPiece.getPieceType() == ChessPiece.PieceType.BISHOP) {
            //down and left
            for (int i = 1; i <= 8; i++) {
                if (myPosition.getRow()-i >= 1 && myPosition.getColumn()-i >= 1) {
                    ChessPosition newPosition = new ChessPosition(myPosition.getRow()-i, myPosition.getColumn()-i);
                    ChessPiece newPiece = board.getPiece(newPosition);
                    if (newPiece == null) {
                        possibleMoves.add(new ChessMove(myPosition, newPosition, null));
                    }
                    else if (newPiece.getTeamColor() != myPiece.getTeamColor()){
                        possibleMoves.add(new ChessMove(myPosition, newPosition, null));
                        break;
                    }
                    else {
                        break;
                    }
                }
            }

            //up and left
            for (int i = 1; i <= 8; i++) {
                if (myPosition.getRow()+i <= 8 && myPosition.getColumn()-i >= 1) {
                    ChessPosition newPosition = new ChessPosition(myPosition.getRow()+i, myPosition.getColumn()-i);
                    ChessPiece newPiece = board.getPiece(newPosition);
                    if (newPiece == null) {
                        possibleMoves.add(new ChessMove(myPosition, newPosition, null));
                    }
                    else if (newPiece.getTeamColor() != myPiece.getTeamColor()){
                        possibleMoves.add(new ChessMove(myPosition, newPosition, null));
                        break;
                    }
                    else {
                        break;
                    }
                }
            }

            //up and right
            for (int i = 1; i <= 8; i++) {
                if (myPosition.getRow()+i <= 8 && myPosition.getColumn()+i <= 8) {
                    ChessPosition newPosition = new ChessPosition(myPosition.getRow()+i, myPosition.getColumn()+i);
                    ChessPiece newPiece = board.getPiece(newPosition);
                    if (newPiece == null) {
                        possibleMoves.add(new ChessMove(myPosition, newPosition, null));
                    }
                    else if (newPiece.getTeamColor() != myPiece.getTeamColor()){
                        possibleMoves.add(new ChessMove(myPosition, newPosition, null));
                        break;
                    }
                    else {
                        break;
                    }
                }
            }

            //down and right
            for (int i = 1; i <= 8; i++) {
                if (myPosition.getRow()-i >= 1 && myPosition.getColumn()+i <= 8) {
                    ChessPosition newPosition = new ChessPosition(myPosition.getRow()-i, myPosition.getColumn()+i);
                    ChessPiece newPiece = board.getPiece(newPosition);
                    if (newPiece == null) {
                        possibleMoves.add(new ChessMove(myPosition, newPosition, null));
                    }
                    else if (newPiece.getTeamColor() != myPiece.getTeamColor()){
                        possibleMoves.add(new ChessMove(myPosition, newPosition, null));
                        break;
                    }
                    else {
                        break;
                    }
                }
            }
        }
        return possibleMoves;
    }
}
