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

	public void placePiece(Piece piece, Position position) {
		
		if(thereIsAPiece(position)) {
			
			throw new BoardException("There is already a pieace on position "+ position);

			
		}

		pieces[position.getRow()][position.getColumn()] = piece;

		piece.position = position;
	}

	public boolean positionExists(int row, int column) {

		return row >= 0 && row < rows && column >= 0 && row < columns;

	}

	public boolean positionExists(Position posision) {

		return positionExists(posision.getRow(), posision.getColumn());

	}

	public boolean thereIsAPiece(Position posision) {
		
		if (!positionExists(posision)) {

			throw new BoardException("Position not in the board");

		}
		return piece(posision) != null;
	}

	public Piece piece(int row, int column) {
		if (!positionExists(row, column)) {

			throw new BoardException("Position not in the board");

		}

		return pieces[row][column];
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

}
