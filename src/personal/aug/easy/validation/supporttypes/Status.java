package personal.aug.easy.validation.supporttypes;

public enum Status {

	ALL_ARE_VALID("all_are_valid"),
	IS_VALID("is_valid"),
	IS_NULL_OR_EMPTY("is_null_or_empty"),
	NOT_MATCH("not_match"),
	INVALID_LENGTH("invalid_length"),
	INVALID_DATE_PATTERN("invalid_date_pattern"),
	DATE_PATTERN_IS_NULL("date_pattern_is_null"),
	INVALID_DATE_VALUE("invalid_date_value"),
	INVALID_MIN_DATE_VALUE("invalid_min_date_value"),
	INVALID_MAX_DATE_VALUE("invalid_max_date_value"),
	DATE_OUT_OF_RANGE("date_out_of_range"),
	NUMBER_OUT_OF_RANGE("number_out_of_range");
	
	private String code;
	
	private Status(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static Status lookup(String code) {
		for (Status s : Status.values()) {
			if (s.getCode().equals(code)) {
				return s;
			}
		}
		
		return null;
	}
}
