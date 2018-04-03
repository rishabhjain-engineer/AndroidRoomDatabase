package com.example.rishabh.androiddatabasearchitecture;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.lang.reflect.Parameter;
import java.util.List;

public class WordRepository {

    private WordDao mWordDao ;
    private LiveData<List<Word>> mAllWords ;


    public WordRepository(Application applicationContext){

        WordRoomDatabase wordRoomDatabase =  WordRoomDatabase.getINSTANCE(applicationContext);
        mWordDao = wordRoomDatabase.wordDao();
        mAllWords = mWordDao.getAllWords() ;

    }


    LiveData<List<Word>> getAllWords(){
        return mAllWords ;
    }


    public void insert(Word word){

        new insertAsyncTask(mWordDao).execute(word);

    }

    
    private static class insertAsyncTask extends AsyncTask<Word,Void,Void>{

        WordDao daoObject ;

        public insertAsyncTask(WordDao mWordDao) {
            this.daoObject = mWordDao ;
        }

        @Override
        protected Void doInBackground(Word... words) {
            daoObject.insert(words[0]);
            return null;
        }
    }
    

}
