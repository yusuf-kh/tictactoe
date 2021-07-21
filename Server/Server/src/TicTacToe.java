import java.util.ArrayList;
import java.util.List;


import java.io.*;
public class TicTacToe{
    static List<String> winners = new ArrayList<String>();
    private char[][] board = new char[3][3];
    private char chance='o';
    private String player1,player2;
    private DataInputStream dis;
    private DataOutputStream dos;
    TicTacToe(){
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                board[i][j]='-';
        System.out.println("Enter player 1 name");
        player1=System.console().readLine();
        System.out.println("Enter player 2 name");
        player2=System.console().readLine();
    }
    TicTacToe(String name1,String name2,DataInputStream dis,DataOutputStream dos)
    {
        System.out.println("In constructor");
        player1=name1;
        player2=name2;
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                board[i][j]='-';
        
        this.dis=dis;
        this.dos=dos;
    }
    public void setMove(int row,int col)
    {
        if(board[row][col]!='-')
        {
            System.out.println("Illegal Move !! Try again");
            getMove();
        }
        else{
            board[row][col]=chance;
            if(chance=='o')
                chance='x';
            else
                chance='o';
            // printBoard();
        }
       
    }
    private void getMove()
    {
        int row=5, col=5;
            printBoard();
            while(true)
            {
                // System.out.println("getMove()");
                try{
                    if(chance=='o')
                        {
                            // System.out.println("Chance");
                            dos.writeUTF(player1+" enter Row and column number");
                        }
                    else
                        dos.writeUTF(player2+" enter Row and column number");
                    
                    row=dis.readInt();
                    col =dis.readInt();
                    if(row>=3||row<0||col>=3||col<0)
                    {
                        try{
                            dos.writeUTF(row+" "+col+" Move Not possible. Try again !!");
                        }
                        catch(IOException e)
                        {
                            System.out.println(e);
                        }
                    }
                    else
                        break;
                }
                catch(IOException e)
                {
                    System.out.println(e);
                }
            }

            setMove(row, col);
        
        // Random rd = new Random();
        // int row=rd.nextInt(3);
        // int col=rd.nextInt(3);
        
    }
    private void printBoard()
    {
        try{
            StringBuilder temp=new StringBuilder("");
            temp.append(" -----------\n");
            for(int i=0;i<3;i++)
            {
                temp.append("| ");
                for(int j=0;j<3;j++)
                {
                    temp.append(board[i][j]+" | ");
                }
                temp.append("\n");
            }
            temp.append(" -----------\n");
            dos.writeUTF(temp.toString());
            // System.out.println("in printboard try");
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
    public char victory()
    {
        boolean won=true;
        char temp;
        for(int i=0;i<3;i++)
        {
            temp=board[i][0];
            for(int j=1;j<3;j++)
            {
                if(Character.compare(temp, board[i][j])!=0)
                {
                    won=false;
                    break;
                }
            }
            if(won&&temp!='-')
                return temp;
            else
                won =true;
        }
        for(int j=0;j<3;j++)
        {
            temp=board[0][j];
            for(int i=1;i<3;i++)
            {
                if(Character.compare(temp, board[i][j])!=0)
                {
                    won=false;
                    break;
                }
            }
            if(won&&temp!='-')
                return temp;
            else
                won =true;
        }
        temp=board[0][0];
        won=true;
        for(int i=1;i<3;i++)
        {
            if(Character.compare(temp, board[i][i])!=0)
            {
                won=false;
                break;
            } 
        }
        if(won&&temp!='-')
            return temp;
        else 
            won=true;
        temp=board[0][2];
        for(int i=1;i<3;i++)
        {
            if(Character.compare(temp, board[i][2-i])!=0)
            {
                won=false;
                break;
            }
        }
        if(won&&temp!='-')
            return temp;
        else 
            return '-';   
    }
    public void playGame()
    {
        System.out.println("TicTacToe.playGame()");
        boolean end=false;
		for(int i=0;i<=8;i++){
		    getMove();
            char temp=victory();
            if(temp!='-')
            {
                
                    if(temp=='o'){
                        
                        try{
                            dos.writeUTF(player1+" won !!");
                        }
                        catch(IOException e)
                        {
                            System.out.println(e);
                        }
                        //winners.add(player1);
                    }
                    else{
                        try{
                            dos.writeUTF(player2+" won !!");
                        }
                        catch(IOException e)
                        {
                            System.out.println(e);
                        }
                        //winners.add(player2);
                     }
                    end=true;
                    break;
                
            }
		}
        if(!end)
        {
            try{
                dos.writeUTF("Game Draw!!");
            }
            catch(IOException e)
            {
                System.out.println(e);
            }
        }
        try{
            dos.writeUTF("GameOver");
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        // printBoard();
    }
    // static void printWinners()
    // {
    //     for(int i=0;i<winners.size();i++)
    //     {
    //         System.out.println(winners.get(i));
    //     }
    // }
}