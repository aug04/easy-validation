package personal.aug.easy.validation.process;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import personal.aug.easy.validation.annotations.ValidateByteArray;
import personal.aug.easy.validation.annotations.ValidateDate;
import personal.aug.easy.validation.annotations.ValidateNotNull;
import personal.aug.easy.validation.annotations.ValidateNumber;
import personal.aug.easy.validation.annotations.ValidateString;
import personal.aug.easy.validation.supporttypes.Status;

public class Processing {

	public Map<String, Object> processing(Class<?> clazz, Object instance) throws Exception {
		Map<String, Object> result = null;
		
		if (clazz != null 
				&& instance != null 
				&& (instance.getClass() == clazz)) {
			result = new HashMap<>();
			
			Field[] fields = clazz.getDeclaredFields();
			if (fields.length > 0) {
				for (Field field : fields) {
					field.setAccessible(true);
					Object value = field.get(instance);
					
					Annotation[] annotations = field.getAnnotations();
					Map<String, Object> resultField = new HashMap<>();
					for (Annotation ann : annotations) {
						if (ann instanceof ValidateNotNull) {
							String val = !String.valueOf(value).equals("null") ? value.toString() : null;
							resultField.put(ValidateNotNull.class.getSimpleName(), 
									handleValidateNotNull((ValidateNotNull) ann, val));
						}
						
						if (ann instanceof ValidateString) {
							String val = !String.valueOf(value).equals("null") ? value.toString() : "";
							resultField.put(ValidateString.class.getSimpleName(), 
									handleValidateString((ValidateString) ann, val));
						}
						
						if (ann instanceof ValidateByteArray) {
							byte[] val = !String.valueOf(value).equals("null") ? (byte[]) value : null;
							resultField.put(ValidateByteArray.class.getSimpleName(), 
									handleValidateByteArray((ValidateByteArray) ann, val));
						}
						
						if (ann instanceof ValidateNumber) {
							Double val = !String.valueOf(value).equals("null") ? Double.valueOf(value.toString()) : null;
							resultField.put(ValidateNumber.class.getSimpleName(), 
									handleValidateNumber((ValidateNumber) ann, val));
						}
						
						if (ann instanceof ValidateDate) {
							String val = !String.valueOf(value).equals("null") ? value.toString() : null;
							resultField.put(ValidateDate.class.getSimpleName(), 
									handleValidateDate((ValidateDate) ann, val));
						}
					}
					
					result.put(field.getName(), resultField);
				}
			}
			boolean allAreValid = true;
			for (Object obj : result.values()) {
				if (obj instanceof Map<?, ?>) {
					for (Object obj2 : ((Map<?, ?>) obj).values()) {
						if (obj2 instanceof ProcessResult) {
							if (!((ProcessResult) obj2).isValid()) {
								allAreValid = false;
								break;
							}
						}
					}
					
					if (!allAreValid)
						break;
				}
			}
			
			result.put(Status.ALL_ARE_VALID.getCode(), allAreValid);
		}
		
		return result;
	}
	
	private ProcessResult handleValidateNotNull(ValidateNotNull ann, String value) {
		ProcessResult result = new ProcessResult();
		
		if (value == null) {
			result.getStatusList().add(Status.IS_NULL_OR_EMPTY);
		} else {
			result.getStatusList().add(Status.IS_VALID);
		}
		
		return result;
	}
	
	private ProcessResult handleValidateByteArray(ValidateByteArray ann, byte[] value) {
		ProcessResult result = new ProcessResult();
		
		if (value == null || value.length == 0) {
			result.getStatusList().add(Status.IS_NULL_OR_EMPTY);
		} else {
			result.getStatusList().add(Status.IS_VALID);
		}
		
		return result;
	}
	
	private ProcessResult handleValidateNumber(ValidateNumber ann, Double value) {
		ProcessResult result = new ProcessResult();
		
		if (value == null) {
			result.getStatusList().add(Status.IS_NULL_OR_EMPTY);
		} else {
			result.getStatusList().add(Status.IS_VALID);
		}
		
		if (ann.min() > 0d) {
			if (value != null && value < ann.min()) {
				result.getStatusList().add(Status.NUMBER_OUT_OF_RANGE);
			} else {
				result.getStatusList().add(Status.IS_VALID);
			}
		}
		
		if (ann.max() > 0d) {
			if (value != null && value > ann.max()) {
				result.getStatusList().add(Status.NUMBER_OUT_OF_RANGE);
			} else {
				result.getStatusList().add(Status.IS_VALID);
			}
		}
		
		return result;
	}
	
