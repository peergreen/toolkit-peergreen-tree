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
package com.peergreen.tree.graph;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.peergreen.tree.Graph;
import com.peergreen.tree.GraphVisitor;
import com.peergreen.tree.Node;


/**
 * Graph allowing to get multiple nodes that are not linked together.
 * @author Florent Benoit
 */
public class SimpleGraph<T> implements Graph<T> {

    private final Set<Node<T>> nodes;

    public SimpleGraph() {
        this.nodes = new HashSet<>();
    }

    public SimpleGraph(Collection<Node<T>> initialNodes) {
        this();
        this.nodes.addAll(initialNodes);
    }


    public void addNode(Node<T> node) {
        nodes.add(node);
    }


    @Override
    public Set<Node<T>> getNodes() {
        return nodes;
    }

    /**
     * Walk on the nodes of this node by using a visitor.
     * @param visitor the visitor to use
     */
    public void walk(GraphVisitor<T> visitor) {
        visitor.visit(this);
    }

}
