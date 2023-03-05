package com.fmt;

/**
 * This class contains the information of services, which is the subclass of
 * {@link Item}.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 */
public class Service extends Item {

	private final double hourlyRate;

	public Service(String idItem, String typeItem, String nameItem, double hourlyRate) {
		super(idItem, typeItem, nameItem);
		this.hourlyRate = hourlyRate;
	}

	public double getHourlyRate() {
		return this.hourlyRate;
	}

	@Override
	public String toString() {
		return this.getIdItem() + " " + this.getTypeItem() 
				+ " " + this.getNameItem() + " " + this.hourlyRate;
	}

}
