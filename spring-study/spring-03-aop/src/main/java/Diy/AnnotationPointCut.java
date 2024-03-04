package Diy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @author Chenix
 * @create 2024-02-06 1:02
 */

@Aspect
public class AnnotationPointCut {
    @Before("execution(* service.Impl.UserServiceImpl.*(..))")
    public void before() {
        System.out.println("before");
    }

    @After("execution(* service.Impl.UserServiceImpl.*(..))")
    public void after() {
        System.out.println("after");
    }

    @Around("execution(* service.Impl.UserServiceImpl.*(..))")
    public Object around(ProceedingJoinPoint pjp) {
        System.out.println("around里的参数：" +  pjp);
        System.out.println("around前");
        try {
            pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("around后");
        return null;
    }

}
