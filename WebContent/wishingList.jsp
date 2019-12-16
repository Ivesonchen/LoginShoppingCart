<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="org.ivesonchen.model.Item"%>
<%@ page import="org.ivesonchen.services.ShoppingCartServices"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your wishing list</title>

<script src="webjars/jquery/3.0.0/jquery.min.js"></script>
<script src="webjars/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="webjars/bootstrap/4.4.1/css/bootstrap.min.css" />
<link rel="stylesheet" href="./style/wishingList.css">
</head>
<body>
	<jsp:useBean id="service"
		class="org.ivesonchen.services.ShoppingCartServices"></jsp:useBean>
	<%
		Integer userId = (Integer) session.getAttribute("userId");
		Map<Item, Integer> map = service.getWishListItem(userId);
	%>

	<div class="container">

		<div class="title row">
			<div class="col-2"></div>
			<div class="col-8 header">
				<span>Your wishing list: </span>
			</div>
		</div>

		<div class="row">

			<div class="list col-8 offset-2 red_border">
				<% for(Map.Entry<Item, Integer> entry : map.entrySet()){%>
				<% Item item = entry.getKey();
				   Integer number = entry.getValue();
				%>

				<div class="item blue_border">
					<div class="image red_border">
						<img src="./images/<%=item.getImageUrl()%>" /> 
						<input type="text" value="<%=item.getImageUrl()%>" style="display: none"
							rel="item_url" />
						<input type="text" value="<%=item.getId()%>" style="display: none"
							rel="item_id" />
					</div>

					<div class="word red_border">
						<div class="name"><%=item.getName()%></div>
						<input type="text" value="<%=item.getName()%>"
							style="display: none" rel="item_name" />
						<div class="description"><%=item.getDescription()%></div>
						<input type="text" value="<%=item.getDescription()%>"
							style="display: none" rel="item_desc" />
					</div>
				
					<div class="qty" rel="itemQty"><%= number%></div>
					<input type="text" value="<%= number%>" style="display: none"
							rel="item_qty" />
				</div>
				<%} %>
			
			</div>

		</div>
		
		<div class="row">
			<a href="store.jsp" class="btn btn-outline-primary" rel="back">Go back to store</a>
		</div>
		
		
	</div>
	<script type="text/javascript" src="./script/wishingList.js"></script>
</body>
</html>