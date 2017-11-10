package com.eybpm.notifier.taskListeners;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.extension.reactor.bus.CamundaEventBus;
import org.camunda.bpm.extension.reactor.bus.CamundaSelector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CamundaSelector(event = TaskListener.EVENTNAME_CREATE)
public class CamundaTaskListenerCreate implements TaskListener {
	
	private static Logger logger = LoggerFactory.getLogger(CamundaTaskListenerCreate.class);
	
	 public CamundaTaskListenerCreate(CamundaEventBus eventBus) {
		    eventBus.register(this);
		  }
	 
	@Override
	public void notify(DelegateTask delegateTask) {
		System.out.println("Task::" + delegateTask.getAssignee());
		logger.debug("Inside create task:: assignee:: "+ delegateTask.getAssignee());
		
	}

}
