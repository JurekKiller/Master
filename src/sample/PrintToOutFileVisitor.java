package sample;

public class PrintToOutFileVisitor implements FileVisitable {

    @Override
    public void visit(String filePath) {
        checkIsImage(filePath);
        if(filePath.endsWith("jpg"))
            System.out.println(filePath);
    }

    private void checkIsImage(String filepath){

    }


}
