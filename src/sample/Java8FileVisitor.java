package sample;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class Java8FileVisitor extends AbstractFileVisitor {

    public Java8FileVisitor(FileVisitable visitable) {
        super(visitable);
    }

    @Override
    public void walk(String rootPath) throws IOException {
        Files.walk(Paths.get(rootPath))
                .filter(path -> path.toFile().isFile())
                .forEach(this::visit);
    }

    public void visit(Path path) {
        visitable.visit(path.toString());
    }
}