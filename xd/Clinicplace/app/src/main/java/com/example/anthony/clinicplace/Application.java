package com.example.anthony.clinicplace;

/**
 * Created by Anthony on 27/10/2017.
 */

import com.amazonaws.mobile.auth.userpools.CognitoUserPoolsSignInProvider;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobile.auth.core.IdentityManager;
import android.support.multidex.MultiDexApplication;


/**
 * Application class responsible for initializing singletons and other common components.
 */
public class Application extends MultiDexApplication {
    private static final String LOG_TAG = Application.class.getSimpleName();


    @Override
    public void onCreate() {
        super.onCreate();
        initializeApplication();

    }

    private void initializeApplication() {

        AWSConfiguration awsConfiguration = new AWSConfiguration(getApplicationContext());

        // If IdentityManager is not created, create it
        if (IdentityManager.getDefaultIdentityManager() == null) {
            IdentityManager identityManager =
                    new IdentityManager(getApplicationContext(), awsConfiguration);
            IdentityManager.setDefaultIdentityManager(identityManager);
        }

        // Add Amazon Cognito User Pools as Identity Provider.
        IdentityManager.getDefaultIdentityManager().addSignInProvider(
                CognitoUserPoolsSignInProvider.class);

    }
}