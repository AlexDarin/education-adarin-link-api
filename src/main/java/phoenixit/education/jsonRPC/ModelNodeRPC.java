package phoenixit.education.jsonRPC;

import com.googlecode.jsonrpc4j.JsonRpcError;
import com.googlecode.jsonrpc4j.JsonRpcErrors;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import org.springframework.stereotype.Component;
import phoenixit.education.exceptions.AlreadyConnectedException;
import phoenixit.education.models.ModelNodeDto;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@JsonRpcService("/model-node")
@Component
public interface ModelNodeRPC {

    ModelNodeDto create(@JsonRpcParam("modelNode") final ModelNodeDto modelNode);

    @JsonRpcErrors({
            @JsonRpcError(exception = EntityNotFoundException.class, code = 404)
    })
    ModelNodeDto get(@JsonRpcParam("id") final Long modelNodeId);

    List<ModelNodeDto> findAll();

    @JsonRpcErrors({
            @JsonRpcError(exception = EntityNotFoundException.class, code = 404)
    })
    ModelNodeDto findByMongoId(@JsonRpcParam(value = "mongoId") String mongoId);

    @JsonRpcErrors({
            @JsonRpcError(exception = EntityNotFoundException.class, code = 404),
            @JsonRpcError(exception = IllegalArgumentException.class, code = 500)
    })
    ModelNodeDto update(@JsonRpcParam("modelNode") final ModelNodeDto modelNode);

    @JsonRpcErrors({
            @JsonRpcError(exception = EntityNotFoundException.class, code = 404)
    })
    void delete(@JsonRpcParam("id") final Long modelNodeId);

    @JsonRpcErrors({
            @JsonRpcError(exception = AlreadyConnectedException.class, code = 500),
            @JsonRpcError(exception = EntityNotFoundException.class, code = 404)
    })
    void linkToClass(@JsonRpcParam(value = "modelNodeId") Long modelNodeId, @JsonRpcParam(value = "classNodeId") Long classNodeId);
}
