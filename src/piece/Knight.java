package piece;

import java.util.ArrayList;
import java.util.List;

import main.GamePanel;
import main.Type;

public class Knight extends Piece {
    public Knight(int color, int col, int row){
        super(color, col, row);

        type = Type.KNIGHT;

        if(color == GamePanel.WHITE){
            image = getImage("/piece/w-knight");
        } else{
            image = getImage("/piece/b-knight");
        }
    }
    @Override
    public boolean canMove(int targetCol, int targetRow){
        if(isWithinBoard(targetCol, targetRow)){
            if(Math.abs(targetCol-preCol) * Math.abs(targetRow-preRow) == 2){
                if(isValidSquare(targetCol, targetRow)){
                    return true;
                }
            }

        }
        return false;
    }

    @Override public List<int[]> getPossibleMoves() { 
        List<int[]> possibleMoves = new ArrayList<>(); 
        int[][] moves = { {2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2} }; 
        for (int[] move : moves) { 
            if (canMove(this.col + move[0], this.row + move[1])) { 
                possibleMoves.add(new int[]{this.col + move[0], this.row + move[1]}); 
            } 
        } 
        return possibleMoves; 
    }
}