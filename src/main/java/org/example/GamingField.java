package org.example;

public class GamingField {
    private GamingCell[][] cells;

    private int[][][][] pointsForComputer = new int[8][8][1][2];

    public int[][][][] getPointsForComputer() {
        return pointsForComputer;
    }

    private GamingCell[][] previousCells;

    private int FindSi(int x, int y) {
        if (x == 0 && y == 0 || x == 7 && y == 7 || x == 7 && y == 1 || x == 1 && y == 7) {
            return 2;
        }
        return 1;
    }

    private GamingCell[][] CopyField(GamingCell[][] field) {
        var oldCells = new GamingCell[8][8];
        for (var i = 0; i < 8; i++) {
            for (var j = 0; j < 8; j++) {
                oldCells[i][j] = new GamingCell(field[i][j].GetChip().GetCondition());
            }
        }
        return oldCells;
    }

    public GamingCell[][] getCells() {
        return cells;
    }

    public GamingField() {
        cells = new GamingCell[8][8];
        for (var x = 0; x < 8; x++) {
            for (var y = 0; y < 8; y++) {
                if (x == 4 && y == 3 || y == 4 && x == 3) {
                    cells[x][y] = new GamingCell("black");
                } else if (x == 3 && y == 3 || x == 4 && y == 4) {
                    cells[x][y] = new GamingCell("white");
                } else {
                    cells[x][y] = new GamingCell("empty");
                }
            }
        }
        CheckAvailable();
        previousCells = CopyField(cells);
    }

