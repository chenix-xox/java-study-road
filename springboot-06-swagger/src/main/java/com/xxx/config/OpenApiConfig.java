package com.xxx.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

/**
 * @author Chenix
 * @create 2024-03-08 21:47
 */

@Configuration
public class OpenApiConfig {

    public String getResourcePathContent(String path) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource(path);
        assert url != null;
        Scanner scanner = new Scanner(url.openStream());
        scanner.useDelimiter("\\A");
        return scanner.next();
    }

    ObjectMapper mapper = new ObjectMapper();
    Info info = mapper.readValue(getResourcePathContent("example/OpenApiInfoExampleJson.json"), Info.class);

    public OpenApiConfig() throws IOException {
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(apiInfo())
                .externalDocs(externalDocumentation());
    }


    /**
     * @return 额外文档链接及文字显示
     */
    private ExternalDocumentation externalDocumentation() {
        return new ExternalDocumentation()
                .description("项目API文档")
                .url("/");
    }

    /**
     * @return 接口文档的信息
     */
    private Info apiInfo() {
//        return new Info()
//                .title("测试 title")
//                .description("SpringBoot3 集成 Swagger3")
//                .version("v1");
        return info;
    }
}
