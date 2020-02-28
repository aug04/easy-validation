package personal.aug.easy.validation.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Validate not null for any field.
 * 
 * @author HungDM
 *
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface ValidateNotNull {

}
