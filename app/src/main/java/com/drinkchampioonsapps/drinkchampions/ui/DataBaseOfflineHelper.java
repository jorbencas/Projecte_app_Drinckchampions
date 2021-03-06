package com.drinkchampioonsapps.drinkchampions.ui;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseOfflineHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "offline";
	private static final int DATABASE_VERSION = 1;

	//Sentencia para crear la BD
	private static final String DATABASE_CREATE = "create table offline (_id integer primary key autoincrement, "
			+ "category text not null, summary text not null, description text not null, number text not null, imagelocal text not null, imagedir text not null, moreinfo text not null, locate text not null);";




	public DataBaseOfflineHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	//Este metodo es llamado cuando se crea la BD
	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	//Metodo que se llama cada vez que se actualiza la BD
	//Incrementa la version
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		Log.w(DataBaseOfflineHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
		database.execSQL("DROP TABLE IF EXISTS offline");
		onCreate(database);
	}

}
