/*
 * Copyright 2013 Peergreen S.A.S.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.peergreen.tree.node;

import java.util.List;

import com.peergreen.tree.Node;
import com.peergreen.tree.NodeVisitor;

/**
 * Abstract class.
 * @author Guillaume Sauthier
 */
public abstract class AbsNode<T> implements Node<T> {

    private final T data;
    private Node<T> parent;


    public AbsNode(T data) {
        this.data = data;
    }

    @Override
    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
        if (parent != null && !parent.getChildren().contains(this)) {
            parent.getChildren().add(this);
        }
    }

    @Override
    public T getData() {
        return data;
    }

    /**
     * Needs to be defined by subclasses.
     */
    @Override
    public abstract List<Node<T>> getChildren();

    @Override
    public void walk(NodeVisitor<T> visitor) {
        visitor.visit(this);
        for (Node<T> child : getChildren()) {
            child.walk(visitor);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node<?> node = (Node<?>) o;

        if (!data.equals(node.getData())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return data.hashCode();
    }
}
