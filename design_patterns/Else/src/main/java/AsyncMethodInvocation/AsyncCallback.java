package AsyncMethodInvocation;

import java.util.Optional;

/**
 * AsyncCallback（可以在任务完成时被执行）
 * @param <T>
 */
public interface AsyncCallback<T> {
    void onComplete(T value, Optional<Exception> ex);
}
