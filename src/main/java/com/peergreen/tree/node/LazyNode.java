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
import java.util.Collections;
import java.util.List;

import com.peergreen.tree.Node;
import com.peergreen.tree.NodeAdapter;

/**
 * Lazy node.
 * The node model is based on an existing node model.
 * Children are discovered in a lazy mode as soon as the structure is traversed (with a
 * {@link com.peergreen.tree.NodeVisitor} or through the Node API).
 * It uses a {@link NodeAdapter} to map the Node structure to the underlying model.
 *
 * @see NodeAdapter
 * @see com.peergreen.tree.NodeVisitor
 * @author Florent Benoit
 */
public class LazyNode<T> extends AbsNode<T> {

    private final NodeAdapter<T> adapter;

    private List<Node<T>> children;

    public LazyNode(NodeAdapter<T> adapter, T data) {
        super(data);
        this.adapter = adapter;
    }


    @Override
    public List<Node<T>> getChildren() {
        if (children == null) {
            // Initialize
            Iterable<T> items = adapter.getChildren(this.getData());
            if ((items != null) && items.iterator().hasNext()) {
                // Node has children
                children = new ArrayList<>();
                for (T item : items) {
                    LazyNode<T> child = new LazyNode<T>(adapter, item);
                    child.setParent(this);
                    //children.add(child);
                }
            } else {
                // Node has no children
                children = Collections.emptyList();
            }
        }
        return children;
    }

}
