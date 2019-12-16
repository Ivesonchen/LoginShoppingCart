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