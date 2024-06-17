package exam;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ChattingClient {

	public void chattingClient() {

		String serverIp = "127.0.0.1";
		//포트 번호는 서버와 클라이언트와 같은 번호를 사용해야 연결이 된다. 
		int serverPort = 8888;

		Socket socket = null;

		DataInputStream dis = null;

		DataOutputStream dos = null;

		try {

			socket = new Socket(serverIp, serverPort);

			InputStream in = socket.getInputStream();

			OutputStream out = socket.getOutputStream();

			dis = new DataInputStream(in);

			dos = new DataOutputStream(out);

			String serverMsg = dis.readUTF();

			System.out.println(serverMsg);
			
			String serverDateMsg = dis.readUTF();

			System.out.println(serverDateMsg);
			
			//서버메세지를 받는 로직이 없음. 
			

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				dis.close();

				dos.close();

			} catch (IOException e) {

				e.printStackTrace();

			}

		}

	}

}
