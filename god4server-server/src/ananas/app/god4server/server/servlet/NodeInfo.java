package ananas.app.god4server.server.servlet;

import ananas.lib.jhrs.JHRSClass;
import ananas.lib.jhrs.server.AbstractJHRSClassResponder;
import ananas.lib.jhrs.server.JHRSRequest;
import ananas.lib.jhrs.server.JHRSResponse;

/**
 * Servlet implementation class HTTPObject
 */
public class NodeInfo extends AbstractJHRSClassResponder /* HttpServlet */{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NodeInfo() {
		super();
	}

	@Override
	public void onInvokeStaticMethod(JHRSClass aClass, JHRSRequest request,
			JHRSResponse response) {
		// TODO Auto-generated method stub
		super.onInvokeStaticMethod(aClass, request, response);
	}

}
