public class HashTable<V> {
    private static final Object BRIDGE = new String("[BRIDGE]".toCharArray());
    private int size;
    private int capacity;
    private String[] keys;
    private V[] values;

    @SuppressWarnings("unchecked")
    public HashTable(){
        this.size = 0;
        this.capacity = 4;
        this.keys = new String[this.capacity];
        this.values = (V[]) new Object[this.capacity];
    }

    public String toString() {
        String output = ("printing the hash table ...\n==================\n");
        for(int i = 0; i < capacity; i++) {
            output += "index:\t" + String.valueOf(i);
            output += ",\tkey:\t" + String.valueOf(keys[i]);
            output += ",\tdata:\t" + String.valueOf(values[i]);
            output+= "\n";
        }
        return output;
    }

    public int getSize(){
        return this.size;
    }

    public int getCapacity(){
        return this.capacity;
    }

    public String[] getKeyArray() {
        return keys.clone();
    }

    public V[] getDataArray() {
        return values.clone();
    }

    public String[] getValidKeys() {
        int count = 0;
        for(String k : keys){
            if(k != null && k != BRIDGE){
                count++;
            }
        };
        String[] result = new String[count];
        int index = 0;
        for(String k : keys){
            if(k != null && k != BRIDGE){
                result[index] = k;
                index++;
            }
        }
        return result;
    }
    public int getHashIndex(String k) {
        int hashValue = 0;
        for(int i = 0; i < k.length(); i++){
            int letter = k.charAt(i) - 96;
            hashValue += (hashValue * 27 + letter);
        }
        return hashValue % this.getCapacity();
    }

    public V lookup(String k)throws NullPointerException{
        if(k == null){
            throw new NullPointerException("lookup(String key): key is null");
        }
        int index = getHashIndex(k);
        for(int i = 0; i < capacity; i++){
            int probe = (index + i) % capacity;
            if(keys[probe] == null){
                return null;
            }
            if(k.equals(keys[probe])){
                return values[probe];
            }
        }
        return null;
    }

    public int insert(String k, V v) throws NullPointerException{
        if(k == null){
            throw new NullPointerException("insert(String k, V v): k is null");
        }
        if(v == null){
            throw new NullPointerException("insert(String k, V v): v is null");
        }

        int index = getHashIndex(k);
        for(int i = 0; i < capacity; i++){
            int probe = (index + i) % capacity;
            if(keys[probe] == null || keys[probe] == BRIDGE){
                keys[probe] = k;
                values[probe] = v;
                size++;
                if ((double)(size) / capacity >= 0.55){
                    sizeUp();
                }
                return probe;
            }
            if(k.equals(keys[probe])){
                values[probe] = v;
                return probe;
            }
        }
        return index;
    }

    @SuppressWarnings("unchecked")
    private void sizeUp() {
        String[] oldKeys = keys;
        V[] oldValues = values;
        capacity *= 2;
        keys = new String[capacity];
        values = (V[]) new Object[capacity];
        size = 0;
        for(int i = 0; i < oldKeys.length; i++) {
            if(oldKeys[i] != null && oldKeys[i] != BRIDGE){
                insert(oldKeys[i], oldValues[i]);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void sizeDown() {
        if(capacity <= 4){
            return;
        }
        String[] oldKeys = keys;
        V[] oldValues = values;
        capacity /= 2;
        keys = new String[capacity];
        values = (V[]) new Object[capacity];
        size = 0;
        for(int i = 0; i < oldKeys.length; i++) {
            if(oldKeys[i] != null && oldKeys[i] != BRIDGE){
                insert(oldKeys[i], oldValues[i]);
            }
        }
    }

    public int delete(String k) {
        if(k == null){
            throw new NullPointerException("delete(String k): k is null");
        }

        int index = getHashIndex(k);
        for(int i = 0; i < capacity; i++){
            int probe = (index + i) % capacity;
            if(keys[probe] == null){
                return index;
            }
            if(k.equals(keys[probe])){
                keys[probe] = (String) BRIDGE;
                values[probe] = null;
                size--;
                if((double)size / capacity <= 0.3){
                    sizeDown();
                }
                return probe;
            }
        }
        return index;
    }
}
