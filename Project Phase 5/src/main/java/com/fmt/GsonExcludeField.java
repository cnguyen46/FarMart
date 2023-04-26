package com.fmt;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * This class is used to implement ExclusionStrategy for converting JSON file
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 */
public class GsonExcludeField implements ExclusionStrategy {

	public boolean shouldSkipField(FieldAttributes f) {
		return f.getAnnotation(GsonOmitField.class) != null;
	}

	public boolean shouldSkipClass(Class<?> clazz) {
		return false;
	}
}
