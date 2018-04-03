package com.example.rishabh.androiddatabasearchitecture;


import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Word.class}, version = 1)

public abstract class WordRoomDatabase extends RoomDatabase{

    public abstract WordDao wordDao();

    private static WordRoomDatabase INSTANCE ;

    public static WordRoomDatabase getINSTANCE(final Context context){

        if(INSTANCE == null) {
            synchronized (WordRoomDatabase.class){
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), WordRoomDatabase.class, "word")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE ;
    }


    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){


        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void,Void,Void>{

        WordDao dao;
        String [] words = {"dolphin", "crocodile", "cobra"};
        public PopulateDbAsync(WordRoomDatabase instance) {
            dao = instance.wordDao() ;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAll();
            for( int i = 0; i <= words.length - 1; i++) {
                Word word = new Word(words[i]);
                dao.insert(word);
            }

            return null;
        }
    }

}
