package sample;

import java.io.IOException;
import java.util.List;

public abstract class AbstractFileVisitor {
    protected final FileVisitable visitable;

    public AbstractFileVisitor(FileVisitable visitable) {
        this.visitable = visitable;
    }

    public abstract List<String> walk(String rootPath) throws IOException;

}