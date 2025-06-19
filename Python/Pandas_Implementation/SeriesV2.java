public class SeriesV2<T> implements Series<T>{
    /** The Integer array that contains the list of data that constitutes a Series object. */
    private LL<T> seriesData;
    private BST<String,T> seriesDataBST;

    /**
     * Constructs a new Series object.
     *
     * @param _rowNames an array of row names
     * @param _data an array of Integer data
     */
    public SeriesV2(String[] _rowNames, T[] _data) {
        seriesData = new LL<>();
        seriesDataBST = new BST<>();
        // TODO: Implement constructor
        if(_data == null){
            throw new NullPointerException("Series(String[] _index, T[] _data): _data can't be null. Terminating the program");
        }
        try{
            if(_rowNames == null){
                throw new NullPointerException();
            }
            for(int i = 0; i < _rowNames.length; i++){
                if(_rowNames[i] == null){
                    throw new IllegalArgumentException("Series(String[] _index, T[] _data): _rowNames is not valid");
                }
            }
            if(_data.length != _rowNames.length){
                throw new IllegalArgumentException("Series(String[] _index, T[] _data): the length of _index and _data must be the same");
            }
            for(int i = 0; i < _data.length; i++){
                seriesData.appendNode(_rowNames[i], _data[i]);
                seriesDataBST.addNode(_rowNames[i], _data[i]);
            }
        } catch (NullPointerException e){
            for (int i = 0; i < _data.length; i++) {
                seriesData.appendNode(String.valueOf(i), _data[i]);
                seriesDataBST.addNode(String.valueOf(i), _data[i]);
            }
        }
    }

    /**
     * Returns a string representation of the Series object.
     */
    public String toString() {
        // TODO: Implement toString method
        return seriesData.toString();
    }

    /**
     * Returns the length of the series object.
     */
    public int getLength() {
        // TODO: Implement getLength method
        return seriesData.getLength();
    }

    /**
     * Returns the row names of this Series object.
     */
    public String[] getRowNames() {
        // TODO: Implement getRowNames method
        return seriesData.getIndexArray();
    }

    /**
     * Returns the data of this Series object as strings.
     */
    public String[] getData() {
        // TODO: Implement getData method
        return seriesData.getDataArray();
    }

    /**
     * Adds a new pair of rowName and data at the end of the Series object.
     *
     * @param rn the row name to be added
     * @param d the Integer data value to be added
     */
    public void append(String rn, T d) {
        // TODO: Implement append method
        String index = rn;
        if(rn == null || rn == ""){
            index = String.valueOf(seriesData.getLength());
        }
        seriesData.appendNode(index, d);
        seriesDataBST.addNode(index,d);
    }

    /**
     * Retrieves a data value given a row name.
     *
     * @param rn the row name to search for
     */
    public T loc(String rn) throws NullPointerException,IllegalArgumentException{
        // TODO: Implement loc method
        if(rn == null){
            throw new NullPointerException("loc(String rn): rn can't be null");
        }
        if(rn.equals("")){
            throw new IllegalArgumentException("loc(String rn): rn can't be an empty string");
        }
        if(seriesDataBST.searchNode(rn) == null){
            return null;
        } else {
            return (T) seriesDataBST.searchNode(rn).getData();
        }
    }

    /**
     * Retrieves multiple data values given an array of row names.
     *
     * @param rn an array of row names to search for
     */
    public T[] loc(String[] rn)throws NullPointerException,IllegalArgumentException{
        // TODO: Implement loc method for multiple row names
        if(rn == null){
            throw new NullPointerException("loc(String[] rn): rn[] can't be null");
        }
        if(rn.length == 0){
            throw new IllegalArgumentException("loc(String[] rn): rn[] can't be an empty array");
        }
        T[] output = (T[])new Object[rn.length];
        for (int i = 0; i < rn.length; i++) {
            output[i] = loc(rn[i]);
        }
        return output;
    }

    /**
     * Retrieves a data value based on its integer index.
     *
     * @param ind the index of the data to retrieve
     */
    public T iloc(int ind) {
        // TODO: Implement iloc method
        try{
            if(ind < 0 || ind >= seriesData.getLength()){
                throw new IndexOutOfBoundsException();
            } else {
                String[] rowNames = seriesData.getIndexArray();
                return (T)seriesDataBST.searchNode(rowNames[ind]).getData();
            }
        } catch(IndexOutOfBoundsException e){
            System.out.println("the index " + String.valueOf(ind) + " is not valid.. returning null");
            return null;
        }
    }

    /**
     * Removes a pair of rowname-data from the Series, given a row name.
     *
     * @param rn the row name of the pair to be removed
     */
    public boolean drop(String rn) throws NullPointerException,IllegalArgumentException{
        // TODO: Implement drop method
        if(rn == null){
            throw new NullPointerException("drop(String rn): rn can't be null");
        }
        if(rn.equals("")){
            throw new IllegalArgumentException("drop(String rn): rn can't be an empty String");
        }
        try{
            seriesData.removeNode(rn);
            seriesDataBST.removeNode(rn);
            return true;
        } catch(IllegalArgumentException e){
            return false;
        }
    }

    /**
     * Replace any data value that is null with value.
     *
     * @param value the new value to replace null values
     */
    public void fillNull(T value) throws IllegalArgumentException {
        // TODO: Implement fillNull method
        if (value == null) {
            throw new IllegalArgumentException("fillNull(T value): value can't be null");
        }
        String[] rowNames = seriesData.getIndexArray();
        String[] data = seriesData.getDataArray();
        for (int i = 0; i < seriesData.getLength(); i++) {
            if(data[i].equals("null")){
                seriesData.updateNode(rowNames[i], value);
                seriesDataBST.updateNode(rowNames[i], value);
            }
        }


    }
}