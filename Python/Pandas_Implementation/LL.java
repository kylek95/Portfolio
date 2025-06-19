public class LL<T>{
    private LLNode head;
    private LLNode tail;
    private int length;

    public LL(){
        this.head = new LLNode();
        this.tail = new LLNode();
        this.head.next = this.tail;
        this.length = 0;
    }

    public String toString(){
        String output = "print the series ...\n==================\n";
        LLNode current = this.head;
        output += "null\t: null\n";
        for(int i = 0; i < this.length;i++) {
            current = current.next;
            output += current.getIndex() + "\t";
            output += ": " + current.getData() + "\n";
        }
        output += "null\t: null\n";
        return output;
    }

    public int getLength(){
        return this.length;
    }
    
    public String[] getDataArray(){
        String[] output = new String[this.length];
        LLNode current = this.head.next;
        for(int i = 0; i < this.length;i++){
            output[i] = String.valueOf(current.data);
            current = current.next;
        }
        return output;
    }

    public String[] getIndexArray(){
        String[] output = new String[this.length];
        LLNode current = this.head.next;
        for(int i = 0; i < this.length;i++){
            output[i] = current.index;
            current = current.next;
        }
        return output;
    }

    public void appendNode(String _index, T _data){
        LLNode current = this.head;
        if(_index == null || _index == ""){
            _index = String.valueOf(this.length);
        }
        this.length++;
        LLNode newNode = new LLNode(_index,_data);
        for(int i = 0; i <= this.length;i++){
            if(current.next == this.tail){
                current.next = newNode;
                newNode.next = this.tail;
                break;
            }
            current = current.next;
        }

    }

    public LLNode searchNode(String _index){
        LLNode current = this.head.next;
        for(int i = 0; i < this.length; i++){
            if(current.index.equals(_index)){
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public void removeNode(String _index) throws IllegalArgumentException{
        LLNode current = this.head;
        boolean flag = true;
        for(int i = 0; i < this.length; i++){
            if(current.next.index.equals(_index)){
                current.next = current.next.next;
                this.length--;
                flag = false;
                break;
            }
            current = current.next;
        }
        if(flag) {
            throw new IllegalArgumentException(
                    "removeNode(String _index): No node with an index "
                            + _index + " in the list");
        }
    }

    public void updateNode(String _index, T value){
        LLNode updatedNode = searchNode(_index);
        if(updatedNode == null){
            throw new IllegalArgumentException(
            "updateNode(String _index, T value): No node with an index " 
            + _index + " in the list");
        } else {
            updatedNode.setData(value);
        }
    }

    public class LLNode{
        private String index;
        private T data;
        private LLNode next;

        public LLNode(){
            this.index = null;
            this.data = null;
            this.next  = null;
        }

        public LLNode(String _index, T _data){
            this.index = _index;
            this.data = _data;
            this.next = null;
        }

        public String getIndex(){
            return this.index;
        }

        public T getData(){
            return this.data;
        }

        public void setData(T d){
            this.data = d;
        }
    }
}