package kr.jung.android_databinding_recyclerview;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//!< 라이브데이터 기능을 사용하기 위해 데이터 베이스를 이용함
//!< Room 라이브러리를 활용한 데이터 베이스 정의
//!< Room은 안드로이드에서 기본 제공하는 라이브러리(단순 서버)라고 생각하면 됨
@Entity(tableName = "memoTable")
public class Memo {

    //!< Room에서 자동으로 객체(id)를 할당
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String contents;

    public Memo(String title, String contents){
        this.title = title;
        this.contents = contents;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }
    public String getContents(){
        return contents;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setContents(String in){
        this.contents = in;
    }

    @Override
    public String toString(){
        return "RecordData{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\''
                + '}';
    }
}
