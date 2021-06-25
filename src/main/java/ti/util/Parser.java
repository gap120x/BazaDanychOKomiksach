package ti.util;

public class Parser {

    public static String parse(String input, String pattern)
    {

        System.out.print("input:" +input + "\n");
        String output = "main";
        String[] strony = pattern.split(";");
        if (input==null) input="main";


        for (String matching: strony)
        {
            if (input.equals(matching)) output = input;
        }
        return output;
    }

}
