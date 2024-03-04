import com.xxx.pojo.Hello;
import com.xxx.service.XxxService;
import com.xxx.service.XxxServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Chenix
 * @create 2024-01-30 22:13
 */
public class Test {

    public static void main(String[] args) {
        // 获取Spring的上下文对象
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        // 对象已经在Spring中管理了，要用直接取出即可
        Hello hello = context.getBean("hello", Hello.class);

        System.out.println(hello.getStr());

        XxxServiceImpl xxxServiceImpl = (XxxServiceImpl) context.getBean("xxxServiceImpl");
        System.out.println(xxxServiceImpl.getStr());
    }
}
