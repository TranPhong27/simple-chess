package piece;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import main.Board;
import main.GamePanel;
import main.Type;

public abstract class Piece {
    public Type type;
    public BufferedImage image;
    public int x, y;
    public int col, row, preCol, preRow;
    public int color;
    public Piece hittingP;
    public boolean moved, twoStepped;

    public Piece(int color, int col, int row){
        this.color = color;
        this.col = col;
        this.row = row;
        x = getX(col);
        y = getY(row);
        preCol = col;
        preRow = row;
    }
  
    public BufferedImage getImage(String imagePath){
        BufferedImage image = null;
        try{
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
        } catch(IOException e){
            e.printStackTrace();
        }
        return image;
    }

    public int getX(int col){
        return col * Board.SQUARE_SIZE;
    }

    public int getY(int row){
        return row * Board.SQUARE_SIZE;
    }

    public int getCol(int x){
        return (x+Board.HALF_SQUARE_SIZE)/Board.SQUARE_SIZE;
    }

    public int getRow(int y){
        return (y+Board.HALF_SQUARE_SIZE)/Board.SQUARE_SIZE;
    }
    // Lấy vị trí của quân cờ trong mảng simPieces
    public int getIndex(){
        for(int index=0; index<=GamePanel.simPieces.size(); index++){
            if(GamePanel.simPieces.get(index) == this){
                return index;
            }
        }
        return 0;
    }
    //Cập nhật vị trí của quân cờ sau khi di chuyển
    public void updatePosition(){
        if(type == Type.PAWN){
            if(Math.abs(row-preRow)==2){
                twoStepped = true;
            }
        }
        x = getX(col);
        y = getY(row);
        preCol = getCol(x);
        preRow = getRow(y);
        moved = true;
    }
    //Đặt lại vị trí của quân cờ về vị trí trước đó 
    public void resetPosition(){
        col = preCol;
        row = preRow;
        x = getX(col);
        y = getY(row);
    }
    //Kiểm tra xem quân cờ có thể di chuyển đến ô mục tiêu không
    public boolean canMove(int targetCol, int targetRow){
        return false;
    }
    // Kiểm tra xem ô mục tiêu có nằm trong bàn cờ hay không
    public boolean isWithinBoard(int targetCol, int targetRow){
        if(targetCol>=0 && targetCol<=7 && targetRow>=0 && targetRow<=7){
            return true;
        }
        return false;
    }
    // Kiểm tra xem ô mục tiêu có phải là ô hiện tại không
    public boolean isSameSquare(int targetCol, int targetRow){
        if(targetCol == preCol && targetRow == preRow){
            return true;
        }
        return false;
    }
    // Lấy quân cờ ở ô mục tiêu nếu tồn tại
    public Piece getHittingP(int targetCol, int targetRow){
        for(Piece piece : GamePanel.simPieces){
            if(piece.col==targetCol && piece.row==targetRow && piece != this){
                return piece;
            }
        }
        return null;
    }
    // Xác định xem ô mục tiêu có hợp lệ để di chuyển đến không
    public boolean isValidSquare(int targetCol, int targetRow){
        hittingP = getHittingP(targetCol, targetRow);
        if(hittingP == null){
            return true;
        }else{
            if(hittingP.color != this.color){
                return true;
            }else{
                hittingP = null;
            }
        }
        return false;
    }
    // Kiểm tra xem có quân cờ nào trên đường thẳng từ vị trí hiện tại đến ô mục tiêu không
    public boolean pieceIsOnStraightLine(int targetCol, int targetRow){
        //When this piece is moving to the left
        for(int c = preCol-1; c>targetCol; c--){
            for(Piece piece : GamePanel.simPieces){
                if(piece.col == c && piece.row == targetRow){
                    hittingP = piece;
                    return true;
                }
            }
        }
        //When this piece is moving to the right
        for(int c=preCol+1; c<targetCol; c++){
            for(Piece piece : GamePanel.simPieces){
                if(piece.col == c && piece.row == targetRow){
                    hittingP = piece;
                    return true;
                }
            }
        }
        //When this piece is moving down
        for(int c=preRow+1; c<targetRow; c++){
            for(Piece piece : GamePanel.simPieces){
                if(piece.row == c && piece.col == targetCol){
                    hittingP = piece;
                    return true;
                }
            }
        }
        //When this piece is moving up
        for(int c=preRow-1; c>targetRow; c--){
            for(Piece piece : GamePanel.simPieces){
                if(piece.row == c && piece.col == targetCol){
                    hittingP = piece;
                    return true;
                }
            }
        }
        return false;
    }
    // Kiểm tra xem có quân cờ nào trên đường chéo từ vị trí hiện tại đến ô mục tiêu không
    public boolean pieceIsOnDiagonalLine(int targetCol, int targetRow){
        if(targetRow < preRow){
            //Up left
            for(int c=preCol-1; c>targetCol; c--){
                int diff = Math.abs(preCol - c);
                for(Piece piece : GamePanel.simPieces){
                    if(piece.col == c && piece.row == preRow - diff){
                        hittingP = piece;
                        return true;
                    }
                }

            }
            //Up right
            for(int c=preCol+1; c<targetCol; c++){
                int diff = Math.abs(preCol - c);;
                for(Piece piece : GamePanel.simPieces){
                    if(piece.col == c && piece.row == preRow - diff){
                        hittingP = piece;
                        return true;
                    }
                }

            }
        }
        if(targetRow > preRow){
            //Down left
            for(int c=preCol-1; c>targetCol; c--){
                int diff = Math.abs(preCol - c);
                for(Piece piece : GamePanel.simPieces){
                    if(piece.col == c && piece.row == preRow + diff){
                        hittingP = piece;
                        return true;
                    }
                }

            }
            //Down right
            for(int c=preCol+1; c<targetCol; c++){
                int diff = Math.abs(preCol - c);
                for(Piece piece : GamePanel.simPieces){
                    if(piece.col == c && piece.row == preRow + diff){
                        hittingP = piece;
                        return true;
                    }
                }

            }
        }
        return false;
    }

    public void movePiece(int col, int row) { 
        this.preCol = this.col; 
        this.preRow = this.row; 
        this.col = col; 
        this.row = row; 
        this.x = getX(col); 
        this.y = getY(row); 
        System.out.println("Piece moved to (" + col + ", " + row + ")"); 
    }

    public abstract List<int[]> getPossibleMoves();

    //Vẽ quân cờ  
    public void draw(Graphics2D g2){
        g2.drawImage(image, x+10, y+10, 80, 80, null);
    }
}