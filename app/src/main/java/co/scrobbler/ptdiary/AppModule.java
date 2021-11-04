package co.scrobbler.ptdiary;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    PtDiaryApp mApplication;

    public AppModule(PtDiaryApp application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    PtDiaryApp providesApplication() {
        return mApplication;
    }
}
