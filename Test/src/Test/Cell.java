package Test;

public class Cell {
    int row;
    int col;
    /**
     * 使用 this 关键字重构 
     * @param row：行
     * @param col：列
     */
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }
    /**
     * 默认构造方法
     */
    public Cell() {
        this(0, 0);
    }
    /**
     * 构造方法的重载 
     * @param cell
     */
    public Cell(Cell cell) {
        this(cell.row, cell.col);
    }
//下落一行
    public void drop() {
        row++;
    }
//左移
public void moveLeft(int d) {
        col -= d;
    }
public String getCellInfo() {
        return row + "," + col;
    }
//重载的 drop 方法
    public void drop(int d) {
        row += d;
    }
//重载的 moveLeft方法
    public void moveLeft() {
        col--;
    }
}