package chess;

import java.util.*;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessBoard that = (ChessBoard) o;
        return Objects.deepEquals(board, that.board);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }

    private final ChessPiece[][] board;
    public ChessBoard() { this.board = new ChessPiece[8][8]; }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) { board[position.getRow()-1][position.getColumn()-1] = piece; }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        return board[position.getRow()-1][position.getColumn()-1];
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {

        Collection<ChessPiece> whitePieces = List.of(
                new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.ROOK),
                new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.KNIGHT),
                new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.BISHOP),
                new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.QUEEN),
                new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.KING),
                new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.BISHOP),
                new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.KNIGHT),
                new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.ROOK)
        );
        addToBoard(whitePieces,1);
        addPawn(ChessGame.TeamColor.WHITE, 2);

        Collection<ChessPiece> blackPieces = List.of(
                new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.ROOK),
                new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.KNIGHT),
                new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.BISHOP),
                new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.QUEEN),
                new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.KING),
                new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.BISHOP),
                new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.KNIGHT),
                new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.ROOK)
        );
        addToBoard(blackPieces,8);
        addPawn(ChessGame.TeamColor.BLACK, 7);
    }

    private void addPawn(ChessGame.TeamColor teamColor, int row) {
        for (int i = 1; i <= 8; i++) {
            addPiece(new ChessPosition(row,i),new ChessPiece(teamColor,ChessPiece.PieceType.PAWN));
        }
    }
    private void addToBoard(Collection<ChessPiece> pieces, int row) {
        int column = 1;
        for (ChessPiece piece : pieces) {
            addPiece(new ChessPosition(row,column),piece);
            column++;
        }
    }

    @Override
    public String toString() {
        StringBuilder board = new StringBuilder();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (this.board[row][col] == null) {
                    board.append("| ");
                }
                else {
                    board.append("|");
                    board.append(this.board[row][col]);
                }
            }
            board.append("|\n");
        }
        return board.toString();
    }
}
