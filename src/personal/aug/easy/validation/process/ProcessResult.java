package personal.aug.easy.validation.process;

import java.util.ArrayList;
import java.util.List;

import personal.aug.easy.validation.supporttypes.Status;

public class ProcessResult {

	private List<Status> statusList;

	public ProcessResult() {
		statusList = new ArrayList<>(0);
	}

	public ProcessResult(List<Status> statusList) {
		this.statusList = statusList;
	}

	public List<Status> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<Status> statusList) {
		this.statusList = statusList;
	}
	
	public boolean isValid() {
		boolean result = true;
		for (Status s : statusList) {
			if (s != Status.IS_VALID) {
				result = false;
				break;
			}
		}
		
		return result;
	}
}
