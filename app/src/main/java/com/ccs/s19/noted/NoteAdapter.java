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

class NoteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

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
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        //View view = inflater.inflate(R.layout.note_row, viewGroup, false);

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.note_row, viewGroup, false);

        switch (i){
            case NoteModel.NOTE_TYPE:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.note_row, viewGroup, false);
                break;
                //return new NoteHolder(view);
            case NoteModel.CHECK_TYPE:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.checklist_row, viewGroup, false);
                break;
                //return new CheckListHolder(view);
            case NoteModel.IMG_TYPE:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.photo_row, viewGroup, false);
                break;
                //return new ImgHolder(view);
        }

        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int itemPosition = mRecyclerView.getChildLayoutPosition(v);

                int type = noteList.get(itemPosition).getType();
                int id = noteList.get(itemPosition).getId();
                String text = noteList.get(itemPosition).getText();
                int ImageId = noteList.get(itemPosition).getImageId();
                String group = noteList.get(itemPosition).getGroup();
                boolean isPinned = noteList.get(itemPosition).isPinned();
                String hour = noteList.get(itemPosition).getHour();
                String minute = noteList.get(itemPosition).getMinute();
                String second = noteList.get(itemPosition).getSecond();
//                String item = id +" - " +text +" - " +ImageId +" - "
//                         +group +" - " +isPinned;
//                Toast.makeText(mainAct, item, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(mainAct.getApplicationContext(), EditNoteActivity.class);
                switch (type){
                    case NoteModel.NOTE_TYPE:
                        intent = new Intent(mainAct.getApplicationContext(), EditNoteActivity.class);
                        break;
                    //return new NoteHolder(view);
                    case NoteModel.CHECK_TYPE:
                        intent = new Intent(mainAct.getApplicationContext(), EditNoteActivity.class);
                        break;
                    //return new CheckListHolder(view);
                    case NoteModel.IMG_TYPE:
                        intent = new Intent(mainAct.getApplicationContext(), EditNoteActivity.class);
                        break;
                    //return new ImgHolder(view);
                }

                intent.putExtra("IDKEY", id);
                intent.putExtra("IMAGEKEY", text);
                intent.putExtra("TEXTKEY", ImageId);
                intent.putExtra("GROUPKEY", group);
                intent.putExtra("PINKEY", isPinned);
                intent.putExtra("HOUR", hour);
                intent.putExtra("MINUTE", minute);
                intent.putExtra("SECOND", second);
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
                        +noteList.get(itemPosition).isPinned()+" - "
                        +noteList.get(itemPosition).getHour()+" - "
                        +noteList.get(itemPosition).getMinute()+" - "
                        +noteList.get(itemPosition).getSecond();
                Toast.makeText(mainAct, item, Toast.LENGTH_LONG).show();
                return false;
            }
        });

        switch (i){
            case NoteModel.NOTE_TYPE:
                return new NoteHolder(view);
            case NoteModel.CHECK_TYPE:
                return new CheckListHolder(view);
            case NoteModel.IMG_TYPE:
                return new ImgHolder(view);
            default:
                return new NoteHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        NoteModel object = noteList.get(position);

        if(object != null){
            switch (object.getType()){
                case NoteModel.NOTE_TYPE:
                    ((NoteHolder)holder).setTextNote(noteList.get(position).getText());
                    ((NoteHolder)holder).setGroupLabel(noteList.get(position).getGroup());  // TODO: set group
                    ((NoteHolder)holder).setPin(noteList.get(position).isPinned());
                    ((NoteHolder)holder).setTime(noteList.get(position).getHour(), noteList.get(position).getMinute(), noteList.get(position).getSecond());
                case NoteModel.CHECK_TYPE:
                    ((CheckListHolder)holder).setTextNote(noteList.get(position).getText());
                    ((CheckListHolder)holder).setTextNote(noteList.get(position).getText());
                    ((CheckListHolder)holder).setGroupLabel(noteList.get(position).getGroup());  // TODO: set group
                    ((CheckListHolder)holder).setPin(noteList.get(position).isPinned());
                    ((CheckListHolder)holder).setTime(noteList.get(position).getHour(), noteList.get(position).getMinute(), noteList.get(position).getSecond());
            }
        }
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public int getItemViewType(int position){
        if(noteList.get(position).getType() != 0 || noteList.get(position).getType() != 1 || noteList.get(position).getType() != 2)
            return noteList.get(position).getType();
        else
            return 1;
    }

    public void addItem(int uid, String text, int img, String grp, boolean isPinned, String hour, String minute, String second){
        noteList.add(new NoteModel(uid, text, img, grp, isPinned, hour, minute, second));
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
