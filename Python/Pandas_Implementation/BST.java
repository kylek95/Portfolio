public class BST<I extends Comparable, T>{
    class BSTNode {
        private I index;
        private T data;
        private BSTNode left;
        private BSTNode right;

        /**
         * Default constructor. Sets all instance variables to be null.
         */
        public BSTNode() {
            // TODO
            this.index = null;
            this.data = null;
            this.left = null;
            this.right = null;
        }

        /**
         * Constructor. Sets data and index to be _data and _index respectively.
         */
        public BSTNode(I _index, T _data) {
            this.index = _index;
            this.data = _data;
            this.left = null;
            this.right = null;
        }

        /**
         * Returns the index stored in this node.
         */
        public I getIndex() {
            // TODO
            return this.index;
        }

        /**
         * Returns the data stored in this node.
         */
        public T getData() {
            // TODO
            return this.data;
        }

        /**
         * Updates the data in this node to the specified value.
         */
        public void setData(T d) {
            // TODO
            this.data = d;
        }

        /**
         * Returns a string representation of the node, indicating its index and data.
         */
        public String toString() {
            // TODO
            return "index:\t"+String.valueOf(this.index)+",\tdata:\t"+String.valueOf(this.data)+"\n";
        }
    }


    private BSTNode root;
    private int size;

    /**
     * Constructor. Initializes an empty BST with root set to null and size set to 0.
     */
    public BST() {
        // TODO
        this.root = null;
        this.size = 0;
    }


    /**
     * Performs an in-order traversal of the BST and records indices and data values.
     */
    private String inOrderTraversal(BSTNode node) {
        // TODO
        if (node == null) {
            return "";
        }
        String left = inOrderTraversal(node.left);
        String current = node.toString();
        String right = inOrderTraversal(node.right);
        return left + current + right;
    }

    /**
     * Returns a string representation of the entire BST using in-order traversal.
     */
    public String toString() {
        // TODO
        String output = "In-order Traversal of the BST ...\n" +
                "==================\n";
        return output + inOrderTraversal(root);
    }

    /**
     * Returns the size of the BST, i.e., the number of valid nodes.
     */
    public int getSize() {
        // TODO
        return this.size;
    }

    /**
     * Adds a new node with the specified index and data to the BST.
     */
    public void addNode(I _index, T _data) {
        // TODO
        BSTNode current = root;
        while(true){
            if(current == null){
                root = new BSTNode(_index,_data);
                size++;
                break;
            }
            if(current.index.compareTo(_index) < 0){
                if(current.right == null){
                    current.right = new BSTNode(_index,_data);
                    size++;
                    break;
                } else {
                    current = current.right;
                }
            } else {
                if (current.left == null) {
                    current.left = new BSTNode(_index, _data);
                    size++;
                    break;
                } else {
                    current = current.left;
                }
            }
        }
    }

    /**
     * Searches for a node with the specified index in the BST.
     */
    public BSTNode searchNode(I _index) {
        // TODO
        BSTNode current = root;
        while(current != null){
            if(current.index.equals(_index)){
                return current;
            } else if(current.index.compareTo(((Object)_index)) < 0){
                current = current.right;
            } else {
                current = current.left;
            }
        }
        return null;
    }

    /**
     * Removes a node with the specified index from the BST.
     */
    public void removeNode(I _index) throws IllegalArgumentException{
        // TODO
        if(searchNode(_index) == null) {
            throw new IllegalArgumentException("removeNode(I _index): No node with an index "
                    + String.valueOf(_index) + " in the BST");
        }
        BSTNode parent = null;
        BSTNode current = root;
        while(current != null && !current.getIndex().equals(_index)){
            parent = current;
            if(current.getIndex().compareTo(_index) < 0){
                current = current.right;
            } else{
                current = current.left;
            }
        }

        if(current.left != null && current.right != null){
            BSTNode successorParent = current;
            BSTNode successor = current.right;

            while (successor.left!=null){
                successorParent = successor;
                successor = successor.left;
            }
            //sets the index and data to the successor node
            current.index = successor.getIndex();
            current.setData(successor.getData());

            //replaces the current node with the successor node
            current = successor;
            parent = successorParent;
        }

        BSTNode child = null;
        if(current.left != null){
            child = current.left;
        } else {
            child = current.right;
        }

        if (parent == null) {
            //checks to see if there are only two nodes
            //sets child to root node
            root = child;
        } else if (parent.left == current) {
            //checks if the removed node is the left or the right child of the parent.
            // if left sets child to the parents left
            parent.left = child;
        } else {

            parent.right = child;
        }
        size--;

    }

    /**
     * Updates a node's data with a new value, given its index.
     */
    public void updateNode(I _index, T _newData) throws IllegalArgumentException{
        // TODO
        BSTNode node = searchNode(_index);
        if(node == null){
            throw new IllegalArgumentException("updateNode(I _index, T _newData): No node with an index "
                    +String.valueOf(_index)+" in the BST");
        }
        node.setData(_newData);
    }

    
/************************************ GRADING CODE (DO NOT MODIFY) ************************************ */
    /**
     * Performs a pre-order traversal of the BST.
     */
    private void preOrderTraversal(BSTNode node, int[] idx, String[] arr, boolean dataFlag) {
        // DO NOT CHANGE THIS. THIS FOR TESTING PURPOSES
        if(node == null)
            return;

        if(dataFlag)
            arr[idx[0]] = String.valueOf(node.getData());
        else
            arr[idx[0]] = String.valueOf(node.getIndex());
        idx[0]++;
        
        preOrderTraversal(node.left, idx, arr, dataFlag);
        preOrderTraversal(node.right, idx, arr, dataFlag);
    }

    /**
     * Returns an array of data values in pre-order traversal order.
     * @return A String array containing the data values of all nodes in pre-order order
     */
    public String[] getDataArray() {
        /// DO NOT CHANGE THIS. THIS FOR TESTING PURPOSES
        String[] dataArr = new String[size];
        preOrderTraversal(this.root, new int[1], dataArr, true);
        return dataArr;
    }

    /**
     * Returns an array of index values in pre-order traversal order.
     * @return A String array containing the index values of all nodes in pre-order order
     */
    public String[] getIndexArray() {
        // DO NOT CHANGE THIS. THIS FOR TESTING PURPOSES
        String[] indexArr = new String[size];
        preOrderTraversal(this.root, new int[1], indexArr, false);
        return indexArr;
    }

/****************************************************************************************************** */

}
