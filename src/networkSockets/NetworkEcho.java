package networkSockets;

import javax.swing.*;
import java.io.*;
import java.net.*;

@SuppressWarnings("serial")
class Output extends JFrame implements Runnable {
	JTextArea m_area = new JTextArea(10, 10);
	InputStream m_in;
	
	public void run() {
		int len;
		byte[] buf = new byte[100];
		try {
			while ((len = m_in.read(buf)) != -1) {
				String s = new String(buf, 0, len);
				m_area.append(s);
			}
		} catch (IOException e) {
		}
		dispose();
	}

	public Output(InputStream in) {
		m_in = in;
		new Thread(this).start();
		add(new JScrollPane(m_area));
		pack();
		setVisible(true);
	}
}

public class NetworkEcho {
	public static void main(String[] args) {
		final int PORT = 7;
		
		try {
			Socket sock = new Socket("localhost", PORT);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
			System.out.println("Port error");
		}
		
		
		
		
		
		try {
			BufferedReader conin = new BufferedReader(new InputStreamReader(System.in));
			boolean cont = true;
			while (cont) {
				String line = conin.readLine();
				if (line.equals("quit")) {
					cont = false;
				} else {
					
					System.out.write(line.getBytes());
					System.out.write('\r');
					System.out.write('\n');
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
//		try {
//			Socket sock = new Socket("localhost", PORT);
//			InputStream in = sock.getInputStream();
//			OutputStream out = sock.getOutputStream();
//
//			new Output(in);
//			BufferedReader conin = new BufferedReader(new InputStreamReader(System.in));
//			boolean cont = true;
//			while (cont) {
//				String line = conin.readLine();
//				if (line.equals("quit")) {
//					cont = false;
//				} else {
//					out.write(line.getBytes());
//					out.write('\r');
//					out.write('\n');
//				}
//			}
//			out.close();
//			in.close();
//			sock.close();
//		} catch (Exception e) {
//			System.out.println(e);
//		}
	}
}
