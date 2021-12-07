package com.example.course.entities.enums;

public enum OrderStatus {

	WAITING_PAYMENT(1),
	PAID(2),
	PACKING(3),
	SHIPPED(4),
	DELIVERED(5),
	CALCELED(6);
	
	private int code;
	
	private OrderStatus(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static OrderStatus valueOf(int code) {
		for(OrderStatus order : OrderStatus.values()) {
			if(order.getCode() == code) {
				return order;
			}
		}
		throw new IllegalArgumentException("Status invalido");
	}
}