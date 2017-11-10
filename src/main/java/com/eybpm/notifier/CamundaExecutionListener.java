package com.eybpm.notifier;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.extension.reactor.bus.CamundaEventBus;
import org.camunda.bpm.extension.reactor.bus.CamundaSelector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CamundaSelector( event = ExecutionListener.EVENTNAME_START)
public class CamundaExecutionListener implements ExecutionListener{

	private static Logger logger = LoggerFactory.getLogger(CamundaExecutionListener.class);
	
	 public CamundaExecutionListener(CamundaEventBus eventBus) {
		    eventBus.register(this);
		  }
	 
	@Override
	public void notify(DelegateExecution execution) throws Exception {
		 System.out.println("Execution::"+ execution.getProcessDefinitionId());
		 logger.debug("Execution:: "+ execution.getProcessDefinitionId());
	}


}
