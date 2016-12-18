package com.storiz;

import android.content.Intent;
import android.os.Bundle;
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

	/* (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setupView();
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
						String nombre = "";
						String email = "";
						String id = "";
						try {
							nombre = jsonObject.getString("name");
							email =  jsonObject.getString("email");

							System.out.println(nombre);
							System.out.println(email);
							System.out.println(id);

						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				});
		Bundle parameters = new Bundle();
		parameters.putString("fields", "id,name,email");
		request.setParameters(parameters);
		request.executeAsync();

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
