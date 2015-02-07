package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import application.JobInfo.JobStatus;

// invent some data for this prototype
public class AppDataHelper {

	public static ObservableList<JobInfo> createJobs() {
		
		ObservableList<JobInfo> list = FXCollections.observableArrayList();
		// job 1
		JobInfo job1 = new JobInfo();
		job1.setParentList(list);
		job1.setScriptName("DTBook to ZedAI");
		job1.setDesc("Convert DTBook XML to ZedAI XML");
		job1.setStatus(JobStatus.DONE);
		job1.addParam("Input File", "Source.xml");
		job1.addParam("Assert Valid", "true");
		job1.addParam("Language", "en");
		job1.addMessage("This is a message about DTBook to ZedAI");
		job1.addMessage("The job is done");
		job1.addMessage("End of messages");
		
		// job 2
		JobInfo job2 = new JobInfo();
		job2.setParentList(list);
		job2.setScriptName("DAISY 2.02 to EPUB 3");
		job2.setDesc("Convert DAISY 2.02 to EPUB 3");
		job2.setStatus(JobStatus.ERROR);
		job2.addParam("Input File", "Source.xml");
		job2.addMessage("This is a message about DAISY 2.02 to EPUB 3");
		job2.addMessage("The job reports an error");
		job2.addMessage("The real pipeline would tell you more information");
		job2.addMessage("End of messages");
		
		// job 3
		JobInfo job3 = new JobInfo();
		job3.setParentList(list);
		job3.setScriptName("DTBook Validator");
		job3.setDesc("Validate a DTBook document");
		job3.setStatus(JobStatus.RUNNING);
		job3.addParam("Input File", "Source.xml");
		job3.addMessage("This is a message about DTBook Validator");
		job3.addMessage("The job is running");
		job3.addMessage("When you choose Trigger Gui Change #1, the job status will change.");
		job3.addMessage("End of messages");
		
		// job 4
		JobInfo job4 = new JobInfo();
		job4.setParentList(list);
		job4.setScriptName("DAISY 3 to EPUB 3");
		job4.setDesc("Convert DAISY 3 to EPUB 3");
		job4.setStatus(JobStatus.IDLE);
		job4.addParam("Input File", "Source.xml");
		job4.addMessage("This is a message about DAISY 3 to EPUB 3");
		job4.addMessage("The job is idle");
		job4.addMessage("When you choose Trigger Gui Change #2, the job status will change.");
		job4.addMessage("End of messages");
		
		list.add(job1);
		list.add(job2);
		list.add(job3);
		list.add(job4);
		
		return list;
	}
	
}
