package thinking.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {

	public static void main(String[] args) {
		System.out.println("start");
		FutureTask<String> futureTask = new FutureTask<String>(
				new Callable<String>() {

					public String call() throws Exception {
						int i = 10;
						while ((i++) <= 100) {
							System.out.println(i);
						}
						return "over";
					}
				});

		ExecutorService service = Executors.newCachedThreadPool();
		service.execute(futureTask);
		try {
			System.out.println(futureTask.get());
		} catch (Exception e) {
		}
		System.out.println("start");
		service.shutdownNow();
	}

}
