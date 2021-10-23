package kr.jung.android_databinding_recyclerview;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//!< room dao를 활용한 데이터 액세스
@Dao
public interface MemoDao {

    //!< 말 그대로 삽입 (데이터 베이스에 Memo클래스 삽입)
    @Insert
    void insert(Memo memo);

    //!< 데이터 베이스에 입력된 쿼리의 수를 파악
    //!< 파일입출력을 하지 않아도 앱을 켰을 때 자동으로 입력했던 내용이 저장
    @Update
    void update(Memo memo);

    //!< 데이터 베이스에서 삭제된 쿼리를 파악
    //!< 앱을 사용할 때도 유동적으로 쿼리를 삭제할 수 있음
    @Delete
    void delete(Memo memo);

    //!< DAO 클래스에서 사용하는 주석으로
    //!< 여기서(↓)는 사용자를 로드하는 단순한 쿼리로 이용
    //!< 여기서(↓) 할당한 쿼리는 Memo 클래스에서 선언한 Room 라이브러리
    @Query("SELECT * FROM memoTable")
    //!< 라이브 데이터 -> 제목을 설정할 시 setText를 사용하지 않아도 자동으로 사용자가 입력한 제목으로 바뀜
    //!< 간단히 말해서 앱 사용중에 앱 UI가 자동으로 업데이트 되는 기능
    LiveData<List<Memo>> getAll();

    //!< 데이터 베이스 삭-제
    @Query("DELETE FROM memoTable")
    void deleteAll();

}
