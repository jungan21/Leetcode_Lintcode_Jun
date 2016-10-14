package ImmutableQueue;

public class ImmutableQueueTest {

	public static void main(String[] args) {
		
		ImmutableQueue<Integer> data = new ImmutableQueue<Integer>();
		ImmutableQueue<Integer> reverse = new ImmutableQueue<Integer>();
		int num =0;
		int max = 1000000;
		long current = System.currentTimeMillis();
		
		while(++num <= max){
			int value = (int)(Math.random()*100);
			data = data.enQueue(value);
		}	
		System.out.println("Time for enqueue "+max+" randome integer "+(System.currentTimeMillis()-current)+" miliseconds");
		
		while(--num>0){
			data =data.deQueue();
		}
		
		System.out.println("Time for dequeue "+max+" randome integer "+(System.currentTimeMillis()-current)+" miliseconds");
		
	}
}
