package org.telcobright.quartzConfig;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.telcobright.task.WatchDogJob;

public class QuartzScheduler {

    public static void scheduleJob() throws SchedulerException {
        // Create a Quartz scheduler
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        // Start the scheduler
        scheduler.start();

        // Define the job and tie it to our HelloJob class
        JobDetail job = JobBuilder.newJob(WatchDogJob.class)
                .withIdentity("job1", "group1")
                .build();

        // Define a trigger that will fire every 5 seconds
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(60)
                        .withRepeatCount(1))
                .build();

        // Tell quartz to schedule the job using our trigger
        scheduler.scheduleJob(job, trigger);
    }


}
