package hfy.action.init;

public class InitSpring {
	
	/**
	 * 测试该类的实例化时间：在启动项目的时候被装载
	 */
	public InitSpring(){
		System.out.println("11111111111111111111111111111111111111");
	}
	
	/**
	 * 测试当前的方法是否能在Action中被调用
	 * @author John
	 * 2017年10月14日 上午9:15:25
	 */
	public void test(){
		System.out.println("Testing!");
	}
}
