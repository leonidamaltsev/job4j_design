package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Map<User, Object> map = new HashMap<>(16);
        Calendar birthday = Calendar.getInstance();
        User user1 = new User("Alex", 1, birthday);
        int hashCode1 = user1.hashCode();
        int hash1 = hashCode1 ^ (hashCode1 >>> 16);
        int bucket1 = hash1 & 15;
        int index1 = ((16 - 1) & user1.hashCode());
        User user2 = new User("Alex", 1, birthday);
        int hashCode2 = user2.hashCode();
        int hash2 = hashCode2 ^ (hashCode1 >>> 16);
        int bucket2 = hash2 & 15;
        int index2 = ((16 - 1) & user2.hashCode());
        map.put(user1, new Object());
        map.put(user2, new Object());
        System.out.printf("user1 - хэш-код: %s , хэш: %s, backet: %s , user1 index: %s",
                hashCode1, hash1, bucket1, index1);
        System.out.printf("user2 - хэш-код: %s , хэш: %s, backet: %s, user2 index: %s",
                hashCode2, hash2, bucket2, index2);
        System.out.println(user1.equals(user2));
    }
    /*
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        User user = (User) obj;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }*/

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }


}
