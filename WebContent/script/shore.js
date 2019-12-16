$(document).ready(function(){
	
	$('[rel="addToCart"]').click(function(){
		var po = $(this);
		var display = $(".disp");
		
		var item = po.parents(".item");
		
		var itemUrl = item.find('[rel="item_url"]').val();
		var itemName = item.find('[rel="item_name"]').val();
		var itemId = item.find('[rel="item_id"]').val();
		var itemDesc = item.find('[rel="item_desc"]').val();

		
		var itemInCart = $('<div class="itemInCart"> <div class="image"><img/></div></div>');
		
		display.append(itemInCart);
	})
})