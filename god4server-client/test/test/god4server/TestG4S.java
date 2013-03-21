package test.god4server;

import ananas.lib.jhrs.JHRSAddress;
import ananas.lib.jhrs.client.ClassEndpoint;
import ananas.lib.jhrs.client.DefaultClassEndpoint;
import ananas.lib.jhrs.client.DefaultObjectEndpoint;
import ananas.lib.jhrs.client.ObjectEndpoint;
import ananas.lib.json.JSONObject;

public class TestG4S implements Runnable {

	public static void main(String[] arg) {
		(new TestG4S()).run();
	}

	@Override
	public void run() {
		String url = "http://localhost:8080/god4server/NodeInfo";
		ClassEndpoint ep2 = new DefaultClassEndpoint(url);
		ObjectEndpoint ep = new DefaultObjectEndpoint(url);
		{
			JHRSAddress comp = ep.getAddress();
			for (; comp != null; comp = comp.getParent()) {
				System.out.println(this.nameOf(comp) + " = " + comp.getURL());
			}
		}
		JSONObject rlt = ep.invoke(null);
		System.out.println("" + rlt);
	}

	private String nameOf(JHRSAddress comp) {
		int len = 40;
		String name = comp.getClass().getName();
		for (; name.length() < len;) {
			name = name + "          ";
		}
		return name.substring(0, len);
	}
}
