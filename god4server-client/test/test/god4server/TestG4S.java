package test.god4server;

import ananas.lib.jhrs.JHRSAddressComponent;
import ananas.lib.jhrs.client.ClassEndpoint;
import ananas.lib.jhrs.client.DefaultClassEndpoint;
import ananas.lib.json.JSONObject;

public class TestG4S implements Runnable {

	public static void main(String[] arg) {
		(new TestG4S()).run();
	}

	@Override
	public void run() {
		String url = "http://localhost:8080/god4server/NodeInfo";
		ClassEndpoint ep = new DefaultClassEndpoint(url);
		// ObjectEndpoint ep = new DefaultEndpoint(url);
		{
			JHRSAddressComponent comp = ep.getAddress();
			for (; comp != null; comp = comp.getParent()) {
				System.out.println(this.nameOf(comp) + " = "
						+ comp.getAddressString());
			}
		}
		JSONObject rlt = ep.invoke(null);
		System.out.println("" + rlt);
	}

	private String nameOf(JHRSAddressComponent comp) {
		int len = 40;
		String name = comp.getClass().getName();
		for (; name.length() < len;) {
			name = name + "          ";
		}
		return name.substring(0, len);
	}
}
