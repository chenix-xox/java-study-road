package service.Impl;

import service.UserService;

/**
 * @author Chenix
 * @create 2024-02-05 0:57
 */
public class UserServiceImpl implements UserService {

    @Override
    public void add() {
        System.out.println("add");
    }

    @Override
    public void select() {
        System.out.println("select");
    }

    @Override
    public void update() {
        System.out.println("update");
    }

    @Override
    public void delete() {
        System.out.println("delete");
    }
}
