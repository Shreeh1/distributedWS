import java.net.Socket;

public class sock {
public static boolean serverListening(String host, int port)
{
    Socket s = null;
    try
    {
        s = new Socket(host, port);
        System.out.println(s);
        return true;
    }
    catch (Exception e)
    {
        System.out.println(e);
        return false;
    }
    finally
    {
        if(s != null)
            try {s.close();}
            catch(Exception e){}
    }
}
public static void main(String[] args) {
  serverListening("127.0.0.1", 3001);
}
}
