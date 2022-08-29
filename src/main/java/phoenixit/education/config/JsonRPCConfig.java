package phoenixit.education.config;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImplExporter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonRPCConfig {

    @Bean
    public static AutoJsonRpcServiceImplExporter autoJsonRpcServiceImplExporter() {
        return new AutoJsonRpcServiceImplExporter();
    }
}
