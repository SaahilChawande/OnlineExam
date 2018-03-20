import java.util.concurrent.*;
public class ConsoleInput {
	String readLine() throws InterruptedException
	{
		ExecutorService ex=Executors.newSingleThreadExecutor();
		String input=null;
		try
		{
			Future<String> result=ex.submit(new ConsoleInputReadTask());
			try
			{
				input=result.get(30000, TimeUnit.MILLISECONDS);
			}catch(ExecutionException e)
			{
				e.getCause().printStackTrace();
			}catch(TimeoutException e){
			result.cancel(true);
			}
		}finally{
			ex.shutdownNow();
		}
		return input;
	}
}
