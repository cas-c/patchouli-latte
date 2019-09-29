package shabby.bot;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.ShutdownEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;

import static java.sql.DriverManager.*;

public class Messages extends ListenerAdapter {
    private Connection conn;
    private int count = 0;

    @Override
    public void onReady(@Nonnull ReadyEvent event) {

        super.onReady(event);
        try {
            Class.forName("org.h2.Driver");
            conn = getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onShutdown(@Nonnull ShutdownEvent event) {
        super.onShutdown(event);
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        if (event.getAuthor().isBot()) return;
        Message message = event.getMessage();
        String content = message.getContentRaw();

        MessageChannel channel = event.getChannel();
        if (content.equals("!ping"))
        {
            MessageUtil.sendMessage(channel, "pong");
            return;
        }

        if (content.equals("!pong"))
        {
            MessageUtil.sendMessage(channel, "ping");
            return;
        }
        if (content.equals("!count")) {
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("UPDATE LATTE SET COUNT = COUNT + 1 WHERE TYPE = 'counter'");
                ResultSet queryResult = stmt.executeQuery("SELECT COUNT FROM LATTE WHERE TYPE = 'counter'");
                ResultSet q2 = stmt.executeQuery("SELECT TYPE, COUNT FROM LATTE");

                while(q2.next()) {
                    String msg = q2.getString("type") + ": " + q2.getString("count");
                    MessageUtil.sendMessage(channel, msg);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return;
        }

        // in home patchouli channel
        if (event.getChannel().getId().equals("627708827499757588")) {
            String authorId = event.getAuthor().getId();

            // borfus
            if (authorId.equals("162687336549711872")) {
                MessageUtil.sendMessage(channel, "borfus.");
            } else if (authorId.equals("191733254909329408")) {
                MessageUtil.sendMessage(channel, "hi muffin ^__^");
            } else {
                MessageUtil.sendMessage(channel, event.getAuthor().getAsTag());
            }
        }
    }
}
