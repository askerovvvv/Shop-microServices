package own.micro.products;

import java.io.IOException;

public class Runner {

    public static void main(String[] args) throws IOException {
        Parser parser = new Parser();
        parser.parseItems("https://new.technodom.kg/");
    }
}
