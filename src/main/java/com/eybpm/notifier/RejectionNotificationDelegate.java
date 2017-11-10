package com.eybpm.notifier;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Rejection is just done via a sysout.
 */

@Service("emailAdapter")
public class RejectionNotificationDelegate implements JavaDelegate {

	private static Logger logger = LoggerFactory.getLogger(RejectionNotificationDelegate.class);
	
  public void execute(DelegateExecution execution) throws Exception {
    String content = (String) execution.getVariable("content");
    String comments = (String) execution.getVariable("comments");

    logger.debug("Unfortunately your tweet has been rejected.\n\n"
           + "Original content: " + content + "\n\n"
           + "Comment: " + comments + "\n\n"
           + "Sorry, please try with better content the next time :-)");
    
    System.out.println("Hi!\n\n"
           + "Unfortunately your tweet has been rejected.\n\n"
           + "Original content: " + content + "\n\n"
           + "Comment: " + comments + "\n\n"
           + "Sorry, please try with better content the next time :-)");
  }

}
