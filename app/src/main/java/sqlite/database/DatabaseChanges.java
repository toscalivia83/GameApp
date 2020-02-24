package sqlite.database;

public class DatabaseChanges {

    DatabaseHelper db;

    public void initialiseDbWithValues() {
        long characterId1 = db.insertCharacter(1, "@drawable/horse1","black,canter");
        long characterId2 = db.insertCharacter(2, "@drawable/horse2","brown,standing");
        long characterId3 = db.insertCharacter(3, "@drawable/horse3","white,canter");
        long characterId4 = db.insertCharacter(4, "@drawable/horse4","baie,standing");
        long characterId5 = db.insertCharacter(5, "@drawable/horse5","grey,canter");
        long characterId6 = db.insertCharacter(6, "@drawable/horse6","brown,headonly,");
        long characterId7 = db.insertCharacter(7, "@drawable/horse7","black,white,headonly");
        long characterId8 = db.insertCharacter(8, "@drawable/horse8","shadow,rearing");
        long characterId9 = db.insertCharacter(9, "@drawable/horse9","grey,canter,lounge");
        long characterId10 = db.insertCharacter(10, "@drawable/horse10","baie,headonly,worried");
        long characterId11 = db.insertCharacter(11, "@drawable/horse11","baie,tired");
        long characterId12 = db.insertCharacter(12, "@drawable/horse12","isabelle,headonly");
        long characterId13 = db.insertCharacter(13, "@drawable/horse13","brown,standing");
        long characterId14 = db.insertCharacter(14, "@drawable/horse14","grey,headonly,smart");
        long characterId15 = db.insertCharacter(15, "@drawable/horse15","brown,white,small,sitted");
    }
}
