package sample;

import java.io.IOException;

public abstract class AbstractFileVisitor {
    protected final FileVisitable visitable;

    public AbstractFileVisitor(FileVisitable visitable) {
        this.visitable = visitable;
    }

    public abstract void walk(String rootPath) throws IOException;

}