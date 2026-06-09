package piece;

import java.util.ArrayList;
import java.util.List;

import main.GamePanel;
import main.Type;

public class Bishop extends Piece {
    public Bishop(int color, int col, int row){
        super(color, col, row);

        type = Type.BISHOP;

        if(color == GamePanel.WHITE){
            image = getImage("/piece/w-bishop");
        } else{
            image = getImage("/piece/b-bishop");
        }
    }
    @Override
    public boolean canMove(int targetCol, int targetRow){
        if(isWithinBoard(targetCol, targetRow) && isSameSquare(targetCol, targetRow) == false){
            if(Math.abs(targetCol-preCol) == Math.abs(targetRow-preRow)){
                if(isValidSquare(targetCol, targetRow) && pieceIsOnDiagonalLine(targetCol, targetRow) == false){
                    return true;
                }
            }
        }
        return false;
    }

    @Override 
    public List<int[]> getPossibleMoves() { 
        List<int[]> possibleMoves = new ArrayList<>(); 
        for (int i = 1; i < 8; i++) { 
            if (canMove(this.col + i, this.row + i)) possibleMoves.add(new int[]{this.col + i, this.row + i}); 
            if (canMove(this.col - i, this.row - i)) possibleMoves.add(new int[]{this.col - i, this.row - i}); 
            if (canMove(this.col + i, this.row - i)) possibleMoves.add(new int[]{this.col + i, this.row - i}); 
            if (canMove(this.col - i, this.row + i)) possibleMoves.add(new int[]{this.col - i, this.row + i}); 
        } 
        return possibleMoves; 
    }
}