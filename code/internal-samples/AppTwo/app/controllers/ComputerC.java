package controllers;


import mef.presenters.commands.IndexComputerCommand;
import mef.presenters.replies.ComputerReply;

import org.mef.framework.commands.IndexCommand;
import org.mef.framework.replies.Reply;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import boundaries.ComputerBoundary;

public class ComputerC extends Controller
{
	public static Result index(int pageNum, String fieldName, String orderBy, String filter) 
    {
		ComputerBoundary boundary = ComputerBoundary.create();
		ComputerReply reply = boundary.process(new IndexComputerCommand(4, pageNum, orderBy, filter));
		return renderOrForward(boundary, reply);
	}
	
    private static Result renderOrForward(ComputerBoundary boundary, ComputerReply reply)
    {
		if (reply.failed())
		{
			return redirect(routes.ErrorC.logout());
		}
		
//		Form<UserModel> frm = null;
		switch(reply.getDestination())
		{
		case Reply.VIEW_INDEX:
//			return ok(views.html.computer.render(reply._allL));    	
			return ok(views.html.computer.render(reply._page));    	


		default:
			return play.mvc.Results.redirect(routes.ErrorC.logout());	
    	}
	}
}
