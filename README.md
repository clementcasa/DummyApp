# DummyApp
Test project for architecture showcase

## Architecture
- Clean architecture
- RxJava
- Hilt
- Room
- Retrofit
- Mockito

## How to use
You have 3 buildVariants
- mockDebug : A build on Debug but this build won't call the api but will use fake locals data
- realDebug : A build on Debug but with api calls
- realRelease : A build for prod

## Run jacoco
To run jacoco you can use this command :
`./gradlew testRealDebugUnitTest JacocoDebugTestReport`
It will generate the Jacoco report you can access here in the build/reports/jacoco

## Be careful
Do not run unit tests on mockDebug