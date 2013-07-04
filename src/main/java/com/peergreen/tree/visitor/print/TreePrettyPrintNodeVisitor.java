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

package com.peergreen.tree.visitor.print;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.peergreen.tree.Node;
import com.peergreen.tree.NodeVisitor;

/**
 * Sample output:
 * <pre>
 * Pipeline #master
 * |-- Pipeline #p1
 * |   |-- UnitOfWork #1
 * |   `-- UnitOfWork #2
 * |-- UnitOfWork #3
 * `-- Pipeline #p2
 *     |-- UnitOfWork #4
 *     `-- UnitOfWork #5
 * </pre>
 */
public abstract class TreePrettyPrintNodeVisitor<T> implements NodeVisitor<T> {

    private enum Element {
        TEE("|-- "), BAR("|   "), LAST("`-- "), BLANK("    ");

        private final String value;

        Element(String value) {
            this.value = value;
        }
    }

    private PrintStream stream ;

    public TreePrettyPrintNodeVisitor() {
        this(System.out);
    }

    public TreePrettyPrintNodeVisitor(PrintStream stream) {
        this.stream = stream;
    }

    @Override
    public void visit(Node<T> node) {

        List<Element> prefix = new ArrayList<Element>();
        if (node.getParent() != null) {
            // we're visiting an item with parent

            // handle last element
            if (isLastChild(node)) {
                prefix.add(0, Element.LAST);
            } else {
                prefix.add(0, Element.TEE);
            }

            // handle other elements
            Node<T> current = node.getParent();
            while (current.getParent() != null) {

                if (isLastChild(current)) {
                    prefix.add(0, Element.BLANK);
                } else {
                    prefix.add(0, Element.BAR);
                }

                // Move cursor
                current = current.getParent();
            }

        } // else no parent (thus no/empty prefix)

        for (Element element : prefix) {
            stream.print(element.value);
        }

        doPrintInfo(stream, node);
    }

    protected abstract void doPrintInfo(PrintStream stream, Node<T> node);

    private boolean isLastChild(Node<?> node) {
        Node<?> parent = node.getParent();
        Iterator<? extends Node<?>> i = parent.getChildren().iterator();

        // Consume the Iterator until we find the current Node
        // Need to use object identity instead of Node.equals() since by default,
        // the equals() method delegates to the content's equals().
        long nodeIdentity = System.identityHashCode(node);
        Node<?> child = i.next();
        while (nodeIdentity != System.identityHashCode(child)) {
            child = i.next();
        }

        // It is the last child if this is the last item of the Iterator
        return !i.hasNext();
    }

}
