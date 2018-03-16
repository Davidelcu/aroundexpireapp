package around.expire.database;

/**
 * Created by Utente on 05/03/2018.
 *
 * @author Davide
 * @version 0.0.1
 */

public class Item {
    private long id;
    private String name;
    private String insertionDate;
    private String expireDate;

    /**
     *getId
     *
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     *setId
     *
     * @param id id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     *getName
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * setName
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getInsertionDate
     *
     * @return int insertionDate
     */
    public String getInsertionDate() {
        return insertionDate;
    }

    /**
     *
     * setInsertionDate
     *
     * @param insertionDate insertiondate
     */
    public void setInsertionDate(String insertionDate) {
        this.insertionDate = insertionDate;
    }

    /**
     *getExpireDate
     *
     * @return expireDate
     */
    public String getExpireDate() {
        return expireDate;
    }

    /**
     *setExpireDate
     *
     * @param expireDate expiredate
     */
    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * toString override
     *
     * @return name + insertionDate + expireDate
     */
    @Override
    public String toString() {
        return "Product: " +
                 name + " - Added: " + insertionDate +
                " - Expire: " + expireDate;
    }
}
