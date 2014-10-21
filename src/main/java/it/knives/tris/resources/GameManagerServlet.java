package it.knives.tris.resources;

import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import it.knives.tris.model.Game;
import it.knives.tris.model.Move;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by Knives on 14/10/14.
 */
@Path("game")
public class GameManagerServlet {

    private final Logger log = Logger.getLogger(GameManagerServlet.class.getName());

    private Game createGame(String gameKey) {
        ChannelService channelService = ChannelServiceFactory.getChannelService();

        Game currGame = new Game();
        currGame.setToken(channelService.createChannel(gameKey));
        currGame.setGameKey(gameKey);

        return currGame;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Game game(@QueryParam("u")String username) {
        return createGame(new Date().getTime() + "");
    }

    @GET
    @Path("{g}")
    @Produces(MediaType.APPLICATION_JSON)
    public Game joinGame(@QueryParam("u")String username, @PathParam("g")String gameKey) {
        return createGame(gameKey);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void move(@QueryParam("g")String gameKey, Move currentMove) {
        ChannelService channelService = ChannelServiceFactory.getChannelService();
        log.info(gameKey);
        channelService.sendMessage(new ChannelMessage(gameKey, "TEST"));
    }

}
