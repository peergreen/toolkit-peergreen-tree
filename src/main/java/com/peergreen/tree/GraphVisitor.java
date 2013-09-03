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
 * Allows to visit a graph
 * @author Florent Benoit
 */
public interface GraphVisitor<T> {

    /**
     * Visitor for a graph
     * @param graph
     */
    void visit(Graph<T> graph);
}
