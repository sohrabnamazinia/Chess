
import game.*;

import java.util.*;


public class Board{
    private ArrayList<Piece> whitePieces;
    private ArrayList<Piece> blackPieces;
    private ArrayList<Piece> allPieces;


    public Board(){
        this.whitePieces = new ArrayList<Piece>();
        this.blackPieces = new ArrayList<Piece>();
    }


    public Board(ArrayList<Piece> whitePieces, ArrayList<Piece> blackPieces){
        this.whitePieces = whitePieces;
        this.blackPieces = blackPieces;
        this.allPieces = new ArrayList<Piece>();
        for( Piece p : this.whitePieces ){
            this.allPieces.add(p);
        }
        for( Piece p : this.blackPieces ){
            this.allPieces.add(p);
        }
    }

    public ArrayList<Piece> getPieces(){
        return this.allPieces;
    }


    public void initPieces(){
        // White Players
        //  Pawns
        for(char ch = 'A'; ch < 'I'; ch++){
            Pawn newPawn = new Pawn(ch, 2, true, "WP");
            this.whitePieces.add(newPawn);
        }
        //  Rook
        for(char ch = 'A'; ch < 'I'; ch += 7){
            Rook newRook = new Rook(ch, 1, true, "WR");
            this.whitePieces.add(newRook);
        }
        //  Knight
        for(char ch = 'B'; ch < 'H'; ch += 5){
            Knight newKnight = new Knight(ch, 1, true, "WN");
            this.whitePieces.add(newKnight);
        }
        //  Bishop
        for(char ch = 'C'; ch < 'G'; ch += 3){
            Bishop newBishop = new Bishop(ch, 1, true, "WB");
            this.whitePieces.add(newBishop);
        }
        //   Queen
        Queen whiteQueen = new Queen('D', 1, true,"WQ");
        this.whitePieces.add(whiteQueen);
        //  King
        King whiteKing = new King('E', 1, true, "WK");
        this.whitePieces.add(whiteKing);
        // Black Players
        //  Pawns
        for(char ch = 'A'; ch < 'I'; ch++){
            Pawn newPawn = new Pawn(ch, 7, false, "BP");
            this.blackPieces.add(newPawn);
        }
        //  Rook
        for(char ch = 'A'; ch < 'I'; ch += 7){
            Rook newRook = new Rook(ch, 8, false, "BR");
            this.blackPieces.add(newRook);
        }
        //  Knight
        for(char ch = 'B'; ch < 'H'; ch += 5){
            Knight newKnight = new Knight(ch, 8, false, "BN");
            this.blackPieces.add(newKnight);
        }
        //  Bishop
        for(char ch = 'C'; ch < 'G'; ch += 3){
            Bishop newBishop = new Bishop(ch, 8, false, "BB");
            this.blackPieces.add(newBishop);
        }
        //   Queen
        Queen blackQueen = new Queen('D', 8, false, "BQ");
        this.blackPieces.add(blackQueen);
        //  King
        King blackKing = new King('E', 8, false, "BK");
        this.blackPieces.add(blackKing);
    }


    public boolean isTakenByWhite(int y, char x){
        boolean flag = false;
        for( Piece p : whitePieces ){
            if( p.getY() == y && p.getX() == x ){

                flag = true;
                break;
            }
        }
        return flag;
    }


    public boolean isTakenByBlack(int y, char x){
        boolean flag = false;
        for( Piece p : blackPieces ){
            if( p.getY() == y && p.getX() == x ){

                flag = true;
                break;
            }
        }
        return flag;
    }


    public boolean isTaken(int y, char x){
        boolean flag = false;
        flag = isTakenByWhite(y, x);
        if( !flag )
            flag = isTakenByBlack(y, x);
        return flag;
    }


    public Piece takenByWhite(int y, char x){
        Piece newPiece = null;
        for( Piece p : whitePieces ){
            if( p.getY() == y && p.getX() == x ){

                newPiece = p;
                break;
            }
        }
        return newPiece;
    }


    public Piece takenByBlack(int y, char x){
        Piece newPiece = null;
        for( Piece p : blackPieces ){
            if( p.getY() == y && p.getX() == x ){

                newPiece = p;
                break;
            }
        }
        return newPiece;
    }


