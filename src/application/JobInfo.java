package application;

import java.util.ArrayList;
import java.util.UUID;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class JobInfo {

	private String jobId;
	private String scriptName;
	private ArrayList<JobParam> params;
	private String desc;
	private JobStatus status;
	ObservableList<String> messages;
	ObservableList<JobInfo> parentList;
	
	public enum JobStatus{RUNNING, IDLE, ERROR, DONE};
	
	public class JobParam {
		String name;
		String value;
		
		public JobParam(String name, String value) {
			this.name = name;
			this.value = value;
		}
	}
	
	public JobInfo() {
		params = new ArrayList<JobParam>();
		messages = FXCollections.observableArrayList();
		jobId = UUID.randomUUID().toString();
	}
	public void setParentList(ObservableList<JobInfo> parent) {
		this.parentList = parent;
	}
	
	public String getJobId(){
		return jobId;
	}
	public void setDesc(String desc) {
		this.desc = desc;
		notifyParent();
	}
	public String getDesc() {
		return desc;
	}
	
	public void setScriptName(String script) {
		this.scriptName = script;
		notifyParent();
	}
	public String getScriptName() {
		return scriptName;
	}
	
	public void setStatus(JobStatus status){
		this.status = status;
		notifyParent();
	}
	public JobStatus getStatus() {
		return status;
	}
	public void addParam(String name, String value) {
		JobParam jp = new JobParam(name, value);
		params.add(jp);
		notifyParent();
	}
	
	public void addMessage(String msg) {
		messages.add(msg);
		notifyParent();
	}
	
	
	// convert to a list of strings
	public ObservableList<String> getParamsAsStrings() {
		ObservableList<String> list = FXCollections.observableArrayList();
		for (JobParam p : params) {
			list.add(p.name + ": " + p.value);
		}
		return list;
	}
	public ObservableList<String> getMessages() {
		return messages;
	}
	
	// make the observable list aware that something has changed
	// there is probably a nicer way to do this with a java bean wrapper around JobInfo
	// but we'll save that for the real GUI
	private void notifyParent() {
		if (parentList == null) {
			return;
		}
		int idx = parentList.indexOf(this);
		if (idx != -1) {
			parentList.set(idx, this);
		}
	}
	
	
}
