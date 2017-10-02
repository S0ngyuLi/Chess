/**
 * Created by songyuli on 10/1/17.
 * A undo command class following command pattern.
 */
public class UndoCommand {
    private Piece piece;
    private Piece captured;
    private ChessboardModel board;
    private ChessboardCell originalCell;
    private ChessboardCell newCell;
    public UndoCommand(Piece piece, ChessboardModel board, ChessboardCell originalCell, ChessboardCell newCell, Piece captured) {
        this.piece = piece;
        this.board = board;
        this.originalCell = originalCell;
        this.newCell = newCell;
        this.captured = captured;
    }

    /*
        Execute the class. i.e. It should only be called once it is removed from the commands stack.
     */
    public void execute() {
        piece.setCell(originalCell);
        if (piece instanceof  Pawn) {
            ((Pawn) piece).undo();
        }
        board.toggleAShouldMove();
        if(captured != null) {
            board.box.remove(captured);
            if(captured.getOwner() == board.getPlayerA()) {
                board.piecesForPlayerA.add(captured);
            }
            else {
                board.piecesForPlayerB.add(captured);
            }
            captured.setCell(newCell);
        }
    }
}
