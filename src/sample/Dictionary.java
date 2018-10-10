package sample;

import com.google.common.collect.ImmutableList;


public class Dictionary {
    static  String dictionaryList ;

    ImmutableList<String> immutable = ImmutableList.of(dictionaryList);

    public ImmutableList<String> getImmutable() {
        return immutable;
    }

    public void setImmutable(ImmutableList<String> immutable) {
        this.immutable = immutable;
    }
}