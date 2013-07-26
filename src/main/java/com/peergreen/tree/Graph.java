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
