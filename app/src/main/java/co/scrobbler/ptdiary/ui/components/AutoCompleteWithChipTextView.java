package co.scrobbler.ptdiary.ui.components;

import android.content.Context;
import android.graphics.RectF;
import android.text.Editable;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.chip.ChipDrawable;

import co.scrobbler.ptdiary.R;

public class AutoCompleteWithChipTextView extends androidx.appcompat.widget.AppCompatAutoCompleteTextView {
    private AdapterView.OnItemClickListener userItemClickListener;
    private OnItemRemoveListener onItemRemoveListener;
    private ChipDrawable chip;
    private Object selectedItem;

    public AutoCompleteWithChipTextView(Context context) {
        super(context);
        initItemClickListener();
    }

    public AutoCompleteWithChipTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initItemClickListener();
    }

    public AutoCompleteWithChipTextView(@NonNull Context context, @Nullable AttributeSet attrs,
                                        int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initItemClickListener();
    }

    @Override
    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
            this.userItemClickListener = onItemClickListener;
    }

    private void initItemClickListener() {
        AdapterView.OnItemClickListener defaultItemClickListener = (parent, view, position, id) -> {
            selectedItem = getAdapter().getItem(position);
            setText(selectedItem.toString());
            Editable editable = getEditableText();

            chip = ChipDrawable.createFromResource(getContext(), R.xml.exercise_chip);
            chip.setText(editable.subSequence(0, editable.length()));
            chip.setBounds(0, 0, chip.getIntrinsicWidth(), chip.getIntrinsicHeight());
            ImageSpan span = new ImageSpan(chip);
            editable.setSpan(span, 0, editable.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            if (userItemClickListener != null) {
                userItemClickListener.onItemClick(parent, view, position, id);
            }
        };
        super.setOnItemClickListener(defaultItemClickListener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(chip == null)
            return super.onTouchEvent(event);

        RectF rect = new RectF();
        chip.getCloseIconTouchBounds(rect);
        float eventX = event.getX();
        if(eventX >= rect.left && eventX <= rect.right) {
            getEditableText().clear();

            if(onItemRemoveListener != null) {
                onItemRemoveListener.onItemRemove(selectedItem);
            }
        }

        return super.onTouchEvent(event);
    }

    public void setOnItemRemoveListener(OnItemRemoveListener onItemRemoveListener) {
        this.onItemRemoveListener = onItemRemoveListener;
    }

    public interface OnItemRemoveListener {
        void onItemRemove(Object item);
    }
}
