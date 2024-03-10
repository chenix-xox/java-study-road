package com.xxx.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Chenix
 * @create 2024-03-10 21:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "人")
public class Person {

    @Schema(description = "名字")
    private String name;
    @Schema(description = "年龄")
    private Integer age;
}
