class TreeNode {
    int data;
    TreeNode leftChild;
    TreeNode rightChild;

    public TreeNode(int data) {
        this.data = data;
        leftChild = null;
        rightChild = null;
    }
}

public class SimpleBST {

    private TreeNode rootNode;

    public SimpleBST() {
        rootNode = null;
    }

    public void addData(int data) {
        rootNode = addRecursively(rootNode, data);
    }

    private TreeNode addRecursively(TreeNode current, int data) {
        if (current == null) {
            return new TreeNode(data);
        }
        if (data < current.data) {
            current.leftChild = addRecursively(current.leftChild, data);
        } else if (data > current.data) {
            current.rightChild = addRecursively(current.rightChild, data);
        }
        return current;
    }

    public boolean contains(int data) {
        return findData(rootNode, data);
    }

    private boolean findData(TreeNode current, int data) {
        if (current == null) {
            return false;
        }
        if (data == current.data) {
            return true;
        }
        return data < current.data 
               ? findData(current.leftChild, data) 
               : findData(current.rightChild, data);
    }

    public void displayInOrder() {
        processInOrder(rootNode);
    }

    private void processInOrder(TreeNode current) {
        if (current != null) {
            processInOrder(current.leftChild);
            System.out.print(current.data + " ");
            processInOrder(current.rightChild);
        }
    }

    public static void main(String[] args) {
        SimpleBST tree = new SimpleBST();
        
        tree.addData(50);
        tree.addData(30);
        tree.addData(70);
        tree.addData(20);
        tree.addData(40);
        tree.addData(60);
        tree.addData(80);

        System.out.println("In-order display:");
        tree.displayInOrder();
        
        System.out.println("\n\nChecking for 25:");
        System.out.println(tree.contains(25) ? "Found" : "Not Found");
        
        System.out.println("\nChecking for 40:");
        System.out.println(tree.contains(40) ? "Found" : "Not Found");
    }
}
