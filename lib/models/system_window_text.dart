import 'package:flutter/material.dart';
import 'package:untitled1/system_alert_window.dart';
import 'package:untitled1/utils/commons.dart';

class SystemWindowText {
  String text;
  double? fontSize;
  Color? textColor;
  FontWeight? fontWeight;
  SystemWindowPadding? padding;

  SystemWindowText(
      {required this.text,
      this.fontSize,
      this.fontWeight,
      this.textColor,
      this.padding});

  Map<String, dynamic> getMap() {
    final Map<String, dynamic> map = <String, dynamic>{
      'text': text,
      'fontSize': fontSize ?? 14.0,
      'fontWeight': Commons.getFontWeight(fontWeight),
      'textColor':
          textColor?.value.toSigned(32) ?? Colors.black.value.toSigned(32),
      'padding': padding?.getMap(),
    };
    return map;
  }
}
