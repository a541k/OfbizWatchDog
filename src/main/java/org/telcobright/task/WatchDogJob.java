package org.telcobright.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.telcobright.helper.OfbizController;

import java.beans.JavaBean;

public class WatchDogJob implements Job {
    OfbizController ofbizController = new OfbizController();
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try{
            if(!ofbizController.isOfbizRunning()){
                ofbizController.startOfbiz();
            }else{
                ofbizController.stopOfbiz();
                ofbizController.startOfbiz();
            }

        }catch (Exception e){
            System.err.println("Error executing");
        }


    }
}
