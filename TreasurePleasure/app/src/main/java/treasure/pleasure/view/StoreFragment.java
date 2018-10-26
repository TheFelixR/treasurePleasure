package treasure.pleasure.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import java.util.ArrayList;
import treasure.pleasure.R;
import treasure.pleasure.presenter.TreasurePleasurePresenter;

/**
 * Creates a dynamic list of products
 * depending on how many products the model
 * currently offers.
 *
 * @author Jesper
 */

public class StoreFragment extends Fragment implements OnClickListener {

  private TreasurePleasurePresenter mPresenter;
  private ImageButton btnCloseStore;
  private ArrayList<Integer> storeProducts;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.fragment_store, container, false);
    setupExitButton(view);
    TableLayout storeTable = view.findViewById(R.id.tableStore);
    storeProducts = mPresenter.getStoreProducts();

    setupStoreTable(storeTable);
    return view;
  }

  private void setupStoreTable(final TableLayout storeTable) {
    Context context = this.getContext();
    storeTable.removeAllViews();

    TableRow header = new TableRow(context);

    TextView name = new TextView(context);
    TextView price = new TextView(context);
    TextView nextValue = new TextView(context);
    TextView buy = new TextView(context);

    name.setText("Product name");
    price.setText("Price");
    nextValue.setText("New value");
    buy.setText("Buy item");

    storeTable.addView(header);

    header.addView(name);
    header.addView(price);
    header.addView(nextValue);
    header.addView(buy);

    for (int i = 0; i < this.storeProducts.size(); i++) {
      final Integer storeProduct = storeProducts.get(i);
      TableRow row = new TableRow(context);
      name = new TextView(context);
      price = new TextView(context);
      nextValue = new TextView(context);
      buy = new TextView(context);

      name.setText(mPresenter.getStoreProductName(storeProduct));
      price.setText(mPresenter.getStoreProductPrice(storeProduct));
      nextValue.setText(mPresenter.getStoreProductNextValue(storeProduct));
      buy.setText("BUY!");

      storeTable.addView(row);

      row.addView(name);
      row.addView(price);
      row.addView(nextValue);
      row.addView(buy);

      buy.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          mPresenter.buyStoreProduct(storeProduct);
          storeProducts = mPresenter.getStoreProducts();
          setupStoreTable(storeTable);
        }
      });
    }
  }

  private void setupExitButton(View view) {
    btnCloseStore = view.findViewById(R.id.close_store_button);
    btnCloseStore.setOnClickListener(this);
  }

  public void setPresenter(TreasurePleasurePresenter presenter) {
    mPresenter = presenter;
    presenter.setStoreView(this);
  }

  @Override
  public void onClick(View view) {

    switch (view.getId()) {
      case R.id.close_store_button:
        mPresenter.btnCloseStoreButtonClicked();
        break;
    }
  }

}

