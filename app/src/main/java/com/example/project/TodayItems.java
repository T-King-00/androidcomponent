package com.example.project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.bson.Document;

import java.util.ArrayList;

import io.realm.mongodb.RealmResultTask;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;
import io.realm.mongodb.mongo.iterable.MongoCursor;


public class TodayItems extends Fragment {



    ArrayAdapter<String> arrayAdapter;

    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_today_items, container, false);
        listView=(ListView) view.findViewById(R.id.listVItemsLayout);
        arrayAdapter=new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1);


        listView.setAdapter(arrayAdapter);
        return view  ;
    }

    @Override
    public void onStart() {
        super.onStart();
        arrayAdapter.clear();
        getData();
    }

    public ArrayAdapter getData(){

        MongoClient monClient= MainActivity.monUser.getMongoClient("mongodb-atlas");
        MongoDatabase monDb= monClient.getDatabase("ToDoListApp");
        MongoCollection<Document> mongoCollection=monDb.getCollection("data");
        Document queryFilter=new Document().append("userID",MainActivity.monUser.getId());
        RealmResultTask<MongoCursor<Document>> findTask=mongoCollection.find(queryFilter).iterator();

        findTask.getAsync(result->{
            String  data;
            if (result.isSuccess())
            {
                MongoCursor<Document> results=result.get();
                while (results.hasNext())
                {
                    Document currentDoc= results.next();
                    if(currentDoc.getString("note")!= null)
                    {
                        data=currentDoc.getString("note");
                        arrayAdapter.add(data);
                    }
                }
            }

        });
        return arrayAdapter;
    }
}

