package Resouces;

import java.util.ArrayList;
import java.util.List;

import Pojo.Login;
import Pojo.OrderDetail;
import Pojo.Orders;
import StepDefinitions.StepDefinitions;

public class TestData {
	
	public static Login payload_login(String userId) {
		
		Login lg = new Login();
		lg.setUserEmail(userId);
		lg.setUserPassword("Sayan@12");
		
		return lg;
	}
	
	public static Orders payload_placeOrder(String country) {
		
		OrderDetail od = new OrderDetail();
		od.setCountry(country);
		od.setproductOrderedId(StepDefinitions.productId);
		
		ArrayList<OrderDetail> list = new ArrayList<OrderDetail>();
		list.add(od);
		
		Orders or = new Orders();
		or.setOrders(list);
		
		return or;
		
	}
}
