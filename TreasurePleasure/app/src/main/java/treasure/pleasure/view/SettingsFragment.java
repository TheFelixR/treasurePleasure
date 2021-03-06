package treasure.pleasure.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;
import treasure.pleasure.R;
import treasure.pleasure.presenter.TreasurePleasurePresenter;

/**
 * TODO
 *
 * @author oskar
 */

public class SettingsFragment extends Fragment {

  private TreasurePleasurePresenter mPresenter;
  private Button btnChangeUsername;
  private ToggleButton toggleGodMode;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.fragment_settings, container, false);
    btnChangeUsername = (Button) view.findViewById(R.id.changeUsernameButton);
    toggleGodMode = (ToggleButton) view.findViewById(R.id.toggleGodMode);

    toggleGodMode.setChecked(mPresenter.isGodMode());

    /**
     * The presenter is called with given input if "Change username" is pressed
     */
    btnChangeUsername.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        EditText newUsername = (EditText) view.findViewById(R.id.usernameInput);
        String usernameAsString = newUsername.getText().toString();
        mPresenter.changeUsername(usernameAsString);
      }
    });

    toggleGodMode.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.toggleGodMode(toggleGodMode.isChecked());
      }
    });

    return view;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    setSubtitleText(mPresenter.getUsername());
    setAvatar(mPresenter.getAvatar());
  }

  public void setPresenter(TreasurePleasurePresenter presenter) {
    mPresenter = presenter;
    presenter.setSettingsView(this);
  }

  public void setSubtitleText(String text) {
    TextView t = (TextView) this.getView().findViewById(R.id.settingsSubtitle);
    t.setText(text);
  }

  public void setAvatar(int i) {
    ImageView iv = (ImageView) this.getView().findViewById(R.id.imageView);
    iv.setImageResource(i);
  }
}
