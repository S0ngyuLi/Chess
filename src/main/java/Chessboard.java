/**
 * Created by songyuli on 9/17/17.
 *
 * Chessboard object, which hold chessboard cells as composition, as well as player information
 *
 */
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Chessboard {
    private ChessboardCell [][] cells;
    private HashSet<Piece> box; // A box of pieces that have been captured
    private King kingForA;
    private King kingForB;

    private HashSet<Piece> piecesForPlayerA; // All of A's pieces on board (not captured)
    private HashSet<Piece> piecesForPlayerB; // All of B's pieces on board (not captured)

    public Player getPlayerA() {
        return playerA;
    }

    public Player getPlayerB() {
        return playerB;
    }

    private Player playerA; // Upper side player
    private Player playerB; // Lower side player
    public AppDelegate appDelegate;

    public Chessboard(Player playerA, Player playerB, AppDelegate delegate){
        this.cells = new ChessboardCell[8][8];
        this.playerA = playerA;
        this.playerB = playerB;
        this.appDelegate = delegate;
        this.box = new HashSet<Piece>();
        this.piecesForPlayerA = new HashSet<>();
        this.piecesForPlayerB = new HashSet<>();
    }

    public void initializeBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cells[i][j] = new ChessboardCell(i, j, this);
            }
        }

        for (int i = 0; i < 8; i++) {
            piecesForPlayerA.add(new Pawn(this.playerA, cells[i][1]));
            piecesForPlayerB.add(new Pawn(this.playerB, cells[i][6]));
        }

        this.kingForA = new King(this.playerA, cells[3][0]);
        this.kingForB = new King(this.playerB, cells[3][7]);

        piecesForPlayerA.add(kingForA);
        piecesForPlayerB.add(kingForB);

        piecesForPlayerA.add(new Queen(this.playerA, cells[4][0]));
        piecesForPlayerB.add(new Queen(this.playerB, cells[4][7]));

        piecesForPlayerA.add(new Rook(this.playerA, cells[0][0]));
        piecesForPlayerA.add(new Rook(this.playerA, cells[7][0]));
        piecesForPlayerB.add(new Rook(this.playerB, cells[0][7]));
        piecesForPlayerB.add(new Rook(this.playerB, cells[7][7]));

        piecesForPlayerA.add(new Knight(this.playerA, cells[1][0]));
        piecesForPlayerA.add(new Knight(this.playerA, cells[6][0]));
        piecesForPlayerB.add(new Knight(this.playerB, cells[1][7]));
        piecesForPlayerB.add(new Knight(this.playerB, cells[6][7]));

        piecesForPlayerA.add(new Bishop(this.playerA, cells[2][0]));
        piecesForPlayerA.add(new Bishop(this.playerA, cells[5][0]));
        piecesForPlayerB.add(new Bishop(this.playerB, cells[2][7]));
        piecesForPlayerB.add(new Bishop(this.playerB, cells[5][7]));
    }

    // Function evaluates to true if A checkmates B

    private boolean isBInCheck() {
        for (Piece piece: this.piecesForPlayerA) {
            ArrayList<ChessboardCell> possibleRoutesA = piece.getAllPossibleRoutes();
            if (possibleRoutesA.contains(kingForB.getChessboardCell())) {
                return true;
            }
        }
        return false;
    }

    private boolean isAInCheck() {
        for (Piece piece: this.piecesForPlayerB) {
            ArrayList<ChessboardCell> possibleRoutesB = piece.getAllPossibleRoutes();
            if (possibleRoutesB.contains(kingForA.getChessboardCell())) {
                return true;
            }
        }
        return false;
    }

    private boolean checkCheckMateForA() {
        for (Piece piece: this.piecesForPlayerA) {
            ArrayList<ChessboardCell> possibleRoutesA = piece.getAllPossibleRoutes();
            if(possibleRoutesA.contains(kingForB.getChessboardCell())) {
                for (Piece rivalPiece : this.piecesForPlayerB) {
                    ArrayList<ChessboardCell> possibleRoutesB = rivalPiece.getAllPossibleRoutes();
                    for (ChessboardCell possibleMove: possibleRoutesB) {
                        // Assume possible moves
                        rivalPiece.willMove(possibleMove, false);

                        if(isBInCheck()) {
                            rivalPiece.resume();
                            return true;
                        }
                        rivalPiece.resume();

                        // Resume possible moves
                    }
                }

            }
        }
        return false;
    }

    private boolean checkCheckMateForB() {
        for (Piece piece: this.piecesForPlayerB) {
            ArrayList<ChessboardCell> possibleRoutesB = piece.getAllPossibleRoutes();
            if(possibleRoutesB.contains(kingForA.getChessboardCell())) {
                for (Piece rivalPiece : this.piecesForPlayerA) {
                    ArrayList<ChessboardCell> possibleRoutesA = rivalPiece.getAllPossibleRoutes();
                    for (ChessboardCell possibleMove: possibleRoutesB) {
                        // Assume possible moves
                        rivalPiece.willMove(possibleMove, false);

                        if(isAInCheck()) {
                            rivalPiece.resume();
                            return true;
                        }
                        rivalPiece.resume();
                    }
                }
            }
        }
        return false;
    }

    public void checkCheckMate() {
        if (checkCheckMateForA()) {
            appDelegate.endWithWinner(playerA);
            return;
        }
        if (checkCheckMateForB()) {
            appDelegate.endWithWinner(playerB);
            return;
        }
    }
    /*
     * Get Cell at a certain location.
     */
    public ChessboardCell getCell(int x, int y) {
        if (x < 8 && x >= 0 && y < 8 && y >= 0) {
            return cells[x][y];
        }
        return null;
    }
    /*
     * Call this function if we want to capture piece at a certain cell.
     * Warning: Call it only if you have verified that targetCell does hold an enemy piece
     */
    public void capturePieces(ChessboardCell targetCell) {
        Player owner = targetCell.getPiece().getOwner();
        if (owner == playerA) {
            piecesForPlayerA.remove(targetCell.getPiece());
        }
        else {
            piecesForPlayerB.remove(targetCell.getPiece());
        }
        box.add(targetCell.getPiece());
        targetCell.getPiece().willRemoveFromBoard();
    }

    public void traverseCell(ArrayList<ChessboardCell> ret, Command command) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (command.checkViableRoute(this.cells[i][j])) {
                    ret.add(cells[i][j]);
                }
            }
        }
    }
    /*
     * Print the internal state of a chessboard. Pieces are represented by char. Uppercase = PlayerA, lowercase = Player B
     */
    public void debugPrint() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String pieceKind = "";
                if (cells[i][j].isVacant()) {
                    pieceKind = " ";
                }
                else {
                    Piece piece = cells[i][j].getPiece();
                    if (piece instanceof Bishop) {
                        pieceKind = "B";
                    }
                    else if (piece instanceof King) {
                        pieceKind = "K";
                    }
                    else if (piece instanceof Knight) {
                        pieceKind = "N";
                    }
                    else if (piece instanceof Pawn) {
                        pieceKind = "P";
                    }
                    else if (piece instanceof Queen) {
                        pieceKind = "Q";
                    }
                    else if (piece instanceof Rook) {
                        pieceKind = "R";
                    }

                    if (piece.getOwner() == playerB) {
                        pieceKind = pieceKind.toLowerCase();
                    }

                }
                System.out.print(" " + pieceKind + " ");
            }
            System.out.print("\n");
        }
        System.out.println("=======================\n");
    }
}
