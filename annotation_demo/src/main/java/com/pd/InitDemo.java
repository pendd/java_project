/**
 * @author: pd
 * @date: 2021-02-16 上午1:55
 */
public class InitDemo {

  @InitMethod
  public void init() {
    System.out.println("init....");
  }

  @InitMethod
  public void test01() {
    System.out.println("test01....");
  }

  public void test02() {
    System.out.println("test02...");
  }
}
