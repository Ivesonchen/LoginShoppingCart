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
	
	<div class="container">
		<div class="row">
			<div class="col-2"></div>
			<div class="col-8 header">
				<span>Grocery Store</span>
			</div>
		</div>

		<div class="main row">
			<div class="cart col-5">
				<h1>Shopping Cart:</h1>
				<div class="disp blue_border"></div>
			</div>

			<div class="storageList col-7 green_border">
				<h1>Item List</h1>
				<jsp:useBean id="service"
					class="org.ivesonchen.services.ShoppingCartServices"></jsp:useBean>
				<%List<Item> list = service.getAllItems();%>

				<%for (Item ele : list) {%>
				<div class="item blue_border">
					<div class="image red_border">
						<img src="./images/<%=ele.getImageUrl()%>" /> <input type="text"
							value="<%=ele.getImageUrl()%>" style="display: none"
							rel="item_url" />

					</div>

					<div class="word red_border">
						<div class="name"><%=ele.getName()%></div>
						<input type="text" value="<%=ele.getName()%>"
							style="display: none" rel="item_name" />
						<div class="description"><%=ele.getDescription()%></div>
						<input type="text" value="<%=ele.getDescription()%>"
							style="display: none" rel="item_desc" />

					</div>

					<div class="operation green_border" rel="addToCart">
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
	<script>
	$(document).ready(function(e){	
		
		refeshCart();
		
		$('[rel="addToCart"]').click(function(){
			var po = $(this);
			var display = $(".disp");
			
			var item = po.parents(".item");
			
			var itemUrl = item.find('[rel="item_url"]').val();
			var itemName = item.find('[rel="item_name"]').val();
			var itemId = item.find('[rel="item_id"]').val();
			var itemDesc = item.find('[rel="item_desc"]').val();
			
			let session = JSON.parse(sessionStorage.getItem('cart'));
			var sessionObj = {};

			if(session == null){
				sessionObj[itemId] = {
						"itemUrl" : itemUrl,
						"itemName" : itemName,
						"itemId" : itemId,
						"itemDesc" : itemDesc,
						"itemQty" : 1
					};
				
				sessionStorage.setItem('cart', JSON.stringify(sessionObj));
			}else {
				if(session[itemId]){
					session[itemId].itemQty += 1;
					sessionStorage.setItem('cart', JSON.stringify(session));
				} else {
					session[itemId] = {
							"itemUrl" : itemUrl,
							"itemName" : itemName,
							"itemId" : itemId,
							"itemDesc" : itemDesc,
							"itemQty" : 1
						};
					sessionStorage.setItem('cart', JSON.stringify(session));
				}
			}
			console.log(session)
			refeshCart();

		})
		
		$(".disp").on('click', '[rel="plusOne"]', function(){
			var po = $(this);
			var itemId = po.parents(".itemInCart").find('[rel="itemId"]').val();
				
			let session = JSON.parse(sessionStorage.getItem('cart'));
			session[itemId].itemQty++;
			sessionStorage.setItem('cart', JSON.stringify(session));
			
			refeshCart();
		})
		
		
		$(".disp").on('click', '[rel="minusOne"]', function(){
			var po = $(this);
			var itemId = po.parents(".itemInCart").find('[rel="itemId"]').val();
			
			let session = JSON.parse(sessionStorage.getItem('cart'));
			var counter = --session[itemId].itemQty;
			if(counter != 0){
				sessionStorage.setItem('cart', JSON.stringify(session));
			} else {
				po.parents(".itemInCart").remove();
				delete session[itemId];
				sessionStorage.setItem('cart', JSON.stringify(session));
			}
			
			refeshCart();	
		})
		
		$(".disp").on('click', '[rel="delete"]', function(){
			var po = $(this);
			var itemId = po.parents(".itemInCart").find('[rel="itemId"]').val();
			
			let session = JSON.parse(sessionStorage.getItem('cart'));

			po.parents(".itemInCart").remove();
			delete session[itemId];
			sessionStorage.setItem('cart', JSON.stringify(session));

			refeshCart();	
		})
		
		$(".disp").on('click', '[rel="move"]', function(){
			var po = $(this);
			let session = JSON.parse(sessionStorage.getItem('cart'));

			var itemId = po.parents(".itemInCart").find('[rel="itemId"]').val();
			
			var userId = $('[rel="currentUserId"]').val();
			var itemQty = session[itemId].itemQty.toString();
			
			var data = {
					"itemId":itemId,
					"userId":userId,
					"itemQty":itemQty
			};
			
			console.log(data);
			
 			$.ajax({
			    type: "POST",
			    url: "ModifyWishList",
			    contentType: "json",
			    data: JSON.stringify(data),
			    success: function(response) {
			        console.log(response);
			    }
			});
			
			po.parents(".itemInCart").remove();
			delete session[itemId];
			sessionStorage.setItem('cart', JSON.stringify(session));

			refeshCart();
		})
	})
	
	function refeshCart(){
		
		$(".disp").empty();
		var sessionObj = {};
		
		var temp = sessionStorage.getItem('cart');
		if(temp != null){
			sessionObj = JSON.parse(temp);
		} else {
			return;
		}
		
		Object.keys(sessionObj).forEach((key) => {
			var display = $(".disp");
			
			var item = sessionObj[key];

			var itemInCart = $(`<div class="itemInCart">
					<input rel="itemId" style="display:none">
					<div class="image"><img rel="item_img"/>
					</div>
					<div class="qty" rel="itemQty"></div>
					<div class="control">
						<div class="plusOne" rel="plusOne">+</div>
						<div class="minusOne" rel="minusOne">-</div>
					</div>
					<div class="del" rel="delete">Delete</div>
					<div class="move" rel="move">Move</div>
				</div>`);			
			
  			itemInCart.find('[rel="item_img"]').attr("src","./images/" + item.itemUrl);
  			itemInCart.find('[rel="itemId"]').attr("value",item.itemId);
  			itemInCart.find('[rel="itemQty"]').html(item.itemQty);
 			display.append(itemInCart);
		});
	}
	</script>
</body>
</html>