package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * A class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessGame chessGame = (ChessGame) o;
        return Objects.equals(chessBoard, chessGame.chessBoard) && teamTurn == chessGame.teamTurn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(chessBoard, teamTurn);
    }

    private ChessBoard chessBoard = new ChessBoard();
    private TeamColor teamTurn;
    public ChessGame() {
        this.chessBoard.resetBoard();
        this.teamTurn = TeamColor.WHITE;
    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return teamTurn;
    }

    /**
     * Sets which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {
        teamTurn = team;
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    private ChessBoard deepCopy(ChessBoard ogBoard) {
        ChessBoard copyBoard = new ChessBoard();
        for (int row = 1; row <= 8; row++) {
            for (int col = 1; col <= 8; col++) {
                ChessPosition pos = new ChessPosition(row,col);
                ChessPiece copyPiece = ogBoard.getPiece(pos);
                if (copyPiece != null) {
                    copyBoard.addPiece(pos,copyPiece);
                }
            }
        }
        return copyBoard;
    }

    /**
     * Gets all valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        ChessPiece piece = chessBoard.getPiece(startPosition);
        if (piece == null) {
            return null;
        }
        Collection<ChessMove> moves = piece.pieceMoves(chessBoard, startPosition);
        Collection<ChessMove> validMoves = new ArrayList<>();
        //checks if the moves are valid according to check checkmate stalemate,
        // returns the ones that are valid out of that list
        ChessBoard realBoard = chessBoard;
        for (ChessMove move : moves) {
            ChessBoard copyBoard = deepCopy(chessBoard);
            copyBoard.addPiece(move.getEndPosition(),piece);
            copyBoard.addPiece(move.getStartPosition(),null);
            chessBoard = copyBoard;
            if (!isInCheck(piece.getTeamColor())) {
                validMoves.add(move);
            }
            chessBoard = realBoard;
        }
        for (ChessMove move : validMoves) {
            System.out.println(move.toString());
        }
        return validMoves;
    }

    /**
     * Makes a move in the chess game
     *
     * @param move chess move to perform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        //throw new RuntimeException("Not implemented");
        ChessPiece piece = chessBoard.getPiece(move.getStartPosition());
        Collection<ChessMove> validMoves = validMoves(move.getStartPosition());
        if (chessBoard.getPiece(move.getStartPosition()) == null) {
            throw new InvalidMoveException("there is not piece at " + move.getStartPosition());
        }
        else if (!validMoves.contains(move)) {
            throw new InvalidMoveException("move" + move.getStartPosition().toString() + "to" + move.getEndPosition().toString() + " is not a valid move");
        }
        else if(piece.getTeamColor() != teamTurn) {
            throw new InvalidMoveException("it is not " + piece.getTeamColor().toString() + " turn it is " + teamTurn);
        }
        else {
            if (move.getPromotionPiece() != null) {
                ChessPiece promotion = new ChessPiece(piece.getTeamColor(),move.getPromotionPiece());
                chessBoard.addPiece(move.getEndPosition(),promotion);
                chessBoard.addPiece(move.getStartPosition(),null);
            }
            else {
                chessBoard.addPiece(move.getEndPosition(),piece);
                chessBoard.addPiece(move.getStartPosition(),null);
            }
            if (teamTurn != TeamColor.BLACK) {
                teamTurn = TeamColor.BLACK;
            }
            else {
                teamTurn = TeamColor.WHITE;
            }
        }
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        ChessPiece king = new ChessPiece(teamColor, ChessPiece.PieceType.KING);
        Collection<ChessPosition> enemyPositions = new ArrayList<>();
        ChessPosition kingPos = new ChessPosition(1,1);
        for (int row = 1; row <= 8; row++) {
            for (int col = 1; col <= 8; col++) {
                ChessPosition pos = new ChessPosition(row,col);
                if (chessBoard.getPiece(pos) != null) {
                    if (chessBoard.getPiece(pos).equals(king)) {
                        kingPos = pos;
                    }
                    else if (chessBoard.getPiece(pos).getTeamColor() != teamColor) {
                        enemyPositions.add(pos);
                    }
                }
            }
        }
        for (ChessPosition pos : enemyPositions) {
            ChessPiece piece = chessBoard.getPiece(pos);
            for (ChessMove move : piece.pieceMoves(chessBoard,pos)) {
                if (move.getEndPosition().equals(kingPos)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */

    public boolean isInCheckmate(TeamColor teamColor) {
        boolean inCheck = isInCheck(teamColor);
        Collection<ChessMove> moves = new ArrayList<>();
        for (int row = 1; row <= 8; row++) {
            for (int col = 1; col <= 8; col++) {
                ChessPosition pos = new ChessPosition(row,col);
                if (chessBoard.getPiece(pos) != null && chessBoard.getPiece(pos).getTeamColor() == teamColor) {
                    moves.addAll(validMoves(pos));
                }
            }
        }
        return inCheck && moves.isEmpty();
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves while not in check.
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        boolean inCheck = isInCheck(teamColor);
        Collection<ChessMove> moves = new ArrayList<>();
        for (int row = 1; row <= 8; row++) {
            for (int col = 1; col <= 8; col++) {
                ChessPosition pos = new ChessPosition(row,col);
                if (chessBoard.getPiece(pos) != null && chessBoard.getPiece(pos).getTeamColor() == teamColor) {
                    moves.addAll(validMoves(pos));
                }
            }
        }
        return !inCheck && moves.isEmpty();
    }

    /**
     * Sets this game's chessboard to a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        chessBoard = board;
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return chessBoard;
    }
}
