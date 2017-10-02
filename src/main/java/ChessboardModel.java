/**
 * Created by songyuli on 9/17/17.
 *
 * ChessboardModel object, which hold chessboard cells as composition, as well as player information
 *
 */
import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class ChessboardModel implements ListModel<JPanel> {
    private boolean AShouldMove;
    private ChessboardCell [][] cells;
    public HashSet<Piece> box; // A box of pieces that have been captured
    public Stack<UndoCommand> stack;
    private King kingForA;
    private King kingForB;

    public HashSet<Piece> piecesForPlayerA; // All of A's pieces on board (not captured)
    public HashSet<Piece> piecesForPlayerB; // All of B's pieces on board (not captured)

    public Player getPlayerA() {
        return playerA;
    }

    public Player getPlayerB() {
        return playerB;
    }

    private Player playerA; // Upper side player
    private Player playerB; // Lower side player
    private ArrayList<ListDataListener> listeners;

    public AppDelegate appDelegate;

    public ChessboardModel(Player playerA, Player playerB, AppDelegate delegate){
        this.stack = new Stack<UndoCommand>();
        this.AShouldMove = true;
        this.cells = new ChessboardCell[8][8];
        this.playerA = playerA;
        this.playerB = playerB;
        this.appDelegate = delegate;
        this.box = new HashSet<Piece>();
        this.piecesForPlayerA = new HashSet<>();
        this.piecesForPlayerB = new HashSet<>();
        this.listeners = new ArrayList<ListDataListener>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cells[i][j] = new ChessboardCell(i, j, this);
                cells[i][j].setPreferredSize(new Dimension(ChessView.tileDimension, ChessView.tileDimension));
                cells[i][j].setBackground((i+j) % 2 == 0? new Color(178, 158, 158) : new Color(226, 226, 226)); // Black : white
                cells[i][j].setLayout(new BorderLayout());
            }
        }
    }

    public boolean getAShouldMove() {
        return this.AShouldMove;
    }

    public void toggleAShouldMove() {
        this.AShouldMove = !AShouldMove;
    }

    /*
        Implements for ListModel
    */
    public void addListDataListener(ListDataListener l) {
        listeners.add(l);
    }

    /*
        Implements for ListModel
    */
    public void removeListDataListener(ListDataListener l) {
        listeners.remove(l);
    }

    /*
        Implements for ListModel
    */
    public void notifyContentChanged() {
        for (ListDataListener listener : listeners) {
            listener.contentsChanged(new ListDataEvent(this, 0, 0, getSize() - 1));
        }
    }

    /*
        Implements for ListModel
    */
    public JPanel getElementAt(int index) {
        int i = index / 8;
        int j = index % 8;
        return this.getCell(i, j);
    }
    /*
        Getter for size variable.
     */
    public int getSize(){
        return 64;
    }

    /*
        Initialize a board with all conventional pieces
     */
    public void initializeBoard() {
        this.stack = new Stack<>();
        this.AShouldMove = true;
        this.box = new HashSet<Piece>();
        this.piecesForPlayerA = new HashSet<>();
        this.piecesForPlayerB = new HashSet<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cells[i][j].clearCell();
                cells[i][j].deselect();
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

        this.notifyContentChanged();
    }

    /*
        Initialize a board with Advisors replaceing Bishops and Princesses replacing Knights.
     */

    public void initializeFairyBoard() {
        this.stack = new Stack<>();
        this.box = new HashSet<Piece>();
        this.piecesForPlayerA = new HashSet<>();
        this.piecesForPlayerB = new HashSet<>();
        this.AShouldMove = true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cells[i][j].clearCell();
                cells[i][j].deselect();
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

        piecesForPlayerA.add(new Princess(this.playerA, cells[1][0]));
        piecesForPlayerA.add(new Princess(this.playerA, cells[6][0]));
        piecesForPlayerB.add(new Princess(this.playerB, cells[1][7]));
        piecesForPlayerB.add(new Princess(this.playerB, cells[6][7]));

        piecesForPlayerA.add(new Advisor(this.playerA, cells[2][0]));
        piecesForPlayerA.add(new Advisor(this.playerA, cells[5][0]));
        piecesForPlayerB.add(new Advisor(this.playerB, cells[2][7]));
        piecesForPlayerB.add(new Advisor(this.playerB, cells[5][7]));

        this.notifyContentChanged();
    }

    /*
        Evaluates to true if B is in check
     */
    private boolean isBInCheck() {
        for (Piece piece: this.piecesForPlayerA) {
            ArrayList<ChessboardCell> possibleRoutesA = piece.getAllPossibleRoutes();
            if (possibleRoutesA.contains(kingForB.getChessboardCell())) {
                return true;
            }
        }
        return false;
    }

    /*
        Evaluates to true if A is in check
     */
    private boolean isAInCheck() {
        for (Piece piece: this.piecesForPlayerB) {
            ArrayList<ChessboardCell> possibleRoutesB = piece.getAllPossibleRoutes();
            if (possibleRoutesB.contains(kingForA.getChessboardCell())) {
                return true;
            }
        }
        return false;
    }

    /*
        Check if A is about to win
    */
    private boolean checkCheckMateForA() {
        for (Piece piece: this.piecesForPlayerA) {
            piece.getChessboardCell().deselect();
            if (piece.checkViablePath(kingForB.getChessboardCell())) {
                if (this.AShouldMove) {
                    return true;
                }
                piece.getChessboardCell().isInCheck();
                for (Piece rivalPiece : this.piecesForPlayerB) {
                    ArrayList<ChessboardCell> possibleRoutesB = rivalPiece.getAllPossibleRoutes();
                    for (ChessboardCell possibleMove: possibleRoutesB) {
                        if (possibleMove == piece.getChessboardCell()) {
                            return false;
                        }
                        // Assume possible moves
                        rivalPiece.willMove(possibleMove, false);

                        if(!piece.isOnBoard() || !piece.checkViablePath(kingForB.getChessboardCell())) {
                            rivalPiece.resume();
                            return false;
                        }
                        rivalPiece.resume();
                    }
                }
                return true;
            }
        }
        return false;
    }
    /*
        Check if B is about to win
     */
    private boolean checkCheckMateForB() {
        for (Piece piece: this.piecesForPlayerB) {
            piece.getChessboardCell().deselect();
            if (piece.checkViablePath(kingForA.getChessboardCell())) {
                if (!this.AShouldMove) {
                    return true;
                }
                piece.getChessboardCell().isInCheck();
                for (Piece rivalPiece : this.piecesForPlayerA) {
                    ArrayList<ChessboardCell> possibleRoutesA = rivalPiece.getAllPossibleRoutes();
                    for (ChessboardCell possibleMove: possibleRoutesA) {
                        if (possibleMove == piece.getChessboardCell()) {
                            return false;
                        }
                        // Assume possible moves
                        rivalPiece.willMove(possibleMove, false);

                        if(!piece.isOnBoard() || !piece.checkViablePath(kingForA.getChessboardCell())) {
                            rivalPiece.resume();
                            return false;
                        }
                        rivalPiece.resume();
                    }
                }
                return true;
            }
        }
        return false;
    }
    /*
        Check if there is a checkmate on the board.
     */
    public void checkCheckMate() {
        if (checkCheckMateForA()) {
            appDelegate.endWithWinner(playerA);
            return;
        }
        else if (checkCheckMateForB()) {
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
    /*
     * Apply command at each tile on the board.
     * Command should be a lambda expression.
     */
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
                    else if (piece instanceof Advisor) {
                        pieceKind = "A";
                    }
                    else if (piece instanceof Princess) {
                        pieceKind = "C";
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
