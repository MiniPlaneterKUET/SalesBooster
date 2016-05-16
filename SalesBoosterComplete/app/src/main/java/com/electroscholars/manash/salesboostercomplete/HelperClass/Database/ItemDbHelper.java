package com.electroscholars.manash.salesboostercomplete.HelperClass.Database;

/**
 * Created by Manash on 5/16/2016.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class ItemDbHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "items";
    public static final int COLNO__ID = 0;
    public static final int COLNO_ITEM_NAME = 1;
    public static final int COLNO_ITEM_PRICE = 2;

    public static final String[] TABLE_COLUMNS = new String[] {"_id", "item_name", "item_price"};

    private static final String DB_FILENAME = "items_ver2.db";
    private static final int DB_VERSION = 1;

    private static final String INITIAL_SCHEMA = "create table items (" +
            "_id integer primary key autoincrement," +
            "item_name varchar(100) not null,"+
            "item_price integer not null" + ")";

    private static final String INITIAL_DATA_INSERT = "insert into items (item_name, item_price) values " +
            "('Potato', 12)," +
            "('Raddish', 14)," +
            "('Pumpkin', 120)";

    public ItemDbHelper(Context context){
        super(context, DB_FILENAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL(INITIAL_SCHEMA);
        sqLiteDatabase.execSQL(INITIAL_DATA_INSERT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        if (oldVersion == 1){

        }
    }
}
