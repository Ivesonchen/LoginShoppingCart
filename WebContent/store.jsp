<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="org.ivesonchen.model.Item"%>
<%@ page import="org.ivesonchen.services.ShoppingCartServices"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Macys' store</title>
<script src="webjars/jquery/3.0.0/jquery.min.js"></script>
<script src="webjars/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="webjars/bootstrap/4.4.1/css/bootstrap.min.css" />
<link rel="stylesheet" href="./style/store.css">

</head>
<body>
	<% Integer id = (Integer) session.getAttribute("userId");
	%>
	
	
	<input rel="currentUserId" style="display:none" value="<%=id %>"/>
	
	<div class="container-fluid full">
		<div class="row title">
			<div class="col-8 offset-2 header">
				<span>Grocery Store</span>
			</div>
		</div>

		<div class="main row">
			<div class="cart col-5">
				<span class="cart_title">Shopping Cart:</span>
				<div class="disp"></div>
			</div>

			<div class="storageList col-7">
				<span class="store_title">Item List</span>
				<jsp:useBean id="service"
					class="org.ivesonchen.services.ShoppingCartServices"></jsp:useBean>
				<%List<Item> list = service.getAllItems();%>

				<%for (Item ele : list) {%>
				<div class="item">
					<div class="image">
						<img src="./images/<%=ele.getImageUrl()%>" /> <input type="text"
							value="<%=ele.getImageUrl()%>" style="display: none"
							rel="item_url" />

					</div>

					<div class="word">
						<div class="name"><%=ele.getName()%></div>
						<input type="text" value="<%=ele.getName()%>"
							style="display: none" rel="item_name" />
						<div class="description"><%=ele.getDescription()%></div>
						<input type="text" value="<%=ele.getDescription()%>"
							style="display: none" rel="item_desc" />

					</div>

					<div class="operation" rel="addToCart">
						add <input type="text" value="<%=ele.getId()%>"
							style="display: none" rel="item_id" />
					</div>
				</div>

				<%}%>
			</div>

		</div>

		<div class="row">
			<div class=""></div>
		</div>
		
		<div class="row">
			<div class="col-4 offset-1">
				<a href="wishingList.jsp" class="btn btn-outline-primary"> Go to my wishing list.</a>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="./script/store.js"></script>
</body>
</html>