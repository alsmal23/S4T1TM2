package edu.csus.yaam.server.util;

import java.util.Collection;
import java.util.LinkedList;

/**
 * @author Ryan R
 * @date 4/1/2018
 */
public class BlockChain<T> extends LinkedList<T> {
    public BlockChain() {}

    public BlockChain(Collection<? extends T> c) {
        super(c);
    }
}