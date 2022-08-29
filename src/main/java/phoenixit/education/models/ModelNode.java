package phoenixit.education.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@AllArgsConstructor
@Getter
@Node("model")
@NoArgsConstructor
@Setter
public class ModelNode {

    @Id
    @GeneratedValue
    private Long id;

    @Property("mongoId")
    private String mongoId;

    @Property("title")
    private String title;
}
