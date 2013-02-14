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

import java.util.Collection;

/**
 * Graph allowing to get multiple nodes that are not linked together.
 * @author Florent Benoit
 */
public interface Graph<T> {

    Collection<Node<T>> getNodes();

    /**
     * Walk on the nodes of this node by using a visitor.
     * @param visitor the visitor to use
     */
    void walk(GraphVisitor<T> visitor);

}
