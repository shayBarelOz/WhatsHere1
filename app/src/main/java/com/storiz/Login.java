package com.storiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.storiz.custom.CustomActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

/**
 * The Activity Login is launched after the Splash screen. You need to write
 * your logic for actual Login.
 */
public class Login extends CustomActivity
{

	private TextView info;
	private LoginButton loginButton;
	private CallbackManager callbackManager;
	private AccessToken accessToken;
	private static Bundle UserValues ;
	private Intent seconderyIntent = null;
	private boolean requestNotFinished = true;
	private static Context context;


	/* (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setupView();
		UserValues = new Bundle();
		Login.context = getApplicationContext();


	}

	/**
	 * Setup the click & other events listeners for the view components of this
	 * screen. You can add your logic for Binding the data to TextViews and
	 * other views as per your need.
	 */
	private void setupView()
	{

		FacebookSdk.sdkInitialize(getApplicationContext());
		callbackManager = CallbackManager.Factory.create();

		setContentView(R.layout.login);
		//setContentView(R.layout.login);
		info = (TextView)findViewById(R.id.info);
		loginButton = (LoginButton)findViewById(R.id.btnLogin);

		List<String> permissionNeeds = Arrays.asList("user_photos", "email", "user_birthday", "public_profile");
		loginButton.setReadPermissions(permissionNeeds);

		loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
			@Override
			public void onSuccess(LoginResult loginResult) {

				accessToken = loginResult.getAccessToken();
				info.setText(
						"User ID: "
								+ loginResult.getAccessToken().getUserId()
								+ "\n" +
								"Auth Token: "
								+ loginResult.getAccessToken().getToken()
				);

			}

			@Override
			public void onCancel() {
				info.setText("Login attempt canceled.");
			}

			@Override
			public void onError(FacebookException e) {
				info.setText("Login attempt failed.");

			}
		});




		//Button b = (Button) setTouchNClick(R.id.btnReg);
		//b.setText(Html.fromHtml(getString(R.string.sign_up)));

		setTouchNClick(R.id.btnLogin);
		//setTouchNClick(R.id.btnForget);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		//callbackManager.onActivityResult(requestCode, resultCode, data);
		super.onActivityResult(requestCode, resultCode, data);
		callbackManager.onActivityResult(requestCode, resultCode, data);


		// getting out email and name
		GraphRequest request = GraphRequest.newMeRequest(
				accessToken,
				new GraphRequest.GraphJSONObjectCallback() {
					@Override
					public void onCompleted(
							final JSONObject object,
							GraphResponse response) {
						// Application code
						final JSONObject jsonObject = response.getJSONObject();

//						try {

							Handler mainHandler = new Handler(getAppContext().getMainLooper());

							Runnable myRunnable = new Runnable() {
								@Override
								public void run() {

									try {

										String name = "";
										String email = "";
										String birthday = "";
										String gender = "";
										String picture = "";
										String id = "";

										id = jsonObject.getString("id");
										name = jsonObject.getString("name");
										email = jsonObject.getString("email");
										birthday = jsonObject.getString("birthday");
										gender = jsonObject.getString("gender");
										picture = jsonObject.getJSONObject("picture").getJSONObject("data").getString("url");

										System.out.println(picture);

										// putting all values in map
										UserValues.putString("id", id);
										UserValues.putString("name", name);
										UserValues.putString("email", email);
										UserValues.putString("birthday", birthday);
										UserValues.putString("gender", gender);
										UserValues.putString("picture", picture);

										Intent i = new Intent(Login.this, MainActivity.class);
										i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
										i.putExtra("UserValues", UserValues);
										startActivity(i);
										finish();

										} catch (JSONException e) {
									requestNotFinished = false;
									e.printStackTrace();
								}

								} // This is your code
							};
							mainHandler.post(myRunnable);


//			Intent i = new Intent(Login.this, MainActivity.class);
//			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//			i.putExtra("UserValues",UserValues);
//			startActivity(i);
//			finish();

							//requestNotFinished = false;

//							seconderyIntent = new Intent(Login.this, MainActivity.class);
//							seconderyIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//							seconderyIntent.putExtra("UserValues",UserValues);
//							startActivity(seconderyIntent);
////							finish();

//
							// run this from the main thread !!!
//							(new Handler(Looper.getMainLooper())).post(new Runnable() {
//								@Override
//								public void run() {
//									Intent i = new Intent(Login.this, MainActivity.class);
//									i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//									i.putExtra("UserValues",UserValues);
//									startActivity(i);
//									finish();
//								}
//							});
//		                  Intent i = new Intent(, MainActivity.class);
//			              i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//			              i.putExtra("UserValues",UserValues);
//			              startActivity(i);
//							finish();

//							System.out.println(nombre);
//							System.out.println(email);
//							System.out.println(id);

//						} catch (JSONException e) {
//							requestNotFinished = false;
//							e.printStackTrace();
//						}
					}
				});
		Bundle parameters = new Bundle();
		parameters.putString("fields", "id,name,email,birthday,gender,picture");
		request.setParameters(parameters);
		request.executeAsync();
		//request.getParameters();
		//request.setCallback();
//		try {
//			Thread.sleep(10000);
//
//			Intent i = new Intent(Login.this, MainActivity.class);
//			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//			i.putExtra("UserValues",UserValues);
//			startActivity(i);
//			finish();
//		}catch (InterruptedException e){
//			e.printStackTrace();
//		}



//		if (resultCode == RESULT_OK) {
//			Toast.makeText(this,"result_ok",Toast.LENGTH_LONG).show();
//		}
//
//		while (requestNotFinished){
//			//MainThread.sleep
//			Toast.makeText(this,"inside while loop !!!!!",Toast.LENGTH_LONG).show();
//		}
//
//		if (!requestNotFinished){
//			Intent i = new Intent(this, MainActivity.class);
//			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//			i.putExtra("UserValues",UserValues);
//			startActivity(i);
//			finish();
//		}


			//if (requestCode == 1 && UserValues.size() > 0 ) {
			// if all fine :
//			Intent i = new Intent(this, MainActivity.class);
//			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//			i.putExtra("UserValues",UserValues);
//			startActivity(i);
//			finish();
		//}



	}

	public static Context getAppContext() {
		return Login.context;
	}

	/* (non-Javadoc)
	 * @see com.taxi.custom.CustomActivity#onClick(android.view.View)
	 */
//	@Override
//	public void onClick(View v)
//	{
//		super.onClick(v);
//		if (v.getId() == R.id.btnLogin)
//		{
//			Intent i = new Intent(this, MainActivity.class);
//			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//			startActivity(i);
//			finish();
//		}
//	}
}
