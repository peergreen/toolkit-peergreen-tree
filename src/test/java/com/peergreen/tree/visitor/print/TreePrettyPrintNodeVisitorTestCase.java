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

import com.peergreen.tree.Node;
import com.peergreen.tree.node.SimpleNode;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.testng.Assert.assertEquals;

public class TreePrettyPrintNodeVisitorTestCase {
    private ByteArrayOutputStream baos;
    private TreePrettyPrintNodeVisitor<String> visitor;

    @BeforeMethod
    public void setUp() throws Exception {
        baos = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(baos);
        visitor = new TreePrettyPrintNodeVisitor<String>(stream) {

            @Override
            protected void doPrintInfo(PrintStream stream, Node<String> node) {
                stream.printf("%s%n", node.getData());
            }
        };
    }

    @Test
    public void testCompleteTree() throws Exception {
        SimpleNode<String> root = new SimpleNode<>("root (level 0)");
        SimpleNode<String> a = new SimpleNode<>("a (level 1)");
        SimpleNode<String> b = new SimpleNode<>("b (level 1)");
        SimpleNode<String> c = new SimpleNode<>("c (level 2)");
        SimpleNode<String> d = new SimpleNode<>("d (level 3)");
        SimpleNode<String> e = new SimpleNode<>("e (level 1)");

        root.addChild(a);
        root.addChild(b);
        root.addChild(e);
        a.setParent(root);
        b.setParent(root);
        e.setParent(root);

        b.addChild(c);
        c.addChild(d);

        c.setParent(b);
        d.setParent(c);

        root.walk(visitor);

        String expected = "root (level 0)\n" +
                          "|-- a (level 1)\n" +
                          "|-- b (level 1)\n" +
                          "|   `-- c (level 2)\n" +
                          "|       `-- d (level 3)\n" +
                          "`-- e (level 1)\n";
        assertEquals(baos.toString(), expected);
    }
}
