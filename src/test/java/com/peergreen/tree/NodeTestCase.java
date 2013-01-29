/*
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

package com.peergreen.tree;

import org.testng.annotations.Test;

import java.util.Collections;

import static org.testng.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: guillaume
 * Date: 29/01/13
 * Time: 11:34
 * To change this template use File | Settings | File Templates.
 */
public class NodeTestCase {

    @Test
    public void testSetParentLinkNodeTogether() throws Exception {
        A one = new A();
        one.value = "One";
        A two = new A();
        two.value = "Two";
        one.child = two;
        Node<A> parent = new Node<A>(new Adapter(), one);
        Node<A> child = new Node<A>(new Adapter(), two);

        child.setParent(parent);

        assertEquals(child.getParent(), parent);
        assertEquals(parent.getChildren().size(), 1);
        assertEquals(parent.getChildren().get(0), child);

    }

    private class A {
        String value;
        A child;
    }

    private class Adapter implements NodeAdapter<A> {

        @Override
        public Iterable<A> getChildren(A a) {
            if (a.child == null) {
                return Collections.emptySet();
            }
            return Collections.singleton(a.child);
        }
    }
}