    private boolean IsIn8(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    public Boolean IsAvailable(int x, int y, String color) {
        if (x >= 0 && x < 8 && y >= 0 && y < 8) {
            if (color == "white" && cells[x][y].GetChip().GetCondition() == "availableWhite") {
                return true;
            } else if (color == "black" && cells[x][y].GetChip().GetCondition() == "availableBlack") {
                return true;
            }
        }
        return false;
    }

    private void ChangeChips(int x, int y) {
        int i;
        // Вниз столбик
        if (x == 3 && y == 2);
        if (cells[x][y].GetChip().GetCondition() == "white") {
            int x_it = x + 1;
            int y_it = y;
            int count = 0;
            while (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "black") {
                x_it++;
                count++;
            }
            if (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "white") {
                for (var j = 1; j <= count; j++) {
                    cells[x + j][y].ChangeChip("white");
                }
            }
        } else if (cells[x][y].GetChip().GetCondition() == "black") {
            int x_it = x + 1;
            int y_it = y;
            int count = 0;
            while (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "white") {
                x_it++;
                count++;
            }
            if (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "black") {
                for (var j = 1; j <= count; j++) {
                    cells[x + j][y].ChangeChip("black");
                }
            }
        }
        // вверх столбик
        if (cells[x][y].GetChip().GetCondition() == "white") {
            int x_it = x - 1;
            int y_it = y;
            int count = 0;
            while (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "black") {
                x_it--;
                count++;
            }
            if (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "white") {
                for (var j = 1; j <= count; j++) {
                    cells[x - j][y].ChangeChip("white");
                }
            }
        } else if (cells[x][y].GetChip().GetCondition() == "black") {
            int x_it = x - 1;
            int y_it = y;
            int count = 0;
            while (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "white") {
                x_it--;
                count++;
            }
            if (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "black") {
                for (var j = 1; j <= count; j++) {
                    cells[x - j][y].ChangeChip("black");
                }
            }
        }
        // вправо строка
        if (cells[x][y].GetChip().GetCondition() == "white") {
            int x_it = x;
            int y_it = y + 1;
            int count = 0;
            while (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "black") {
                y_it++;
                count++;
            }
            if (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "white") {
                for (var j = 1; j <= count; j++) {
                    cells[x][y + j].ChangeChip("white");
                }
            }
        } else if (cells[x][y].GetChip().GetCondition() == "black") {
            int x_it = x;
            int y_it = y + 1;
            int count = 0;
            while (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "white") {
                y_it++;
                count++;
            }
            if (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "black") {
                for (var j = 1; j <= count; j++) {
                    cells[x][y + j].ChangeChip("black");
                }
            }
        }
        // влево строка
        if (cells[x][y].GetChip().GetCondition() == "white") {
            int x_it = x;
            int y_it = y - 1;
            int count = 0;
            while (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "black") {
                y_it--;
                count++;
            }
            if (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "white") {
                for (var j = 1; j <= count; j++) {
                    cells[x][y - j].ChangeChip("white");
                }
            }
        } else if (cells[x][y].GetChip().GetCondition() == "black") {
            int x_it = x;
            int y_it = y - 1;
            int count = 0;
            while (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "white") {
                y_it--;
                count++;
            }
            if (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "black") {
                for (var j = 1; j <= count; j++) {
                    cells[x][y - j].ChangeChip("black");
                }
            }
        }
        // диагональ влево вверх
        if (cells[x][y].GetChip().GetCondition() == "white") {
            int x_it = x - 1;
            int y_it = y - 1;
            int count = 0;
            while (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "black") {
                x_it--;
                y_it--;
                count++;
            }
            if (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "white") {
                for (var j = 1; j <= count; j++) {
                    cells[x - j][y - j].ChangeChip("white");
                }
            }
        } else if (cells[x][y].GetChip().GetCondition() == "black") {
            int x_it = x - 1;
            int y_it = y - 1;
            int count = 0;
            while (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "white") {
                x_it--;
                y_it--;
                count++;
            }
            if (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "black") {
                for (var j = 1; j <= count; j++) {
                    cells[x - j][y - j].ChangeChip("black");
                }
            }
        }
        // вправо вниз диагональ
        if (cells[x][y].GetChip().GetCondition() == "white") {
            int x_it = x + 1;
            int y_it = y + 1;
            int count = 0;
            while (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "black") {
                x_it++;
                y_it++;
                count++;
            }
            if (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "white") {
                for (var j = 1; j <= count; j++) {
                    cells[x + j][y + j].ChangeChip("white");
                }
            }
        } else if (cells[x][y].GetChip().GetCondition() == "black") {
            int x_it = x + 1;
            int y_it = y + 1;
            int count = 0;
            while (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "white") {
                x_it++;
                y_it++;
                count++;
            }
            if (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "black") {
                for (var j = 1; j <= count; j++) {
                    cells[x + j][y + j].ChangeChip("black");
                }
            }
        }
        // влево вниз диагональ
        if (cells[x][y].GetChip().GetCondition() == "white") {
            int x_it = x - 1;
            int y_it = y + 1;
            int count = 0;
            while (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "black") {
                x_it--;
                y_it++;
                count++;
            }
            if (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "white") {
                for (var j = 1; j <= count; j++) {
                    cells[x - j][y + j].ChangeChip("white");
                }
            }
        } else if (cells[x][y].GetChip().GetCondition() == "black") {
            int x_it = x - 1;
            int y_it = y + 1;
            int count = 0;
            while (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "white") {
                x_it--;
                y_it++;
                count++;
            }
            if (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "black") {
                for (var j = 1; j <= count; j++) {
                    cells[x - j][y + j].ChangeChip("black");
                }
            }
        }
        // вправо вверх диагональ
        if (cells[x][y].GetChip().GetCondition() == "white") {
            int x_it = x + 1;
            int y_it = y - 1;
            int count = 0;
            while (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "black") {
                x_it++;
                y_it--;
                count++;
            }
            if (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "white") {
                for (var j = 1; j < count; j++) {
                    cells[x + j][y - j].ChangeChip("white");
                }
            }
        } else if (cells[x][y].GetChip().GetCondition() == "black") {
            int x_it = x + 1;
            int y_it = y - 1;
            int count = 0;
            while (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "white") {
                x_it++;
                y_it--;
                count++;
            }
            if (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "black") {
                for (var j = 1; j < count; j++) {
                    cells[x + j][y - j].ChangeChip("black");
                }
            }
        }
    }

    private void ClearAllAvailable() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (cells[x][y].GetChip().GetCondition() == "availableWhite" ||
                        cells[x][y].GetChip().GetCondition() == "availableBlack") {
                    cells[x][y].ChangeChip("empty");
                }
            }
        }
    }

    public Boolean SkipMove(String color) {
        CheckAvailable();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (cells[x][y].GetChip().GetCondition() == color
                        && cells[x][y].GetChip().GetCondition() == "availableWhite") {
                    return true;
                } else if (cells[x][y].GetChip().GetCondition() == color
                        && cells[x][y].GetChip().GetCondition() == "availableBlack") {
                    return true;
                }
            }
        }
        return false;
    }

    private void CheckAvailable() {
        ClearAllAvailable();
//        pointsForComputer = new int[8][8][1][2];
        for (var x = 0; x < 8; x++) {
            for (var y = 0; y < 8; y++) {
                int i;
                if (cells[x][y].GetChip().GetCondition() == "empty") {
                    // вправо строка
                    int x_it = x;
                    int y_it = y + 1;
                    int count = 0;
                    int sumOfPoints = 0;
                    while (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "white") {
                        y_it++;
                        sumOfPoints += FindSi(x_it, y_it);
                        count++;
                    }
                    if (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "black" && count >= 1) {
                        cells[x][y].ChangeChip("availableBlack");
                        pointsForComputer[x][y][0][0] += sumOfPoints;
                    }
                    x_it = x;
                    y_it = y + 1;
                    count = 0;
                    sumOfPoints = 0;
                    while (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "black") {
                        y_it++;
                        sumOfPoints += FindSi(x_it, y_it);
                        count++;
                    }
                    if (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "white" && count >= 1) {
                        cells[x][y].ChangeChip("availableWhite");
                        pointsForComputer[x][y][0][1] += sumOfPoints;
                    }
                    //влево строка
                    x_it = x;
                    y_it = y - 1;
                    count = 0;
                    sumOfPoints = 0;
                    while (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "black") {
                        y_it--;
                        sumOfPoints += FindSi(x_it, y_it);
                        count++;
                    }
                    if (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "white" && count >= 1) {
                        cells[x][y].ChangeChip("availableWhite");
                        pointsForComputer[x][y][0][1] += sumOfPoints;
                    }
                    x_it = x;
                    y_it = y - 1;
                    count = 0;
                    sumOfPoints = 0;
                    while (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "white") {
                        y_it--;
                        sumOfPoints += FindSi(x_it, y_it);
                        count++;
                    }
                    if (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "black" && count >= 1) {
                        cells[x][y].ChangeChip("availableBlack");
                        pointsForComputer[x][y][0][0] += sumOfPoints;
                    }
                    // вниз столбик
                    x_it = x + 1;
                    y_it = y;
                    count = 0;
                    sumOfPoints = 0;
                    while (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "black") {
                        x_it++;
                        sumOfPoints += FindSi(x_it, y_it);
                        count++;
                    }
                    if (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "white" && count >= 1) {
                        cells[x][y].ChangeChip("availableWhite");
                        pointsForComputer[x][y][0][1] += sumOfPoints;
                    }
                    x_it = x + 1;
                    y_it = y;
                    count = 0;
                    sumOfPoints = 0;
                    while (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "white") {
                        x_it++;
                        sumOfPoints += FindSi(x_it, y_it);
                        count++;
                    }
                    if (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "black" && count >= 1) {
                        cells[x][y].ChangeChip("availableBlack");
                        pointsForComputer[x][y][0][0] += sumOfPoints;
                    }
                    // вверх столбик
                    x_it = x - 1;
                    y_it = y;
                    count = 0;
                    sumOfPoints = 0;
                    while (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "black") {
                        x_it--;
                        sumOfPoints += FindSi(x_it, y_it);
                        count++;
                    }
                    if (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "white" && count >= 1) {
                        cells[x][y].ChangeChip("availableWhite");
                        pointsForComputer[x][y][0][1] += sumOfPoints;
                    }
                    x_it = x - 1;
                    y_it = y;
                    count = 0;
                    sumOfPoints = 0;
                    while (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "white") {
                        x_it--;
                        sumOfPoints += FindSi(x_it, y_it);
                        count++;
                    }
                    if (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "black" && count >= 1) {
                        cells[x][y].ChangeChip("availableBlack");
                        pointsForComputer[x][y][0][0] += sumOfPoints;
                    }
                    // влево вверх диагональ
                    x_it = x - 1;
                    y_it = y - 1;
                    count = 0;
                    sumOfPoints = 0;
                    while (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "black") {
                        x_it--;
                        y_it--;
                        sumOfPoints += FindSi(x_it, y_it);
                        count++;
                    }
                    if (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "white" && count >= 1) {
                        cells[x][y].ChangeChip("availableWhite");
                        pointsForComputer[x][y][0][1] += sumOfPoints;
                    }
                    x_it = x - 1;
                    y_it = y - 1;
                    count = 0;
                    sumOfPoints = 0;
                    while (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "white") {
                        x_it--;
                        y_it--;
                        sumOfPoints += FindSi(x_it, y_it);
                        count++;
                    }
                    if (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "black" && count >= 1) {
                        cells[x][y].ChangeChip("availableBlack");
                        pointsForComputer[x][y][0][0] += sumOfPoints;
                    }
                    // вправо вниз диагональ
                    x_it = x + 1;
                    y_it = y + 1;
                    count = 0;
                    sumOfPoints = 0;
                    while (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "black") {
                        x_it++;
                        y_it++;
                        sumOfPoints += FindSi(x_it, y_it);
                        count++;
                    }
                    if (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "white" && count >= 1) {
                        cells[x][y].ChangeChip("availableWhite");
                        pointsForComputer[x][y][0][1] += sumOfPoints;
                    }
                    x_it = x + 1;
                    y_it = y + 1;
                    count = 0;
                    sumOfPoints = 0;
                    while (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "white") {
                        x_it++;
                        y_it++;
                        sumOfPoints += FindSi(x_it, y_it);
                        count++;
                    }
                    if (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "black" && count >= 1) {
                        cells[x][y].ChangeChip("availableBlack");
                        pointsForComputer[x][y][0][0] += sumOfPoints;
                    }
                    //влево вниз диагональ
                    x_it = x - 1;
                    y_it = y + 1;
                    count = 0;
                    sumOfPoints = 0;
                    while (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "black") {
                        x_it--;
                        y_it++;
                        sumOfPoints += FindSi(x_it, y_it);
                        count++;
                    }
                    if (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "white" && count >= 1) {
                        cells[x][y].ChangeChip("availableWhite");
                        pointsForComputer[x][y][0][1] += sumOfPoints;
                    }
                    x_it = x - 1;
                    y_it = y + 1;
                    count = 0;
                    sumOfPoints = 0;
                    while (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "white") {
                        x_it--;
                        y_it++;
                        sumOfPoints += FindSi(x_it, y_it);
                        count++;
                    }
                    if (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "black" && count >= 1) {
                        cells[x][y].ChangeChip("availableBlack");
                        pointsForComputer[x][y][0][0] += sumOfPoints;
                    }
                    // вправо вверх диагональ
                    x_it = x + 1;
                    y_it = y - 1;
                    count = 0;
                    sumOfPoints = 0;
                    while (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "black") {
                        x_it++;
                        y_it--;
                        sumOfPoints += FindSi(x_it, y_it);
                        count++;
                    }
                    if (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "white" && count >= 1) {
                        cells[x][y].ChangeChip("availableWhite");
                        pointsForComputer[x][y][0][1] += sumOfPoints;
                    }
                    x_it = x + 1;
                    y_it = y - 1;
                    count = 0;
                    sumOfPoints = 0;
                    while (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "white") {
                        x_it++;
                        y_it--;
                        sumOfPoints += FindSi(x_it, y_it);
                        count++;
                    }
                    if (IsIn8(x_it, y_it) && cells[x_it][y_it].GetChip().GetCondition() == "black" && count >= 1) {
                        cells[x][y].ChangeChip("availableBlack");
                        pointsForComputer[x][y][0][0] += sumOfPoints;
                    }
                }
            }
        }
    }

    public void MakeMove(int x, int y, String color) {
        previousCells = CopyField(cells);
        cells[x][y].ChangeChip(color);
        ChangeChips(x, y);
        CheckAvailable();
    }

    public void TakeStepBack() {
        cells = CopyField(previousCells);
        CheckAvailable();
    }


    public boolean CheckEnd() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (cells[i][j].GetChip().GetCondition() == "availableWhite" ||
                        cells[i][j].GetChip().GetCondition() == "availableBlack") {
                    return false;
                }
            }
        }
        return true;
    }

    // отдельно для черных, отдельно для белых
    public String DrawField(String color) {
        String exit = "";
        for (var i = 0; i < 8; i++) {
            for (var j = 0; j < 8; j++) {
                if (color == "white" && cells[i][j].GetChip().GetCondition() == "availableWhite") {
                    exit += "aw";
                } else if (color == "black" && cells[i][j].GetChip().GetCondition() == "availableBlack") {
                    exit += "ab";
                }
                if (color != "white" && cells[i][j].GetChip().GetCondition() == "availableWhite") {
                    exit += "e ";
                } else if (color != "black" && cells[i][j].GetChip().GetCondition() == "availableBlack") {
                    exit += "e ";
                } else if (cells[i][j].GetChip().GetCondition() == "empty") {
                    exit += "e ";
                } else if (cells[i][j].GetChip().GetCondition() == "black") {
                    exit += "b ";
                } else if (cells[i][j].GetChip().GetCondition() == "white") {
                    exit += "w ";
                }
                exit += " ";
            }
            exit += "\n";
        }
        return exit;
    }
}
