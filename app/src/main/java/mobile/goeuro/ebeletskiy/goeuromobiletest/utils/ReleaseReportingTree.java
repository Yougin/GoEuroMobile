package mobile.goeuro.ebeletskiy.goeuromobiletest.utils;

import timber.log.Timber;

/**
 * The one to be used to commit to a reporting tool and not native Android logging system for
 * release builds
 */
public class ReleaseReportingTree extends Timber.HollowTree {

  @Override public void i(String message, Object... args) {
    // TODO e.g., Crashlytics.log(String.format(message, args));
  }

  @Override public void i(Throwable t, String message, Object... args) {
    i(message, args); // Just add to the log.
  }

  @Override public void e(String message, Object... args) {
    i("ERROR: " + message, args); // Just add to the log.
  }

  @Override public void e(Throwable t, String message, Object... args) {
    e(message, args);
    // TODO e.g., Crashlytics.logException(t);
  }
}
