package br.com.clearsys.professorama.br.com.clearsys.professorama.br.com.clearsys.professorama.novaAtividade;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;

import br.com.clearsys.professorama.br.com.clearsys.professorama.br.com.clearsys.professorama.adapters.ListAdapterView;

public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
    private AdapterView.OnItemClickListener onItemClickListener;
    GestureDetector gestureDetector;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public RecyclerItemClickListener(Context context, OnItemClickListener listener) {
        onItemClickListener = (AdapterView.OnItemClickListener) listener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    @SuppressWarnings("SingleStatementInBlock")
    @Override
    public boolean onInterceptTouchEvent( RecyclerView recyclerView,  MotionEvent motionEvent) {

        View childView = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
        if (childView != null && onItemClickListener != null && gestureDetector.onTouchEvent(motionEvent))
            onItemClickListener.onItemClick(null,childView, recyclerView.getChildAdapterPosition(childView), 0 );


        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }
}
