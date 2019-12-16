package org.ivesonchen.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ivesonchen.model.Wishs;
import org.ivesonchen.services.ShoppingCartServices;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class ModifyWishList
 */
@WebServlet("/ModifyWishList")
public class ModifyWishList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyWishList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
//		JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
//		String itemId = data.get("itemId").getAsString();
//		String userId = data.get("userId").getAsString();
//		String itemQty = data.get("itemQty").getAsString();
		Map<String, String> map = new HashMap<>();
		
		getParamsFromPost(request, map);
		String userId = map.get("userId");
		String itemId = map.get("itemId");
		String itemQty = map.get("itemQty");
//		System.out.println(userId.length());
//		System.out.println(itemId.length());
//		System.out.println(itemQty.length());
//
//		System.out.println(itemId + "," + userId + "," + itemQty);

		int user_id = Integer.parseInt(userId);
		int item_id = Integer.parseInt(itemId);
		int item_qty = Integer.parseInt(itemQty);
		System.out.println(user_id + "," + item_id + "," + item_qty);

		ShoppingCartServices service = new ShoppingCartServices();
		
		int result = service.findItemByUserId_ItemId(user_id, item_id);
		if(result == 0 || result == -1) {
			// first time in db
			int resultCode = service.insertWishInfo(new Wishs(user_id, item_id, item_qty));
			if(resultCode > 0) {
				response.setStatus(HttpServletResponse.SC_CREATED);
			}
		} else {
			int resultCode = service.updateItemNumber(new Wishs(user_id, item_id, item_qty));
			if(resultCode > 0) {
				response.setStatus(HttpServletResponse.SC_CREATED);
			}
		}
	}
	
	private void getParamsFromPost(HttpServletRequest request, Map<String, String> map) throws IOException {
	    BufferedReader reader = request.getReader();
	    StringBuilder sb = new StringBuilder();
	    String line = reader.readLine();
	    while (line != null) {
	      sb.append(line + "\n");
	      line = reader.readLine();
	    }
	    reader.close();
	    String params = sb.toString();
	    System.out.println(params);
	    String[] _params = params.substring(1, params.length()-2).split(",");
	    System.out.println(_params.length);
	    for (String param : _params) {
	    	System.out.println(param);
	    	String[] temp = param.split(":");
	    	map.put(temp[0].substring(1, temp[0].length()-1),temp[1].substring(1, temp[1].length()-1));
	    }
	  }

}
