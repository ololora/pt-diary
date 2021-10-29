package co.scrobbler.ptdiary;

import android.app.Application;

import co.scrobbler.ptdiary.db.DbModule;

public class MyApplication extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .dbModule(new DbModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
