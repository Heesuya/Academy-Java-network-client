package kh.java.func;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class QuizClient {
	public void program() {
		Scanner sc = new Scanner(System.in);
		String serverIp = "1.220.236.74";
		int serverPort = 18080;
		Socket socket = null;
		DataOutputStream dos = null;
		DataInputStream dis = null;
		
		try {
			socket = new Socket(serverIp, serverPort);
			OutputStream out = socket.getOutputStream();
			InputStream in = socket.getInputStream();
			
			dos = new DataOutputStream(out);
			dis = new DataInputStream(in);
			
			while(true) {
				String serverMgs = dis.readUTF();
				System.out.println(serverMgs);
	
				char lastLetter = serverMgs.charAt(serverMgs.length()-1);
				if(lastLetter == '?') {
					System.out.print("서버에 보낼 메세지 : ");
					String str = sc.nextLine();
					dos.writeUTF(str);
					
				}else if(lastLetter == '끝') {
					break;
				}
			}
		} catch (EOFException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				socket.close();
				dos.close();
				dis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				
	}
}
