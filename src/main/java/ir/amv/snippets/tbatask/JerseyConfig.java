package ir.amv.snippets.tbatask;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;
import java.util.List;

/**
 * Jersey configuration.
 */
@Component
@ApplicationPath("/rest")
public class JerseyConfig extends ResourceConfig
{
    @Autowired
    public JerseyConfig(List<IBaseRestService> restServices)
    {
        restServices.forEach(this::register);
    }
}