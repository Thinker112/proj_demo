package callback;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleTask extends Task{

    @Override
    public void execute() {
        log.info("Perform some important activity and after call the callback method.");
    }
}
