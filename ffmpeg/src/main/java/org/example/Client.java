package org.example;

import java.util.concurrent.*;

public class Client {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        ScheduledFuture<Long> schedule = null;
        ScheduledFuture<Long> schedule2 = null;

        schedule = executorService.schedule(System::currentTimeMillis, 1, TimeUnit.SECONDS);
        schedule2 = executorService.schedule(System::currentTimeMillis, 1, TimeUnit.SECONDS);


        while (!(schedule.isDone() && schedule2.isDone())) {
            System.out.println(
                    String.format(
                            "schedule is %s and schedule2 is %s",
                            schedule.isDone() ? "done" : "not done",
                            schedule2.isDone() ? "done" : "not done"
                    )
            );
            Thread.sleep(300);
        }

        Long aLong = schedule.get();
        Long aLong2 = schedule2.get();
        System.out.println("aLong2 = " + aLong2);
        System.out.println("aLong = " + aLong);
        executorService.shutdown();


    }
}
