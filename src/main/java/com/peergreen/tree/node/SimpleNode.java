/**
 * Copyright 2013 Peergreen S.A.S.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.peergreen.tree.node;

import java.util.ArrayList;
import java.util.List;

import com.peergreen.tree.Node;

/**
 * Simple node.
 * It can be used when there is no existing node/graphs
 * This allows to add children
 * @author Florent Benoit
 */
public class SimpleNode<T> extends AbsNode<T> {

    /**
     * List of nodes that are children.
     */
    private final List<Node<T>> children;

    /**
     * Make a simple node wrapping the given data.
     * @param data the data wrapped.
     */
    public SimpleNode(T data) {
        super(data);
        this.children = new ArrayList<>();
    }

    /**
     * @return list of children
     */
    @Override
    public List<Node<T>> getChildren() {
        return children;
    }


    public void addChild(Node<T> child) {
        children.add(child);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Node<");
        sb.append(String.valueOf(getData()));
        sb.append(">");
        return sb.toString();
    }

}
