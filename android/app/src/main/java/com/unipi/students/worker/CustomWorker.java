package io.ionic.starter;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.ForegroundUpdater;
import androidx.work.ProgressUpdater;
import androidx.work.WorkerFactory;
import androidx.work.WorkerParameters;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.Executor;

public class CustomWorker extends WorkerParameters {

  public MockWorkerParameters() {
    super(null, null); // Pass null for Context and WorkerParameters
  }

  /**
   * @param id
   * @param inputData
   * @param tags
   * @param runtimeExtras
   * @param runAttemptCount
   * @param generation
   * @param backgroundExecutor
   * @param workTaskExecutor
   * @param workerFactory
   * @param progressUpdater
   * @param foregroundUpdater
   */
  public CustomWorker(@NonNull UUID id, @NonNull Data inputData, @NonNull Collection<String> tags, @NonNull RuntimeExtras runtimeExtras, int runAttemptCount, int generation, @NonNull Executor backgroundExecutor, @NonNull TaskExecutor workTaskExecutor, @NonNull WorkerFactory workerFactory, @NonNull ProgressUpdater progressUpdater, @NonNull ForegroundUpdater foregroundUpdater) {
    super(id, inputData, tags, runtimeExtras, runAttemptCount, generation, backgroundExecutor, workTaskExecutor, workerFactory, progressUpdater, foregroundUpdater);
  }

  // Add any additional mock functionality or parameters as needed
}

