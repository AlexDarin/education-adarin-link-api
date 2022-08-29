package phoenixit.education.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import phoenixit.education.components.ModelNodeMapper;
import phoenixit.education.exceptions.AlreadyConnectedException;
import phoenixit.education.models.ModelNode;
import phoenixit.education.models.ModelNodeDto;
import phoenixit.education.repositories.ModelNodeRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ModelNodeService {

    private final ModelNodeRepository modelNodeRepository;

    private final ModelNodeMapper modelNodeMapper;

    private final ClassNodeService classNodeService;

    @Transactional
    public ModelNodeDto createModelNode(final ModelNodeDto modelNodeDto) {
        return modelNodeMapper.toDto(modelNodeRepository.save(modelNodeMapper.toEntity(modelNodeDto)));
    }

    @Transactional(readOnly = true)
    public ModelNodeDto getModelNodeById(final Long modelNodeId) {
        return modelNodeRepository.findById(modelNodeId)
                .map(modelNodeMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("No model node with id = " + modelNodeId + " found"));
    }

    @Transactional(readOnly = true)
    public List<ModelNodeDto> findAll() {
        return modelNodeRepository.findAll().stream()
                .map(modelNodeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ModelNodeDto findByMongoId(String mongoId) {
        return modelNodeRepository.findByMongoId(mongoId)
                .map(modelNodeMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("No class node with mongoId = " + mongoId + " found"));
    }

    @Transactional
    public ModelNodeDto updateModelNode(final ModelNodeDto modelNodeDto) {
        modelNodeRepository.findById(modelNodeDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("No model node with id = " + modelNodeDto.getId() + " found"));

        return modelNodeMapper.toDto(modelNodeRepository.save(modelNodeMapper.toEntity(modelNodeDto)));
    }

    @Transactional
    public void deleteModelNode(final Long modelNodeId) {
        modelNodeRepository.findById(modelNodeId)
                .orElseThrow(() -> new EntityNotFoundException("No model node with id = " + modelNodeId + " found"));

        modelNodeRepository.deleteById(modelNodeId);
    }

    @Transactional
    public void linkToClass(Long modelNodeId, Long classNodeId) {
        classNodeService.getClassNodeById(classNodeId);
        modelNodeRepository.findById(modelNodeId)
                .orElseThrow(() -> new EntityNotFoundException("No model node with id = " + modelNodeId + " found"));

        List<ModelNode> nodes = modelNodeRepository.getLinkedModels();
        nodes.forEach(modelNode -> {
                    if (modelNode.getId().equals(modelNodeId)) {
                        throw new AlreadyConnectedException("Model node with id = " + modelNodeId + " already connected");
                    }
                });

        modelNodeRepository.linkModelToClass(modelNodeId, classNodeId);
    }
}
