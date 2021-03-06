package org.test4j.datafilling.annotations;

import java.io.Serializable;

import org.test4j.datafilling.annotations.FillInteger;

@SuppressWarnings("serial")
public class IntegerValueWithErrorPojo implements Serializable {

	@FillInteger(value = "hfajkfhalfh")
	private int intFieldWithErrorInAnnotation;

	/**
	 * @return the intFieldWithErrorInAnnotation
	 */
	public int getIntFieldWithErrorInAnnotation() {
		return intFieldWithErrorInAnnotation;
	}

	/**
	 * @param intFieldWithErrorInAnnotation
	 *            the intFieldWithErrorInAnnotation to set
	 */
	public void setIntFieldWithErrorInAnnotation(int intFieldWithErrorInAnnotation) {
		this.intFieldWithErrorInAnnotation = intFieldWithErrorInAnnotation;
	}
}
