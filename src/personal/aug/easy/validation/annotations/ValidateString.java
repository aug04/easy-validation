package personal.aug.easy.validation.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(FIELD)
public @interface ValidateString {

	public int minLength() default 0;
	public int maxLength() default Integer.MAX_VALUE;
	public String match() default "";
}
