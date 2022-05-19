package net.nanquanyuhao.bean;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Data
// 指定在MongoDB中生成的表名
@Document(collection = "t_student")
public class Student {

    @Id
    // MonogoDB表中的id一般为字符串类型
    private String id;

    // Hibernate Validator 参数校验
    @NotBlank(message = "姓名不能为空")
    private String name;

    @Range(min = 10, max = 80, message = "年龄必须在{min}-{max}范围内")
    private int age;
}
