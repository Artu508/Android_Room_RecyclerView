package kr.jung.android_databinding_recyclerview;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/*
 * MemoDatabase 클래스는 추상클래스이다.
 * database객체를 여러곳에서 매번 생성하는 것이 어려워 싱글톤을 이용했다.
 */

//!< (대충 데이터 베이스를 사용하겠다는 줄)
@Database(entities = {Memo.class}, version = 1, exportSchema = false)
public abstract class MemoDatabase extends RoomDatabase {

    public abstract MemoDao memoDao();

    private static volatile MemoDatabase INSTANCE;

    public static MemoDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MemoDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MemoDatabase.class, "memo_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    /*
    //!< 해외 사이트에서 퍼온 자료
    //!< RoomDataBase에 처음부터 데이터가 들어가 있기를 원한다면 추가하도록 함
    //!< 우리가 만들 앱에는 사용자가 처음부터 직접 추가하기 때문에 해당사항 없음
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                MemoDao dao = INSTANCE.memoDao();
                dao.deleteAll();

                Memo memo = new Memo("Hello1","memo1");
                dao.insert(word);
                word = new Memo("Hello2","hello2");
                dao.insert(word);
            });
        }
    };
    */

    //DB 객체 제거
    public static void destroyInstance() {
        INSTANCE = null;
    }


}
