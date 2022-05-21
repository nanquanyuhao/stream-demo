package net.nanquanyuhao.router;

import net.nanquanyuhao.handler.StudentHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * JavaConfig 类
 */
@Configuration
public class StudentRouter {

    /**
     * 用于将请求路由到指定的处理器方法
     *
     * @param handler
     * @return
     */
    @Bean
    RouterFunction<ServerResponse> customRouter(StudentHandler handler) {

        return RouterFunctions
                // 相当于 @RequestMapping("/student")
                .nest(RequestPredicates.path("/student"),
                        RouterFunctions.route(RequestPredicates.GET("/all"), handler::findAllHandler)
                                .andRoute(RequestPredicates.POST("/save")
                                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                                        handler::saveHandler)
                                .andRoute(RequestPredicates.DELETE("/del/{id}"), handler::delHandler)
                                .andRoute(RequestPredicates.PUT("/update/{id}")
                                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                                        handler::updateHandler)
                                .andRoute(RequestPredicates.GET("/find/{id}"), handler::findByIdHandler)
                                .andRoute(RequestPredicates.GET("/age/{below}/{top}"), handler::findByAgeHandler)
                                .andRoute(RequestPredicates.GET("/sse/age/{below}/{top}"), handler::findByAgeSseHandler)
                );
    }
}
