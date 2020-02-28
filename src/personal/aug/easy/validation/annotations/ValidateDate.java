package personal.aug.easy.validation.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(FIELD)
public @interface ValidateDate {

	public String min() default "01/01/1970";
	public String max() default "01/01/2200";
	public String pattern() default "dd/MM/yyyy";
}
