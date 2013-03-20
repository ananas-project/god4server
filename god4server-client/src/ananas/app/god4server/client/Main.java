package ananas.app.god4server.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import ananas.lib.io.IStreamConnection;
import ananas.lib.util.CommandLinePropertiesUtil;
import ananas.lib.util.logging.AbstractLoggerFactory;
import ananas.lib.util.logging.Logger;

public class Main implements Runnable {

	private final static Logger logger = (new AbstractLoggerFactory() {
	}).getLogger();

	private final Properties mProp;

	public Main(Properties prop) {
		this.mProp = prop;
	}

	public static void main(String[] args) {
		Properties prop = CommandLinePropertiesUtil.argumentsToProperties(args);
		Main theMain = new Main(prop);
		theMain.run();
	}

	@Override
	public void run() {
		logger.info(this.mProp + "");

		try {
			this.doTry();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	class MySSHStream implements IStreamConnection {

		private String mHost;
		private int mPort;
		private InputStream mIn;
		private OutputStream mOut;

		public MySSHStream(String host, int port) {
			this.mHost = host;
			this.mPort = port;
		}

		@Override
		public OutputStream getOutputStream() throws IOException {
			return this.mOut;
		}

		@Override
		public void close() throws IOException {
			// TODO Auto-generated method stub

		}

		@Override
		public InputStream getInputStream() throws IOException {
			return this.mIn;
		}

		public void _conn() {

			/*
			 * SocketFactory sf = SocketFactory.getDefault(); // SocketFactory
			 * sf = SSLSocketFactory.getDefault();
			 * 
			 * Socket sock = sf.createSocket(); sock.connect(new
			 * InetSocketAddress(host, port));
			 */
		}

		public void connect() throws IOException {
			String cmd = "ssh root@puyatech.com";
			Process proc = Runtime.getRuntime().exec(cmd);

			this.mIn = proc.getInputStream();
			this.mOut = proc.getOutputStream();
		}
	}

	private void doTry() throws IOException, InterruptedException {

		String host = "puyatech.com";
		int port = 22;

		IStreamConnection conn;
		{
			MySSHStream stream = new MySSHStream(host, port);
			stream.connect();
			conn = stream;
		}

		InputStream in_sock = conn.getInputStream();
		OutputStream out_sock = conn.getOutputStream();

		OutputStream out_con = System.out;
		InputStream in_con = System.in;

		MyStreamPump pump1 = new MyStreamPump(in_sock, out_con);
		MyStreamPump pump2 = new MyStreamPump(in_con, out_sock);

		Thread thd1 = new Thread(pump1);
		Thread thd2 = new Thread(pump2);
		thd1.start();
		thd2.start();
		// pump1.run();
		thd1.join();
		thd2.join();

		conn.close();
	}

	class MyStreamPump implements Runnable {

		private OutputStream mOut;
		private InputStream mIn;

		public MyStreamPump(InputStream in, OutputStream out) {
			this.mIn = in;
			this.mOut = out;
		}

		@Override
		public void run() {

			try {

				logger.trace("doPump:" + this);

				this.doPump();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		private void doPump() throws IOException {

			byte[] buff = new byte[1];
			for (;;) {
				int cb = this.mIn.read(buff);
				if (cb > 0) {
					this.mOut.write(buff, 0, cb);
					byte b = buff[0];
					if (b == 0x0a || b == 0x0d) {
						System.out.println(this + "CRLF");
					}
				} else {
					break;
				}
			}
		}
	}

}
