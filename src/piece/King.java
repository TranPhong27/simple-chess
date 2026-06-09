package piece;

import java.util.ArrayList;
import java.util.List;

import main.GamePanel;
import main.Type;

public class King extends Piece {
    public King(int color, int col, int row){
        super(color, col, row);

        type = Type.KING;

        if(color == GamePanel.WHITE){
            image = getImage("/piece/w-king");
        } else{
            image = getImage("/piece/b-king");
        }
    }
    @Override
    public boolean canMove(int targetCol, int targetRow){
        //Movement
        if(isWithinBoard(targetCol, targetRow)){
            if(Math.abs(targetCol-preCol) + Math.abs(targetRow-preRow) == 1 || 
                Math.abs(targetCol-preCol) * Math.abs(targetRow-preRow) == 1){
                    if(isValidSquare(targetCol, targetRow)){
                        return true;
                    }
            }
        }
        //Castling
        if(moved == false){
            //Right castling
            if(targetCol == preCol+2 && targetRow == preRow && pieceIsOnStraightLine(targetCol, targetRow) == false){
                for(Piece piece : GamePanel.simPieces){
                    if(piece.col == preCol+3 && piece.row == preRow && piece.moved == false){
                        GamePanel.castlingP = piece;
                        return true;
                    }
                }

            }
            //Left castling
            if(targetCol == preCol-2 && targetRow == preRow && pieceIsOnStraightLine(targetCol, targetRow) == false){
                Piece p[] = new Piece[2];
                for(Piece piece : GamePanel.simPieces){
                    if(piece.col == preCol-3 && piece.row == preRow){
                        p[0] = piece;
                    }
                    if(piece.col == preCol-4 && piece.row == preRow){
                        p[1] = piece;
                    }
                    if(p[0] == null && p[1] != null && p[1].moved == false){
                        GamePanel.castlingP = p[1];
                        return true;
                    }
                } 
            }

        }
        return false;
    }

    @Override public List<int[]> getPossibleMoves() { 
        List<int[]> possibleMoves = new ArrayList<>(); 
        int[][] moves = { {1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1} }; 
        for (int[] move : moves) { 
            if (canMove(this.col + move[0], this.row + move[1])) { 
                possibleMoves.add(new int[]{this.col + move[0], this.row + move[1]}); 
            } 
        } 
        return possibleMoves; 
    }
}