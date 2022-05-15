package net.nanquanyuhao;

import java.util.*;
import java.util.stream.Collectors;

public class CollectorTest {

    public static void main(String[] args) {

        Student s1 = new Student("aa", 10, 1);
        Student s2 = new Student("bb", 20, 2);
        Student s3 = new Student("cc", 10, 3);
        List<Student> list = Arrays.asList(s1, s2, s3);
        System.out.println(list);

        // 用 collect() 进行转化的话仅可转化为 List/Set 类型
        // collect()：接收一个 Collector 实例，将流中元素收集成另外一个数据结构，结果：[10, 20, 10]
        List<Integer> ageList = list.stream()
                .map(Student::getAge)
                .collect(Collectors.toList());
        System.out.println(ageList);

        //转成 set，结果：[20, 10]
        Set<Integer> ageSet = list.stream()
                .map(Student::getAge)
                .collect(Collectors.toSet());
        System.out.println(ageSet);

        // 转成 map，注: key 不能相同，否则报错，结果：{cc=10, bb=20, aa=10}
        Map<String, Integer> studentMap = list.stream().collect(Collectors.toMap(Student::getName, Student::getAge));
        System.out.println(studentMap);

        // 字符串分隔符连接，结果：(aa,bb,cc)
        String joinName = list.stream().map(Student::getName).collect(Collectors.joining(",", "(", ")"));
        System.out.println(joinName);

        // 聚合操作
        // 1. 学生总数，结果：3
        Long count = list.stream().collect(Collectors.counting());
        System.out.println(count);

        // 2. 最大最小年龄，结果：20 & 10
        Integer maxAge = list.stream().map(Student::getAge).collect(Collectors.maxBy(Integer::compare)).get();
        Integer minAge = list.stream().map(Student::getAge).collect(Collectors.minBy(Integer::compare)).get();
        System.out.println("最大年龄：" + maxAge + "，最小年龄：" + minAge);

        // 3. 所有人的年龄，结果：40
        Integer sumAge = list.stream().collect(Collectors.summingInt(Student::getAge));
        System.out.println(sumAge);

        // 4. 平均年龄，结果：13.333333333333334
        Double averageAge = list.stream().collect(Collectors.averagingDouble(Student::getAge));
        System.out.println(averageAge);

        // 5. 带上以上所有方法
        DoubleSummaryStatistics statistics = list.stream().collect(Collectors.summarizingDouble(Student::getAge));
        System.out.println("count:" + statistics.getCount() + ",max:" + statistics.getMax() +
                ",sum:" + statistics.getSum() + ",average:" + statistics.getAverage());

        // 6. 分组
        Map<Integer, List<Student>> ageMap = list.stream().collect(Collectors.groupingBy(Student::getAge));
        System.out.println(ageMap);

        // 7. 多重分组，先根据类型分再根据年龄分
        Map<Integer, Map<Integer, List<Student>>> typeAgeMap =
                list.stream().collect(Collectors.groupingBy(Student::getType, Collectors.groupingBy(Student::getAge)));
        System.out.println(typeAgeMap);

        // 分区
        // 9. 分成两部分，一部分大于 10 岁，一部分小于等于 10岁
        Map<Boolean, List<Student>> partMap = list.stream().collect(Collectors.partitioningBy(v -> v.getAge() > 10));
        System.out.println(partMap);

        // 10. 规约，结果：40
        Integer allAge = list.stream().map(Student::getAge).collect(Collectors.reducing(Integer::sum)).get();
        System.out.println(allAge);

        // 11. 作用为，过滤掉同岁的人。实际是后来发现冲突的对象不再纳入而已：
        // 将 list 存为 TreeSet，并使用 Comparator.comparing 指定比较的元素为某个属性，将不重复的 TreeSet 集合转回 List
        List<Student> studentList = list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(
                () -> new TreeSet<>(Comparator.comparing(o -> o.getAge()))), ArrayList::new));
        System.out.println(studentList);
    }
}
