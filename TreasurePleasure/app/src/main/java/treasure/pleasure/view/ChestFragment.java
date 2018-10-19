package treasure.pleasure.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import treasure.pleasure.R;
import treasure.pleasure.presenter.TreasurePleasurePresenter;

/**
 * Creates a view fragment showing the Chest.
 * @author David
 */

public class ChestFragment extends Fragment {
    TreasurePleasurePresenter mPresenter;
    private ImageButton btnClose;
    private Button btnStoreItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_chest, container, false);

        btnClose = (ImageButton) view.findViewById(R.id.close_button);
        btnStoreItems = (Button) view.findViewById(R.id.store_items_button);
        setupButtons();

        return view;
    }

    private void setupButtons() {

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.closeChestButtonClicked();
            }
        });

        btnStoreItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.storeItemsButtonClicked();
            }
        });
    }

    /**
     * Establish communication between TreasurePleasurePresenter and BackpackFragment
     *
     * @param presenter
     */
    public void setPresenter(TreasurePleasurePresenter presenter) {
        this.mPresenter = presenter;
    }
}
