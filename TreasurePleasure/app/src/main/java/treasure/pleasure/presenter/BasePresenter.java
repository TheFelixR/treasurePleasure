package treasure.pleasure.presenter;

import treasure.pleasure.model.TreasurePleasure;
import treasure.pleasure.model.TreasurePleasureFactory;
import treasure.pleasure.view.BaseActivity;

/**
 * @author John
 *
 * This is the superclass that every presenter will use. The Presenter handles logic regarding
 * fetching a model that is running in the application. It also has logic that handles attaching the
 * presenter to an activity.
 *
 *
 * @param <Activity> Java generic, that is forced to be a instance of BaseActivity. The programmer
 *                  This way the programmer are forced to use BaseActivities to use Basepresenter.
 *
 *
 * By letting presenters and activities the programmer dont have to think about attaching / detaching
 *                  / instanciating model. This will be done in the background by calling super()
 *                  constructor.
 */

public abstract class BasePresenter<Activity extends BaseActivity> {

  /**
   * A basePresenter that handles logic with instanciating / getting the instance of the model. By
   * extending this class, the programmer does not have to worry  about setting the model, but it is
   * done for them
   */
  protected TreasurePleasure model;

  protected Activity view;

  protected BasePresenter() {
    this.model = TreasurePleasureFactory.getInstance();

  }


  /**
   * attaches a view to the presenter
   */
  public void attachActivity(Activity activity) {
    this.view = activity;

  }

  /**
   * detaches a view to the presenter
   */
  public void detachActivity() {
    this.view = null;
  }


}