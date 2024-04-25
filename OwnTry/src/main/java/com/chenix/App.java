package com.chenix;

import com.chenix.model.Child;
import com.chenix.model.Parent;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Parent parent = new Parent();
        parent.setName("父类名称");
        parent.setAge("45");
        Child child = parent;
        child.setChildName("你好");
        System.out.println(child);
    }
}
