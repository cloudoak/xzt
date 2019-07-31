/**
 * Copyright &copy; 2012-2016  All rights reserved.
 */
package com.ambition.agile.common.supcan.freeform;

import com.ambition.agile.common.supcan.common.Common;
import com.ambition.agile.common.supcan.common.properties.Properties;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 硕正FreeForm
 * @author WangZhen
 * @version 2013-11-04
 */
@XStreamAlias("FreeForm")
public class FreeForm extends Common {

	public FreeForm() {
		super();
	}
	
	public FreeForm(Properties properties) {
		this();
		this.properties = properties;
	}
	
}
