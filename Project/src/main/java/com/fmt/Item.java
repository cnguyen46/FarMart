package com.fmt;

/**
 * This class contains the information of invoice items data.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 *
 */
public abstract class Item {

	private String idItem;
	private String typeItem;
	private String nameItem;

	public Item(String code, String nameItem) {
		this.idItem = code;
		this.nameItem = nameItem;
	}

	public String getIdItem() {
		return this.idItem;
	}

	public String getNameItem() {
		return this.nameItem;
	}

	public String getTypeItem() {
		return this.typeItem;
	}

}
