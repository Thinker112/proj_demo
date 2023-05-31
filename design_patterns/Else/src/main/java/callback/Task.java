package callback;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public abstract class Task {

    final void executeWith(Callback callback){
        execute();
        Optional.ofNullable(callback).ifPresent(Callback::call);
    }

    public abstract void execute();
}
