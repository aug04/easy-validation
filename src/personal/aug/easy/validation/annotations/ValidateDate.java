package personal.aug.easy.validation.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Validate date range of the field with String type.
 * 
 * @author AUG
 *
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface ValidateDate {

	/**
	 * Set minimum date value, default value is "01/01/1970", value have to match
	 * with pattern, default pattern value is "dd/MM/yyyy".
	 */
	public String min() default "01/01/1970";

	/**
	 * Set maximum date value, default value is "01/01/3000", value have to match
	 * with pattern, default pattern value is "dd/MM/yyyy".
	 */
	public String max() default "01/01/3000";

	/**
	 * The pattern to check date value string match with, default value is
	 * "dd/MM/yyyy".
	 */
	public String pattern() default "dd/MM/yyyy";
}
