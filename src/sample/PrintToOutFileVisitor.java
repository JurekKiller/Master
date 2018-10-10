package sample;

public class PrintToOutFileVisitor implements FileVisitable {

    @Override
    public String visit(String filePath) {
            String a = checkIsImage(filePath);
            return a;

    }


    private String checkIsImage(String filepath){
        return  Dictionary.extensions.stream().filter(filepath::endsWith).toString();

    }
}
