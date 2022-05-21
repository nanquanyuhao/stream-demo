package net.nanquanyuhao.util;

import net.nanquanyuhao.bean.Student;
import net.nanquanyuhao.exception.StudentException;
import org.springframework.util.StringUtils;

import java.util.stream.Stream;

/**
 * 参数校验工具类
 */
public class ValidatorUtil {

    // 无效姓名列表
    private static final String[] INVALIDE_NAMES = {"admin", "administrator"};
    // 年龄范围上下限
    private static final int[] INVALIDE_AGES = {15, 80};

    // 校验姓名
    public static void validateStudent(Student student) {

        String name = student.getName();
        int age = student.getAge();

        // 对Student的name进行为空的校验
        if (!StringUtils.hasText(name)) {
            throw new StudentException("姓名不能为空", "name", "为 null 或空串");
        }

        Stream.of(INVALIDE_NAMES)   // Stream，元素为"admin", "administrator"
                // 若name与invlideNmae相等，则通过过滤，保留在Stream中
                .filter(invalideName -> name.equalsIgnoreCase(invalideName))
                .findAny()   // Optional
                // 只要 Optional 中封装的对象不空，则说明当前 name 为无效姓名
                .ifPresent(invalideName -> {
                    throw new StudentException("使用了非法姓名", "name", invalideName);
                });

        // 对Student的age进行年龄范围的校验
        if (age < INVALIDE_AGES[0] || age > INVALIDE_AGES[1]) {
            String msg = "年龄超出了（" + INVALIDE_AGES[0] + "," + INVALIDE_AGES[1] + "）范围";
            throw new StudentException(msg, "age", age);
        }

    }
}
