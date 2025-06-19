public class DataFrame {
    private HashTable<SeriesV2<Object>> tabularData;
    private int numRows;
    private int numCols;


    public DataFrame(){
        numRows = 0;
        numCols = 0;
        tabularData = new HashTable<>();
    }

    public DataFrame(String _k, SeriesV2<Object> _series){
        numCols = 1;
        numRows = _series.getLength();
        tabularData = new HashTable<>();
        tabularData.insert(_k, _series);
    }

    public SeriesV2<Object> colLoc(String k){
        return tabularData.lookup(k);
    }

    public String toString(){
        String output = "printing the dataframe ...\n==================\n";
        String[] keys = tabularData.getValidKeys();
        Object[] values = tabularData.getDataArray();
        for(int i = 0; i < keys.length; i++){
            int index = tabularData.getHashIndex(keys[i]);
            output += "[colName:\t" + keys[i]+ "]\n";
            output += values[index].toString()+"\n";
        }
        return output;
    }

    public int getNumRows(){
        return this.numRows;
    }
    public int getNumCols(){
        return this.numCols;
    }

    public String[] getColNames(){
        return tabularData.getValidKeys();
    }

    public void addColumn(String k, SeriesV2<Object> s) throws IllegalArgumentException{
        if(s.getLength() != numRows){
            throw new IllegalArgumentException("addColumn(String k, SeriesV2<Object> s): the length of s does not match the dataframe's # of rows");
        }
        tabularData.insert(k, s);
        numCols++;
    }

    public void removeColumn(String k){
        tabularData.delete(k);
        numCols--;
    }
}
