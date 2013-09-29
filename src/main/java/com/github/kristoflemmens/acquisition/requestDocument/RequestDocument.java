package com.github.kristoflemmens.acquisition.requestDocument;

import java.util.List;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;

public class RequestDocument {
    private final String data;

    public RequestDocument(String data) {
        this.data = data;
    }

    public String organismCode() {
        return "organismCode";
    }

    public String companyId() {
        return "companyId";
    }

    public String data() {
        return data;
    }

    public Set<String> inssOfChildren() {
        return newHashSet("inssOfChild1", "inssOfChild2");
    }

    public String fileOwnerInss() {
        return "fileOwnerInss";
    }

    public Set<String> inssOfActors() {
        return newHashSet("inssOfActor1", "inssOfActor2");
    }

    public List<Actor> actors() {
        return newArrayList();
    }
}
