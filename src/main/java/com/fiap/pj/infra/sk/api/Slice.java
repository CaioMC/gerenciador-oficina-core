package com.fiap.pj.infra.sk.api;

import lombok.Getter;

import java.util.List;

@Getter
public class Slice<T> {

    private final boolean hasNext;
    private final List<T> items;

    public Slice(boolean hasNext, List<T> items) {
        this.hasNext = hasNext;
        this.items = items;
    }
}