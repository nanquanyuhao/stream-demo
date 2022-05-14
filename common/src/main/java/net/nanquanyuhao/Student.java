package net.nanquanyuhao;

/**
 * 自定义测试学生类
 */
public class Student {

    private String name;
    private Integer age;
    private Integer type;

    public Student() {

    }

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Student(String name, Integer age, Integer type) {
        this.name = name;
        this.age = age;
        this.type = type;
    }

    /**
     * 静态方法
     *
     * @param hours
     */
    public static void sleeping(int hours) {
        System.out.println("学生每天需要睡眠：" + hours + "小时");
    }

    /**
     * 实例方法
     *
     * @param minutes
     * @return
     */
    public String play(int minutes) {
        // public String play(Student this, int minutes) {
        return name + "已经玩儿了" + minutes + "分钟了。";
    }

    /**
     * 实例方法
     *
     * @param course
     */
    public void study(String course) {
        // public void study(Student this, String course) {
        System.out.println(name + "正在学习" + course);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", type=" + type +
                '}';
    }

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
