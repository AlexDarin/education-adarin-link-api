package phoenixit.education.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@AllArgsConstructor
@Getter
@Node("class")
@NoArgsConstructor
@Setter
public class ClassNode {

    @Id
    @GeneratedValue
    private Long id;

    private String mongoId;

    private String title;
}
