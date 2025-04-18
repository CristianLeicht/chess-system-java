package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private Board board;
	private int turn;
	private Color currentPlayer;

	public ChessMatch() {
		board = new Board(8, 8);
		turn = 1;
		currentPlayer = Color.WHITE;
		inicialSetup();
	}

	public ChessPiece[][] getPieces() {

		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];

		for (int i = 0; i < board.getRows(); i++) {

			for (int j = 0; j < board.getColumns(); j++) {

				mat[i][j] = (ChessPiece) board.piece(i, j);
			}

		}

		return mat;

	}

	public boolean[][] possibleMoves(ChessPosition sourcePosition) {

		Position position = sourcePosition.toPosition();

		validateSourcePosition(position);

		return board.piece(position).possibleMoves();
	}

	public ChessPiece perfomChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {

		// convert position into matrix positions
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();

		validateSourcePosition(source);
		validateTargetPosition(source, target);

		Piece capturePiece = makeMove(source, target);
		nextTurn();
		return (ChessPiece) capturePiece;

	}

	private void validateTargetPosition(Position source, Position target) {

		if (!board.piece(source).possibleMove(target)) {
			throw new ChessException("The chosen piece can't move to the target position");

		}

	}

	private Piece makeMove(Position source, Position target) {

		Piece piece = board.removePiece(source);

		Piece capturedPiece = board.removePiece(target);

		board.placePiece(piece, target);

		return capturedPiece;
	}

	private void validateSourcePosition(Position position) {

		if (!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position");
		}
		if (!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible move for the chosen piece");

		}
		if (currentPlayer != ((ChessPiece) board.piece(position)).getColor()) {
			throw new ChessException("");

		}

	}

	private void nextTurn() {
		turn++;

		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	private void placeNewPiece(char column, int row, ChessPiece piece) {

		board.placePiece(piece, new ChessPosition(column, row).toPosition());

	}

	private void inicialSetup() {

		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
		placeNewPiece('c', 2, new Rook(board, Color.WHITE));
		placeNewPiece('d', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 1, new Rook(board, Color.WHITE));
		placeNewPiece('d', 1, new King(board, Color.WHITE));

		placeNewPiece('c', 7, new Rook(board, Color.BLACK));
		placeNewPiece('c', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 8, new King(board, Color.BLACK));
	}

	public int getTurn() {
		return turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}

}
