package phoenixit.education.jsonRPC;

import com.googlecode.jsonrpc4j.JsonRpcError;
import com.googlecode.jsonrpc4j.JsonRpcErrors;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import org.springframework.stereotype.Component;
import phoenixit.education.models.ClassNodeDto;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@JsonRpcService("/class-node")
@Component
public interface ClassNodeRPC {

    ClassNodeDto create(@JsonRpcParam("classNode") final ClassNodeDto classNode);

    @JsonRpcErrors({
            @JsonRpcError(exception = EntityNotFoundException.class, code = 404)
    })
    ClassNodeDto get(@JsonRpcParam("id") final Long classNodeId);

    List<ClassNodeDto> findAll();

    @JsonRpcErrors({
            @JsonRpcError(exception = EntityNotFoundException.class, code = 404)
    })
    ClassNodeDto findByMongoId(@JsonRpcParam(value = "mongoId") String mongoId);

    @JsonRpcErrors({
            @JsonRpcError(exception = EntityNotFoundException.class, code = 404),
            @JsonRpcError(exception = IllegalArgumentException.class, code = 500)
    })
    ClassNodeDto update(@JsonRpcParam("classNode") final ClassNodeDto classNode);

    @JsonRpcErrors({
            @JsonRpcError(exception = EntityNotFoundException.class, code = 404)
    })
    void delete(@JsonRpcParam("id") final Long classNodeId);
}
