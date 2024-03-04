import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.Impl.UserServiceImpl;
import service.UserService;

/**
 * @author Chenix
 * @create 2024-02-05 1:53
 */
public class test {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("springContext.xml");
        UserService userService = context.getBean("userService", UserService.class);
        userService.add();
    }
}
