package com.fmt;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This interface is used as annotation for excluding some fields during converting to JSON file.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface GsonOmitField {}