# App Base MVVM
Application Base for MVVM

This library are used in MVVM coding structure for application base module.

## How to use.
To get a Git project into your build:

<b>Step 1.1</b> Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:

```java
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

<b>Step 1.2</b> Add the JitPack repository to your build file
Add it in your settings.gradle.kts at the end of repositories:

```kotlin
    dependencyResolutionManagement {
        repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
        repositories {
            ...
            maven { url = uri("https://jitpack.io") }
        }
    }
```

<b>Step 2.1</b> Add the dependency for JAVA.

```java
	dependencies {
        implementation 'com.github.ashish99799:app_base_mvvm:1.0.2'
    }
```

<b>Step 2.2</b> Add the dependency for KOTLIN.

```kotlin
	dependencies {
        implementation("com.github.ashish99799:app_base_mvvm:1.0.2")
	}
```
