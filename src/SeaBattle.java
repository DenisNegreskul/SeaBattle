import java.util.Arrays;

public class SeaBattle {
    private static final int FIELD_LENGTH = 10;
    private final char[][] field = new char[FIELD_LENGTH + 2][FIELD_LENGTH + 2];

    public SeaBattle() {
        for (char[] chars : field) {
            Arrays.fill(chars, '.');
        }
    }

    public char[][] getField() {
        char[][] result = new char[field.length - 2][field.length - 2];
        for (int i = 1; i < field.length - 1; i++) {
            System.arraycopy(field[i], 1, result[i - 1], 0, field.length - 2);
        }
        return result;
    }

    void shoot(int line, int column, String issue) {
        line++;
        column++;
        if (issue.equals("m")) {
            field[line][column] = '#';
        } else if (issue.equals("h")) {
            field[line][column] = 'x';
        } else {
            destroyShip(line, column);
        }
    }

    char check(int line, int column) {
        return field[line + 1][column + 1];
    }

    private void destroyShip(int line, int column) {
        field[line][column] = 'x';
        findShip(line, column, "Down");
        findShip(line, column, "Up");
        findShip(line, column, "Left");
        findShip(line, column, "Right");
    }

    private void findShip(int line, int column, String direction) {
        int lineFactor = 0, columnFactor = 0;

        switch (direction) {
            case "Up":
                lineFactor = -1;
                break;
            case "Down":
                lineFactor = 1;
                break;
            case "Left":
                columnFactor = -1;
                break;
            case "Right":
                columnFactor = 1;
                break;
        }

        column += columnFactor;
        line += lineFactor;

        while (field[line][column] == 'x') {
            if (lineFactor == 0) {
                fillUp(line, column);
                fillDown(line, column);
                column += columnFactor;
            } else {
                fillLeft(line, column);
                fillRight(line, column);
                line += lineFactor;
            }
        }

        fillDiagonals(line - lineFactor, column - columnFactor);
        field[line][column] = '#';
    }

    private void fillLeft(int line, int column) {
        field[line][column - 1] = '#';
    }

    private void fillRight(int line, int column) {
        field[line][column + 1] = '#';
    }

    private void fillUp(int line, int column) {
        field[line - 1][column] = '#';
    }

    private void fillDown(int line, int column) {
        field[line + 1][column] = '#';
    }

    private void fillDiagonals(int line, int column) {
        field[line - 1][column - 1] = '#';
        field[line + 1][column + 1] = '#';
        field[line - 1][column + 1] = '#';
        field[line + 1][column - 1] = '#';
    }
}
