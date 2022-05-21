package net.nanquanyuhao.handler;

import net.nanquanyuhao.bean.Student;
import net.nanquanyuhao.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * 处理器类
 */
@Component
public class StudentHandler {

    @Autowired
    private StudentRepository repository;

    /**
     * 查询所有
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> findAllHandler(ServerRequest request) {
        return ServerResponse
                .ok()   // 响应码200
                .contentType(MediaType.APPLICATION_JSON)
                .body(repository.findAll(), Student.class);
    }
}
