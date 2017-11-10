package com.eybpm.notifier;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Use if you don't want to access Twitter but just to do some sysout
 * @author ruecker
 */
@Service("tweetAdapter")
public class TweetContentOfflineDelegate implements JavaDelegate {

	 private static Logger logger = LoggerFactory.getLogger(TweetContentOfflineDelegate.class);
	 
  public void execute(DelegateExecution execution) throws Exception {
	  
    String content = (String) execution.getVariable("content");
    String comment = (String)execution.getVariable("Comments");
    //System.out.println("comment posted by approver"+comment);
    //System.out.println("NOW WE WOULD TWITTER: '" + content + "'");
    
    logger.debug("comment posted by approver::"+ comment + ":: NOW WE WOULD tweet: '" + content + "'");
    
  }

}
