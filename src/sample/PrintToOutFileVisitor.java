package sample;

import java.util.stream.Collectors;

public class PrintToOutFileVisitor implements FileVisitable {

    @Override
    public String visit(String filePath) {
            String a = checkIsImage(filePath);
            return a;

    }


    private String checkIsImage(String filepath){
       String a  = Dictionary.extensions.stream().filter(filepath::endsWith).collect(Collectors.joining());
        return a;
    }
}
