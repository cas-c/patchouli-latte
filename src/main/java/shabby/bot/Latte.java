package shabby.bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Latte {
    public static void main(String[] arguments) throws Exception
    {
        JDA api = new JDABuilder("").build();
        api.addEventListener(new Messages());
    }
}
