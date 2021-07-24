/**   
 * 
 * @Title: DemoThread09.java 
 * @Prject: DemoThread
 * @Package: com.liang.demo 
 * @author: yin.hl
 * @date: 2017年3月20日 下午10:55:51 
 * @version: V1.0   
 */
package current.thread.mimaxueyuan.demo.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile无法保证原子性
 * @author: Kevin
 * @官网: 	www.mimaxueyuan.com
 * @Q Q群:	660567408
 * @Email:	mimaxueyuan@163.com
 * [每天进步一点点、人生带来大改变...]
 * [本代码对应视频地址:http://study.163.com/course/introduction/1004176043.htm]
 */
public class DemoThread14 implements Runnable{
	
	public static /*volatile*/ AtomicInteger sum = new AtomicInteger(0);
	
	public static void add(){
		System.out.println(Thread.currentThread().getName()+"初始sum="+sum);
		for(int i=0;i<10000;i++){
			//sum++;
			sum.addAndGet(1);
		}
		System.out.println(Thread.currentThread().getName()+"计算后sum="+sum);
	}
	
	@Override
	public void run() {
		add();
	}
	
	public static void main(String[] args) {
		//如果volatile具有原子性,那么10个线程并发调用，最终结果应该为100000
		ExecutorService es = Executors.newFixedThreadPool(10);
		for(int i=0;i<10;i++){
			es.submit(new DemoThread14());
		}
		es.shutdown();
		while(true){
			if(es.isTerminated()){
				System.out.println("sum最终="+sum);
				if(sum.get()==100000){
					System.out.println(sum+"=ok");
				}else{
					System.out.println(sum+"=no");
				}
				break;
			}
		}
	}
}