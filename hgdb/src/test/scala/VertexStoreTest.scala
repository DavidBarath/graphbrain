import org.scalatest.FunSuite
import com.graphbrain.VertexStore
import com.graphbrain.Vertex
import com.graphbrain.Node

class VertexStoreTest extends FunSuite {
  val store = VertexStore("testhgdb")

  test("put Vertex") {
    val vertex = Vertex("vertex0")
    store.remove(vertex)
    store.put(vertex)
    
    val vertexOut = store.get("vertex0")
    assert(vertex.id == vertexOut.id)
  }

  test("put Node") {
    val node = Node("node0")
    store.remove(node)
    store.put(node)
    
    val nodeOut = store.get("node0")
    assert(node.id == nodeOut.id)
  }

  test("add two node relationship") {
    val node0 = Node("node0")
    val node1 = Node("node1")
    store.remove(node0)
    store.remove(node1)
    store.put(node0)
    store.put(node1)
    store.addrel("test", Array[Node](node0, node1))

    val eid = "test " + node0.id + " " + node1.id
    val edge = store.get(eid)
    assert(edge.id == eid)
  }

  test("delete two node relationship") {
    val node0 = Node("node0")
    val node1 = Node("node1")
    store.remove(node0)
    store.remove(node1)
    store.put(node0)
    store.put(node1)
    store.addrel("test", Array[Node](node0, node1))
    store.delrel("test", Array[Node](node0, node1))
  }
}