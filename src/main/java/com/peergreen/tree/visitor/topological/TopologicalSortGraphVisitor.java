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

package com.peergreen.tree.visitor.topological;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import com.peergreen.tree.Graph;
import com.peergreen.tree.GraphVisitor;
import com.peergreen.tree.Node;

/**
 * Implementation of the topological sort.
 * @param <T> the data wrapped into the Node
 * @author Florent Benoit
 */
public class TopologicalSortGraphVisitor<T> implements GraphVisitor<T> {

    /**
     * Sorted List.
     */
    private final List<Node<T>> sortedList;

    /**
     * Sort has been made.
     */
    private boolean sorted = false;

    /**
     * Constructor with the given set of nodes to sort.
     * @param nodes the list of nodes on which topological sort will be applied
     */
    public TopologicalSortGraphVisitor() {
        this.sortedList = new ArrayList<>();
    }

    /**
     * @return a sorted list
     */
    public List<Node<T>> getSortedList() {
        if (!this.sorted) {
           throw new IllegalStateException("Visitor has not be called, graph is not yet sorted.");
        }
        return this.sortedList;
    }

    /**
     * @return a sorted list of inner elements
     */
    public List<T> getSortedInnerList() {
        List<T> innerList = new ArrayList<>();
        for (Node<T> node : getSortedList()) {
            innerList.add(node.getData());
        }
        return innerList;
    }

    @Override
    public void visit(Graph<T> graph) {
        Set<Node<T>> visitedNodes = new HashSet<>();

        //TODO: here we should call a NodeVisitor
        for (Node<T> node : graph.getNodes()) {
            visit(node, graph.getNodes(), visitedNodes, new Stack<Node<T>>());
        }
        sorted = true;
    }


    /**
     * Here is the algorithm for the topological sort.
     * L <-- Empty list that will contain the sorted nodes <br/>
     * S <-- Set of all nodes with no outgoing edges<br/>
     * for each node n in S do<br/>
     *     visit(n) <br/>
     * <br/>
     * function visit(node n)<br/>
     *     if n has not been visited yet then<br/>
     *         mark n as visited<br/>
     *         for each node m with an edge from m to n do<br/>
     *             visit(m)<br/>
     *         add n to L<br/>
     *
     *
     * @param analyzingNode node that is being analyzed
     * @param stackVisitedNodes the local stack of visited nodes to detect cycles
     */
    public void visit(final Node<T> analyzingNode, final Collection<Node<T>> nodes, final Set<Node<T>> visitedNodes, final Stack<Node<T>> stackVisitedNodes) {

        // Cycle detected
        if (stackVisitedNodes.contains(analyzingNode)) {
            StringBuilder sb = new StringBuilder();
            sb.append("There is a cycle error for the node '");
            sb.append(analyzingNode);
            sb.append("' and cycle is made between the nodes '");
            sb.append(stackVisitedNodes);
            sb.append("'. Dependencies are : \n");
            for (Node<T> stackNode : stackVisitedNodes) {
                sb.append(" ( ");
                sb.append(stackNode);
                sb.append("--->");
                sb.append(stackNode.getChildren());
                sb.append(" )");
            }
            throw new TopologicalSortGraphCycleException(sb.toString());
        }

        // Not yet visited, so visit the node
        if (!visitedNodes.contains(analyzingNode)) {
            visitedNodes.add(analyzingNode);
            for (Node<T> node : nodes) {
                if (analyzingNode.getChildren().contains(node)) {
                    stackVisitedNodes.push(analyzingNode);
                    visit(node, nodes, visitedNodes, stackVisitedNodes);
                    stackVisitedNodes.pop();
                }
            }
            // Add element to the sorted list
            this.sortedList.add(analyzingNode);

        }
    }

}