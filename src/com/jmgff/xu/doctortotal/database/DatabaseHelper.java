package com.jmgff.xu.doctortotal.database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.jmgff.xu.doctortotal.R;
import com.jmgff.xu.doctortotal.util.GMConstants;

public class DatabaseHelper {
	private final static String DATABASE_PATH = "/data"
			+ Environment.getDataDirectory().getAbsolutePath()
			+ "/com.jmgff.xu.doctortotal/databases/";

	private SQLiteDatabase database;
	private Context context;

	public DatabaseHelper(Context context) {
		this.context = context;
	}

	public boolean initDatabase() {
		try {
			String databaseFilename = DATABASE_PATH + GMConstants.DATABASE_NAME;
			File dir = new File(DATABASE_PATH);
			if (!dir.exists()) {
				dir.mkdir();
			}
			// 数据库文件
			File dbfile = new File(dir, GMConstants.DATABASE_NAME);

			if (!dbfile.exists()) {
				dbfile.createNewFile();
			}
			// 加载欲导入的数据库
			InputStream is = context.getResources().openRawResource(
					R.raw.totalscale);
			FileOutputStream fos = new FileOutputStream(dbfile);
			byte[] buffere = new byte[is.available()];
			is.read(buffere);
			fos.write(buffere);
			if (is != null) {
				is.close();
			}
			if (fos != null) {
				fos.flush();
				fos.close();
			}

			this.database = SQLiteDatabase.openOrCreateDatabase(
					databaseFilename, null);
			closeDatabase();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public SQLiteDatabase openDatabase() {

		String databaseFilename = DATABASE_PATH + GMConstants.DATABASE_NAME;
		// File dir = new File(DATABASE_PATH);
		// if (!dir.exists()) {
		// dir.mkdir();
		// }
		// // 数据库文件
		// File dbfile = new File(dir, GMConstants.DATABASE_NAME);
		// try {
		// if (!dbfile.exists()) {
		// dbfile.createNewFile();
		// }
		// // 加载欲导入的数据库
		// InputStream is = context.getResources().openRawResource(
		// R.raw.totalscale);
		// FileOutputStream fos = new FileOutputStream(dbfile);
		// byte[] buffere = new byte[is.available()];
		// is.read(buffere);
		// fos.write(buffere);
		// is.close();
		// fos.close();
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

		return SQLiteDatabase.openDatabase(databaseFilename, null,
				SQLiteDatabase.OPEN_READWRITE);
	}

	public void closeDatabase() {
		if (database.isOpen()) {
			this.database.close();
		}

	}
}
