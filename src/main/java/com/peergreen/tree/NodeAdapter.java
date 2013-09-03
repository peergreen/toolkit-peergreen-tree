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

/**
 * A {@literal NodeAdapter} have the responsibility to adapt an existing tree structure into a {@link Node}.
 * It is only used with {@link com.peergreen.tree.node.LazyNode}.
 *
 * @see com.peergreen.tree.node.LazyNode
 * @author Guillaume Sauthier
 */
public interface NodeAdapter<T> {

    /**
     * Returns the list of children of the given instance.
     * @param object instance with potential children.
     * @return the children of the given object (If there are no children, an empty structure <bold>must</bold> be returned).
     * @see java.util.Collections#emptyList()
     */
    Iterable<T> getChildren(T object);
}
