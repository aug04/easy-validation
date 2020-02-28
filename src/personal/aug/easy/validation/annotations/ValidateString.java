package personal.aug.easy.validation.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Validate the field with String type, can check minimum, maximum length and
 * check regular expression.
 * 
 * @author AUG
 *
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface ValidateString {

	/**
	 * Set the minimum length of the string. Default value 0.
	 */
	public int minLength() default 0;

	/**
	 * Set the maximum length of the string. Default value Integer.MAX_VALUE.
	 */
	public int maxLength() default Integer.MAX_VALUE;

	/**
	 * Set the regular expression to check. Default value is empty (ignored empty).
	 */
	public String match() default "";
}
