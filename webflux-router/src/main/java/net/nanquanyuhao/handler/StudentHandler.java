package net.nanquanyuhao.handler;

import net.nanquanyuhao.bean.Student;
import net.nanquanyuhao.repository.StudentRepository;
import net.nanquanyuhao.util.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
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

    /**
     * 添加数据
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> saveHandler(ServerRequest request) {
        // 从请求中获取到要添加的数据
        Mono<Student> studentMono = request.bodyToMono(Student.class);

        studentMono = studentMono.map(stu -> {
            // 验证Student对象
            ValidatorUtil.validateStudent(stu);
            return stu;
        });

        return ServerResponse
                .ok()   // 响应码200
                .contentType(MediaType.APPLICATION_JSON)
                .body(repository.saveAll(studentMono), Student.class);
    }

    /**
     * 数据删除
     *
     * @param serverRequest
     * @return
     */
    public Mono<ServerResponse> delHandler(ServerRequest serverRequest) {
        // 从请求中获取到要添加的数据
        String id = serverRequest.pathVariable("id");

        return repository.findById(id)
                .flatMap(stu -> repository.deleteById(id)
                        // build() 是构造器模式最终操作，返回 Mono<ServerResponse> 对象
                        .then(ServerResponse.ok().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    /**
     * 有状态修改
     *
     * @param serverRequest
     * @return
     */
    public Mono<ServerResponse> updateHandler(ServerRequest serverRequest) {
        // 从请求中获取到要添加的数据
        String id = serverRequest.pathVariable("id");
        Mono<Student> studentMono = serverRequest.bodyToMono(Student.class);

        // 将 id 封装到了 Mono 的元素中
        Mono<Student> studentMonoId = studentMono.map(stu -> {
            // 验证Student对象
            ValidatorUtil.validateStudent(stu);
            stu.setId(id);
            return stu;
        });

        return repository.findById(id)
                // findById() 返回值类型为 Mono<Student>，故 flatMap() 内 Function 入参类型必须为 Student 即入参 stu
                .flatMap(stu -> {
                    Flux<Student> studentFlux = repository.saveAll(studentMonoId);
                    // Function 出参数必须为 Mono<>，此处需要包装为 Mono<ServerResponse>，满足最终返回值要求
                    return ServerResponse
                            .ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(studentFlux, Student.class);
                })
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    /**
     * 有状态查询
     *
     * @param serverRequest
     * @return
     */
    public Mono<ServerResponse> findByIdHandler(ServerRequest serverRequest) {
        // 从请求中获取到要添加的数据
        String id = serverRequest.pathVariable("id");

        return repository.findById(id)
                .flatMap(stu -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        // 减少一次查询，进行 Student 对象的包装
                        .body(Mono.just(stu), Student.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    /**
     * 根据年龄范围查询（一次性返回数据）
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> findByAgeHandler(ServerRequest request) {
        // 从请求中获取到要添加的数据
        String belowStr = request.pathVariable("below");
        String topStr = request.pathVariable("top");
        Integer below = Integer.valueOf(belowStr);
        Integer top = Integer.valueOf(topStr);

        return ServerResponse
                .ok()   // 响应码200
                .contentType(MediaType.APPLICATION_JSON)
                .body(repository.findByAgeBetween(below, top), Student.class);
    }

    /**
     * 根据年龄范围查询(以SSE形式返回数据)
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> findByAgeSseHandler(ServerRequest request) {
        // 从请求中获取到要添加的数据
        String belowStr = request.pathVariable("below");
        String topStr = request.pathVariable("top");
        Integer below = Integer.valueOf(belowStr);
        Integer top = Integer.valueOf(topStr);

        return ServerResponse
                .ok()   // 响应码200
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(repository.findByAgeBetween(below, top), Student.class);
    }
}
