package ir.amv.snippets.tbatask;

import ir.amv.snippets.tbatask.board.IBoardRestService;
import ir.amv.snippets.tbatask.board.impl.BoardRestServiceImpl;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/rest")
public class JerseyConfig extends ResourceConfig
{
    public JerseyConfig()
    {
        register(BoardRestServiceImpl.class);
    }
}