import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class mac {
	public String getmacadress()
	{
		InetAddress ip;
		StringBuilder sb = new StringBuilder();
	try {
			ip = InetAddress.getLocalHost();
			System.out.println("Current IP address : " + ip.getHostAddress());
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);
			byte[] mac = network.getHardwareAddress();
			System.out.print("Current MAC address : ");
			for (int i = 0; i < mac.length; i++) 
			{
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
			}
			System.out.println(sb.toString());
	} catch (UnknownHostException e) {
		e.printStackTrace();
	} catch (SocketException e){
		e.printStackTrace();
	}
	return  sb.toString();
   }
    
    public static void main(String arg[])
    {
        
        mac m = new mac();
        m.getmacadress();
    }

}

