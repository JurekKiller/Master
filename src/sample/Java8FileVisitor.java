package sample;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

class Java8FileVisitor extends AbstractFileVisitor {

    public Java8FileVisitor(FileVisitable visitable) {
        super(visitable);
    }

    @Override
    public List<String> walk(String rootPath) throws IOException {
       return Files.walk(Paths.get(rootPath))
                .filter(path -> path.toFile().isFile())
                .map(this::visit)
                .collect(Collectors.toList());
    }

    public String visit(Path path) {
       return visitable.visit(path.toString());
    }
}