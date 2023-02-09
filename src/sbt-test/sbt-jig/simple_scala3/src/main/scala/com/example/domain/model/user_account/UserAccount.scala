package com.example.domain.model.user_account

import com.example.domain.model.contract.Contract

/** ユーザアカウント
  */
case class UserAccount(
    contract: Contract,
    userName: UserName
)
