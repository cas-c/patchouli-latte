package shabby.bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Latte {
    public static void main(String[] arguments) throws Exception
    {
        JDA api = new JDABuilder("MTY4NTQ1Nzc1ODAwOTQyNTky.XZAjvw.FY5f23SmK428959rcvROjF036qE").build();
        api.addEventListener(new Messages());
    }
}
