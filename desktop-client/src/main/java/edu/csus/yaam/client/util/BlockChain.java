package edu.csus.yaam.client.util;

import java.util.Collection;
import java.util.LinkedList;

/**
 * @author Ryan R
 * @date 4/10/2018
 */
public class BlockChain<T> extends LinkedList<T> {
    public BlockChain() {}

    public BlockChain(Collection<? extends T> c) {
        super(c);
    }
}