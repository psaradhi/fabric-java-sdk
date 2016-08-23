
/**
 * The KeyValStore interface used for persistent storage.
 */
public interface KeyValStore {

    /**
     * Get the value associated with name.
     * @param name
     * @param cb function(err,value)
     */
    void getValue(String name, GetValueCallback cb)

    /**
     * Set the value associated with name.
     * @param name
     * @param cb function(err)
     */
    void setValue(String name, String value, ErrorCallback cb);

}
