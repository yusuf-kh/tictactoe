// Java implementation for a client
// Save file as Client.java
  
import java.io.*;
import java.net.*;
import java.util.Scanner;
  
// Client class
public class Client 
{
    public static void main(String[] args) throws IOException 
    {
        try
        {
            Scanner scn = new Scanner(System.in);
            InetAddress ip = InetAddress.getByName("localhost");
            Socket s = new Socket(ip, 5056);
      
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            while (true) 
            {
                
                String temp1=dis.readUTF();
                String temp2=dis.readUTF();
                System.out.println(temp1);
                System.out.println(temp2);
                if(temp2.equals("GameOver"))
                break;
             
                int row = scn.nextInt();
                int col = scn.nextInt();
                dos.writeInt(row);
                dos.writeInt(col) ;
            }
              
            scn.close();
            dis.close();
            dos.close();
           // s.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}