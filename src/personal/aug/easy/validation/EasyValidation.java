package personal.aug.easy.validation;

import java.util.Map;

import personal.aug.easy.validation.process.Processing;

public abstract class EasyValidation {
	
	private Processing processing;
	
	public EasyValidation() {
		processing = new Processing();
	}
	
	public Map<String, Object> validate() throws Exception {
		return processing.processing(this.getClass(), this);
	}
}
