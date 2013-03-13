package com.andiEngine.tests;

import com.andiEngine.AndiEngineView;

import android.os.Bundle;
import android.app.Activity;
import android.view.Window;

public class MainActivity extends Activity {

	MyFirstScene scene;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Set fullscreen
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		// Create scene and add to engine
		AndiEngineView engineView = new AndiEngineView(this);
		scene = new MyFirstScene(this);
		engineView.setScene(scene);
		
		// Set content view
		setContentView(engineView);//R.layout.activity_main);
	}

}
