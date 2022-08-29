package phoenixit.education.repositories;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import phoenixit.education.models.ModelNode;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModelNodeRepository extends Neo4jRepository<ModelNode, Long> {

    Optional<ModelNode> findByMongoId(String mongoId);

    @Query("match (m: model) where id(m) = $modelNodeId " +
           "match (c: class) where id(c) = $classNodeId " +
           "create (m) - [r:MODEL_TO_CLASS] -> (c)")
    void linkModelToClass(Long modelNodeId, Long classNodeId);

    @Query("match (m:model) - [r:MODEL_TO_CLASS] -> (c:class) " +
            "return (m)")
    List<ModelNode> getLinkedModels();
}
