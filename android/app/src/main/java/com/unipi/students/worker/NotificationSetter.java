package io.ionic.starter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkerParameters;
import com.getcapacitor.BridgeActivity;
import java.util.concurrent.TimeUnit;

public class NotificationSetter extends BridgeActivity {


  @Override
  public void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    Context context = null;
    WorkManager workManager = WorkManager.getInstance(context);
    @SuppressLint("RestrictedApi") WorkerParameters parameters = new WorkerParameters();
    createWorkManager(workManager);
    createPushNotificationChannel(context);

  }

  private void createWorkManager(WorkManager workmanager){

    Constraints constraint = new Constraints.Builder()
      .setRequiredNetworkType(NetworkType.CONNECTED)
      .build();

    PeriodicWorkRequest workRequest =
      new PeriodicWorkRequest.Builder(NotificationWorker.class, 15, TimeUnit.MINUTES)
        .addTag("PUSH_NOTIFICATION")
        .setConstraints(constraint)
        .build();

    workmanager.enqueueUniquePeriodicWork(
      "PUSH_NOTIFICATION",
      ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE,
      workRequest);
  }

  private void createPushNotificationChannel(Context context){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
      int importance = NotificationManager.IMPORTANCE_HIGH;
      NotificationChannel notificationChannel = new NotificationChannel("ReminderChannel", "Reminder", importance);
      NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
      notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
      if (notificationManager != null) {
        notificationManager.createNotificationChannel(notificationChannel);
      }
    }
  }
}
