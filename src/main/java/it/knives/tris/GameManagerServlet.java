package it.knives.tris;

import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Knives on 14/10/14.
 */
public class GameManagerServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String gameKey = req.getParameter("g");
        String user = req.getParameter("u");

        if (gameKey == null) {
            gameKey = new Date().getTime() + "";
        }

        ChannelService channelService = ChannelServiceFactory.getChannelService();
        String token = channelService.createChannel(user + gameKey);


        String index = "{token:"+ token +", gameKey: " + gameKey + "}";

        resp.setContentType("text/json");
        resp.getWriter().write(index);
    }
}
