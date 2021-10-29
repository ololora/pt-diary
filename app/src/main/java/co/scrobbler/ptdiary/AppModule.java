package co.scrobbler.ptdiary;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    MyApplication mApplication;

    public AppModule(MyApplication application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    MyApplication providesApplication() {
        return mApplication;
    }
}
