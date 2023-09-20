package br.com.makersweb.makersnews.common.exception.handler;

import br.com.makersweb.makersnews.common.model.Problem;
import br.com.makersweb.makersnews.common.model.ProblemType;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.OffsetDateTime;

/**
 * @author aaristides
 */
@RestController
@RequestMapping("/error")
public class JsonErrorController extends AbstractErrorController {

    public JsonErrorController(final ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @GetMapping
    public ResponseEntity<Problem> error(final HttpServletRequest request) {
        HttpStatus status = this.getStatus(request);
        ProblemType problemType = ProblemType.of(status).orElse(ProblemType.SYSTEM_ERROR);
        Problem problem = Problem.builder()
                .timestamp(OffsetDateTime.now())
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle()).build();
        return ResponseEntity.status(status).body(problem);
    }


}
