	$(document).ready(function(e){
		
		$('[rel="back"]').click(function(){
			
			console.log("hahahha");
			var po = $(this);
			var cart = {};
			
			$(".list").find(".item").each((index,ele)=>{
				var itemId = $(ele).find('[rel="item_id"]').val();
				var itemName = $(ele).find('[rel="item_name"]').val();
				var itemUrl = $(ele).find('[rel="item_url"]').val();
				var itemDesc = $(ele).find('[rel="item_desc"]').val();
				var itemQty = $(ele).find('[rel="item_qty"]').val();
				
				
				cart[itemId] = {
						"itemName": itemName,
						"itemUrl" : itemUrl,
						"itemId" :itemId,
						"itemDesc" : itemDesc,
						"itemQty" : parseInt(itemQty)
				}
			});
			
			sessionStorage.setItem('cart', JSON.stringify(cart));
		})
		
})