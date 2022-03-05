package com.ankit.platformspecificcode;

import androidx.annotation.NonNull;
import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;

public class MainActivity extends FlutterActivity {
  private static final String CHANNEL = "ankit.platform.demo";

  @Override
  public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
  super.configureFlutterEngine(flutterEngine);
    new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL)
        .setMethodCallHandler(
          (call, result) -> {
            if (call.method.equals("getCount")) {
              int count = call.argument("count");

              if (count != -1) {
                result.success(count + 1);
              } else {
                result.error("ERROR", "Something went wrong", null);
              }
            } else {
              result.notImplemented();
            }
          }
        );
  }
}