package com.guyang.sources.io;

import org.junit.Test;

import java.io.*;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 序列化和反序列化例子
 * 序列化使用ObjectOutputStream.writeObject()方法，反序列化使用ObjectInputStream.readObject()方法
 * @date 2018/3/20 9:50$
 */
public class SerializeDemo implements Serializable {
    //不设置serialVersionUID可能会出现反序列化失败。
    private static final long serialVersionUID = 9209287450906110143L;

    /**
     * 序列化
     *
     * @throws Exception
     */
    @Test
    public void serializeTest() throws Exception {
        Person p1 = new Person("顾洋", 30, 55f);
        FileOutputStream fileOutputStream = new FileOutputStream(new File("a.txt"));
        ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
        oos.writeObject(p1);

        oos.flush();
        oos.close();
    }

    @Test
    public void deserializeTest() throws Exception {
        FileInputStream fis = new FileInputStream(new File("a.txt"));
        ObjectInputStream objectInputStream = new ObjectInputStream(fis);
        Person o = (Person) objectInputStream.readObject();
        System.out.println(o);
    }


    class Person implements Serializable {
        private static final long serialVersionUID = 3729944475003110200L;

        private String name;
        transient private Integer age;//transient 反序列化时不取值，取默认值
        private Float weight;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Float getWeight() {
            return weight;
        }

        public void setWeight(Float weight) {
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", weight=" + weight +
                    '}';
        }

        public Person() {
        }

        public Person(String name, Integer age, Float weight) {
            this.name = name;
            this.age = age;
            this.weight = weight;
        }
    }

}
