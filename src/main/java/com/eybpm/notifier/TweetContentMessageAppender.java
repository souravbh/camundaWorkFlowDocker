package com.eybpm.notifier;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("appendMessage")
public class TweetContentMessageAppender implements JavaDelegate {

	private static Logger logger = LoggerFactory.getLogger(TweetContentMessageAppender.class);

	public void execute(DelegateExecution execution) throws Exception {
		String content = (String) execution.getVariable("content");

		logger.debug("Tweet content appender");
		execution.setVariable("content", content + " me sailing with BPM!!");
	}

}
