package personal.aug.easy.validation.supporttypes;

import java.io.Serializable;

public class Tween<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private T start;
	private T end;
	
	public Tween(T start, T end) {
		this.start = start;
		this.end = end;
	}

	public T getStart() {
		return start;
	}

	public void setStart(T start) {
		this.start = start;
	}

	public T getEnd() {
		return end;
	}

	public void setEnd(T end) {
		this.end = end;
	}
}
