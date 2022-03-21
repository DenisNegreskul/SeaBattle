import java.util.Arrays;

public class SeaBattle {
    private static final int FIELD_LENGTH = 10;
    private final char[][] field = new char[FIELD_LENGTH][FIELD_LENGTH];

    public SeaBattle() {
        for (char[] chars : field) {
            Arrays.fill(chars, '.');
        }
    }

    public char[][] getField() {
        char[][] result = new char[field.length][field.length];
        for (int i = 0; i < field.length; i++) {
            result[i] = field[i].clone();
        }
        return result;
    }

    void shoot(int line, int column, String issue) {
        if (issue.equals("m")) {
            field[line][column] = '#';
        } else if (issue.equals("h")) {
            field[line][column] = 'x';
        } else {
            destroyShip(line, column);
        }
    }

    char check(int line, int column) {
        return field[line][column];
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
        int factor, shift = 0;

        switch (direction) {
            case "Up":
                lineFactor = -1;
                shift = line;
                break;
            case "Down":
                lineFactor = 1;
                shift = line;
                break;
            case "Left":
                columnFactor = -1;
                shift = column;
                break;
            case "Right":
                columnFactor = 1;
                shift = column;
                break;
        }

        factor = lineFactor + columnFactor;
        column += columnFactor;
        line += lineFactor;
        shift += factor;

        while (shift >= 0 && shift < field.length && field[line][column] == 'x') {
            if (lineFactor == 0) {
                fillUp(line, column);
                fillDown(line, column);
                column += columnFactor;
            } else {
                fillLeft(line, column);
                fillRight(line, column);
                line += lineFactor;
            }
            shift += factor;
        }

        if (shift >= 0 && shift < field.length) {
            fillDiagonals(line - lineFactor, column - columnFactor);
            field[line][column] = '#';
        }
    }

    private void fillLeft(int line, int column) {
        if (column > 0) field[line][column - 1] = '#';
    }

    private void fillRight(int line, int column) {
        if (column < field.length - 1) field[line][column + 1] = '#';
    }

    private void fillUp(int line, int column) {
        if (line > 0) field[line - 1][column] = '#';
    }

    private void fillDown(int line, int column) {
        if (line < field.length - 1) field[line + 1][column] = '#';
    }

    private void fillDiagonals(int line, int column) {
        if (line > 0 && column > 0) {
            field[line - 1][column - 1] = '#';
        }
        if (line < field.length - 1 && column < field.length - 1) {
            field[line + 1][column + 1] = '#';
        }
        if (line > 0 && column < field.length - 1) {
            field[line - 1][column + 1] = '#';
        }
        if (line < field.length - 1 && column > 0) {
            field[line + 1][column - 1] = '#';
        }
    }
}
