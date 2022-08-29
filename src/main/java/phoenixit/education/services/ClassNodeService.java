package phoenixit.education.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import phoenixit.education.components.ClassNodeMapper;
import phoenixit.education.models.ClassNodeDto;
import phoenixit.education.repositories.ClassNodeRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassNodeService {

    private final ClassNodeRepository classNodeRepository;

    private final ClassNodeMapper classNodeMapper;

    @Transactional
    public ClassNodeDto createClassNode(final ClassNodeDto classNodedto) {
        return classNodeMapper.toDto(classNodeRepository.save(classNodeMapper.toEntity(classNodedto)));
    }

    @Transactional(readOnly = true)
    public ClassNodeDto getClassNodeById(final Long classNodeId) {
        return classNodeRepository.findById(classNodeId)
                .map(classNodeMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("No class node with id = " + classNodeId + " found"));
    }

    @Transactional(readOnly = true)
    public List<ClassNodeDto> findAll() {
        return classNodeRepository.findAll().stream()
                .map(classNodeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ClassNodeDto findByMongoId(String mongoId) {
        return classNodeRepository.findByMongoId(mongoId)
                .map(classNodeMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("No class node with mongoId = " + mongoId + " found"));
    }


    @Transactional
    public ClassNodeDto updateClassNode(final ClassNodeDto classNodeDto) {
        classNodeRepository.findById(classNodeDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("No class node with id = " + classNodeDto.getId() + " found"));

        return classNodeMapper.toDto(classNodeRepository.save(classNodeMapper.toEntity(classNodeDto)));
    }

    @Transactional
    public void deleteClassNode(final Long classNodeId) {
        classNodeRepository.findById(classNodeId)
                .orElseThrow(() -> new EntityNotFoundException("No class node with id = " + classNodeId + " found"));

        classNodeRepository.deleteById(classNodeId);
    }
}
