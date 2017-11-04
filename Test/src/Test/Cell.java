package Test;

public class Cell {
    int row;
    int col;
    /**
     * ʹ�� this �ؼ����ع� 
     * @param row����
     * @param col����
     */
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }
    /**
     * Ĭ�Ϲ��췽��
     */
    public Cell() {
        this(0, 0);
    }
    /**
     * ���췽�������� 
     * @param cell
     */
    public Cell(Cell cell) {
        this(cell.row, cell.col);
    }
//����һ��
    public void drop() {
        row++;
    }
//����
public void moveLeft(int d) {
        col -= d;
    }
public String getCellInfo() {
        return row + "," + col;
    }
//���ص� drop ����
    public void drop(int d) {
        row += d;
    }
//���ص� moveLeft����
    public void moveLeft() {
        col--;
    }
}