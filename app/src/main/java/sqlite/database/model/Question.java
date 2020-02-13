package sqlite.database.model;

public class Question {
    public static final String TABLE_NAME = "question";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_SENTENCE = "sentence";
    public static final String COLUMN_CHARACTERISTICS = "characteristics";

    private int id;
    private String sentence;
    private String characteristics;

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_SENTENCE + " TEXT,"
                    + COLUMN_CHARACTERISTICS + " TEXT" // TODO: should be Array[text]
                    + ")";

    public Question() {
    }

    public Question(int id, String sentence, String characteristics) {
        this.id = id;
        this.sentence = sentence;
        this.characteristics = characteristics;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }
}
