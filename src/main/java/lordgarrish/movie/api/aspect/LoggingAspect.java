package lordgarrish.movie.api.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import lordgarrish.movie.api.model.Movie;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@Slf4j
@Aspect
@Component
public class LoggingAspect {
    @Autowired
    private ObjectMapper mapper;

    @Before("@annotation(lordgarrish.movie.api.annotation.LogReadingAttempt)")
    public void logGetMethodsAdvice(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        GetMapping mapping = signature.getMethod().getAnnotation(GetMapping.class);

        Map<String, Object> parameters = getParameters(joinPoint);

        try {
            log.info("READING ATTEMPT: Method: GET, Path: /movie-api/{}, Param(s): {}, Arguments: {} ",
                    mapping.path(), mapping.params(), mapper.writeValueAsString(parameters));
        } catch (JsonProcessingException e) {
            log.error("Error while converting", e);
        }
    }

    @AfterReturning(value ="@annotation(lordgarrish.movie.api.annotation.LogMovieAdding)", returning = "movies")
    public void logAddMoviesMethodsAdvice(JoinPoint joinPoint, List<Movie> movies) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        PostMapping mapping = signature.getMethod().getAnnotation(PostMapping.class);

        log.info("ADDING ATTEMPT SUCCESSFUL: Method: POST, Path: /movie-api/{}, Param(s): {}.",
                mapping.path(), mapping.params());
    }

    private Map<String, Object> getParameters(JoinPoint joinPoint) {
        CodeSignature signature = (CodeSignature) joinPoint.getSignature();
        HashMap<String, Object> map = new HashMap<>();
        String[] parameterNames = signature.getParameterNames();

        for (int i = 0; i < parameterNames.length; i++) {
            if(parameterNames[i].equals("pageable")) continue;
            map.put(parameterNames[i], joinPoint.getArgs()[i]);
        }

        return map;
    }
}
