package personal.aug.easy.validation.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Validate the field with number type, just validate range.
 * 
 * @author AUG
 *
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface ValidateNumber {

	/**
	 * The start value, default 0.
	 */
	public double min() default 0d;

	/**
	 * The end value, default Double.MAX_VALUE.
	 * 
	 */
	public double max() default Double.MAX_VALUE;
}
