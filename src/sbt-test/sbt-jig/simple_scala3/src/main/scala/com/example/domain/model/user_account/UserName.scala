package com.example.domain.model.user_account

/** ユーザ名
  */
case class UserName(value: String):
  def decorationName: String = s"$value"
