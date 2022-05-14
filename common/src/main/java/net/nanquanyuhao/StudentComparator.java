package net.nanquanyuhao;

import java.util.Comparator;

/**
 * Student 比较器
 */
public class StudentComparator implements Comparator<Student> {

    /**
     * 比较规则：年龄大者 Student 对象大
     *
     * @param o1
     * @param o2
     * @return
     */
    @Override
    public int compare(Student o1, Student o2) {

        if (o1.getAge() > o2.getAge()) {
            return 1;
        } else if (o1.getAge() < o2.getAge()) {
            return -1;
        }
        return 0;
    }
}