    public Piece takenBy(int y, char x){
        Piece newPiece = null;
        newPiece = takenByWhite(y, x);
        if( newPiece == null )
            newPiece = takenByBlack(y, x);
        return newPiece;
    }



    public void printBoard(String player){
        if( player.equals("W") ){

            for(int j = 9; j > 0; j--){
                for(char i = 'A'; i < 'J'; i++){
                    if( j == 9 ){
                        if( i - 'A' > 0 ){

                            System.out.print((char)(i - 1) + "   ");
                        }else{
                            System.out.print("    ");
                        }
                    }else{
                        if( i == 'A' ){
                            System.out.print(j + "   ");
                        }else{// Main Clause
                            char mapedI = (char)(i - 1);
                            if( isTaken(j, mapedI) ){

                                System.out.print(takenBy(j, mapedI).getName() + "  ");
                            }else{
                                System.out.print("-   ");
                            }
                        }
                    }
                }
                System.out.println();
            }
        }else{
            for(int j = 0; j < 9; j++){
                for(char i = 'I'; i >= 'A'; i--){
                    if( j == 0 ){
                        if( i == 'I' ){

                            System.out.print("    ");
                        }else{
                            System.out.print((char)(i) + "   ");
                        }
                    }else{
                        if( i == 'I' ){
                            System.out.print(j + "   ");
                        }else{// Main Clause
                            char mapedI = (char)(i);
                            if( isTaken(j, mapedI) ){

                                System.out.print(takenBy(j, mapedI).getName() + "  ");
                            }else{
                                System.out.print("-   ");
                            }
                        }
                    }
                }
                System.out.println();
            }
        }
    }

