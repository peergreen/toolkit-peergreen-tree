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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.peergreen.tree.Graph;
import com.peergreen.tree.Node;
import com.peergreen.tree.node.SimpleNode;
import com.peergreen.tree.visitor.topological.TopologicalSortGraphCycleException;
import com.peergreen.tree.visitor.topological.TopologicalSortGraphVisitor;

/**
 * Tests for the topological sort.
 * @author Florent Benoit
 */
public class TestTopologicalSort {


    @Test
    public void testSort() {
        SimpleNode<String> nodeA = new SimpleNode<>("A");
        SimpleNode<String> nodeB = new SimpleNode<>("B");
        SimpleNode<String> nodeC = new SimpleNode<>("C");
        SimpleNode<String> nodeD = new SimpleNode<>("D");
        SimpleNode<String> nodeE = new SimpleNode<>("E");
        SimpleNode<String> nodeF = new SimpleNode<>("F");

        nodeD.addChild(nodeA);
        nodeD.addChild(nodeB);

        nodeE.addChild(nodeB);
        nodeE.addChild(nodeC);

        nodeF.addChild(nodeD);
        nodeF.addChild(nodeE);

        Set<Node<String>> nodes = new HashSet<>();
        nodes.add(nodeA);
        nodes.add(nodeB);
        nodes.add(nodeC);
        nodes.add(nodeD);
        nodes.add(nodeE);
        nodes.add(nodeF);

        Graph<String> graph = new SimpleGraph<>(nodes);
        TopologicalSortGraphVisitor<String> visitor = new TopologicalSortGraphVisitor<>();
        graph.walk(visitor);


        List<Node<String>> sortedList = visitor.getSortedList();

        // Get indexes
        int iNodeA = sortedList.indexOf(nodeA);
        int iNodeB = sortedList.indexOf(nodeB);
        int iNodeC = sortedList.indexOf(nodeC);
        int iNodeD = sortedList.indexOf(nodeD);
        int iNodeE = sortedList.indexOf(nodeE);
        int iNodeF = sortedList.indexOf(nodeF);

        // Check Node A and B are before D
        Assert.assertTrue(iNodeA < iNodeD);
        Assert.assertTrue(iNodeB < iNodeD);

        // Check Node B and C are before E
        Assert.assertTrue(iNodeB < iNodeE);
        Assert.assertTrue(iNodeC < iNodeE);

        // Check Node D and E are before F
        Assert.assertTrue(iNodeD < iNodeF);
        Assert.assertTrue(iNodeE < iNodeF);
    }


    @Test(expectedExceptions=TopologicalSortGraphCycleException.class)
    public void testCycle() {
        SimpleNode<String> nodeA = new SimpleNode<>("A");
        SimpleNode<String> nodeB = new SimpleNode<>("B");
        SimpleNode<String> nodeC = new SimpleNode<>("C");
        SimpleNode<String> nodeD = new SimpleNode<>("D");

        nodeB.addChild(nodeD);
        nodeC.addChild(nodeD);

        nodeA.addChild(nodeB);
        nodeA.addChild(nodeC);

        nodeD.addChild(nodeA);

        Set<Node<String>> nodes = new HashSet<>();
        nodes.add(nodeA);
        nodes.add(nodeB);
        nodes.add(nodeC);
        nodes.add(nodeD);

        Graph<String> graph = new SimpleGraph<>(nodes);
        TopologicalSortGraphVisitor<String> visitor = new TopologicalSortGraphVisitor<>();
        graph.walk(visitor);

        Assert.fail("There is a cycle and it appears it was not found :-/");
    }
}