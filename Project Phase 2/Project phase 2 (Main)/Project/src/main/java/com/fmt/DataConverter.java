package com.fmt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class contain many methods that uses to convert data
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 */
public class DataConverter {

	/**
	 * This method is used to convert the HashMap into the List 
	 * 
	 * @param <T>
	 * @param hashMapData<String, T>
	 * @return List<T>
	 */
	
	public static <T> List<T> convertMapToList(HashMap<String, T> hashMapData) {

		List<T> listData = new ArrayList<>();
		for (String str : hashMapData.keySet()) {
			listData.add(hashMapData.get(str));
		}
		return listData;
	}
}
