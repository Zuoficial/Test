package smoowy.recycleviewrealm.Adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

public class SimpleTouchCallback extends ItemTouchHelper.Callback {

    private static SwipeListener mSwipeListener;


    public SimpleTouchCallback(SwipeListener listener) {
        mSwipeListener = listener;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(ItemTouchHelper.UP|ItemTouchHelper.DOWN,ItemTouchHelper.START|ItemTouchHelper.END);
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        mSwipeListener.onSwipe(viewHolder.getLayoutPosition());
    }
}
