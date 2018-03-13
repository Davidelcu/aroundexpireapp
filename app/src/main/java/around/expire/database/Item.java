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
    private int insertionDate;
    private int expireDate;

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
    public int getInsertionDate() {
        return insertionDate;
    }

    /**
     *
     * setInsertionDate
     *
     * @param insertionDate insertiondate
     */
    public void setInsertionDate(int insertionDate) {
        this.insertionDate = insertionDate;
    }

    /**
     *getExpireDate
     *
     * @return expireDate
     */
    public int getExpireDate() {
        return expireDate;
    }

    /**
     *setExpireDate
     *
     * @param expireDate expiredate
     */
    public void setExpireDate(int expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * toString override
     *
     * @return name + insertionDate + expireDate
     */
    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", insertionDate=" + insertionDate +
                ", expireDate=" + expireDate +
                '}';
    }
}
