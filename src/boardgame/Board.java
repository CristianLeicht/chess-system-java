package boardgame;

public class Board {

	private int rows;
	private int columns;
	private Piece[][] pieces;

	public Board(int rows, int columns) {

		if (rows < 1 || columns < 1) {

			throw new BoardException("Error creating board: there must be at least 1 row and 1 column");

		}

		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public Piece piece(Position position) {
		return piece(position.getRow(), position.getColumn());
	}

	public Piece piece(int row, int column) {
		if (!positionExists(row, column)) {

			throw new BoardException("Position not in the board");

		}

		return pieces[row][column];
	}

	public Piece removePiece(Position position) {

		Piece pieceToRemove = piece(position);

		if (!positionExists(position)) {

			throw new BoardException("Cannot remove piece: position out of bounds");

		}

		if (piece(position) == null) {
			return null;
		}

		pieceToRemove.position = null;
		pieces[position.getRow()][position.getColumn()] = null;
		return pieceToRemove;

	}

	public void placePiece(Piece piece, Position position) {

		if (thereIsAPiece(position)) {

			throw new BoardException("There is already a pieace on position " + position);

		}

		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}

	public boolean positionExists(int row, int column) {

		return row >= 0 && row < rows && column >= 0 && column < columns;

	}

	public boolean positionExists(Position posision) {

		return positionExists(posision.getRow(), posision.getColumn());

	}

	public boolean thereIsAPiece(Position position) {

		if (!positionExists(position)) {

			throw new BoardException("Position not in the board");

		}
		return piece(position) != null;
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

}
