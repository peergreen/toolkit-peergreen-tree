/**
 * Copyright 2012-2013 Peergreen S.A.S. All rights reserved.
 * Proprietary and confidential.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.peergreen.tree;

import static org.testng.Assert.assertEquals;

import java.util.Collections;

import org.testng.annotations.Test;

import com.peergreen.tree.node.AbsNode;
import com.peergreen.tree.node.LazyNode;

/**
 * @author Guillaume Sauthier
 */
public class LazyNodeTestCase {

    @Test
    public void testSetParentLinkNodeTogether() throws Exception {
        A one = new A();
        one.value = "One";
        A two = new A();
        two.value = "Two";
        one.child = two;
        AbsNode<A> parent = new LazyNode<A>(new Adapter(), one);
        AbsNode<A> child = new LazyNode<A>(new Adapter(), two);

        child.setParent(parent);

        assertEquals(child.getParent(), parent);
        assertEquals(parent.getChildren().size(), 1);
        assertEquals(parent.getChildren().get(0), child);

    }

    private class A {
        @SuppressWarnings("unused")
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
