package com.eybpm.notifier.taskListeners;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.extension.reactor.bus.CamundaEventBus;
import org.camunda.bpm.extension.reactor.bus.CamundaSelector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CamundaSelector(event = TaskListener.EVENTNAME_COMPLETE)
public class CamundaTaskListenerComplete implements TaskListener {

	 private static Logger logger = LoggerFactory.getLogger(CamundaTaskListenerComplete.class);
	 
	 public CamundaTaskListenerComplete(CamundaEventBus eventBus) {
		    eventBus.register(this);
		  }
	 
	@Override
	public void notify(DelegateTask delegateTask) {
		logger.debug("Inside complete event task:::"+ delegateTask.getTaskDefinitionKey());
	}
}
