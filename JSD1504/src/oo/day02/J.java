package oo.day02;

public class J {
	Cell[] cells;
	J() {
		this(0, 0);
	}

	J(int row, int col) {
		cells = new Cell[4];
		cells[0] = new Cell(row, col);
		cells[1] = new Cell(row, col + 1);
		cells[2] = new Cell(row, col + 2);
		cells[3] = new Cell(row + 1, col + 2);
	}

	void drop() {
	}

	void moveLeft() {
	}

	void moveRight() {
	}

}
