package shabby.bot;

import net.dv8tion.jda.api.entities.MessageChannel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static java.sql.DriverManager.getConnection;

public class MessageUtil {
    public static void sendMessage(MessageChannel channel, String text) {
        channel.sendMessage(text).queue();
        try {
            Class.forName("org.h2.Driver");
            Connection conn = getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE LATTE SET COUNT = COUNT + 1 WHERE TYPE = 'sent'");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
