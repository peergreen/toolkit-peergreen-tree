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
package com.peergreen.tree;

import java.util.List;

/**
 * Interface of all nodes.
 * Nodes allows to get parent and children.
 * Nodes can be visited with the help of a Visitor.
 * @author Florent Benoit
 */
public interface Node<T> {

    /**
     * @return data associated to this node.
     */
    T getData();

    /**
     * @return the parent node.
     */
    Node<T> getParent();

    /**
     * @return children of the nodes.
     */
    List<Node<T>> getChildren();

    /**
     * Walk on the nodes of this node by using a visitor.
     * @param visitor the visitor to use
     */
    void walk(NodeVisitor<T> visitor);
}
