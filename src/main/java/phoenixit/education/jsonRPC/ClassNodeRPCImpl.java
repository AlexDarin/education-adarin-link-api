package phoenixit.education.jsonRPC;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import phoenixit.education.models.ClassNodeDto;
import phoenixit.education.services.ClassNodeService;

import java.util.List;

@AutoJsonRpcServiceImpl
@Component
@RequiredArgsConstructor
public class ClassNodeRPCImpl implements ClassNodeRPC {

    private final ClassNodeService classNodeService;

    @Override
    public ClassNodeDto create(ClassNodeDto classNode) {
        return classNodeService.createClassNode(classNode);
    }

    @Override
    public ClassNodeDto get(Long classNodeId) {
        return classNodeService.getClassNodeById(classNodeId);
    }

    @Override
    public List<ClassNodeDto> findAll() {
        return classNodeService.findAll();
    }

    @Override
    public ClassNodeDto findByMongoId(String mongoId) {
        return classNodeService.findByMongoId(mongoId);
    }

    @Override
    public ClassNodeDto update(ClassNodeDto classNode) {
        return classNodeService.updateClassNode(classNode);
    }

    @Override
    public void delete(Long classNodeId) {
        classNodeService.deleteClassNode(classNodeId);
    }
}
