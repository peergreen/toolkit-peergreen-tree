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
