package Resouces;

public enum APIResources {
			
	LoginAPI("/api/ecom/auth/login"),
	CreateProductAPI("/api/ecom/product/add-product"),
	CreateOrderAPI("/api/ecom/order/create-order"),
	DeleteOrderAPI("/api/ecom/order/delete-order/{orders}"),
	DeleteProductAPI("/api/ecom/product/delete-product/{productOrderId}");

	private String resource;
	
	APIResources(String resource) {
		this.resource = resource;
	}
	
	public String getResouce() {
		return resource;
		
	}
}
