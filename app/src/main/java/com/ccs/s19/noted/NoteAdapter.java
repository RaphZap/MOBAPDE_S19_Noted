package com.ccs.s19.noted;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

class NoteAdapter extends RecyclerView.Adapter<NoteHolder> {

    private ArrayList<NoteModel> noteList;
    MainActivity mainAct;
    RecyclerView mRecyclerView;


    public NoteAdapter(MainActivity main, RecyclerView mRecyclerView) {
        noteList = new ArrayList<NoteModel>();
        mainAct = main;
        this.mRecyclerView = mRecyclerView;

    }


    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.note_row, viewGroup, false);

        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int itemPosition = mRecyclerView.getChildLayoutPosition(v);

                int id = noteList.get(itemPosition).getId();
                String text = noteList.get(itemPosition).getText();
                int ImageId = noteList.get(itemPosition).getImageId();
                String group = noteList.get(itemPosition).getGroup();
                boolean isPinned = noteList.get(itemPosition).isPinned();
//                String item = id +" - " +text +" - " +ImageId +" - "
//                         +group +" - " +isPinned;
//                Toast.makeText(mainAct, item, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(mainAct.getApplicationContext(), EditNoteActivity.class);
                intent.putExtra("IDKEY", id);
                intent.putExtra("IMAGEKEY", text);
                intent.putExtra("TEXTKEY", ImageId);
                intent.putExtra("GROUPKEY", group);
                intent.putExtra("PINKEY", isPinned);
                mainAct.startActivity(intent);
            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                int itemPosition = mRecyclerView.getChildLayoutPosition(v);
                String item = noteList.get(itemPosition).getId()+" - "
                        +noteList.get(itemPosition).getText()+" - "
                        +noteList.get(itemPosition).getImageId()+" - "
                        +noteList.get(itemPosition).getGroup()+" - "
                        +noteList.get(itemPosition).isPinned();
                Toast.makeText(mainAct, item, Toast.LENGTH_LONG).show();
                return false;
            }
        });

        NoteHolder holder = new NoteHolder(view, mainAct);

        return holder;
    }



    @Override
    public void onBindViewHolder(@NonNull NoteHolder noteHolder, int position) {
//        noteHolder.setIcon(noteList.get(position).getImage());

        noteHolder.setTextNote(noteList.get(position).getText());
//        noteHolder.setImage(noteList.get(position).getImageId());  // TODO: set image
        noteHolder.setTextNote(noteList.get(position).getText());
        noteHolder.setGroupLabel(noteList.get(position).getGroup());  // TODO: set group
        noteHolder.setPin(noteList.get(position).isPinned());
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public void addItem(int uid, String text, int img, String grp, boolean isPinned){
        noteList.add(new NoteModel(uid, text, img, grp, isPinned));
        notifyItemInserted(noteList.size()-1);
        Log.d("NOTE ADAPTER ACTIVITY", "Added Item!!!");
    }

    public void clearItems(){
        while (noteList.size()>0) {
            noteList.remove(0);
            notifyItemRemoved(0);
        }
        Log.d("NOTE ADAPTER ACTIVITY", "Cleared Items!!!");
    }

}
