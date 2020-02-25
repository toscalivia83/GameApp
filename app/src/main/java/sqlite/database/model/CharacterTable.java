package sqlite.database.model;

public class CharacterTable {
    public static final String TABLE_NAME = "character";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_IMG_URL = "img_url";
    public static final String COLUMN_CHARACTERISTICS = "characteristics";

    private int id;
    private String img_url;
    private String characteristics;

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_IMG_URL + " TEXT,"
                    + COLUMN_CHARACTERISTICS + " TEXT" // TODO: should be Array[text]
                    + ")";

    public CharacterTable() {
    }

    public CharacterTable(int id, String img_url, String characteristics) {
        this.id = id;
        this.img_url = img_url;
        this.characteristics = characteristics;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }
}
