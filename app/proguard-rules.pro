# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.

# Keep calculator expression evaluation
-keep class com.flow.calculator.data.model.** { *; }
-keep class com.flow.calculator.domain.calculator.** { *; }

# Hilt
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }
-keep class * extends dagger.hilt.android.internal.managers.ComponentSupplier { *; }

# Compose
-keep class androidx.compose.** { *; }
