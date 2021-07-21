  
import java.io.*;
import java.net.*;
  
// Server class
public class Server 
{
    public static void main(String[] args) throws IOException 
    {
        ServerSocket ss = new ServerSocket(5056);
        while (true) 
        {
            Socket s = null;
              
            try 
            {
                s = ss.accept(); 
                System.out.println("A new client is connected : " + s);
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                  
                System.out.println("Assigning new thread for this client");
                Thread t = new ClientHandler(s, dis, dos);
                t.start();
                  
            }
            catch (Exception e){
                s.close();
                e.printStackTrace();
            }
        }
    }
}
  
// ClientHandler class
class ClientHandler extends Thread 
{
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;
    // Constructor
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) 
    {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }
    @Override
    public void run() 
    {   
        TicTacToe b=new TicTacToe("player1","player2",dis,dos);
        b.playGame();
        try
        {
            // closing resources
            this.dis.close();
            this.dos.close();
              
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}