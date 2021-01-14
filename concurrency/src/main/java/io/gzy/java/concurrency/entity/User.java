package io.gzy.java.concurrency.entity;

/**
 * description: User
 * date: 2021/1/7 9:36 上午
 * author: guizhenyu
 */
public class User {
    private String name;

    private String addr;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public User(String name, String addr) {
        this.name = name;
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", addr='" + addr + '\'' +
                '}';
    }
}
