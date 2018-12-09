package com.ccs.s19.noted;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

class NoteAdapter extends RecyclerView.Adapter<NoteHolder> {

    private ArrayList<NoteModel> noteList;

    public NoteAdapter() {
        noteList = new ArrayList<NoteModel>();

        // Create sample notes.
//        noteList.add(new NoteModel("heyyyyyy"));
//        noteList.add(new NoteModel("alohaaaa"));
//        noteList.add(new NoteModel("The FitnessGram™ Pacer Test is a multistage aerobic capacity test that progressively gets more difficult as it continues. "));
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //(5) An inflater is again used to populate the view. This information can be directly
        //    taken from a layout.
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.note_row, viewGroup, false);

        //(6) The view created must be given to a holder. The holder will serve as the in-between
        //    system that interacts with the view.
        NoteHolder holder = new NoteHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder noteHolder, int position) {
//        noteHolder.setIcon(noteList.get(position).getImage());
        noteHolder.setTextNote(noteList.get(position).getText());
//        noteHolder.set
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    //(9) Information can be added later on but the notifyItemInserted function must be called to
    //    tell the system a new piece of information is added.
    public void addItem(int uid, String text, int img, String grp){
        noteList.add(new NoteModel(uid, text, img, grp));
        notifyItemInserted(noteList.size()-1);
        Log.d("NOTE ADAPTER ACTIVITY", "Added Item!!!");
    }

}