    public boolean move(String from, String to, String color){// Name Format: 1D
        char[] fromArray  = from.toCharArray();
        char[] toArray    = to.toCharArray();
        if( fromArray.length == 2 && toArray.length == 2 ){// Name is consisted of 2 chars

            if( fromArray[0] >= '1' && fromArray[0] <= '8' && fromArray[1] >= 'A' && fromArray[1] <= 'H' ){// Check from bound

                if( toArray[0] >= '1' && toArray[0] <= '8' && toArray[1] >= 'A' && toArray[1] <= 'H' ){// Check to bound

                    if( canGo(fromArray, toArray, color) ){

                        ArrayList<Piece> enemy = null;
                        ArrayList<Piece> selector = null;
                        Piece found   = null;
                        Piece attack  = null;
                        if( color.equals("W") ){

                            enemy     = this.blackPieces;
                            selector  = this.whitePieces;
                            found     = takenByWhite(fromArray[0] - '0', fromArray[1]);
                            attack    = takenByBlack(toArray[0] - '0', toArray[1]);
                        }else{
                            enemy     = this.whitePieces;
                            selector  = this.blackPieces;
                            found     = takenByBlack(fromArray[0] - '0', fromArray[1]);
                            attack    = takenByWhite(toArray[0] - '0', toArray[1]);
                        }
                        if( attack != null ){

                            enemy.remove(attack);
                            this.allPieces.remove(attack);
                        }
                        if( found instanceof Pawn && ((Pawn) found).getFirstMove() == true ){
                            ((Pawn) found).setFirstMove(false);
                        }
                        found.setY(toArray[0] - '0');
                        found.setX(toArray[1]);
                        return true;
                    }else{
                        return false;
                    }
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    public boolean virtualCheck(ArrayList<Piece> enemy, char x, int y){
        for(Piece p : enemy){
            if( p.canMove(x, y) ){ // Check if move is valid

                if( p.checkWay(this.allPieces, x, y) ){

                    if( p instanceof Pawn && p.getX() - x == 0 ){

                        return false;
                    }else{
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public boolean kingCheck(ArrayList<Piece> base, ArrayList<Piece> enemy){
        // find king
        int kingY   = 0;
        char kingX  = 'A';
        for(Piece p : base){
            if( p instanceof King){

                kingY = p.y;
                kingX = p.x;
            }
        }
        if( virtualCheck(enemy, kingX, kingY) ){

            return true;
        }
        return false;
    }


    private boolean kingMate(ArrayList<Piece> base, ArrayList<Piece> enemy){
        int kingY   = 0;
        char kingX  = 'A';
        King king = null;
        String color = null;
        for(Piece p : base){
            if( p instanceof King){

                kingY = p.y;
                kingX = p.x;
                king = (King)p;
                color = Character.toString(p.getName().charAt(0));
            }
        }
        int[] X = {1, 1, 1, 0, -1, -1, -1, 0};
        int[] Y = {1, 0, -1, -1, -1, 0, 1, 1};
        int counter = 0;
        for(int i = 0; i < 8; i++){
            char tempX  = (char)(kingX + X[i]);
            int tempY   = kingY +  Y[i];
            if( canGo(king, tempX, tempY, color) ){

                System.out.println(king.getName() + " : " + tempX + tempY);
                counter++;
            }
        }
        if( counter == 0 && virtualCheck(enemy, kingX, kingY) ){

            return true;
        }else{
            return false;
        }
    }

    /**
     * Check which player is checkmated
     * @author Omiid
     * @return W for white, B for Black
     */
    public String checkMate(){
        if( kingMate(this.blackPieces, this.whitePieces) ){

            return "B";
        }else if ( kingMate(this.whitePieces, this.blackPieces) ){

            return "W";
        }else{
            return null;
        }
    }


    public boolean canGo(Piece p, char x, int y, String color){
        char[] fromArray = {(char)(p.getY() + '0'), p.getX()};
        char[] toArray = {(char)(y + '0'), x};
        return canGo(fromArray, toArray, color);

    }
    public boolean canGo(char[] fromArray, char []toArray, String color){
        ArrayList<Piece> selector = null;
        ArrayList<Piece> enemy    = null;
        Piece found   = null;
        boolean toBusy  = false;
        Piece attack    = null;
        boolean booleanColor = false;
        if( color.equals("W") ){

            selector  = this.whitePieces;
            enemy     = this.blackPieces;
            found     = takenByWhite(fromArray[0] - '0', fromArray[1]);
            toBusy    = isTakenByWhite(toArray[0] - '0', toArray[1]);
            attack    = takenByBlack(toArray[0] - '0', toArray[1]);
            booleanColor = true;
        }else{
            selector  = this.blackPieces;
            enemy     = this.whitePieces;
            found     = takenByBlack(fromArray[0] - '0', fromArray[1]);
            toBusy    = isTakenByBlack(toArray[0] - '0', toArray[1]);
            attack    = takenByWhite(toArray[0] - '0', toArray[1]);
        }
        if( found != null && found.getColor() == booleanColor && !toBusy ){// Found and to is not busy

            if( kingCheck(selector, enemy) && !(found instanceof King) ){

                return false;
            }else{
                if( toArray[0] >= '1' && toArray[0] <= '8' && toArray[1] >= 'A' && toArray[1] <= 'H' ){

                    if( found.canMove(toArray[1], toArray[0] - '0') ){ // Check if move is valid
                        if( found.checkWay(this.allPieces, toArray[1], toArray[0] - '0') ){
                            if( (found instanceof Pawn) && found.crossMove(toArray[1], toArray[0] - '0') ){

                                if( attack == null ){

                                    return false;
                                }else{
                                    if( toArray[0] - '0' == attack.getY() && toArray[1] == attack.getX() ){

                                        if( found.color && toArray[0] < fromArray[0] ){

                                            return true;
                                        }else if( !found.color && fromArray[0] < toArray[0] ){

                                            return true;
                                        }else{
                                            return false;
                                        }
                                    }else{
                                        return false;
                                    }
                                }
                            }else if( (found instanceof Pawn) && toArray[1] - fromArray[1] == 0 && attack != null){

                                return false;
                            }else if( (found instanceof King) && virtualCheck(enemy, toArray[1], toArray[0] - '0')){

                                return false;
                            }else{
                                if( attack != null ){

                                    enemy.remove(attack);
                                    this.allPieces.remove(attack);
                                }
                                char tempX = found.getX();
                                int tempY = found.getY();
                                found.setY(toArray[0] - '0');
                                found.setX(toArray[1]);
                                boolean flag = true;
                                if( kingCheck(selector, enemy) ){
                                    flag = false;
                                }
                                if( attack != null ){
                                    enemy.add(attack);
                                    this.allPieces.add(attack);
                                }
                                found.setX(tempX);
                                found.setY(tempY);
                                return flag;
                            }
                        }else{
                            return false;
                        }
                    }else{
                        return false;
                    }
                }else{
                    return false;
                }
            }
        }else{
            return false;
        }
    }


}
