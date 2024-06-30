package mmk.omak;

class Initializer00 {
	
	public static void main(String[] args) {
		new Initializer01User().contextLoads();
		new Initializer02Currency().contextLoads();
		new Initializer03Customer().contextLoads();
		new Initializer04Supplier().contextLoads();
		new Initializer05Contact().contextLoads();
		new Initializer06ProductBrand().contextLoads();
		new Initializer07ProductCategory().contextLoads();
		new Initializer08Product().contextLoads();
		new Initializer09SalesOffer().contextLoads();
		new Initializer10SalesOrder().contextLoads();
	}
}
