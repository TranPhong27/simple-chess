package piece;

import java.util.ArrayList;
import java.util.List;

import main.GamePanel;
import main.Type;

public class Pawn extends Piece{
    public Pawn(int color, int col, int row){
        super(color, col, row);

        type = Type.PAWN;

        if(color == GamePanel.WHITE){
            image = getImage("/piece/w-pawn");
        } else{
            image = getImage("/piece/b-pawn");
        }
    }
    @Override
    public boolean canMove(int targetCol, int targetRow){
        if(isWithinBoard(targetCol, targetRow) && isSameSquare(targetCol, targetRow) == false){
            int moveValue;
            if(color == GamePanel.WHITE){
                moveValue = -1;
            }else{
                moveValue = 1;
            }
            hittingP = getHittingP(targetCol, targetRow);
            if(targetCol == preCol && targetRow == preRow + moveValue && hittingP == null){
                return true;
            }
            if(targetCol == preCol && targetRow == preRow + moveValue*2 && hittingP == null && 
                moved == false && pieceIsOnStraightLine(targetCol, targetRow) == false){
                    return true;
            }
            if(Math.abs(targetCol-preCol) == 1 && targetRow == preRow + moveValue && hittingP != null && 
                hittingP.color != color){
                    return true;
                }
            //Bắt tốt qua đường
            if(Math.abs(targetCol-preCol) == 1 && targetRow == preRow + moveValue){
                for(Piece piece : GamePanel.simPieces){
                    if(piece.col == targetCol && piece.row == preRow && piece.twoStepped == true){
                        hittingP = piece;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override public List<int[]> getPossibleMoves() { 
        List<int[]> possibleMoves = new ArrayList<>(); 
        int moveValue = (color == GamePanel.WHITE) ? -1 : 1; 
        int startRow = (color == GamePanel.WHITE) ? 6 : 1; 
        if (canMove(this.col, this.row + moveValue)) { 
            possibleMoves.add(new int[]{this.col, this.row + moveValue}); 
        } 
        if (this.row == startRow && canMove(this.col, this.row + 2 * moveValue)) { 
            possibleMoves.add(new int[]{this.col, this.row + 2 * moveValue}); 
        } 
        if (canMove(this.col - 1, this.row + moveValue)) { 
            possibleMoves.add(new int[]{this.col - 1, this.row + moveValue}); 
        } 
        if (canMove(this.col + 1, this.row + moveValue)) { 
            possibleMoves.add(new int[]{this.col + 1, this.row + moveValue}); 
        } 
        return possibleMoves; 
    }
}