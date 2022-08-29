package phoenixit.education.jsonRPC;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import phoenixit.education.models.ModelNodeDto;
import phoenixit.education.services.ModelNodeService;

import java.util.List;

@AutoJsonRpcServiceImpl
@Component
@RequiredArgsConstructor
public class ModelNodeRPCImpl implements ModelNodeRPC {

    private final ModelNodeService modelNodeService;

    @Override
    public ModelNodeDto create(ModelNodeDto modelNode) {
        return modelNodeService.createModelNode(modelNode);
    }

    @Override
    public ModelNodeDto get(Long modelNodeId) {
        return modelNodeService.getModelNodeById(modelNodeId);
    }

    @Override
    public List<ModelNodeDto> findAll() {
        return modelNodeService.findAll();
    }

    @Override
    public ModelNodeDto findByMongoId(String mongoId) {
        return modelNodeService.findByMongoId(mongoId);
    }

    @Override
    public ModelNodeDto update(ModelNodeDto modelNode) {
        return modelNodeService.updateModelNode(modelNode);
    }

    @Override
    public void delete(Long modelNodeId) {
        modelNodeService.deleteModelNode(modelNodeId);
    }

    @Override
    public void linkToClass(Long modelNodeId, Long classNodeId) {
        modelNodeService.linkToClass(modelNodeId, classNodeId);
    }
}
