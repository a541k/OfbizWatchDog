package org.telcobright;

import org.quartz.SchedulerException;
import org.telcobright.quartzConfig.QuartzScheduler;

public class Main {
    public static void main(String[] args) throws SchedulerException {
        QuartzScheduler scheduler = new QuartzScheduler();
        scheduler.scheduleJob();
    }
}