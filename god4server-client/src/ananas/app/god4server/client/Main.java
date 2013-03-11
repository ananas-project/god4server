package ananas.app.god4server.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Properties;

import javax.net.SocketFactory;

import org.apache.log4j.Logger;

import ananas.lib.util.CommandLinePropertiesUtil;
import ananas.lib.util.log4j.AbstractLoggerFactory;

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

	private void doTry() throws IOException, InterruptedException {

		String host = "puyatech.com";
		int port = 22;

		SocketFactory sf = SocketFactory.getDefault();
		// SocketFactory sf = SSLSocketFactory.getDefault();

		Socket sock = sf.createSocket();
		sock.connect(new InetSocketAddress(host, port));

		InputStream in_sock = sock.getInputStream();
		OutputStream out_sock = sock.getOutputStream();

		OutputStream out_con = System.out;
		InputStream in_con = System.in;

		MyStreamPump pump1 = new MyStreamPump(in_sock, out_con);
		MyStreamPump pump2 = new MyStreamPump(in_con, out_sock);

		Thread thd = new Thread(pump2);
		thd.start();
		pump1.run();
		thd.join();

		sock.close();
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
				this.doPump();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		private void doPump() throws IOException {

			byte[] buff = new byte[256];
			for (;;) {
				int cb = this.mIn.read(buff);
				if (cb > 0) {
					this.mOut.write(buff, 0, cb);
				} else {
					break;
				}
			}

		}
	}

}
