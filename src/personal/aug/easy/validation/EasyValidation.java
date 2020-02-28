package personal.aug.easy.validation;

import java.util.Map;

import personal.aug.easy.validation.process.Processing;

/**
 * Just extend this class and call validate() function to validate an object.
 * 
 * @author AUG
 *
 */
public abstract class EasyValidation {

	private Processing processing;

	public EasyValidation() {
		processing = new Processing();
	}

	/**
	 * Validate an instance object marked with Validate annotation.
	 * 
	 * @return a Map holder the validation values...
	 * @throws Exception
	 * @author AUG
	 */
	public Map<String, Object> validate() throws Exception {
		return processing.processing(this.getClass(), this);
	}
}