	private ProcessResult handleValidateDate(ValidateDate ann, String value) {
		ProcessResult result = new ProcessResult();
		
		if (value == null) {
			result.getStatusList().add(Status.IS_NULL_OR_EMPTY);
		} else {
			result.getStatusList().add(Status.IS_VALID);
		}
		
		if (!isNullOrEmpty(value) && !isNullOrEmpty(ann.pattern())) {
			SimpleDateFormat sdf = null;
			try {
				sdf = new SimpleDateFormat(ann.pattern());
				sdf.format(new Date());
			} catch (IllegalArgumentException e) {
				result.getStatusList().add(Status.INVALID_DATE_PATTERN);
			}
			
			if (sdf != null) {
				result.getStatusList().add(Status.IS_VALID);
				Date date = null;
				try {
					date = sdf.parse(value);
				} catch (ParseException e) {
					result.getStatusList().add(Status.INVALID_DATE_VALUE);
				}
				
				if (date != null) {
					result.getStatusList().add(Status.IS_VALID);
					// if has only minimum date
					if (!isNullOrEmpty(ann.min()) && isNullOrEmpty(ann.max())) {
						// TODO: check min, max date
						Date minDate = null;
						try {
							minDate = sdf.parse(ann.min());
							if (date.before(minDate))
								result.getStatusList().add(Status.DATE_OUT_OF_RANGE);
							else
								result.getStatusList().add(Status.IS_VALID);
						} catch (ParseException e) {
							result.getStatusList().add(Status.INVALID_MIN_DATE_VALUE);
						}
					} else if (isNullOrEmpty(ann.min()) && !isNullOrEmpty(ann.max())) {
						// if has only maximum date
						Date maxDate = null;
						try {
							maxDate = sdf.parse(ann.max());
							if (date.after(maxDate))
								result.getStatusList().add(Status.DATE_OUT_OF_RANGE);
							else
								result.getStatusList().add(Status.IS_VALID);
						} catch (ParseException e) {
							result.getStatusList().add(Status.INVALID_MAX_DATE_VALUE);
						}
					} else {
						// has both minimum & maximum date
						Date minDate = null;
						try {
							minDate = sdf.parse(ann.min());
						} catch (ParseException e) {
							result.getStatusList().add(Status.INVALID_MIN_DATE_VALUE);
						}
						
						Date maxDate = null;
						try {
							maxDate = sdf.parse(ann.max());
						} catch (ParseException e) {
							result.getStatusList().add(Status.INVALID_MAX_DATE_VALUE);
						}
						
						if (minDate != null && maxDate != null) {
							if (minDate.after(maxDate) || minDate.equals(maxDate)) {
								result.getStatusList().add(Status.INVALID_MIN_DATE_VALUE);
							} else if (date.before(minDate) || date.after(maxDate)) {
								result.getStatusList().add(Status.DATE_OUT_OF_RANGE);
							}
						}
					}
				}
			}
		} else {  // pattern is null
			result.getStatusList().add(Status.DATE_PATTERN_IS_NULL);
		}
		
		return result;
	}
	
	private ProcessResult handleValidateString(ValidateString ann, String value) {
		ProcessResult result = new ProcessResult();
		
		if (value == null || value.isEmpty()) {
			result.getStatusList().add(Status.IS_NULL_OR_EMPTY);
		} else {
			result.getStatusList().add(Status.IS_VALID);
		}
		
		if (ann.minLength() > 0) {
			if (value.length() < ann.minLength()) {
				result.getStatusList().add(Status.INVALID_LENGTH);
			} else {
				result.getStatusList().add(Status.IS_VALID);
			}
		}
		
		if (ann.maxLength() > 0) {
			if (value.length() > ann.maxLength()) {
				result.getStatusList().add(Status.INVALID_LENGTH);
			} else {
				result.getStatusList().add(Status.IS_VALID);
			}
		}
		
		if (ann.match() != null && !ann.match().isEmpty()) {
			if (!value.matches(ann.match())) {
				result.getStatusList().add(Status.NOT_MATCH);
			} else {
				result.getStatusList().add(Status.IS_VALID);
			}
		}
		
		return result;
	}
	
	private boolean isNullOrEmpty(String s) {
		return s == null || s.isEmpty();
	}

}
