package com.fmt;
/**
 * This class contains the information of services, which is the subclass of {@link Item}.
 * @author Cong Nguyen
 * @author Yashraj Purbey
 */
public class Service extends Item {

	private double hourlyRate;
	
	
	public Service(String idItem, String nameItem, double hourlyRate) {
		super(idItem, nameItem);
		this.hourlyRate = hourlyRate;
	}


	public double getHourlyRate() {
		return this.hourlyRate;
	}

	public String toString() {
		return this.getIdItem() + " " + this.getNameItem()
				+ " " + this.hourlyRate;
	}
	
}
