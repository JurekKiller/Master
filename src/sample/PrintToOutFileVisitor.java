package sample;

public class PrintToOutFileVisitor implements FileVisitable {

    @Override
    public void visit(String filePath) {

        if(checkIsImage(filePath))
            System.out.println(filePath);
    }

    private boolean checkIsImage(String filepath){
           return   Dictionary.extensions.stream().anyMatch(filepath::endsWith);
    }
}
