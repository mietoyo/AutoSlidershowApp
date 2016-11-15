package jp.techacademy.mie.toyo.autoslidershowapp;

public abstract class TimerTask {

    void run() {
        if (System.currentTimeMillis() - scheduledExecutionTime() >=
                MAX_TARDINESS)
            return; // Too late; skip this execution.
        // Perform the task
    }
}
